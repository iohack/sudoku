/**
 * 
 */
package fr.vborg.sudoku.ui.console;

import java.util.Objects;
import java.util.Scanner;

import fr.vborg.sudoku.game.Move;

/**
 * Reads user input from the console.
 *
 * @since 1.0
 */
public final class ConsoleInput
{
    private static final String QUIT_COMMAND =
            "quit";

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
        String input =
                scanner.nextLine().trim();

        return Integer.parseInt(input);
    }


    /**
     * Reads a move.
     *
     * @return the entered move
     */
    public Move readMove()
    {
        String input =
                scanner.nextLine().trim();

        if (QUIT_COMMAND.equalsIgnoreCase(input))
        {
            return null;
        }

        String[] tokens =
                input.split("\\s+");

        if (tokens.length != 3)
        {
            throw new IllegalArgumentException(
                    "Invalid move");
        }

        return new Move(
                Integer.parseInt(tokens[0]) - 1 ,
                Integer.parseInt(tokens[1]) - 1 ,
                Integer.parseInt(tokens[2]));
    }
}
