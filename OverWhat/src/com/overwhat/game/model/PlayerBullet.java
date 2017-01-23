package com.overwhat.game.model;

import java.awt.Rectangle;

import com.overwhat.game.main.GameMain;

public class PlayerBullet {

//	private float deltaX = player.getX() - point.getX();
	private int x, y, width, height, velX;
	private Rectangle rect;
	
	private boolean visible = false;
	private boolean hitEnemy = false;

	public PlayerBullet(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = 33;
		this.height = 13;
		velX = 1000;
		rect = new Rectangle(x, y, width, height);
	}

	public void update(float delta) {
		x += velX * delta;
		updateRect();
	}

	private void updateRect() {
		rect.setBounds(x, y, width, height);
	}

	public boolean isDead() {
		return (x < 0 || x + width > GameMain.GAME_WIDTH);
	}

	public void spawnBullet(int mouseX, int mouseY) 
	{
		
	}
	
	public void removeBullet() {
		
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
		if (!hitEnemy)
		{
			if (x < 1100)
			{
				visible = true;
			}
			else
			{
				visible = false;
			}
		}
		return visible;
	}
	
	public void hideBullet()
	{
		hitEnemy = true;
		visible = false;
	}

	public void onCollideWM(WindowMaker windowmaker) {
		visible = false;
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
