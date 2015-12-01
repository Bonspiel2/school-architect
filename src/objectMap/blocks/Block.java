package objectMap.blocks;

import java.awt.Color;
import java.awt.Graphics2D;

import objectMap.Placeable;

public class Block implements Placeable{
	
	
	boolean traversable;
	Color drawColor;
	
	public Block(boolean traversable, Color drawColor){
		this.traversable = traversable;
		this.drawColor = drawColor;
	}

	public void draw(Graphics2D g, int x, int y, int size) {
		g.setColor(drawColor);
		g.fillRect(x, y, size, size);
	}

	public boolean isTraversable() {
		return traversable;
	}

}
