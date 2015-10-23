package view;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import model.PaintObject;
// Alex Yee
public class NetpaintGUI extends JFrame{
	private List<PaintObject> objects;
	public NetpaintGUI() {
		objects = new ArrayList<PaintObject>();
	}
    private void drawShapesOnMe(Graphics g, List<PaintObject> shapes) {
    	
    }
}
