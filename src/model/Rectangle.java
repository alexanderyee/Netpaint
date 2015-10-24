package model;

import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

// Alex Yee
public class Rectangle extends PaintObject implements Serializable{

	public Rectangle(int x1, int x2, int y1, int y2, Color color) {
		super(x1, x2, y1, y2, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Shape getShape() {
		// TODO Auto-generated method stub
		return new Rectangle2D.Double(super.getXPoints()[0], super.getYPoints()[0], super.getXPoints()[1]-super.getXPoints()[0],
				super.getYPoints()[1]-super.getYPoints()[0]);
	}

	@Override
	public boolean isImage() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
