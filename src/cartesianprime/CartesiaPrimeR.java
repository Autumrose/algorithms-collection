package cartesianprime;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CartesiaPrimeR {

	public static void main(String[] args) {
		ArrayList<Point> enemies = new ArrayList<Point>();
		Scanner scan = new Scanner(System.in);
		int x = scan.nextInt();
		int y = scan.nextInt();
		int a = scan.nextInt();
		int b = scan.nextInt();
		int c = scan.nextInt();
		int d = scan.nextInt();
		int time = scan.nextInt();
		int ships = scan.nextInt();
		// 4 All borg coordinates
		for (int i = 0; i < ships; i++) {
			enemies.add(new Point(scan.nextInt(), scan.nextInt()));
		} 
		scan.close();
		// Determine if a final state is reachable using DFS.
		HashMap<Location, Location> prev = new HashMap<Location, Location>();
		Location s = breadthFirstSearch(new Location(x, y, a, b, c, d, time, ships, enemies, 0), prev);
		if(s == null) {
			System.out.println("You will be assimilated! Resistance is futile!");
		}else {
			LinkedList<Location> result = new LinkedList<Location>();
			result.addFirst(s);
			while (prev.get(s) != s) {
				s = prev.get(s);
				result.addFirst(s);
			}
			int size = result.size() - 1;
			if ((size > time) || (size <= 0)) {
				System.out.println("You will be assimilated! Resistance is futile!");
			} else {
				size = time - size;
				System.out.println("I had " + size + " to spare! Beam me up Scotty!");
			}
		}

		//test();
	}

	public static Location breadthFirstSearch(Location start, HashMap<Location, Location> prev) {
		prev.put(start, start);
		LinkedList<Location> queue = new LinkedList<Location>();
		queue.addLast(start);
		int count = 1;
		while (!queue.isEmpty()) {
			Location loc = queue.removeFirst();
			count = loc.level + 1;
			for (Location next : loc.nextLocations(count)) {
				if (prev.get(next) == null) {
					queue.addLast(next);
					prev.put(next, loc);
					if (next.isFinalLocation()) {
						return next;
					}
				}
			}
			
		}
		return null;
	}

	public static void test() {
		ArrayList<Point> enemies = new ArrayList<Point>();
		HashMap<Location, Location> prev = new HashMap<Location, Location>();
		Location s = breadthFirstSearch(new Location(3, 2, 2, 3, 1, 4, 3, 0, enemies, 0), prev);
		if(s == null) {
			System.out.println("You will be assimilated! Resistance is futile!");
		}else {
			LinkedList<Location> result = new LinkedList<Location>();
			result.addFirst(s);
			while (prev.get(s) != s) {
				s = prev.get(s);
				result.addFirst(s);
			}
			int size = result.size() - 1;
			if ((size > 3) || (size <= 0)) {
				System.out.println("You will be assimilated! Resistance is futile!");
			} else {
				size = 3 - size;
				System.out.println("I had " + size + " to spare! Beam me up Scotty!");
			}
		}

		prev = new HashMap<Location, Location>();
		s = breadthFirstSearch(new Location(3, 2, 2, 3, 1, 4, 2, 0, enemies, 0), prev);
		if(s == null) {
			System.out.println("You will be assimilated! Resistance is futile!");
		}else {
			LinkedList<Location> result = new LinkedList<Location>();
			result.addFirst(s);
			while (prev.get(s) != s) {
				s = prev.get(s);
				result.addFirst(s);
			}
			int size = result.size() - 1;
			if ((size > 2)) {
				System.out.println("You will be assimilated! Resistance is futile!");
			} else {
				size = 2 - size;
				System.out.println("I had " + size + " to spare! Beam me up Scotty!");
			}
		}

		prev = new HashMap<Location, Location>();
		enemies.add(new Point(-3, 3));
		s = breadthFirstSearch(new Location(-4, 4, 1, 10, 1, 10, 5, 1, enemies, 0), prev);

		if(s == null) {
			System.out.println("You will be assimilated! Resistance is futile!");
		}else {
			LinkedList<Location> result = new LinkedList<Location>();
			result.addFirst(s);
			while (prev.get(s) != s) {
				s = prev.get(s);
				result.addFirst(s);
			}
			int size = result.size() - 1;
			if ((size > 5)) {
				System.out.println("You will be assimilated! Resistance is futile!");
			} else {
				size = 5 - size;
				System.out.println("I had " + size + " to spare! Beam me up Scotty!");
			}
		}

		prev = new HashMap<Location, Location>();
		enemies.clear();
		enemies.add(new Point(-1, -1));
		s = breadthFirstSearch(new Location(-2, -2, 1, 2, 1, 2, 4, 1, enemies, 0), prev);

		if(s == null) {
			System.out.println("You will be assimilated! Resistance is futile!");
		}else{
			LinkedList<Location> result = new LinkedList<Location>();
			result.addFirst(s);
			while (prev.get(s) != s) {
				s = prev.get(s);
				result.addFirst(s);
			}
			int size = result.size() - 1;
			if ((size > 4)) {
				System.out.println("You will be assimilated! Resistance is futile!");
			} else {
				size = 4 - size;
				System.out.println("I had " + size + " to spare! Beam me up Scotty!");
			}
		}
		
	}
}

