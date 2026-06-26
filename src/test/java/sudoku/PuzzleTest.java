/**
 * 
 */
package sudoku;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import fr.vborg.sudoku.Puzzle;
import fr.vborg.sudoku.RuleException;

/**
 * 
 */
class PuzzleTest {
	private Puzzle puzzle = new Puzzle(9);
	/**
	 * Test method for {@link fr.vborg.sudoku.Puzzle#Puzzle(int)}.
	 */
	@Test
	public void shouldPuzzleSizeGreaterOrEqualToFour()
	{
		assertThrows(IllegalArgumentException.class, 
			() -> new Puzzle(3)
		);		
	}
	/**
	 * Test method for {@link fr.vborg.sudoku.Puzzle#Puzzle(int)}.
	 */
	@Test
	public void shouldPuzzleBePerfectSquare()
	{
		assertThrows(IllegalArgumentException.class, 
			() -> new Puzzle(5)
		);		
	}
	/**
	 * Test method for {@link fr.vborg.sudoku.Puzzle#setValueAt(int,int,int)}.
	 */
	@Test
	public void shouldSetValueAtWithNonNegativeRowIndex()
	{		
		assertThrows(IllegalArgumentException.class, 
			() -> puzzle.setValueAt(-1, 5, 5)
		);		
	}
	
	/**
	 * Test method for {@link fr.vborg.sudoku.Puzzle#setValueAt(int,int,int)}.
	 */
	@Test
	public void shouldSetValueAtWithNonNegativeColumnIndex()
	{		
		assertThrows(IllegalArgumentException.class, 
			() -> puzzle.setValueAt(5, -1, 5)
		);		
	}
	
	/**
	 * Test method for {@link fr.vborg.sudoku.Puzzle#setValueAt(int,int,int)}.
	 */
	@Test
	public void shouldSetValueAtWithRowIndexLessThanGridSize()
	{		
		assertThrows(IllegalArgumentException.class, 
			() -> puzzle.setValueAt(9, 5, 5)
		);		
	}
	
	/**
	 * Test method for {@link fr.vborg.sudoku.Puzzle#setValueAt(int,int,int)}.
	 */
	@Test
	public void shouldSetValueAtWithColumnIndexLessThanGridSize()
	{		
		assertThrows(IllegalArgumentException.class, 
			() -> puzzle.setValueAt(5, 9, 5)
		);		
	}
	
	/**
	 * Test method for {@link fr.vborg.sudoku.Puzzle#setValueAt(int,int,int)}.
	 */
	@Test
	public void shouldSetValueAtWithNegativeValue()
	{		
		assertThrows(IllegalArgumentException.class, 
			() -> puzzle.setValueAt(5, 5, -1)
		);		
	}
	
	/**
	 * Test method for {@link fr.vborg.sudoku.Puzzle#setValueAt(int,int,int)}.
	 */
	@Test
	public void shouldSetValueAtWithValueGreaterThanGridSize()
	{		
		assertThrows(IllegalArgumentException.class, 
			() -> puzzle.setValueAt(5, 5, 10)
		);		
	}
	/**
	 * Test method for {@link fr.vborg.sudoku.Puzzle#setValueAt(int,int,int)}.
	*/
	@Test
	public void shouldSetValueAtWithValueInRow()
	{		
		try{
			puzzle.setValueAt(0, 2, 9);
			puzzle.setValueAt(4, 4, 9);
			puzzle.setValueAt(7, 7, 9);
			
		}catch(RuleException rex)
		{
			fail(rex.getMessage());
		}
		// TOP LEFT
		assertThrows(RuleException.class, 
			() -> puzzle.setValueAt(0, 0, 9)
		);
		// CENTER
		assertThrows(RuleException.class, 
				() -> puzzle.setValueAt(3, 3, 9)
		);
		// BOTTOM RIGHT
		assertThrows(RuleException.class, 
				() -> puzzle.setValueAt(7, 8, 9)
		);
		
	}
	

	/**
	 * Test method for {@link fr.vborg.sudoku.Puzzle#getValueAt(int,int)}.
	 */
	@Test
	public void shouldGetValueAtWithNegativeRowIndex()
	{		
		assertThrows(IllegalArgumentException.class, 
			() -> puzzle.getValueAt(-1, 5)
		);		
	}
	
	/**
	 * Test method for {@link fr.vborg.sudoku.Puzzle#getValueAt(int,int)}.
	 */
	@Test
	public void shouldGetValueAtWithNegativecOLIndex()
	{		
		assertThrows(IllegalArgumentException.class, 
			() -> puzzle.getValueAt(5, -1)
		);		
	}
	
	/**
	 * Test method for {@link fr.vborg.sudoku.Puzzle#getValueAt(int,int)}.
	 */
	@Test
	public void shouldGetValueAtWithRowIndexGreaterOrEqualToGridSize()
	{		
		assertThrows(IllegalArgumentException.class, 
			() -> puzzle.getValueAt(9, 5)
		);		
	}
	
	/**
	 * Test method for {@link fr.vborg.sudoku.Puzzle#getValueAt(int,int)}.
	 */
	@Test
	public void shouldGetValueAtWithColIndexGreaterOrEqualToGridSize()
	{		
		assertThrows(IllegalArgumentException.class, 
			() -> puzzle.getValueAt(5, 9)
		);		
	}
	
}
