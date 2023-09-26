package gridGobble;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class GridGobble {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String[] nextLine = scan.nextLine().split(" ");
		int n = Integer.parseInt(nextLine[0]);
		int m = Integer.parseInt(nextLine[1]);
		int[][] grid = new int[n][m];
		for (int i = 0; i < n; i++) {
			nextLine = scan.nextLine().split(" ");
			for (int j = 0; j < m; j++) {
				grid[i][j] = Integer.parseInt(nextLine[j]);
			}
		}
		int[][] data = populate(n, m);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				data[i][j] = -1;
			}
		}
		Integer[] largest = new Integer[m];
			for (int i = 0; i < m; i++) {
				largest[i] = calculate(grid, data, n-1, i, m, n);
			}
		System.out.println(Collections.max(Arrays.asList(largest)));
		scan.close();
	}

	private static int calculate(int[][] grid, int[][] data, int i, int j, int m, int n) {
		int one;
		int two;
		int three;
		if (data[i][j] != -1) {
			return data[i][j];
		}
		if (i == 0) {
			return grid[i][j];
		}
		int total = 0;
		if (j == 0) {
			one = grid[i][j] + calculate(grid, data, i - 1, j, m, n);
			two = grid[i][j] - (grid [i-1][j+1])*2 + calculate(grid, data, i - 1, j + 1, m, n);
			three = grid[i][j] - (grid[i-1][m-1])*2 + calculate(grid, data, i - 1, m - 1, m, n);
			total = Math.max(Math.max(one, two),three);
		}
		else if (j != m-1) {
			one = grid[i][j] + calculate(grid, data, i - 1, j, m, n);
			two = grid[i][j] - (grid[i-1][j+1])*2 + calculate(grid, data, i - 1, j + 1, m, n);
			three = grid[i][j] - (grid[i-1][j-1])*2 + calculate(grid, data, i - 1, j - 1, m, n);
			total = Math.max(Math.max(one, two),three);
		}
		else {
			one = grid[i][j] + calculate(grid, data, i - 1, j, m, n);
			two = grid[i][j] - (grid[i-1][0])*2 + calculate(grid, data, i - 1, 0, m, n);
			three = grid[i][j] - (grid[i-1][j-1])*2 + calculate(grid, data, i - 1, j - 1, m, n);
			total = Math.max(Math.max(one, two),three);
		}
		data[i][j] =  total;
		return total;
	}
	public static int[][] populate(int n, int m){
		int[][] data = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				data[i][j] = -1;
			}
		}
		return data;
	}
	
	
}