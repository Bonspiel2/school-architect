package objectMap.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Object {
	
	boolean traversable;
	BufferedImage sprite;
	
	public Object(boolean traversable, BufferedImage sprite){
		this.traversable = traversable;
		this.sprite = sprite;
	}

	public void draw(int x, int y, int size) {
		BufferedImage resizedImage = new BufferedImage(size, size, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(sprite, x, y, null);
	}
}
