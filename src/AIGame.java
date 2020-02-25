import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class AIGame {

    private String version;
    protected int[][] grid;

    /*main() was removed because we can't override main because main is a static method*/
    //public abstract void main(String[] args) throws IOException, FileNotFoundException;

    /**
     * loads file into grid
     * @param filePath
     * @throws FileNotFoundException
     * @throws IOException
     */
    public AIGame(String filePath) throws FileNotFoundException, IOException
    {
        String filepath = filePath;
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

    public abstract String toString(int[][] grid);
//    {
//
//        for(int row = 0; row < grid.length; row++)
//        {
//            for(int col = 0; col < grid.length; col++)
//            {
//
//                System.out.print(grid[row][col] + " "); //prints all the integers from every position in the grid in a 9 by 9 format
//            }
//            System.out.print("\n");
//        }
//
//        return null;
//    }





}
