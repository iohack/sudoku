/**
 * 
 */
package fr.vborg.sudoku.rule;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import fr.vborg.sudoku.model.Grid;
import fr.vborg.sudoku.model.RuleException;

/**
 * 
 */
public class ClassicRuleValidatorBenchTest 
{
	private final RuleValidator validator =
	        new ClassicRuleValidator();
	
	@Nested
	class BenchTest
	{
		private final int ITERATION_COUNT = 10000000;
		private final int TEST_GRID_SIZE = 16;
		
		/**
		 * 
		 */
		private BenchTest()
		{
			System.out.println("Starting bench with : ");
			System.out.println("Grid size : " + TEST_GRID_SIZE);
			System.out.println("Iteration count : " + ITERATION_COUNT);			
		}
		
		@Test
		public void measure_value_validation_time()		
		{
			try{
				Grid grid = new Grid(TEST_GRID_SIZE);

				final long valueStartTime = System.currentTimeMillis();
				for(int count = 0; count < ITERATION_COUNT; count++)
				{
					for(int columnIndex = 0; columnIndex < TEST_GRID_SIZE; columnIndex++)
					{
						validator.validate(grid, 0, columnIndex);
					}
				}
				final long valueEndTime = System.currentTimeMillis();
			
				System.out.println("Value validation time  : " + (valueEndTime - valueStartTime) + "ms");
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
				
				final long newStartTime = System.currentTimeMillis();
				for(int count = 0; count < ITERATION_COUNT; count++)
				{
					validator.validateColumn(grid, 0);
					
				}
				final long newEndTime = System.currentTimeMillis();
				System.out.println("Column validation time : " + (newEndTime - newStartTime) + "ms");				
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
				
				final long newStartTime = System.currentTimeMillis();
				for(int count = 0; count < ITERATION_COUNT; count++)
				{
					validator.validateRow(grid, 0);
					
				}
				final long newEndTime = System.currentTimeMillis();
				System.out.println("Row validation time   : " + (newEndTime - newStartTime) + "ms");				
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
				
				final long newStartTime = System.currentTimeMillis();
				for(int count = 0; count < ITERATION_COUNT; count++)
				{
					validator.validateBox(grid, 0);
					
				}
				final long newEndTime = System.currentTimeMillis();
				System.out.println("Box validation time   : " + (newEndTime - newStartTime) + "ms");				
			}
			catch(final RuleException ruleException)
			{
				fail(ruleException.getMessage());
			}
		}
	}	
}
