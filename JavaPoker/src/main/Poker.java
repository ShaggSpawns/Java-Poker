package main;
import java.util.Scanner;
import java.util.ArrayList;

public class Poker {
	public static Scanner kb = new Scanner(System.in);
	public static Board board;
	public static ArrayList<Player> playerList = new ArrayList<Player>();
	public static final double INITIAL_ANTE = 100;
	private static final boolean DEBUG = true;
	
	public static void main(String[] args) {
		if (!DEBUG) {
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
		} else {
			playerList.add(new Player("Bob"));
			playerList.add(new Player("Joe"));
			ArrayList<Card> boardCards = new ArrayList<Card>(getBoardCards());
			playerList.get(0).addCard(new Card(Rank.Ten, Suit.Club));
			playerList.get(0).addCard(new Card(Rank.Three, Suit.Club));
			playerList.get(1).addCard(new Card(Rank.Ten, Suit.Club));
			playerList.get(1).addCard(new Card(Rank.Ace, Suit.Club));
			
			int lowestScore = 10;
			ArrayList<Player> winners = new ArrayList<Player>();
			PokerUtils pokerUtils = new PokerUtils(boardCards);
			for (Player p: playerList) {
				int score = pokerUtils.score(p.getCards());
				if (score < lowestScore) {
					winners.clear();
					winners.add(p);
					lowestScore = score;
				} else if (score == lowestScore) {
					winners.add(p);
				}
			}
			//if (winners.size() != 1)
				//PokerUtils.determineWinner(winners, lowestScore);
			System.out.println("\n" + boardCards);
			for (Player p: winners) {
				System.out.println(p + " WON " + " [" + lowestScore + "]");
			}
		}
	}
	
	private static ArrayList<Card> getBoardCards() {
		ArrayList<Card> boardCards = new ArrayList<Card>();
		boardCards.add(new Card(Rank.Ace, Suit.Club));
		boardCards.add(new Card(Rank.King, Suit.Club));
		boardCards.add(new Card(Rank.Queen, Suit.Club));
		boardCards.add(new Card(Rank.Jack, Suit.Club));
		boardCards.add(new Card(Rank.Three, Suit.Club));
		return boardCards;
	}
}