package objectMap.objects;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Desk extends Object{

	public Desk() throws IOException {
		super(true, ImageIO.read(new File("src/resources/objects.png")));
	}

}
