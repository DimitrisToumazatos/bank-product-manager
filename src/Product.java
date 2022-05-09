class Product {
	protected String ID;
	protected String number;
	protected String TIN;
	
	Product(String ID, String number, String TIN) {
		this.ID = ID;
		this.number = number;
		this.TIN = TIN;
	}
	
	String getID() {
		return ID;
	}
	
	String getNumber() {
		return number;
	}
	
	public String toString() {
		return String.format("[Product] ID: %s | Number: %s | TIN: %s", ID, number, TIN);
	}
}
