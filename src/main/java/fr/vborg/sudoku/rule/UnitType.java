/**
 * Contains the classes responsible for validating Sudoku rules.
 *
 * @since 1.0
 */
package fr.vborg.sudoku.rule;

/**
 * Identifies the different logical units of a Sudoku grid.
 *
 * <p>A unit is a group of cells that must contain unique values
 * according to the Sudoku rules.</p>
 *
 * @since 1.0
 */
public enum UnitType 
{
	/**
     * A horizontal row.
     */
	ROW("row"),
	
	/**
     * A vertical column.
     */
	COLUMN("column"),
    
	/**
    * A sub-grid (box).
    */
	BOX("box");

	/**
     * Human-readable label used in exception messages.
     */
	private final String label;

	 /**
     * Creates a unit type with its display label.
     *
     * @param label human-readable label
     */
    private UnitType(String label)
    {
        this.label = label;
    }
    
    /**
     * Returns the human-readable label of this unit type.
     *
     * @return the unit label
     */
    @Override
    public String toString()
    {
        return label;
    }
}
