import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Life extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1861382120217385512L;
	public static int[][] soup;
	public static int cellSize = 11;
	public static int rows;
	public static int columns;
	public static void main(String[] args) throws InterruptedException {

		JFrame f = new JFrame();
//		int[][] startSoup = {{0,0,0,0, 0, 0, 0},
//				{0,1,1,1,0, 0, 0},
//				{0,0,0,0,0, 0, 0},
//				{0,0,0,0, 0, 0, 0},
//				{0,0,0,0, 0, 0, 0},
//				{0,0,0,0, 1, 1, 0},
//				{0,0,0,0, 1, 1, 0}};
//		soup = startSoup;
//		rows = 7;
//		columns = 7;
		Scanner kboard = new Scanner(System.in);
		System.out.println("Please input the filename:");
		String fileName = kboard.nextLine();
		kboard.close();
		if (!fileName.endsWith(".soup"))
		{
			return;
		}
		readSoup(fileName);
		f.setSize(1000, 1000);
		Life l = new Life();
		f.add(l);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		while(true)
		{
			l.progresses();
			
			Thread.sleep(600);
			l.repaint();
		}
	}


	public void paint(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect(0, 0, columns * cellSize, rows * cellSize);
		g.setColor(Color.green);
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < columns; c++)
			{
				if (soup[r][c] != 0)
					g.fillRect(cellSize*c, cellSize*r, cellSize, cellSize);
			}
	}
	
	// Opens file fileName and puts the soup described inside it into the soup array.
	// Also sets rows and columns to proper values.
	public static void readSoup(String fileName)
	{
		// YOUR CODE HERE
	}
	
	// Updates the soup according to the rules of Life
	public void progresses()
	{
		// YOUR CODE HERE
	}

}

