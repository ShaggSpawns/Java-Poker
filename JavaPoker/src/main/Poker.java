package main;
import java.util.Scanner;
import java.util.ArrayList;

//TODO Score Method, Chips, Betting, Orbit Count

public class Poker {
	public static void main(String[] args) {
		
		ArrayList<Card> board = new ArrayList<Card>();
		ArrayList<Player> playerList = new ArrayList<Player>();
		Scanner kb = new Scanner(System.in);
		String userInput;
		int numPlayers;
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
			
			System.out.println("Enter in number of players (2-9)");
			numPlayers = kb.nextInt();
			
			for(int i = 0; i < numPlayers; i++) {
				System.out.println("Enter in player " + i + "'s name:");
				playerList.add(new Player(kb.nextLine()));
			}
			new Board(playerList);
			
			//TODO input parameters
			PokerUtils.score();
		}
	}
}