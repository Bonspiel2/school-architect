package objectMap;

import java.awt.Color;
import java.awt.Graphics2D;

public class Block {
	
	private boolean passive;
	private int id;
	private Color[] c = {Color.GREEN, Color.RED};
	
	public Block(int id, boolean passive){
		
		this.id = id;
		this.passive = passive;

	}

	public void draw(Graphics2D g, int x, int y, int size) {
		
		g.setColor(c[id]);
		g.fillRect(x, y, size, size);
		
	}

	public void setId(int id) {
		this.id = id;
		
	}

}
