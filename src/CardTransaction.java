class CardTransaction {

    private String cardID;
    private String reason;
    private double amount;
    
    CardTransaction(String cardID, double amount, String reason) {
        this.cardID = cardID;
        this.amount = amount;
        this.reason = reason;
    }

    String getCardID() {
        return this.cardID;
    }

    double getAmount() {
        return this.amount;
    }
    
    public String toString() {
        return "Card ID: "+ this.cardID + "\nAmount: " + this.amount + "\nReason: " + this.reason;
    }
}