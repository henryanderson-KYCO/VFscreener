package org.henrya.vfscreener.algorithm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.Timer;

import org.henrya.vfscreener.stimulus.FDTStimulus;
import org.henrya.vfscreener.stimulus.CentralStimulus;

import org.henrya.vfscreener.stimulus.Stimulus;
import org.henrya.vfscreener.tests.types.PerimetryTest;

public class BasicAlgorithm {
	private int falsePositives = 0;
	private float startOpacity;
	private float opacityRate;
	private float max;
	private float min;
	private LinkedList<Stimulus> queue;
	private PerimetryTest perimetryTest;
	private Timer timer;

	public BasicAlgorithm(int responseDelay, int visibilityTime, float startOpacity, float opacityRate, float max, float min) {
		this.startOpacity = startOpacity;
		this.opacityRate = opacityRate;
		this.max = max;
		this.min = min;
		this.timer = new Timer(responseDelay + visibilityTime, new ActionListener(){
 
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!queue.isEmpty()) {
					
					Stimulus currentStimulus = perimetryTest.getCurrentStimulus();
					float lastClickedOpacity = currentStimulus.getLastClickedOpacity();	
					float currentOpacity = currentStimulus.getOpacity();
					System.out.println("1500---" + getOutputData(currentStimulus) + "------- " + currentStimulus.isVisible());

					if(currentStimulus.getClicked()) {
						successfulClick(currentStimulus, currentOpacity, lastClickedOpacity);
					}
					else {
						missedClick(currentStimulus, currentOpacity, lastClickedOpacity);
					}
					
					perimetryTest.getFrame().getPerimetryPanel().repaint();
					currentStimulus.setClicked(false);
					
					
					
					if(!queue.isEmpty()) {
						perimetryTest.setCurrentStimulus(queue.getFirst());
						currentStimulus = perimetryTest.getCurrentStimulus();
						currentStimulus.setVisible(true);
					}
					
				}
				if(queue.isEmpty()) {
					System.out.println("COMPLETE");
					perimetryTest.setCurrentStimulus(null);
					for(Stimulus stim : perimetryTest.getAllStimulusPoints()) {
						System.out.println(getOutputData(stim));
					}
				}
			}
		});
		timer.start();
		
		Timer test = new Timer(40, new ActionListener(){
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				if(perimetryTest.getFrame()  != null) {
				Stimulus current = perimetryTest.getCurrentStimulus();
				if((current.getVisibleStartTime() + visibilityTime) < System.currentTimeMillis()) {
					current.setVisible(false);
					perimetryTest.getFrame().getPerimetryPanel().repaint();
				}
				//System.out.println(getOutputData(current) + "------- " + current.isVisible());
				perimetryTest.getFrame().getPerimetryPanel().repaint();

			}
			}
		});
		test.start();
	}
	
	private void successfulClick(Stimulus currentStimulus, float currentOpacity, float lastClickedOpacity) {
		float newOpacity = currentOpacity - opacityRate;
		if(newOpacity > max) {
			currentStimulus.setLastClickedOpacity(max);
			queue.remove();
		}
		else if(newOpacity < min) {
			currentStimulus.setLastClickedOpacity(max);
			queue.remove();
		}
		else if(lastClickedOpacity != -1 && newOpacity >= lastClickedOpacity) { // threshold found for point
			queue.remove();
		}
		else {
			currentStimulus.setOpacity(newOpacity);
			currentStimulus.setLastClickedOpacity(currentOpacity);
			queue.remove();
			queue.add(currentStimulus);
		}
	}
	
	private void missedClick(Stimulus currentStimulus, float currentOpacity, float lastClickedOpacity) {
		float newOpacity = currentOpacity + opacityRate;
		if(newOpacity > max) {
			currentStimulus.setLastClickedOpacity(max);
			queue.remove();
		}
		else if(newOpacity < min) {
			currentStimulus.setLastClickedOpacity(max);
			queue.remove();
		}
		else if(lastClickedOpacity != -1 && newOpacity >= lastClickedOpacity) { // threshold found for point
			queue.remove();
		}
		else {
			currentStimulus.setOpacity(newOpacity);
			queue.remove();
			queue.add(currentStimulus);
		}
	}
	
	private String getOutputData(Stimulus stim) {
		if(stim instanceof FDTStimulus) {
			FDTStimulus grid = (FDTStimulus) stim;
			return "(" + grid.getSimpleX() + ", " + grid.getSimpleY() + ")  - " + grid.getLastClickedOpacity();
		}
		else if(stim instanceof CentralStimulus) {
			CentralStimulus center = (CentralStimulus) stim;
			return "(" + center.getSimpleX() + ", " + center.getSimpleY() + ")  - " + center.getLastClickedOpacity();
		}
		return null;
	}
	
	
	public void generateQueue(PerimetryTest perimetryTest) {
		this.perimetryTest = perimetryTest;
		this.queue = new LinkedList<Stimulus>();
		for(Stimulus stimulus : this.perimetryTest.getAllStimulusPoints()) {
			stimulus.setOpacity(this.startOpacity);
			this.queue.add(stimulus);
		}
		Collections.shuffle(this.queue);
		System.out.println(queue.size());
		
		perimetryTest.setCurrentStimulus(this.queue.getFirst());
		perimetryTest.getCurrentStimulus().setVisible(true);
	}
	
	public Stimulus getNextStimulus() {
		if(this.queue.get(1) != null) {
			return this.queue.get(1);
		}
		return null;
	}
	
	public Timer getTimer() {
		return this.timer;
	}
}
