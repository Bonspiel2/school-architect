package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import main.Game;
import objectMap.BlockMap;
import objectMap.Direction;
import objectMap.MapInteractor;
import objectMap.blocks.*;
import utilities.Button;

public class School implements GameState {
	
	private MapInteractor map;

	private final int GRASS_ID = 0;
	private final int BRICK_ID = 1;
	private final int TILE_ID = 2;
	private boolean left, right, up, down;
	private final int cameraSpeed = 5;

	private boolean placeable;
	private boolean currentlyPlacing;
	private Point originPlacingPoint;
	private Block blockToPlace;
	private boolean shiftHeld;
	private boolean placingBuilding;

	/////Bottom Menu/////
	private Button[] bottomMenuButtons = {	new Button(10, (Game.HEIGHT - 40), 50, 30, "Building"),
			new Button(70, (Game.HEIGHT - 40), 50, 30, "Blocks")};
	private Button[] optionButtons;
	private boolean clicked;
	private Point popUpMenuPosition;
	private BottomMenuOption option;
	private final int popUpMenuWidth = 170;
	private final int popUpMenuHeight = 50;

	private Point mousePosition;

	public School(){
		
		map = new MapInteractor();

		placeable = false;
		currentlyPlacing = false;
		originPlacingPoint = new Point(0,0);
		blockToPlace = new Grass();
		shiftHeld = false;

		/////Bottom Menu/////
		optionButtons = new Button[0];
		clicked = false;
		popUpMenuPosition = new Point(10, (Game.HEIGHT - 100));
		option = BottomMenuOption.NOT_SELECTED;

		mousePosition = new Point(600, 500); 

	}

	public void init(){
	}

