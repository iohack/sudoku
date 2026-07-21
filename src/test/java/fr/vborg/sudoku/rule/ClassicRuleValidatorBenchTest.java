/**
 * 
 */
package fr.vborg.sudoku.rule;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import fr.vborg.sudoku.model.Grid;
import fr.vborg.sudoku.model.RuleException;

/**
 * 
 */
@Tag("benchmark")
public class ClassicRuleValidatorBenchTest 
{
	private final RuleValidator validator =
	        new ClassicRuleValidator();
	
	@Nested
	class BenchTest
	{
		private final int ITERATION_COUNT = 10_000_000;
		private final int TEST_GRID_SIZE = 16;
		
		/**
		 * 
		 */
		private BenchTest()
		{
			System.out.println("Starting bench with : ");
			//System.out.println("Grid size : " + TEST_GRID_SIZE);
			//System.out.println("Iteration count : " + ITERATION_COUNT);			
		}
		
		@Test
		public void measure_value_validation_time()		
		{
			try{
				Grid grid = new Grid(TEST_GRID_SIZE);

				final long valueStartTime = System.nanoTime();
				for(int count = 0; count < ITERATION_COUNT; count++)
				{
					for(int columnIndex = 0; columnIndex < TEST_GRID_SIZE; columnIndex++)
					{
						validator.validate(grid, 0, columnIndex);
					}
				}
				System.out.printf("Value validation time : %.3f ms%n", (System.nanoTime() - valueStartTime) / 1_000_000.0);
			}
			catch(final RuleException ruleException)
			{
				fail(ruleException.getMessage());
			}
		}
		
		@Test
		public void measure_column_validation_time()
		{
			try{
				Grid grid = new Grid(TEST_GRID_SIZE);
				// Warm-up
				for (int i = 0; i < 10_000; i++) {
				    validator.validate(grid, 0, 0);
				}
				final long columnStartTime = System.nanoTime();
				for(int count = 0; count < ITERATION_COUNT; count++)
				{
					validator.validateColumn(grid, 0);
					
				}				
				System.out.printf("Column validation time : %.3f ms%n", (System.nanoTime() - columnStartTime) / 1_000_000.0);				
			}
			catch(final RuleException ruleException)
			{
				fail(ruleException.getMessage());
			}
		}

		@Test
		public void measure_row_validation_time()
		{
			try{
				Grid grid = new Grid(TEST_GRID_SIZE);
				// Warm-up
				for (int i = 0; i < 10_000; i++) {
				    validator.validateRow(grid, 0);
				}
				final long rowStartTime = System.nanoTime();
				for(int count = 0; count < ITERATION_COUNT; count++)
				{
					validator.validateRow(grid, 0);
					
				}
				System.out.printf("Row validation time : %.3f ms%n", (System.nanoTime() - rowStartTime) / 1_000_000.0);
			}
			catch(final RuleException ruleException)
			{
				fail(ruleException.getMessage());
			}
		}
		@Test		
		void measure_box_validation_time()
		{
			try{
				Grid grid = new Grid(TEST_GRID_SIZE);
				// Warm-up
				for (int i = 0; i < 10_000; i++) {
				    validator.validateBox(grid, 0);
				}
				final long boxStartTime = System.nanoTime();
				for(int count = 0; count < ITERATION_COUNT; count++)
				{
					validator.validateBox(grid, 0);
					
				}
				System.out.printf("Box validation time : %.3f ms%n", (System.nanoTime() - boxStartTime) / 1_000_000.0);				
			}
			catch(final RuleException ruleException)
			{
				fail(ruleException.getMessage());
			}
		}
	}	
}
