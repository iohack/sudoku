package fr.vborg.sudoku.generator;

import fr.vborg.sudoku.model.Grid;

/**
 * Generates complete Sudoku solutions.
 */
public interface CompleteGridGenerator extends GridGenerator {
	/**
     * Generates a completed Sudoku grid.
     *
     * @param size grid size
     * @return a complete valid Sudoku solution
     */
    @Override
    Grid generate(int size) throws GridGenerationException;
}
