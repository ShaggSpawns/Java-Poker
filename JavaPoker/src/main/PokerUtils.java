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
		String cardSuit = "club";
		
		for(int i = 0; i < 52; i++) {
			switch(suit) {
			case 2:
				cardSuit = "spade";
				break;
			case 3:
				cardSuit = "diamond";
				break;
			case 4:
				cardSuit = "heart";
				break;
			default:
				cardSuit = "club";
				break;
			}
			
			shuffleThis[i] = (value.set(cardNum), suit.set(cardSuit));
			cardNum++;
			if(cardNum == 14) {
				suit++;
				cardNum = 1;
			}
		}
		
		for(int a = 0; a < 52; a++)
			shuffled[a] = 0;
		
		System.out.print("Shuffling, please wait");
		
		for(int i = 0; i < 52; i++) {
			loc = (int)(Math.random()*52);
			
			if(i%4 == 0)
				System.out.print(".");
			
			if(shuffled[loc] == 0) {
				shuffled[loc] = shuffleThis[position];
				position++;
			}
			else
				i--;
		}
		for(int i = 0; i < 52; i ++)
			if(shuffled[i] == 0)
				shuffled[i] = 99;
		return shuffled;
	}
	
	//TODO parameters (board, player hands)
	public static int score() {
		
	}
}
