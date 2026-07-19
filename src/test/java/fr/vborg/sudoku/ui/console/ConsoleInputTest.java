package fr.vborg.sudoku.ui.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Scanner;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import fr.vborg.sudoku.game.Move;

/**
 * Unit tests for {@link ConsoleInput}.
 *
 * @since 1.0
 */
class ConsoleInputTest
{
    @Nested
    class Constructor
    {
        @Test
        void shouldCreateConsoleInput()
        {
            new ConsoleInput(
                    new Scanner(""));
        }

        @Test
        void shouldRejectNullScanner()
        {
            assertThrows(
                    NullPointerException.class,
                    () -> new ConsoleInput(null));
        }
    }

    @Nested
    class ReadGridSize
    {
        @Test
        void shouldReadGridSize()
        {
            ConsoleInput input =
                    new ConsoleInput(
                            new Scanner("9"));

            assertEquals(
                    9,
                    input.readGridSize());
        }

        @Test
        void shouldTrimGridSize()
        {
            ConsoleInput input =
                    new ConsoleInput(
                            new Scanner("   16   "));

            assertEquals(
                    16,
                    input.readGridSize());
        }

        @Test
        void shouldRejectInvalidGridSize()
        {
            ConsoleInput input =
                    new ConsoleInput(
                            new Scanner("abc"));

            assertThrows(
                    NumberFormatException.class,
                    input::readGridSize);
        }
    }

    @Nested
    class ReadMove
    {
    	@Test
    	void shouldReturnLocalizedMessageForInvalidMove()
    	{
    	    ConsoleInput input =
    	            new ConsoleInput(
    	                    new Scanner("1 2"));

    	    IllegalArgumentException exception =
    	            assertThrows(
    	                    IllegalArgumentException.class,
    	                    input::readMove);

    	    assertEquals(
    	            "Coup invalide",
    	            exception.getMessage());
    	}
    	
    	@Test
    	void shouldReadMoveWithTabSeparator()
    	{
    	    ConsoleInput input =
    	            new ConsoleInput(
    	                    new Scanner("1\t2\t3"));

    	    Move move =
    	            input.readMove();

    	    assertEquals(
    	            0,
    	            move.getRow());

    	    assertEquals(
    	            1,
    	            move.getColumn());

    	    assertEquals(
    	            3,
    	            move.getValue());
    	}
    	
    	@Test
        void shouldReadMove()
        {
            ConsoleInput input =
                    new ConsoleInput(
                            new Scanner("1 2 3"));

            Move move =
                    input.readMove();

            assertEquals(
                    0,
                    move.getRow());

            assertEquals(
                    1,
                    move.getColumn());

            assertEquals(
                    3,
                    move.getValue());
        }

        @Test
        void shouldReadMoveWithEmptyValue()
        {
            ConsoleInput input =
                    new ConsoleInput(
                            new Scanner("4 5 0"));

            Move move =
                    input.readMove();

            assertEquals(
                    3,
                    move.getRow());

            assertEquals(
                    4,
                    move.getColumn());

            assertEquals(
                    0,
                    move.getValue());
        }

        @Test
        void shouldTrimMove()
        {
            ConsoleInput input =
                    new ConsoleInput(
                            new Scanner("   1   2   3   "));

            Move move =
                    input.readMove();

            assertEquals(
                    0,
                    move.getRow());

            assertEquals(
                    1,
                    move.getColumn());

            assertEquals(
                    3,
                    move.getValue());
        }

        @Test
        void shouldReturnNullWhenQuitCommandIsEntered()
        {
            ConsoleInput input =
                    new ConsoleInput(
                            new Scanner("quit"));

            assertNull(
                    input.readMove());
        }

        @Test
        void shouldAcceptQuitCommandIgnoringCase()
        {
            ConsoleInput input =
                    new ConsoleInput(
                            new Scanner("QuIt"));

            assertNull(
                    input.readMove());
        }

        @Test
        void shouldRejectMoveWithMissingToken()
        {
            ConsoleInput input =
                    new ConsoleInput(
                            new Scanner("1 2"));

            assertThrows(
                    IllegalArgumentException.class,
                    input::readMove);
        }

        @Test
        void shouldRejectMoveWithTooManyTokens()
        {
            ConsoleInput input =
                    new ConsoleInput(
                            new Scanner("1 2 3 4"));

            assertThrows(
                    IllegalArgumentException.class,
                    input::readMove);
        }

        @Test
        void shouldRejectMoveWithInvalidNumber()
        {
            ConsoleInput input =
                    new ConsoleInput(
                            new Scanner("1 a 3"));

            assertThrows(
                    IllegalArgumentException.class,
                    input::readMove);
        }
    }
}