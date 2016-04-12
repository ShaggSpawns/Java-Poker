package main;

import java.util.ArrayList;

public class Player {
	private double chipCount;
	private int playTimes = 0; // TODO Save player data to file
	private String name;
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	Player(String name) {
		setName(name);
		setChipCount(3000);
	}
	
	Player(String name, double chipCount) {
		setName(name);
		setChipCount(chipCount);
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void clearHand() {
		cards.clear();
	}
	
	public double pay(double amount) {
		setChipCount(chipCount - amount);
		return amount;
	}
	public void addWinnings(double winnings) {
		setChipCount(chipCount + winnings);
	}
	
	public int getPlayTimes() {
		return playTimes;
	}
	
	public void setPlayTimes(int playTimes) {
		this.playTimes = playTimes;
	}
	
	public double getChipCount() {
		return chipCount;
	}
	//TODO Stop players from being able to enter in bets past their available balance
	private void setChipCount(double chipCount) {
		if (chipCount >= 0)
			this.chipCount = chipCount;
		else
			try {
				throw new Exception("Broke");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public String toString() {
		return name + " (" + chipCount + ") " + cards;
	}
}
