/**
 * 
 */
package sudoku;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.vborg.sudoku.utils.ValueFinder;

/**
 * 
 */
class ValueFinderTest {

	@Test
    void shouldFindValueInFirstCell() {
        int[][] grid = {
            {5, 1, 2},
            {3, 4, 5}
        };

        int[] result = ValueFinder.findFirstValue(grid, 5);

        assertNotNull(result);
        assertArrayEquals(new int[] {0, 0}, result);
    }

    @Test
    void shouldFindValueInMiddleOfGrid() {
        int[][] grid = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int[] result = ValueFinder.findFirstValue(grid, 5);

        assertNotNull(result);
        assertArrayEquals(new int[] {1, 1}, result);
    }

    @Test
    void shouldFindValueInLastCell() {
        int[][] grid = {
            {1, 2},
            {3, 4}
        };

        int[] result = ValueFinder.findFirstValue(grid, 4);

        assertNotNull(result);
        assertArrayEquals(new int[] {1, 1}, result);
    }

    @Test
    void shouldReturnFirstOccurrenceWhenValueAppearsMultipleTimes() {
        int[][] grid = {
            {1, 2, 3},
            {4, 2, 6},
            {7, 8, 2}
        };

        int[] result = ValueFinder.findFirstValue(grid, 2);

        assertNotNull(result);
        assertArrayEquals(new int[] {0, 1}, result);
    }

    @Test
    void shouldReturnNullWhenValueIsNotFound() {
        int[][] grid = {
            {1, 2, 3},
            {4, 5, 6}
        };

        assertNull(ValueFinder.findFirstValue(grid, 99));
    }

    @Test
    void shouldFindZeroValue() {
        int[][] grid = {
            {1, 2, 3},
            {4, 0, 6}
        };

        int[] result = ValueFinder.findFirstValue(grid, 0);

        assertNotNull(result);
        assertArrayEquals(new int[] {1, 1}, result);
    }

    @Test
    void shouldFindNegativeValue() {
        int[][] grid = {
            {1, -2, 3},
            {4, 5, 6}
        };

        int[] result = ValueFinder.findFirstValue(grid, -2);

        assertNotNull(result);
        assertArrayEquals(new int[] {0, 1}, result);
    }

    @Test
    void shouldWorkWithSingleCellGrid() {
        int[][] grid = {
            {42}
        };

        int[] result = ValueFinder.findFirstValue(grid, 42);

        assertNotNull(result);
        assertArrayEquals(new int[] {0, 0}, result);
    }

    @Test
    void shouldReturnNullForEmptyGrid() {
        int[][] grid = new int[0][0];

        assertNull(ValueFinder.findFirstValue(grid, 1));
    }
    
    @Test
    void shouldThrowExceptionForNullGrid() {
        assertThrows(
            NullPointerException.class,
            () -> ValueFinder.findFirstValue(null, 1)
        );
    }
    
    @Test
    void shouldCountMultipleOccurrences() {
        int[][] grid = {
            {1, 2, 3},
            {2, 4, 2},
            {5, 2, 6}
        };

        assertEquals(4, ValueFinder.countValue(grid, 2));
    }

    @Test
    void shouldCountSingleOccurrence() {
        int[][] grid = {
            {1, 2, 3},
            {4, 5, 6}
        };

        assertEquals(1, ValueFinder.countValue(grid, 5));
    }

    @Test
    void shouldReturnZeroWhenValueNotFound() {
        int[][] grid = {
            {1, 2, 3},
            {4, 5, 6}
        };

        assertEquals(0, ValueFinder.countValue(grid, 9));
    }

    @Test
    void shouldReturnZeroForEmptyGrid() {
        int[][] grid = {};

        assertEquals(0, ValueFinder.countValue(grid, 1));
    }

    @Test
    void shouldThrowExceptionWhenGridIsNull() {
        assertThrows(
            NullPointerException.class,
            () -> ValueFinder.countValue(null, 1)
        );
    }

    @Test
    void shouldHandleJaggedGrid() {
        int[][] grid = {
            {1, 2},
            {2},
            {2, 3, 2}
        };

        assertEquals(4, ValueFinder.countValue(grid, 2));
    }

}
