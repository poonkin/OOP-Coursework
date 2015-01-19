package camelinaction;

public class Console implements Observer{
	
	@Override
	public void updateIntrade(Boolean inTrade){
		System.out.println("***** Stock inTrade has been updated to " + inTrade + " *****");
	}
	
	@Override
	public void updateHasBeenTraded(Boolean hasBeenTraded){
		System.out.println("***** Stock hasBeenTraded has been updated to " + hasBeenTraded + " *****");
	}

}
