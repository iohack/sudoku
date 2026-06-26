/**
 * 
 */
package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.vborg.sudoku.generator.ManualEntry;

/**
 * 
 */
class ManualEntryTest {

	/**
	 * Test method for {@link fr.vborg.sudoku.generator.ManualEntry#entryNew()}.
	 */
	@Test
	void testEntryNew() {
		try{
			ManualEntry.entryNew();
		}catch(final Exception exc) {
			fail(exc.getMessage());
		}
	}

}
