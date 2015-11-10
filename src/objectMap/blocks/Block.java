package objectMap.blocks;

import java.awt.Color;
import java.awt.Graphics2D;

public class Block {
	
	public final static int LINE = 1;
	public final static int SQUARE = 2;
	
	
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
	
	public int getPlacingType(){
		return Block.LINE;
		
	}

}
