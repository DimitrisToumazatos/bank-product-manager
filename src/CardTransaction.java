Class CardTransaction{

    private String cardID;
    private double amount;
    private String reason;
    CardTransaction(String cardID,double amount,String reason){
        this.cardID=cardID;
        this.amount=amount;
        this.reason=reason;
    }

    String getCardID(){
        return this.cardID;
    }

    String amount(){
        return this.amount;
    }

    String reason(){
        return this.reason;
    }
    
    public String toString(){
        return "Card ID: "+this.cardID+"\nAmount: "+this.amount+"\nReason: "+this.reason;
    }
}