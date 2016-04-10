package main;

public enum Suit {
	Heart("Heart"), Diamond("Diamond"), Club("Club"), Spade("Spade");
	
	private String name;
	Suit(String name) {
		this.name = name;
	}
	public String toString() {
		return name;
	}
}
