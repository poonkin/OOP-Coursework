package camelinaction;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by bsaterfiel on 8/17/14.
 */
public class Stock implements StockObserver{

    String symbol = null;
    Vector<Double> open = new Vector<Double>();
    Vector<Double> high = new Vector<Double>();
    Vector<Double> low = new Vector<Double>();
    Vector<Double> close = new Vector<Double>();
    Vector<Integer> volume = new Vector<Integer>();
    Vector<Double> slowMA = new Vector<Double>();
    Vector<Double> fastMA = new Vector<Double>();
    Boolean inTrade = false;
    Boolean hasBeenTraded = false;
    ArrayList<Observer> myStockObservers = new ArrayList<Observer>();

    public Stock (String symbol){
        this.symbol = symbol;
        this.inTrade = false;
        this.hasBeenTraded = false;
    }

    void quoteUpdate(double open, double high, double low, double close, int volume){
        this.open.addElement(open);
        this.high.addElement(high);
        this.low.addElement(low);
        this.close.addElement(close);
        this.volume.addElement(volume);
    }
    
    @Override
	public void register(Observer o) {
		myStockObservers.add(o);		
	}

	@Override
	public void remove(Observer o) {
		myStockObservers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : myStockObservers){
			System.out.println("This is to show the Observer Pattern.");
			o.updateIntrade(this.inTrade);
			o.updateHasBeenTraded(this.hasBeenTraded);
		}
	}

    // Update the Fast Moving Average (FMA) based on close
    void updateFastMovingAverage(Vector<Double> v, int maLength){
        double ma = 0.0;
        if (v.size() >= maLength){
            for (int i = 0; i < maLength; i++){
                ma += (Double) v.elementAt(v.size()-1-i);
            }
            ma = ma/maLength;
        }
        this.fastMA.addElement(ma);
    }
    
    void setInTrade(Boolean trueOrFalse){
    	this.inTrade = trueOrFalse;
    	notifyObservers();
    }
    
    void setHasBeenTraded(Boolean trueOrFalse){
    	this.hasBeenTraded = trueOrFalse;
    	notifyObservers();
    }

    // Update the Slow Moving Average (SMA) based on close
    void updateSlowMovingAverage(Vector<Double> v, int maLength){
        double ma = 0.0;
        if (v.size() >= maLength){
            for (int i = 0; i < maLength; i++){
                ma += (Double) v.elementAt(v.size()-1-i);
            }
            ma = ma/maLength;
        }
        this.slowMA.addElement(ma);
    }

    // Used for debugging a stock object
    void printVector(Vector v){
        for (int i=0; i < v.size(); i++){
            System.out.print(v.elementAt(i) + ", ");
        }
    }
    

}
