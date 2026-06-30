/**
 * 
 */
package fr.vborg.sudoku.utils;

import java.util.Objects;

/**
 * 
 */
public final class GridUtils {
	
	private GridUtils() {
		 throw new AssertionError("Utility class");
	}
	
	/**
	 * Test if a grid is empty.
	 * <p>
	 * A grid must not be null and must contain at least one row.
	 * @param grid The grid to test.
	 * @return {@code true} if the grid is empty else {@code false} 
	 */
	public static boolean isEmpty(final int[][] grid)
	{
		Objects.requireNonNull(grid, "grid must not be null");
		return grid.length > 0;		
	}
	
	/**
	 * Test if a grid is rectangular.<br>
	 * It means a grid must have r row(s) and c column(s) with :<br>
	 * <ul>
	 * <li>r > 0</li>
	 * <li>c > 0</li>
	 * <li>Each row has c column(s)</li>
	 * </ul>
	 * <p>
	 * A grid must not be empty otherwise a {@link fr.vborg.sudoku.GridException} is raised.
	 * <p>
	 *  
	 * @param grid Grid to test
	 * @throws GridException If the grid is empty
	 */
	public static void validateRectangular(final int [][] grid) throws GridException
	{
		if(isEmpty(grid)) 
		{
			throw new GridException("empty grid, unable to get a row length");
		}
		
		Objects.requireNonNull(grid[0], "row 0 must not be null");
		final int firstRowLength = grid[0].length;
		if( firstRowLength == 0) 
		{
			throw new GridException("first row is empty (length = 0)");
		}
		for(int i=1; i<grid.length; i++) {
			Objects.requireNonNull(grid[i], "null row, unable get to its length at index " + i);
			if(grid[i].length == 0 )
			{
				throw new GridException("empty row at index " + i);
			}
			if(grid[i].length != firstRowLength) {
				throw new GridException("Row " + i + " length (" + grid[i].length +
					    ") differs from first row length (" + firstRowLength + ")");
			}
		}
	}
	
	/**
	 * Tests whether a grid is square.
	 *
	 * <p>A square grid is a rectangular grid whose row count
	 * equals its column count.</p>
	 *
	 * @param grid the grid to test
	 * @throws GridException if the grid is empty
	 */public static void validateSquare(final int [][] grid) throws GridException
	{
		validateRectangular(grid);
		
		if (grid.length != grid[0].length)
		{
			throw new GridException("Grid is not square: rows=" + grid.length +
				    ", columns=" + grid[0].length);
		}
	}
}
