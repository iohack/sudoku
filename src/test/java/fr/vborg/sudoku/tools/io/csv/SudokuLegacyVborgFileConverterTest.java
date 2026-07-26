package fr.vborg.sudoku.tools.io.csv;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SudokuLegacyVborgFileConverterTest {

	private static final String VALID_SOLUTION_VBORG =
	        "5;3;4;6;7;8;9;1;2;" +
	        "6;7;2;1;9;5;3;4;8;" +
	        "1;9;8;3;4;2;5;6;7;" +
	        "8;5;9;7;6;1;4;2;3;" +
	        "4;2;6;8;5;3;7;9;1;" +
	        "7;1;3;9;2;4;8;5;6;" +
	        "9;6;1;5;3;7;2;8;4;" +
	        "2;8;7;4;1;9;6;3;5;" +
	        "3;4;5;2;8;6;1;7;9";
	
	private static final String VALID_GRID =
            "530070000" +
            "600195000" +
            "098000060" +
            "800060003" +
            "400803001" +
            "700020006" +
            "060000280" +
            "000419005" +
            "000080079";

    private static final String VALID_SOLUTION =
            "534678912" +
            "672195348" +
            "198342567" +
            "859761423" +
            "426853791" +
            "713924856" +
            "961537284" +
            "287419635" +
            "345286179";
    private static final String VALID_GRID_VBORG =
            "5;3;0;0;7;0;0;0;0;" +
            "6;0;0;1;9;5;0;0;0;" +
            "0;9;8;0;0;0;0;6;0;" +
            "8;0;0;0;6;0;0;0;3;" +
            "4;0;0;8;0;3;0;0;1;" +
            "7;0;0;0;2;0;0;0;6;" +
            "0;6;0;0;0;0;2;8;0;" +
            "0;0;0;4;1;9;0;0;5;" +
            "0;0;0;0;8;0;0;7;9";
    
    @Nested
    class ConvertLegacyToVborgTests {
    	
    	@Test
    	void should_reject_zero_grid_size()
    	{
    		assertThrows(
    				IllegalArgumentException.class,
    				() ->
    				SudokuLegacyVborgFileConverter
    				.convertLegacyToVborg("1234", 0));
    	}

    	@Test
        void should_convert_4x4_grid() throws InconsistencyCsvFileException
        {
            String result =
                SudokuLegacyVborgFileConverter.convertLegacyToVborg(
                    "1234000000004321",
                    4);

            assertEquals(
                "1;2;3;4;0;0;0;0;0;0;0;0;4;3;2;1",
                result);
        }

        @Test
        void should_convert9x9_grid() throws InconsistencyCsvFileException 
        {
        	String result =
        	        SudokuLegacyVborgFileConverter.convertLegacyToVborg(
        	                VALID_GRID,
        	                9);

        		assertAll(
        				() -> assertEquals(
        						VALID_GRID_VBORG,
        						result),

        				() -> assertEquals(
        						80,
        						result.chars()
        						.filter(c -> c == ';')
        						.count())
        				);
        }
        
        @Test
        void should_convert_all_cells_of_grid()
                throws InconsistencyCsvFileException {

            String result =
                    SudokuLegacyVborgFileConverter.convertLegacyToVborg(
                            VALID_GRID,
                            9);

            assertEquals(
                    81,
                    result.split(";").length);
        }
        
        @Test
        void should_convert_empty_grid()
                throws InconsistencyCsvFileException {

            String result =
                    SudokuLegacyVborgFileConverter.convertLegacyToVborg(
                            "0000000000000000",
                            4);

            assertEquals(
                    "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0",
                    result);
        }

        @Test
        void should_reject_null_value() {

            assertThrows(
                    NullPointerException.class,
                    () ->
                    SudokuLegacyVborgFileConverter
                            .convertLegacyToVborg(null, 9));
        }


        @Test
        void should_reject_invalid_characters() {

            assertThrows(
                    InconsistencyCsvFileException.class,
                    () ->
                    SudokuLegacyVborgFileConverter
                            .convertLegacyToVborg("12A", 1));
        }


        @Test
        void should_reject_invalid_length() {

            assertThrows(
                    InconsistencyCsvFileException.class,
                    () ->
                    SudokuLegacyVborgFileConverter
                            .convertLegacyToVborg("123", 2));
        }


        @Test
        void should_reject_negative_grid_size() {

            assertThrows(
                    IllegalArgumentException.class,
                    () ->
                    SudokuLegacyVborgFileConverter
                            .convertLegacyToVborg("123", -1));
        }
    }


    @Nested
    class ConvertLegacyFileTests {

    	@Test
    	void should_reject_directory_as_destination(@TempDir Path tempDir)
    	        throws IOException {

    	    Path source = tempDir.resolve("legacy.csv");
    	    Path destination = tempDir.resolve("directory");


    	    Files.write(
    	            source,
    	            List.of(
    	                    "quizzes,solutions",
    	                    VALID_GRID + "," + VALID_SOLUTION),
    	            StandardCharsets.UTF_8);


    	    Files.createDirectory(destination);


    	    assertThrows(
    	            IOException.class,
    	            () ->
    	            SudokuLegacyVborgFileConverter.convertLegacyFile(
    	                    source,
    	                    destination));
    	}
    	
    	@Test
    	void should_reject_missing_source_file(@TempDir Path tempDir)
    	{
    	    Path source = tempDir.resolve("missing.csv");
    	    Path destination = tempDir.resolve("vborg.csv");

    	    assertThrows(
    	            IOException.class,
    	            () ->
    	            SudokuLegacyVborgFileConverter.convertLegacyFile(
    	                    source,
    	                    destination));
    	}
    	
    	@Test
        void should_convert_complete_csv_file(@TempDir Path tempDir)
                throws IOException, InconsistencyCsvFileException {

            Path source = tempDir.resolve("legacy.csv");
            Path destination = tempDir.resolve("vborg.csv");

            Files.write(
                    source,
                    List.of(
                            "quizzes,solutions",
                            VALID_GRID + "," + VALID_SOLUTION),
                    StandardCharsets.UTF_8);


            SudokuLegacyVborgFileConverter.convertLegacyFile(
                    source,
                    destination);


            List<String> lines =
                    Files.readAllLines(destination, StandardCharsets.UTF_8);


            assertEquals(
                    "puzzles,solutions",
                    lines.get(0));


            assertEquals(
                    2,
                    lines.size());

            assertEquals(
                    VALID_GRID_VBORG + "," + VALID_SOLUTION_VBORG,
                    lines.get(1));
        }


        @Test
        void should_reject_invalid_header(@TempDir Path tempDir)
                throws IOException {

            Path source = tempDir.resolve("legacy.csv");
            Path destination = tempDir.resolve("vborg.csv");

            Files.write(
                    source,
                    List.of(
                            "wrong,header"),
                    StandardCharsets.UTF_8);


            assertThrows(
                    InconsistencyCsvFileException.class,
                    () ->
                    SudokuLegacyVborgFileConverter.convertLegacyFile(
                            source,
                            destination));
        }


        @Test
        void should_accept_empty_file(@TempDir Path tempDir)
                throws IOException, InconsistencyCsvFileException {

            Path source = tempDir.resolve("legacy.csv");
            Path destination = tempDir.resolve("vborg.csv");

            Files.createFile(source);


            SudokuLegacyVborgFileConverter.convertLegacyFile(
                    source,
                    destination);


            assertEquals(
                    0,
                    Files.size(destination));
        }


        @Test
        void should_reject_missing_separator(@TempDir Path tempDir)
                throws IOException {

            Path source = tempDir.resolve("legacy.csv");
            Path destination = tempDir.resolve("vborg.csv");


            Files.write(
                    source,
                    List.of(
                            "quizzes,solutions",
                            VALID_GRID),
                    StandardCharsets.UTF_8);


            assertThrows(
                    InconsistencyCsvFileException.class,
                    () ->
                    SudokuLegacyVborgFileConverter.convertLegacyFile(
                            source,
                            destination));
        }


        @Test
        void should_reject_multiple_separators(@TempDir Path tempDir)
                throws IOException {

            Path source = tempDir.resolve("legacy.csv");
            Path destination = tempDir.resolve("vborg.csv");


            Files.write(
                    source,
                    List.of(
                            "quizzes,solutions",
                            VALID_GRID + "," +
                            VALID_SOLUTION + ",extra"),
                    StandardCharsets.UTF_8);


            assertThrows(
                    InconsistencyCsvFileException.class,
                    () ->
                    SudokuLegacyVborgFileConverter.convertLegacyFile(
                            source,
                            destination));
        }


        @Test
        void should_ignore_empty_and_whitespace_lines(@TempDir Path tempDir)
                throws IOException, InconsistencyCsvFileException {

            Path source = tempDir.resolve("legacy.csv");
            Path destination = tempDir.resolve("vborg.csv");


            Files.write(
                    source,
                    List.of(
                            "quizzes,solutions",
                            "",
                            "   ",
                            VALID_GRID + "," + VALID_SOLUTION),
                    StandardCharsets.UTF_8);

            SudokuLegacyVborgFileConverter.convertLegacyFile(
                    source,
                    destination);


            assertEquals
            (
                    2,
                    Files.readAllLines(destination, StandardCharsets.UTF_8).size()
            );
        }
        
        @Test
        void should_reject_null_legacy_path()
        {
            assertThrows
            (
                    NullPointerException.class,
                    () -> SudokuLegacyVborgFileConverter.convertLegacyFile(
                            null,
                            Path.of("dummy"))
            );
        }        
    }


    @Nested
    class PuzzleSolutionCompatibilityTests {

    	@Test
    	void should_reject_invalid_solution_length(@TempDir Path tempDir)
    	        throws IOException {

    	    Path source = tempDir.resolve("legacy.csv");
    	    Path destination = tempDir.resolve("vborg.csv");

    	    Files.write(
    	        source,
    	        List.of(
    	            "quizzes,solutions",
    	            VALID_GRID + ",123"),
    	        StandardCharsets.UTF_8);


    	    assertThrows(
    	        InconsistencyCsvFileException.class,
    	        () ->
    	        SudokuLegacyVborgFileConverter.convertLegacyFile(
    	                source,
    	                destination));
    	}
    	
    	@Test
    	void should_preserve_solution_when_puzzle_is_valid(@TempDir Path tempDir)
    	        throws IOException, InconsistencyCsvFileException {

    	    Path source = tempDir.resolve("legacy.csv");
    	    Path destination = tempDir.resolve("vborg.csv");

    	    Files.write(
    	            source,
    	            List.of(
    	                    "quizzes,solutions",
    	                    VALID_GRID + "," + VALID_SOLUTION),
    	            StandardCharsets.UTF_8);

    	    SudokuLegacyVborgFileConverter.convertLegacyFile(
    	            source,
    	            destination);

    	    List<String> lines =
    	            Files.readAllLines(destination, StandardCharsets.UTF_8);

    	    assertEquals(
    	            VALID_GRID_VBORG + "," + VALID_SOLUTION_VBORG,
    	            lines.get(1));
    	}

        @Test
        void should_reject_incompatible_puzzle(@TempDir Path tempDir)
                throws IOException {

            Path source = tempDir.resolve("legacy.csv");
            Path destination = tempDir.resolve("vborg.csv");


            String invalidPuzzle =
                    "930070000" +
                    VALID_GRID.substring(9);


            Files.write(
                    source,
                    List.of(
                            "quizzes,solutions",
                            invalidPuzzle + "," + VALID_SOLUTION),
                    StandardCharsets.UTF_8);


            assertThrows
            (
                    InconsistencyCsvFileException.class,
                    () ->
                    SudokuLegacyVborgFileConverter.convertLegacyFile(
                            source,
                            destination)
            );
        }
        @Test
        void should_convert_multiple_csv_entries(@TempDir Path tempDir)
                throws IOException, InconsistencyCsvFileException {

            Path source = tempDir.resolve("legacy.csv");
            Path destination = tempDir.resolve("vborg.csv");

            String secondGrid =
                    "000000000" +
                    "000000000" +
                    "000000000" +
                    "000000000" +
                    "000000000" +
                    "000000000" +
                    "000000000" +
                    "000000000" +
                    "000000000";

            String secondSolution =
                    "123456789" +
                    "456789123" +
                    "789123456" +
                    "234567891" +
                    "567891234" +
                    "891234567" +
                    "345678912" +
                    "678912345" +
                    "912345678";

            Files.write(
                    source,
                    List.of(
                            "quizzes,solutions",
                            VALID_GRID + "," + VALID_SOLUTION,
                            secondGrid + "," + secondSolution),
                    StandardCharsets.UTF_8);


            SudokuLegacyVborgFileConverter.convertLegacyFile(
                    source,
                    destination);


            List<String> lines =
                    Files.readAllLines(destination, StandardCharsets.UTF_8);


            assertAll(
                    () -> assertEquals(
                            3,
                            lines.size()),

                    () -> assertEquals(
                            "puzzles,solutions",
                            lines.get(0)),

                    () -> assertEquals(
                            VALID_GRID_VBORG + "," + VALID_SOLUTION_VBORG,
                            lines.get(1)),

                    () -> assertEquals(
                            "0;0;0;0;0;0;0;0;0;" +
                            "0;0;0;0;0;0;0;0;0;" +
                            "0;0;0;0;0;0;0;0;0;" +
                            "0;0;0;0;0;0;0;0;0;" +
                            "0;0;0;0;0;0;0;0;0;" +
                            "0;0;0;0;0;0;0;0;0;" +
                            "0;0;0;0;0;0;0;0;0;" +
                            "0;0;0;0;0;0;0;0;0;" +
                            "0;0;0;0;0;0;0;0;0" +
                            "," +
                            "1;2;3;4;5;6;7;8;9;" +
                            "4;5;6;7;8;9;1;2;3;" +
                            "7;8;9;1;2;3;4;5;6;" +
                            "2;3;4;5;6;7;8;9;1;" +
                            "5;6;7;8;9;1;2;3;4;" +
                            "8;9;1;2;3;4;5;6;7;" +
                            "3;4;5;6;7;8;9;1;2;" +
                            "6;7;8;9;1;2;3;4;5;" +
                            "9;1;2;3;4;5;6;7;8",
                            lines.get(2))
            );
        }
    }
}