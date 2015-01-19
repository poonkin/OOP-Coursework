package camelinaction;

public class MovingAverageCrossover implements Strategy{
	private int tradeNumber = 1;
//	private final static double diff = .1;
	
	@Override
	public String checkStrategy(Stock s){
		String trade = "No Trade";
		double diff = s.close.lastElement() * .001;
		if (s.hasBeenTraded == false){
        if (s.slowMA.size() >= 15){
        	if (s.fastMA.lastElement() > s.slowMA.lastElement() && s.inTrade == false &&
        			(s.fastMA.lastElement() - s.slowMA.lastElement() >= diff) ){
        		LongTrade lt = new LongTrade();
        		lt.assembleTrade(tradeNumber, s.symbol, 1000, s.close.lastElement());
        		System.out.println("***** NEW TRADE: " + lt.trade + " ******");
        		setTradeNumber();
//        		s.inTrade = true;
        		s.setInTrade(true);
//        		s.hasBeenTraded = true;
        		s.setHasBeenTraded(true);
        		return lt.trade;
        	} else if (s.fastMA.lastElement() < s.slowMA.lastElement() && s.inTrade == false && 
        			(s.slowMA.lastElement() - s.fastMA.lastElement() >= diff) ){
        		ShortTrade st = new ShortTrade();
        		st.assembleTrade(tradeNumber, s.symbol, 1000, s.close.lastElement());
        		System.out.println("***** NEW TRADE: " + st.trade + " ******");
        		setTradeNumber();
//        		s.inTrade = true;
        		s.setInTrade(true);
//        		s.hasBeenTraded = true;
        		s.setHasBeenTraded(true);
        		return st.trade;
        	} else {
        		return trade;
        	}
        }
		}
        return trade;
    }
	
	public void setTradeNumber(){
		this.tradeNumber++;
	}
	
}
