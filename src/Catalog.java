import java.util.*;

class Catalog {

    private ArrayList<Product> productList = new ArrayList<Product>();
    private ArrayList<CardTransaction> cardTransactionList = new ArrayList<CardTransaction>();
    private ArrayList<ProductSale> productSaleList = new ArrayList<ProductSale>();

    public void add(Product newProduct) {
        this.productList.add(newProduct);
    }
    
    public void add(CardTransaction newTransaction) {
        this.cardTransactionList.add(newTransaction);
    }

    public void add(ProductSale newSale) {
        this.productSaleList.add(newSale);
    }
    
    public ArrayList<Product> getProducts() {
    	return productList;
    }

    public void printProducts() {
        for (Product item : productList) {
            System.out.println(item);
        }
    }
    
    public void printLoans() {
    	for (Product item : productList) {
    		if(item instanceof Loan) {
    			System.out.println(item);
    		}
    	}
    }
    
    public void printCCs() {
    	for (Product item : productList) {
    		if(item instanceof CreditCard) {
    			System.out.println(item);
    		}
    	}
    }
    
    public void printCardTransactions() {
        for (CardTransaction item : cardTransactionList) {
            System.out.println(item);
        }
    }

    public void printProductSales() {
        for (ProductSale item : productSaleList) {
            System.out.println(item);
        }
    }
}