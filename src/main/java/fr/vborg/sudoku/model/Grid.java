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
	 * Represents an empty cell.
	 *
	 * Any value different from {@code EMPTY}
	 * is considered as entered in the grid.
	 */
	public static final int EMPTY = 0;
	
	/**
	 * Minimum supported Sudoku grid size. 
	 */
	public static final int MIN_SIZE = 4;
	
	/**
	 * Grid size (number of rows, columns and boxes).
	 */
	private final int size;
	
	/**
	 * Boxes (sub-grid) size (square root of grid size).
	 */
	private final int boxSize;
	
	/**
	 * Values stored in the grid.
	 */
	private final int[][] values;
	
	/**
	 * Creates an empty Sudoku grid.
	 *
	 * <p>The size must be greater than or equal to {@link #MIN_SIZE}
	 * and must be a perfect square (4, 9, 16...). This constraint
	 * ensures that the grid can be divided into equally sized boxes.</p>
	 *
	 * @param size the grid size
	 * @throws IllegalArgumentException the size is invalid
	 */
	public Grid(final int size)
	{
		if(size < MIN_SIZE)
		{
			throw new IllegalArgumentException("Size out of range (<" + MIN_SIZE + ") : " + size);
		}
		final int computedBoxSize = (int) Math.sqrt(size);
		if (computedBoxSize * computedBoxSize != size)
		{
		    throw new IllegalArgumentException(
		        "Size must be a perfect square: " + size);
		}

		boxSize = computedBoxSize;
		this.size = size;
		
		values = new int[this.size][this.size];		
	}
	
	/**
	 * Returns the grid size.
	 * @return the grid size
	 */
	public int getSize()
	{
		return size;
	}
	
	public int getBoxSize()
	{
		return boxSize;
	}
	
	/**
	 * Returns the value at the specified row and column.
	 * <p>
	 * Indexes can not be negative nor greater than their
	 * maximum value ({@code size-1})
	 * </p>
	 * 
	 * @param rowIndex the row index
	 * @param columnIndex the column index
	 * @return the value stored in the specified cell
	 */
	public int getValue(final int rowIndex, final int columnIndex)
	{
		validateIndex(rowIndex);
		validateIndex(columnIndex);
		return values[rowIndex][columnIndex];
	}
	
	/**
	 * Sets or replaces the value at the specified cell.
	 * <p>
	 * Validation is performed on the cell coordinates and the value range.<br>
	 * Note : you can also use this method to clear a cell by passing 
	 * {@link #EMPTY} as a value.
	 * </p>
	 * 
	 * @param value the value to affect or replace
	 * @param rowIndex the row index
	 * @param columnIndex the column index
	 */
	public void setValue(final int value, final int rowIndex, final int columnIndex)
	{
		validateIndex(rowIndex);
		validateIndex(columnIndex);
		validateValue(value);
		values[rowIndex][columnIndex] = value;
	}
	
	
	/**
	 * Validates that the value is within the allowed range.
	 * @param index the index to validate
	 */
	private void validateIndex(final int index)
	{
		if( index < 0 || index >= size )
		{
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds [0.." + (size - 1) + "]");
		}
	}
	
	
	/**
	 * Validates that the value is within the allowed range.
	 *
	 * @param value the value to validate
	 */
	private void validateValue(final int value)
	{
		if( value < EMPTY || value > size )
		{
			throw new IllegalArgumentException("Value out of range [" + EMPTY + "-" + size + "] : " + value);
		}
	}
		
	/**
	 * Returns whether the specified cell is empty.
	 * @param rowIndex Row index
	 * @param columnIndex Column index
	 * @return {@code true} if empty, {@code false} otherwise
	 */
	public boolean isEmpty(final int rowIndex, final int columnIndex)
	{
		return getValue(rowIndex, columnIndex) == EMPTY;
	}
	
	/**
	 * Clears the specified cell.
	 *
 	 * <p>The cell value becomes {@link #EMPTY}.</p>
 	 *
	 * @param rowIndex Row index
	 * @param columnIndex Column index
	 */
	public void clear(final int rowIndex, final int columnIndex)
	{
		setValue(EMPTY, rowIndex, columnIndex);
	}
	
	/**
	 * Returns a copy of the row at the specified index.
	 *
	 * <p>The returned array is independent from the internal
	 * grid representation. Modifying it does not affect the grid.</p>
	 *
	 * @param rowIndex Row index
	 * @return a copy of the row values
	 * @throws IndexOutOfBoundsException If the index is invalid
	 */
	public int[] getRow(final int rowIndex)
	{
		validateIndex(rowIndex);
		
		return values[rowIndex].clone();
	}
	
	/**
	 * Returns a copy of the box at the specified index.
	 *
	 * <p>Boxes are numbered from left to right and top to bottom.</p>
	 *
	 * <pre>
	 * +---+---+---+
	 * | 0 | 1 | 2 |
	 * +---+---+---+
	 * | 3 | 4 | 5 |
	 * +---+---+---+
	 * | 6 | 7 | 8 |
	 * +---+---+---+
	 * </pre>
	 *
	 * <p>The returned array contains the box values in row-major order.</p>
	 *
	 * @param boxIndex box index
	 * @return a copy of the box values
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	public int[] getBox(final int boxIndex)
	{
	    validateIndex(boxIndex);

	    final int[] box = new int[size];

	    final int startRow = (boxIndex / boxSize) * boxSize;
	    final int startColumn = (boxIndex % boxSize) * boxSize;

	    int index = 0;

	    for (int row = 0; row < boxSize; row++) 
	    {
	        for (int column = 0; column < boxSize; column++) 
	        {
	            box[index++] = values[startRow + row][startColumn + column];
	        }
	    }

	    return box;
	}
	
	/**
	 * Returns a copy of the column at the specified index.
	 *
	 * <p>The returned array is independent from the internal
	 * grid representation. Modifying it does not affect the grid.</p>
	 *
	 * @param columnIndex Column index
	 * @return a copy of the column values
	 * @throws IndexOutOfBoundsException if the index is invalid
	 */
	public int[] getColumn(final int columnIndex)
	{
		validateIndex(columnIndex);
		
		final int[] column = new int[size];
		
		for(int rowIndex = 0; rowIndex < size; rowIndex++)
		{
			column[rowIndex] = values[rowIndex][columnIndex];
		}
		
		return column;
	}
}
