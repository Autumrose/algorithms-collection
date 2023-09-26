package narrowArtGallery;

import java.util.Arrays;
import java.util.Scanner;

public class NarrowArtClass {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int blocks = scan.nextInt();
		int rows = scan.nextInt();
		
		while (rows != 0) {
			int[][] rooms = new int[rows][2];
			for (int i=0; i<rows; i++)
				for (int j=0; j<2; j++)
					rooms[i][j] = scan.nextInt();
			int[][][] dp = new int[rows][blocks+1][3];
			for (int i=0; i<rows; i++)
				for (int j=0; j<=blocks; j++)
					Arrays.fill(dp[i][j], -1);
			dp[0][0][0] = rooms[0][0] + rooms[0][1];
			if (blocks > 0) dp[0][1][1] = rooms[0][1];
			if (blocks > 0) dp[0][1][2] = rooms[0][0];
			for (int i=1; i<rows; i++) {
				for (int j=0; j<=i+1 && j<=blocks; j++) {
					if (maxArr(dp[i-1][j]) >= 0)
						dp[i][j][0] = rooms[i][0] + rooms[i][1] + maxArr(dp[i-1][j]);
					if (j > 0 && Math.max(dp[i-1][j-1][1], dp[i-1][j-1][0]) >= 0)
						dp[i][j][1] = rooms[i][1] + Math.max(dp[i-1][j-1][1], dp[i-1][j-1][0]);
					if (j > 0 && Math.max(dp[i-1][j-1][2], dp[i-1][j-1][0]) >= 0)
						dp[i][j][2] = rooms[i][0] + Math.max(dp[i-1][j-1][2], dp[i-1][j-1][0]);
				}
			}
			System.out.println(maxArr(dp[rows-1][blocks]));
			rows = scan.nextInt();
			blocks = scan.nextInt();
		}
		scan.close();
	}

	public static int maxArr(int[] arr) {
		int solution = arr[0];
		for (int i=1; i<arr.length; i++)
			solution = Math.max(solution, arr[i]);
		return solution;
	}
}
