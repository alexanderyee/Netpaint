package model;

import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.io.Serializable;



// Alex Yee
public abstract class PaintObject implements Serializable{
	private int[] xPoints;
	private int[] yPoints;
	private Color color;

	public PaintObject(int x1, int y1, int x2, int y2, Color color) {
		this.color = color;
		xPoints = new int[] { x1, x2 };
		yPoints = new int[] { y1, y2 };
	}

	public Color getColor() {
		return this.color;
	}
	public int[] getXPoints(){
		return xPoints;
	}
	public int[] getYPoints(){
		return yPoints;
	}
	public abstract Shape getShape();
	public abstract boolean isImage();
	public abstract Image getImage();
	
}
