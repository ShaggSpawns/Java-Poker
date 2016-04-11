package main;

import java.util.ArrayList;

public class PokerUtils {
	ArrayList<Card> boardCards;
	ArrayList<Card> playerCards;
	ArrayList<Card> allCards;
	
	public PokerUtils(ArrayList<Card> boardCards) {
		this.boardCards = new ArrayList<Card>(boardCards);
	}
	
	public int score(ArrayList<Card> playerCards) {
		this.playerCards = new ArrayList<Card>(playerCards);
		allCards = new ArrayList<Card>(boardCards);
		allCards.addAll(playerCards);
		boolean flush = checkFlush();
		if (flush) {
			if (checkRoyalFlush())
				return 1;
			else if (checkStraightFlush())
				return 2;
		}
		if (checkFourOfKind())
			return 3;
		else if (checkFullHouse())
			return 4;
		else if (flush)
			return 5;
		else if (checkStraight())
			return 6;
		else if (checkThreeOfKind())
			return 7;
		else if (checkTwoPair())
			return 8;
		else if (checkPair())
			return 9;
		else
			return 10;
	}

	private boolean checkFlush() {
		int numHeart = 0;
		int numDiamond = 0;
		int numClub = 0;
		int numSpade = 0;
		for (Card p: playerCards) {
			for (Card b: boardCards) {
				if (p.getSuit() == b.getSuit()) {
					switch (p.getSuit()) {
					case Heart:
						numHeart++;
						break;
					case Diamond:
						numDiamond++;
						break;
					case Club:
						numClub++;
						break;
					case Spade:
						numSpade++;
						break;
					}
				}
			}
		}
		return (numHeart >= 5 || numDiamond >= 5 || numClub >= 5 || numSpade >= 5);
	}
	
	private boolean checkRoyalFlush() {
		return false;
	}
	
	private boolean checkStraightFlush() {
		return false;
	}
	
	private boolean checkFourOfKind() {
		return false;
	}
	
	private boolean checkFullHouse() {
		return false;
	}
	
	private boolean checkStraight() {
		return false;
	}
	
	private boolean checkThreeOfKind() {
		return false;
	}
	
	private boolean checkTwoPair() {
		boolean firstCard = false;
		boolean secondCard = false;
		for (Card b: boardCards) {
			if (b.equals(playerCards.get(0)))
				firstCard = true;
		}
		if (firstCard) {
			for (Card b: boardCards) {
				if (b.equals(playerCards.get(1)))
					secondCard = true;
			}
		}
		return firstCard && secondCard;
	}
	
	private boolean checkPair() {
		for (Card p: playerCards) {
			for (Card b: boardCards) {
				if (p.equals(b))
					return true;
			}
		}
		return false;
	}
	
	public static void determineWinner(ArrayList<Player> winners, int lowestScore) {
		switch (lowestScore) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;
		case 10:
			highestCardHolder(winners);
			break;
		}
	}
	
	private static void highestCardHolder(ArrayList<Player> winners) {
		Card highestCard = winners.get(0).getCards().get(0);
		ArrayList<Player> highestCardHolders = new ArrayList<Player>();
		for (Player p: winners) {
			for (Card c: p.getCards()) {
				if (c.equals(highestCard))
					highestCardHolders.add(p);
				if (c.compareTo(highestCard) > 0) {
					highestCardHolders.removeAll(highestCardHolders);
					highestCardHolders.add(p);
					highestCard = c;
				}
			}
		}
		winners.removeAll(winners);
		winners.addAll(highestCardHolders);
	}
	
}
