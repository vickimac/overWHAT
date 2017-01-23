package com.overwhat.game.main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.overwhat.framework.animation.Animation;
import com.overwhat.framework.animation.Frame;

public class Resources {
	public static BufferedImage iconpng, tracedRun1, tracedRun2, tracedRun3, tracedRun4, menuLogo, menuSymbol, menuOptions, menuSelector, tracedBullet, tracedPortrait, tracedBlinkIcon, tracedRecallIcon, windowMaker, windowMakerUlt, windowPortrait;
	
	public static AudioClip menuTheme, menuBeep, tracedCheers, tracedDeja, tracedGun, windowGun, windowTheme, windowUltSound, congratulations, tracedHappened, tracedSniper;
	
	public static Color skyBlue, owBlue, brightBlue, cdBlack;
	
	public static Animation tracedRunAnim;
	
	public static void load()
	{
		// MENU RESOURCES
		menuLogo = loadImage("Menu_Logo.png");
		menuSymbol = loadImage("Menu_Symbol.png");
		menuOptions = loadImage("Menu_Options.png");
		menuSelector = loadImage("Menu_Selector.png");	
		
		menuTheme = loadSound("overWhat_theme.wav");
		menuBeep = loadSound("Menu_Beep2.wav");
		
		congratulations = loadSound("congrats.wav");
		
		iconpng = loadImage("iconpng.png");
		
		// TRACED RESOURCES
		tracedRun1 = loadImage("traced/Traced_Run_1.png");
		tracedRun2 = loadImage("traced/Traced_Run_2.png");
		tracedRun3 = loadImage("traced/Traced_Run_3.png");
		tracedRun4 = loadImage("traced/Traced_Run_4.png");
		Frame f1 = new Frame(tracedRun1, .2f);
		Frame f2 = new Frame(tracedRun2, .2f);
		Frame f3 = new Frame(tracedRun3, .2f);
		Frame f4 = new Frame(tracedRun4, .2f);
		tracedRunAnim = new Animation(f1, f2, f3, f4);
		
		tracedPortrait = loadImage("traced/Traced_Portrait.png");
		tracedBullet = loadImage("traced/traced_Bullet.png");
		tracedBlinkIcon = loadImage("traced/Traced_BlinkIcon.png");
		tracedRecallIcon = loadImage("traced/Traced_RecallIcon.png");
		
		tracedCheers = loadSound("traced/traced_Cheers.wav");
		tracedDeja = loadSound("traced/traced_deja.wav");
		tracedGun = loadSound("traced/Traced_Gun.wav");
		tracedHappened = loadSound("traced/traced_happened.wav");
		tracedSniper = loadSound("traced/traced_sniper.wav");
		
			
		// WINDOWMAKER RESOURCES
		windowMaker = loadImage("windowmaker/WindowMaker.png");
		windowMakerUlt = loadImage("windowmaker/WindowMakerULT.png");
		windowPortrait = loadImage("windowmaker/Window_Portrait.png");
		
		windowGun = loadSound("windowmaker/window_gun.wav");
		windowTheme = loadSound("windowmaker/windowmaker_theme.wav");
		windowUltSound = loadSound("windowmaker/window_ult.wav");
		
		// COLOUR RESOURCES
		skyBlue = new Color(208, 244, 247);
		owBlue = new Color(0, 210, 255);
		brightBlue = new Color(0, 255, 246);
		cdBlack = new Color(0, 0, 0, 164);
	}
	
	private static AudioClip loadSound(String filename)
	{
		URL fileURL = Resources.class.getResource("/resources/" + filename);
		return Applet.newAudioClip(fileURL);
	}
	
	private static BufferedImage loadImage(String filename)
	{
		BufferedImage img = null;
		try {
			img = ImageIO.read(Resources.class.getResourceAsStream("/resources/" + filename));
		} catch (IOException e) {
			System.out.println("Error while reading: " + filename);
			e.printStackTrace();
		}
		return img;
	}
}
