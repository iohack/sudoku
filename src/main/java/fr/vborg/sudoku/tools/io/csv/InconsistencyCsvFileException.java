/**
 * 
 */
package fr.vborg.sudoku.tools.io.csv;

/**
 * Raised if inconsistency detected in a CSV file.
 */
@SuppressWarnings("serial")
public class InconsistencyCsvFileException extends Exception {
	/**
	 * Create the exception with explanation message.
	 * @param message explanation message
	 */
	public InconsistencyCsvFileException(final String message)
	{
		super(message);
	}
}
