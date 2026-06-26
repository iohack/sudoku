/**
 * 
 */
package fr.vborg.sudoku;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 */
public class Puzzle {
	/**
	 * Logging.
	 */
	private static final Logger LOGGER = LogManager.getLogger(Puzzle.class);
	
	/**
	 * Grid. 
	 */
	private int[][] grid;
	
	public Puzzle(int size)	
	{
		// Size must be greater or equal to 4
		if( size < 4 )
		{
			throw new IllegalArgumentException("size < 4");
		}
		LOGGER.debug("size validated (>=4) : " + size);
		// The size must correspond to a perfect square
		int sqrt = (int) Math.sqrt(size);
	    if( sqrt * sqrt != size )
	    {
	    	throw new IllegalArgumentException("grid isn't a perfect square");
	    }
	    LOGGER.debug("size validated (correspond to a perfect square : " + size);
	    grid = new int[size][size];
	}
	
	public void setValueAt(int rowIndex, int colIndex, int value) throws RuleException
	{
		if(value < 0) {
			throw new IllegalArgumentException("negative value : " + value);
		}
		if(value > grid.length ) {
			throw new IllegalArgumentException("value > grid size : " + value);
		}
		
		if(rowIndex < 0) {
			throw new IllegalArgumentException("negative row index : " + rowIndex);
		}
		if(colIndex < 0) {
			throw new IllegalArgumentException("negative row index : " + rowIndex);
		}
		if(rowIndex >= grid.length) {
			throw new IllegalArgumentException("row index > (grid size - 1) : " + rowIndex);
		}
		if(colIndex >= grid.length) {
			throw new IllegalArgumentException("column index > (grid size - 1) : " + colIndex);
		}
		LOGGER.debug("row and column indexes validated : " + rowIndex + "," + colIndex);
		if(grid[rowIndex][colIndex] ==0) {
			LOGGER.debug("empty value, trying setting it");
		}else {
			LOGGER.debug("not empty value : {}, trying replacing it",grid[rowIndex][colIndex]);
		}
		// Checking value is not in row
		int[] row = grid[rowIndex];
		for(int tmpValue : row)
		{
			if( value == tmpValue )
			{
				throw new RuleException("value already in row");
			}
		}
		LOGGER.debug("rule valid : value not in row");
		
		// Checking value is not in column
		for(int[] tmpRow : grid)
		{
			if(value == tmpRow[colIndex])
			{
				throw new RuleException("value already in column");
			}				
		}
		LOGGER.debug("rule valid : value not in column");
		
		// Checking value is not in box
		int boxSize = (int)Math.sqrt(value);
		int rowStart = ((rowIndex + 1) / boxSize) * boxSize;
	    int colStart = ((colIndex + 1) / boxSize) * boxSize;
	    for (int i = rowStart; i < rowStart + boxSize; i++) 
	    {
	        for (int j = colStart; j < colStart + boxSize; j++) 
	        {
	            if (value == grid[i][j]) 
	            {
	            	throw new RuleException("value already in box");
	            }
	        }
	    }
	    LOGGER.debug("rule valid : value not in box");
	    grid[rowIndex][colIndex] = value;
	    LOGGER.debug("rules valid, value assigned");
	    
	}
	
	public int getValueAt(int rowIndex, int colIndex)
	{
		if(rowIndex < 0) {
			throw new IllegalArgumentException("negative row index : " + rowIndex);
		}
		if(colIndex < 0) {
			throw new IllegalArgumentException("negative row index : " + rowIndex);
		}
		if(rowIndex >= grid.length) {
			throw new IllegalArgumentException("row index > (grid size - 1) : " + rowIndex);
		}
		if(colIndex >= grid.length) {
			throw new IllegalArgumentException("column index > (grid size - 1) : " + colIndex);
		}
		return grid[rowIndex][colIndex];
	}
	
	public void print() {
		for(int[] row : grid)
		{
			for(int col : row) {
				System.out.print(col);				
			}
			System.out.println();
		}
	}
}
