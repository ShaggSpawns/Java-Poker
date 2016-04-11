package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Board {
	private static int handsPlayed = 0;
	private static double ante =  0.0;
	private ArrayList<Player> players;
	private ArrayList<Player> foldedPlayers = new ArrayList<Player>();
	private ArrayList<Card> shuffledCards;
	private ArrayList<Card> boardCards = new ArrayList<Card>();
	private double pot = 0.0;
	
	public Board(ArrayList<Player> players, double ante) {
		Board.ante = ante;
		new Board(players);
	}
	public Board(ArrayList<Player> players) {
		this.players = players;
		shuffledCards = getShuffledCards();
		if(++handsPlayed % 46 == 0) {
			ante += 50;
			handsPlayed = 0;
		}
		
		dealCards();
		payAnte();
		for (int i = 3; i <= 5; i++) {
			showCards(i);
			getBet(Poker.kb);
		}
		determineWinner();
		removePlayerCards();
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
	
	private void payAnte() {
		for (int i = 0; i < players.size(); i++) {
			if (i == handsPlayed % players.size()) {
				pot += players.get(i).pay(ante * (players.size() - 1));
				if (i != 0)
					pot += players.get(i - 1).pay((ante * (players.size() - 1)) / 2);
				else
					pot += players.get(players.size() - 1).pay((ante * (players.size() - 1)) / 2);
			} else if (i != handsPlayed % players.size() - 1) {
				if (handsPlayed % players.size() != -1)
					pot += players.get(i).pay(ante);
			}
		}
	}
	
	private void showCards(int i) {
		for (int j = 0; j < i; j++)
			System.out.print(boardCards.get(j) + " ");
		System.out.println();
	}
	
	private void getBet(Scanner scanner) {
		String input = "";
		for (Player p: players) {
			if (!foldedPlayers.contains(p)) {
				System.out.print(p + " enter bet (or fold): ");
				input = scanner.nextLine().toUpperCase();
				if (input.equals("FOLD"))
					foldedPlayers.add(p);
				else
					pot += p.pay(Integer.parseInt(input));
			}
		}
	}
	
	private void determineWinner() {
		int lowestScore = 10;
		ArrayList<Player> winners = new ArrayList<Player>();
		PokerUtils pokerUtils = new PokerUtils(boardCards);
		for (Player p: players) {
			if (!foldedPlayers.contains(p)) {
				int score = pokerUtils.score(p.getCards());
				if (score < lowestScore) {
					winners.removeAll(winners);
					winners.add(p);
					lowestScore = score;
				} else if (score == lowestScore) {
					winners.add(p);
				}
			}
		}
		if (winners.size() != 1)
			PokerUtils.determineWinner(winners, lowestScore);
		System.out.println("\n" + boardCards);
		for (Player p: winners) {
			p.addWinnings(pot / winners.size());
			System.out.println(p + " WON " + (pot / winners.size()) + " [" + lowestScore + "]");
		}
	}
	
	private void removePlayerCards() {
		for (Player p: players) {
			p.clearHand();
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
	
	public void printPot() {
		System.out.println("Pot: " + pot);
	}
}
