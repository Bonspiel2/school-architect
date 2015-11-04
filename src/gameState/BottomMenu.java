package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import main.Game;
import utilities.Button;

public class BottomMenu {
	
	private Button[] bottomMenuButtons = {new Button(10, (Game.HEIGHT - 40), 30, 30, "Blocks")};
	private Button[] optionButtons;
	private boolean clicked;
	private Point popUpMenuPosition;
	private BottomMenuOption option;
	private final int popUpMenuWidth = 170;
	private final int popUpMenuHeight = 50;

	public BottomMenu(BottomMenuOption b){
		option = b;
		switch(b){
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
	

	public void draw(Graphics2D g) {
		
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

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public BottomMenuOption mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for (Button b : bottomMenuButtons){
			System.out.println("Clicked!");
			if (b.containsPoint(e.getX(), e.getY())){
				System.out.println("OOOOOO");
				return BottomMenuOption.BLOCK;
				
			}
		}
		return BottomMenuOption.NOT_SELECTED;

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean contains(int x, int y) {
		
		System.out.println(clicked);
		
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
