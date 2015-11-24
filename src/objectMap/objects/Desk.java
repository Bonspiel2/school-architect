package objectMap.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import objectMap.BlockMap;
import objectMap.Placeable;
import resources.SpriteSheet;

public class Desk extends Object implements Placeable{
	

	public Desk(){
		super(true);
	}
	
	public void draw(Graphics2D g, int x, int y, int size){
		if (super.getOrientation() == VERTICAL){
			g.drawImage(SpriteSheet.getSprite("deskVertical"), x, y, size, size, null);
		} else {
			g.drawImage(SpriteSheet.getSprite("deskHorizontal"), x, y, size, size, null);
		}
	}

}
