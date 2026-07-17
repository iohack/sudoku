/**
 * 
 */
package sudoku;

import fr.vborg.sudoku.game.SudokuGame;
import fr.vborg.sudoku.model.RuleException;
import fr.vborg.sudoku.ui.console.ConsoleRenderer;

/**
 * Demonstrates the console rendering of a Sudoku grid.
 * <p>
 * This class is intended for manual testing during development and is not
 * part of the public API.
 * </p>
 *
 * @since 1.0
 */
public final class ConsoleRendererDemo {
	
	/**
	 * Size of the demonstration grid.
	 */
	private static final int GRID_SIZE = 16;
	
	private ConsoleRendererDemo()
	{
	    // Utility class.
	}
	
	/**
	 * Runs the console rendering demonstration.
	 *
	 * @param args command-line arguments (ignored)
	 * @throws RuleException if the demonstration grid is invalid
	 */
	public static void main(final String[] args) throws RuleException
	{
		final SudokuGame game = new SudokuGame(GRID_SIZE);

		game.setValue(0, 0, 5);
		game.setValue(0, 1, 3);
		game.setValue(0, 12, 15);

		game.setValue(1, 0, 6);
		game.setValue(1, 3, 1);
		game.setValue(1, 4, 9);
		game.setValue(1, 5, 5);

		game.setValue(2, 1, 9);
		game.setValue(2, 2, 8);
		game.setValue(2, 7, 6);

		final ConsoleRenderer renderer = new ConsoleRenderer();
		renderer.render(game);
	}
}
