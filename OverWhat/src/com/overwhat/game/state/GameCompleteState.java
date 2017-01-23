package com.overwhat.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

import com.overwhat.game.main.GameMain;
import com.overwhat.game.main.Resources;

public class GameCompleteState extends State {

	private Font font;
	
	public GameCompleteState()
	{
		font = new Font("Comic Sans MS", Font.BOLD, 75);
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		Resources.congratulations.play();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0,  0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		g.setColor(Color.DARK_GRAY);
		g.setFont(font);
		g.drawString("GAME COMPLETE", 175, 175);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		g.setColor(Color.GRAY);
		g.drawString("I hope you enjoyed my completely original and unique game!", 100, 350);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Press E or Ctrl to Continue", 15, 500);
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
			Resources.congratulations.stop();
		}
		
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
