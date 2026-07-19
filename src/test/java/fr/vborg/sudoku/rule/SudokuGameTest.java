package fr.vborg.sudoku.rule;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import fr.vborg.sudoku.game.SudokuGame;
import fr.vborg.sudoku.model.Grid;
import fr.vborg.sudoku.model.RuleException;

class SudokuGameTest
{
    @Nested
    class ConstructorTests
    {
        @Test
        void should_create_game_with_valid_size()
        {
            final SudokuGame game = new SudokuGame(9);

            assertEquals(9, game.getSize());
        }

        @Test
        void should_throw_when_size_is_invalid()
        {
            assertThrows(
                IllegalArgumentException.class,
                () -> new SudokuGame(5)
            );
        }
        @Test
        void should_throw_when_validator_is_null()
        {
            assertThrows(
                    NullPointerException.class,
                    () -> new SudokuGame(9, null)
            );
        }
    }


    @Nested
    class SetValueTests
    {
    	@Test
    	void should_replace_existing_value()
    	{
    	    final SudokuGame game = new SudokuGame(9);

    	    assertDoesNotThrow(() -> {
    	        game.setValue(0, 0, 5);
    	        game.setValue(0, 0, 8);
    	    });

    	    assertEquals(8, game.getValue(0, 0));
    	}
    	@Test
        void should_throw_when_row_is_negative()
        {
            final SudokuGame game = new SudokuGame(9);

            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> game.setValue(-1, 0, 5)
            );
        }
    	@Test
    	void should_throw_when_row_is_out_of_bounds()
    	{
    	    final SudokuGame game = new SudokuGame(9);

    	    assertThrows(
    	            IndexOutOfBoundsException.class,
    	            () -> game.setValue(9, 0, 5)
    	    );
    	}
    	@Test
    	void should_throw_when_value_is_negative()
    	{
    	    final SudokuGame game = new SudokuGame(9);

    	    assertThrows(
    	            IllegalArgumentException.class,
    	            () -> game.setValue(0, 0, -1)
    	    );
    	}
    	
    	@Test
        void should_accept_valid_move()
        {
            final SudokuGame game = new SudokuGame(9);

            assertDoesNotThrow(() ->
                game.setValue(0, 0, 5)
            );

            assertEquals(
                    5,
                    game.getValue(0, 0)
            );
        }


        @Test
        void should_reject_duplicate_in_row() throws RuleException
        {
            final SudokuGame game = new SudokuGame(9);

            game.setValue(0, 0, 5);

            final RuleException exception = assertThrows(
                RuleException.class,
                () -> game.setValue(0, 1, 5)
            );

            assertEquals(
                "Duplicate value 5 in row 1.",
                exception.getMessage()
            );
        }


        @Test
        void should_reject_duplicate_in_column() throws RuleException
        {
            final SudokuGame game = new SudokuGame(9);

            game.setValue(0, 0, 5);

            final RuleException exception = assertThrows(
                RuleException.class,
                () -> game.setValue(1, 0, 5)
            );

            assertEquals(
                "Duplicate value 5 in column 1.",
                exception.getMessage()
            );
        }
        
        @Test
        void should_reject_duplicate_in_box() throws RuleException
        {
            final SudokuGame game = new SudokuGame(9);

            game.setValue(0, 0, 5);

            final RuleException exception = assertThrows(
                RuleException.class,
                () -> game.setValue(1, 1, 5)
            );

            assertEquals(
                "Duplicate value 5 in box 1.",
                exception.getMessage()
            );
        }
        
        @Test
        void should_restore_previous_value_when_move_is_invalid() throws RuleException
        {
            final SudokuGame game = new SudokuGame(9);

            game.setValue(0, 0, 5);
            game.setValue(0, 1, 3);

            assertThrows(
                    RuleException.class,
                    () -> game.setValue(0, 1, 5)
            );

            assertEquals(
                    3,
                    game.getValue(0, 1)
            );
        }
        @Test
        void should_throw_when_value_is_invalid()
        {
            final SudokuGame game = new SudokuGame(9);

            assertThrows(
                    IllegalArgumentException.class,
                    () -> game.setValue(0, 0, 10)
            );
        }
        @Test
        void should_throw_when_column_is_out_of_bounds()
        {
        	final SudokuGame game = new SudokuGame(9);

            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> game.setValue(0, 9, 5)
            );
        }
    }
    
    @Nested
    class ClearTests
    {
    	@Test
    	void should_throw_when_clearing_invalid_cell()
    	{
    	    final SudokuGame game = new SudokuGame(9);

    	    assertThrows(
    	            IndexOutOfBoundsException.class,
    	            () -> game.clear(9, 0)
    	    );
    	}
    	@Test
        void should_clear_cell() throws RuleException
        {
            final SudokuGame game = new SudokuGame(9);

            game.setValue(0, 0, 5);
            game.clear(0, 0);

            assertEquals(Grid.EMPTY, game.getValue(0, 0));
        }
        @Test
        void should_accept_clearing_empty_cell()
        {
            final SudokuGame game = new SudokuGame(9);

            assertDoesNotThrow(() -> game.clear(0, 0));

            assertEquals(Grid.EMPTY, game.getValue(0, 0));
        } 
        
        
    }
    @Nested
    class GetValueTests
    {
        @Test
        void should_return_empty_for_new_game()
        {
            final SudokuGame game = new SudokuGame(9);

            assertEquals(Grid.EMPTY, game.getValue(0, 0));
        }
        @Test
        void should_throw_when_getting_invalid_row()
        {
        	final SudokuGame game = new SudokuGame(9);

            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> game.getValue(-1, 0)
            );
        }
        
        @Test
        void should_throw_when_getting_invalid_column()
        {
        	final SudokuGame game = new SudokuGame(9);

            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> game.getValue(0, 9)
            );
        }
    }
    
    
    
    
    @Nested
    class GetSizeTests
    {
        @Test
        void should_return_grid_size()
        {
        	final SudokuGame game = new SudokuGame(9);

            assertEquals(9, game.getSize());
        }
    }
}