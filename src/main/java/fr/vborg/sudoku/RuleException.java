/**
 * 
 */
package fr.vborg.sudoku;

/**
 * Sudoku rule violation.
 * <p>
 * A value can not be twice in row, column and box.
 * </p>
 */
public class RuleException extends Exception {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -463216279962725401L;

	/** 
	 * @param message Message describing then reason.
	 */
	public RuleException(final String message)
	{
		super(message);
	}
}
