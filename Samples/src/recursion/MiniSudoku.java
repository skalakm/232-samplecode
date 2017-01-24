package recursion;

/**
 * Mini (3x3) Sudoku solver that uses recursive backtracking. In Mini Sudoku
 * there is one 3x3 box. Each row and each column must contain the values 1,2,3.
 * <code>
 * For example:
 *   3 2 1 
 *   2 1 3 
 *   1 3 2 
 * </code>
 * 
 * Example adapted from: https://brilliant.org/wiki/recursive-backtracking/.
 * 
 * @author Grant Braught
 * @author Dickinson College
 * @version Jan 29, 2016
 */
public class MiniSudoku {

	/**
	 * Try to solve a few boards.
	 * 
	 * @param args none
	 */
	public static void main(String[] args) {
		// Has solution that requires backtracking.
		int[][] board = { 	{ 0, 0, 0 }, 
							{ 0, 0, 3 }, 
							{ 1, 0, 0 } };
		miniSudokuSolver(board);

		// Has no solution, and detects it by backtracking the whole way back.
		int[][] board2 = { 	{ 0, 1, 0 }, 
							{ 0, 0, 3 }, 
							{ 1, 0, 0 } };
		miniSudokuSolver(board2);
	}
	
	/**
	 * Try to solve the given mini sudoku board. If there is a solution the
	 * board is printed. If not, the message "No Solution!" is printed.
	 * 
	 * @param board
	 *            the mini sudoku board with the initial clues.
	 */
	public static void miniSudokuSolver(int[][] board) {
		if (miniSudokuSolver(board, 0, 0)) {
			// Found a solution.
			// Print the solved board.
			for (int r = 0; r < board.length; r++) {
				for (int c = 0; c < board[r].length; c++) {
					System.out.print(board[r][c] + " ");
				}
				System.out.println();
			}
		} else {
			System.out.println("No Solution!");
		}
	}

	/*
	 * Recursive Problem Transformation:
	 * 
	 * Note: Cells will be processed in row-major order (i.e. across the row and
	 * then down to the next row).
	 * 
	 * This method tries to fill in a solution for the board assuming that all
	 * cells before the indicated row and column already have values. It returns
	 * true if such a solution exists, and false if it does not.
	 */
	private static boolean miniSudokuSolver(int[][] board, int r, int c) {
		if (isSolved(board)) {
			// The board is already solved so we are done!
			return true;
		}
		else if (!isValid(board)) {
			// The board is not valid as is, so it can't possibly be solved from r,c!
			return false;
		} else if (board[r][c] != 0) {
			/*
			 * Cell r,c already has a value (e.g. one of the hints), so skip
			 * over it and go onto the next cell. Note: Row stays the same
			 * unless c is 2. Column takes values 0,1,2,0,1,2...
			 */
			return miniSudokuSolver(board, r + (c / 2), (c + 1) % 3);
		} else {
			/*
			 * Use recursive backtracking to solve the board. Try a 1 in the
			 * cell and see if we can find a solution. If that doesn't work, try
			 * a 2 and if that doesn't work try a 3.
			 */
			for (int i = 1; i <= 3; i++) {
				// Try value i in the cell at r,c
				board[r][c] = i;

				/*
				 * Ask: can we solve the rest of the board with i at r,c?
				 */
				if (miniSudokuSolver(board, r + (c / 2), (c + 1) % 3)) {
					// Yes, yes we can!
					return true;
				}

				/*
				 * We were unable to solve the rest of the board with i at 
				 * r,c. So, remove i from r,c and try the next value...
				 */
				board[r][c] = 0;
			}

			/*
			 * We were unable to solve the rest of the board with 1, 2 or 3 at
			 * r,c. So there must not be any solution at all.
			 * 
			 * Backtrack!
			 */
			return false;
		}
	}

	/*
	 * Some helper methods below here...
	 */

	/*
	 * Check if the board is solved. A board is solved if all of its rows and
	 * all of its columns are complete. This method does that by checking for
	 * incomplete rows or columns (i.e. does not contain the values 1, 2 & 3 in
	 * some order). If an incomplete row or column is found the board is not
	 * solved. If no incomplete rows or columns are found then the board is
	 * solved.
	 */
	private static boolean isSolved(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			if (!completeRow(board, i) || !completeCol(board, i)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Check if an indicated row of the board is complete. Complete rows contain
	 * the values 1, 2 and 3 in some order.
	 */
	private static boolean completeRow(int[][] board, int r) {
		boolean[] used = new boolean[board[r].length + 1];

		/*
		 * NOTE: Marking used[0] = true ensures that if there is a cell with no
		 * value in it (i.e. it holds 0) that the row will be marked as not
		 * complete.
		 */
		used[0] = true;
		for (int c = 0; c < board[r].length; c++) {
			int val = board[r][c];
			if (used[val] == true) {
				return false;
			} else {
				used[val] = true;
			}
		}
		return true;
	}

	/*
	 * Check if an indicated column of the board is complete. Complete columns
	 * contain the values 1, 2 and 3 in some order.
	 */
	private static boolean completeCol(int[][] board, int c) {
		boolean[] used = new boolean[board.length + 1];

		/*
		 * NOTE: Marking used[0] = true ensures that if there is a cell with no
		 * value in it (i.e. it holds 0) that the col will be marked as not
		 * complete.
		 */
		used[0] = true;
		for (int r = 0; r < board.length; r++) {
			int val = board[r][c];
			if (used[val] == true) {
				return false;
			} else {
				used[val] = true;
			}
		}
		return true;
	}

	/*
	 * Check if the board is valid. A board is valid if all of its rows and all
	 * of its columns are valid. If an invalid row or column is found the board
	 * is not valid. If no invalid rows or columns are found then the board is
	 * solved.
	 */
	private static boolean isValid(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			if (!validRow(board, i) || !validCol(board, i)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Check if the row is valid. A row is valid if it can be a part of a valid
	 * solution (i.e. it does not contain two of any number.)
	 */
	private static boolean validRow(int[][] board, int r) {
		boolean[] used = new boolean[board[r].length + 1];

		for (int c = 0; c < board[r].length; c++) {
			int val = board[r][c];
			if (used[val] == true) {
				return false;
			} else if (val != 0) {
				used[val] = true;
			}
		}
		return true;
	}

	/*
	 * Check if the col is valid. A col is valid if it can be a part of a valid
	 * solution (i.e. it does not contain two of any number.)
	 */
	private static boolean validCol(int[][] board, int c) {
		boolean[] used = new boolean[board.length + 1];

		for (int r = 0; r < board.length; r++) {
			int val = board[r][c];
			if (used[val] == true) {
				return false;
			} else if (val != 0) {
				used[val] = true;
			}
		}
		return true;
	}

}
