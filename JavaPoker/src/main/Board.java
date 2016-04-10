package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board {
	private ArrayList<Card> cards;
	private ArrayList<Player> players;
	
	public Board(Player[] players) {
		this.players = new ArrayList<Player>(Arrays.asList(players));
		cards = new ArrayList<Card>(getBoardCards());
	}
	
	private ArrayList<Card> getBoardCards() {
		ArrayList<Card> shuffled = new ArrayList<Card>();
		for (Rank r: Rank.values()) {
			for (Suit s: Suit.values()) {
				shuffled.add(new Card(r, s));
			}
		}
		Collections.shuffle(shuffled);
		return shuffled;
	}
}
