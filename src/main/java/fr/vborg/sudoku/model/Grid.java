/**
 * Contains the classes that represent the Sudoku domain model.
 *
 * <p>These classes do not perform any user input or console/GUI output.
 * They are only responsible for representing and manipulating the
 * application's data.</p>
 *
 * @since 1.0
 */
package fr.vborg.sudoku.model;

/**
 * Represents the state of a Sudoku grid.
 *
 * <p>The grid stores only the values entered in each cell.
 * It does not validate Sudoku rules or perform any user interaction.</p>
 *
 * @since 1.0
 */
public class Grid {
	/**
	 * Zero means an empty grid value.
	 */
	public static final int EMPTY = 0;
	
	/**
	 * This is the technical minimum grid size for the Sudoku game. 
	 */
	public static final int MIN_SIZE = 4;
	
	/**
	 * Size of grid.
	 * It means number of rows, columns and boxes. 
	 */
	private final int size;
	
	/**
	 * The user values generated or entered by the user.
	 */
	private final int[][] values;
	
	/**
	 * Create the internal grid model based on its size.
	 * <p>
	 * 	Because of Sudoku technical rule, its grid size must
	 * greater or equal to 4, otherwise an IllegalArgumentException
	 * is raised.
	 * @param pSize Grid size.
	 */
	public Grid(int pSize)
	{
		if(pSize < MIN_SIZE)
		{
			throw new IllegalArgumentException("Size out of range (<" + MIN_SIZE + ") : " + pSize);
		}
		this.size = pSize;
		if( Math.sqrt(size) % 1 != 0 )
		{
			throw new IllegalArgumentException("Size must be a perfect square : " + size);
		}
		this.values = new int[size][size];
	}
	
	/**
	 * Size grid (number of rows, columns and boxes)
	 * @return Size
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 * Value at specified row and column indexes.
	 * <p>
	 * Indexes can not be negative nor greater than their
	 * maximum value ({@code size-1})
	 * </p>
	 * 
	 * @param rowIdx Row index
	 * @param colIdx Column index
	 * @return Value
	 */
	public int getValue(int rowIdx, int colIdx)
	{
		validateIndex(rowIdx);
		validateIndex(colIdx);
		return this.values[rowIdx][colIdx];
	}
	
	/**
	 * Affect or replace a value at specified row and column indexes.
	 * <p>
	 * Validation is performed on the cell coordinates and the value range.<br>
	 * Note : you can also use this method to clear a cell by passing 
	 * {@link #EMPTY} as a value.
	 * </p>
	 * 
	 * @param value Value to affect or replace
	 * @param rowIdx Row index
	 * @param colIdx Column index
	 */
	public void setValue(int value, int rowIdx, int colIdx)
	{
		validateIndex(rowIdx);
		validateIndex(colIdx);
		validateValue(value);
		this.values[rowIdx][colIdx] = value;
	}
	
	
	/**
	 * Asserts that the specified index is within the valid grid bounds.
	 * @param index Index to validate
	 */
	private void validateIndex(int index)
	{
		if( index < EMPTY || index >= size )
		{
			throw new IllegalArgumentException("Index out of range [" + EMPTY + "-" + (size - 1) + "] : " + index);
		}
	}
	
	
	/**
	 * Asserts the value parameter is between the range [EMPTY-size].
	 * @param value Value to validate
	 */
	private void validateValue(int value)
	{
		if( value < EMPTY || value > size )
		{
			throw new IllegalArgumentException("Value out of range [" + EMPTY + "-" + size + "] : " + value);
		}
	}
		
	/**
	 * Test if a grid value is empty. See {@link Grid#EMPTY}.
	 * @param rowIdx Row index
	 * @param colIdx Column index
	 * @return {@code true} if empty, {@code false} otherwise
	 */
	public boolean isEmpty(int rowIdx, int colIdx)
	{
		return getValue(rowIdx, colIdx) == EMPTY;
	}
	
	/**
	 * Empty a grid value.
	 * @param rowIdx Row index
	 * @param colIdx Column index
	 */
	public void clear(int rowIdx, int colIdx)
	{
		validateIndex(rowIdx);
		validateIndex(colIdx);
		this.values[rowIdx][colIdx] = EMPTY;
	}	
}
