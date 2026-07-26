/**
 * 
 */
package fr.vborg.sudoku.tools.io.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.regex.Pattern;

import fr.vborg.sudoku.localization.Messages;

/**
* Utility class used to convert Sudoku CSV files from the legacy format
* to the Vborg format.
*
* <p>The legacy format stores each Sudoku grid as a compact sequence of
* digits, where each character represents a single cell value. This format
* is limited to Sudoku grids whose values are in the range {@code 0..9}.</p>
*
* <p>The Vborg format stores each cell value as an individual field separated
* by a semicolon ({@code ';'}). This representation supports values greater
* than {@code 9} and is therefore suitable for larger Sudoku grids such as
* {@code 16×16}, {@code 25×25} and beyond.</p>
*
* <h2>Supported formats</h2>
*
* <h3>Legacy format</h3>
* <pre>
* quizzes,solutions
* 530070000...,534678912...
* </pre>
*
* <h3>Vborg format</h3>
* <pre>
* puzzles,solutions
* 5;3;0;0;7;0;0;0;0...,5;3;4;6;7;8;9;1;2...
* </pre>
*
* <p>During the conversion, the converter validates the consistency of the
* source data before producing the destination file. In particular, it checks
* the format of legacy grids and ensures that every non-empty value present
* in a puzzle matches the corresponding value in its associated solution.</p>
*
* <p>This class is stateless and its utility methods do not maintain
* internal mutable state.</p>
*
* @since 1.8.1
*/
public final class SudokuLegacyVborgFileConverter {
	/**
	 * Separator used between the puzzle and its solution in CSV records.
	 */
	private static final String DATA_SEPARATOR = ",";
	
	/**
	 * Separator used between Sudoku cell values in the Vborg format.
	 */
	private static final String VBORG_VALUE_SEPARATOR = ";";	
	
	/**
	 * Regular expression matching a legacy Sudoku grid.
	 *
	 * <p>A legacy grid contains only decimal digits, one character per cell.</p>
	 */
	private static final Pattern LEGACY_PATTERN = Pattern.compile("\\d+");
	
	/**
	 * Size of legacy Sudoku grids.
	 */
	private static final int LEGACY_GRID_SIZE = 9;
	
	/**
	 * Expected header of legacy CSV files.
	 */
	private static final String LEGACY_HEADER = "quizzes,solutions";
	
	/**
	 * Header written to Vborg CSV files.
	 */
	private static final String VBORG_HEADER   = "puzzles,solutions";
	
	/**
	 * Utility class. 
	 */
	private SudokuLegacyVborgFileConverter()
    {
    }
	
	/**
	 * Converts a legacy Sudoku CSV file into the Vborg CSV format.
	 *
	 * <p>The source file is processed sequentially without loading its
	 * entire contents into memory, making this method suitable for very
	 * large files.</p>
	 *
	 * <p>The header line is validated and replaced with the corresponding Vborg header.</p>
	 * 
	 * <p>The original String is never modified.</p>
	 * 
	 * <p>The grid size must be positive.</p>
	 *
	 * @param legacyPath path of the source CSV file
	 * @param vborgPath path of the destination CSV file
	 * @throws IOException if an I/O error occurs
	 * @throws InconsistencyCsvFileException if the source file contains
	 *         inconsistent data
	 */
	public static void convertLegacyFile(final Path legacyPath, final Path vborgPath) 
			throws InconsistencyCsvFileException,
				IOException
	{
		Objects.requireNonNull(legacyPath, "legacyPath");
		Objects.requireNonNull(vborgPath, "vborgPath");
		try (BufferedReader reader =
	            	Files.newBufferedReader(legacyPath, StandardCharsets.UTF_8);
				BufferedWriter writer = 
					Files.newBufferedWriter(vborgPath, StandardCharsets.UTF_8))
	    {
			// Validate legacy header and write Vborg header
			String line = reader.readLine();
			if( line == null )
			{
				// Empty file
				return;
			}
			if (!LEGACY_HEADER.equals(line.trim()))
			{
			    throw new InconsistencyCsvFileException(Messages.get("csv.converter.invalidHeader", LEGACY_HEADER,
			    	    line));
			}
			writer.write(VBORG_HEADER);
			writer.newLine();
			while( ( line = reader.readLine() ) != null )
			{
				if( line.isBlank() )
				{
					// Ignoring empty lines
					continue;
				}
				final int separatorIndex = line.indexOf(DATA_SEPARATOR);
				if( separatorIndex == -1 )
				{
					throw new InconsistencyCsvFileException(Messages.get("csv.converter.separatorNotFound", DATA_SEPARATOR));					
				}
				
				final String legacyPuzzle = line.substring(0, separatorIndex).trim();				
				final String legacySolution = line.substring(separatorIndex + 1).trim();
				if( line.indexOf(DATA_SEPARATOR, separatorIndex + 1) != -1 )
				{
					throw new InconsistencyCsvFileException(Messages.get("csv.converter.multipleSeparators"));
				}
				
				writeRecord(writer, legacyPuzzle, legacySolution);				
			}			
	    }
	}
	
