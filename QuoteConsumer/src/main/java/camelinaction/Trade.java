package camelinaction;

public abstract class Trade {
	
	public String trade = null;
	
	abstract void assembleTrade(int tradeRefNum, String symbol, int numShares, double price);
	
}
