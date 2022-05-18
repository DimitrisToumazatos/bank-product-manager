import java.util.ArrayList;

class CreditCard extends Product{
	private double commission;
	private double moveLimit;
	private double yearLimit;
	private ArrayList<CardTransaction> transactions;
	
	CreditCard(String ID, String number, String TIN, double commission, double moveLimit, double yearLimit) {
		super(ID, number, TIN);
		this.commission = commission;
		this.moveLimit = moveLimit;
		this.yearLimit = yearLimit;
		this.transactions = new ArrayList<CardTransaction>();
	}
	
	void addTransaction(CardTransaction ct) {
		transactions.add(ct);
	}
	
	ArrayList<CardTransaction> getTransactions() {
		return transactions;
	}
	
	double getCommission() {
		return commission;
	}
	
	double getMoveLimit() {
		return moveLimit;
	}
	
	double getYearLimit() {
		return yearLimit;
	}
	
	public String toString() {
		return String.format("%s | Commission% : %f%% | Move Limit: %f Euro | Year Limit: %f Euro ", super.toString(), commission, moveLimit, yearLimit);
	}
}