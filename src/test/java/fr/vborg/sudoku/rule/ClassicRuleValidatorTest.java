/**
 * 
 */
package fr.vborg.sudoku.rule;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import fr.vborg.sudoku.model.Grid;
import fr.vborg.sudoku.model.RuleException;
import fr.vborg.sudoku.rule.ClassicRuleValidator;
import fr.vborg.sudoku.rule.RuleValidator;

/**
 * Unit tests for class {@link fr.vborg.sudoku.rule.ClassicRuleValidator}.
 */
class ClassicRuleValidatorTest 
{
	private final RuleValidator validator =
	        new ClassicRuleValidator();
	
	@Nested
	class ValidateTests
	{
		@Test
		void should_not_throw_when_grid_has_no_duplicates()
		{
			Grid grid = new Grid(9);

			grid.setValue(5, 0, 0);
			grid.setValue(3, 0, 1);
			grid.setValue(7, 1, 0);

			assertDoesNotThrow(() ->
			validator.validate(grid, 0, 0));

		}
		
		@Test
	    void should_throw_when_row_contains_duplicate()
	    {
			Grid grid = new Grid(9);

		    grid.setValue(5, 0, 0);
		    grid.setValue(3, 0, 1);
		    grid.setValue(7, 1, 0);

		    // Duplicate value 7 in row 1
		    grid.setValue(7, 1, 3);

		    RuleException ruleException = assertThrows(
		            RuleException.class,
		            () -> validator.validate(grid, 1, 0)
		    );

		    // Asserting good exception message
		    assertEquals(
		            "Duplicate value 7 in row 2.",
		            ruleException.getMessage()
		    );		
	    }

	    @Test
	    void should_throw_when_column_contains_duplicate()
	    {
	    	Grid grid = new Grid(9);

	        grid.setValue(5, 0, 0);
	        grid.setValue(3, 1, 0);
	        grid.setValue(7, 2, 0);

	        // Duplicate value 7 in column 0
	        grid.setValue(7, 3, 0);

	        RuleException ruleException = assertThrows(
	                RuleException.class,
	                () -> validator.validate(grid, 3, 0)
	        );

	        assertEquals(
	                "Duplicate value 7 in column 1.",
	                ruleException.getMessage()
	        );
	    }

	    @Test
	    void should_throw_when_box_contains_duplicate()
	    {
	    	 Grid grid = new Grid(9);

	    	 grid.setValue(5, 0, 0);
	    	    grid.setValue(3, 0, 1);
	    	    grid.setValue(7, 1, 0);

	    	    // Duplicate value 7 in the same box,
	    	    // but not in the same row or column
	    	    grid.setValue(7, 2, 1);

	    	    RuleException ruleException = assertThrows(
	    	            RuleException.class,
	    	            () -> validator.validate(grid, 1, 1)
	    	    );

	    	    assertEquals(
	    	            "Duplicate value 7 in box 1.",
	    	            ruleException.getMessage()
	    	    );
	    }

	    @Test
	    void should_throw_when_grid_is_null()
	    {
	    	assertThrows(
	    			NullPointerException.class,
	                () -> validator.validate(null, 0, 0)
	        );
	    }
	}
}
