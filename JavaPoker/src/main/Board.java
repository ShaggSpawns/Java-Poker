package main;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
	private ArrayList<Player> players;
	private ArrayList<Card> shuffledCards;
	private ArrayList<Card> boardCards;
	
	public Board(ArrayList<Player> players) {
		this.players = players;
		shuffledCards = new ArrayList<Card>(getShuffledCards());
		for (int i = 0; i < 5; i++) {
			boardCards.add(shuffledCards.get(0));
			shuffledCards.remove(0);
		}
		
	}
	
	private ArrayList<Card> getShuffledCards() {
		ArrayList<Card> shuffled = new ArrayList<Card>();
		for (Rank r: Rank.values()) {
			for (Suit s: Suit.values()) {
				shuffled.add(new Card(r, s));
			}
		}
		Collections.shuffle(shuffled);
		return shuffled;
	}
	
	public void printPlayers() {
		System.out.println(players);
	}
}
