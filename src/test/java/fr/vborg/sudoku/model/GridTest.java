package fr.vborg.sudoku.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import fr.vborg.sudoku.model.Grid;

class GridTest {

	// =========================================================
    // Constructor
    // =========================================================
    @Nested
    class ConstructorTests {

        @Test
        void should_create_grid_with_min_size() {
            Grid grid = new Grid(Grid.MIN_SIZE);
            assertEquals(Grid.MIN_SIZE, grid.getSize());
        }

        @Test
        void should_create_grid_with_valid_size_9() {
            Grid grid = new Grid(9);
            assertEquals(9, grid.getSize());
        }

        @Test
        void should_throw_exception_when_size_too_small() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Grid(3));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -5})
        void should_throw_exception_for_non_positive_sizes(int size) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Grid(size));
        }

        @ParameterizedTest
        @ValueSource(ints = {5, 6, 7, 8, 10, 12, 15})
        void should_throw_exception_for_non_perfect_square(int size) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Grid(size));
        }
        
        @Test
        void should_create_grid_with_valid_size_16()
        {
            Grid grid = new Grid(16);

            assertEquals(16, grid.getSize());
        }           
    }

    // =========================================================
    // getValue
    // =========================================================
    @Nested
    class GetValueTests {

        @Test
        void new_grid_should_be_empty() {
            Grid grid = new Grid(9);

            for (int i = 0; i < grid.getSize(); i++) {
                for (int j = 0;  j < grid.getSize(); j++) {
                    assertEquals(Grid.EMPTY, grid.getValue(i, j));
                }
            }
        }

        @Test
        void should_throw_exception_for_negative_index() {
            Grid grid = new Grid(9);

            assertThrows(IndexOutOfBoundsException.class,
                    () -> grid.getValue(-1, 0));

            assertThrows(IndexOutOfBoundsException.class,
                    () -> grid.getValue(0, -1));
        }

        @Test
        void should_throw_exception_for_out_of_bounds_index() {
            Grid grid = new Grid(9);

            assertThrows(IndexOutOfBoundsException.class,
                    () -> grid.getValue(9, 0));

            assertThrows(IndexOutOfBoundsException.class,
                    () -> grid.getValue(0, 9));
        }
    }

    // =========================================================
    // setValue
    // =========================================================
    @Nested
    class SetValueTests {

        @Test
        void should_set_value_correctly()
        {
            Grid grid = new Grid(9);

            grid.setValue(5, 1, 1);

            assertEquals(5, grid.getValue(1, 1));
        }

        @Test
        void should_accept_empty_value()
        {
            Grid grid = new Grid(9);

            grid.setValue(Grid.EMPTY, 2, 2);

            assertTrue(grid.isEmpty(2, 2));
        }

        @Test
        void should_throw_exception_for_negative_value() 
        {
            Grid grid = new Grid(9);

            assertThrows(IllegalArgumentException.class,
                    () -> grid.setValue(-1, 0, 0));
        }

        @Test
        void should_throw_exception_for_value_too_large() 
        {
            Grid grid = new Grid(9);

            assertThrows(IllegalArgumentException.class,
                    () -> grid.setValue(10, 0, 0));
        }

        @Test
        void should_throw_exception_for_invalid_index() 
        {
            Grid grid = new Grid(9);

            assertThrows(IndexOutOfBoundsException.class,
                    () -> grid.setValue(5, -1, 0));

            assertThrows(IndexOutOfBoundsException.class,
                    () -> grid.setValue(5, 0, 9));
        }
    }

    // =========================================================
    // clear
    // =========================================================
    @Nested
    class ClearTests 
    {

        @Test
        void should_clear_value() 
        {
            Grid grid = new Grid(9);

            grid.setValue(7, 3, 3);
            grid.clear(3, 3);

            assertEquals(Grid.EMPTY, grid.getValue(3, 3));
        }

        @Test
        void should_allow_clear_on_empty_cell() 
        {
            Grid grid = new Grid(9);

            assertDoesNotThrow(() -> grid.clear(0, 0));
        }

        @Test
        void should_throw_exception_for_invalid_index() 
        {
            Grid grid = new Grid(9);

            assertThrows(IndexOutOfBoundsException.class,
                    () -> grid.clear(-1, 0));

            assertThrows(IndexOutOfBoundsException.class,
                    () -> grid.clear(0, 9));
        }
    }

    // =========================================================
    // isEmpty
    // =========================================================
    @Nested
    class IsEmptyTests 
    {

        @Test
        void should_return_true_on_new_grid() 
        {
            Grid grid = new Grid(9);

            assertTrue(grid.isEmpty(0, 0));
        }

        @Test
        void should_return_false_after_setting_value() 
        {
            Grid grid = new Grid(9);

            grid.setValue(3, 0, 0);

            assertFalse(grid.isEmpty(0, 0));
        }

        @Test
        void should_return_true_after_clear()
        {
            Grid grid = new Grid(9);

            grid.setValue(3, 0, 0);
            grid.clear(0, 0);

            assertTrue(grid.isEmpty(0, 0));
        }
    }

    // =========================================================
    // integration / consistency
    // =========================================================
    @Nested
    class ConsistencyTests 
    {

        @Test
        void should_only_modify_target_cell_when_setting_value() 
        {
            Grid grid = new Grid(9);

            grid.setValue(5, 4, 4);

            for (int i = 0; i < grid.getSize(); i++) 
            {
                for (int j = 0; j < grid.getSize(); j++) 
                {
                    if (i == 4 && j == 4) continue;
                    assertEquals(Grid.EMPTY, grid.getValue(i, j));
                }
            }
        }

        @Test
        void should_overwrite_existing_value() 
        {
            Grid grid = new Grid(9);

            grid.setValue(1, 2, 2);
            grid.setValue(8, 2, 2);

            assertEquals(8, grid.getValue(2, 2));
        }
    }
    
    

    
    @Nested
    class GetColumnTests
    {
    	@Test
    	void should_return_column_values() 
    	{
    		Grid grid = new Grid(4);

    		grid.setValue(1, 0, 2);
    		grid.setValue(2, 1, 2);
    		grid.setValue(3, 2, 2);
    		grid.setValue(4, 3, 2);

    		assertArrayEquals(
    				new int[] {1, 2, 3, 4},
    				grid.getColumn(2));
    	}

    	@Test
    	void returned_column_should_be_a_copy() 
    	{
    		Grid grid = new Grid(4);

    		int[] column = grid.getColumn(0);

    		column[0] = 42;

    		assertEquals(Grid.EMPTY, grid.getValue(0, 0));
    	}

    	@Test
    	void should_throw_exception_for_invalid_column() 
    	{
    		Grid grid = new Grid(4);

    		assertThrows(IndexOutOfBoundsException.class,
    				() -> grid.getColumn(-1));

    		assertThrows(IndexOutOfBoundsException.class,
    				() -> grid.getColumn(4));
    	}
    }
    
    @Nested
    class GetBoxTests{
    	@Test
    	void should_return_first_box() 
    	{
    		Grid grid = new Grid(4);

    		grid.setValue(1,0,0);
    		grid.setValue(2,0,1);
    		grid.setValue(3,1,0);
    		grid.setValue(4,1,1);

    		assertArrayEquals(
    				new int[] {1, 2, 3, 4},
    				grid.getBox(0));
    	}

    	@Test
    	void should_return_last_box() 
    	{
    		Grid grid = new Grid(4);

    		grid.setValue(1,2,2);
    		grid.setValue(2,2,3);
    		grid.setValue(3,3,2);
    		grid.setValue(4,3,3);

    		assertArrayEquals(
    				new int[] {1, 2, 3, 4},
    				grid.getBox(3));
    	}

    	@Test
    	void returned_box_should_be_a_copy() 
    	{
    		Grid grid = new Grid(4);

    		int[] box = grid.getBox(0);

    		box[0] = 99;

    		assertEquals(Grid.EMPTY, grid.getValue(0, 0));
    	}

    	@Test
    	void should_throw_exception_for_invalid_box() 
    	{
    		Grid grid = new Grid(4);

    		assertThrows(IndexOutOfBoundsException.class,
    				() -> grid.getBox(-1));

    		assertThrows(IndexOutOfBoundsException.class,
    				() -> grid.getBox(4));
    	}
    	@Test
        void should_return_center_box_in_standard_grid()
        {
            Grid grid = new Grid(9);

            grid.setValue(5, 4, 4);

            assertEquals(5, grid.getBox(4)[4]);
        }
    }
    
    @Nested
    class GetRowTests
    {
    	@Test
        void returned_row_modification_should_not_modify_grid()
        {
            Grid grid = new Grid(4);

            grid.setValue(4, 0, 0);

            int[] row = grid.getRow(0);
            row[0] = 99;

            assertEquals(4, grid.getValue(0, 0));
        }
    	@Test
        void returned_row_should_be_a_copy() 
        {
            Grid grid = new Grid(4);

            int[] row = grid.getRow(0);
            row[0] = 99;

            assertEquals(Grid.EMPTY, grid.getValue(0, 0));
        }
        
        @Test
        void should_throw_exception_for_invalid_row() 
        {
            Grid grid = new Grid(4);

            assertThrows(IndexOutOfBoundsException.class,
                    () -> grid.getRow(-1));

            assertThrows(IndexOutOfBoundsException.class,
                    () -> grid.getRow(4));
        }
        @Test
        void should_return_row_values() 
        {
            Grid grid = new Grid(4);

            grid.setValue(1, 2, 0);
            grid.setValue(2, 2, 1);
            grid.setValue(3, 2, 2);
            grid.setValue(4, 2, 3);

            assertArrayEquals(
                    new int[] {1, 2, 3, 4},
                    grid.getRow(2));
        }
    }    
}
