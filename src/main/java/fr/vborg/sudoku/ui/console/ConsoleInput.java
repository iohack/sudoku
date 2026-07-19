/**
 * 
 */
package fr.vborg.sudoku.ui.console;

import java.util.Objects;
import java.util.Scanner;

import fr.vborg.sudoku.game.Move;
import fr.vborg.sudoku.localization.Messages;

/**
 * Reads user input from the console.
 *
 * @since 1.0
 */
public final class ConsoleInput
{
	/**
	 * Command used to quit the current game.
	 */
	private static final String QUIT_COMMAND =
            "quit";
    
    /**
     * Token string separator.
     */
    private static final String TOKEN_SEPARATOR =
            "\\s+";
    
    /**
     * Token count needed to do a move.
     */
    private static final int MOVE_TOKEN_COUNT =
            3;

    /**
     * Input scanner.
     */
    private final Scanner scanner;


    public ConsoleInput(final Scanner scanner)
    {
        this.scanner =
                Objects.requireNonNull(scanner);
    }


    /**
     * Reads the requested grid size.
     *
     * @return the grid size
     */
    public int readGridSize()
    {
        String input = readLine();

        return Integer.parseInt(input);
    }

    private String readLine()
    {
    	return scanner.nextLine().trim();
    }

    /**
     * Reads a move.
     *
     * @return the entered move
     */
    public Move readMove()
    {
        String input = readLine();

        if (QUIT_COMMAND.equalsIgnoreCase(input))
        {
            return null;
        }

        String[] tokens =
                input.split(TOKEN_SEPARATOR);

        if (tokens.length != MOVE_TOKEN_COUNT)
        {
            throw new IllegalArgumentException(
            		Messages.get("console.invalid"));
        }

        try
        {
        	return new Move(
        			Integer.parseInt(tokens[0]) - 1 ,
        			Integer.parseInt(tokens[1]) - 1 ,
        			Integer.parseInt(tokens[2]));
        }
        catch (NumberFormatException exception)
        {
        	throw new IllegalArgumentException(
        			Messages.get("console.invalid"),
        			exception);
        }
    }
}
