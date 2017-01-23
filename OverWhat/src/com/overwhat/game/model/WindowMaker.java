package com.overwhat.game.model;

import java.awt.Rectangle;

import com.overwhat.game.main.GameMain;
import com.overwhat.game.main.Resources;

public class WindowMaker{
	
	private int x, y;
	private int width, height, velY;
	private Rectangle rect;
	
	//player stats

	public static final int enemyMaxHP = 75;
	public static final int bulletDamage = 7;
	
	private boolean gunShooting;
	private boolean gunReady = false;
	
	private float gunMaxAim = 0.5f;
	private float gunAim = gunMaxAim;
	private float gunMaxReload = 1.5f;
	private float gunReload = gunMaxReload;


	
	private final static int MOVE_SPEED_Y = 6;
	
	public WindowMaker(float x, float y, int width, int height)
	{
		this.width = 120;
		this.height = 120;
		this.x = 850;
		this.y = (GameMain.GAME_HEIGHT/2) - height;
		

		rect = new Rectangle();
		gunShooting = false;
		updateRects();
	}
	
	public void update(float delta)
	{
		
		
		y += velY;

		if (y < 0) {
			y = 0;
		} else if (y + height > GameMain.GAME_HEIGHT) {
			y = GameMain.GAME_HEIGHT - height;
		}

		
		if (gunReload > 0)
		{
			gunReload -= delta;
			gunShooting = false;
		}
		else
		{
			gunReady = true;
			//gunReload = gunMaxReload;
		}
		
		// if gun is not ready
		
		// if reload time ends and the gun is ready
		if (gunReload <= 0 && gunReady)
		{
			// begin gun aim
			gunAim -= delta;
			
			// once gun aim reaches 0 make gun not ready and shoot
			if (gunAim <= 0 && gunReady)
			{
				// prevents it from continuously casting gunShoot while gunReload is 0	
				gunShoot();
				gunAim = gunMaxAim;
				gunReady = false;
			}
		}
		
		updateRects();
	}
	
	public void updateRects()
	{
		rect.setBounds((int) x + 10, (int) y, width - 20, height);
		
	}
	
	
// MOVEMENT
	public void accelUp() {
		velY = -MOVE_SPEED_Y;
	}

	public void accelDown() {
		velY = MOVE_SPEED_Y;
	}

	public void stop() {
		velY = 0;
	}
	
	
// HEALTH
	public boolean isAlive(int enemyHP)
	{
		if (enemyHP > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
// ATTACKS
	public void gunShoot()
	{
		if (gunShooting == false)
		{
			Resources.windowGun.play();
			gunShooting = true;
			gunReload = gunMaxReload;
		}
	}
		

	
	
	public float getGunReload()
	{
		return gunReload;
	}
	
	public float getGunMaxReload()
	{
		return gunMaxReload;
	}
	
	public boolean getGunShooting()
	{
		return gunShooting;
	}
	
	public boolean getGunReady()
	{
		return gunReady;
	}

	public float getGunAim()
	{
		return gunAim;
	}

	public float getGunMaxAim()
	{
		return gunMaxAim;
	}
	
	
	public int getX()
	{
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getVelY()
	{
		return velY;
	}

	public Rectangle getRect()
	{
		return rect;
	}
	



}
