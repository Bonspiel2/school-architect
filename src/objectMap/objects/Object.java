package objectMap.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import objectMap.Placeable;

public class Object implements Placeable{
	
	public static final boolean VERTICAL = true;
	public static final boolean HORIZONTAL = false;
	
	boolean traversable;
	private boolean orientation;
	
	public Object(boolean traversable, boolean orientation){
		this.traversable = traversable;
		setOrientation(VERTICAL);
	}


	public void draw(Graphics2D g, int x, int y, int size) {
	}


	public void switchOrientation() {
		if (getOrientation() == VERTICAL){
			setOrientation(HORIZONTAL);
		} else {
			setOrientation(VERTICAL);
		}
			
		
	}


	public boolean getOrientation() {
		return orientation;
	}


	public void setOrientation(boolean orientation) {
		this.orientation = orientation;
	}
	
	public Object clone(){
		return new Object(traversable, orientation);
	}
	
	public void interact(){
	}
}
