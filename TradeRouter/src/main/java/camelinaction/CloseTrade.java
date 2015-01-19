package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CloseTrade implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		String[] body = exchange.getIn().getBody(String.class).split(",");
//		String status = body[0].replaceAll("\\s","");
		int tradeNum = Integer.parseInt(body[1].replaceAll("\\s",""));
//		String symbol = body[2].replaceAll("\\s","");
		String direction = body[3].replaceAll("\\s","");
		int numShares = 1000;
		Double price = Double.parseDouble(body[4].replaceAll("\\s",""));
		
		Portfolio port = Portfolio.getInstance();
		double origTradeVal = port.tradeValues.get(tradeNum);
		double origTradePrice = origTradeVal / numShares;
		
		double profit = tradeProfit(numShares, price, origTradePrice, direction);
		double totalToPort = totalTradeValue(numShares, price, origTradePrice, direction, origTradeVal);
		
		port.updatePortfolioValue(profit);
		port.updateTempValuePostTrade(totalToPort);
		
		System.out.println("Trade Number " + tradeNum + " has been Closed and Updated in the Portfolio");
		System.out.println("The Total Portfolio Value is now: $" + port.value);
	}
	
	public double tradeProfit(int numShares, double tradePrice, double inPrice, String direction){
		if (direction.equals("SELL")){
			return numShares * (tradePrice - inPrice);
		} else {
			return numShares * (inPrice - tradePrice);
		}
	}
	
	public double totalTradeValue(int numShares, double tradePrice, double inPrice, String direction, double tradeValue){
		if (direction.equals("SELL")){
			return (numShares * (tradePrice - inPrice)) + (tradeValue);
		} else {
			return (numShares * (inPrice - tradePrice)) + (tradeValue);
		}
	}

}
