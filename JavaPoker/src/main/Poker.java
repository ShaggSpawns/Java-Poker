package main;
import java.util.Scanner;

public class Poker {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		String userInput;
		boolean playMore = true;
		
		while(playMore) {
			System.out.println("Would you like to play some POKER!");
			userInput = kb.nextLine().toUpperCase();
			
			if(userInput.equals("YES"))
				playMore = true;
			else {
				playMore = false;
				break;
			}
			
			PokerUtils.shuffle();
		
		
		
		
		
		
		
			//TODO input parameters
			PokerUtils.score();
		}
	}
}