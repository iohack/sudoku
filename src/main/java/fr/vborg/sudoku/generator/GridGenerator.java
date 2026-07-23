/**
 * 
 */
package fr.vborg.sudoku.generator;

import fr.vborg.sudoku.model.Grid;

/**
 * Generates valid Sudoku grids.
 *
 * <p>Implementations are responsible for creating a complete
 * Sudoku grid that satisfies the game rules.</p>
 *
 * @since 1.0
 */
public interface GridGenerator {
	/**
     * Generates a complete Sudoku grid of the specified size.
     *
     * <p>The returned grid contains no {@link Grid#EMPTY} cells
     * and satisfies all Sudoku rules.</p>
     *
     * @param size the grid size (4, 9, 16, ...)
     * @return a generated Sudoku grid
     * @throws IllegalArgumentException if the size is invalid
   	 * @throws GridGenerationException if generating problem
	 */
	public Grid generate(final int size) throws GridGenerationException;
}
