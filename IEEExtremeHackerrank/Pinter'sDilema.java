import java.io.*;
import java.util.*;

public class Solution 
{
	static HashMap<String, Integer> map = new HashMap<>();
	
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		
		while(t != 0)
		{
			int n = scanner.nextInt();
			int[] arr = new int[n];
			
			for (int i = 0; i < n; i++) 
				arr[i] = scanner.nextInt();
			
			System.out.println(getResult(n-1, arr, 0,0));
			map.clear();
			t--;
		}
	}

	private static int getResult(int n, int[] arr, int FB, int SB)
	{
		String thisS = n+"|"+arr.toString()+"|"+FB+"|"+SB;
		
		if(n == 0)
		{
			if(arr[0] == FB || arr[0] == SB)
			{
				map.put(thisS, 0);
				return 0;
			}
			
			map.put(thisS, 1);
			return 1;
		}
		
		int oneWay;
		int secWay;
		
		if(arr[n] == FB)
		{
			String s = n-1+"|"+arr.toString()+"|"+arr[n]+"|"+SB;
			if(map.containsKey(s))
			{
				map.put(thisS, map.get(s));
				return map.get(s);
			}
			
			int x = getResult(n-1, arr, arr[n], SB);
			map.put(thisS, x);
			return x;
		}
		
		if(arr[n] == SB)
		{
			String s = n-1+"|"+arr.toString()+"|"+FB+"|"+arr[n];
			if(map.containsKey(s))
			{
				map.put(thisS, map.get(s));
				return map.get(s);
			}
			
			int x = getResult(n-1, arr, FB, arr[n]);
			map.put(thisS, x);
			return x;
		}
        
		
		String s1 = n-1+"|"+arr.toString()+"|"+arr[n]+"|"+SB;
		String s2 = n-1+"|"+arr.toString()+"|"+FB+"|"+arr[n];
		
		if(map.containsKey(s1))
			oneWay = map.get(s1)+1;
		
		else
			oneWay = getResult(n-1, arr, arr[n], SB) + 1;
		
		if(map.containsKey(s2))
			secWay = map.get(s2)+1;
		
		else
			secWay =  getResult(n-1, arr, FB, arr[n]) + 1;
		
		int min = Math.min(oneWay, secWay);
		map.put(thisS, min);
		return min;
	}
}