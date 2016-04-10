package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Board {
	private ArrayList<Player> players;
	private ArrayList<Player> foldedPlayers = new ArrayList<Player>();
	private ArrayList<Card> shuffledCards;
	private ArrayList<Card> boardCards = new ArrayList<Card>();
	private double pot = 0.0;
	
	public Board(ArrayList<Player> players, double ante, int handCount) {
		this.players = players;
		shuffledCards = getShuffledCards();
		
		dealCards();
		payAnte(ante, handCount);
		for (int i = 3; i <= 5; i++) {
			showCards(i);
			getBet();
		}
		determineWinner();
	}
	
	private void dealCards() {
		for (int i = 0; i < 5; i++) {
			boardCards.add(shuffledCards.get(0));
			shuffledCards.remove(0);
		}
		for (Player p: players) {
			for (int i = 0; i < 2; i++) {
				p.addCard(shuffledCards.get(0));
				shuffledCards.remove(0);
			}
		}
	}
	
	private void payAnte(double ante, int handCount) {
		for (int i = 0; i < players.size(); i++) {
			if (i == handCount%players.size()) {
				pot += players.get(i).pay(ante * (players.size() - 1));
				if (i != 0)
					pot += players.get(i - 1).pay((ante * (players.size() - 1)) / 2);
				else
					pot += players.get(players.size() - 1).pay((ante * (players.size() - 1)) / 2);
			} else if (i != handCount%players.size() - 1) {
				if (handCount%players.size() != -1)
					pot += players.get(i).pay(ante);
			}
		}
	}
	
	private void showCards(int i) {
		for (int j = 0; j < i; j++)
			System.out.print(boardCards.get(j) + " ");
		System.out.println();
	}
	
	private void getBet() {
		Scanner scanner = new Scanner(System.in);
		String input;
		for (Player p: players) {
			if (!foldedPlayers.contains(p)) {
				System.out.print(p.getName() + " enter bet (or fold): ");
				input = scanner.nextLine().toUpperCase();
				if (input.equals("FOLD"))
					foldedPlayers.add(p);
				else
					pot += p.pay(Integer.parseInt(input));
			}
		}
		scanner.close();
	}
	
	private void determineWinner() {
		//TODO re-add folded players to player list
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
	
	public void printPot() {
		System.out.println("Pot: " + pot);
	}
}
