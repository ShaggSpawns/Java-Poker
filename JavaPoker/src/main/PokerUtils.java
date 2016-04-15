package main;

import java.util.ArrayList;
import java.util.Collections;

public class PokerUtils {
	private ArrayList<Card> boardCards;
	private ArrayList<Card> playerCards;
	private ArrayList<Card> allCards;
	private Card highestStraightCard;
	private ArrayList<Card> straightCards;
	boolean handPair;
	
	public PokerUtils(ArrayList<Card> boardCards) {
		this.boardCards = new ArrayList<Card>(boardCards);
	}
	
	public int score(ArrayList<Card> playerCards) {
		this.playerCards = new ArrayList<Card>(playerCards);
		handPair = playerCards.get(0).getRank().equals(playerCards.get(1).getRank());
		allCards = new ArrayList<Card>(boardCards);
		allCards.addAll(playerCards);
		Collections.sort(allCards);
		highestStraightCard = null;
		straightCards = new ArrayList<Card>(getStraightCards());
		System.out.println(straightCards);
		if (checkFlush()) {
			if (checkStraightFlush()) {
				if (checkRoyalFlush()) {
					return 1;
				} else {
					return 2;
				}
			}
		}
		if (checkFourOfKind())
			return 3;
		if (checkFullHouse())
			return 4;
		if (checkFlush())
			return 5;
		if(checkStraight())
			return 6;
		if (checkThreeOfKind())
			return 7;
		if (checkTwoPair())
			return 8;
		if (checkPair())
			return 9;
		return 10;
	}

	private boolean checkFlush() {
		int numHeart = 0;
		int numDiamond = 0;
		int numClub = 0;
		int numSpade = 0;
		for (Card p : playerCards) {
			for (Card b : boardCards) {
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
	
	private ArrayList<Card> getStraightCards() {
		// Remove duplicated rank cards
		ArrayList<Card> noDupes = new ArrayList<Card>(allCards);
		for (int i = 1; i < noDupes.size(); i++) {
			if (noDupes.get(i).getRank().equals(noDupes.get(i - 1).getRank())) {
				noDupes.remove(i);
			}
		}
		
		// Build contingent list of straight cards
		ArrayList<Card> straightCards = new ArrayList<Card>();
		for (int i = 1; i < allCards.size(); i++) {
			if (straightCards.size() == 0)
				straightCards.add(allCards.get(i - 1));
			if (allCards.get(i).getRank().equals(allCards.get(i - 1).getRank().nextRank()))
				straightCards.add(allCards.get(i));
			else if (straightCards.size() < 5)
				straightCards.clear();
			else
				break;
		}
		
		// Check if straight and return list
		if (straightCards.size() < 5)
			straightCards.clear();
		else {
			for (Card p: playerCards) {
				for (Card s: straightCards) {
					if (p.getRank().equals(s.getRank())) {
						highestStraightCard = straightCards.get(straightCards.size() - 1);
						return straightCards;
					}
				}
			}
			straightCards.clear();
		}
		return straightCards;
	}
	
	private boolean checkRoyalFlush() {
		return highestStraightCard != null && highestStraightCard.getRank().equals(Rank.Ace);
	}
	
	private boolean checkStraightFlush() {
		if(checkStraight()) {
			int numHeart = 0;
			int numDiamond = 0;
			int numClub = 0;
			int numSpade = 0;
			for (Card c : straightCards) {
				switch (c.getSuit()) {
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
			return (numHeart >= 5 || numDiamond >= 5 || numClub >= 5 || numSpade >= 5);
		}
		return false;
	}
	
	private boolean checkFourOfKind() {
		int totalCardCount = 1;
		//goes through players hand and counts number of similar cards on the board
		for (Card p : playerCards) {
			for (Card b : boardCards) {
				if (p.equals(b))
					totalCardCount++;
				if (totalCardCount >= 4)
					return true;
			}
			if(!handPair)
				totalCardCount = 1;
		}
		return false;
	}
	
	private boolean checkFullHouse() {
		return checkTwoPair() && checkThreeOfKind();
	}
	
	private boolean checkStraight() {
		return straightCards.size() != 0;
	}

	private boolean checkThreeOfKind() {
		int totalCardCount = 1;
		for (Card p : playerCards) {
			for (Card b : boardCards) {
				if (p.equals(b))
					totalCardCount++;
				if (totalCardCount >= 3)
					return true;
			}
			if(!handPair)
				totalCardCount = 1;
		}
		return false;
	}
	
	private boolean checkTwoPair() {
		if (handPair)
			return false;
		boolean firstCard = false;
		boolean secondCard = false;
		for (Card b : boardCards) {
			if (b.getRank().equals(playerCards.get(0).getRank()))
				firstCard = true;
		}
		if (firstCard) {
			for (Card b : boardCards) {
				if (b.getRank().equals(playerCards.get(1).getRank()))
					secondCard = true;
			}
		}
		return firstCard && secondCard;
	}
	
	private boolean checkPair() {
		if(handPair)
			return true;
		for (Card p : playerCards) {
			for (Card b : boardCards) {
				if (p.equals(b))
					return true;
			}
		}
		return false;
	}
	
	// Determine the actual winner if necessary
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
		//ran into an error here once in 50 games
		Card highestCard = winners.get(0).getCards().get(0);
		ArrayList<Player> highestCardHolders = new ArrayList<Player>();
		for (Player p : winners) {
			for (Card c : p.getCards()) {
				if (c.equals(highestCard))
					highestCardHolders.add(p);
				if (c.compareTo(highestCard) > 0) {
					highestCardHolders.clear();
					highestCardHolders.add(p);
					highestCard = c;
				}
			}
		}
		winners.clear();
		winners.addAll(highestCardHolders);
	}
}
