package org.henrya.vfscreener.stimulus;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

public class CentralStimulus extends Stimulus {

	private int simpleX = -1; // x coordinate relative to other stimulus
	private int simpleY = -1; // y coordinate relative to other stimulus
	private int attempts;
	private float value;
	
	public CentralStimulus(int startX, int startY, int size) {
		super(startX, startY, size);		
		
		Graphics2D graphics1 = (Graphics2D) this.getImage1().getGraphics();
	    GradientPaint gp1 = new GradientPaint(25, 25, Color.white, 1, 25, Color.black, true);

	    graphics1.setPaint(gp1);
	    graphics1.fillOval(0, 0, this.getSize(), this.getSize());
	    
	    Graphics2D graphics2 = (Graphics2D) this.getImage2().getGraphics();
	    GradientPaint gp2 = new GradientPaint(25, 25, Color.black, 1, 25, Color.white, true);

	    graphics2.setPaint(gp2);
	    graphics2.fillOval(0, 0, this.getSize(), this.getSize());
	}
	
	public int getSimpleX() {
		return this.simpleX;
	}
	
	public int getSimpleY() {
		return this.simpleY;
	}

	
	public void addAttempt() {
		this.attempts++;
	}
	
	public int getAttempts() {
		return this.attempts;
	}
	
	public float getValue() {
		return this.value;
	}
	
	public void setValue(float value) {
		this.value = value;
	}

}
