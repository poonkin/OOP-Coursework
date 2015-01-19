package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TradeMonitor implements Processor{
	
	@Override
	public void process(Exchange exchange) throws Exception {
		String[] body = exchange.getIn().getBody(String.class).split(",");
		Monitor mon = Monitor.getInstance();
		int tradeStatus = -2;
		String tradeMessage = "";
		String firstOut = body[0].replaceAll("[^a-zA-Z]", "");
		firstOut = firstOut.replaceAll("\\s", "");
		// Put the trade into the Confirmed Trade Hash
		if (firstOut.equals("OK")){
			ConfirmedTrade t = new ConfirmedTrade(Integer.parseInt(body[1].replaceAll("\\s","")), body[2].replaceAll("\\s",""),
													body[3].replaceAll("\\s",""),Integer.parseInt(body[4].replaceAll("\\s","")),
															Double.parseDouble(body[5].replaceAll("\\s","")) );
			mon.addTradeToHash(body[2].replaceAll("\\s",""), t);
			exchange.getIn().setBody("Confirmed Trade");
		// Below: Check to trade exists in Hash and if it does check other maintenance requirements.
		} else if (mon.tradesInProgress.containsKey(firstOut)){	
			// Just print whether the trade is up or down
			mon.printStatus(firstOut, Double.parseDouble(body[5].replaceAll("\\s","")),
					mon.tradesInProgress.get(firstOut).price,
					mon.tradesInProgress.get(firstOut).numShares);
			// Check to see if the trade is ready to be exited.
			tradeStatus = mon.checkTradeStatus(firstOut, Double.parseDouble(body[5].replaceAll("\\s","")),
					mon.tradesInProgress.get(firstOut).price);
			// Package message and print status
			tradeMessage = mon.packageTradeMessage(Double.parseDouble(body[5].replaceAll("\\s","")), tradeStatus, body);
			System.out.println(tradeMessage);
			exchange.getIn().setBody(tradeMessage);
		// Put quote not usable out to the console
		}  else {
			System.out.println("Trade Quote not usable");
			exchange.getIn().setBody("Not Useable");
		}
	}

}
