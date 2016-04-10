package main;
import java.util.ArrayList;
import java.io.*;

//TODO Score method

public class PokerUtils extends Poker{
	//TODO parameters (board, player hands)
	public static int score(Card[] playerHand, ArrayList<Card> boardCards) {
		Card[] board = (Card[]) boardCards.toArray();
		Card[] player = playerHand;
		
		boolean handPair = checkHandPair(player);
		boolean pair = false;
		boolean twoPair = false;
		boolean threeOfKind = false;
		boolean fourOfKind = false;
		boolean fullHouse = false;
		boolean straight = checkStraight();
		boolean flush = false;
		boolean straightFlush = false;
		
		// Get number of each card, only if the hand has the card as well
		int[] numOfElement = {0,0,0,0,0};
		boolean[] hasElement = {false,false,false,false,false};
		for (int k = 0; k < boardCards.size(); k++) {
			for (int j = 0; j < player.length; j++) {
				if (board[k].getRank() == player[j].getRank()) {
					hasElement[k] = true;
				}
			}
			if (hasElement[k]) {
				for (int j = 0; j < boardCards.size(); j++) {
					if (board[k].getRank() == board[j].getRank())
						numOfElement[k]++;
				}
			}
		}
		/* Card Permutations (numOfElement)
		 * 333xx = four
		 * 22022 = full
		 * 22010 = full
		 * 22000 = three (plus hand = four)
		 * 10000 = pair (plus hand = three)
		 * 10100 = two pair
		 */
		int num1s = 0;
		int num2s = 0;
		int num3s = 0;
		for (int e: numOfElement) {
			switch (e) {
			case 1:
				num1s++;
				break;
			case 2:
				num2s++;
				break;
			case 3:
				num3s++;
				break;
			}
		}
		if (num3s != 0) fourOfKind = true;
		else if (num2s == 2 && handPair) fourOfKind = true;
		else if (num2s == 4) fullHouse = true;
		else if (num2s == 2 && num1s == 1) fullHouse = true;
		else if (num2s == 2) threeOfKind = true;
		else if (num1s == 1 && handPair) threeOfKind = true;
		else if (num1s == 2) twoPair = true;
		else if (num1s == 1) pair = true;
		// End Permutations
		
		// Returns
		if(straightFlush) return 2;
		if(fourOfKind) return 3;
		if(fullHouse) return 4;
		if(flush) return 5;
		if(straight) return 6;
		if(threeOfKind) return 7;
		if(twoPair) return 8;
		if(pair) return 9;
		return 10;
	}
	
	public static boolean checkHandPair(Card[] player) {
		return player[0] == player[1];
	}
	
	public static boolean checkPair() {
		
		return false;
	}
	public static boolean checkTwoPair() {
		
		return false;
	}
	public static boolean checkSet() {
		
		return false;
	}
	public static boolean checkStraight() {
		
		return false;
	}
	public static boolean checkFourKind() {
		
		return false;
	}
	public static boolean checkFlush() {
		
		return false;
	}
}
