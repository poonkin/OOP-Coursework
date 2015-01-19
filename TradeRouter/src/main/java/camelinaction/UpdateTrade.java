package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class UpdateTrade implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		String[] body = exchange.getIn().getBody(String.class).split(",");
//		String status = body[0].replaceAll("\\s","");
		int tradeNum = Integer.parseInt(body[1].replaceAll("\\s",""));
//		String symbol = body[2].replaceAll("\\s","");
//		String direction = body[3].replaceAll("\\s","");
		int numShares = Integer.parseInt(body[4].replaceAll("\\s",""));
		Double price = Double.parseDouble(body[5].replaceAll("\\s",""));
		System.out.println("Trade Number " + tradeNum + " has been updated in the Portfolio");
		double checkAmount = tradeValue(numShares, price);
		Portfolio port = Portfolio.getInstance();
		port.tradeValues.put(tradeNum, checkAmount);
		System.out.println("Iterating through Trade Position Values:");
		port.iterateThroughPositions();
	}
	
	public double tradeValue(int numShares, double tradePrice){
		return numShares * tradePrice;
	}

}
