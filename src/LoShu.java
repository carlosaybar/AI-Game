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


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoShu extends AIGame {
	
	public LoShu(String filePath) throws FileNotFoundException, IOException {
		super(filePath);
		// TODO Auto-generated constructor stub
	}

	static int [] [] grid = {{0 , 0}};
	static int [] arrayOfInts;
	static 	ArrayList<Integer>puzzle = new ArrayList<Integer>();
	/**
	 * here in the main method, i call all the other methods declared below
	 * @param args the arguments in the main method
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String [] args) throws FileNotFoundException, IOException
	{
		String filePath = "C:\\Users\\Missions\\Desktop\\sudoku.txt";//args[0];
		readFile(); //calls the sequence method from below
		setArray(); // calls the setArray method
		checkIfLoShu(); // calls the checkIfLoShu method
	}
	
	/**
	 *in the following method the user inputs the sequence as a string
	 *the program converts each character in the sequence into an integer
	 *and stores them in an array called arrayOfInts
	 * @throws IOException, fileNotFoundException 
	 */
	public static void readFile() throws IOException, FileNotFoundException
	{

	   
		String filePath = "C:\\Users\\Missions\\Desktop\\LoShu.txt";//args[0];
       // String file = new File(filePath); // creates a file object containing the .txt file
        Scanner inputFile = new Scanner(filePath); // reads from .txt file
        
		FileWriter fileWriter = new FileWriter(filePath , true);
		Scanner sc = new Scanner (new File(filePath));
		int loadUsers = 0;
		while(sc.hasNextInt())
		{
			puzzle.add(sc.nextInt());
		}
        sc.close(); // closes the Scanner object
		


		
	}
	
	
	/**
	 * this method gets the integers from the one dimensional array (arrayOfInts)
	 * and stores each integer in the two dimensional array grid
	 * 
	 */
	public static void setArray()
	{
		
		int totalNumbers = ((puzzle).size()); 
		int size = (int) Math.sqrt(totalNumbers); //does the square root of the arraylist in order to get the size of row and col
		 grid = new int [size][size]; //sets the size of the grid to 3x3
        // initializes each element of grid[i][j] from the .txt file
		int i =0;
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++)
            {
                    grid[y][x] = puzzle.get(i);
                    i++;
            }

        }


		for(int row = 0; row < grid.length; row++)
		{
			for(int col = 0; col < grid.length; col++)
			{

					System.out.print(grid[row][col] + " "); //prints all the integers from every position in the grid the proper format
			}
			System.out.print("\n");
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
