/**
 * Contains game rules management. 
 */
package fr.vborg.sudoku.rule;

import fr.vborg.sudoku.model.Grid;
import fr.vborg.sudoku.model.RuleException;

/**
 * Validates that the specified values respect the Sudoku rules.
 *
 * @since 1.0
 */
public interface RuleValidator {
	/**
	 * Raise an exception if an implementation class find an
	 * error with this method parameters.
     * @param grid The source grid values
     * @param rowIdx Row index
     * @param colIdx Column index
     * @throws RuleException if a rule is violated
     */
    void validate(final Grid grid, int rowIdx, int colIdx) throws RuleException;
    
    /**
     * Validates the complete grid state.
     *
     * @param grid the grid to validate
     * @throws RuleException if one or more Sudoku rules are violated
     */
    void validate(final Grid grid) throws RuleException;  
}
