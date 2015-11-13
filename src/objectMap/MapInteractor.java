package objectMap;

import java.awt.Point;

public class MapInteractor {
	
	private BlockMap blockMap;
	
	private final int cameraSpeed = 5;
	
	public MapInteractor(){
		blockMap = new BlockMap();
		blockMap.loadMap();
	}
	
	public void move(Direction d){
		
		Point pos = blockMap.getViewPosition();
		
		switch(d){
			case RIGHT:
				blockMap.setViewPosition(new Point(pos.x-cameraSpeed, pos.y));
				break;
			case LEFT:
				blockMap.setViewPosition(new Point(pos.x+cameraSpeed, pos.y));
				break;
			case UP:
				blockMap.setViewPosition(new Point(pos.x, pos.y-cameraSpeed));
				break;
			case DOWN:
				blockMap.setViewPosition(new Point(pos.x-cameraSpeed, pos.y+cameraSpeed));
				break;
		}
	}
	
	

}
