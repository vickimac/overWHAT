package com.overwhat.game.model;

import java.awt.Rectangle;

import com.overwhat.game.main.GameMain;
import com.overwhat.game.model.Player;

public class WindowBullet {

//	private float deltaX = player.getX() - point.getX();
	private int x, y, width, height;
	private Rectangle rect;

	
	private boolean visible = false;

	public WindowBullet(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = 1000;
		this.height = 15;
		rect = new Rectangle(x, y, width, height);
	}

	public void update(float delta) {
		
	}

	public void updateRect(boolean rdy, boolean shooting, int windowY) {
		// if gun is ready, have it update 
		if (rdy || shooting)
		{
				visible = true;
				x = 0;
				y = windowY + 40;
				rect.setBounds((int) x, (int) y, (int)width, (int)height);
			
		}
		else
		{
			visible = false;
			x = -1000;
			y = -1000;
			rect.setBounds((int) x, (int) y, 0, 0);
		}
	}

	public boolean isDead() {
		return (x < 0 || x + width > GameMain.GAME_WIDTH);
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Rectangle getRect() {
		return rect;
	}

	public boolean isVisible() {
		// TODO Auto-generated method stub
		return visible;
	}

	public void onCollide(Player player) {
		visible = false;
		y = -1000;
		x = -1000;
	}

	public boolean isOutsideBounds() {
		// TODO Auto-generated method stub
		if (x >= 1300 || y >= 768 || y <= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
