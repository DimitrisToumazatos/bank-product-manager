import java.util.ArrayList;
import java.util.HashMap;

class Seller extends BankSellers {

    private String ID;
    private String firstName;
    private String lastName;
    private String TIN;
    private ArrayList<ProductSale> sales = new ArrayList<ProductSale>();
    private HashMap<String, Double> commission = new HashMap<String, Double>();

    Seller(String ID, String firstName, String lastName, String TIN) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.TIN = TIN;
        this.commission.put("total", 0.0);
        super.insertSeller(this);
    }
    
    void addSale(ProductSale sale) {
		sales.add(sale);
	}
    
    void setCommission(String ID, double com) {
    	if(commission.containsKey(ID)) {
    		commission.replace(ID, com);
    		return;
    	}
    	commission.put(ID, com);
    }
    
    ArrayList<ProductSale> getSales() {
    	return sales;
    }

    String getID() {
        return ID;
    }
    
    String getName() {
    	return firstName + " " + lastName;
    }
    
    String getTIN() {
    	return TIN;
    }
    
    double getCommission(String ID) {
    	if(commission.containsKey(ID)) return commission.get(ID);
    	return 0;
    }
    
    public String toString() {
        return "ID Number: " + this.ID + "\nLast Name: " + this.lastName + "\nFirst Name: " + this.firstName + "\nTIN: " + this.TIN;
    }
}