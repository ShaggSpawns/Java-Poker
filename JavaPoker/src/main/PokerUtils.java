package main;
import java.util.ArrayList;

//TODO Score method

public class PokerUtils{
	//TODO parameters (board, player hands)
	public int[] goodCards = {0, 0};
	public int numGoodCards = 0;
	public boolean handPairs = false;
	
	public int score(Card[] playerHand, ArrayList<Card> boardCards) {
		int cardSelector = 0;
		boolean pair = (pair(playerHand, boardCards) || handPair(playerHand));
		
		if(pair)
			return 9;
		return 10;
	}
	
	public boolean handPair(Card[] playerHand) {
		if(playerHand[0].getRank() == playerHand[1].getRank())
			return true;
		return false;
	}
	
	public boolean pair(Card[] playerHand, ArrayList<Card> boardCards) { 
		for(int i = 0; i < 2; i++)
			for(int k = 0; k < 5; k++)
				if(playerHand[i].getRank() == boardCards.get(k).getRank()) {
					goodCards[i] = 1;
					numGoodCards++;
					return true;
				}
		return false;
	}
	
	public boolean set(Card[] playerHand, ArrayList<Card> boardCards) {
		int numOfCards = 1;
		for(int k = 0; k < 5; k++) {
			if(playerHand[0].getRank() == boardCards.get(k).getRank())
				numOfCards++;
		}
		
		if(handPairs == true)
			numOfCards++;
		
		if(numOfCards >= 3)
			return true;
		return false;
	}
}
