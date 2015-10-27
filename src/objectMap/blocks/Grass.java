package objectMap.blocks;

import java.awt.Color;
import java.awt.Graphics2D;

public class Grass extends Block{
	
	boolean traversable;
	
	Color drawColor;

	public Grass(){
		super();
		traversable = true;
		drawColor = Color.GREEN;
	}
	
	public void draw(Graphics2D g, int x, int y, int size){
		g.setColor(drawColor);
		g.fillRect(x, y, size, size);
	}
		
		

}
