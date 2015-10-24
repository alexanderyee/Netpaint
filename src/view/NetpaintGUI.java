package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Image;
import model.Line;
import model.Oval;
import model.PaintObject;
import model.Rectangle;

// Alex Yee
public class NetpaintGUI extends JFrame {
	private List<PaintObject> objects;
	private JScrollPane scpane;
	private JPanel canvas;

	public static void main(String[] args) {
		NetpaintGUI window = new NetpaintGUI();
		window.setVisible(true);
	}

	public NetpaintGUI() {
		this.setTitle("Netpaint - Alex Yee");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screensize);
		objects = new ArrayList<PaintObject>();
		
		objects.add(new Rectangle(500, 600, 600, 750, Color.RED));
		objects.add(new Image(50, 50, 200, 200, Color.BLACK));
		objects.add(new Line(250, 250, 450, 450, Color.GREEN));
		objects.add(new Line(250, 450, 450, 250, Color.GREEN));
		objects.add(new Oval(500, 250, 600, 350, Color.BLUE));
		canvas = new CanvasPanel();
		canvas.setPreferredSize(new Dimension(2048, 1024));
		this.add(new JColorChooser(), BorderLayout.SOUTH);
		scpane = new JScrollPane(canvas);
		this.add(scpane, BorderLayout.CENTER);
	}

	class CanvasPanel extends JPanel {
		public CanvasPanel() {
			this.setOpaque(true);
			this.setBackground(Color.WHITE);
			
		}
		@Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawShapesOnMe(g, objects);
		}
		private void drawShapesOnMe(Graphics g, List<PaintObject> shapes) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			for (PaintObject shape : shapes) {
				g2.setColor(shape.getColor());
				if (shape.isImage())
					g2.drawImage(shape.getImage(), shape.getXPoints()[0], shape.getYPoints()[0], null);
				else{
					g2.draw(shape.getShape());
					g2.fill(shape.getShape());
				}
			}
		}
	}
}
