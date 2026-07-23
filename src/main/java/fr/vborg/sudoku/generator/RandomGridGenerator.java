package fr.vborg.sudoku.generator;

import java.util.Objects;

import fr.vborg.sudoku.model.Grid;

/**
 * Generates a random Sudoku grid.
 *
 * <p>
 * This implementation does not guarantee that the generated grid is solvable
 * or even valid. It is intended as a basic implementation and as a starting
 * point for more advanced generators.
 * </p>
 */
public class RandomGridGenerator implements GridGenerator
{
    @Override
    public Grid generate(final int size)
    {
        final Grid grid = new Grid(size);

        // TODO generate random values.

        return grid;
    }
}