package org.henrya.vfscreener.swing;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import org.henrya.vfscreener.stimulus.FDTStimulus;
import org.henrya.vfscreener.stimulus.Stimulus;
import org.henrya.vfscreener.tests.types.PerimetryTest;

public class PerimetryPanel extends JPanel {
	private PerimetryFrame frame;
	private PerimetryTest perimetryTest;
	private Dimension dimension;
	
	public PerimetryPanel(PerimetryFrame frame, PerimetryTest perimetryTest) {
		this.frame = frame;
		this.perimetryTest = perimetryTest;
		
		this.dimension = new Dimension(this.perimetryTest.getPanelLength(), this.perimetryTest.getPanelWidth());
		this.setBackground(Color.WHITE);
	}
	
	@Override
    public Dimension getMinimumSize() {
        return this.dimension;
    }

    @Override
    public Dimension getMaximumSize() {
        return this.dimension;    }

    @Override
    public Dimension getPreferredSize() {
        return this.dimension;
    }
    
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Graphics2D graphics = (Graphics2D) g.create();
    	Stimulus currentStimulus = this.perimetryTest.getCurrentStimulus();
    	
    	int fixationSize = perimetryTest.getFixationSize();
    	graphics.fillOval(perimetryTest.getFixationStartX(), perimetryTest.getFixationStartY(), fixationSize, fixationSize);
    	System.out.println(1);
    	System.out.println("visible: " + currentStimulus.isVisible());
    	if(currentStimulus != null && currentStimulus.isVisible()) {
    		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, currentStimulus.getOpacity()));
    		graphics.drawImage(currentStimulus.getCurrentImage(), currentStimulus.getStartX(), currentStimulus.getStartY(), this);
    		currentStimulus.nextImage();
    		System.out.println("painted");
    
    	}
    }
}
