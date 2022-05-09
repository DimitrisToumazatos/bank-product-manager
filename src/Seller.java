class Seller {

    private String ID;
    private String firstName;
    private String lastName;
    private String TIN;

    Seller(String ID, String firstName, String lastName, String TIN) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.TIN = TIN;
    }

    String getID() {
        return ID;
    }
    
    public String toString() {
        return "ID Number: " + this.ID + "\nLast Name: " + this.lastName + "\nFirst Name: " + this.firstName;
    }
}