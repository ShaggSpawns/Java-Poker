package main;


public class Player {
	private double chipCount;
	private int playTimes = 0; // TODO Save player data to file
	
	Player(String name) { 
		setChipCount(3000);
	}
	
	Player(String name, double chipCount) {
		setChipCount(chipCount);
	}
	
	public void newChipCount(double winnings) {
		setChipCount(chipCount + winnings);
	}

	public int getPlayTimes() {
		return playTimes;
	}

	public void setPlayTimes(int playTimes) {
		this.playTimes = playTimes;
	}

	public double getChipCount() {
		return chipCount;
	}

	private void setChipCount(double chipCount) {
		if (chipCount > 0)
			this.chipCount = chipCount;
		else
			try {
				throw new Exception("Broke");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
