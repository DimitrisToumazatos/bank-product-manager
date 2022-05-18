import java.util.*;

class BankDict {
	private HashMap<String, ArrayList<ProductSale>> sellerDictionary  = new HashMap<String, ArrayList<ProductSale>>();
	private HashMap<String, ArrayList<CardTransaction>> cardDictionary = new HashMap<String, ArrayList<CardTransaction>>();
    private HashMap<String, Double> cardIDs = new HashMap<String, Double>();
    private HashMap<String, Double> loanIDs = new HashMap<String, Double>();
    private HashMap<String, Double> sellerCommissions = new HashMap<String, Double>();
    
    // 1 hashmap product_id -> product object
    // to test type: if Object instanceof Loan
    // for commission we use getter
    // to check if exists: contains in hashmap
	
	void addAction(String ID, ProductSale sale) {
		if (sellerDictionary.containsKey(ID)) {
			sellerDictionary.get(ID).add(sale);
		} else {
			sellerDictionary.put(ID, new ArrayList<ProductSale>() );
			sellerDictionary.get(ID).add(sale);
		}
	}
	
	void addAction(String cardID, CardTransaction ct) {
		if(cardDictionary.containsKey(cardID)) {
			cardDictionary.get(cardID).add(ct);
		} else {
			cardDictionary.put(cardID, new ArrayList<CardTransaction>());
			cardDictionary.get(cardID).add(ct);
		}
	}
	
	void addSellerCommission(String sellerID, double com) {
		if(!sellerCommissions.containsKey(sellerID)) {
			sellerCommissions.put(sellerID, com);
			return;
		}
		
		sellerCommissions.replace(sellerID, com);
	}
	
	void addCardID(String cardID, double commission) {
		cardIDs.put(cardID, commission);
	}
	
	void addLoanID(String loanID, double amount) {
		loanIDs.put(loanID, amount);
	}
	
	ArrayList<ProductSale> getActions(String ID) {
		return sellerDictionary.get(ID);
	}
	
	ArrayList<CardTransaction> getTransactionsByCard(String cardID) {
		return cardDictionary.get(cardID);
	}
	
	double getCommission(String ID) {
		return sellerCommissions.get(ID);
	}
	
	Set<String> getSellers() {
		return sellerDictionary.keySet();
	}
	
	ArrayList<Double> getCommissions() {
		return (ArrayList<Double>)sellerCommissions.values();
	}
	double getInterestByCard(String ID) {
		return cardIDs.get(ID);
	}
	
	double getLoanAmount(String ID) {
		return loanIDs.get(ID);
	}
	
	public boolean containsLoan(String ID) {
    	return loanIDs.containsKey(ID);
    }
	
	public boolean containsSeller(String ID) {
		return sellerDictionary.containsKey(ID);
	}
    
    public boolean containsCard(String ID) {
    	return cardIDs.containsKey(ID);
    }
	
	void printActionsByID(String ID) {
		for (ProductSale item : sellerDictionary.get(ID)) {
			System.out.println(item);
		}
	}	
}