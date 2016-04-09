package main;


public class Player {
	private double chipCount;
	int playTimes = 0;
	
	Player(String name) { 
		newChipCount(3000);
	}
	
	private void newChipCount(double winnings) {
		chipCount += winnings;
	}
}
