import java.awt.*;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;
/**
 * 
 * @author Nomi
 *
 */
public class Life extends JPanel {

	private static final long serialVersionUID = -1861382120217385512L;
	public static int[][] soup;
	public static int cellSize = 11;
	public static int rows;
	public static int columns;
	public static void main(String[] args) throws InterruptedException, FileNotFoundException {

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
//		if (!fileName.endsWith(".psp"))
//		{
//			return;
//		}
		readSoup(fileName);
		f.setSize(1000, 1000);
		Life l = new Life();
		f.add(l);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		while(true)
		{
			l.progresses();

			Thread.sleep(100);
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
	public static void readSoup(String fileName) throws FileNotFoundException
	{
		Scanner read = new Scanner(new BufferedReader(new FileReader(fileName)));
		rows = read.nextInt();
		columns = read.nextInt();
		soup = new int[rows][columns];
		read.nextLine();
		for (int r = 0; r < rows; r++)
		{
			String nextLine = read.nextLine();
			for (int c = 0; c < columns; c++)
				soup[r][c] = Integer.parseInt(nextLine.charAt(c) + "");
		}
		read.close();
	}

	// Updates the soup according to the rules of Life
	public void progresses()
	{
		int[][] progress = new int[rows][columns];
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < columns; c++)
			{
				int surroundingLife = getSurroundingLife(r,c);
				if(soup[r][c] == 1)									//if the cell is alive
					if (surroundingLife < 2 || surroundingLife >= 4)
						progress[r][c] = 0;
					else
						progress[r][c] = 1;
				else												//if the cell is dead
					if (surroundingLife == 3)
						progress[r][c] = 1;
					else
						progress[r][c] = 0;
			}
		soup = progress;
	}

	private int getSurroundingLife(int row, int col)
	{
		int life = 0;
		for (int c = col-1; c <= col+1; c++)
			life += getValue(row-1,c) + getValue(row+1,c);
		life += getValue(row,col-1) + getValue(row,col+1);
		return life;
	}

	private int getValue(int row, int col)
	{
		if (row >= 0 && row < rows && col >= 0 && col < columns)
			return soup[row][col];
		return 0;
	}
}