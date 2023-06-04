package org.henrya.vfscreener;

import org.henrya.vfscreener.algorithm.BasicAlgorithm;
import org.henrya.vfscreener.stimulus.FalsePositive;
import org.henrya.vfscreener.tests.types.BasicSquareTest;

public class Main {
	
	public static void main(String[] args) {
		BasicSquareTest test = new BasicSquareTest(20, 33, 5, 5);
		System.out.println(PerimetryUtils.getObjectSizeFromDegrees(20, 33));
		test.setAlgorithm(new BasicAlgorithm(800, 500, 0.1F, 0.02F, 1F, 0F));
		//test.getAllStimulusPoints().add(new FalsePositive());
		//test.getAllStimulusPoints().add(new FalsePositive());
		//test.getAllStimulusPoints().add(new FalsePositive());

		test.start();
	}

}
