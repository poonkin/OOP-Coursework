package camelinaction;

public class ConfirmedTrade {

	int tradeNum = 0;
	String symbol = "";
	String direction = "";
	int numShares = 0;
	double price = 0.00;
	Boolean toBeClosed = false;
	
	public ConfirmedTrade(int tradeNum, String symbol, String direction, int numShares, double price){
		this.tradeNum = tradeNum;
		this.symbol = symbol;
		this.direction = direction;
		this.numShares = numShares;
		this.price = price;
		this.toBeClosed = false;
	}
	
}
