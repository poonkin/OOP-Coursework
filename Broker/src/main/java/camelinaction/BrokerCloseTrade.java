package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BrokerCloseTrade implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		String fill = "Closed, ";
		String body = fill.concat(exchange.getIn().getBody(String.class));
		System.out.println(body);
		exchange.getIn().setBody(body);
		
	}

	
}
