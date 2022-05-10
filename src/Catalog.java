import java.util.*;

class Catalog {

    private ArrayList<Seller> sellerList = new ArrayList<Seller>();
    private ArrayList<Product> productList = new ArrayList<Product>();
    private ArrayList<CardTransaction> cardTransactionList = new ArrayList<CardTransaction>();
    private ArrayList<ProductSale> productSaleList = new ArrayList<ProductSale>();

    public void add(Seller newSeller) {
        this.sellerList.add(newSeller);
    }

    public void add(Product newProduct) {
        this.productList.add(newProduct);
    }
    
    public void add(CardTransaction newTransaction) {
        this.cardTransactionList.add(newTransaction);
    }

    public void add(ProductSale newSale) {
        this.productSaleList.add(newSale);
    }

    public void printSellers() {
        for (Seller item : sellerList) {
            System.out.println(item);
        }
    }

    public void printProducts() {
        for (Product item : productList) {
            System.out.println(item);
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