package com.overwhat.framework.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

import com.overwhat.game.state.State;

public class InputHandler implements KeyListener, MouseListener 
{
	private final Set<Character> pressed = new HashSet<Character>();
	
	private State currentState;
	
	public void setCurrentState(State currentState)
	{
		this.currentState = currentState;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		currentState.onClick(e);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		currentState.onKeyPress(e);
		
		// if more than 1 key is currently pressed
		if (pressed.size() > 1)
		{
			pressed.add(e.getKeyChar());	
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		currentState.onKeyRelease(e);
		pressed.remove(e.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
