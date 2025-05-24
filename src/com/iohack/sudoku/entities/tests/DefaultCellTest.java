/**
 * 
 */
package com.iohack.sudoku.entities.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.iohack.sudoku.entities.DefaultCellImpl;

/**
 * Tests unitaires sur la classe DefaultCell.
 */
class DefaultCellTest {
	
	// Constantes.
	public static final int CELL_COUNT_TYPICALLY = 8;
	public static final int CELL_COUNT_NEGATIVEONE = -1;
	public static final int CELL_COUNT_ZERO = 0;
	public static final int CELL_COUNT_50 = 50;
			
	/**
	 * Test method for {@link com.iohack.sudoku.entities.DefaultCellImpl#DefaultCellImpl(int)}.
	 */
	@Test
	void testDefaultCellImpl() {
		new DefaultCellImpl(CELL_COUNT_TYPICALLY);	
	}

	/**
	 * Test method for {@link com.iohack.sudoku.entities.DefaultCellImpl#isEmpty()}.
	 */
	@Test
	void testIsEmpty() {
		assertTrue(new DefaultCellImpl(CELL_COUNT_TYPICALLY).isEmpty());
	}

	/**
	 * Test method for {@link com.iohack.sudoku.entities.DefaultCellImpl#addCandidateValue(int)}.
	 */
	@Test
	void testAddCandidateValueWithGood8parameter() {
		new DefaultCellImpl(CELL_COUNT_TYPICALLY).addCandidateValue(7);
	}

	/**
	 * Test method for {@link com.iohack.sudoku.entities.DefaultCellImpl#removeCandidateValue(int)}.
	 */
	@Test
	void testRemoveCandidateValue() {
		new DefaultCellImpl(CELL_COUNT_TYPICALLY).removeCandidateValue(5);
	}

	/**
	 * Test method for {@link com.iohack.sudoku.entities.DefaultCellImpl#contains(int)}.
	 */
	@Test
	void testContains() {
		assertTrue(new DefaultCellImpl(CELL_COUNT_TYPICALLY).contains(5));
	}

	/**
	 * Test method for {@link com.iohack.sudoku.entities.DefaultCellImpl#getCellCount()}.
	 */
	@Test
	void testGetCellCount() {
		assertEquals(8, new DefaultCellImpl(CELL_COUNT_TYPICALLY).getCellCount());
	}

	/**
	 * Test method for {@link com.iohack.sudoku.entities.DefaultCellImpl#cellsAsList()}.
	 */
	@Test
	void testCellsAsList() {
		assertTrue(new DefaultCellImpl(CELL_COUNT_TYPICALLY).cellsAsList().size() == 8);
	}	
}
