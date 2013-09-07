package com.onedimension.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

public class GameComponent extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 160;
	public static final int HEIGHT = 140;
	public static final int SCALE = 4;
	
	private int[] pixels;
	private BufferedImage image;
	
	private boolean running;
	
	private JFrame frame;
	private Thread thread;
	public GameComponent() {
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		frame = new JFrame("A Spark");
		
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	}
	
	public void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		int frames = 0;
		int tickCount = 0;
		
		double delta = 0;
		double secondsPerTick = 1 / 60.0;
		
		long lastTime = System.nanoTime();
		
		requestFocus();
		
		while(running) {
			long now = System.nanoTime();
			long elapsedTime = now - lastTime;
			lastTime = now;
			if (elapsedTime < 0)
				elapsedTime = 0;
			if (elapsedTime > 100000000)
				elapsedTime = 100000000;
			delta += elapsedTime / 1000000000.0;

			boolean ticked = false;
			while (delta > secondsPerTick) {
				tick();
				delta -= secondsPerTick;
				ticked = true;

				tickCount++;
				if (tickCount % 60 == 0) {
					System.out.println(frames + " fps");
					lastTime += 1000;
					frames = 0;
				}
			}

			if (ticked) {
				render();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	Random r = new Random();
	
	public void tick() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = r.nextInt(0xffffff) & 0xff00ff;
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		new GameComponent().start();
	}
}
