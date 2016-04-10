package main;
import java.util.ArrayList;

//TODO Score method

public class PokerUtils extends Poker{
	//TODO parameters (board, player hands)
	public static int[] goodCards = {0, 0};
	public static int numGoodCards = 0;
	public static boolean handPairs = false;
	
	public static int score(Card[] playerHand, ArrayList<Card> boardCards) {
		int cardSelector = 0;
		
		
		return 10;
	}
	
	public static boolean handPair(Card[] playerHand) {
		if(playerHand[0].getRank() == playerHand[1].getRank())
			return true;
		return false;
	}
	
	public static boolean pair(Card[] playerHand, ArrayList<Card> boardCards) { 
		for(int i = 0; i < 2; i++)
			for(int k = 0; k < 5; k++)
				if(playerHand[i].getRank() == boardCards.get(k).getRank()) {
					goodCards[i] = 1;
					numGoodCards++;
					return true;
				}
		return false;
	}
	
	public static boolean set(Card[] playerHand, ArrayList<Card> boardCards) {
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
