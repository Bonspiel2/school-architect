package objectMap;

import java.awt.Graphics2D;

import main.Game;

public class BlockMap {
	
	private Block[][] map;
	int size;
	int sizeScale;
	int xOffSet;
	int yOffSet;
	double zoom;
	
	public BlockMap(){
		map = new Block[100][100];
		size = Game.HEIGHT/100;
		xOffSet = (int) ((Game.WIDTH - (size * 100))/2);
		yOffSet = 0;
		zoom = 1;
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
				map[x][y].draw(g, (int)((x * size) * zoom) + xOffSet, (int)((y * size) * zoom) + yOffSet, (int) (size * zoom));
			}
			
		}
	}
	
	///////GETTERS AND SETTERS////////
	
	public void place(int x, int y , int id){
		map[(int) ((x - xOffSet)/size)][(int) ((y - yOffSet)/size)].setId(1);
	}

	public void setXOffSet(int xOffSet) {
		this.xOffSet = xOffSet;
	}

	public void setYOffSet(int yOffSet) {
		this.yOffSet = yOffSet;
	}

	public int getxOffSet() {
		return xOffSet;
	}

	public int getyOffSet() {
		return yOffSet;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
		this.zoom = zoom;
	}
	
	
	
	

}
