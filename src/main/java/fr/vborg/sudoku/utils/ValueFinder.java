package fr.vborg.sudoku.utils;

import java.util.Objects;

/**
 * Utility class based on grid values process
 */
public final class ValueFinder {
	private ValueFinder()
	{
		// Utilities class
	}
	

	/**
     * Finds the coordinates of the first cell containing the specified value.
     * <p>
     * The search is performed row by row, from left to right and
     * from top to bottom.
     *
     * @param grid the grid to search
     * @param value the cell value to search
     * @return an array containing the row and column indices of the first
        cell containing the specified value,
        or {@code null} if no such cell is found
     */
	public static int[] findFirstValue(int[][] grid, int value) {
		Objects.requireNonNull(grid, "grid");
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				if (grid[row][col] == value) {
					return new int[] {row, col};
				}
			}
		}
		return null;
	}
	
	
	/**
	 * Counts how many times a given value appears in the grid.
	 *
	 * @param grid the 2D array to search (must not be null)
	 * @param value the value to count
	 * @return the number of occurrences of the value in the grid
	 * @throws NullPointerException if grid is null
	 */
	public static int countValue(int[][] grid, int value)
	{
		Objects.requireNonNull(grid, "grid");
		int count = 0;
		for (int[] row : grid) {
			for (int cell : row) {
				if (cell == value) {
					count++;
				}
			}
		}
		return count;
	}
}
