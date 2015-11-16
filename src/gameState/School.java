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
import objectMap.MapInteractor;
import objectMap.PlacingType;
import objectMap.blocks.*;
import utilities.*;

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

//		if (placingBuilding){
//			blockMap.d
//		} else if (currentlyPlacing){
//			blockMap.draw(g, true, mousePosition, originPlacingPoint, blockToPlace);
//		} else{
//			blockMap.draw(g, placeable);
//		}
		
		map.draw(g, mousePosition);

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
		int x = e.getX();
		int y = e.getY();
		
		if (!bottomMenuContains(x, y)){
			map.startPlacing(x, y);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();

		if (!bottomMenuContains(x, y)){
			map.endPlacing(x, y);
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
		map.zoom(e.getX(), e.getY(), e.getWheelRotation());
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
				map.setPlaceable(true);
				map.setPlacingType(PlacingType.LINE);
				map.setBlockToPlace(new Grass());
			} else if (buttonNumber == BRICK_ID){
				map.setPlaceable(true);
				map.setPlacingType(PlacingType.LINE);
				map.setBlockToPlace(new Brick());
			} else if (buttonNumber == TILE_ID){
				map.setPlaceable(true);
				map.setPlacingType(PlacingType.SQUARE);
				map.setBlockToPlace(new Tile());
			} else {
				map.setPlaceable(false);
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
			map.setPlaceable(true);
			map.setPlacingType(PlacingType.BUILDING);
			map.setBlockToPlace(new Brick());
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
