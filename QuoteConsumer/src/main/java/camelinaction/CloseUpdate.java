package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CloseUpdate implements Processor{

	Console myConsole = new Console();
	
	@Override
	public void process(Exchange exchange) throws Exception {
		String[] body = exchange.getIn().getBody(String.class).split(",");
		String symbol = body[2].replaceAll("\\s","");
		Stock stk = AllStocks.getInstance().getStock(symbol);
		stk.register(myConsole);
//		stk.inTrade = false;
		stk.setInTrade(false);
//		if (stk.inTrade == false){
//			System.out.println("************************Stock: " + symbol + " has now been marked Out Of Trade************************");
//		} else {
//			System.out.println("**************Unable to change inTrade status.*********************");
//		}
	}

	
}
