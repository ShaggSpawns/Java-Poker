package main;
import java.util.Scanner;
import java.util.ArrayList;

//TODO Score Method, Chips, Betting, Orbit Count

public class Poker {
	public static String userInput;
	public static boolean playMore = true;
	public static Scanner kb = new Scanner(System.in);
	public static Board board;
	public static int numPlayers;
	public static ArrayList<Player> playerList = new ArrayList<Player>();
	public static boolean noBroke = true;
	
	public static void main(String[] args) {
		introSequence();
		
		while(board.getPlayers() > 1) {
			
		}
		
		endSequence();
	}
	
	public static void introSequence() {
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
		
	}
	
	public static void endSequence() {
		System.out.println("Game Over!");
	}
}