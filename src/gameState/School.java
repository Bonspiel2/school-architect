package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import main.Game;
import objectMap.BlockMap;
import objectMap.blocks.BlockType;
import utilities.Button;

public class School implements GameState {
	
	private BlockMap blockMap;
	private boolean left, right, up, down;
	private int cameraSpeed;
	
	private boolean placeable;
	private BlockType blockToPlace;
	
	private BottomMenu bottomMenu = new BottomMenu(BottomMenuOption.NOT_SELECTED);
	
	public School(){
		blockMap = new BlockMap();
		blockMap.loadMap();
		boolean left, right, up, down = false;
		cameraSpeed = 5;
		placeable = false;
		blockToPlace = BlockType.NONE;
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
		
		blockMap.draw(g);
		bottomMenu.draw(g);
		

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		bottomMenu.mouseClicked(e);

	}
	
	private void select(int button){
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (placeable){
			if (!bottomMenu.contains(e.getX(), e.getY()) && blockMap.contains(new Point(e.getX(), e.getY()))){
				blockMap.place(e.getX(), e.getY());
				placeable = false;
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

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

}
