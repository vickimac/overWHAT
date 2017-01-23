package com.overwhat.game.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.overwhat.game.main.GameMain;
import com.overwhat.game.main.Resources;

public class MenuState extends State{

	private int currentSelection = 0;
	
	@Override
	public void init() {
		Resources.menuTheme.play();
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Resources.owBlue);
		g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		
		g.drawImage(Resources.menuLogo, 225, 310, null);
		g.drawImage(Resources.menuSymbol, 370, 35, null);
		g.drawImage(Resources.menuOptions, 325, 475, null);
		
		
		
		if (currentSelection == 0)
		{
			g.drawImage(Resources.menuSelector, 350, 500, null);
		}
		else
		{
			g.drawImage(Resources.menuSelector, 350, 600, null);
		}
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onKeyPress(KeyEvent e) {
	
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			Resources.menuTheme.stop();
			Resources.tracedGun.play();
			if (currentSelection == 0)
			{
				setCurrentState(new LevelOne());
			}
			else if (currentSelection == 1)
			{
				GameMain.sGame.exit();
			}
		}
		else if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP)
		{
			Resources.menuBeep.play();
			currentSelection = 0;
		}
		else if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			Resources.menuBeep.play();
			currentSelection = 1;
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
