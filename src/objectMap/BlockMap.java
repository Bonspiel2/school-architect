package objectMap;

import java.awt.Graphics2D;
import java.awt.Point;

import main.Game;
import objectMap.blocks.*;

public class BlockMap {
	
	private Block[][] map;
	private Object[][] objects;
	double zoom;
	int size;
	
	Point viewPosition;
	

	public BlockMap(){
		map = new Block[100][100];
		objects = new Object[100][100];
		size = Game.HEIGHT/100;
		zoom = 1;
		viewPosition = new Point(0,0);
	}

	public void loadMap(){
		
		for (int x = 0; x < map.length; x++){
			for (int y = 0; y < map[0].length; y++){
				map[x][y] = new Grass();
			}
			
		}
		
		for (int x = 0; x < objects.length; x++){
			for (int y = 0; y < objects[0].length; y++){
				objects[x][y] = new Object(0, 0, true);
			}
		}
		
	}
	
	public void draw(Graphics2D g){
		
		int blockSize = size;
		
		for (int x = 0; x < map.length; x++){
			for (int y = 0; y < map[0].length; y++){
				map[x][y].draw(g, (x * blockSize) + viewPosition.x, (y * blockSize) + viewPosition.y, blockSize);
			}
			
		}
		
//		for (int x = 0; x < objects.length; x++){
//			for (int y = 0; y < objects[0].length; y++){
//				objects[x][y].draw(g, (x * blockSize) - viewPosition.x, (y * blockSize) - viewPosition.y, blockSize);
//			}
//			
//		}
	}
	
	public boolean fixBounds(){
		if (viewPosition.x > Game.WIDTH/2){
			
			viewPosition.x = Game.WIDTH/2;
			return true;
		}
		if (viewPosition.x + (size * 100) < Game.WIDTH/2){
			viewPosition.x = (Game.WIDTH/2) - (size * 100);
			return true;
		}
		if (viewPosition.y > Game.HEIGHT/2){
			viewPosition.y = Game.HEIGHT/2;
			return true;
		}
		if (viewPosition.y + (size * 100) < Game.HEIGHT/2){
			viewPosition.y = (Game.HEIGHT/2) - (size * 100);
			return true;
		}
		if (size > 100){
			size = 100;
			return true;
		}
		if (size < 4){
			size = 4;
			return true;
		}
		return false;
	}
	
	public void place(int x, int y){
		int blockSize = (int) (size * zoom);
		map[(int) ((x - viewPosition.x)/blockSize)][(int) ((y - viewPosition.y)/blockSize)] = new Brick();
	}
	
	///////GETTERS AND SETTERS////////
	


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
	
	public Point getViewPosition() {
		return viewPosition;
	}

	public void setViewPosition(Point viewPosition) {
		this.viewPosition = viewPosition;
	}
	
	public boolean contains(Point p){
		if (p.x >= viewPosition.x && p.x <= viewPosition.x + (size * 100) && p.y >= viewPosition.y && p.y <= viewPosition.y + (size * 100) ){
			return true;
		} else{
			return false;
		}
	}


	
	
	
	

}
