import java.util.ArrayList;

class Seller extends BankSellers {

    private String ID;
    private String firstName;
    private String lastName;
    private String TIN;
    private ArrayList<ProductSale> sales = new ArrayList<ProductSale>();

    Seller(String ID, String firstName, String lastName, String TIN) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.TIN = TIN;
        super.insertSeller(this);
    }
    
    void addSale(ProductSale sale) {
		sales.add(sale);
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
    
    public String toString() {
        return "ID Number: " + this.ID + "\nLast Name: " + this.lastName + "\nFirst Name: " + this.firstName + "\nTIN: " + this.TIN;
    }
}