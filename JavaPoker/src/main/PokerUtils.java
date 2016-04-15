package main;

import java.util.ArrayList;
import java.util.Collections;

public class PokerUtils {
	private ArrayList<Card> boardCards;
	private ArrayList<Card> playerCards;
	private ArrayList<Card> allCards;
	ArrayList<Card> sortedCardsLowHigh;
	Card highestStraightCard, highCardInHand;
	boolean handPair;
	
	public PokerUtils(ArrayList<Card> boardCards) {
		this.boardCards = new ArrayList<Card>(boardCards);
	}

	/*
	 * TODO Check flush -> royal -> straight TODO Check four of a kind TODO
	 * Check full house TODO Check straight TODO Check three of a kind (DONE)
	 * TODO Highest effecting card in hand
	 */
	public int score(ArrayList<Card> playerCards) {
		this.playerCards = new ArrayList<Card>(playerCards);
		boolean handPairThing = playerCards.get(0).getRank().getValue() == playerCards.get(1).getRank().getValue() ? false:true;
		handPair = handPairThing;
		handPair = playerCards.get(0).getRank().equals(playerCards.get(1).getRank());
		allCards = new ArrayList<Card>(boardCards);
		allCards.addAll(playerCards);
		Collections.sort(allCards);
		if (checkFlush()) {
			if (checkRoyalFlush())
				return 1;
			else if (checkStraightFlush())
				return 2;
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
		if (checkPair() || handPair)
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

	//checks highest card in straight to verify that it is royal and checks that straight is all of same suit
	//TODO use checkStraightFlush(), then just check that the last card is an Ace
	private boolean checkRoyalFlush() {

		if(highestStraightCard.getRank().equals(14)) //issue here when a flush occurs

		if(highestStraightCard.getRank().equals(Rank.Ace))
			for(int i = 0; i < 5; i++)
				if(!(sortedCardsLowHigh.get(i).getSuit() == sortedCardsLowHigh.get(0).getSuit()))
					return false;
		else
			return false;
		return true;
	}
	
	// TODO Does this work? No. Oh you right. Will fix later. Going to do the same thing as straightCheck() most likely
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
		int totalStraightCards = 1;
		int currentCheck;
		int currentCheckLoc = 0;
		boolean straight = false;
		
		//checks if a straight exists in the newly sorted cards
		currentCheck = allCards.get(0).getRank().getValue();
		for(int i = 1; i < allCards.size(); i++) {
			if(allCards.get(i).getRank().getValue() == currentCheck + 1) {
				totalStraightCards++;
				System.out.println(totalStraightCards);
			}
			else {
				totalStraightCards = 1;
				currentCheckLoc = i;
			}
			currentCheck = allCards.get(i).getRank().getValue();
			if(totalStraightCards >= 5)
				straight = true;
		}
		
		//removes all cards that are not part of the straight, if the straight exists
		if(straight == true) {
			for(int i = 0; i < currentCheckLoc; i++)
				allCards.remove(0);
			for(int i = currentCheckLoc + 5; i < allCards.size();)
				allCards.remove(allCards.size());
			//checks if the player has one of the cards in the straight
			for (Card p : playerCards)
				for (Card s : allCards)
					if (p.equals(s))
						return true;
		}
		highestStraightCard = allCards.get(3);
		sortedCardsLowHigh = allCards;
		return false;
	}

	private boolean checkThreeOfKind() {
		int totalCardCount = 1;
		//goes through players hand and counts number of similar cards on the board
		for (Card p : playerCards) {
			for (Card b : boardCards) {
				if (p.equals(b))
					totalCardCount++;
				if (totalCardCount >= 3) {
					highCardInHand = p;
					return true;
				}
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
			if (b.getRank().equals(playerCards.get(0).getRank()))
				firstCard = true;
		}
		if (firstCard) {
			for (Card b : boardCards) {
				if (b.getRank().equals(playerCards.get(1).getRank()))
					secondCard = true;
			}
		}
		highCardInHand = playerCards.get(0).getRank().getValue() > playerCards.get(1).getRank().getValue() ? playerCards.get(0) : playerCards.get(1);
		return firstCard && secondCard;
	}

	private boolean checkPair() {
		for (Card p : playerCards) {
			for (Card b : boardCards) {
				if (p.equals(b))
					return true;
			}
		}
		if(handPair)
			return true;
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
