package model;

import java.awt.Color;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.io.Serializable;

// Alex Yee
public class Line extends PaintObject implements Serializable{

	public Line(int x1, int x2, int y1, int y2, Color color) {
		super(x1, x2, y1, y2, color);
	}

	@Override
	public Shape getShape() {
		return new Line2D.Double(super.getXPoints()[0], super.getYPoints()[0], super.getXPoints()[1],
				super.getYPoints()[1]);
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
