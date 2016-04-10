package main;

public class Card {
	private Rank rank;
	private Suit suit;
	
	Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public String toString() {
    	return rank.toString() + "(" + suit.toString() + ")";
    }
}
