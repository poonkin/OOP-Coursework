package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ExecuteTrade implements Processor{
	
	@Override
	public void process(Exchange exchange) throws Exception {
		String[] body = exchange.getIn().getBody(String.class).split(",");
		String tradeNum = body[0].replaceAll("\\s","");
		String symbol = body[1].replaceAll("\\s","");
		String direction = body[2].replaceAll("\\s","");
		int numShares = Integer.parseInt(body[3].replaceAll("\\s",""));
		Double price = Double.parseDouble(body[4].replaceAll("\\s",""));
		System.out.println("Trade Number for confirmation is: " + tradeNum + ", Symbol: "+ symbol + ", Direction: " + direction + " "
				+ numShares + " @ " + "$" + price);
		double checkAmount = tradeValue(numShares, price);
		Portfolio port = Portfolio.getInstance();
		if (port.checkTradeAllowed(checkAmount)){
			exchange.getIn().setBody(tradeNum + ", " + symbol + ", " + direction + ", " + numShares + ", " + price);
			port.updateTempValue(checkAmount);
			System.out.println("Portfolio temp value is now: " + port.tempPortValue);
		} else {
			exchange.getIn().setBody( "Portfolio Liquidity not Available for trade: " + body);
		}
	}
	
	public double tradeValue(int numShares, double tradePrice){
		return numShares * tradePrice;
	}

}
