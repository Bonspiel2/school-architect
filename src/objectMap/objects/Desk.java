package objectMap.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import objectMap.BlockMap;
import objectMap.Placeable;

public class Desk extends Object implements Placeable{
	
	static final BufferedImage SPRITE_VERTICAL = BlockMap.OBJECT_SPRITESHEET.getSubimage(0, 0, BlockMap.SPRITE_SIZE, BlockMap.SPRITE_SIZE);
	static final BufferedImage SPRITE_HORIZONTAL = BlockMap.OBJECT_SPRITESHEET.getSubimage(0, BlockMap.SPRITE_SIZE * 1, BlockMap.SPRITE_SIZE, BlockMap.SPRITE_SIZE);
	
	
	private final boolean VERTICAL = true;
	private final boolean HORIZONTAL = false;
	private boolean orientation;
	

	public Desk(){
		super(true);
		orientation = VERTICAL;
	}
	
	public void draw(Graphics2D g, int x, int y, int size){
		if (orientation == VERTICAL){
			g.drawImage(SPRITE_VERTICAL, x, y, size, size, null);
		} else {
			g.drawImage(SPRITE_HORIZONTAL, x, y, size, size, null);
		}
	}

}
