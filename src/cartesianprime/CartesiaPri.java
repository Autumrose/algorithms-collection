package cartesianprime;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartesiaPri {
	public static void main(String[] args) {
		/*Scanner scan = new Scanner(System.in);
		ArrayList<Point> enemies = new ArrayList<Point>();
		int x = scan.nextInt();
		int y = scan.nextInt();
		int a = scan.nextInt();
		int b = scan.nextInt();
		int c = scan.nextInt();
		int d = scan.nextInt();
		int time = scan.nextInt();
		int ships = scan.nextInt();

		for (int i = 0; i < ships; i++) {
			enemies.add(new Point(scan.nextInt(), scan.nextInt()));
		}
		int solution = traverse(x, y, enemies, 0, a, b, c, d, time, ships);
		if(solution == 1000000000) {
			System.out.println("You will be assimilated! Resistance is futile!");
		}else {
			System.out.println("I had " + (solution + 1) + " to spare! Beam me up Scotty!");
		}

		scan.close();*/

		test();
	}

	public static int traverse(int x, int y, ArrayList<Point> enemies, int counter, int a, int b, int c, int d, int time, int ships) {
		int tempX = 0;
		int tempY = 0;
		if ((time + 1) == counter) {
			return -1;
		}
		if (x == 0 && y == 0) {			
			return 1;
		}
		if (x != 0) {
			tempX = (counter * a) % b;
		}
		if (y != 0) {
			tempY = (counter * c) % d;
		}
		if (ships != 0 && causeConflict(x + tempX, y + tempY, enemies, ships)) {
			return -1;
		}
		
		int first = traverse(tempX + x, tempY + y, enemies, counter+1, a, b, c, d, time, ships);
		int second = traverse((-1 * tempX) + x, (-1 * tempY) + y, enemies, counter+1, a, b, c, d, time, ships);
		int third = traverse(tempX + x, (-1 * tempY) + y, enemies, counter+1, a, b, c, d, time, ships);
		int fourth = traverse((-1 * tempX) + x, tempY + y, enemies, counter+1, a, b, c, d, time, ships);
		if(first < 0) {
			first = 1000000000;
		}
		if(second < 0) {
			second = 1000000000;
		}
		if(third < 0) {
			third = 1000000000;
		}
		if(fourth < 0) {
			fourth = 1000000000;
		}
		int comp1 = Math.min(first, second);
		int comp2 = Math.min(third, fourth);
		return Math.min(comp1, comp2);
		
	}

	public static boolean causeConflict(int x, int y, ArrayList<Point> enemies, int ships) {
		for (int j = 0; j < ships; j++) {
			if ((enemies.get(j).x == x) && (enemies.get(j).y == y)) {
				return true;
			}
		}
		return false;
	}

	public static void test() {

		int x = 3;
		int y = 2;
		ArrayList<Point> enemies = new ArrayList<Point>();
		int solution = traverse(x, y, enemies, 1, 2, 3, 1, 4, 3, 0);
		if(solution == 1000000000) {
			System.out.println("You will be assimilated! Resistance is futile!");
		}else {
			System.out.println("I had " + (solution + 1) + " to spare! Beam me up Scotty!");
		}

		x = 3;
		y = 2;
		solution = traverse(x, y, enemies, 1, 2, 3, 1, 4, 2, 0);
		if(solution == 1000000000) {
			System.out.println("You will be assimilated! Resistance is futile!");
		}else {
			System.out.println("I had " + (solution + 1) + " to spare! Beam me up Scotty!");
		}

		x = -4;
		y = 4;
		enemies.add(new Point(-3, 3));
		solution = traverse(x, y, enemies, 1, 1, 10, 1, 10, 5, 1);
		if(solution == 1000000000) {
			System.out.println("You will be assimilated! Resistance is futile!");
		}else {
			System.out.println("I had " + (solution + 1) + " to spare! Beam me up Scotty!");
		}

		x = -2;
		y = -2;
		enemies.clear();
		enemies.add(new Point(-1, -1));
		solution = traverse(x, y, enemies, 1, 1, 2, 1, 2, 4, 1);
		if(solution == 1000000000) {
			System.out.println("You will be assimilated! Resistance is futile!");
		}else {
			System.out.println("I had " + (solution + 1) + " to spare! Beam me up Scotty!");
		}
	}
}
