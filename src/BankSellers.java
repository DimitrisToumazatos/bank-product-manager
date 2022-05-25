import java.util.*;

class BankSellers {
	static private int key = 0;
	static private HashMap<Integer, Seller> sellers = new HashMap<Integer, Seller>();
	
	public void insertSeller(Seller seller) {
		key++;
		sellers.put(key, seller);
	}
	
	public void printSellers() {
		for(Map.Entry<Integer, Seller> Seller : sellers.entrySet()) {
			System.out.printf("[Key: %d] | ID: %s | Name: %s | TIN: %s%n", Seller.getKey(), 
					Seller.getValue().getID(), Seller.getValue().getName(), 
					Seller.getValue().getTIN());
		}
	}
	
	public boolean sellerExists(int key) {
		return sellers.containsKey(key);
	}
	
	public ArrayList<Seller> getSellerList() {
    	return new ArrayList<Seller>(sellers.values());
    }
	
	public Seller getSeller(int key) {
		return sellers.get(key);
	}

}
