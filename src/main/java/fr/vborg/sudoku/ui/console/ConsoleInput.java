/**
 * 
 */
package fr.vborg.sudoku.ui.console;

import java.util.Scanner;

/**
 * 
 */
public final class ConsoleInput {
	 private final Scanner scanner;

	    public ConsoleInput()
	    {
	        scanner = new Scanner(System.in);
	    }

	    public int readRow()
	    {
	        System.out.print("Row    : ");
	        return scanner.nextInt();
	    }

	    public int readColumn()
	    {
	        System.out.print("Column : ");
	        return scanner.nextInt();
	    } 

	    public int readValue()
	    {
	        System.out.print("Value  : ");
	        return scanner.nextInt();
	    }
}
