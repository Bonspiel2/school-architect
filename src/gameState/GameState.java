package gameState;

import java.awt.event.MouseEvent;

public interface GameState {

	void update();
	void draw(java.awt.Graphics2D g);

	void keyPressed(int k);
	void keyReleased(int k);

	void mouseClicked(MouseEvent e);

	void mousePressed(MouseEvent e);
	void mouseReleased(MouseEvent e);

	void mouseEntered(MouseEvent e);
	void mouseExited(MouseEvent e);


}
