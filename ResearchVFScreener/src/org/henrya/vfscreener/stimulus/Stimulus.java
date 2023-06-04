package org.henrya.vfscreener.stimulus;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Stimulus {
	private int startX;
	private int startY;
	private int size;
	private BufferedImage image1;
	private BufferedImage image2;
	private BufferedImage currentImage = image1;

	private boolean clicked;
	private float opacity;
	private float lastClickedOpacity = -1;
	private long visibleStartTime;
	private boolean visible;
	
	
	public Stimulus(int startX, int startY, int size) {
		this.startX = startX;
		this.startY = startY;
		this.size = size;
		
		this.image1 = new BufferedImage(this.size, this.size, BufferedImage.TYPE_INT_ARGB);
		this.image2 = new BufferedImage(this.size, this.size, BufferedImage.TYPE_INT_ARGB);

	    //Graphics2D graphics = (Graphics2D) this.image.getGraphics();
	    //graphics.fillRect(0, 0, this.size, this.size);

	}
	
	public int getStartX() {
		return this.startX;
	}
	
	public int getStartY() {
		return this.startY;
	}
	public int getSize() {
		return this.size;
	}
	
	public BufferedImage getImage1() {
		return this.image1;
	}
	public BufferedImage getImage2() {
		return this.image1;
	}
	
	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}
	
	public float getOpacity() {
		return this.opacity;
	}
	
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	public boolean getClicked() {
		return this.clicked;
	}
	
	public float getLastClickedOpacity() {
		return this.lastClickedOpacity;
	}
	public void setLastClickedOpacity(float opacity) {
		this.lastClickedOpacity = opacity;
	}
	public boolean isVisible() {
		return this.visible;
	}
	public void setVisible(boolean isVisible) {
		this.visible = isVisible;
		if(isVisible) {
			this.visibleStartTime = System.currentTimeMillis();
		}
		else {
			this.visibleStartTime = 0;
		}
	}
	public long getVisibleStartTime() {
		return this.visibleStartTime;
	}
	
	public BufferedImage getCurrentImage() {
		return this.currentImage;
	}
	
	public void nextImage() {
		if(this.getCurrentImage() == this.image1) {
			this.currentImage = this.image2;
			System.out.println("img2");
		}
		else {
			this.currentImage = this.image1;
			System.out.println("img1");

		}
	}
}
