package objectMap.blocks;

import java.awt.Color;
import java.awt.Graphics2D;

public class Brick extends Block{
	
	boolean traversable;
	
	Color drawColor;

	public Brick(){
		super();
		traversable = true;
		drawColor = Color.RED;
	}
	
	public void draw(Graphics2D g, int x, int y, int size){
		g.setColor(drawColor);
		g.fillRect(x, y, size, size);
	}
		
		

}
