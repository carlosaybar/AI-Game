import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class AIGame {

	private String version;
	protected int[][] grid;
	public abstract void main(String[] args) throws FileNotFoundException, IOException;
	
	/**
	 * loads file into grid
	 * @param filePath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public AIGame(String filePath) throws FileNotFoundException, IOException 
	{
		//readPuzzle(filePath); //calls the readPuzzle method
	}
	
	protected int[][]solve()
	{
		return null;
		
	}
	
	/**
	 * returns true if the grid is valid
	 * @return
	 */
	public boolean isValid()
	{
		return false;
		
	}
	
	/**
	 * this toString method returns the elements in the grid 
	 * as a string in a 9 by 9 order
	 */
	
	public String toString()
	{
		
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
	
	
	
	

}
