package main;

import java.util.ArrayList;

public class PokerUtils {
	private ArrayList<Card> boardCards;
	private ArrayList<Card> playerCards;
	private ArrayList<Card> allCards;
	ArrayList<Card> sortedCardsLowHigh;
	Card highestStraightCard;
	boolean handPair = playerCards.get(0).getRank().getValue() == playerCards.get(1).getRank().getValue() ? false:true;

	<<<<<<<HEAD

	public int score(ArrayList<Card> playerHand, ArrayList<Card> boardCards) {
		//if we aren't using any of these variables (i don't think i did) feel free to remove them
		int cardSelector = 0;
		boolean pair = (pair(playerHand, boardCards) || handPair(playerHand));
		boolean twoPair = twoPair(playerHand, boardCards);
		boolean threeOfKind = set(playerHand, boardCards);
		boolean fourOfKind = false;
		boolean fullHouse = false;
		boolean straight = checkStraight(playerHand, straightCards);
		boolean flush = checkFlush(playerHand, boardCards);
		boolean straightFlush = false;
		
		if(straightFlush)
			return 2;
		if(fourOfKind)
=======

	public PokerUtils(ArrayList<Card> boardCards) {
		this.boardCards = new ArrayList<Card>(boardCards);
	}

	/*
	 * TODO Check flush -> royal -> straight TODO Check four of a kind TODO
	 * Check full house TODO Check straight TODO Check three of a kind (DONE)
	 */
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
>>>>>>> branch 'master' of https://github.com/ShaggSpawns/Java-Poker.git
			return 3;
		else if (checkFullHouse())
			return 4;
		else if (flush)
			return 5;
<<<<<<< HEAD
		if(straight)
			return 6;
		if(threeOfKind)
=======
		else if (checkStraight())
			return 6;
		else if (checkThreeOfKind())
>>>>>>> branch 'master' of https://github.com/ShaggSpawns/Java-Poker.git
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

	//checks highest card in straight to verify that it is royal and checks that straight is all of same suit
	private boolean checkRoyalFlush() {
		if(highestStraightCard.getRank().equals("A"))
			for(int i = 0; i < 5; i++)
				if(!(sortedCardsLowHigh.get(i).getSuit().equals(sortedCardsLowHigh.get(0).getSuit())))
					return false;
		else
			return false;
		return true;
	}

	private boolean checkStraightFlush() {
		if(checkStraight() && checkFlush())
			return true;
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
		if(checkTwoPair() && checkThreeOfKind())
			return true;
		return false;
	}

	
	//TODO Run through this with some test cases
	private boolean checkStraight() {
		int min = Rank.values()[0].getValue();
		int minLoc = 0;
		int totalStraightCards = 1;
		int currentCheck;
		int currentCheckLoc = 0;
		boolean playerHasCard = false;
		boolean straight = false;

		ArrayList<Card> allCards = new ArrayList<Card>();
		ArrayList<Card> sortedCards = new ArrayList<Card>();
		for (Card p : playerCards)
			allCards.add(p);
		for (Card b : boardCards)
			allCards.add(b);

		//Sorts allCards lowest to highest
		for (int k = 0; k < allCards.size(); k++) {
			for (int i = 0; i < allCards.size(); i++)
				if (allCards.get(i).getRank().getValue() < min) {
					minLoc = i;
					min = allCards.get(i).getRank().getValue();
				}
			sortedCards.add(allCards.get(minLoc));
			allCards.remove(minLoc);
		}
		
		//checks if a straight exists in the newly sorted cards
		currentCheck = sortedCards.get(0).getRank().getValue();
		for(int i = 1; i < sortedCards.size(); i++) {
			if(sortedCards.get(i).getRank().getValue() == currentCheck + 1)
				totalStraightCards++;
			else {
				totalStraightCards = 1;
				currentCheckLoc = i;
			}
			currentCheck = sortedCards.get(i).getRank().getValue();
			if(totalStraightCards >= 5)
				straight = true;
		}
		
		//removes all cards that are not part of the straight, if the straight exists
		if(straight == true) {
			for(int i = 0; i < currentCheckLoc; i++)
				sortedCards.remove(0);
			for(int i = currentCheckLoc + 5; i < sortedCards.size();)
				sortedCards.remove(sortedCards.size());
			//checks if the player has one of the cards in the straight
			for (Card p : playerCards)
				for (Card s : sortedCards)
					if (p.equals(s))
						return true;
		}
		highestStraightCard = sortedCards.get(5);
		sortedCardsLowHigh = sortedCards;
		return false;
	}

	private boolean checkThreeOfKind() {
		int totalCardCount = 1;
		//goes through players hand and counts number of similar cards on the board
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
		boolean firstCard = false;
		boolean secondCard = false;
		for (Card b : boardCards) {
			if (b.equals(playerCards.get(0)))
				firstCard = true;
		}
		if (firstCard) {
			for (Card b : boardCards) {
				if (b.equals(playerCards.get(1)))
					secondCard = true;
			}
		}
		return firstCard && secondCard;
	}

	private boolean checkPair() {
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
