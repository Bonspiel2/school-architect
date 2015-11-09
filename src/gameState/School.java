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
import objectMap.blocks.*;
import utilities.Button;

public class School implements GameState {

	private static final int GRASS_ID = 0;
	private static final int BRICK_ID = 1;
	private BlockMap blockMap;
	private boolean left, right, up, down;
	private int cameraSpeed;

	private boolean placeable;
	private boolean currentlyPlacing;
	private Point originPlacingPoint;
	private Block blockToPlace;

	/////Bottom Menu/////
	private Button[] bottomMenuButtons = {new Button(10, (Game.HEIGHT - 40), 30, 30, "Blocks")};
	private Button[] optionButtons;
	private boolean clicked;
	private Point popUpMenuPosition;
	private BottomMenuOption option;
	private final int popUpMenuWidth = 170;
	private final int popUpMenuHeight = 50;
	
	private Point mousePosition;

	public School(){
		blockMap = new BlockMap();
		blockMap.loadMap();
		boolean left, right, up, down = false;
		cameraSpeed = 5;
		placeable = false;
		currentlyPlacing = false;
		originPlacingPoint = new Point(0,0);
		blockToPlace = new Block();

		/////Bottom Menu/////
		option = BottomMenuOption.NOT_SELECTED;
		optionButtons = new Button[0];
		popUpMenuPosition = new Point(10, (Game.HEIGHT - 100));
		clicked = false;
		
		mousePosition = new Point(600, 500); 

	}

	public void init(){
	}

	@Override
	public void update() {
		Point pos = blockMap.getViewPosition();
		if (right){
			blockMap.setViewPosition(new Point(pos.x-cameraSpeed, pos.y));
			pos.x-=cameraSpeed;
			blockMap.fixBounds();
		}
		if (left){
			blockMap.setViewPosition(new Point(pos.x+cameraSpeed, pos.y));
			pos.x+=cameraSpeed;
			blockMap.fixBounds();
		}
		if (down){
			blockMap.setViewPosition(new Point(pos.x, pos.y-cameraSpeed));
			pos.y-=cameraSpeed;
			blockMap.fixBounds();
		}
		if (up){
			blockMap.setViewPosition(new Point(pos.x, pos.y+cameraSpeed));
			pos.y+=cameraSpeed;
			blockMap.fixBounds();
		}


	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		if (currentlyPlacing){
			blockMap.draw(g, true, mousePosition, originPlacingPoint, blockToPlace);
		}else if (placeable){
			blockMap.draw(g, true);
		} else{
			blockMap.draw(g, false);
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
			System.out.println("yolo");
			
			blockToPlace.draw(g, mousePosition.x - (size/2), mousePosition.y - (size/2), size);
		}
		


	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		for (int i = 0; i < optionButtons.length; i++){
			if (optionButtons[i].containsPoint(e.getX(), e.getY())){
				selectOption(i);
			}
		}

		boolean switched = false;
		for (Button b : bottomMenuButtons){
			if (b.containsPoint(e.getX(), e.getY())){
				switchMenu(BottomMenuOption.BLOCK);
				switched = true;
				break;
			}
		}

		switch (option){

		case NOT_SELECTED:
			break;

		default:
			if (!switched){
				switchMenu(BottomMenuOption.NOT_SELECTED);
			}
			break;
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
				} else {
					placeable = false;
					blockToPlace = new Block();
				}
					
		}
		
	}

	private void switchMenu(BottomMenuOption b){
		option = b;

		switch(option){

		case BLOCK:
			optionButtons = new Button[2];
			optionButtons[0] = new Button(20, (Game.HEIGHT - 90), 30, 30, "Grass");
			optionButtons[1] = new Button(60, (Game.HEIGHT - 90), 30, 30, "Brick");
			popUpMenuPosition = new Point(10, (Game.HEIGHT - 100));
			clicked = true;
			break;
		case NOT_SELECTED:
			optionButtons = new Button[0];
			popUpMenuPosition = new Point(10, (Game.HEIGHT - 100));
			clicked = false;
			break;
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println(placeable);
		if (placeable){
			currentlyPlacing = true;
			originPlacingPoint = e.getPoint();
		}
		

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (placeable){
			if (!bottomMenuContains(e.getX(), e.getY()) && blockMap.contains(new Point(e.getX(), e.getY()))){
				blockMap.place(e.getX(), e.getY(), blockToPlace);
				currentlyPlacing = false;
				placeable = false;
				blockToPlace = new Block();
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

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
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

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
		}

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches =  e.getWheelRotation();

		if (notches < 0){
			blockMap.setSize(blockMap.getSize() + 1);

			if (!blockMap.fixBounds()){


				int newX = Math.round((e.getX() * 100/Game.WIDTH));
				int newY = Math.round((e.getY() * 100/Game.HEIGHT));

				Point oldPos = blockMap.getViewPosition();


				blockMap.setViewPosition(new Point(oldPos.x - newX, oldPos.y - newY));
			}
		} else{
			blockMap.setSize(blockMap.getSize() - 1);

			if (!blockMap.fixBounds()){

				int newX = Math.round((e.getX() * 100 /Game.WIDTH));
				int newY = Math.round((e.getY() * 100/Game.HEIGHT));

				Point oldPos = blockMap.getViewPosition();

				blockMap.setViewPosition(new Point(oldPos.x + newX, oldPos.y + newY));
			}
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

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePosition = e.getPoint();
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mousePosition = e.getPoint();
		
	}

}
