package main;

import javax.swing.JFrame;

public class GameScreen {

	public static void main(String[] args) {
		
		JFrame window = new JFrame("School Architect");
		
		window.setContentPane(new Game());
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);

	}

}
