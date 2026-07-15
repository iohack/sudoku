/**
 * Contains classes to manage Sudoku games.
 */
package fr.vborg.sudoku.game;

import fr.vborg.sudoku.model.Grid;
import fr.vborg.sudoku.model.RuleException;
import fr.vborg.sudoku.rule.ClassicRuleValidator;
import fr.vborg.sudoku.rule.RuleValidator;

/**
 * 
 */
public class SudokuGame {
	 private final Grid grid;
	    private final RuleValidator validator;

	    public SudokuGame(int size) {
	        grid = new Grid(size);
	        validator = new ClassicRuleValidator();
	    }

	    public void setValue(int row, int column, int value)
	            throws RuleException {

	        grid.setValue(value, row, column);
	        validator.validate(grid, row, column);
	    }

	    public void clear(int row, int column) {
	        grid.clear(row, column);
	    }

	    public int getValue(int row, int column) {
	        return grid.getValue(row, column);
	    }

	    public Grid getGrid() {
	        return grid;
	    }
}