	@Override
	public void update() {
		if (right){
			map.move(Direction.RIGHT);
		}
		if (left){
			map.move(Direction.LEFT);
		}
		if (down){
			map.move(Direction.DOWN);
		}
		if (up){
			map.move(Direction.UP);
		}


	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		if (placingBuilding){
			blockMap.d
		} else if (currentlyPlacing){
			blockMap.draw(g, true, mousePosition, originPlacingPoint, blockToPlace);
		} else{
			blockMap.draw(g, placeable);
		}

		/////Bottom Menu//////
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRect(0, Game.HEIGHT - 50, Game.WIDTH, Game.HEIGHT);

		for (Button b : bottomMenuButtons){

			g.setColor(Color.BLUE);
			g.setFont(new Font("Tahoma", Font.BOLD, 10));

			b.draw(g);
		}
		if (clicked){
			g.setColor(new Color(0, 0, 0, 100));
			g.fillRect(popUpMenuPosition.x, popUpMenuPosition.y, popUpMenuWidth, popUpMenuHeight);
			g.setColor(Color.ORANGE);
			for (Button b : optionButtons){
				b.draw(g);
			}
		}

		if (placeable){
			int size = blockMap.getSize();

			blockToPlace.draw(g, mousePosition.x - (size/2), mousePosition.y - (size/2), size);
		}



	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();

		for (int i = 0; i < optionButtons.length; i++){
			if (optionButtons[i].containsPoint(x, y)){
				selectOption(i);
			}
		}

		boolean switched = false;

		for (int i = 0; i < bottomMenuButtons.length; i++){
			if (bottomMenuButtons[i].containsPoint(x, y)){
				switchMenu(i);
				switched = true;
				break;
			}
		}

		switch (option){

		case NOT_SELECTED:
			break;

		default:
			if (!switched){
				switchMenu(-1);
			}
			break;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (placeable){
			currentlyPlacing = true;
			originPlacingPoint = e.getPoint();
		}


	}

	@Override
	public void mouseReleased(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();

		if (placeable){
			if (!bottomMenuContains(x, y) && blockMap.contains(x, y)){
				int size = blockMap.getSize();
				if (blockToPlace.getPlacingType() == Block.LINE){
					if (Math.abs(mousePosition.x - originPlacingPoint.x) >= Math.abs(mousePosition.y - originPlacingPoint.y)){
						int mouseX = x;
						int originX = originPlacingPoint.x;


						if (mouseX < originX){
							int temp = mouseX;
							mouseX = originX;
							originX = temp;
							mouseX+=size/2;

						}
						for (int placingX = originX; placingX < mouseX; placingX+=size){
							blockMap.place(placingX, originPlacingPoint.y, blockToPlace);
						}

					} else {
						int mouseY = y;
						int originY = originPlacingPoint.y;

						if (mouseY < originY){
							int temp = mouseY;
							mouseY = originY;
							originY = temp;
							mouseY+=size/2;

						}
						for (int placingY = originY; placingY < mouseY; placingY+=size){
							blockMap.place(originPlacingPoint.x, placingY, blockToPlace);
						}
					}
				} else if (blockToPlace.getPlacingType() == Block.SQUARE) {

					int mouseX = x;
					int originX = originPlacingPoint.x;
					int mouseY = y;
					int originY = originPlacingPoint.y;

					if (mouseX < originX){
						int temp = mouseX;
						mouseX = originX;
						originX = temp;
						mouseX+=size/2;

					}

					if (mouseY < originY){
						int temp = mouseY;
						mouseY = originY;
						originY = temp;
						mouseY+=size/2;

					}
					for (int placingX = originX; placingX < mouseX; placingX+=size){
						for (int placingY = originY; placingY < mouseY; placingY+=size){
							blockMap.place(placingX, placingY, blockToPlace);
						}
					}

				}

				currentlyPlacing = false;
				if (!shiftHeld){
					placeable = false;
				}
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition = e.getPoint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mousePosition = e.getPoint();
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches =  e.getWheelRotation();
		int size = blockMap.getSize();

		if (notches < 0){
			if (size < 100){
				blockMap.setSize(blockMap.getSize() + 1);

				int newX = Math.round((e.getX() * 100/Game.WIDTH));
				int newY = Math.round((e.getY() * 100/Game.HEIGHT));

				Point oldPos = blockMap.getViewPosition();


				blockMap.setViewPosition(new Point(oldPos.x - newX, oldPos.y - newY));
			}
		}else{
			if (size > 4){
				blockMap.setSize(blockMap.getSize() - 1);


				int newX = Math.round((e.getX() * 100 /Game.WIDTH));
				int newY = Math.round((e.getY() * 100/Game.HEIGHT));

				Point oldPos = blockMap.getViewPosition();

				blockMap.setViewPosition(new Point(oldPos.x + newX, oldPos.y + newY));
			}
		}



	}

	@Override
	public void keyTyped(KeyEvent e) {
	}	

	@Override
	public void keyPressed(KeyEvent e) {

		int button = e.getKeyCode();

		if (button == KeyEvent.VK_A){
			left = true;
		} else if (button == KeyEvent.VK_D){
			right = true;
		} else if (button == KeyEvent.VK_W){
			up = true;
		} else if (button == KeyEvent.VK_S){
			down = true;
		} else if (button == KeyEvent.VK_SHIFT){
			shiftHeld = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int button = e.getKeyCode();

		if (button == KeyEvent.VK_A){
			left = false;
		} else if (button == KeyEvent.VK_D){
			right = false;
		} else if (button == KeyEvent.VK_W){
			up = false;
		} else if (button == KeyEvent.VK_S){
			down = false;
		} else if (button == KeyEvent.VK_SHIFT){
			shiftHeld = false;
		}

	}
	
	private void selectOption(int buttonNumber) {

		switch(option) {
		case BLOCK:
			if (buttonNumber == GRASS_ID){
				placeable = true;
				blockToPlace = new Grass();
			} else if (buttonNumber == BRICK_ID){
				placeable = true;
				blockToPlace = new Brick();
			} else if (buttonNumber == TILE_ID){
				placeable = true;
				blockToPlace = new Tile();
			} else {
				placeable = false;
			}

		}

	}

	private void switchMenu(int optionNumber){
		
		if (optionNumber == 0){
			option = BottomMenuOption.BUILDING;
		} else if (optionNumber == 1){
			option = BottomMenuOption.BLOCK;
		} else {
			option = BottomMenuOption.NOT_SELECTED;
		}

		switch(option){

		case BLOCK:
			optionButtons = new Button[3];
			optionButtons[0] = new Button(20, (Game.HEIGHT - 90), 30, 30, "Grass");
			optionButtons[1] = new Button(60, (Game.HEIGHT - 90), 30, 30, "Brick");
			optionButtons[2] = new Button(100, (Game.HEIGHT - 90), 30, 30, "Tile");
			popUpMenuPosition = new Point(10, (Game.HEIGHT - 100));
			clicked = true;
			break;
		case BUILDING:
			placeable = true;
			placingBuilding = true;
			blockToPlace = new Brick();
			break;
		case NOT_SELECTED:
			optionButtons = new Button[0];
			popUpMenuPosition = new Point(10, (Game.HEIGHT - 100));
			clicked = false;
			break;
		}

	}
	
	private boolean bottomMenuContains(int x, int y) {

		if(y > Game.HEIGHT - 50){
			return true;
		}

		if (clicked){
			if (x >= popUpMenuPosition.x && x <= popUpMenuPosition.x + popUpMenuWidth && y >= popUpMenuPosition.y && y <= popUpMenuPosition.y + popUpMenuHeight){
				return true;
			}
		}

		return false;
	}

}
