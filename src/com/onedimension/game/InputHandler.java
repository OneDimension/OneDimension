package com.onedimension.game;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements KeyListener, MouseMotionListener, MouseListener, FocusListener {

	public boolean[] keys = new boolean[65536];
	
	public void focusGained(FocusEvent e) {
		
	}

	public void focusLost(FocusEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key > 0 || key <= keys.length) {
			keys[key] = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key > 0 || key <= keys.length) {
			keys[key] = false;
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
}
