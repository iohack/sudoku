/**
 * 
 */
package fr.vborg.sudoku.generator;

import fr.vborg.sudoku.model.Grid;

/**
 * Create an empty Grid
 */
public class EmptyGridGenerator implements GridGenerator 
{

	@Override
	public Grid generate(final int size) throws GridGenerationException 
	{
		return new Grid(size);
	}

}
