package com.overwhat.game.model;

import java.awt.Rectangle;

import com.overwhat.game.main.GameMain;
import com.overwhat.game.main.Resources;

public class Player{
	
	private int x, y;
	private int width, height, velY;
	private Rectangle rect;
	
	//player stats

	public static final int playerMaxHP = 20;
	public static final int bulletDamage = 1;
	
	private boolean gunShooting;
	private float gunReload = .3f;
	
	private boolean blinkReady;
	private float blinkCD = 0f;
	private float blinkMaxCD = 4f;
	
	private boolean recallReady;
	private float recallMaxCD = 14.0f;
	private float recallCD = 0f;
	private float recallMaxImmuneDur = 1.0f;
	private float recallImmuneDur = 0f;
	private boolean recallImmune;
	
	
	
	private final static int MOVE_SPEED_Y = 7;
	
	public Player(float x, float y, int width, int height)
	{
		this.x = 2;
		this.y = 0;
		this.width = 189;
		this.height = 194;
		

		rect = new Rectangle();
		recallImmune = false;
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

		
		if (gunReload > 0 && gunShooting)
		{
			gunReload -= delta;
		}
		else
		{
			gunShooting = false;
			gunReload = .3f;
		}
		
		// if recall is not ready, update cooldown. if cooldown is 0 then reset recall
		if (!recallReady)
		{
			if (recallCD > 0)
			{
				recallCD -= delta;
			}
			else
			{
				recallReady = true;
			}
		}
	
		// if immune from recall is up, countdown the duration. if duration is 0 then reset immunity.
		if (recallImmune)
		{
			if (recallImmuneDur > 0)
			{
				recallImmuneDur -= delta;
			}
			else
			{
				recallImmune = false;
			}
		}
		
		
		//cd on blink to prevent spam and promote strategic play over who can press keys the fastest
		if (!blinkReady)
		{
			if (blinkCD > 0)
			{
				blinkCD -= delta;
			}
			else
			{
				blinkReady = true;
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
	
	public void blinkUp() {
		// if blink is ready
		if (blinkReady)
		{
			blinkReady = false;
			blinkCD = blinkMaxCD;
			y = 0;
			Resources.tracedCheers.play();
		}
	}
	
	public void blinkDown(){
		if (blinkReady)
		{
			blinkReady = false;
			blinkCD = blinkMaxCD;
			y = (GameMain.GAME_HEIGHT - height);
			Resources.tracedCheers.play();
		}
	}
	

// HEALTH
	public boolean isAlive(int playerHP)
	{
		if (playerHP > 0)
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
			Resources.tracedGun.play();
			gunShooting = true;
		}
	}
	
	public float getGunReload()
	{
		return gunReload;
	}
	
	public boolean getGunShooting()
	{
		return gunShooting;
	}

	// Recall ability. If it is ready, activates immunity, plays sound and heals the health from last hit. Also makes it not ready and sets cooldown to max.
	public void recall()
	{
		if (recallReady)
		{
			recallReady = false;
			recallCD = recallMaxCD;
			recallImmune = true;
			recallImmuneDur = recallMaxImmuneDur;
			
			Resources.tracedDeja.play();
		}
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
	
	// get blink cooldown view
	public float getBlinkCD()
	{
		return blinkCD;
	}
	

	public float getBlinkMaxCD() {
		// TODO Auto-generated method stub
		return blinkMaxCD;
	}
	
	public boolean getBlinkReady()
	{
		return blinkReady;
	}
	
	// get recall cooldown view
	public float getRecallCD()
	{
		return recallCD;
	}
	
	public boolean getRecallReady()
	{
		return recallReady;
	}
	

	public float getRecallMaxCD()
	{
		return recallMaxCD;
	}



}
