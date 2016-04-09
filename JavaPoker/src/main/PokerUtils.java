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
			
			if(shuffled[loc].getValue() == Rank.values()[13]) {
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
	public static int score() {
		return 0;
	}
}
