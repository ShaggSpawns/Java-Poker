package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Board {
	private ArrayList<Player> players;
	private ArrayList<Card> shuffledCards;
	private ArrayList<Card> boardCards = new ArrayList<Card>();
	private double pot = 0.0;
	
	public Board(ArrayList<Player> players, double ante) {
		this.players = players;
		shuffledCards = getShuffledCards();
		
		
		dealCards();
		payAnte(ante);
		for (int i = 0; i < 3; i++) {
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
	
	private void payAnte(double ante) {
		for (Player p: players) {
			pot += p.pay(ante);
		}
			
	}
	
	private void showCards(int i) {
		
	}
	
	private void getBet() {
		Scanner scanner = new Scanner(System.in);
		String input;
		for (Player p: players) {
			System.out.print(p.getName() + " enter bet (or fold): ");
			input = scanner.nextLine().toUpperCase();
			if (!input.equals("FOLD")) {
				pot += p.pay(Integer.parseInt(input));
			}
		}
		scanner.close();
	}
	
	private void determineWinner() {
		
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
