/**
 * Contains the classes that represent the Sudoku domain model.
 *
 * <p>These classes do not perform any user input or console/GUI output.
 * They are only responsible for representing and manipulating the
 * application's data.</p>
 *
 * @since 1.0
 */
package fr.vborg.sudoku.model;

/**
 * Thrown when a Sudoku game rule is violated.
 * 
 * <p>
 * A value can be present only once in :
 * <ul>
 * 	<li>A row.</li>
 * 	<li>A column.</li>
 * 	<li>A box.</li>
 * </ul>
 */
public class RuleException extends Exception {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -6314569628430105988L;

	/**
	 * Explain the rule violation reason.
	 * @param message Reason
	 */
	public RuleException(String message) {
		super(message);
	}
}
