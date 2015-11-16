package objectMap;

import java.awt.Color;
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
		
		zoom = 1;
		size = Game.HEIGHT/100;
		
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

	public void draw(Graphics2D g, boolean showGrid){

		int blockSize = size;

		for (int x = 0; x < map.length; x++){
			for (int y = 0; y < map[0].length; y++){
				map[x][y].draw(g, (x * blockSize) + viewPosition.x, (y * blockSize) + viewPosition.y, blockSize);
				if (showGrid){
					g.setColor(Color.BLACK);
					g.fillRect((x * size) + viewPosition.x, (y * size) + viewPosition.y, 1, size);
					g.fillRect((x * size) + viewPosition.x, (y * size) + viewPosition.y, size, 1);
				}
			}

		}

		//		for (int x = 0; x < objects.length; x++){
		//			for (int y = 0; y < objects[0].length; y++){
		//				objects[x][y].draw(g, (x * blockSize) - viewPosition.x, (y * blockSize) - viewPosition.y, blockSize);
		//			}
		//			
		//		}
	}

	public void draw(Graphics2D g, boolean showGrid, Point mousePosition, Point originPosition, Block blockToPlace){

		int blockSize = size;

		for (int x = 0; x < map.length; x++){
			for (int y = 0; y < map[0].length; y++){
				map[x][y].draw(g, (x * blockSize) + viewPosition.x, (y * blockSize) + viewPosition.y, blockSize);
				if (showGrid){
					g.setColor(Color.BLACK);
					g.fillRect((x * size) + viewPosition.x, (y * size) + viewPosition.y, 1, size);
					g.fillRect((x * size) + viewPosition.x, (y * size) + viewPosition.y, size, 1);
				}
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
		
		boolean boundsChanged = false;
		if (viewPosition.x > Game.WIDTH/2){

			viewPosition.x = Game.WIDTH/2;
			boundsChanged = true;
		}
		if (viewPosition.x + (size * 100) < Game.WIDTH/2){
			viewPosition.x = (Game.WIDTH/2) - (size * 100);
			boundsChanged = true;
		}
		if (viewPosition.y > Game.HEIGHT/2){
			viewPosition.y = Game.HEIGHT/2;
			boundsChanged = true;
		}
		if (viewPosition.y + (size * 100) < Game.HEIGHT/2){
			viewPosition.y = (Game.HEIGHT/2) - (size * 100);
			boundsChanged = true;
		}
		if (size > 100){
			size = 100;
			boundsChanged = true;
		}
		if (size < 4){
			size = 4;
			boundsChanged = true;
		}
		return boundsChanged;
	}

	public void place(int x, int y, Block blockToPlace){
		int blockSize = (int) (size * zoom);
		map[(int) ((x - viewPosition.x)/blockSize)][(int) ((y - viewPosition.y)/blockSize)] = blockToPlace;
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

	public boolean contains(int x, int y){
		if (x >= viewPosition.x && x <= viewPosition.x + (size * 100) && y >= viewPosition.y && y <= viewPosition.y + (size * 100) ){
			return true;
		} else{
			return false;
		}
	}







}
