package camelinaction;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class Portfolio{
	
	double value = 1000000.00;
	double tempPortValue = value;

	HashMap <Integer, Double> tradeValues = new HashMap <Integer, Double>();
    
    private Portfolio(){}

    public static Portfolio getInstance(){
        if (instance == null){
            synchronized (Portfolio.class) {
                if (instance == null) {
                    instance = new Portfolio();
                }
            }
        }
        return instance;
    }
    
    private static Portfolio instance = null;
    
    public void iterateThroughPositions(){
    	System.out.println("This is to show the Iterator Pattern.");
    	Collection<Double> tvals = tradeValues.values();
    	Iterator<Double> myIterator = tvals.iterator();
    	while (myIterator.hasNext()){
    		System.out.println(myIterator.next());
    		
    	}
    }
    
    public double getPortfolioValue(){
    	return this.value;
    }
    
    public Boolean checkTradeAllowed(double tradeAmount){
    	if ( this.tempPortValue >= tradeAmount){
    		return true;
    	}
    	return false;
    }
    
    public void updateTempValuePostTrade(double profitAndPrincipal){
       	this.tempPortValue += profitAndPrincipal;
    }
    
    public void updatePortfolioValue(double profit){
    	this.value += profit;
    	System.out.println("Portfolio Value is now: $" + this.value);
    }
    
    public void updateTempValue(double tradeCost){
    	this.tempPortValue -= tradeCost;
    }
    
}