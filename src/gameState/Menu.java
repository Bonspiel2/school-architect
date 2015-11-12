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
	
	public Menu() {
	}
	
	public void init() {
	}
	
	public void update() {
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
	
	private void select(int button) {
		if (button == PLAY_BUTTON){
			Game.setGameState(new School());
		}
	}
	
	public void keyPressed(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}
	
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

	public void mousePressed(MouseEvent e) {		
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
	
	
	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}
	

	public void mouseWheelMoved(MouseWheelEvent e) {
	}

}
