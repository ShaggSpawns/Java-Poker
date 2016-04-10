package main;

public class Card implements Comparable<Card> {
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
	
	public int compareTo(Card other) {
		if (this.rank == other.rank && this.suit == other.suit)
			return 0;
		return this.rank.getValue() - other.rank.getValue();
	}
	
	@Override
	public boolean equals(Object obj) {
		Card other = (Card)obj;
		return this.compareTo(other) == 0;
	}
}
