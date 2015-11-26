package objectMap.objects;

import java.awt.Graphics2D;

import objectMap.Placeable;
import resources.SpriteSheet;

public class Door extends Object implements Placeable{

	public Door() {
		super(false, Object.VERTICAL);
	}
	
	public void draw(Graphics2D g, int x, int y, int size){
		if (getOrientation() == VERTICAL){
			g.drawImage(SpriteSheet.getSprite("doorVertical"), x, y, size, size, null);
		} else {
			g.drawImage(SpriteSheet.getSprite("doorHorizontal"), x, y, size, size, null);
		}
	}
	
	public Door clone(){
		Door returnDoor = new Door();
		
		returnDoor.setOrientation(getOrientation());
		
		return returnDoor;
	}

}
