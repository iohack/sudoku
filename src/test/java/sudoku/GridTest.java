package sudoku;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import fr.vborg.sudoku.model.Grid;
import fr.vborg.sudoku.model.RuleException;

class GridTest {

	// =========================================================
    // Constructor
    // =========================================================
    @Nested
    class ConstructorTests {

        @Test
        void should_create_grid_with_min_size() {
            Grid grid = new Grid(Grid.MIN_SIZE);
            assertEquals(Grid.MIN_SIZE, grid.getSize());
        }

        @Test
        void should_create_grid_with_valid_size_9() {
            Grid grid = new Grid(9);
            assertEquals(9, grid.getSize());
        }

        @Test
        void should_throw_exception_when_size_too_small() {
            assertThrows(IllegalArgumentException.class,
                    () -> new Grid(3));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, -5})
        void should_throw_exception_for_non_positive_sizes(int size) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Grid(size));
        }

        @ParameterizedTest
        @ValueSource(ints = {5, 6, 7, 8, 10, 12, 15})
        void should_throw_exception_for_non_perfect_square(int size) {
            assertThrows(IllegalArgumentException.class,
                    () -> new Grid(size));
        }
    }

    // =========================================================
    // getValue
    // =========================================================
    @Nested
    class GetValueTests {

        @Test
        void new_grid_should_be_empty() {
            Grid grid = new Grid(9);

            for (int i = 0; i < grid.getSize(); i++) {
                for (int j = 0; j < grid.getSize(); j++) {
                    assertEquals(Grid.EMPTY, grid.getValue(i, j));
                }
            }
        }

        @Test
        void should_throw_exception_for_negative_index() {
            Grid grid = new Grid(9);

            assertThrows(IllegalArgumentException.class,
                    () -> grid.getValue(-1, 0));

            assertThrows(IllegalArgumentException.class,
                    () -> grid.getValue(0, -1));
        }

        @Test
        void should_throw_exception_for_out_of_bounds_index() {
            Grid grid = new Grid(9);

            assertThrows(IllegalArgumentException.class,
                    () -> grid.getValue(9, 0));

            assertThrows(IllegalArgumentException.class,
                    () -> grid.getValue(0, 9));
        }
    }

    // =========================================================
    // setValue
    // =========================================================
    @Nested
    class SetValueTests {

        @Test
        void should_set_value_correctly() throws RuleException {
            Grid grid = new Grid(9);

            grid.setValue(5, 1, 1);

            assertEquals(5, grid.getValue(1, 1));
        }

        @Test
        void should_accept_empty_value() throws RuleException {
            Grid grid = new Grid(9);

            grid.setValue(Grid.EMPTY, 2, 2);

            assertTrue(grid.isEmpty(2, 2));
        }

        @Test
        void should_throw_exception_for_negative_value() {
            Grid grid = new Grid(9);

            assertThrows(IllegalArgumentException.class,
                    () -> grid.setValue(-1, 0, 0));
        }

        @Test
        void should_throw_exception_for_value_too_large() {
            Grid grid = new Grid(9);

            assertThrows(IllegalArgumentException.class,
                    () -> grid.setValue(10, 0, 0));
        }

        @Test
        void should_throw_exception_for_invalid_index() {
            Grid grid = new Grid(9);

            assertThrows(IllegalArgumentException.class,
                    () -> grid.setValue(5, -1, 0));

            assertThrows(IllegalArgumentException.class,
                    () -> grid.setValue(5, 0, 9));
        }
    }

    // =========================================================
    // clear
    // =========================================================
    @Nested
    class ClearTests {

        @Test
        void should_clear_value() {
            Grid grid = new Grid(9);

            grid.setValue(7, 3, 3);
            grid.clear(3, 3);

            assertEquals(Grid.EMPTY, grid.getValue(3, 3));
        }

        @Test
        void should_allow_clear_on_empty_cell() {
            Grid grid = new Grid(9);

            assertDoesNotThrow(() -> grid.clear(0, 0));
        }

        @Test
        void should_throw_exception_for_invalid_index() {
            Grid grid = new Grid(9);

            assertThrows(IllegalArgumentException.class,
                    () -> grid.clear(-1, 0));

            assertThrows(IllegalArgumentException.class,
                    () -> grid.clear(0, 9));
        }
    }

    // =========================================================
    // isEmpty
    // =========================================================
    @Nested
    class IsEmptyTests {

        @Test
        void should_return_true_on_new_grid() {
            Grid grid = new Grid(9);

            assertTrue(grid.isEmpty(0, 0));
        }

        @Test
        void should_return_false_after_setting_value() throws RuleException {
            Grid grid = new Grid(9);

            grid.setValue(3, 0, 0);

            assertFalse(grid.isEmpty(0, 0));
        }

        @Test
        void should_return_true_after_clear() throws RuleException {
            Grid grid = new Grid(9);

            grid.setValue(3, 0, 0);
            grid.clear(0, 0);

            assertTrue(grid.isEmpty(0, 0));
        }
    }

    // =========================================================
    // integration / consistency
    // =========================================================
    @Nested
    class ConsistencyTests {

        @Test
        void should_not_affect_other_cells_when_setting_value() throws RuleException {
            Grid grid = new Grid(9);

            grid.setValue(5, 4, 4);

            for (int i = 0; i < grid.getSize(); i++) {
                for (int j = 0; j < grid.getSize(); j++) {
                    if (i == 4 && j == 4) continue;
                    assertEquals(Grid.EMPTY, grid.getValue(i, j));
                }
            }
        }

        @Test
        void should_overwrite_existing_value() throws RuleException {
            Grid grid = new Grid(9);

            grid.setValue(1, 2, 2);
            grid.setValue(8, 2, 2);

            assertEquals(8, grid.getValue(2, 2));
        }
    }
}