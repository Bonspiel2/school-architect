package objectMap.objects;

import java.awt.Graphics2D;

import objectMap.Placeable;
import resources.SpriteSheet;

public class Door extends Object implements Placeable{
	
	private boolean open;

	public Door() {
		super(false, Object.VERTICAL);
		open = false;
	}
	
	public void draw(Graphics2D g, int x, int y, int size){
		if (getOrientation() == VERTICAL){
			if (open){
				g.drawImage(SpriteSheet.getSprite("doorVertical"), (int) (x + (size * 0.8)), y, size, size, null);
			} else {
				g.drawImage(SpriteSheet.getSprite("doorVertical"), x, y, size, size, null);
			}
		} else {
			if (open) {
				g.drawImage(SpriteSheet.getSprite("doorHorizontal"), x, (int) (y - (size * 0.8)), size, size, null);
			} else {
				g.drawImage(SpriteSheet.getSprite("doorHorizontal"), x, y, size, size, null);
			}
		}
	}
	
	public Door clone(){
		Door returnDoor = new Door();
		
		returnDoor.setOrientation(getOrientation());
		
		return returnDoor;
	}
	
	public void interact(){
		open = !open;
		traversable = !traversable;
	}
	
	public boolean isEmpty(){
		return false;
	}
	

}
