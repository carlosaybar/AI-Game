import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;


/**
 * @class Sudoku Creates a Sudoku object using 2D arrays and array lists
 */
public class Sudoku extends AIGame{
    public int[][] grid;
    public int gridGenerated;
    public int[][] tempGrid;
    ArrayList<Integer>puzzle = new ArrayList<Integer>();

    /**
     * loads file into grid
     * @param filePath
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Sudoku(String filePath) throws FileNotFoundException, IOException {
        super(filePath);
        readPuzzle(filePath);
        gridGenerated = 0;
    }

    /**
     * Constructs an instance of the Sudoku class
     * @param args
     * @throws IOException
     * @throws FileNotFoundException
     */

    public static void main(String[] args) throws IOException, FileNotFoundException {
        String filePath = args[0];

        Sudoku sudoku1 = new Sudoku(filePath);

        sudoku1.toString(); // prints the puzzle without a solution
        System.out.println();

        sudoku1.solve();

        sudoku1.toString();
    }

    /**
     * Gets called by the constructor. Inputs the contents of the .txt file into a 2D array
     * @param filePath a string location of the sudoku .txt file
     * @return grid[][] is 2D array of the .txt file
     * @throws IOException handles common input/output exception
     */
    public int[][] readPuzzle(String filePath)throws IOException {

        Scanner inputFile = new Scanner(filePath); // reads from .txt file

        FileWriter fileWriter = new FileWriter(filePath , true);
        Scanner sc = new Scanner (new File(filePath));
        int loadUsers = 0;
        while(sc.hasNextInt())
        {
            puzzle.add(sc.nextInt());
        }
        sc.close();

        int totalNumbers = ((puzzle).size());
        int size = (int) Math.sqrt(totalNumbers); //does the square root of the arraylist in order to get the size of row and col

        grid = new int[size][size]; // initializes 2D array
        tempGrid = new int [size][size];
        File file = new File(filePath); // creates a file object containing the .txt file

        // initializes each element of grid[i][j] from the .txt file
        int h = 0;
        for(int j = 0; j < size; j++){
            for(int i = 0; i < size; i++){
                grid[i][j] = puzzle.get(h);
                h++;
            }
        }

        inputFile.close(); // closes the Scanner object
        return grid;
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
        for(int j = 0; j < tempGrid.length; j++){
            if (candidates.contains(tempGrid[row][j])) {
                candidates.remove(new Integer(tempGrid[row][j])); // filters candidates by row
            }
        }

        // searches the column for candidates
        for(int i = 0; i < tempGrid.length; i++){
            if (candidates.contains(tempGrid[i][col])){
                candidates.remove(new Integer(tempGrid[i][col]));// filters candidates by col
            }
        }

        // checks the subgrid for candidates
        int subRow = row - row % 3;
        int subCol = col - col % 3;

        for(int j = subRow; j < subRow + 3; j++){
            for(int i = subCol; i < subCol + 3; i++){
                if(candidates.contains(tempGrid[i][j])){
                    candidates.remove(new Integer(tempGrid[i][j])); // filters candidates by subgrid
                }
            }
        }
        return candidates;
    }

    /**
     * Checks the entire row to see if an element occurs more than once, since there are 9 possible elements and 9
     * available spots, that means in a valid row, for each element to appear, it should only appear once
     * @param row is used as a location parameter on a 2D array
     * @return rowCheck true if row is valid
     */
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
     * Checks the entire sub-grid (3x3) to see if an element occurs more than once, since there are 9 possible elements
     * and 9 available spots, that means in a valid row, for each element to appear, it should only appear once
     * @param row is used as a location parameter on a 2D array
     * @param col is used as a location parameter on a 2D array
     * @return
     */
    public boolean isSubGridValid(int row, int col){
        boolean subGridValid = false;

        int[] options = {1,2,3,4,5,6,7,8,9};
        ArrayList candidates = new ArrayList<Integer>();
        ArrayList subGridValues = new ArrayList<Integer>();
        int subRow = row - row % 3;
        int subCol = col - col % 3;

        // initializes an ArrayList using candidates
        for(int v: options){
            candidates.add(v);
        }

        for(int j = subRow; j < subRow + 3; j++){
            for(int i = subCol; i < subCol + 3; i++){
                subGridValues = new ArrayList(grid[i][j]);
            }
        }

        // sorts subGridValues so that we can utilize ArrayList.equals() which compares 2 objects
        Collections.sort(subGridValues);

        if(subGridValues.equals(candidates)){
            subGridValid = true;
        }

        return subGridValid;
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
            for(int j = 0; j < grid.length; j++){
                for(int i = 0; i < grid.length; i++){
                    tempGrid[i][j] = grid[i][j];
                }
            }
            isComplete = true;
            boolean isNull = false;
            for(int j = 0; j < tempGrid.length; j++){
                for(int i = 0; i < tempGrid.length; i++){

                    if(tempGrid[i][j] == 0){
                        ArrayList<Integer> currentCandidates = getCandidates(i,j);
                        if(currentCandidates.size() == 0){
                            isNull = true;
                            break;
                        }
                        index = randint.nextInt(currentCandidates.size());
                        tempGrid[i][j] = currentCandidates.get(index);
                    }
                }
                if(isNull){
                    break;
                }
            }

            for(int j = 0; j < tempGrid.length; j++){
                for(int i = 0; i < tempGrid.length; i++){
                    if(tempGrid[i][j] == 0){
                        isComplete = false;
                    }
                }
            }
            System.out.println("Grids Generated: " + gridGenerated);
            toString(tempGrid);
            System.out.println();

            gridGenerated++;
        } while(!isComplete);

        return tempGrid;
    }

    /**
     * Prints the sudoku grid
     * @return null
     */
    @Override
    public String toString(int[][] tempGrid){
        for(int j = 0; j < 9; j++)
            for(int i = 0; i < 9; i++){
                System.out.print(tempGrid[i][j] + " ");
                if(i == 8){
                    System.out.println();
                }
            }
        return null;
    }

    /**
     * Gets the variable gridGenerated which counts the times the Sudoku.solve() method tries to solve the puzzle
     * @return gridGenerated
     */
    public int getGridsGenerated(){
        return gridGenerated;
    }
}
