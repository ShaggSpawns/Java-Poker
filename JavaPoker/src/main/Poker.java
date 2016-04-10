package main;
import java.util.Scanner;
import java.util.ArrayList;

//TODO Score Method, Chips, Betting, Orbit Count

public class Poker {
	public static String userInput;
	public static int numHandsPlayed = 1;
	public static int bigBlind, smallBlind;
	public static boolean playMore = true;
	public static Scanner kb = new Scanner(System.in);
	public static Board board;
	public static ArrayList<Player> playerList = new ArrayList<Player>();
	public static double ANTE = 100; //Antes change based on number of hands that have been played. There are also Big Blinds and Small Blinds
								     //Big blinds in the opening (ante:100) will be 800 (9 players playing, 8 players ante'd)
									 //Small blinds are always half the Big blind. So opening will be (small) 400/800 (big) ante 100
	
	public static void main(String[] args) {
		while(playMore) {
			introSequence();
			while(playerList.size() > 1) {
				board = new Board(playerList, ANTE, numHandsPlayed);
				
				numHandsPlayed++;
				
				//TODO move this to board class and make players pay these amounts every 8 rounds. IE. Round 1, player 1 pays BB. Player 9 pays SB
				//players 2-8 pay ante. Round 2, Player 2 pays BB, Player 1 pays SB, others pay ante. Etc.
				if(numHandsPlayed%46 == 0) {
					ANTE += 50;
					numHandsPlayed = 1;
				}
				bigBlind = (int) (ANTE/playerList.size()-1);
				smallBlind = bigBlind/2;
			}
			endSequence();
		}
		kb.close();
	}
	
	public static void introSequence() {
		int numPlayers;
		System.out.println("Would you like to play some POKER!");
		userInput = kb.nextLine().toUpperCase();
		playMore = userInput.equals("YES");
		
		System.out.println("Enter in number of players (2-9)");
		numPlayers = kb.nextInt();
		kb.nextLine();
		
		for(int i = 1; i < numPlayers + 1; i++) {
			System.out.println("Enter in player " + i + "'s name:");
			playerList.add(new Player(kb.nextLine()));
		}
	}
	
	public static void endSequence() {
		System.out.println("Game Over!");
		System.out.println("Player " + playerList.get(0) + " WINS!");
		
		System.out.println("Would you like to play again?");
		userInput = kb.nextLine();
		playMore = userInput.equals("YES");
	}
}