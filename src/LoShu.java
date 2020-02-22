/*
 Carlos Aybar
 Intro to Java
 01/09/20
 This program asks the user for a sequence of nine numbers
 then its separates the nine numbers into individual integers
 and stores them in an array, from that array I pass the numbers
 into a two dimensional grid and then check to see if the sequence
 is a magic square or not
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LoShu extends AIGame {
	
	public LoShu(String filePath) throws FileNotFoundException, IOException {
		super(filePath);
		// TODO Auto-generated constructor stub
	}

	static int [] [] grid = {{0 , 0}};
	static String sequence;
	static int [] arrayOfInts;
	
	/**
	 * here in the main method, i call all the other methods declared below
	 * @param args the arguments in the main method
	 */
	public static void main(String [] args)
	{
		sequence(); //calls the sequence method from below
		setArray(); // calls the setArray method
		checkIfLoShu(); // calls the checkIfLoShu method
	}
	
	/**
	 *in the following method the user inputs the sequence as a string
	 *the program converts each character in the sequence into an integer
	 *and stores them in an array called arrayOfInts
	 */
	public static void sequence()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("please enter a sequence of nine numbers: ");
		sequence = input.nextLine();
		arrayOfInts = new int[sequence.length()]; //sets the length of the arrayOfInts array to the string Sequence
		
	    grid = new int [3][3]; //sets the size of the grid to 3x3
		
	    
	    /*
	     * this for loop devides the string sequence into characters and
	     * converts each one into integers
	     */
	    int i;
		for(i = 0; i < sequence.length(); i++){
			arrayOfInts[i] = Integer.parseInt(String.valueOf(sequence.charAt(i))); 
		    System.out.println(arrayOfInts[i]);
		}

		
	}
	
	
	/**
	 * this method gets the integers from the one dimensional array (arrayOfInts)
	 * and stores each integer in the two dimensional array grid
	 * 
	 */
	public static void setArray()
	{
		
		/*
		 * the first two for loops iterates through the rows and colums of the grid array
		 * and fills each possition with the appropriate number
		 */
		for(int row = 0; row <3; row++) 
		{
			for(int col = 0; col <3; col++)
			{
				for (int i = 0; i < sequence.length(); i++) //iterates through the sequence of numbers
				{
				grid[row][col] = arrayOfInts[i]; //writing the numbers from array into the grid
				i++;
				}
				System.out.println(grid);
		}


	}	
		
	}
	
	/**
	 * here I check to see if the sum of the rows, columns, and diagonals are equal
	 * if they are equal, the program will output a message saying that it is a Lo Shu Square
	 * @return
	 */
	public static boolean checkIfLoShu() {
		
		/*
		 * this if statement checks to see if the sum of the columns, rows, and diagonals are equal
		 */
        if( grid [0][0] + grid[1][0] + grid[2][0] == grid [0][1] + grid[1][1] + grid[2][1] &&
        	grid [0][1] + grid[1][1] + grid[2][1] == grid [0][2] + grid[1][2] + grid[2][2] &&
			grid [0][0] + grid[0][1] + grid[0][2] ==  grid [1][0] + grid[1][1] + grid[1][2] && 
			grid [1][0] + grid[1][1] + grid[1][2] == grid [2][0] + grid[2][1] + grid[2][2] && 
		 	 grid [0][0] + grid[1][1] + grid[2][2] == grid [2][0] + grid[1][1] + grid[0][2])  
        {
        	System.out.println("this is a shu magic square");
            return true;
        }
        else
        {
        	System.out.println( "this is not a shu magic square");
            return false;
        }

}
}

/*
 *Error Report: I was unable to figure out how to write the elements of the array arrayOfInts 
 *into the two dimensional array grid [] []
 *for some reason I am only writing the address where each specific element is located
 *this issue hinders my program from accurately determining what is a Lo Shu magic square and
 *what is not.
*/