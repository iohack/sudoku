/**
 * 
 */
package fr.vborg.sudoku.generator;

import java.util.Scanner;

import fr.vborg.sudoku.Puzzle;
import fr.vborg.sudoku.RuleException;

/**
 * 
 */
public final class ManualEntry {
	/**
	 * A classical 9x9 grid puzzle. 
	 */
	public static final int DEFAULT_SIZE = 9;
	
	/**
	 * To read from keyboard. 
	 */
	private static Scanner scanner = new Scanner(System.in);

	private static String response;
	
	private static int readSize()
	{
		String input = scanner.nextLine();
		return "".equals(input)?DEFAULT_SIZE:Integer.parseInt(input);		
	}
	
	/*
	public static Puzzle entryNew()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Entre the puzzle size (press [enter] for 9: ");
		String strSize = scanner.nextLine();
		int size=DEFAULT_SIZE;
		if( ! strSize.equals("") )
		{
			try 
			{
				size = Integer.parseInt(strSize);				
			}catch(final NumberFormatException nfe) {
				System.err.println("size is not a valid integer : " + strSize);
				String strValue = scanner.next();
			}
		}
		else
		{
			System.out.println("defaut size : " + DEFAULT_SIZE);
		}
		try{
			Puzzle puzzle =  new Puzzle(size);
			System.out.println("size valid, puzzle created successfully with empty values (0)");
			System.out.println("enter values from top left to bottom right...");
			System.out.println("[enter] mean empty value");
			
			for(int rowIndex = 0; rowIndex < size; rowIndex++)
			{
				for(int colIndex = 0; colIndex < size; colIndex ++) 
				{
					System.out.print("row " + (rowIndex+1) + ", column " + (colIndex+1) + " : ");
					boolean validInput = false;
					int value = 0;
					while( ! validInput )
					{
						String strValue = scanner.nextLine();
						if( strValue.isEmpty() )
						{
							System.out.println("Empty input, value set to 0");
							validInput = true;
						}
						else
						{
							try 
							{
								value = Integer.parseInt(strValue);								
							}
							catch(NumberFormatException nfe)
							{
								System.err.println("not a valid number : " + strValue);
							}
						}						
					}
					puzzle.setValueAt(rowIndex, colIndex, value);
				}
			}
		}catch(final IllegalArgumentException iae) {
			System.err.println("argument error : " + iae.getMessage());
		}
		return null;
	}
	
	private static int getReadNumber() {
		
		
	}
	*/
}
