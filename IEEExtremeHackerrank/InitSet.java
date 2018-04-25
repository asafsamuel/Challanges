import java.io.*;
import java.util.*;

public class Solution 
{
   public static void main(String[] args) 
	{
		// Get number of question = Q
	    Scanner console = new Scanner(System.in);
	    int queries = Integer.parseInt(console.nextLine());

	    for (int i = 0; i < queries; i++) 
	    {
	        long N = console.nextLong();
	        long A = console.nextLong();
	        long B = console.nextLong();

	        long result = sumOfCoprimeNumbersInRange(A, B, N);
	        System.out.println(result);
	    }
	}
	
	// Calculate the sum (modulo MOD) of all numbers in the range a...b which are coprime to N.
	private static long sumOfCoprimeNumbersInRange(long a, long b, long n) 
	{
	    List<Long> primes = primeFactors(n);
	    long sum = inclExcl(a, b, 1, primes) % 1000000007;
	    
	    if (sum < 0) 
	        sum += 1000000007;
	    
	    return sum;
	}
	
	// Calculate all the numbers from 1 to k, computed modulo MOD [series]
	private static long sumUpTo(long k) 
	{
	    k = k % 1000000007; // Otherwise can be overflow.
	    return (k * (k+1) / 2) % 1000000007;
	}
	
	// Sum of all numbers (modulo MOD) in a...b which are divisible by k, but not by any multiple of k with factors in the primes list
	private static long inclExcl(long a, long b, long k, List<Long> primes) 
	{
	    long s1 = sumUpTo(b/k)*k;	// Sum of all numbers in 1...b which are divisible by k
	    long s2 = sumUpTo((a-1) / k) * k;	// Sum of all numbers in 1...(a-1) which are divisible by k
	    long sum = (s1 - s2) % 1000000007;

	    for (int index = 0; index < primes.size(); index++) 
	        sum = (sum - inclExcl(a, b, k * primes.get(index), primes.subList(index + 1, primes.size()))) % 1000000007;

	    return sum;
	}
	
	// List of all prime factors of n.
	private static List<Long> primeFactors(long n) 
	{
	    List<Long> factors = new ArrayList<Long>();
	    long md = 2;
	    
	    if (n % md == 0) 
	    {
	        factors.add(md);
	        
	        do 
	        {
	            n /= md;
	        } while (n % md == 0);
	    }
	    
	    md = 3;
	    
	    while (md <= java.lang.Math.sqrt(n) + 1) 
	    {
	        if (n % md == 0) 
	        {
	            factors.add(md);
	            
	            do 
	            {
	                n /= md;
	            } while (n % md == 0);
	        }
	        
	        md += 2;
	    }
	    
	    if (n > 1) 
	        factors.add(n);
	    
	    return factors;
	}
}