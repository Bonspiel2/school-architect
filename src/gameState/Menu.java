package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import main.Game;
import utilities.Button;

public class Menu implements GameState {
	
	private Button[] options = {new Button(334, 462, 100, 100, "Play")};
	private final int PLAY_BUTTON = 0;
	
	public Menu(){
		
	}
	
	public void init(){
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0,0,Game.WIDTH, Game.HEIGHT);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Tahoma", Font.BOLD, 28));
		g.drawString("School Architect", 256, 192);
		
		g.setFont(new Font("Tahoma", Font.BOLD, 18));
		for (Button b : options){
			b.draw(g);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		for (int i = 0; i < options.length; i++){
			if (options[i].containsPoint(x, y)){
				select(i);
				break;
			}
		}
	}

	private void select(int button) {
		if (button == PLAY_BUTTON){
			Game.setGameState(new School());
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}



}
