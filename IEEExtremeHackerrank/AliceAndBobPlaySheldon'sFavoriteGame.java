import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution 
{
	public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		Map<String,Integer> translator = new HashMap<>();
		
		// Initialize map
		translator.put("Scissors", 0);
		translator.put("Paper", 1);
		translator.put("Rock", 2);
		translator.put("Lizard", 3);
		translator.put("Spock", 4);
		
		// Get inputs from the user
		int q = scanner.nextInt();
		
		while(q > 0)
		{
			int aliceShape = translator.get(scanner.next());
			int bobShape = translator.get(scanner.next());
			BigInteger times = new BigInteger(scanner.next());
			
			singleGame(aliceShape, bobShape, times);
			q--;
		}
	}
		
	public static void singleGame(Integer alice, Integer bob, BigInteger times)
	{
		int change=0;
		
		if(bob==4)
			change=1;
		
		times.add(new BigInteger("1"));
		
		List<String> pastGames = new ArrayList<>();
		List<Integer> results = new ArrayList<>();
		String currentGame = alice.toString() + "," + bob.toString();
		
		Integer end = 0;
		Integer start = 0;
		
		while((start = pastGames.indexOf(currentGame)) == -1)
		{
			pastGames.add(currentGame);
				
			if(alice == bob)	// Tie
			{
				alice = (alice+4)%5;
					
				if((end+change)%2==0)
					bob = 4;
					
				else
					bob =3;
					
				results.add(0);
			}
				
			else if(bob == (alice+1)%5 || bob == (alice+3)%5)	// Alice wins
			{
				if((end+change)%2==0) 
					bob = 4;
					
				else
					bob = 1;
					
				results.add(1);
			}
				
			else	// Bob wins
			{
				alice = (bob+4)%5;
				
				if((end+change)%2==0)
					bob = 4;
				
				else 
					bob = 2;
					
				results.add(-1);
			}
				
			currentGame = alice.toString()+","+bob.toString();
			end++;
		}
		
		if(times.compareTo(new BigInteger(end.toString()))<0)
		{
			int sumAlice = 0;
			int sumTie = 0;
			int sumBob=0;
			
			for (int i = 0; i < times.intValue(); i++)
			{
				if(results.get(i)==0)
					sumTie++;
				
				else if(results.get(i)==1)
					sumAlice++;
				
				else if(results.get(i)==-1)
					sumBob++;
			}
				
			if(sumAlice==sumBob)
				System.out.println("Alice and Bob tie, each winning "+sumAlice+" game(s) and tying "+sumTie+" game(s)");
			
			else if(sumAlice>sumBob)
				System.out.println("Alice wins, by winning "+sumAlice+" game(s) and tying "+sumTie+" game(s)");
			
			else 
				System.out.println("Bob wins, by winning "+sumBob+" game(s) and tying "+sumTie+" game(s)");
			
			return;
		}
		
		BigInteger circleSize = new BigInteger(new Integer(end-start).toString());
		BigInteger one = new BigInteger("1");
		BigInteger sumAliceCircle = new BigInteger("0");
		BigInteger sumBobCircle = new BigInteger("0");
		BigInteger sumTieCircle = new BigInteger("0");
		
		for (int i = start; i < end; i++) 
		{
			if(results.get(i)==0)
				sumTieCircle = sumTieCircle.add(one);
			
			else if(results.get(i)==1)
				sumAliceCircle = sumAliceCircle.add(one);
			
			else if(results.get(i)==-1)
				sumBobCircle = sumBobCircle.add(one);
		}
		
		// Temp = start - ((times-start)%circleSize)/circleSize
		BigInteger temp = (times.subtract(new BigInteger(start.toString())).subtract((times.subtract(new BigInteger(start.toString()))).mod(circleSize))).divide(circleSize);
		sumTieCircle = sumTieCircle.multiply(temp);
		sumAliceCircle = sumAliceCircle.multiply(temp);
		sumBobCircle = sumBobCircle.multiply(temp);
			
		for (int i = 0; i < start; ++i) 
		{
			if(results.get(i)==0)
				sumTieCircle = sumTieCircle.add(one);
			
			else if(results.get(i)==1)
				sumAliceCircle = sumAliceCircle.add(one);
			
			else if(results.get(i)==-1)
				sumBobCircle = sumBobCircle.add(one);
		}
			
		for (int i = start; i < start + (times.subtract(new BigInteger(start.toString())).mod(circleSize)).intValue(); ++i) 
		{
			if(results.get(i)==0)
				sumTieCircle = sumTieCircle.add(one);
			
			else if(results.get(i)==1)
				sumAliceCircle = sumAliceCircle.add(one);
			
			else if(results.get(i)==-1)
				sumBobCircle = sumBobCircle.add(one);
		}
			
		if(sumAliceCircle.compareTo(sumBobCircle) == 0) 
			System.out.println("Alice and Bob tie, each winning "+sumAliceCircle+" game(s) and tying "+sumTieCircle+" game(s)");
		
		else if(sumAliceCircle.compareTo(sumBobCircle) > 0)
			System.out.println("Alice wins, by winning "+sumAliceCircle+" game(s) and tying "+sumTieCircle+" game(s)");
		
		else 
			System.out.println("Bob wins, by winning "+sumBobCircle+" game(s) and tying "+sumTieCircle+" game(s)");
		
		return;
	}
}