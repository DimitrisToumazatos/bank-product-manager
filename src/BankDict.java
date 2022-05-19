import java.util.*;

class BankDict {
	private HashMap<String, CreditCard> cards = new HashMap<String, CreditCard>();
	private HashMap<String, Loan> loans = new HashMap<String, Loan>();
    private HashMap<String, Double> sellerCommissions = new HashMap<String, Double>();
	
	void add(String productID, CreditCard product) {
		cards.put(productID, product);
	}
	
	void add(String productID, Loan product) {
		loans.put(productID, product);
	}
	
	void addSellerCommission(String sellerID, double com) {
		if(!sellerCommissions.containsKey(sellerID)) {
			sellerCommissions.put(sellerID, com);
			return;
		}
		
		sellerCommissions.replace(sellerID, com);
	}
	
	CreditCard getCard(String productID) {
		return cards.get(productID);
	}
	
	Loan getLoan(String productID) {
		return loans.get(productID);
	}
	
	ArrayList<CreditCard> getCCs() {
		return new ArrayList<CreditCard>(cards.values());
	}
	
	double getCommission(String ID) {
		return sellerCommissions.get(ID);
	}
	
	ArrayList<Double> getCommissions() {
		return (ArrayList<Double>)sellerCommissions.values();
	}
	
	double getInterestByCard(String ID) {
		return cards.get(ID).getCommission();
	}
	
	double getLoanAmount(String ID) {
		return loans.get(ID).getAmount();
	}
	
	public boolean containsLoan(String ID) {
    	return loans.containsKey(ID);
    }
    
    public boolean containsCard(String ID) {
    	return cards.containsKey(ID);
    }	
}