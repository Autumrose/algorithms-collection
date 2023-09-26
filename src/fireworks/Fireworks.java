package fireworks;

import java.util.ArrayList;
import java.util.Scanner;

public class Fireworks {
	public static void main(String args[]) 
	{ 
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt(); 
		int k = scan.nextInt();
		ArrayList<Integer> combine = new ArrayList<Integer>();
		combine.add(0);
		for (int i = 1; i < n; i++) {
			combine.add(scan.nextInt() + combine.get(i-1));
		} 
		
		int scenario;
		while (scan.hasNext()) {
			scenario = scan.nextInt();
			System.out.println(binarySearch(combine, n, scenario));
		}
		scan.close();
		//test();
	} 
		static int binarySearch(ArrayList<Integer> combine, int n, int scenario) 
		{ 
			combine.sort(null);; 
			int high = combine.get(n-1); 
			int mid; 
			int low = 0;
			int solution = 0; 
			boolean poss = false;
			int magnet;
			int loc;
			while (high >= low) 
			{ 
				poss = false;
				mid = (low + high)/2;
				magnet = 1;
				loc = combine.get(0); 
				for (int i = 1; i < n; i++) 
				{ 
					if (combine.get(i) - loc >= mid) 
					{ 
						magnet++; 
						loc = combine.get(i); 
	 
						if (scenario == magnet) 
							poss = true; 
					} 
				} 
				if (poss == true) {
					solution = Math.max(solution, mid); 
					low = mid + 1;
				}else{ 
					high = mid - 1; 
				} 
			} 
			return solution; 
		} 


		public static void test() {
			int n = 4;
			ArrayList<Integer> test = new ArrayList<Integer>();
			test.add(0);
			test.add(1);
			test.add(3);
			test.add(6);
			
			int scenario = 3;
			System.out.println(binarySearch(test, n, scenario));

			
		}
}
