Class ProductSale {
    private String sellerID;
    private String productID;
    private String reason;
    ProductSale(String sellerID,String productID,String reason){
        this.sellerID=sellerID;
        this.productID=productID;
        this.reason=reason;

    }
    String getSellerID(){
        return this.sellerID;
    }
    String getProductID(){
        return this.productID;
    }
    String getReason(){
        return this.reason;
    }


}