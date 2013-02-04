package a2;


/**
 * Jesse Osiecki
 * Febuary 4 2013
 */

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TriangleImpl implements Triangle, Polygon{
	
	//Making a Points array here, like polygon, so methods can be minimally modified between classes
	Point[] points;
	public TriangleImpl(Point a, Point b, Point c) {
		points = new Point[] {a,b,c};
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
	static double rtd3(double d) {
		//Vertex average was rounding up b/c of rtd, so I made this
		DecimalFormat twoDForm = new DecimalFormat("#.###");
	    return Double.valueOf(twoDForm.format(d));
	}

	@Override
	public Category getCategory() {
		ArrayList<Double> sides = new ArrayList<Double>();
		int sidesEqual = 0;
		sides.add(rtd(Math.sqrt(Math.pow(points[0].getX() - points[1].getX(), 2) + Math.pow(points[0].getY() - points[1].getY(), 2))));
		sides.add(rtd(Math.sqrt(Math.pow(points[1].getX() - points[2].getX(), 2) + Math.pow(points[1].getY() - points[2].getY(), 2))));
		sides.add(rtd(Math.sqrt(Math.pow(points[2].getX() - points[0].getX(), 2) + Math.pow(points[2].getY() - points[0].getY(), 2))));
		for(int i = 0; i<2; i++){
			//shrinking nesteed loop
			for(int q = i; q<3; q++){
				if(q != i){
					//System.out.println(sides.get(i) + " " + sides.get(q));
					if(sides.get(i).equals(sides.get(q))){
						sidesEqual++;
					}
				}
			}
		}
		//System.out.println(sidesEqual);
		
		//TODO works but need to come back and check logic
		if(sidesEqual >= 2){
			return Category.EQUILATERAL;
		}
		else if(sidesEqual == 1){
			return Category.ISOSCELES;
		}
		else {
			return Category.SCALENE;
		}
	}

	@Override
	public boolean isRight() {
		ArrayList<Double> sides = new ArrayList<Double>();
		for(int i = 0; i<points.length; i++){
			double x0 = 0, x1 = 0, y1 = 0, y0 = 0;
			try{
				x0 = points[i].getX(); y0 = points[i].getY(); x1 = points[i+1].getX(); y1 = points[i+1].getY();
			}catch(ArrayIndexOutOfBoundsException e1){
				x0 = points[i].getX(); y0 = points[i].getY(); x1 = points[0].getX(); y1 = points[0].getY();
			}
			double newSide = rtd(Math.sqrt(Math.pow((x1 - x0), 2) + Math.pow((y1 - y0), 2)));
			sides.add(newSide);
		}
		if(rtd(sides.get(0)) == rtd(Math.sqrt(sides.get(1) * sides.get(1) + sides.get(2) * sides.get(2)))){
			return true;
		}
		else if(rtd(sides.get(1)) == rtd(Math.sqrt(sides.get(2) * sides.get(2) + sides.get(0) * sides.get(0)))){
			return true;
		}
		else if(rtd(sides.get(2)) == rtd(Math.sqrt(sides.get(0) * sides.get(0) + sides.get(1) * sides.get(1)))){
			return true;
		}
		else{
			return false;
		}
	}

	public Point getA() {
		return points[0];
	}

	public Point getB() {
		return points[1];
	}
	public Point getC() {
		return points[2];
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
		avgX = rtd(avgX)/points.length;
		avgY = rtd(avgY)/points.length;
		return new Point(rtd3(avgX), rtd3(avgY));
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
		/*
		System.out.println("LowX" + lowX);
		System.out.println("LowY" + lowY);
		System.out.println("highX" + highX);
		System.out.println("highY" + highY);
		*/
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
