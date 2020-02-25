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
	static int size;
	static String filePath = "C:\\Users\\Missions\\Desktop\\LoShu.txt";
	/**
	 * here in the main method, i call all the other methods declared below
	 * @param args the arguments in the main method
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String []args) throws FileNotFoundException, IOException{

		LoShu lo = new LoShu(filePath);
		readFile();
		//setArray();
		lo.solve();//lo rellenamos
		lo.toString();
		}
	
	/**
	 *in the following method the user inputs the sequence as a string
	 *the program converts each character in the sequence into an integer
	 *and stores them in an array called arrayOfInts
	 * @throws IOException, fileNotFoundException 
	 */
	public static void readFile() throws IOException, FileNotFoundException
	{

	   
//args[0];
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
		
		int totalNumbers = ((puzzle).size()); 
		size = (int) Math.sqrt(totalNumbers); //does the square root of the arraylist in order to get the size of row and col
		grid = new int [size][size]; //sets the size of the grid to 3x3	
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
	}

	/**
	 * 
	 */
	public int [][] solve(){

	int col = size/2;
	int row = 0;
	int next_col = 0;
	int next_row = 0;

	grid[row][col]= 1;
	
	for(int i = 2; i <= size*size;i++){
		if (row-1<0){
			row = size-1;
		}else{
			row = row-1;
		}
		if (col+1 > size-1){ //after the you have filled a position in the last column...
			col = 0;		 //it will start the next row, going back to colum 0
		}else{
			col = col+1; //otherwise it will go to the next position in the row, one colum over
		}
		if (grid[row][col] == 0){
			grid[row][col] = i;
			next_row = row; 
			next_col = col;//stores the last position filled
		}
		else{
			row = next_row + 1;
			col = next_col;//goes back to the last position filled
			grid[row][col] = i;
		}
	}
	return grid;

	}
		
	public String toString(){
		System.out.println();
		for(int row = 0; row < grid.length; row++)
		{
			for(int col = 0; col < grid.length; col++)
			{

				System.out.print(grid[row][col] + "\t"); //prints all the integers from every position in the grid in a 9 by 9 format
			}
			System.out.print("\n");
		}
		return null;
	}

	@Override
	public String toString(int[][] grid) {
		// TODO Auto-generated method stub
		return null;
	}
}