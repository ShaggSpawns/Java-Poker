package main;
import java.util.ArrayList;
import java.io.*;

//TODO Score method

public class PokerUtils extends Poker{
	public static Card[] shuffle() {
		Card[] shuffled = new Card[52];
		Card[] shuffleThis = new Card[52];
		int cardNum = 1;
		int suit = 1;
		int loc;
		int position = 0;
		int k = 0;
		Suit cardSuit = Suit.Club;
		
		for(int i = 0; i < 52; i++) {
			switch(suit) {
			case 2:
				cardSuit = Suit.Spade;
				break;
			case 3:
				cardSuit = Suit.Diamond;
				break;
			case 4:
				cardSuit = Suit.Heart;
				break;
			default:
				break;
			}
			
			shuffleThis[i] = new Card(Rank.values()[k], cardSuit);
			k++;
			cardNum++;
			if(k == 13) k = 0;
			if(cardNum == 14) {
				suit++;
				cardNum = 1;
			}
		}
		
		for(int a = 0; a < 52; a++)
			shuffled[a] = new Card(Rank.values()[13], cardSuit);
		
		System.out.print("Shuffling, please wait");
		
		for(int i = 0; i < 52; i++) {
			loc = (int)(Math.random()*52);
			
			if(i%9 == 0)
				System.out.print(".");
			
			if(shuffled[loc].getRank() == Rank.values()[13]) {
				shuffled[loc] = shuffleThis[position];
				position++;
			}
			else
				i--;
		}
		for(int i = 9; i < 61; i++) {
			System.out.print(shuffled[i-9] + " ");
		}
		return shuffled;
	}
	
	//TODO parameters (board, player hands)
	public static int score(Card[] playerHand, ArrayList<Card> boardCards) {
		Card[] board = (Card[]) boardCards.toArray();
		Card[] player = playerHand;
		
		boolean handPair = checkHandPair();
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
				if (board[k] == player[j]) {
					hasElement[k] = true;
				}
			}
			if (hasElement[k]) {
				for (int j = 0; j < boardCards.size(); j++) {
					if (boardCards.get(k) == boardCards.get(j))
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
		if (num3s != 0)
			fourOfKind = true;
		else if (num2s == 2 && handPair)
			fourOfKind = true;
		else if (num2s == 4)
			fullHouse = true;
		else if (num2s == 2 && num1s == 1)
			fullHouse = true;
		else if (num2s == 2)
			threeOfKind = true;
		else if (num1s == 1 && handPair)
			threeOfKind = true;
		else if (num1s == 2)
			twoPair = true;
		else if (num1s == 1)
			pair = true;
		// End Permutations
		
		// Returns


		return 10;
	}
	
	public static boolean checkHandPair() {
		return playerCards[0] == playerCards[1];
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
