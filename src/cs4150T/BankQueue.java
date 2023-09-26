package cs4150T;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BankQueue {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		String nextLine = scan.nextLine();
		String data[] = nextLine.split(" ");
		ArrayList<Person> line = new ArrayList<Person>();
		int numOfPeople = Integer.parseInt(data[0]);
		while (scan.hasNextLine()) {
			nextLine = scan.nextLine();
			data = nextLine.split(" ");
			line.add(new Person(Integer.parseInt(data[1]), Integer.parseInt(data[0])));
		}
		System.out.println(calculate(line));
		scan.close();
//		test();
	}

	public static void test() {
		ArrayList<Person> line = new ArrayList<Person>();

		line.add(new Person(1, 1000));
		line.add(new Person(2, 2000));
		line.add(new Person(2, 500));
		line.add(new Person(0, 1200));

		System.out.println(calculate(line));
		line.clear();
		line.add(new Person(0, 1000));
		line.add(new Person(1, 2000));
		line.add(new Person(1, 500));

		System.out.println(calculate(line));
	}
	
	public static int calculate(ArrayList<Person> line) {
		Collections.sort(line);
//		ArrayList<Boolean> helped = new ArrayList<Boolean>();
		boolean[] helped = new boolean[100];
		int timeLeft;
		int total = 0;
		for(int i = 0; i < line.size(); i++) {
            timeLeft = line.get(i).minToWait;
			while(timeLeft >=0) {
				if(!helped[timeLeft]) {
					total += line.get(i).amount;
					helped[timeLeft] = true;
					break;
				}
				timeLeft--;
			}
		}
		return total;
	}

}
class Person implements Comparable<Person>{
	int minToWait;
	int amount;
	Person(int minToWait, int amount){
		this.minToWait = minToWait;
		this.amount = amount;
	}

	@Override
	public int compareTo(Person person) {
		return person.amount - amount;
	}

	
}
