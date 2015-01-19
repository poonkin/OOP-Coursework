package camelinaction;

import java.util.HashMap;


public class AllStocks{

    // This could be processed with a file and vector of stocks to create a number of the objects.
    // Since only two are under consideration, we can just construct them here.

    HashMap<String, Stock> allStocks = new HashMap<String, Stock>();
    
    private AllStocks(){
    	Stock ci = new Stock("CI");
        Stock cog = new Stock("COG");
        allStocks.put("CI", ci);
        allStocks.put("COG", cog);
    }

    public static AllStocks getInstance(){
        if (instance == null){
            synchronized (AllStocks.class) {
                if (instance == null) {
                    instance = new AllStocks();
                }
            }
        }
        return instance;
    }
    private static AllStocks instance = null;
    
    public Stock getStock(String key){
    	return allStocks.get(key);
    }
    
    
}
