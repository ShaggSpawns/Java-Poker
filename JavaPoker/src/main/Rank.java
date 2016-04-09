package main;

public enum Rank {
	Ace(1, "A"), Two(2, "2"), Three(3, "3"), Four(4, "4"), Five(5, "5"),
	Six(6, "6"), Seven(7, "7"), Eight(8, "8"), Nine(9, "9"), Ten(10, "10"),
	Jack(11, "J"), Queen(12, "Q"), King(13, "K"), Zero(0, "Z");

    private int value;
    private String name;

    Rank(int value, String name) {
        this.value = value;
        this.name = name;
    }
    
    public int getNumVal() {
        return value;
    }
    
    public String toString() {
    	return name;
    }
}
