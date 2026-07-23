package fr.vborg.sudoku.generator;

import fr.vborg.sudoku.model.Grid;

/**
 * Generates incomplete Sudoku grid.
 */
public interface InompleteGridGenerator extends GridGenerator {
	/**
     * Generates a incomplete Sudoku grid.
     *
     * @param size grid size
     * @return a complete valid Sudoku solution
     */
    @Override
    Grid generate(int size) throws GridGenerationException;
}