	/**
	 * Validates that a legacy puzzle is consistent with its solution.
	 *
	 * <p>Every non-empty cell (value different from {@code 0}) in the puzzle
	 * must contain the same value at the corresponding position in the solution.
	 * Empty cells are ignored.</p>
	 *
	 * @param puzzle legacy puzzle
	 * @param solution associated legacy solution
	 * @throws NullPointerException if one argument is {@code null}
	 * @throws InconsistencyCsvFileException if the puzzle and the solution
	 *         are inconsistent
	 */
	private static void validatePuzzleSolution(final String puzzle, final String solution) throws InconsistencyCsvFileException
	{
		Objects.requireNonNull(puzzle,"puzzle");
		Objects.requireNonNull(solution,"solution");
		
		final int puzzleLength = puzzle.length();
		final int solutionLength = solution.length();
		
		if( puzzleLength != solutionLength )
		{
			throw new InconsistencyCsvFileException(Messages.get("csv.converter.invalidPuzzleLength", puzzleLength, solutionLength));
		}
		
		for(int charIndex = 0; charIndex < puzzleLength; charIndex++)
		{
			final char puzzleChar = puzzle.charAt(charIndex); 
			if( puzzleChar != '0' )
			{
				final char solutionChar = solution.charAt(charIndex);
				if( puzzleChar != solutionChar ) {
					throw new InconsistencyCsvFileException(Messages.get("csv.converter.invalidPuzzleValue", charIndex, puzzleChar, solutionChar));
				}
			}
		}
	}
	
	/**
	 * Converts a legacy Sudoku grid into the Vborg textual format.
	 *
	 * <p>This method only supports legacy grids where each cell is represented
	 * by a single decimal digit. Therefore, it is limited to grids containing
	 * values from 0 to 9.</p> 
	 *
	 * @param legacyString the legacy grid
	 * @param gridSize grid size (4, 9, 16, ...)
	 * @return the converted grid
	 * @throws InconsistencyCsvFileException if the input is invalid
	 */
	public static String convertLegacyToVborg(final String legacyString, final int gridSize) throws InconsistencyCsvFileException
	{
		Objects.requireNonNull(legacyString, "legacyString");
		final String trimmedLegacyString = legacyString.trim();
		if( ! LEGACY_PATTERN.matcher(trimmedLegacyString).matches() )
		{
			throw new InconsistencyCsvFileException(Messages.get("csv.converter.invalidLegacyPattern", legacyString, LEGACY_PATTERN.pattern()));
		}
		if (gridSize <= 0)
		{
		    throw new IllegalArgumentException("Negative grid size");
		}
		final int valueCount = gridSize * gridSize;
		final int trimmedLegacyStringLength = trimmedLegacyString.length();
		
		if (trimmedLegacyStringLength != valueCount )
		{
		    throw new InconsistencyCsvFileException(Messages.get("csv.converter.invalidGridLength", valueCount, trimmedLegacyStringLength));
		}
		
		final StringBuilder stringBuilder = new StringBuilder(trimmedLegacyStringLength * 2 - 1 );
		for( int charIndex = 0; charIndex < valueCount; charIndex++)
		{
			if( charIndex > 0 )
			{
				stringBuilder.append(VBORG_VALUE_SEPARATOR);				
			}
			stringBuilder.append(trimmedLegacyString.charAt(charIndex));
		}
		return stringBuilder.toString();		
	}
	
	/**
	 * Converts a validated legacy record and writes it to the destination file.
	 *
	 * <p>Both grids are converted to the Vborg format before being written
	 * as a single CSV record.</p>
	 *
	 * @param writer destination writer
	 * @param legacyPuzzle legacy puzzle
	 * @param legacySolution legacy solution
	 * @throws IOException if an I/O error occurs
	 * @throws InconsistencyCsvFileException if one of the grids is invalid
	 *         or if the puzzle is inconsistent with its solution
	 */
	 private static void writeRecord(
	        final BufferedWriter writer,
	        final String legacyPuzzle,
	        final String legacySolution)
	        throws IOException, InconsistencyCsvFileException
	{
	    validatePuzzleSolution(legacyPuzzle, legacySolution);

	    writer.write(convertLegacyToVborg(legacyPuzzle, LEGACY_GRID_SIZE));
	    writer.write(DATA_SEPARATOR);
	    writer.write(convertLegacyToVborg(legacySolution, LEGACY_GRID_SIZE));
	    writer.newLine();
	}
}
