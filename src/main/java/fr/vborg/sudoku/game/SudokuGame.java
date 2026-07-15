/**
 * Contains classes to manage Sudoku games.
 */
package fr.vborg.sudoku.game;

import java.util.Objects;

import fr.vborg.sudoku.model.Grid;
import fr.vborg.sudoku.model.RuleException;
import fr.vborg.sudoku.rule.ClassicRuleValidator;
import fr.vborg.sudoku.rule.RuleValidator;

/**
 * Represents a Sudoku game.
 * <p>
 * This class is the entry point for manipulating a Sudoku grid. It delegates
 * the storage of values to a {@link Grid} and validates every modification
 * using a {@link RuleValidator}.
 * </p>
 *
 * @since 1.0
 */
public class SudokuGame {
	/**
	 * Internal Sudoku grid.
	 */
	private final Grid grid;
	
	/**
	 * Validator used to enforce Sudoku rules.
	 */
	private final RuleValidator validator;

	/**
	 * Creates a new Sudoku game.
	 *
	 * @param size the size of the grid (4, 9, 16, ...)
	 * @throws IllegalArgumentException if the size is invalid
	 */
	public SudokuGame(final int size) 
	{
		this(size, new ClassicRuleValidator());
	}

	/**
	 * Creates a new Sudoku game with a custom rule validator.
	 *
	 * @param size the size of the grid (4, 9, 16, ...)
	 * @param validator the rule validator used to check moves
	 * @throws IllegalArgumentException if the size is invalid
	 * @throws NullPointerException if validator is null
	 */
	public SudokuGame(final int size, final RuleValidator validator)
	{
	    this.grid = new Grid(size);
	    this.validator = Objects.requireNonNull(validator, "validator");
	}
	
	/**
	 * Sets a value in the grid.
	 * <p>
	 * The value is first written to the grid, then the Sudoku rules are
	 * validated. If the validation fails, the previous value is restored and
	 * the exception is propagated.
	 * </p>
	 *
	 * @param row the row index
	 * @param column the column index
	 * @param value the value to place in the cell, or {@link Grid#EMPTY} to clear it
	 * @throws RuleException if the move violates Sudoku rules
	 * @throws IllegalArgumentException if the value is invalid
	 * @throws IndexOutOfBoundsException if the coordinates are invalid
	 */
	public void setValue(final int row, final int column, final int value)
			throws RuleException 
	{

		final int oldValue = grid.getValue(row, column);

		grid.setValue(value, row, column);

		try 
		{
			validator.validate(grid, row, column);
		}
		catch (final RuleException ruleException) 
		{
			grid.setValue(oldValue, row, column);
			throw ruleException;
		}
	}

	/**
	 * Clears the specified cell.
	 *
	 * @param row the row index
	 * @param column the column index
	 *
	 * @throws RuleException if clearing the cell violates Sudoku rules
	 * @throws IndexOutOfBoundsException if the coordinates are invalid
	 */
	public void clear(final int row, final int column) 
			throws RuleException 
	{
		setValue(row, column, Grid.EMPTY);
	}

	/**
	 * Returns the value stored in a cell.
	 *
	 * @param row the row index
	 * @param column the column index
	 * @return the cell value, or {@link Grid#EMPTY} if the cell is empty
	 * @throws IndexOutOfBoundsException if the coordinates are invalid
	 */
	public int getValue(final int row, final int column) 
	{
		return grid.getValue(row, column);
	}
	
	/**
	 * Returns the size of the Sudoku grid.
	 *
	 * @return the number of rows and columns
	 */
	public int getSize() 
	{
	    return grid.getSize();
	}
}
