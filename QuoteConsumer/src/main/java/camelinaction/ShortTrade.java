package camelinaction;

public class ShortTrade extends Trade{
	
	@Override
	public void assembleTrade(int tradeRefNum, String symbol, int numShares, double price){
		this.trade = (tradeRefNum + ", " + symbol + ", SELL, " + numShares + ", " + price);
	}
}
