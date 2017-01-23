package com.overwhat.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

import com.overwhat.game.main.GameMain;
import com.overwhat.game.main.Resources;

public class GameOverState extends State {

	private Font font;
	
	public GameOverState()
	{
		font = new Font("Comic Sans MS", Font.BOLD, 50);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		Resources.tracedHappened.play();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0,  0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("GAME OVERwhat", 325, 175);
		g.setColor(Color.YELLOW);
		g.drawString("Press E or Ctrl to Continue", 175, 350);
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		if (e.getKeyChar() == 'e' || e.getKeyCode() == KeyEvent.VK_CONTROL)
		{
			setCurrentState(new MenuState());
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
