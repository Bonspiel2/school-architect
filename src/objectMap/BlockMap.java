package objectMap;

import java.awt.Graphics2D;

import main.Game;

public class BlockMap {
	
	private Block[][] map;
	int size;
	int sizeScale;
	
	public BlockMap(){
		map = new Block[100][100];
		size = Game.HEIGHT/100;
	}
	
	public void loadMap(){
		for (int x = 0; x < map.length; x++){
			for (int y = 0; y < map[0].length; y++){
				map[x][y] = new Block(0, true);
			}
			
		}
	}
	
	public void draw(Graphics2D g){
		for (int x = 0; x < map.length; x++){
			for (int y = 0; y < map[0].length; y++){
				map[x][y].draw(g, x * size, y * size, size);
			}
			
		}
	}
	
	public void place(int x, int y , int id){
		map[x/size][y/size].setId(1);
	}

}
