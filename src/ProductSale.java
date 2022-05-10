class ProductSale extends SellerAction {
    private String sellerID;
    private String productID;
    
    ProductSale(String sellerID,String productID,String reason){
    	super(reason);
        this.sellerID=sellerID;
        this.productID=productID;
    }
    
    String getSellerID(){
        return sellerID;
    }
    
    String getProductID(){
        return productID;
    }
}