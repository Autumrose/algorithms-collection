package narrowArtGallery;

import java.util.Arrays;
import java.util.Scanner;

public class NarrowArtGalleryFail {

	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(System.in);
			int blocks = scan.nextInt();
			int rows = scan.nextInt();

			while (rows != 0) {
				int[][] rooms = new int[rows][2];
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < 2; j++) {
						rooms[i][j] = scan.nextInt();
					}
				}
				System.out.println(work(rows, blocks, rooms));
				rows = scan.nextInt();
				blocks = scan.nextInt();
			}
			scan.close();
		} catch (Exception e) {

		}
	}

	public static String work(int rows, int blocks, int[][] rooms) {
		int[][][] all = new int[rows][blocks + 1][3];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j <= blocks; j++)
				Arrays.fill(all[i][j], -1);

		all[0][0][0] = rooms[0][0] + rooms[0][1];
		if (blocks > 0) {
			all[0][1][1] = rooms[0][1];
		}
		if (blocks > 0) {
			all[0][1][2] = rooms[0][0];
		}

		for (int i = 1; i < rows; i++) {
			for (int j = 0; j <= i + 1 && j <= blocks; j++) {

				if (findMax(all[i - 1][j]) >= 0)
					all[i][j][0] = rooms[i][0] + rooms[i][1] + findMax(all[i - 1][j]);

				if (j > 0 && Math.max(all[i - 1][j - 1][1], all[i - 1][j - 1][0]) >= 0)
					all[i][j][1] = rooms[i][1] + Math.max(all[i - 1][j - 1][1], all[i - 1][j - 1][0]);
				if (j > 0 && Math.max(all[i - 1][j - 1][2], all[i - 1][j - 1][0]) >= 0)
					all[i][j][2] = rooms[i][0] + Math.max(all[i - 1][j - 1][2], all[i - 1][j - 1][0]);
			}
		}
		return Integer.toString(findMax(all[rows - 1][blocks]));

	}

	public static int findMax(int[] arr) {
		int answer = arr[0];
		for (int i = 1; i < arr.length; i++) {
			answer = Math.max(answer, arr[i]);
		}
		return answer;
	}

}
