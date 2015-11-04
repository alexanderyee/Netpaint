package model;

import java.awt.Color;
import java.awt.Shape;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

// Alex Yee
public class Image extends PaintObject implements Serializable {

	public Image(int x1, int x2, int y1, int y2, Color color) {
		super(x1, x2, y1, y2, color);

	}

	@Override
	public Shape getShape() {
		return null;
	}

	@Override
	public boolean isImage() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public java.awt.Image getImage() {
		// TODO Auto-generated method stub
		try {
			int newWidth = super.getXPoints()[1] - super.getXPoints()[0];
			int newHeight = super.getYPoints()[1] - super.getYPoints()[0];
			
				java.awt.Image inputImage = ImageIO.read(new File("./images/doge.jpeg")).getScaledInstance(newWidth,
						newHeight, java.awt.Image.SCALE_DEFAULT);
				return inputImage;
			

		} catch (IOException e) {

		}
		return null;
	}

}
