package cartesianprime;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class CartesiaPrime {
	public static void main(String args[]) {

		/*
		 * Scanner scan = new Scanner(System.in); ArrayList<Point> coordinates = new
		 * ArrayList<Point>(); coordinates.add(new Point(scan.nextInt(),
		 * scan.nextInt())); //0 Kirk starting pt coordinates.add(new
		 * Point(scan.nextInt(), scan.nextInt())); //1 a and b coordinates.add(new
		 * Point(scan.nextInt(), scan.nextInt())); //2 c and d coordinates.add(new
		 * Point(scan.nextInt(), scan.nextInt())); //3 min til borg hit and number of
		 * ships //4 All borg coordinates for(int i = 0; i < coordinates.get(3).y; i ++)
		 * { coordinates.add(new Point(scan.nextInt(), scan.nextInt())); }
		 * System.out.println(transport(coordinates)); scan.close();
		 */
		test();
	}

	public static String transport(ArrayList<Point> coordinates) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		Point temp;
		Point goal = new Point(0, 0);
		final int a = coordinates.get(1).x;
		final int b = coordinates.get(1).y;
		final int c = coordinates.get(2).x;
		final int d = coordinates.get(2).y;
		final int seconds = coordinates.get(3).x;
		final int enemies = coordinates.get(3).y;
		int x = 0;
		int y = 0;
		for (int i = 1; i <= seconds; i++) {
			x = 0;
			y = 0;
			if ((coordinates.get(0).x != 0) && (coordinates.get(0).x > 0)) {
				x = (i * a) % b;
				if ((coordinates.get(0).x - x >= 0)) {
					x = x * -1;
				} else if ((coordinates.get(0).x + x) != ((i + 1) * a) % b) {
					x = x * -1;
				}
			} else if ((coordinates.get(0).x != 0) && (coordinates.get(0).x < 0)) {
				x = (i * a) % b;
				if ((coordinates.get(0).x + x > 0) && (coordinates.get(0).x - x != (((i + 1) * a) % b) * -1)) {
					x = x * -1;
				}
			}
			if ((coordinates.get(0).y != 0) && (coordinates.get(0).y > 0)) {
				y = (i * c) % d;
				if ((coordinates.get(0).y - y >= 0)) {
					y = y * -1;

				} else if ((coordinates.get(0).y + y) != ((i + 1) * c) % d) {
					y = y * -1;
				}
			} else if ((coordinates.get(0).y != 0) && (coordinates.get(0).y < 0)) {
				y = (i * c) % d;
				if ((coordinates.get(0).y + y > 0) && (coordinates.get(0).y - y != (((i + 1) * c) % d) * -1)) {
					y = y * -1;
				}
			}
			temp = new Point(x + coordinates.get(0).x, y + coordinates.get(0).y);
			Point bestRoute;
			if (causeConflict(enemies, temp, coordinates)) {
				bestRoute = new Point((-1 * x) + coordinates.get(0).x, (-1 * y) + coordinates.get(0).y);
				if (!causeConflict(enemies, bestRoute, coordinates)
						&& (((bestRoute.x + ((i + 1) * a) % b) == 0) || (((bestRoute.x - ((i + 1) * a) % b) == 0)))
						&& (((bestRoute.y + ((i + 1) * a) % b) == 0) || ((bestRoute.y - ((i + 1) * a) % b) == 0))) {
					x = x * -1;
					y = y * -1;
				}
				bestRoute = new Point((-1 * x) + coordinates.get(0).x, y + coordinates.get(0).y);
				if (!causeConflict(enemies, bestRoute, coordinates)
						&& (((bestRoute.x + ((i + 1) * a) % b) == 0) || ((bestRoute.x - ((i + 1) * a) % b) == 0))) {
					x = x * -1;
				}
				bestRoute = new Point(x + coordinates.get(0).x, (-1 * y) + coordinates.get(0).y);
				if (!causeConflict(enemies, bestRoute, coordinates)
						&& (((bestRoute.y + ((i + 1) * a) % b) == 0) || ((bestRoute.y - ((i + 1) * a) % b) == 0))) {
					y = y * -1;
				}

			}
			coordinates.set(0, temp);
			if (temp.equals(goal)) {
				path.add(seconds - i);
				return ("I had " + (seconds - i) + " to spare! Beam me up Scotty!");
			}

		}
		return "You will be assimilated! Resistance is futile!";

	}

	public static boolean causeConflict(int enemies, Point temp, ArrayList<Point> coordinates) {
		for (int j = 3; j < 3 + enemies; j++) {
			if (coordinates.get(0).equals(coordinates.get(j + 1))) {
				return true;
			}
		}
		return false;
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
		coordinates.add(new Point(-3, 3));
		System.out.println(transport(coordinates));

//		coordinates = new ArrayList<Point>();
//		coordinates.add(new Point(4, -4));
//		coordinates.add(new Point(1, 10));
//		coordinates.add(new Point(1, 10));
//		coordinates.add(new Point(5, 1));
//		coordinates.add(new Point(-3, -3));
//		System.out.println(transport(coordinates));

		coordinates = new ArrayList<Point>();
		coordinates.add(new Point(-2, -2));
		coordinates.add(new Point(1, 2));
		coordinates.add(new Point(1, 2));
		coordinates.add(new Point(4, 1));
		coordinates.add(new Point(-1, -1));
		System.out.println(transport(coordinates));
	}
}
