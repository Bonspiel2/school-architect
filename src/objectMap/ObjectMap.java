package objectMap;

import java.awt.Graphics2D;

public class ObjectMap {
	
	private Object[][] map;
	
	public ObjectMap(){
		map = new Object[100][100];
	}
	
	public void loadMap(){
		for (int x = 0; x < map.length; x++){
			for (int y = 0; y < map[0].length; y++){
				map[x][y] = new Object(0, 1, true);
			}
			
		}
	}
	
	public void draw(Graphics2D g){
		for (int x = 0; x < map.length; x++){
			for (int y = 0; y < map[0].length; y++){
				map[x][y] = new Object(0, 1, true);
			}
			
		}
	}
}
