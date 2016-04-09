package main;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Poker {
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		shuffle();
	}

	fuck you
	public static card[] shuffle() {
		card[] shuffled = new card[];
		card[] shuffleThis = new card[52];
		int cardNum = 1;
		int loc;
		int position = 0;
		
		for(int i = 0; i < 52; i++) {
			shuffleThis[i] = cardNum;
			cardNum++;
			if(cardNum == 14)
				cardNum = 1;
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

}