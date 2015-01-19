package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ProcessQuote implements Processor{
	
	MovingAverageCrossover mac = new MovingAverageCrossover();
	String trade = null;
	Console myConsole = new Console();
	
	@Override
	public void process(Exchange exchange) throws Exception {
		String[] body = exchange.getIn().getBody(String.class).split(",");
		String ticker = body[0].replaceAll("[^a-zA-Z]", "");
		String time = body[1];
		Double open = Double.parseDouble(body[2]);
		Double high = Double.parseDouble(body[3]);
		Double low = Double.parseDouble(body[4]);
		Double close = Double.parseDouble(body[5]);
		int volume = Integer.parseInt(body[6].substring(1,body[6].length()-1));
		System.out.println("Updated Stock: " + ticker + " Time is: " + time + " Open is: " + open + " High is: " + high +
				" Low is: " + low + " Close is: " + close + " Volume is: " + volume);
		Stock stk = AllStocks.getInstance().getStock(ticker);
		stk.quoteUpdate(open, high, low, close, volume);
		stk.updateFastMovingAverage(stk.close, 5);
		stk.updateSlowMovingAverage(stk.close, 15);
		stk.register(myConsole);
		
//		System.out.println("The fast moving average vector is: ");
//		stk.printVector(stk.fastMA);
//		System.out.println("\n");
//		System.out.println("The slow moving average vector is: ");
//		stk.printVector(stk.slowMA);
//		System.out.println("\n");
//		if (stk.hasBeenTraded == false){
//			System.out.println("This stock has not been used in a strategy yet.");
//		} else {
//			System.out.println("*****This stock was already used in a strategy.*****");
//		}
		trade = mac.checkStrategy(stk);
		if (trade != null){
			exchange.getIn().setBody(trade);
		} else {
			exchange.getIn().setBody("No trade");
		}
	}

}
