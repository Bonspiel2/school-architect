package objectMap.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import objectMap.Placeable;

public class Desk extends Object implements Placeable{
	
	BufferedImage sprite;

	public Desk(){
		super(true);
		
		try {
			sprite = ImageIO.read(new File("src/resources/objects.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void draw(Graphics2D g, int x, int y, int size){
		g.drawImage(sprite, x, y, size, size, null);
	}

}
