package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
	private boolean clicked;
	private JColorChooser chooser;
	private JButton colorButton;
	private JPanel controls;
	private Color currColor;
	private int xClicked;
	private int yClicked;
	private String shapeSelected;
	private JRadioButton lineButton;
	private JRadioButton ovalButton;
	private JRadioButton rectangleButton;
	private JRadioButton imageButton;
	private ButtonGroup bGroup;
	public static void main(String[] args) {
		NetpaintGUI window = new NetpaintGUI();
		window.setVisible(true);
	}

	public NetpaintGUI() {
		this.setTitle("Netpaint - Alex Yee");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screensize);
		currColor = Color.BLACK;
		clicked = false;
		shapeSelected = "RECTANGLE";
		objects = new ArrayList<PaintObject>();
		canvas = new CanvasPanel();
		canvas.setPreferredSize(new Dimension(2048, 1024));
		scpane = new JScrollPane(canvas);
		this.add(scpane, BorderLayout.CENTER);

		controls = new JPanel();
		controls.setLayout(null);
		controls.setPreferredSize(new Dimension((int) screensize.getWidth(), 50));
		chooser = new JColorChooser();
		colorButton = new JButton("Color");
		colorButton.addActionListener(new ColorButtonL());
		colorButton.setSize(80, 20);
		colorButton.setLocation(0, 0);
		lineButton = new JRadioButton("Line");
		ovalButton = new JRadioButton("Oval");
		rectangleButton = new JRadioButton("Rectangle");
		imageButton = new JRadioButton("Image");
		lineButton.setSize(80, 20);
		ovalButton.setSize(80, 20);
		rectangleButton.setSize(100, 20);
		imageButton.setSize(80, 20);
		lineButton.setLocation(50, 20);
		ovalButton.setLocation(150, 20);
		rectangleButton.setLocation(250, 20);
		imageButton.setLocation(350, 20);
		lineButton.setActionCommand("LINE");
		ovalButton.setActionCommand("OVAL");
		rectangleButton.setActionCommand("RECTANGLE");
		imageButton.setActionCommand("IMAGE");
		lineButton.addActionListener(new RadioListenin());
		ovalButton.addActionListener(new RadioListenin());
		rectangleButton.addActionListener(new RadioListenin());
		imageButton.addActionListener(new RadioListenin());
		bGroup = new ButtonGroup();
		bGroup.add(lineButton);
		bGroup.add(rectangleButton);
		bGroup.add(ovalButton);
		bGroup.add(imageButton);
		controls.add(colorButton);
		controls.add(lineButton);
		controls.add(rectangleButton);
		controls.add(ovalButton);
		controls.add(imageButton);	
		this.add(controls, BorderLayout.SOUTH);
	}
	private class RadioListenin implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
				shapeSelected = e.getActionCommand();
			
		}
		
	}
	class CanvasPanel extends JPanel {
		public CanvasPanel() {
			this.setOpaque(true);
			this.setBackground(Color.WHITE);
			MouseListenin ml = new MouseListenin();
			this.addMouseListener(ml);
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
				else {
					g2.draw(shape.getShape());
					g2.fill(shape.getShape());
				}
			}
		}
	}

	private class ColorButtonL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			currColor = JColorChooser.showDialog(null, "Choose a Color", chooser.getForeground());

		}
	}

	private class MouseListenin implements MouseListener, MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (clicked) {
				PaintObject curr = new Line(xClicked, yClicked, e.getX(), e.getY(), currColor);
				if (shapeSelected.equals("RECTANGLE"))
					curr = new Rectangle(xClicked, yClicked, e.getX(), e.getY(), currColor);
				else if (shapeSelected.equals("OVAL"))
					curr = new Oval(xClicked, yClicked, e.getX(), e.getY(), currColor);
				else if (shapeSelected.equals("IMAGE"))
					curr = new Image(xClicked, yClicked, e.getX(), e.getY(), currColor);
				objects.remove(objects.size() - 1);
				objects.add(curr);
				repaint();
			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			clicked = !clicked;
			if (clicked) {
				xClicked = e.getX();
				yClicked = e.getY();
				PaintObject curr = new Line(xClicked, yClicked, e.getX(), e.getY(), currColor);
				if (shapeSelected.equals("RECTANGLE"))
					curr = new Rectangle(xClicked, yClicked, e.getX(), e.getY(), currColor);
				else if (shapeSelected.equals("OVAL"))
					curr = new Oval(xClicked, yClicked, e.getX(), e.getY(), currColor);
				else if (shapeSelected.equals("IMAGE"))
					curr = new Image(xClicked, yClicked, e.getX(), e.getY(), currColor);
				objects.add(curr);
				repaint();
			} else {
				objects.remove(objects.size() - 1);
				PaintObject curr = new Line(xClicked, yClicked, e.getX(), e.getY(), currColor);
				if (shapeSelected.equals("RECTANGLE"))
					curr = new Rectangle(xClicked, yClicked, e.getX(), e.getY(), currColor);
				else if (shapeSelected.equals("OVAL"))
					curr = new Oval(xClicked, yClicked, e.getX(), e.getY(), currColor);
				else if (shapeSelected.equals("IMAGE"))
					curr = new Image(xClicked, yClicked, e.getX(), e.getY(), currColor);
				objects.add(curr);
				repaint();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
