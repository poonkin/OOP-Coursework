package camelinaction;

public class LongTrade extends Trade{
	
	@Override
	public void assembleTrade(int tradeRefNum, String symbol, int numShares, double price){
		this.trade = (tradeRefNum + ", " + symbol + ", BUY, " + numShares + ", " + price);
	}

}
