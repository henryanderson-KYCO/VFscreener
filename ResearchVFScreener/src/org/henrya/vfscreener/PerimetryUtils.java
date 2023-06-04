package org.henrya.vfscreener;

import java.awt.Dimension;
import java.awt.Toolkit;

public class PerimetryUtils {
	
	public static int getScreenWidth() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		return (int) screen.getWidth();
	}
	
	public static int getScreenHeight() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		return (int) screen.getHeight();
	}
	
	public static int getPixelsPerCm() {
		int pixelsPerInch = Toolkit.getDefaultToolkit().getScreenResolution();
		int pixelsPerCm = (int) (pixelsPerInch / 2.54);
		return pixelsPerCm;
	}
	
	// gets the total width of the test based on the total degrees of vision
	public static double getSizeFromDegreesCm(double degrees, double distance) {
		return getObjectSizeFromDegrees(degrees, distance);
	}
	public static int getSizeFromDegreesPixels(double degrees, double distance) {
		return (int) (getObjectSizeFromDegrees(degrees, distance) * getPixelsPerCm());
	}
	
	public static double getObjectSizeFromDegrees(double degrees, double distance) {
		double size = 2 * distance * Math.tan(Math.toRadians(degrees / 2));
		return size;
	}
	
	public static int getRoundedSize(double degrees, double distance) {
		int size = getSizeFromDegreesPixels(degrees, distance);
		return roundToNearestDegree(size, degrees);
	}
	
	public static int roundToNearestDegree(int size, double degrees) {
		double mod = size % degrees;
		double floor = size - mod;
		double ceiling = (size - mod) + degrees;
		System.out.println("mod " + mod);
		System.out.println("floor " + floor);
		System.out.println("ceiling " + ceiling);
		int roundedSize = (int) ((Math.abs(ceiling - size) < Math.abs(floor - size)) ? ceiling : floor);
		System.out.println("rounded:" + roundedSize);

		return roundedSize;
	}
}
