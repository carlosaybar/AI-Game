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
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class LoShu extends AIGame {
	
	public LoShu(String filePath) throws FileNotFoundException, IOException {
		super(filePath);
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
		String FilePath = "C:\\Users\\Missions\\Desktop\\LoShu.txt";
		LoShu lo = new LoShu(FilePath);
		String filePath = "C:\\Users\\Missions\\Desktop\\LoShu.txt";//args[0];
		readFile(); //calls the sequence method from below
		setArray(); // calls the setArray method
		lo.solve();
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
     * Filters elements per row and column from a list of all possible values
     * @param row is used as a location parameter on a 2D array
     * @param col is used as a location parameter on a 2D array
     * @return candidates a filtered array of valid candidates per element [row,col]
     */
    public ArrayList<Integer> getCandidates(int row, int col){
        int[] options = {1,2,3,4,5,6,7,8,9}; // list of all possible values in a sudoku square
        ArrayList candidates = new ArrayList<Integer>(); // ArrayList that holds Integer objects

        // adds each element of value[] into ArrayList candidates
        for(int o: options){
            candidates.add(o);
        }

        // searches the row for candidates
        for(int j = 0; j < grid.length; j++){
            if (candidates.contains(grid[row][j])) {
                candidates.remove(new Integer(grid[row][j])); // filters candidates by row
            }
        }

        // searches the column for candidates
        for(int i = 0; i < grid.length; i++){
            if (candidates.contains(grid[i][col])){
                candidates.remove(new Integer(grid[i][col]));// filters candidates by col
            }
        }

        // checks the subgrid for candidates
        int subRow = row - row % 3;
        int subCol = col - col % 3;

        for(int j = subRow; j < subRow + 3; j++){
            for(int i = subCol; i < subCol + 3; i++){
                if(candidates.contains(grid[i][j])){
                    candidates.remove(new Integer(grid[i][j])); // filters candidates by subgrid
                }
            }
        }
        return candidates;
    }
	
	
    public boolean isRowValid(int row){
        boolean rowCheck = false; //defaults to false
        int[] options = {1,2,3,4,5,6,7,8,9};
        ArrayList rowValues = new ArrayList<Integer>();
        ArrayList candidates = new ArrayList<Integer>();

        // adds each element of value[] into ArrayList candidates
        for(int v: options){
            candidates.add(v);
        }

        for(int i = 0; i < grid.length; i++){
            rowValues.add(new Integer(grid[row][i]));
        }

        // sorts subGridValues so that we can utilize ArrayList.equals() which compares 2 objects
        Collections.sort(rowValues);

        if(rowValues.equals(candidates)){
            rowCheck = true; // if rowValues contains each value of candidates then set this to true
        }

        return rowCheck;
    }

    /**
     * Checks the entire column to see if an element occurs more than once, since there are 9 possible elements and 9
     * available spots, that means in a valid row, for each element to appear, it should only appear once
     * @param col is used as a location parameter on a 2D array
     * @return colCheck true if column is valid
     */
    public boolean isColValid(int col){
        boolean colCheck = false;
        int[] options = {1,2,3,4,5,6,7,8,9};
        ArrayList candidates = new ArrayList<Integer>();
        ArrayList colValues = new ArrayList<Integer>();

        // adds each element of value[] into ArrayList candidates
        for(int v: options){
            candidates.add(v);
        }

        for(int i = 0; i < grid.length; i++){
            colValues.add(new Integer(grid[i][col]));
        }

        // sorts subGridValues so that we can utilize ArrayList.equals() which compares 2 objects
        Collections.sort(colValues);

        if(colValues.equals(candidates)){
            colCheck = true;
        }

        return colCheck;
    }
	

    /**
     * Incorporates Class Sudoku to try and solve the puzzle
     * @return grid[][] which should hopefully be completed
     */
    public int[][] solve(){
        Random randint = new Random();
        int index = 0;
        boolean isComplete;

        do{
            isComplete = true;
            boolean isNull = false;
            for(int j = 0; j < grid.length; j++){
                for(int i = 0; i < grid.length; i++){
                	ArrayList<Integer> currentCandidates = new ArrayList<Integer>();

                    if(grid[i][j] == 0){
                        currentCandidates = getCandidates(i,j);
                        if(currentCandidates.size() == 0){
                            isNull = true;
                            break;
                        }
                        index = randint.nextInt(currentCandidates.size());
                        grid[i][j] = currentCandidates.get(index);
                        toString();
                      
                    }

                    System.out.println();
                    //System.out.print(grid[j][i]);
                    toString();                   
                }
                if(isNull){
                  break;
                }

            }

            for(int j = 0; j < grid.length; j++){
                for(int i = 0; i < grid.length; i++){
                    if(grid[i][j] == 0) 
                    {
                        isComplete = false;
                    }
                }
            }
        } while(!isComplete);


        return grid;
    }
	
    /**
     * Prints the sudoku grid
     * @return null
     */
    public String toString(){
    	System.out.println();
		for(int row = 0; row < grid.length; row++)
		{
			for(int col = 0; col < grid.length; col++)
			{

					System.out.print(grid[row][col] + " "); //prints all the integers from every position in the grid in a 9 by 9 format
			}
			System.out.print("\n");
		}
        return null;
    }
	
	/**
	 * here I check to see if the sum of the rows, columns, and diagonals are equal
	 * if they are equal, the program will output a message saying that it is a Lo Shu Square
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static boolean checkIfLoShu() throws FileNotFoundException, IOException {
		String FilePath = "C:\\Users\\Missions\\Desktop\\LoShu.txt";
		LoShu lo = new LoShu(FilePath);
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
        	System.out.println("this is a not a shu magic square");
        }
		return false;

}
}