class Location {

	private int x;
	private int y;
	private int time;
	private int a;
	private int b;
	private int c;
	private int d;
	private int ships;
	private ArrayList<Point> enemies;
	public int level;

	// Constructs the specified state
	public Location(int x, int y, int a, int b, int c, int d, int time, int ships, ArrayList<Point> enemies, int level) {
		this.x = x;
		this.y = y;
		this.time = time;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.ships = ships;
		this.enemies = enemies;
		this.level = level;
	}

	// Returns list of all states reachable with one pouring
	public List<Location> nextLocations(int count) {
		LinkedList<Location> points = new LinkedList<Location>();
		//if(count < (time + 2)) {
			int tempX = 0;
			int tempY = 0;
			// ++ CASE
			tempX = (count * a) % b;
			tempY = (count * c) % d;
			if (!causeConflict(ships, x + tempX, y + tempY, enemies)) {
				points.add(new Location(tempX + x, tempY + y, a, b, c, d, time, ships, enemies, count));
			}
	
			// -- CASE
			tempX = (count * a) % b;
			tempX *= -1;
			tempY = (count * c) % d;
			tempY *= -1;
			if (!causeConflict(ships, x + tempX, y + tempY, enemies)) {
				points.add(new Location(tempX + x, tempY + y, a, b, c, d, time, ships, enemies, count));
			}
	
			// -+ CASE
			tempX = (count * a) % b;
			tempX *= -1;
			tempY = (count * c) % d;
			if (!causeConflict(ships, x + tempX, y + tempY, enemies)) {
				points.add(new Location(tempX + x, tempY + y, a, b, c, d, time, ships, enemies, count));
			}
	
			// +- CASE
			tempX = (count * a) % b;
			tempY = (count * c) % d;
			tempY *= -1;
			if (!causeConflict(ships, x + tempX, y + tempY, enemies)) {
				points.add(new Location(tempX + x, tempY + y, a, b, c, d, time, ships, enemies, count));
			}
		//}
		return points;
	}

	public static boolean causeConflict(int ships, int x, int y, ArrayList<Point> enemies) {
		for (Point enemy : enemies) {
			if ((enemy.x == x) && (enemy.y == y)) {
				return true;
			}
		}
		return false;
	}

	public boolean isFinalLocation() {
		return (x == 0 && y == 0);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Location) {
			Location s = (Location) o;
			return (x == s.x) && (y == s.y);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return x + " " + y;
	}
	
	@Override
	public int hashCode() {		 
		int result = 17;
		result = 31 * result + Integer.hashCode(x);
		result = 31 * result + Integer.hashCode(y);
		result = 31 * result + Integer.hashCode(level);
		return result;
	}
}
