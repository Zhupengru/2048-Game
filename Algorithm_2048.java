package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Algorithm_2048 {

	static long[][] afterMove(long[][] board) {
		int i, j;
		i = (int) (Math.random() * 4);
		j = (int) (Math.random() * 4);
		while (board[i][j] > 0) {
			i = (int) (Math.random() * 4);
			j = (int) (Math.random() * 4);
		}
		board[i][j] = Math.random() <= 0.2? 4: 2;
		return board;
	}

	static long[][] newBoard(long[][] board) {
		board = new long[4][4];
		int i, j;
		i = (int) (Math.random() * 4);
		j = (int) (Math.random() * 4);
		board[i][j] = Math.random() <= 0.2? 4: 2;
		i = (int) (Math.random() * 4);
		j = (int) (Math.random() * 4);
		board[i][j] = Math.random() <= 0.2? 4: 2;
		return board;
	}

	static void showBoard(long[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++)
				if (board[i][j] != 0)
					System.out.print(String.format("%1$,4d", board[i][j]) + " ");
				else {
					System.out.print("  __ ");
				}
			System.out.println();
		}
		System.out.println();
	}

	static long[][] left(long[][] board) {
		for (int i = 0; i < board.length; i++) {
			List<Long> nums = new ArrayList<>();
			// put into a list
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != 0)
					nums.add(board[i][j]);
			}
			// push left
			int index = 0, j = 0;
			// make zeros
			board[i] = new long[board.length];
			while (index < nums.size()) {
				if (index < nums.size() - 1 && Math.abs(nums.get(index) - nums.get(index + 1)) < 1) {
					board[i][j] = nums.get(index) * 2;
					j++;
					index += 2;
				} else {
					board[i][j] = nums.get(index);
					j++;
					index++;
				}
			}
		}
		return board;
	}

	static long[][] right(long[][] board) {
		for (int i = 0; i < board.length; i++) {
			List<Long> nums = new ArrayList<>();
			// put into a list
			for (int j = board.length - 1; j >= 0; j--) {
				if (board[i][j] != 0)
					nums.add(board[i][j]);
			}
			// push left
			int index = 0, j = board.length - 1;
			// make zeros
			board[i] = new long[board.length];
			while (index < nums.size()) {
				if (index < nums.size() - 1 && Math.abs(nums.get(index) - nums.get(index + 1)) < 1) {
					board[i][j] = nums.get(index) * 2;
					j--;
					index += 2;
				} else {
					board[i][j] = nums.get(index);
					j--;
					index++;
				}
			}
		}
		return board;
	}

	static long[][] up(long[][] board) {
		for (int i = 0; i < board.length; i++) {
			List<Long> nums = new ArrayList<>();
			
			for (int j = 0; j < board.length; j++) {
				if (board[j][i] != 0)
					nums.add(board[j][i]);
			}
			int index = 0, j = 0;
			for (int col = 0; col < board.length; col++) {
				board[col][i] = 0;
			}

			while (index < nums.size()) {
				if (index < nums.size() - 1 && Math.abs(nums.get(index) - nums.get(index + 1)) < 1) {
					board[j][i] = nums.get(index) * 2;
					j++;
					index += 2;
				} else {
					board[j][i] = nums.get(index);
					j++;
					index++;
				}
			}
		}
		return board;
	}

	static long[][] down(long[][] board) {
		for (int i = 0; i < board.length; i++) {
			List<Long> nums = new ArrayList<>();
			for (int j = board.length - 1; j >= 0; j--) {
				if (board[j][i] != 0)
					nums.add(board[j][i]);
			}
			int index = 0, j = board.length - 1;
			for (int col = 0; col < board.length; col++) {
				board[col][i] = 0;
			}

			while (index < nums.size()) {
				if (index < nums.size() - 1 && Math.abs(nums.get(index) - nums.get(index + 1)) < 1) {
					board[j][i] = nums.get(index) * 2;
					j--;
					index += 2;
				} else {
					board[j][i] = nums.get(index);
					j--;
					index++;
				}
			}
		}
		return board;
	}

	static boolean end(long[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == 0)
					return false;
				if ((i > 0 && board[i][j] == board[i - 1][j]) || (j > 0 && board[i][j] == board[i][j - 1])
						|| (i < board.length - 1 && board[i][j] == board[i + 1][j])
						|| (j < board.length - 1 && board[i][j] == board[i][j + 1]))
					return false;
			}
		}
		return true;
	}
	
	static long totalScore(long[][]board) {
		long sum = 0;
		for (int i = 0 ; i < board.length; i++)
			for (int j = 0; j < board.length; j++)
				sum+=board[i][j];
		return sum;
	}
	
	static boolean win(long[][] board) {
		long winScore = 2048;
		for (int i = 0 ; i < board.length; i++) 
			for (int j = 0 ; j < board.length; j++) 
				if (Math.abs(board[i][j] - winScore) < 1) {
					System.out.print("You win!");
					return true;
				}
		return false;
			
	}
	
	static boolean same(long[][] copy, long[][] board) {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++)
				if (copy[i][j] != board[i][j])
					return false;
		return true;
	}
}
