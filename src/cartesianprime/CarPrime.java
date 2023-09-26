package cartesianprime;

import java.awt.Point;
import java.util.ArrayList;

public class CarPrime {
	public static void main(String args[]) {
	       /* Scanner scan = new Scanner(System.in); 
	         ArrayList<Point> coordinates = new ArrayList<Point>(); 
	         coordinates.add(new Point(scan.nextInt(), scan.nextInt())); //0 Kirk starting pt 
	         coordinates.add(new Point(scan.nextInt(), scan.nextInt())); //1 a and b  
	         coordinates.add(new Point(scan.nextInt(), scan.nextInt())); //2 c and d  
	         coordinates.add(new Point(scan.nextInt(), scan.nextInt())); //3 min til borg hit and number of ships
	         //4 All borg coordinates 
	         for(int i = 0; i < coordinates.get(3).y; i ++) {
	             coordinates.add(new Point(scan.nextInt(), scan.nextInt())); 
	        }
	         if(coordinates.get(0).x == 0 && coordinates.get(0).y ==0)
	             System.out.println("I had " + coordinates.get(3).x + " to spare! Beam me up Scotty!"); 

	         System.out.println(transport(coordinates)); 
	         scan.close();*/
	         
	        test();
	    }

	public static String transport(ArrayList<Point> coordinates) {
	        ArrayList<Integer> path = new ArrayList<Integer>();
	        Point temp;
	        Point start = coordinates.get(0);
	        Point goal = new Point(0, 0);
	        final int a = coordinates.get(1).x;
	        final int b = coordinates.get(1).y;
	        final int c = coordinates.get(2).x;
	        final int d = coordinates.get(2).y;
	        final int seconds = coordinates.get(3).x;
	        final int enemies = coordinates.get(3).y;
	        boolean boolX = false;
	        boolean boolY = true;
	        int x;
	        int y;
	        for (int i = 0; i < seconds; i++) {
	        	
	        }
	        if(path.size() <= 0) {
	            return "You will be assimilated! Resistance is futile!";
	        }else {
	            path.sort(null);
	            if(path.get(path.size() - 1) < 0) {
		            return "You will be assimilated! Resistance is futile!";
	            }
	            return ("I had " + path.get(path.size() - 1) + " to spare! Beam me up Scotty!");
	        }
	        
	    }

	public static void test() {

	        ArrayList<Point> coordinates = new ArrayList<Point>();
	        coordinates.add(new Point(3, 2));
	        coordinates.add(new Point(2, 3));
	        coordinates.add(new Point(1, 4));
	        coordinates.add(new Point(3, 0));
	        System.out.println(transport(coordinates));

	        coordinates = new ArrayList<Point>();
	        coordinates.add(new Point(3, 2));
	        coordinates.add(new Point(2, 3));
	        coordinates.add(new Point(1, 4));
	        coordinates.add(new Point(2, 0));
	        System.out.println(transport(coordinates));

	        coordinates = new ArrayList<Point>();
	        coordinates.add(new Point(-4, 4));
	        coordinates.add(new Point(1, 10));
	        coordinates.add(new Point(1, 10));
	        coordinates.add(new Point(5, 1));
	        coordinates.add(new Point(-3, -3));
	        System.out.println(transport(coordinates));
	        
	        coordinates = new ArrayList<Point>();
	        coordinates.add(new Point(4, -4));
	        coordinates.add(new Point(1, 10));
	        coordinates.add(new Point(1, 10));
	        coordinates.add(new Point(5, 1));
	        coordinates.add(new Point(-3, -3));
	        System.out.println(transport(coordinates));

	        coordinates = new ArrayList<Point>();
	        coordinates.add(new Point(-2, -2));
	        coordinates.add(new Point(1, 2));
	        coordinates.add(new Point(1, 2));
	        coordinates.add(new Point(4, 1));
	        coordinates.add(new Point(-1, -1));
	        System.out.println(transport(coordinates));
	    }
}
