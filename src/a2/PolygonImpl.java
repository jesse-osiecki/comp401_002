package a2;

/**
 * Jesse Osiecki
 * Febuary 4 2013
 */

import java.text.DecimalFormat;

public class PolygonImpl implements Polygon {
	
	Point[] points;

	public PolygonImpl(Point[] points){
		this.points = points.clone();
	}
	
	/**
	 * rtd, or roundTwoDecimals rounds the double to two decimal points
	 * @param d the double to be rounded
	 * @return the input rounded to two decimal points
	 * CREDIT: Piazza Site - Kingsley Chanakira
	 */
	static double rtd(double d) {
	    DecimalFormat twoDForm = new DecimalFormat("#.##");
	    return Double.valueOf(twoDForm.format(d));
	}

	@Override
	public Point[] getPoints() {
		//clone method used to make encapsulation peachy
		return points.clone();
	}

	@Override
	public int getNumSides() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point getVertexAverage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getCentroid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void move(double dx, double dy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void move(Point c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void scale(double factor) {
		// TODO Auto-generated method stub

	}

}
