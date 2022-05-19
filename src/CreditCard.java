import java.util.ArrayList;

class CreditCard extends Product{
	private double commission;
	private double moveLimit;
	private double yearLimit;
	private double cashFlow;
	private ArrayList<CardTransaction> transactions;
	
	CreditCard(String ID, String number, String TIN, int seller_key, double commission, double moveLimit, double yearLimit) {
		super(ID, number, TIN, seller_key);
		this.commission = commission;
		this.moveLimit = moveLimit;
		this.yearLimit = yearLimit;
		this.cashFlow = 0;
		this.transactions = new ArrayList<CardTransaction>();
	}
	
	void addTransaction(CardTransaction ct) {
		transactions.add(ct);
	}
	
	void increaseCashFlow(double amount) {
		cashFlow += amount;
	}
	
	ArrayList<CardTransaction> getTransactions() {
		return transactions;
	}
	
	double getCommission() {
		return commission;
	}
	
	double getCashFlow() {
		return cashFlow;
	}
	
	double getMoveLimit() {
		return moveLimit;
	}
	
	double getYearLimit() {
		return yearLimit;
	}
	
	public String toString() {
		return String.format("%s | Commission%% : %.2f%% | Move Limit: %.2f Euro | Year Limit: %.2f Euro ", super.toString(), commission*100, moveLimit, yearLimit);
	}
}