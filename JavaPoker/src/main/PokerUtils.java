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
		/* Check flush -> royal -> straight
		 * Check four of a kind
		 * Check full house
		 * Check flush
		 * Check straight
		 * Check three of a kind
		 * Check two pair
		 * Check pair
		 * Return high card
		 */
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
					switch(p.getSuit()) {
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
		return false;
	}
	
	private boolean checkPair() {
		return false;
	}
	
	public static void highestCardHolder(ArrayList<Player> winners) {
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
	/*
	public int[] goodCards = {0, 0};
	public int numGoodCards = 0;
	public boolean handPairs = false;
	public int incapableScore(ArrayList<Card> playerHand, ArrayList<Card> boardCards) {
		int cardSelector = 0;
		boolean pair = (pair(playerHand, boardCards) || handPair(playerHand));
		boolean handPair = handPair(playerHand);
		boolean twoPair = twoPair(playerHand, boardCards);
		boolean threeOfKind = set(playerHand, boardCards);
		boolean fourOfKind = false;
		boolean fullHouse = false;
		boolean flush = checkFlush(playerHand, boardCards);
		boolean straightFlush = false;
		
		if(straightFlush)
			return 2;
		if(fourOfKind)
			return 3;
		if(fullHouse)
			return 4;
		if(flush)
			return 5;
		if(threeOfKind)
			return 7;
		if(twoPair)
			return 8;
		if(pair)
			return 9;
		return 10;
	}
	
	public boolean handPair(ArrayList<Card> playerHand) {
		if(playerHand.get(0).getRank() == playerHand.get(1).getRank())
			return true;
		return false;
	}
	
	public boolean pair(ArrayList<Card> playerHand, ArrayList<Card> boardCards) { 
		for(int i = 0; i < 2; i++)
			for(int k = 0; k < 5; k++)
				if(playerHand.get(i).getRank() == boardCards.get(k).getRank()) {
					goodCards[i] = 1;
					numGoodCards++;
					return true;
				}
		return false;
	}
	
	public boolean twoPair(ArrayList<Card> playerHand, ArrayList<Card> boardCards) {
		boolean p1 = false;
		boolean p2 = false;
		for(int i = 0; i < 5; i++)
			if(playerHand.get(0).getRank() == boardCards.get(i).getRank())
				p1 = true;
		
		if(p1 == false)
			return false;
		
		for(int i = 0; i < 5; i++)
			if(playerHand.get(1).getRank() == boardCards.get(i).getRank())
				p2 = true;
		if(p1 && p2)
			return true;
		return false;
	}
	
	public boolean set(ArrayList<Card> playerHand, ArrayList<Card> boardCards) {
		int numOfCards = 1;
		int start = 0;
		int end = 1;
		if(goodCards[0] != 1) start = 1;
		if(goodCards[1] == 1) end = 0;
		
		for(int i = start; i <= end; i++) {
			for(int k = 0; k < 5; k++) {
				if(playerHand.get(i).getRank() == boardCards.get(k).getRank())
					numOfCards++;
			}
			if(handPairs == true)
				numOfCards++;
			if(numOfCards >= 3)
				return true;
			numOfCards = 1;
		}
		return false;
	}
	
	public boolean checkFlush(ArrayList<Card> playerHand, ArrayList<Card> boardCards) {
		int numOfCards = 1;
		for(int i = 0; i < 5; i++)
			if(playerHand.get(0).getSuit() == boardCards.get(i).getSuit())
				numOfCards++;
		if(numOfCards >= 5)
			return true;
		
		numOfCards = 1;
		for(int i = 0; i < 5; i++)
			if(playerHand.get(1).getSuit() == boardCards.get(i).getSuit())
				numOfCards++;
		if(numOfCards >= 5)
			return true;
		return false;
	}
	*/
}
