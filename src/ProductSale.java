class ProductSale {
    private String sellerID;
    private String productID;
    private String reason;
    
    ProductSale(String sellerID,String productID,String reason) {
        this.sellerID=sellerID;
        this.productID=productID;
        this.reason = reason;
    }
    
    String getSellerID() {
        return sellerID;
    }
    
    String getProductID() {
        return productID;
    }
    
    public String toString() {
    	return "Seller ID: " + sellerID + " | Product ID: " + productID + " | " + "Reason: " + reason;
    }
}