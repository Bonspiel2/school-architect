package gameState;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface GameState {
	
	
	void init();
	void update();
	void draw(java.awt.Graphics2D g);

	void keyPressed(KeyEvent e);
	void keyTyped(KeyEvent e);
	void keyReleased(KeyEvent e);

	void mouseClicked(MouseEvent e);

	void mousePressed(MouseEvent e);
	void mouseReleased(MouseEvent e);

	void mouseEntered(MouseEvent e);
	void mouseExited(MouseEvent e);


}
