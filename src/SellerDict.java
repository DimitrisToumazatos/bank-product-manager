import java.util.*;

class SellerDict {
	private HashMap<String, ArrayList<SellerAction>> sellerDictionary  = new HashMap<String, ArrayList<SellerAction>>();
	
	void addAction(String ID, SellerAction sale) {
		if (sellerDictionary.containsKey(ID)) {
			sellerDictionary.get(ID).add(sale);
		} else {
			sellerDictionary.put(ID, new ArrayList<SellerAction>() );
			sellerDictionary.get(ID).add(sale);
		}
	}
	
	ArrayList<SellerAction> getActions(String ID) {
		return sellerDictionary.get(ID);
	}
	
	void printActionsByID(String ID) {
		for (SellerAction item : sellerDictionary.get(ID)) {
			System.out.println(item);
		}
	}	
}