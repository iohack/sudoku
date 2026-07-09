package fr.vborg.sudoku.rule;
import java.util.Objects;

import fr.vborg.sudoku.model.Grid;
import fr.vborg.sudoku.model.RuleException;

/**
 * Validates the rules of a classic Sudoku.
 * <p>Ensures that each row, column and box contains unique values.
 * Empty cells ({@link Grid#EMPTY}) are ignored during validation.</p>
 *
 * @since 1.0
 * <ul>
 *   <li>Unique values in every row.</li>
 *   <li>Unique values in every column.</li>
 *   <li>Unique values in every box.</li>
 * </ul>
 */
public class ClassicRuleValidator implements RuleValidator 
{
	
	@Override
	public void validate(final Grid grid, final int rowIndex, final int columnIndex) throws RuleException 
	{
		Objects.requireNonNull(grid, "grid");
		
		validateRow(grid, rowIndex);
		validateColumn(grid, columnIndex);
		validateBox(grid, rowIndex, columnIndex);
	}

	private void validateDuplicates(final int[] values, final UnitType unitType, final int unitIndex) throws RuleException 
	{
		Objects.requireNonNull(values,"values");
		Objects.requireNonNull(unitType,"unitType");
		final int size = values.length;
		// No duplicate check is required for arrays containing fewer than two values.		
		if( size < 2 )
		{
			return;
		}
		
		// Creating a seen values array plus one element for empty value
		// index 1..size used for Sudoku values
		// Index 0 is reserved for Grid.EMPTY
		final boolean[] seen = new boolean[size + 1];
		
		for(final int value : values)
		{
			
			// Ignoring empty values
			if( value == Grid.EMPTY )
			{
				continue;
			}
			
			if (value < 1 || value > size) 
			{
	            throw new RuleException(
	                String.format(
	                    "Value out of bounds %d in %s %d.",
	                    value,
	                    unitType,
	                    unitIndex + 1));
	        }
			
			if( seen[value] )
			{
				throw new RuleException(
					String.format("Duplicate value %d in %s %d.",
						value,
						unitType,
						unitIndex + 1
					)
				);
			}
			
			seen[value] = true;
		}		
	}
	
	private void validateRow(final Grid grid, 
							 final int rowIndex) 
			throws RuleException {
	    validateDuplicates(grid.getRow(rowIndex), UnitType.ROW, rowIndex);
	}

	private void validateColumn(final Grid grid, 
								final int columnIndex) 
			throws RuleException {
	    validateDuplicates(grid.getColumn(columnIndex), UnitType.COLUMN, columnIndex);
	}
	
	private void validateBox(final Grid grid,
							 final int rowIndex,
							 final int columnIndex)
			throws RuleException
	{
		// Validate row and column indexes before computing the box index.
		grid.getValue(rowIndex, columnIndex);
		final int boxSize = grid.getBoxSize();

		final int boxIndex =
				(rowIndex / boxSize) * boxSize
				+ (columnIndex / boxSize);

		validateDuplicates(grid.getBox(boxIndex),
				UnitType.BOX,
				boxIndex);
	}
	
	
}
