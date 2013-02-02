package a2;

import java.text.DecimalFormat;

public class TriangleImpl implements Triangle, Polygon{

	public TriangleImpl(Point a, Point b, Point c) {
		// TODO Auto-generated constructor stub
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
	public Category getCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Point getA() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getC() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point[] getPoints() {
		// TODO Auto-generated method stub
		return null;
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
