package org.henrya.vfscreener.tests.types;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.Timer;

import org.henrya.vfscreener.stimulus.CentralStimulus;
import org.henrya.vfscreener.stimulus.FDTStimulus;
import org.henrya.vfscreener.stimulus.Stimulus;
import org.henrya.vfscreener.algorithm.BasicAlgorithm;
import org.henrya.vfscreener.PerimetryUtils;


public class BasicSquareTest extends PerimetryTest {
	private int degrees;
	private int distance;
	private int stimulusSize;
	private int centralStimulusSize;
	private int panelSize;

	
	public BasicSquareTest(int degrees, int distance, int stimulusSize, int centralStimulusSize) {
		this.degrees = degrees;
		this.distance = distance;
		this.stimulusSize = stimulusSize;
		this.centralStimulusSize = centralStimulusSize;
		
		this.panelSize = PerimetryUtils.getRoundedSize(this.degrees, this.distance);
		System.out.println(PerimetryUtils.getObjectSizeFromDegrees(this.degrees, this.distance));
		this.setPanelLength(panelSize);
		this.setPanelWidth(panelSize);
		this.setFixationSize(10);
		
		int fixationCoord = (this.panelSize / 2) - (this.getFixationSize() / 2);
		
		this.setFixationStartX(fixationCoord);
		this.setFixationStartY(fixationCoord);

		this.generateGrid();
	}
	
	private void generateGrid() {
		int degreeSize = this.panelSize / (this.degrees);
		int stimulusSize = degreeSize * this.stimulusSize;
		int gridWidth = this.panelSize / stimulusSize;
		int gridLength = this.panelSize / stimulusSize;
		
		System.out.println(gridWidth);
		System.out.println(stimulusSize);
		
		int centralSize = degreeSize * this.centralStimulusSize;
		int centerStartX = (this.panelSize / 2) - (centralSize / 2);
		int centerStartY = (this.panelSize / 2) - (centralSize / 2);

        this.getAllStimulusPoints().add(new CentralStimulus(centerStartX, centerStartY, centralSize));

		
		for(int x = 0; x < gridLength; x++) {
			for(int y = 0; y < gridWidth; y++) {
				int startX = x * stimulusSize;
				int startY = y * stimulusSize;
                this.getAllStimulusPoints().add(new FDTStimulus(startX, startY, stimulusSize, x, y));
			}
		}
	}
	


	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onKeyPressed(KeyEvent event) {
		System.out.println("KEY PRESSED");
		this.getCurrentStimulus().setClicked(true);
		
	}
}
