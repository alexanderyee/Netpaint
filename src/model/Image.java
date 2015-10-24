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
			return ImageIO.read(new File("./images/doge.jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
