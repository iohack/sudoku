/**
 * 
 */
package fr.vborg.sudoku.generator;

@SuppressWarnings("serial")
public class GridGenerationException extends Exception
{
    /**
     * Exception raised when generating grids.
     * @param message message 
     */
    public GridGenerationException(final String message)
    {
    	super(message);
    }
}