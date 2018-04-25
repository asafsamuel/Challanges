import java.io.*;
import java.util.*;

public class Solution 
{
    public static void main(String[] args) 
    {
    	Scanner scanner = new Scanner(System.in);
    	int[] arr = new int[3];
    	
    	for (int i = 0; i < 3; i++) 
			arr[i] = scanner.nextInt();
    	
    	ArrayList<Integer> indexes = new ArrayList<>();
    	if(printSteps(arr, 0, indexes) == 12)
    		System.out.println("Ok");
    	
    	else
    		printWays(arr,indexes);
	}

	private static void printWays(int[] arr2, ArrayList<Integer> indexes) 
	{
		System.out.println(arr2[0]+" "+arr2[1]+" "+arr2[2]);
		for (int i = indexes.size()-1; i >=0; i--) 
		{
			switch (indexes.get(i)) 
			{
			case 0:
				arr2[1] = arr2[1]-arr2[0];
				arr2[0] = arr2[0]*2;
				System.out.println(arr2[0]+" "+arr2[1]+" "+arr2[2]);
				break;
				
			case 1:
				arr2[2] = arr2[2] - arr2[0];
				arr2[0] = arr2[0]*2;
				System.out.println(arr2[0]+" "+arr2[1]+" "+arr2[2]);
				break;
				
			case 2:
				arr2[0] = arr2[0]-arr2[1];
				arr2[1] = arr2[1]*2;
				System.out.println(arr2[0]+" "+arr2[1]+" "+arr2[2]);
				break;
				
			case 3:
				arr2[2] = arr2[2]-arr2[1];
				arr2[1] = arr2[1]*2;
				System.out.println(arr2[0]+" "+arr2[1]+" "+arr2[2]);
				break;
				
			case 4:
				arr2[0] = arr2[0]-arr2[2];
				arr2[2] = arr2[2]*2;
				System.out.println(arr2[0]+" "+arr2[1]+" "+arr2[2]);
				break;
				
			case 5:
				arr2[1] = arr2[1] - arr2[2];
				arr2[2] = arr2[2]*2;
				System.out.println(arr2[0]+" "+arr2[1]+" "+arr2[2]);
				break;
			}
		}
	}

	private static int printSteps(int[] array, int count, ArrayList<Integer> indexes) 
	{
		if(count > 11)
			return 12;
		
		if(array[0] == 0 || array[1] == 0 || array[2] == 0)
			return count;

		int[] arr1 = new int[3];
		int[] arr2 = new int[3];
		int[] arr3 = new int[3];
		int[] arr4 = new int[3];
		int[] arr5 = new int[3];
		int[] arr6 = new int[3];
		
		ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
		temp.add(new ArrayList<>());
		temp.add(new ArrayList<>());
		temp.add(new ArrayList<>());
		temp.add(new ArrayList<>());
		temp.add(new ArrayList<>());
		temp.add(new ArrayList<>());
		
		int One = 12,Two = 12,Third = 12,Forth = 12,Five = 12,Six = 12;
		
		// 1 win 2
		arr1[0] = array[0]*2;
		arr1[1] = array[1]-array[0];
		arr1[2] = array[2];
		
		if(arr1[1] >= 0)
			One = printSteps(arr1, count+1, temp.get(0));
		
		// 1 win 3
		arr2[0] = array[0]*2;
		arr2[1] = array[1];
		arr2[2] = array[2] - array[0];
		
		if(arr2[2] >= 0)
			Two = printSteps(arr2, count+1, temp.get(1));
		
		// 2 win 1
		arr3[0] = array[0]-array[1];
		arr3[1] = array[1]*2;
		arr3[2] = array[2];
		
		if(arr3[0] >= 0)
			Third = printSteps(arr3, count+1, temp.get(2));
		
		// 2 win 3
		arr4[0] = array[0];
		arr4[1] = array[1]*2;
		arr4[2] = array[2]-array[1];
		
		if(arr4[2] >= 0)
			Forth = printSteps(arr4, count+1, temp.get(3));
		
		// 3 win 1
		arr5[0] = array[0]-array[2];
		arr5[1] = array[1];
		arr5[2] = array[2]*2;
		
		if(arr5[0] >= 0)
			Five = printSteps(arr5, count+1, temp.get(4));
		
		// 3 win 2
		arr6[0] = array[0];
		arr6[1] = array[1] - array[2];
		arr6[2] = array[2]*2;
		
		if(arr6[1] >= 0)
			Six = printSteps(arr6, count+1, temp.get(5));
		
		// Find min and way
		int min = One;
		int index = 0;
		
		if(min > Two)
		{
			min = Two;
			index = 1;
		}
		
		if(min > Third)
		{
			min = Third;
			index = 2;
		}
		
		if(min > Forth)
		{
			min = Forth;
			index = 3;
		}
		
		if(min > Five)
		{
			min = Five;
			index = 4;
		}
		
		if(min > Six)
		{
			min = Six;
			index = 5;
		}
		
		for (Integer i : temp.get(index)) 
			indexes.add(i);
		
		indexes.add(index);
		return min;
	}
}
