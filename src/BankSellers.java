import java.util.*;

class BankSellers {
	static private int key = 0;
	static private HashMap<Integer, Seller> sellers = new HashMap<Integer, Seller>();
	
	void insertSeller(Seller seller) {
		key++;
		sellers.put(key, seller);
	}
	
	void printSellers() {
		for(Map.Entry<Integer, Seller> Seller : sellers.entrySet()) {
			System.out.printf("[Key: %d] | ID: %s | Name: %s | TIN: %s%n", Seller.getKey(), 
					Seller.getValue().getID(), Seller.getValue().getName(), 
					Seller.getValue().getTIN());
		}
	}
	
	boolean sellerExists(int key) {
		return sellers.containsKey(key);
	}
	
	ArrayList<Seller> getSellerList() {
    	return new ArrayList<Seller>(sellers.values());
    }
	
	Seller getSeller(int key) {
		return sellers.get(key);
	}

}
