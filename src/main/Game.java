
package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import gameState.*;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseWheelListener{
	//dimensions
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static final int SCALE  = 2;

	//game thread
	private Thread thread;
	private boolean running; 
	private int FPS;
	private long targetTime; 

	//image
	private BufferedImage image;
	private Graphics2D g;
	
	//game states
	private static GameState currentState;
	
	
	public Game(){	// Game constructor
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		FPS = 30;
		targetTime = 1000/FPS;
		currentState = new Menu();
	}

	private void init(){	// initalizes game states
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
	}
	public void run(){	// runs game
		init();

		long start, elapsed, wait;	//Vars to keep track of game's run times
		while(running){
			start = System.nanoTime();

			update();
			draw();
			drawToScreen();

			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed/1000000;
			if(wait <0) wait = 5;
			try{
				Thread.sleep(wait);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	private void update() {	// updates current game state
		currentState.update();
	}


	private void draw() {	// draws current game state
		currentState.draw(g);
	}

	private void drawToScreen() {	// scales and draws game with formating
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH ,  HEIGHT , null);
		g2.dispose();
	}
	public void keyTyped(KeyEvent e) {
		currentState.keyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		currentState.keyPressed(e);

	}

	public void keyReleased(KeyEvent e) {
		currentState.keyReleased(e);
	}
	
	public void mouseClicked(MouseEvent e){
		
		currentState.mouseClicked(e);
	}
	
	public void mousePressed(MouseEvent e){
		currentState.mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e){
		currentState.mouseReleased(e);
	}

	public void mouseEntered(MouseEvent e){
		currentState.mouseEntered(e);
	}
	
	public void mouseExited(MouseEvent e){
		currentState.mouseExited(e);
	}

	public void addNotify(){	// declares parent status and adds listeners
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			addKeyListener(this);
			addMouseListener(this);
			addMouseWheelListener(this);
			thread.start();
		}
	}
	
	

	public static void setGameState(GameState newCurrentState) {
		currentState = newCurrentState;
		currentState.init();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		currentState.mouseWheelMoved(e);
		
	}
}