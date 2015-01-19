package camelinaction;

import java.util.HashMap;


// Singleton with Iterator
public class Monitor{

	// These are the bounds for the trades to exit with profit or loss
	private final static double gain = 0.005; 
	private final static double loss = 0.0035;
	Boolean hasSentCloseMsg = false;
	

	// This is the hash of trades going at the moment
	HashMap <String, ConfirmedTrade> tradesInProgress = new HashMap <String, ConfirmedTrade>();
    
	// Constructor
    private Monitor(){}

    public static Monitor getInstance(){
        if (instance == null){
            synchronized (Monitor.class) {
                if (instance == null) {
                    instance = new Monitor();
                }
            }
        }
        return instance;
    }
    
    private static Monitor instance = null;
    
    public void printStatus(String symbol, double curPrice, double tradePrice, int numShares){
		double priceDif = curPrice - tradePrice;
		Boolean printMsg = this.tradesInProgress.get(symbol).toBeClosed;
		if (!printMsg){
			if (curPrice >= tradePrice){
				System.out.println(symbol + " is UP for a gain of $" + priceDif + " or $" + (priceDif * numShares) + 
					" on " + numShares + " shares");
			} else {
				System.out.println(symbol + " is DOWN for a loss of $" + priceDif + " or $" + (priceDif * numShares) + 
					" on " + numShares + " shares");
			}
		}
	}
	
	public String packageTradeMessage(double curPrice, int tradeStatus, String[] body){
		String msg = "Trade Remains Open";
		String symbol = body[0].replaceAll("[^a-zA-Z]", "");
		symbol = symbol.replaceAll("\\s", "");
		int tradeNum = this.tradesInProgress.get(symbol).tradeNum;
		String direction = this.tradesInProgress.get(symbol).direction;
		int numShares = this.tradesInProgress.get(symbol).numShares;
		Double price = this.tradesInProgress.get(symbol).price;
		double tradeVal = (curPrice - price) * numShares;
		
		if (tradeStatus == 1){
			if (direction.equalsIgnoreCase("BUY")){
				System.out.println("Closing trade number: " + tradeNum + " with a profit of $" + tradeVal);
				removeTradeFromHash(symbol);
				return msg = tradeNum + ", " + symbol + ", " + "SELL, " + curPrice;		
			} else {
				System.out.println("Closing trade number: " + tradeNum + " with a profit of $" + tradeVal);
				removeTradeFromHash(symbol);
				return msg = tradeNum + ", " + symbol + ", " + "BUY, " + curPrice;
			}
		} else if (tradeStatus == -1){
			if (direction.equalsIgnoreCase("BUY")){
				System.out.println("Closing trade number: " + tradeNum + " with a loss of $" + tradeVal);
				removeTradeFromHash(symbol);
				return msg = tradeNum + ", " + symbol + ", " + "SELL, " + curPrice;
			} else {
				System.out.println("Closing trade number: " + tradeNum + " with a loss of $" + tradeVal);
				removeTradeFromHash(symbol);
				return msg = tradeNum + ", " + symbol + ", " + "BUY, " + curPrice;
			}
		} else {
			return msg;
		}	
	}

	public int checkTradeStatus(String symbol, double curPrice, double tradePrice){
		if (curPrice >= (tradePrice * (1 + gain))){
			// exit trade for profit
			this.tradesInProgress.get(symbol).toBeClosed = true;
			return 1;
		} else if (curPrice <= (tradePrice * (1-loss))){
			// exit trade for loss
			this.tradesInProgress.get(symbol).toBeClosed = true;
			return -1;
		} else {
			// nothing to do yet
			return 0;
		}
	}
	
	public void addTradeToHash(String symbol, ConfirmedTrade t){
		this.tradesInProgress.put(symbol, t) ;
	}
	
	public void removeTradeFromHash(String symbol){
		this.tradesInProgress.remove(symbol);
	}
	
	
	
//    public double getPortfolioValue(){
//    	return this.value;
//    }
//    
//    public Boolean checkTradeAllowed(double tradeAmount){
//    	if ( this.tempPortValue >= tradeAmount){
//    		return true;
//    	}
//    	return false;
//    }
//
//    public void updateTempValue(double tradeCost){
//    	this.tempPortValue -= tradeCost;
//    }
    
}