package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
//TODO add in section where if player's bet == player's totalChipCount && another player calls, original player to bet (the all-inner) auto-shows
//cards, even if other players are still betting against on another. I.E.
//4 player game.
//Name(TotalChipCount) Action: Amount
//P1(1500) BET: 1500 (All In), P2(2500) BET:1500 (Call), P1 shows hand, P3(4500) FOLD, P4(3200) BET: 1500, 
//First three cards shown on board
//P1(0) NOTHING (Cards still shown), P2(1000) CHECK: 0, P4(1700) BET:1000, P1(0) NOTHING (Cards still shown), P2(1000) CALL:1000 (All In)
//All Players Show hands
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
		determineAnte();
		removePlayers();
		payAnte();
		dealCards();
		play();
		determineWinner();
		removePlayerCards();
	}
	
	private void determineAnte() {
		if(++handsPlayed % 46 == 0) {
			ante += 50;
			handsPlayed = 0;
		}
	}
	
	private void removePlayers() {
		for (Player p: players) {
			if (p.getChipCount() < ante)
				players.remove(p);
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
	
	private void play() {
		for (int i = 2; i <= 5; i++) {
			if (foldedPlayers.size() == players.size() - 1)
				break;
			if(i > 2)
				showCards(i);
			getBet(Poker.kb);
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
				else {
					if(input.equals(""))
						pot += p.pay(0);
					else
						pot += p.pay(Integer.parseInt(input));
				}
			}
		}
	}
	
	private void determineWinner() {
		int lowestScore = 10;
		ArrayList<Player> winners = new ArrayList<Player>();
		PokerUtils pokerUtils = new PokerUtils(boardCards);
		for (Player p: players) {
			//Error found here after running game (122)
			if (!foldedPlayers.contains(p)) {
				int score = pokerUtils.score(p.getCards());
				if (score < lowestScore) {
					winners.clear();
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
