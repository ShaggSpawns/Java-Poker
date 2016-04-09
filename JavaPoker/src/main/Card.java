package main;

public class Card{
	
	private Rank value;
	private Suit suit;
	
	Card(Rank value, Suit suit) {
		this.value = value;
		this.suit = suit;
	}
	
	public Rank getValue() {
		return value;
	}
	
	public Suit getSuit() {
		return suit;
	}
}
