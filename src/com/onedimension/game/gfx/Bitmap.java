package com.onedimension.game.gfx;

public class Bitmap {
	public final int width;
	public final int height;
	public final int[] pixels;
	
	public Bitmap(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[ width * height];
	}
	
	public void render(Bitmap bitmap, int xOffset, int yOffset) {
		for(int y = 0; y < bitmap.height; y++) {
			int yy = y + yOffset;
			if(yy < 0 || yy >= height) continue;
			for(int x = 0; x < bitmap.width; x++) {
				int xx = x + xOffset;
				if(xx < 0 || xx >= width) continue;
				int src = bitmap.pixels[x + y * bitmap.width];
				pixels[xx + yy * width] = src;
			}
		}
	}
	
	public void render(Bitmap bitmap, int xOffset, int yOffset, int xo, int yo, int w, int h, int col) {
		for(int y = 0; y < h; y++) {
			int yy = y + yOffset;
			if(yy < 0 || yy >= height) continue;
			for(int x = 0; x < w; x++) {
				int xx = x + xOffset;
				if(xx < 0 || xx >= width) continue;
				int src = bitmap.pixels[(x + xo) + (y + yo) * bitmap.width];
				if(src > 0) {
					pixels[xx + yy * width] = src * col;
				}
			}
		}
	}
}
