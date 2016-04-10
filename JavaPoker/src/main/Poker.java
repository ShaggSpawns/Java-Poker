package main;
import java.util.Scanner;
import java.util.ArrayList;

//TODO Score Method, Chips, Betting, Orbit Count

public class Poker {
	public static void main(String[] args) {
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		Board board;
		Scanner kb = new Scanner(System.in);
		String userInput;
		int numPlayers;
		boolean playMore = true;
		boolean noBroke = true;
		boolean p1 = false;
		boolean p2 = false;
		
		System.out.println("Would you like to play some POKER!");
		userInput = kb.nextLine().toUpperCase();
		
		if(userInput.equals("YES")) playMore = true;
		else playMore = false;
		
		System.out.println("Enter in number of players (2-9)");
		numPlayers = kb.nextInt();
		kb.nextLine();
		
		for(int i = 1; i < numPlayers+1; i++) {
			System.out.println("Enter in player " + i + "'s name:");
			playerList.add(new Player(kb.nextLine()));
		}
		
		board = new Board(playerList);
		
		while(noBroke) {
			for(int i = 0; i < playerList.size(); i++) {
				if(p1 == true)
					if(playerList.get(i).getChipCount() > 0)
						p2 = true;
				if(p1 == false)
					if(playerList.get(i).getChipCount() > 0)
						p1 = true;	
			}
			if(p2 != true) { 
				noBroke = false;
				break;
			}
		}
	}
}