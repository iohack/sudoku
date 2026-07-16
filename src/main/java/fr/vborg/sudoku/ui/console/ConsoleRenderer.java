/**
 * 
 */
package fr.vborg.sudoku.ui.console;

/**
 * Renders a Sudoku game in the console.
 * <p>
 * The renderer automatically adapts to the grid size and keeps all columns
 * aligned, making it suitable for 4x4, 9x9, 16x16, 25x25 and larger Sudoku
 * variants.
 * </p>
 *
 * @since 1.0
 */
public class ConsoleRenderer {
	/**
     * Displays the Sudoku grid.
     *
     * @param game the game to display
     * @throws NullPointerException if {@code game} is {@code null}
     */
    public void render(final SudokuGame game)
    {
        if (game == null)
        {
            throw new NullPointerException("game");
        }

        final int size = game.getSize();
        final int boxSize = (int) Math.sqrt(size);

        // Width of a cell (9 -> 1, 16 -> 2, 25 -> 2, 100 -> 3...)
        final int cellWidth = String.valueOf(size).length();

        // Width of row/column indexes.
        final int indexWidth = String.valueOf(size - 1).length();

        printColumnHeader(size, boxSize, cellWidth, indexWidth);
        printSeparator(size, boxSize, cellWidth, indexWidth);

        for (int row = 0; row < size; row++)
        {
            System.out.printf("%" + indexWidth + "d |", row);

            for (int column = 0; column < size; column++)
            {
                if (column > 0 && column % boxSize == 0)
                {
                    System.out.print(" |");
                }

                final int value = game.getValue(row, column);

                final String text =
                        value == Grid.EMPTY
                        ? "."
                        : Integer.toString(value);

                System.out.printf(" %" + cellWidth + "s", text);
            }

            System.out.println();

            if ((row + 1) % boxSize == 0 && row + 1 < size)
            {
                printSeparator(size, boxSize, cellWidth, indexWidth);
            }
        }
    }

    private void printColumnHeader(
            final int size,
            final int boxSize,
            final int cellWidth,
            final int indexWidth)
    {
        System.out.printf("%" + indexWidth + "s |", "");

        for (int column = 0; column < size; column++)
        {
            if (column > 0 && column % boxSize == 0)
            {
                System.out.print(" |");
            }

            System.out.printf(" %" + cellWidth + "d", column);
        }

        System.out.println();
    }

    private void printSeparator(
            final int size,
            final int boxSize,
            final int cellWidth,
            final int indexWidth)
    {
        for (int i = 0; i < indexWidth; i++)
        {
            System.out.print("-");
        }

        System.out.print("-+");

        for (int column = 0; column < size; column++)
        {
            if (column > 0 && column % boxSize == 0)
            {
                System.out.print("-+-");
            }

            for (int i = 0; i < cellWidth + 1; i++)
            {
                System.out.print("-");
            }
        }

        System.out.println();
    }
}	
