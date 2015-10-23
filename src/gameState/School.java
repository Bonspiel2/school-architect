package gameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import main.Game;
import objectMap.BlockMap;

public class School implements GameState {
	
	BlockMap blockMap;
	boolean left, right, up, down;
	int cameraSpeed;
	
	public School(){
		blockMap = new BlockMap();
		boolean left, right, up, down = false;
		cameraSpeed = 5;
	}
	
	public void init(){
		blockMap.loadMap();
	}

	@Override
	public void update() {
		if (right){
			blockMap.setXOffSet(blockMap.getxOffSet()-cameraSpeed);
		}
		if (left){
			blockMap.setXOffSet(blockMap.getxOffSet()+cameraSpeed);
		}
		if (down){
			blockMap.setYOffSet(blockMap.getyOffSet()-cameraSpeed);
		}
		if (up){
			blockMap.setYOffSet(blockMap.getyOffSet()+cameraSpeed);
		}

	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		blockMap.draw(g);
		

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		blockMap.place(e.getX(), e.getY(), 1);

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
			blockMap.setZoom(blockMap.getZoom() * 1.1f);
			
			blockMap.setXOffSet((int) (e.getX() * (1.1f - 1f) + 1.1f * blockMap.getxOffSet()));
			blockMap.setYOffSet((int) (e.getY() * (1.1f - 1f) + 1.1f * blockMap.getyOffSet()));
			System.out.println(e.getX());
			System.out.println(e.getY());
		} else{
			int oldSideLength = blockMap.getSize() * 100;
			blockMap.setSize(blockMap.getSize() - 1);
			int sideLength = blockMap.getSize() * 100;
			//blockMap.setXOffSet(blockMap.getxOffSet() - blockMap.getBlockX(e.getX()));
			//blockMap.setYOffSet(blockMap.getyOffSet() - blockMap.getBlockY(e.getY()) + 1);
		}
		
		
		
	}

}
