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
		return String.format("%s | Loan Amount: %.2f Euro | Yearly Interest Rate: %.2f%%", super.toString(), amount, yearlyRate*100);
	}
}
