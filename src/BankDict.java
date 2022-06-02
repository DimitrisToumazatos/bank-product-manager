import java.util.*;

class BankDict {
	private HashMap<String, CreditCard> cards = new HashMap<String, CreditCard>();
	private HashMap<String, Loan> loans = new HashMap<String, Loan>();
    private HashMap<String, Double> sellerCommissions = new HashMap<String, Double>();
	
	public void add(String productID, CreditCard product) {
		cards.put(productID, product);
	}
	
	public void add(String productID, Loan product) {
		loans.put(productID, product);
	}
	
	public void addSellerCommission(String sellerID, double com) {
		if(!sellerCommissions.containsKey(sellerID)) {
			sellerCommissions.put(sellerID, com);
			return;
		}
		
		sellerCommissions.replace(sellerID, com);
	}
	
	public CreditCard getCard(String productID) {
		return cards.get(productID);
	}
	
	public Loan getLoan(String productID) {
		return loans.get(productID);
	}
	
	public ArrayList<CreditCard> getCCs() {
		return new ArrayList<CreditCard>(cards.values());
	}
	
	public ArrayList<Loan> getLoans() {
		return new ArrayList<Loan>(loans.values());
	}
	
	public double getCommission(String ID) {
		return sellerCommissions.get(ID);
	}
	
	public ArrayList<Double> getCommissions() {
		return (ArrayList<Double>)sellerCommissions.values();
	}
	
	public HashMap<String, Loan> getLoanMap() {
		return loans;
	}
	
	public double getInterestByCard(String ID) {
		return cards.get(ID).getCommission();
	}
	
	public double getLoanAmount(String ID) {
		return loans.get(ID).getAmount();
	}
	
	public boolean containsLoan(String ID) {
    	return loans.containsKey(ID);
    }
    
    public boolean containsCard(String ID) {
    	return cards.containsKey(ID);
    }	
}