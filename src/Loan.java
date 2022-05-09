class Loan extends Product {
	private double amount;
	private double yearlyRate;
	
	Loan(String ID, String number, String TIN, double amount, double yearlyRate) {
		super(ID, number, TIN);
		this.amount = amount;
		this.yearlyRate = yearlyRate;
	}
	
	double getAmount() {
		return amount;
	}
	
	double getYearlyRate() {
		return yearlyRate;
	}
	
	public String toString() {
		return String.format("%s | Loan Amount: %s | Yearly Interest Rate: %s", super.toString(), amount, yearlyRate);
	}
}
