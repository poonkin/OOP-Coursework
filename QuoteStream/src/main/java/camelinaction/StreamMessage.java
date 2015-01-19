package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class StreamMessage implements Processor{
	// ready-made for two stocks increase as stocks are added or make it wide open in production
	private final static int NUMSTOCKS = 2;
	// to simulate the stocks arriving at nearly accurate times (1 minute quotes (60000 below) would be real-time.)
	private final static int QUOTE_DELIVERY_TIME = 1000;
    int ctr = 0;
	public void process(Exchange exchange) throws Exception {
		String body[] = exchange.getIn().getBody(String.class).split("\t");
		System.out.println(body);
		if (ctr % NUMSTOCKS == 0 && ctr >= NUMSTOCKS){
			Thread.sleep(QUOTE_DELIVERY_TIME);
			ctr++;
		} else {
			ctr++;
		}
		String ticker = body[0].replaceAll("[^a-zA-Z]", "");
//		System.out.println("*************************************");
		System.out.println("***** MESSAGE FROM FILE: " + exchange.getIn().getHeader("CamelFileName") + " is heading to QuoteStreamBroker Queue for Stock: " + ticker + " *****");
//		System.out.println("*************************************");
	}
}
