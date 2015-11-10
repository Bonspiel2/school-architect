package objectMap.blocks;

import java.awt.Color;

public class Tile extends Block {

	public Tile() {
		super(true, Color.GRAY);
	}
	
	public int getPlacingType(){
		return Block.SQUARE;
	}

}
