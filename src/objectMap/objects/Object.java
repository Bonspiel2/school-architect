package objectMap.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import objectMap.Placeable;

public class Object implements Placeable{
	
	boolean traversable;
	
	public Object(boolean traversable){
		this.traversable = traversable;
	}


	public void draw(Graphics2D g, int x, int y, int size) {
	}
}
