/**
 * Contains game rules management. 
 */
package fr.vborg.sudoku.rule;

import fr.vborg.sudoku.model.Grid;
import fr.vborg.sudoku.model.RuleException;

// TODO : séparer la validation en ligne, colonne, box.
// Amélioration des performances, éviter de calculer la
// validation pour une valeur plusieurs fois.

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
    
    /**
     * Validates a specified row.
     *
     * @param rowIndex row index
     * @throws RuleException if one or more Sudoku rules are violated
     */
    void validateRow(final Grid grid, final int rowIndex) throws RuleException;
    
    /**
     * Validates a specified column.
     *
     * @param columnIndex row index
     * @throws RuleException if one or more Sudoku rules are violated
     */
    void validateColumn(final Grid grid, final int columnIndex) throws RuleException;
    
    /**
     * Validates a specified box.
     *
     * @param boxIndex box index
     * @throws RuleException if one or more Sudoku rules are violated
     */
    void validateBox(final Grid grid, final int boxIndex) throws RuleException;
    
}
