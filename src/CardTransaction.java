class CardTransaction extends SellerAction{

    private String cardID;
    private double amount;
    
    CardTransaction(String cardID,double amount,String reason){
    	super(reason);
        this.cardID = cardID;
        this.amount = amount;
    }

    String getCardID(){
        return this.cardID;
    }

    double getAmount(){
        return this.amount;
    }
    
    public String toString(){
        return "Card ID: "+ this.cardID + "\nAmount: " + this.amount + "\nReason: " + super.getReason();
    }
}