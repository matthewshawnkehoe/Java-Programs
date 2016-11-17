/**
This program is created in order to simulate Conway's Game of Life.

@author Matthew Kehoe
@version June 6th, 2014
*/

import java.io.*;
import java.util.Scanner;


/**
The main method calls all of the other methods below.
A for() loop is written to print out the generation of 
new cell plots for the next ten generations.
@ param (String[] args) references the console.
@ return type is void.
*/

public class Life
{
	private static Scanner inputfile;

	public static void main(String[] args) throws FileNotFoundException
	{
        boolean[][] cells = ReadFile();
        PrintBoard(cells);
        for (int i = 0; i < 10; i++)
        {
        	cells = nextGeneration(cells);
        	PrintBoard(cells);
        }
		
	}
	
	/** 
	The ReadFile() method receives data from numbers.txt.
	The data from numbers.txt is stored into a 2D array.
	This 2D array is then called by the main() method. 
	This method is static and does not change. We would never
	need to re-import the initial matrix to decide if the
	initial values of the first matrix are 1 or 0.
	@ return type is static boolean.
	*/
	
    public static boolean[][] ReadFile() throws FileNotFoundException{
        boolean[][] matrix = new boolean[10][10];
		File file = new File("Numbers.txt");
		inputfile = new Scanner(file);
        for(int i = 0; i < matrix.length; i++)
        {

            for(int j = 0; j < matrix[0].length; j++)
            {
            	if (inputfile.hasNextInt() && inputfile.nextInt() != 0)    
            	{
            		matrix[i][j] = true;
            	}
            }
        }
        return matrix;
    }
    
    /** 
    The PrintBoard() method is used to show the 2D
    array inside the console. Since we are referencing the 
    Scanner class we can print the 2D array inside the console.
    @ param boolean[][] matrix created from the ReadFile() method
    @ return type is void.
    */
		
    public static void PrintBoard(boolean[][] matrix)
    {
        String cell = "";
        for(boolean[] i : matrix){
            for(boolean val : i)
                if(val)
                    cell += "1 ";
                else
                    cell += "0 ";
            cell += "\n";
        }
        System.out.println(cell);
    }
    
    /** 
    The NextGeneration() method checks the result of the 
    rulesofLife() method. If this method returns true, a new
    cell should sprout. Unlike creating human children, matrices have
    separate index sproutings.
	@ param boolean[][] is referencing the current generation matrix.
	@ return type is static boolean.
     */
    
    public static boolean[][] nextGeneration(boolean[][] cells)
    {
    	boolean[][] nextGenerationOfCells = new boolean[cells.length][cells[0].length];
    	int newCellGenerated;
    	for (int i = 0; i < cells.length; i++)
    	{
    		for (int j = 0; j < cells[0].length; j++)
    		{
    			newCellGenerated = NumberOfNeighbors(cells, i, j);
    			if (rulesOfLife(newCellGenerated, cells[i][j]))
    			{
    				nextGenerationOfCells[i][j] = true;
    			}
    		}
    	}
    	return nextGenerationOfCells;
    }
    
    
    /**
    The rulesOfLife() method follows the logic below
    
	1. Any live cell with fewer than two live neighbors dies, as if caused by loneliness.
	2. Any live cell with two or three live neighbors lives on to the next generation.
	3. Any live cell with more than three live neighbors dies, as if by overcrowding.
	4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
	@param integer value for the numberOfNeghbors, boolean value for alive
	@ return type of static boolean

     */
    
    public static boolean rulesOfLife(int numberOfNeighbors, boolean alive){
        if( alive && (numberOfNeighbors == 2 || numberOfNeighbors == 3))
            return true;
        else if (!alive && numberOfNeighbors == 3)
            return true;
        else
            return false;
    }
    
    /** 
    The NumberOfNeighbors() method looks to see if neighbors of the
    current cell are alive or dead. To check the corner cells we need to
    create a separate method which can see if the matrix is inside the cell
    plot. This will handle edge cases of negative values.
    @ param static int, the integer value does not change until the next
    generation.
    */
    
    private static int NumberOfNeighbors(boolean[][] cells, int r, int c) {
        
    	int deadOrAlive = cells[r][c] ? -1 : 0;
        for(int i = r - 1; i <= r + 1; i++)
        {
            for(int j = c - 1; j <= c + 1; j++)
            {
                if( insideMatrix(cells, i, j) && cells[i][j] )
                {
                	deadOrAlive++;
                }
            }
        }

        return deadOrAlive;
    }
    
    /** 
    The final innerMatrix() method checks to see if the cells referenced
    are inside the matrix. This will target cells inside and outside the
    edge of the matrix. We need to include this inside the NumberOfNeighbors()
    method in order to see if the cell is dead or alive.
    @ param static boolean, a true or false value that doesn't change until
    the generation is reset.
    */

    private static boolean insideMatrix(boolean[][] grid, int i, int j) 
    {
        return i >= 0 && i < grid.length && j >= 0 &&
        		j < grid[0].length;
    }
	
}
