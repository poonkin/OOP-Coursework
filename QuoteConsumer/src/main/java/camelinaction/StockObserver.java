package camelinaction;

public interface StockObserver {

	public void register(Observer o);
	public void remove(Observer o);
	public void notifyObservers();
	
}
