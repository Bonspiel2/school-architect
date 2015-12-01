package entities;

import java.awt.Color;
import java.awt.Graphics2D;


import objectMap.BlockMap;
import objectMap.MapInteractor;

public class Student extends Entity {

	public Student(int x, int y) {
		super(x, y);
	}
	
	public void draw(Graphics2D g){
		BlockMap b = MapInteractor.blockMap;
		
		g.setColor(new Color(255, 228, 171));
		g.fillRect(getX() - (b.getSize()/2), getY(), (int) (b.getSize() * 0.5), (int) (b.getSize() * 0.5));
	}

}
