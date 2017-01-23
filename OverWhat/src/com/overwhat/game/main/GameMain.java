package com.overwhat.game.main;

import javax.swing.JFrame;

public class GameMain {

	private static final String GAME_TITLE = "overWHAT";
	public static final int GAME_WIDTH = 1024;
	public static final int GAME_HEIGHT = 768;
	public static Game sGame;
	
	public static void main(String[] args)
	{
		
		JFrame frame = new JFrame(GAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		sGame = new Game(GAME_WIDTH, GAME_HEIGHT);
		frame.add(sGame);
		frame.pack();
		frame.setVisible(true);	
		frame.setIconImage(Resources.iconpng);
	}
}
