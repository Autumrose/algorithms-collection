package Rainbow;

import java.util.ArrayList;
import java.util.Scanner;

public class underTheRainbow {
	public static void main(String[] args)
    {
		Scanner scan = new Scanner(System.in);
		String nextLine = scan.nextLine();
		int numOfHotels = Integer.parseInt(nextLine);
		scan.nextLine();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		while (scan.hasNextLine() ) {
			distances.add(Integer.parseInt(scan.nextLine()));
		}
		scan.close();
		System.out.println(calculate(distances, numOfHotels));   
//		test();
    }
	public static void test() {
		ArrayList<Integer> distances = new ArrayList<Integer>();
		int numOfHotels = 3;
		distances.add(350);
		distances.add(450);
		distances.add(825);
		System.out.println(calculate(distances, numOfHotels));
		
		distances.clear();
		distances.add(350);
		distances.add(450);
		distances.add(700);
		System.out.println(calculate(distances, numOfHotels));
	}
	public static String calculate(ArrayList<Integer> distances, int numOfHotels) {
		ArrayList<Integer> min = new ArrayList<Integer>(numOfHotels+1);
		for (int i = 0; i < numOfHotels ; i++)
        {
			min.add((400 - distances.get(i)) * (400 - distances.get(i)));

            for (int j = 0; j < i; j++)
            {

                int dailyPenalty = (int)Math.pow(400 - (distances.get(i) - distances.get(j)), 2);
                if ((min.get(j) + dailyPenalty) < min.get(i))
                {
                    min.set(i, min.get(j) + dailyPenalty);
                }
            }
        }
		return Integer.toString(min.get(numOfHotels-1));
	}
}
