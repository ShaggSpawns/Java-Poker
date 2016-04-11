package main;
import java.util.Scanner;
import java.util.ArrayList;

public class Poker {
	public static Scanner kb = new Scanner(System.in);
	public static Board board;
	public static ArrayList<Player> playerList = new ArrayList<Player>();
	public static final double INITIAL_ANTE = 100;
	
	public static void main(String[] args) {
		String input = null;
		do {
			System.out.print("Enter player name (Enter to escape): ");
			input = kb.nextLine();
			if (!input.equals(""))
				playerList.add(new Player(input));
		} while (!input.equals("") && playerList.size() < 9);
		board = new Board(playerList, INITIAL_ANTE);
		
		// Continue to play until one player remains
		while(playerList.size() > 1) {
			System.out.println("\nNEW GAME\n");
			board = new Board(playerList);
		}
		
		System.out.println("Game Over!");
		System.out.println("Player " + playerList.get(0) + " WINS!");
		kb.close();
	}
}