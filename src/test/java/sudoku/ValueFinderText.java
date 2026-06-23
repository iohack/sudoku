/**
 * 
 */
package sudoku;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.vborg.sudoku.utils.ValueFinder;

/**
 * 
 */
class ValueFinderText {

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

}
