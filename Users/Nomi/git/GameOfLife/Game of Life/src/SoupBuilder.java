import java.io.*;
import java.util.*;
/**
 * Creates the PRIMORDIAL SOUP
 * @since Jan 21, 2016
 * @author Nomi Ringach
 * @version 1.0.0
 */
public class SoupBuilder 
{
	public static void main(String[] args) throws IOException
	{
		Scanner k = new Scanner(System.in);
		System.out.println("Please input \"ROWS COLUMNS FILENAME PROBABILITY-OF-LIFE\"");
		int rows = k.nextInt(), cols = k.nextInt();
		String filename = k.next();
		double probability = k.nextDouble();
		PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".psp")), true);
		write.println(rows + " " + cols);
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
				write.print((int)(Math.random()+probability));
			write.println();
		}
		k.close();
		write.close();
	}
}