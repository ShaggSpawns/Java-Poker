package main;

import java.util.ArrayList;

//TODO Score method

public class PokerUtils {
	public int[] goodCards = {0, 0};
	public int numGoodCards = 0;
	public boolean handPairs = false;
	
	public int score(ArrayList<Card> playerHand, ArrayList<Card> boardCards) {
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
}
