import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String args[])
	{
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[][] square = new int[n][n];
		
		int[] colums = new int[n];
		int[] rows = new int[n];
		
		// Create square and sum rows and columns
		for (int i = 0; i < square.length; i++)
		{	
			for (int j = 0; j < square[i].length; j++)
			{
				square[i][j] = scanner.nextInt();
				rows[i] = rows[i] + square[i][j];
				colums[j] = colums[j]+ square[i][j];
			}
		}
		
		// Sum main-diagonal and anti-diagonal
		int main = 0;
		int anti = 0;
		
		for (int i=0,j=0; i<n; i++,j++) 
		{
			main = main+square[i][j];
			anti = anti+square[n-1-i][j];
		}
		
		// Get errors
		ArrayList<Integer> errors = new ArrayList<>();
		
		if(main != anti)
			errors.add(0);
		
		for (int i = 0; i < colums.length; i++) 
		{
			if(main != colums[i])
				errors.add(-i-1);
		}
		
		for (int i = 0; i < rows.length; i++) 
		{
			if(main != rows[i])
				errors.add(i+1);
		}
		
		// Print all to screen
		System.out.println(errors.size());
		errors.sort(new Comparator<Integer>() 
		{
			@Override
			public int compare(Integer o1, Integer o2) 
			{
				return o1-o2;
			}
		});
		
		for (Integer integer : errors) 
			System.out.println(integer);
	}
}