package com.overwhat.game.state;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.overwhat.game.main.GameMain;
import com.overwhat.game.main.Resources;
import com.overwhat.game.model.Player;
import com.overwhat.game.model.PlayerBullet;
import com.overwhat.game.model.WindowBullet;
import com.overwhat.game.model.WindowMaker;


public class LevelOne extends State{

	
	// Player
	private Player player;
	private int playerHP = Player.playerMaxHP;
	private int lastHit = 0;
	
	// Enemy
	private WindowMaker windowmaker;
	public WindowBullet windowBullet;
	private int enemyHP = WindowMaker.enemyMaxHP;
	private String enemyName = "Windowmaker";
	private boolean ultInUse = false;
	public boolean bulletCantDmg = false;
	
	//private boolean[] keyChars;
	//private boolean[] keyCodes;
	private HashMap<Character, Boolean> keyChars = new HashMap<Character, Boolean>();
	private HashMap<Integer, Boolean> keyCodes = new HashMap<Integer, Boolean>();
	
	
	private Font guiFont;
	// Level Specific
	private int currentLevel = 1;

	private long fireTime;
	private ArrayList<PlayerBullet> playerBullets;
	private static final int PLAYERBULLET_WIDTH = 33;
	private static final int PLAYERBULLET_HEIGHT = 13;
	
	
	private static final int PLAYER_WIDTH = 133;
	private static final int PLAYER_HEIGHT = 176;
	
	private static final int ENEMY_WIDTH = 238;
	private static final int ENEMY_HEIGHT = 208;

