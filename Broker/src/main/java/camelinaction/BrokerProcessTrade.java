package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BrokerProcessTrade implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		// This will always just return the executed trade for simplicity.
		// In real life we could make this a pub sub and see what brokers were advertising what prices and volumes of shares.
		// I think that is a bit beyond the scope of this project, and is also hard to "mock"
		// Also, to note appending the string with OK is for simplicity because in reality brokers will send fill messages
		// in many different forms and there are all types of broker messages to handle.
		
		String fill = "OK, ";
		String body = fill.concat(exchange.getIn().getBody(String.class));
		System.out.println(body);
		exchange.getIn().setBody(body);
	}

}
