class Product extends BankProducts {
	protected String ID;
	protected String number;
	protected String TIN;
	protected int seller_key;
	
	Product(String ID, String number, String TIN, int seller_key) {
		this.ID = ID;
		this.number = number;
		this.TIN = TIN;
		this.seller_key = seller_key;
		super.insertProduct(this);
	}

	void setSeller(int key) {
		seller_key = key;
	}
	
	String getID() {
		return ID;
	}
	
	String getNumber() {
		return number;
	}
	
	String getTIN() {
		return TIN;
	}
	
	int getSellerKey() {
		return seller_key;
	}
	
	public String toString() {
		return String.format("ID: %s | Number: %s | TIN: %s", ID, number, TIN);
	}
}
