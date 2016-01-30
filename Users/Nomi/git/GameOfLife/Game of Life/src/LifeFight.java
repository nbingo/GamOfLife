import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class LifeFight extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6163833510697226749L;
	public static int[][] soup;
	public static int cellSize = 8;
	public static int rows;
	public static int columns;
	public static void main(String[] args) throws InterruptedException {

		JFrame f = new JFrame();
		Scanner kboard = new Scanner(System.in);
		System.out.println("Please input the filenames:");
		String fileName1 = kboard.next();
		String fileName2 = kboard.next();
		kboard.close();

		combineSoups(fileName1, fileName2);
		f.setSize(1850, 950);
		LifeFight l = new LifeFight();
		f.add(l);
		f.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	l.whoWon();
		        System.exit(0);
		    }
		});
		f.setVisible(true);
		
		while(true)
		{
			l.progresses();
			
			Thread.sleep(50);
			l.repaint();
		}
	}
/**
 * 
 * @return the number of the side that won. 0 = left and 1 = right and 2 = tie.
 */
	public int whoWon()
	{
		int greenTotal = 0;
		int redTotal = 0;
		int greenInvasion = 0;
		int redInvasion = 0;
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				if (soup[i][j] == 1)
				{
					greenTotal++;
					if (j >= columns/2)
						greenInvasion++;
				}
				else if (soup[i][j] == 2)
				{
					redTotal++;
					if (j < columns/2)
						redInvasion++;
				}
			}
		}
		System.out.println("Green total: " + greenTotal);
		System.out.println("Red total: " + redTotal);
		System.out.println("Green invasion total: " + greenInvasion);
		System.out.println("Red invasion total: " + redInvasion);
		String winner;
		if (greenInvasion > redInvasion)
			winner = "GREEN";
		else if (redInvasion > greenInvasion)
			winner = "RED";
		else
			winner = "TIE";
		System.out.println(".....and the winner is: " + winner + "!");
		if (winner == "GREEN")
			return 0;
		else if (winner == "RED")
			return 1;
		else
			return 2;
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect(0, 0, columns * cellSize, rows * cellSize);
		g.setColor(Color.green);
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < columns; c++)
			{
				if (soup[r][c] == 1)
				{
					g.setColor(Color.green);
					g.fillRect(cellSize*c, cellSize*r, cellSize-1, cellSize-1);
				}
				else if (soup[r][c] == 2)
				{
					g.setColor(Color.red);
					g.fillRect(cellSize*c, cellSize*r, cellSize-1, cellSize-1);
				}
					
			}
	}
	
	
	public static void combineSoups(String fileName1, String fileName2)
	{
		File file1 = new File(fileName1);
		Scanner input = null;
		try
		{
			System.out.println("Reading the first file: " + fileName1);
			input = new Scanner(file1);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Cannot open " + fileName1 + ".");
			System.exit(1);
		}
		
		int row = 0;
		int col = 0;
		try
		{
			rows = input.nextInt();  
			columns = input.nextInt()*2;
			System.out.println("First file.  NumRows: " + rows + ", NumCols: " + columns/2);
			soup = new int[rows][columns];
			
			for (row = 0;   row < rows;   row++) 
			{
				String rowString = input.next();
			
				for (col = 0; col < columns/2; col++)
				 	soup[row][col] = Integer.parseInt(rowString.charAt(col) + "");
			}
		}
		catch (Exception e)
		{
			System.out.print("Error creating soup with file1: " + e.toString());
			System.out.println("Error occurred at row: " + row + ", col: " + col);
		}
		input.close();
		Scanner input2 = null;
		File file2 = new File(fileName2);
		try
		{
			System.out.println("Reading the second file: " + fileName2);
			input2 = new Scanner(file2);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Cannot open " + fileName2 + ".");
			System.exit(1);
		}
		
		try
		{
			if (rows != input2.nextInt())
			{
				System.out.println("Error: number of rows does not match");
				System.exit(1);
			}
			if (columns != input2.nextInt()*2)
			{
				System.out.println("Error: number of columns does not match");
				System.exit(1);				
			}
			System.out.println("Second file.  NumRows: " + rows + ", NumCols: " + columns/2);
			
			for (row = 0;   row < rows;   row++) 
			{
				String rowString = input2.next();
				for (col = 0; col < columns/2; col++)
				 	soup[row][columns-col-1] = Integer.parseInt(rowString.charAt(col) + "")*2;
			}
		}
		catch (Exception e)
		{
			System.out.print("Error creating soup with file2: " + e.toString());
			System.out.println("Error occurred at row: " + row + ", col: " + col);
		}
		input2.close();
	}
	
	// Updates the soup according to the rules of Life
	public void progresses()
	{
		int[][] newGen = new int[rows][columns];
		for (int row = 0; row < rows; row++)
		{
			for (int col = 0; col < columns; col++)
			{
				int[] neighbors = countNeighbors(row, col);

				if (soup[row][col] == 1)
				{
					int difference = Math.abs(neighbors[0]-neighbors[1]);
					if (difference == 2 || difference == 3)
					{
						newGen[row][col] = 1;
					}
					else if (difference == 1 && neighbors[0] >= 2)
					{
						newGen[row][col] = 1;
					}
					
				}
				else if (soup[row][col] == 2)
				{
					int difference = Math.abs(neighbors[0]-neighbors[1]);
					if (difference == 2 || difference == 3)
					{
						newGen[row][col] = 2;
					}
					else if (difference == 1 && neighbors[1] >= 2)
					{
						newGen[row][col] = 2;
					}
					
				}
				else
				{
					if (neighbors[0] == 3 && neighbors[1] == 3)
					{
						if (Math.random() < 0.5)
							newGen[row][col] = 1;
						else
							newGen[row][col] = 2;
					}
					else if (neighbors[0] == 3)
					{
						newGen[row][col] = 1;
					}
					else if (neighbors[1] == 3)
					{
						newGen[row][col] = 2;
					}
				}
			}
		}
		soup = newGen;
	}
	
	private int[] countNeighbors(int row, int col)
	{
		int[] count = new int[2];
		for (int i = row-1; i < row+2; i++)
		{
			for (int j = col-1; j < col+2; j++)
			{
				if (i != row || j != col)
				{
					try
					{
						if (soup[i%rows][j%columns] == 1)
							count[0]++;
						else if (soup[i%rows][j%columns] == 2)
							count[1]++;
					}
					catch (ArrayIndexOutOfBoundsException e) {}
				}
			}
		}
		return count;
	}
	

}

