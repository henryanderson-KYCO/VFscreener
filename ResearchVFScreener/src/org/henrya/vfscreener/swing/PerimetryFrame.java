package org.henrya.vfscreener.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import org.henrya.vfscreener.tests.types.PerimetryTest;

public class PerimetryFrame extends JFrame {
	private PerimetryTest perimetryTest;
	private PerimetryPanel perimetryPanel;
	
	public PerimetryFrame(PerimetryTest perimetryTest) {
		this.perimetryTest = perimetryTest;
		
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));

        this.perimetryPanel = new PerimetryPanel(this, this.perimetryTest);
        contentPane.add(this.perimetryPanel);

        contentPane.setBackground(Color.BLACK);
        this.setSize(new Dimension(this.perimetryTest.getPanelWidth() + 100, this.perimetryTest.getPanelWidth() + 100));
        
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                perimetryTest.onKeyPressed(event);
            }
        });
	}
	
	public PerimetryPanel getPerimetryPanel() {
		return this.perimetryPanel;
	}
}
