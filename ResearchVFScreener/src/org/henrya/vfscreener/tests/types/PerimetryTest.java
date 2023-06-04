package org.henrya.vfscreener.tests.types;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

import org.henrya.vfscreener.stimulus.Stimulus;
import org.henrya.vfscreener.algorithm.BasicAlgorithm;
import org.henrya.vfscreener.swing.PerimetryFrame;

public abstract class PerimetryTest {
	private PerimetryFrame frame;
	private int panelLength;
	private int panelWidth;
	private long startTime;
	private long endTime;
	private Stimulus currentStimulus;
	private BasicAlgorithm algorithm;
	private LinkedList<Stimulus> stimulusPoints = new LinkedList<Stimulus>();
	private int fixationStartX;
	private int fixationStartY;
	private int fixationSize;
	
	public abstract void onStart();
	public abstract void onKeyPressed(KeyEvent event);
	
	public void start() {
		this.startTime = System.currentTimeMillis();
		this.frame = new PerimetryFrame(this);
		frame.setVisible(true);
		//this.algorithm.getTimer().start();
	}
	
	public void stop() {
		this.endTime = System.currentTimeMillis();
		this.getAlgorithm().getTimer().stop();
		this.frame.setVisible(false);
	}
	
	public int getPanelLength() {
		return this.panelLength;
	}
	
	public int getPanelWidth() {
		return this.panelWidth;
	}

	protected void setPanelLength(int panelLength) {
		this.panelLength = panelLength;
	}
	
	protected void setPanelWidth(int panelWidth) {
		this.panelWidth = panelWidth;
	}
	
	public long getStartTime() {
		return this.startTime;
	}
	
	public long getStopTime() {
		return this.endTime;
	}
	
	public void setCurrentStimulus(Stimulus stimulus) {
		this.currentStimulus = stimulus;
	}
	
	public Stimulus getCurrentStimulus() {
		return this.currentStimulus;
	}
	
	public void setAlgorithm(BasicAlgorithm algorithm) {
		this.algorithm = algorithm;
		this.algorithm.generateQueue(this);
	}
	
	public BasicAlgorithm getAlgorithm() {
		return this.algorithm;
	}
	
	public LinkedList<Stimulus> getAllStimulusPoints() {
		return this.stimulusPoints;
	}
	
	public PerimetryFrame getFrame() {
		return this.frame;
	}
	
	public int getFixationStartX() {
		return this.fixationStartX;
	}
	public int getFixationStartY() {
		return this.fixationStartY;
	}
	public int getFixationSize() {
		return this.fixationSize;
	}
	public void setFixationStartX(int fixationStartX) {
		this.fixationStartX = fixationStartX;
	}
	public void setFixationStartY(int fixationStartY) {
		this.fixationStartY = fixationStartY;
	}
	public void setFixationSize(int fixationSize) {
		this.fixationSize = fixationSize;
	}
}
