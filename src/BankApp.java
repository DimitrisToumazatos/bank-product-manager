import java.util.*;

public class BankApp {
	static Catalog bankData = new Catalog();
	static BankDict bankDict = new BankDict();
	
	public static double computeCommission(String sellerID) {
		if(!bankDict.containsSeller(sellerID)) {
			bankDict.addSellerCommission(sellerID, 0);
			return 0;
		}
		
		ArrayList<ProductSale> sales = bankDict.getActions(sellerID);

		double com = 0;
		double loan_amount = 0;
		
		for(ProductSale psale : sales) {
			String productID = psale.getProductID();
			
			if(bankDict.containsCard(productID)) {
				ArrayList<CardTransaction> cts = bankDict.getTransactionsByCard(productID);
				
				for(CardTransaction ct : cts) {
					com += ct.getAmount() * bankDict.getInterestByCard(productID);
				}
			} else {
				loan_amount += bankDict.getLoanAmount(productID);
			}
		}
		
		if(loan_amount < 500000) {
			com += loan_amount * 0.01;
			
		} else if (loan_amount < 2000000) {
			com += loan_amount * 0.02;
			
		} else {
			com += loan_amount * 0.025;
		}
		
		bankDict.addSellerCommission(sellerID, com);
		return com;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		bankData.add(new Seller("014", "Aleksandar 'Sasha'", "Vezenkov", "581510137"));
		bankData.add(new Seller("002", "Tyler", "Dorsey", "232105129"));
		bankData.add(new Seller("011", "Kostas", "Sloukas", "264908119"));
		bankData.add(new Seller("077", "Shaquille", "McKissic", "191508067"));
		bankData.add(new Seller("015", "Giorgos", "Printezis", "130301037"));
		
		bankData.add(new Loan("L1", "01", "998751013", 250000.0, 0.03));
		bankData.add(new Loan("L2", "02", "211103214", 20.5, 0.09));
		bankData.add(new Loan("L3", "03", "120803199", 49.99, 0.07));
		bankData.add(new Loan("L4", "04", "140203154", 9.0, 0.43));
		
		bankDict.addLoanID("L1", 250000);
		bankDict.addLoanID("L2", 20.5);
		bankDict.addLoanID("L3", 49.99);
		bankDict.addLoanID("L4", 9);
		
		bankData.add(new CreditCard("C1", "05", "03163589", 0.005, 2000.0, 100000.0));
		bankData.add(new CreditCard("C2", "06", "90367834", 0.007, 5000.0, 200000.0));
		bankData.add(new CreditCard("C3", "07", "98964824", 0.009, 10000.0, 500000.0));
		
		bankDict.addCardID("C1", 0.005);
		bankDict.addCardID("C2", 0.007);
		bankDict.addCardID("C3", 0.009);
		
		ProductSale aps = new ProductSale("014", "L1", "Reconstruction of Monaco BC home stadium.");
		ProductSale bps = new ProductSale("077", "L2", "Mandatory repairs for damages in OAKA."); 
		ProductSale cps = new ProductSale("011", "L3", "Purchase of chocolate.");
		ProductSale dps = new ProductSale("015", "C1", "Netflix account instead of communication with teammates.");
		ProductSale eps = new ProductSale("011", "C2", "Baby expenses.");
		ProductSale fps = new ProductSale("002", "C3", "Caffeine products, must wake up.");
		
		bankData.add(aps);
		bankData.add(bps);
		bankData.add(cps);
		bankData.add(dps);
		bankData.add(eps);
		bankData.add(fps);
		
		bankDict.addAction("014", aps);
		bankDict.addAction("077", bps);
		bankDict.addAction("011", cps);
		bankDict.addAction("015", dps);
		bankDict.addAction("011", eps);
		bankDict.addAction("002", fps);
		
		CardTransaction a = new CardTransaction("C1", 500.0, "5-year netflix plan");
		CardTransaction b = new CardTransaction("C1", 25.0, "Movie bill");
		CardTransaction c = new CardTransaction("C1", 10.0, "TV Show bill");
		CardTransaction d = new CardTransaction("C1", 7.50, "Movie ticket");
		bankData.add(a);
		bankDict.addAction("C1", a);
		bankData.add(b);
		bankDict.addAction("C1", b);
		bankData.add(c);
		bankDict.addAction("C1", c);
		bankData.add(d);
		bankDict.addAction("C1", d);
		
		CardTransaction a2 = new CardTransaction("C2", 100.0, "Diapers");
		CardTransaction b2 = new CardTransaction("C2", 200.0, "Baby swing");
		CardTransaction c2 = new CardTransaction("C2", 30.0, "Tricycle");
		CardTransaction d2 = new CardTransaction("C2", 5.0, "Pacifier");
		bankData.add(a2);
		bankDict.addAction("C2", a2);
		bankData.add(b2);
		bankDict.addAction("C2", b2);
		bankData.add(c2);
		bankDict.addAction("C2", c2);
		bankData.add(d2);
		bankDict.addAction("C2", d2);
		
		CardTransaction a3 = new CardTransaction("C3", 20.0, "Aeropress");
		CardTransaction b3 = new CardTransaction("C3", 8.00, "Coffee beans");
		CardTransaction c3 = new CardTransaction("C3", 200.0, "Espresso machine");
		CardTransaction d3 = new CardTransaction("C3", 9.99, "Caffeine gums");
		bankData.add(a3);
		bankDict.addAction("C3", a3);
		bankData.add(b3);
		bankDict.addAction("C3", b3);
		bankData.add(c3);
		bankDict.addAction("C3", c3);
		bankData.add(d3);
		bankDict.addAction("C3", d3);
		
		Boolean done = false;
		
		while(!done) {
			System.out.println("\n\n[MENU]");
			System.out.println("1. Insert Seller");
			System.out.println("2. Insert Bank Product");
			System.out.println("3. Insert Product Sale");
			System.out.println("4. Insert Credit Card Transaction");
			System.out.println("5. Show Loans");
			System.out.println("6. Compute a Seller's Commission");
			System.out.println("7. Show Credit Card Transactions Linked to a Seller");
			System.out.println("8. Compute Commissions for all Sellers");
			System.out.println("9. Show the Commission Amount of all Sellers");
			System.out.println("0. Exit");
			System.out.print("> ");
			
			int ans = Integer.parseInt(in.nextLine());
			switch(ans) {
				case 0:
					done = true;
					break;
					
				case 1:
					String ID, fn, ln, TIN;
					System.out.print("\nEnter Seller ID: ");
					ID = in.nextLine();
					System.out.print("\nEnter first name: ");
					fn = in.nextLine();
					System.out.print("\nEnter last name: ");
					ln = in.nextLine();
					System.out.print("\nEnter TIN: ");
					TIN = in.nextLine();
					bankData.add(new Seller(ID, fn, ln, TIN));
					break;
				
				case 2:
					System.out.print("\nDo you want to insert a loan or a credit card? (L/*): ");
					String prod = in.nextLine();
					
					if (prod.toUpperCase().equals("L")) {
						String pID, num, pTIN;
						double amount, yr;
						
						System.out.print("\nEnter Loan ID: ");
						pID = in.nextLine();
						System.out.print("\nEnter Loan number: ");
						num = in.nextLine();
						System.out.print("\nEnter TIN: ");
						pTIN = in.nextLine();
						System.out.print("\nEnter amount: ");
						amount = Double.parseDouble(in.nextLine());
						System.out.print("\nEnter yearly rate: ");
						yr = Double.parseDouble(in.nextLine());
						
						bankData.add(new Loan(pID, num, pTIN, amount, yr));
						
					} else {
						String pID, num, pTIN;
						double com, movelim, yearlim;
						
						System.out.print("\nEnter Credit Card ID: ");
						pID = in.nextLine();
						System.out.print("\nEnter Credit Card number: ");
						num = in.nextLine();
						System.out.print("\nEnter TIN: ");
						pTIN = in.nextLine();
						System.out.print("\nEnter commission: ");
						com = Double.parseDouble(in.nextLine());
						System.out.print("\nEnter max move limit: ");
						movelim = Double.parseDouble(in.nextLine());
						System.out.print("\nEnter max move limit: ");
						yearlim = Double.parseDouble(in.nextLine());
						
						bankData.add(new CreditCard(pID, num, pTIN, com, movelim, yearlim));
					}
					break;
				
				case 3:
					String sID, pID, reason;
					System.out.print("\nEnter Seller ID: ");
					sID = in.nextLine();
					
					boolean exists = false;
					
					for(Seller person : bankData.getSellerList()) {
						if(person.getID().equalsIgnoreCase(sID)) {
							exists = true;
							break;
						}
					}
					
					if(!exists) {
						System.out.println("No seller with that ID found.");
						break;
					}
 					
					System.out.print("\nEnter Product ID: ");
					pID = in.nextLine();
					System.out.print("\nEnter reason: ");
					reason = in.nextLine();
					
					if(bankDict.containsLoan(pID) || bankDict.containsCard(pID)) {
						ProductSale sale = new ProductSale(sID, pID, reason);
						bankData.add(sale);
						bankDict.addAction(sID, sale);
					} else {
						System.out.println("No product with that ID found.");
					}
					break;
				
				case 4:
					String cID, res;
					double amount;
					
					System.out.print("\nEnter Credit Card ID: ");
					cID = in.nextLine();
					
					if(!bankDict.containsCard(cID)) {
						System.out.println("\nNo Credit Card with that ID found.");
						break;
					}
					
					System.out.print("\nEnter Credit Card Amount: ");
					amount = Double.parseDouble(in.nextLine());
					System.out.print("\nEnter reason: ");
					res = in.nextLine();
					
					bankData.add(new CardTransaction(cID, amount, res));
					break;
					
				case 5:
					bankData.printLoans();
					break;
				
				case 6:
					bankData.printSellers();
					System.out.print("\nEnter Seller ID to compute commission for: ");
					String sellerID = in.nextLine();
					computeCommission(sellerID);
					break;
				
				case 7:
					bankData.printSellers();
					String SID;
					System.out.print("\nEnter Seller ID: ");
					SID = in.nextLine();
					ArrayList<ProductSale> salesOfSeller = bankDict.getActions(SID);
					for(ProductSale ps : salesOfSeller) {
						String prodID = ps.getProductID();
						if(bankDict.containsCard(prodID)) {
							for(CardTransaction ct : bankDict.getTransactionsByCard(prodID)) {
								System.out.println(ct);
							}
						}
					}
					break;
				
				case 8:
					for(String seller_ID : bankDict.getSellers()) {
						computeCommission(seller_ID);
					}
					break;
					
				case 9:
					double total_commissions = 0;
					
					for(Seller seller : bankData.getSellerList()) {
						double com = computeCommission(seller.getID());
						total_commissions += com;
						System.out.print("\n-------------------\n");
						System.out.printf("\n%s\nCommission Given: $%.2f\n", seller, com);
					}
					
					System.out.print("\n-------------------\n");
					
					System.out.printf("\nTotal Commissions: $%.2f\n", total_commissions);
			}
			
			
		}
		
		in.close();
	}

}
