import java.io.*;
import java.util.*;

public class Solution 
{
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        
        for(int n = 0; n < N; n++) 
        {
            long input = scan.nextLong();
            long highest = Long.highestOneBit(input);
            long output = ((input & ~highest) << 1) + 1;
            System.out.println(output);            
        }
    }
}