package a2;

/**
 * Jesse Osiecki
 * Febuary 4 2013
 */

import java.text.DecimalFormat;
import java.util.Iterator;

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

	public Point[] getPoints() {
		//clone method used to make encapsulation peachy
		return points.clone();
	}

	public int getNumSides() {
		// #sides should == #points
		return points.length;
	}

	public Point getVertexAverage() {
		double avgX = 0;
		double avgY = 0;
		for(Point p : points){
			avgX += p.getX();
			avgY += p.getY();
		}
		avgX = avgX/points.length;
		avgY = avgY/points.length;
		return new Point(rtd(avgX), rtd(avgY));
	}

	public Rectangle getBoundingBox() {
		//logically this init only works if the loop runs once
		double highX = 0, lowX = 0, highY = 0, lowY = 0;
		boolean fR = true;
		for(Point p: points){
			if(fR){
				lowX = p.getX();
				lowY = p.getY();
				highX = p.getX();
				highY = p.getY();
				fR = false;
			}
			else{
				if(p.getX()<lowX){
					lowX = p.getX();
				}
				else if(p.getX() > highX){
					highX = p.getX();
				}
				if(p.getY()<lowY){
					lowY = p.getY();
				}
				else if(p.getY()>highY){
					highY = p.getY();
				}
			}
		}
		
		return new RectangleImpl(new Point(rtd(lowX), rtd(lowY)), new Point(rtd(highX), rtd(highY)));
	}

	@Override
	public Point getCentroid() {
		double cx = 0, cy = 0;
		for(int i = 0; i < points.length; i++){
			double x0, y0, x1, y1;
			
			try{
				x0 = points[i].getX(); y0 = points[i].getY(); x1 = points[i+1].getX(); y1 = points[i+1].getY();
			}catch(ArrayIndexOutOfBoundsException e1){
				x0 = points[i].getX(); y0 = points[i].getY(); x1 = points[0].getX(); y1 = points[0].getY();
			}
			
			cx += (x0 + x1) * (x0 * y1 - x1 * y0);
			cy += (y0 + y1) * (x0 * y1 - x1 * y0);
		}
		
		cx *= 1/(6*getRawArea());
		cy *= 1/(6*getRawArea());
		return new Point(rtd(cx), rtd(cy));
	}

	public double getArea() {

		return rtd(Math.abs(getRawArea()));
	}
	private double getRawArea(){
		double area = 0, x0, x1, y0, y1;
		for(int i = 0; i<points.length; i++){
			try{
				x0 = points[i].getX(); y0 = points[i].getY(); x1 = points[i+1].getX(); y1 = points[i+1].getY();
			}catch(ArrayIndexOutOfBoundsException e1){
				x0 = points[i].getX(); y0 = points[i].getY(); x1 = points[0].getX(); y1 = points[0].getY();
			}
			area += (x0*y1 - y0*x1);
			
		}
		area *= .5;
		return area;
	}

	public void move(double dx, double dy) {
		//have to access array elements, rather not use iterator here
		for(int i = 0; i<points.length; i ++){
			points[i] = points[i].translate(rtd(dx), rtd(dy));
		}
	}

	public void move(Point c) {
		double dx = c.getX() - getCentroid().getX();
		double dy = c.getY() - getCentroid().getY();
		move(rtd(dx), rtd(dy));
	}

	public void scale(double factor) {
		//centroid origin at zero
		Point c = getCentroid();
		for(int i = 0; i<points.length; i ++){
			points[i] = points[i].translate(-1 * c.getX(), -1 * c.getY());
		}
		//TODO do I really need two loops here? Seems messy
		//Scale the shape, move back
		for(int i = 0; i<points.length; i ++){
			points[i] = new Point(rtd(points[i].getX() * factor + c.getX()), rtd(points[i].getY() * factor + c.getY()));
		}
		
	}

}
