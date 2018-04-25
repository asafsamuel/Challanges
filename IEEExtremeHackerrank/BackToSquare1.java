import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution 
{  
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
        ArrayList<Double> probability = new ArrayList<>();
        int n = scanner.nextInt();
		
		while(n != 0)
        {
			for(int i=0; i<n-1; i++)
				probability.add(scanner.nextDouble());
			
			float now = 1;
			float ans = 1;
			
			for(int i = probability.size()-1; i>=0; i--)
			{
				now /= probability.get(i);
				ans += now;
			}
			
			System.out.println(Math.round(ans));
			n = scanner.nextInt();
            probability.clear();
		}
	}
}
