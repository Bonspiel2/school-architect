package resources;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class SpriteSheet {
	
	private static HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();
	
	public static void addSprites(BufferedImage sheet, int spriteSize, int rows, int cols, String[] ids){
		for (int x = 0; x < rows; x++){
			for (int y = 0; y < cols; y++){
				sprites.put(ids[(x *cols) + y], 
							sheet.getSubimage(x * spriteSize, y * spriteSize, spriteSize, spriteSize));
			}
		}
	}
	
	public static void clearSheet(){
		sprites.clear();
	}
	
	public static BufferedImage getSprite(String id){
		return sprites.get(id);
	}

}
