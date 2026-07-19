package fr.vborg.sudoku.game;

import java.util.Objects;

import fr.vborg.sudoku.localization.Messages;

/**
 * Represents a Sudoku move.
 *
 * @since 1.0
 */
public final class Move
{
	
	 /**
     * Message key used for string representation.
     */
    private static final String FORMAT_KEY =
            "move.format";
    
    private final int row;
    private final int column;
    private final int value;


    /**
     * Creates a Sudoku move.
     *
     * <p>
     * Row and column use zero-based indices, consistent with the internal
     * representation of {@code Grid}. User interfaces are responsible for
     * converting to and from one-based indices.
     * </p>
     *
     * @param row the zero-based row index
     * @param column the zero-based column index
     * @param value the value to insert
     */
    public Move(
            final int row,
            final int column,
            final int value)
    {
        this.row = row;
        this.column = column;
        this.value = value;
    }


    /**
	 * Returns the row index.
     * @return the row index
     */
    public int getRow()
    {
        return row;
    }


    /**
     * Returns the column index.
     * @return the column index
     */
    public int getColumn()
    {
        return column;
    }

    
    /**
     * Returns the cell value.
     * @return the cell value
     */
    public int getValue()
    {
        return value;
    }


    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        if (!(obj instanceof Move))
        {
            return false;
        }

        Move other = (Move) obj;

        return row == other.row
                && column == other.column
                && value == other.value;
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(
                row,
                column,
                value);
    }

    /**
     * 
     * @return
     */
    public String format()
    {
        return Messages.get(
        		FORMAT_KEY,
                row + 1,
                column + 1,
                value);
    }
    
    @Override
    public String toString()
    {
    	return format();
    }
}