	@Override
	public void init() {
		player = new Player(160, GameMain.GAME_HEIGHT - 45 - PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
		windowmaker = new WindowMaker(750, GameMain.GAME_HEIGHT - 45 - ENEMY_HEIGHT, ENEMY_WIDTH, ENEMY_HEIGHT);
		
		guiFont = new Font("Comic Sans MS", Font.BOLD, 25);
		
		playerBullets = new ArrayList<PlayerBullet>();
		Resources.windowTheme.play();
		
		windowBullet = new WindowBullet(-1000, -1000, 0, 0);

	}

	@Override
	public void update(float delta) {
if (!player.isAlive(playerHP))
{
	Resources.windowTheme.stop();
	Resources.tracedSniper.stop();
	Resources.tracedDeja.stop();
	Resources.tracedCheers.stop();
	setCurrentState(new GameOverState());
}
if (!windowmaker.isAlive(enemyHP))
{
	Resources.windowTheme.stop();
	setCurrentState(new GameCompleteState());
}
		// If ult is not current in use and enemy reaches 50% health
		if (!ultInUse)
		{
			if (enemyHP < (WindowMaker.enemyMaxHP/2))
			{
				Resources.windowUltSound.play();
				ultInUse = true;
			}
		}

		if (windowmaker.getGunShooting() || windowmaker.getGunReady())
		{
			windowmaker.stop();
		}
		else
		{
			if ((int) player.getY() < (int) windowmaker.getY())
			{
				windowmaker.accelUp();
			}
			else if ((int) player.getY() > (int) windowmaker.getY())
			{
				windowmaker.accelDown();
			}
			else if ((int) player.getY() == (int) windowmaker.getY())
			{
				windowmaker.stop();
			}
		}

		
		// Keybindings
		if((keyChars.containsKey('w') && keyChars.get('w')) || (keyCodes.containsKey(KeyEvent.VK_UP) && keyCodes.get(KeyEvent.VK_UP)))
		{
			player.accelUp();
		}
		else if (keyChars.containsKey('s') && keyChars.get('s') || (keyCodes.containsKey(KeyEvent.VK_DOWN) && keyCodes.get(KeyEvent.VK_DOWN)))
		{
			player.accelDown();
		}
		else
		{
			player.stop();
		}
		
		if (keyChars.containsKey('a') && keyChars.get('a') || (keyCodes.containsKey(KeyEvent.VK_LEFT) && keyCodes.get(KeyEvent.VK_LEFT)))
		{
			player.blinkUp();
		}
		else if (keyChars.containsKey('d') && keyChars.get('d') || (keyCodes.containsKey(KeyEvent.VK_RIGHT) && keyCodes.get(KeyEvent.VK_RIGHT)))
		{
			player.blinkDown();
		}
		
		
		if (keyChars.containsKey('e') && keyChars.get('e') || (keyCodes.containsKey(KeyEvent.VK_CONTROL) && keyCodes.get(KeyEvent.VK_CONTROL)))
		{
			if (player.getRecallReady())
			{
				player.recall();
			
				// cap health at max
				if (playerHP < (Player.playerMaxHP - lastHit))
				{
					playerHP = playerHP + WindowMaker.bulletDamage;
				}
				else
				{
					playerHP = Player.playerMaxHP;
				}
			}
		}
		
		if (keyCodes.containsKey(KeyEvent.VK_SPACE) && keyCodes.get(KeyEvent.VK_SPACE))
		{
			player.gunShoot();
				
			if (fireTime + 300 < System.currentTimeMillis()){
				fireTime = System.currentTimeMillis();
				PlayerBullet playerBullet = new PlayerBullet(player.getX() + 145, player.getY() + 70, PLAYERBULLET_WIDTH, PLAYERBULLET_HEIGHT);
				playerBullets.add(playerBullet);
			}
		}
		

		Resources.tracedRunAnim.update(delta);
		player.update(delta);
		updateBullets(delta);
		windowmaker.update(delta);
		updateEnemyBullets(delta);
		
	}

	
	private void updateBullets(float delta)
	{
		for (PlayerBullet b : playerBullets)
		{
			b.update(delta);
			
			if (b.isVisible())
			{

				if (b.getRect().intersects(windowmaker.getRect()))
				{
					b.hideBullet();
					b.onCollideWM(windowmaker);
					enemyHP = enemyHP - Player.bulletDamage;
				}
			}
		}
	}

	private void updateEnemyBullets(float delta)
	{
		if (windowBullet != null)
		{
			windowBullet.updateRect((boolean) (windowmaker.getGunReady()), (boolean) windowmaker.getGunShooting(), windowmaker.getY());
				if (windowBullet.getRect().intersects(player.getRect()) && windowmaker.getGunShooting())
				{
					if (!bulletCantDmg)
					{
						lastHit =  WindowMaker.bulletDamage;
						playerHP = playerHP - WindowMaker.bulletDamage;
						Resources.tracedSniper.play();
						bulletCantDmg = true;
					}
				}
			if (windowBullet.isVisible())
			{
				// if player is hit, it become true and wont deal damage anymore.Resets when not visible
				bulletCantDmg = false;
			}
		}
	}
	
	@Override
	public void render(Graphics g) {

			
		
		if (ultInUse)
		{
			g.setColor(Color.RED);
		}
		else
		{
			g.setColor(Color.GRAY);
		}
		g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		renderEnemyBullets(g);
		renderEnemy(g);
		renderPlayer(g);
		renderPlayerBullets(g);
		renderGUI(g);
		
		
	}
	
	private void renderGUI(Graphics g)
	{	
		g.setFont(guiFont);
		g.setColor(Color.WHITE);
		g.drawString("Level " + currentLevel, 20, 30);
		g.drawString("Traced", 100, 700);
		g.drawString(playerHP + " / 20", 100, 725);
		
		// PLAYER
		// Player Portrait
		g.drawImage(Resources.tracedPortrait, 20, 680, null);
		
		// Max health outline
		g.drawRoundRect(100, 730, 100, 20, 10, 15);
		// Player Health bar
		g.fillRoundRect(100, 730, (int) (playerHP / 0.2), 20, 10, 153);
			
		g.drawString("A", 827, 665);
		g.drawImage(Resources.tracedBlinkIcon, 800, 665, null);
		g.drawString("D", 827, 760);
		g.drawImage(Resources.tracedRecallIcon, 900, 665, null);
		g.drawString("E", 927, 665);
		
		if (!player.getBlinkReady())
		{
			g.setColor(Resources.cdBlack);
			g.fillRoundRect(803, 669, 68, (int) (player.getBlinkCD() * (68 / ((float) player.getBlinkMaxCD()))), 10, 10);
			
		}
		
		if (!player.getRecallReady())
		{
			g.setColor(Resources.cdBlack);
			g.fillRoundRect(903, 669, 68, (int) (player.getRecallCD() * (68 / ((float) (player.getRecallMaxCD())))), 10, 10);
		}
		
		
		if (player.getGunReload() > 0)
		{
			g.setColor(Resources.brightBlue);
			g.fillRect(1000, 669, 10, (int) (-((player.getGunReload() * 226.66)-68)));
		}
		
		if (player.getGunShooting() == false)
		{
			g.setColor(Resources.brightBlue);
			g.fillRect(1000, 669, 10, 68);
		}
		
		
		// ENEMY
		g.drawImage(Resources.windowPortrait, 940, 10, null);
		g.setColor(Color.WHITE);
		g.drawString(enemyName, 770, 25);
		g.drawString(enemyHP + " / " + WindowMaker.enemyMaxHP, 835, 50);
		
		g.drawRoundRect(830, 55, 100, 20, 10, 15 );
		g.fillRoundRect(830, 55, (int) (enemyHP / 0.75), 20, 10, 153);
	}
	
	private void renderPlayer(Graphics g)
	{
				Resources.tracedRunAnim.render(g, (int) player.getX(), (int) player.getY(), player.getWidth(), player.getHeight());
	}
	
	private void renderEnemy(Graphics g)
	{
		if (!ultInUse)
		{
				g.drawImage(Resources.windowMaker, (int) windowmaker.getX() - 100, (int) windowmaker.getY(), (int) ENEMY_WIDTH, (int) ENEMY_HEIGHT, null);
		}
		else
		{
			g.drawImage(Resources.windowMakerUlt, (int) windowmaker.getX() - 100, (int) windowmaker.getY(), (int) ENEMY_WIDTH, (int) ENEMY_HEIGHT, null);
		}
	}
	
	private void renderPlayerBullets(Graphics g)
	{
		for (PlayerBullet playerBullet : playerBullets)
		{
			if (playerBullet.isVisible())
			{
				g.drawImage(Resources.tracedBullet, (int) playerBullet.getX(), (int) playerBullet.getY(), PLAYERBULLET_WIDTH, PLAYERBULLET_HEIGHT, null);
			}
		}
	}
	
	private void renderEnemyBullets(Graphics g)
	{
		if (windowBullet != null)
		{
			if (windowBullet.isVisible())
			{
				g.setColor(Color.RED);
				g.fillRect((int) (windowBullet.getX()), (int) (windowBullet.getY()), 750, 15);			
			}
		}
	}


	
	@Override
	public void onClick(MouseEvent e) {
	}
	
	
	public void mousePressed(MouseEvent e)
	{
	}
	
	public void mouseReleased(MouseEvent e)
	{
	}


	@Override
	public void onKeyPress(KeyEvent e) {
		
		keyChars.remove(e.getKeyChar());
		keyChars.put(e.getKeyChar(), true);
		keyCodes.remove(e.getKeyCode());
		keyCodes.put(e.getKeyCode(), true);
		//keyChars[e.getKeyChar()] = true;
		//keyCodes[e.getKeyCode()] = true;

		
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		keyChars.remove(e.getKeyChar());
		keyChars.put(e.getKeyChar(), false);
		keyCodes.remove(e.getKeyCode());
		keyCodes.put(e.getKeyCode(), false);
		
	
	}

}
