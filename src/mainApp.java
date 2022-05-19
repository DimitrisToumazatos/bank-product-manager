/* 
 * 	Team Number: 055
 * 
 * 	Alexios Papadopoulos-Siountris 	3210154
 *  Dimitrios Fotogiannopoulos 		3210214
 *  Dimitrios Toumazatos 			3210199
 *  
 */


import java.util.*;

public class mainApp {
	static Catalog bankData = new Catalog();
	static BankDict bankDict = new BankDict();
	static BankSellers sellers = new BankSellers();
	static Scanner in = new Scanner(System.in);
	
	public static String returnToMenu(String action) {
		String cont = "";
		System.out.print(action + "... Proceed? (Yes: 1/No: 2)\n> ");
		
		do {
			cont = in.nextLine();
			if (!cont.equals("1") && !cont.equals("2")) {
				System.out.print("Please type a valid answer (Yes: 1/No: 2)\n> ");
			}
		} while (!cont.equals("1") && !cont.equals("2"));			
		
		return cont;
	}
	
	public static double computeCommission(Seller seller) {
		System.out.println(seller);
		
		if(seller.getSales().isEmpty()) {
			seller.setCommission("total", 0);
			return 0;
		}

		double com = 0;
		double maxLoanCom = 0;
		
		System.out.println("\n\t(Credit Card Commissions)");
		for(ProductSale sale : seller.getSales()) {
			String pID = sale.getProductID();
			
			if(!pID.equals("loans") && !pID.equals("total") && bankDict.getCard(pID) != null) {
				com += seller.getCommission(pID);
				
				System.out.println("\n[" +  bankDict.getCard(pID) + "]");
				System.out.printf("Seller Commission: %.2f Euro\n", seller.getCommission(pID));
			}
		}
		System.out.printf("\nTotal Credit Card Commission: %.2f Euro\n", com);
		
		System.out.println("\n\t(Loan Commissions)");
		for(ProductSale sale : seller.getSales()) {
			String pID = sale.getProductID();
			if(bankDict.containsLoan(pID)) {
				System.out.println("\n[" + bankDict.getLoan(pID) + "]");
				maxLoanCom += bankDict.getLoan(pID).getYearlyRate() * bankDict.getLoan(pID).getAmount();
			}
		}
		
		double loan_amount = seller.getCommission("loans");
		double loan_com = 0;
		
		if(loan_amount < 500000) {
			loan_com += loan_amount * 0.01;
			
		} else if (loan_amount < 2000000) {
			loan_com += loan_amount * 0.02;
			
		} else {
			loan_com += loan_amount * 0.025;
		}
		
		loan_com = (loan_com <= maxLoanCom) ? loan_com : maxLoanCom;
		
		com += loan_com;

		System.out.printf("\nTotal Loan Commission: %.2f Euro\n", loan_com);
		
		System.out.printf("\nTotal Seller Commission: %.2f Euro\n", com);
		
		seller.setCommission("total", com);
		return com;
	}

	public static void main(String[] args) {
		new Seller("014", "Aleksandar 'Sasha'", "Vezenkov", "581510137");
		new Seller("002", "Tyler", "Dorsey", "232105129");
		new Seller("011", "Kostas", "Sloukas", "264908119");
		new Seller("077", "Shaquille", "McKissic", "191508067");
		new Seller("015", "Giorgos", "Printezis", "130301037");
		
		Loan loan = new Loan("L1", "01", "998751013", 1, 250000.0, 0.03);
		bankDict.add("L1", loan);
		bankData.add(loan);
		sellers.getSeller(1).setCommission("loans", loan.getAmount());
		
		loan = new Loan("L2", "02", "211103214", 4, 20.5, 0.09);
		bankDict.add("L2", loan);
		bankData.add(loan);
		sellers.getSeller(4).setCommission("loans", loan.getAmount());
		
		loan = new Loan("L3", "03", "120803199", 3, 49.99, 0.07);
		bankDict.add("L3", loan);
		bankData.add(loan);
		sellers.getSeller(3).setCommission("loans", loan.getAmount());
		
		loan = new Loan("L4", "04", "140203154", 0, 9.0, 0.43);
		bankDict.add("L4", loan);
		bankData.add(loan);
		
		CreditCard card = new CreditCard("C1", "05", "03163589", 5, 0.005, 2000.0, 100000.0);
		bankDict.add("C1", card);
		bankData.add(card);
		
		card = new CreditCard("C2", "06", "90367834", 3, 0.007, 5000.0, 200000.0);
		bankDict.add("C2", card);
		bankData.add(card);
		
		card = new CreditCard("C3", "07", "98964824", 2, 0.009, 10000.0, 500000.0);
		bankDict.add("C3", card);
		bankData.add(card);
		
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
		
		sellers.getSeller(1).addSale(aps);
		sellers.getSeller(4).addSale(bps);
		sellers.getSeller(3).addSale(cps);
		sellers.getSeller(5).addSale(dps);
		sellers.getSeller(3).addSale(eps);
		sellers.getSeller(2).addSale(fps);
		
		CardTransaction a = new CardTransaction("C1", 500.0, "5-year netflix plan");
		CardTransaction b = new CardTransaction("C1", 25.0, "Movie bill");
		CardTransaction c = new CardTransaction("C1", 10.0, "TV Show bill");
		CardTransaction d = new CardTransaction("C1", 7.50, "Movie ticket");
		bankData.add(a);
		bankDict.getCard("C1").addTransaction(a);
		bankData.add(b);
		bankDict.getCard("C1").addTransaction(b);
		bankData.add(c);
		bankDict.getCard("C1").addTransaction(c);
		bankData.add(d);
		bankDict.getCard("C1").addTransaction(d);
		
		CardTransaction a2 = new CardTransaction("C2", 100.0, "Diapers");
		CardTransaction b2 = new CardTransaction("C2", 200.0, "Baby swing");
		CardTransaction c2 = new CardTransaction("C2", 30.0, "Tricycle");
		CardTransaction d2 = new CardTransaction("C2", 5.0, "Pacifier");
		bankData.add(a2);
		bankDict.getCard("C2").addTransaction(a2);
		bankData.add(b2);
		bankDict.getCard("C2").addTransaction(b2);
		bankData.add(c2);
		bankDict.getCard("C2").addTransaction(c2);
		bankData.add(d2);
		bankDict.getCard("C2").addTransaction(d2);
		
		CardTransaction a3 = new CardTransaction("C3", 20.0, "Aeropress");
		CardTransaction b3 = new CardTransaction("C3", 8.00, "Coffee beans");
		CardTransaction c3 = new CardTransaction("C3", 200.0, "Espresso machine");
		CardTransaction d3 = new CardTransaction("C3", 9.99, "Caffeine gums");
		bankData.add(a3);
		bankDict.getCard("C3").addTransaction(a3);
		bankData.add(b3);
		bankDict.getCard("C3").addTransaction(b3);
		bankData.add(c3);
		bankDict.getCard("C3").addTransaction(c3);
		bankData.add(d3);
		bankDict.getCard("C3").addTransaction(d3);
		
		
		/* Initializes the commissions for all the above card transactions */
		
		for(CreditCard cc : bankDict.getCCs()) {
			double transactionCommission = 0;
			
			for(CardTransaction ct : cc.getTransactions()) {
				transactionCommission += ct.getAmount() * cc.getCommission();
			}
			
			sellers.getSeller(cc.getSellerKey()).setCommission(cc.getID(), transactionCommission);
		}
		
		
		
		Boolean done = false;
		
		while(!done) {
			System.out.println("\n\n\t[MENU]");
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
			
			String sID, pID;
			int key;
			CardTransaction ct;
			switch(ans) {			
				case 0:
					done = true;
					break;
					
				case 1:
					if (returnToMenu("Inserting Seller").equals("2")) {
						break;
					}
					
					String fn, ln, TIN;					
					
					System.out.print("\nEnter Seller ID: ");
					sID = in.nextLine();
					System.out.print("\nEnter first name: ");
					fn = in.nextLine();
					System.out.print("\nEnter last name: ");
					ln = in.nextLine();
					System.out.print("\nEnter TIN: ");
					TIN = in.nextLine();
					new Seller(sID, fn, ln, TIN);
					break;
				
				case 2:										
					if (returnToMenu("Inserting Bank Product").equals("2")) {
						break;
					}
					
					String prod = "";
					while(!prod.equals("1") && !prod.equals("2") && !prod.equals("3")) {
						System.out.print("\nDo you want to insert a loan or a credit card? (1: Loan/2: Credit Card/3: Exit): ");
						prod = in.nextLine().strip();
					}
				
					if (prod.equals("1")) {
						String num, pTIN;
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
						
						bankData.add(new Loan(pID, num, pTIN, 0, amount, yr));
						
					} else if (prod.equals("2")) {
						String num, pTIN;
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
						System.out.print("\nEnter max year limit: ");
						yearlim = Double.parseDouble(in.nextLine());
						
						bankData.add(new CreditCard(pID, num, pTIN, 0, com, movelim, yearlim));
					}
					break;
				
				case 3:
					if (returnToMenu("Inserting Product Sale").equals("2")) {
						break;
					}
					
					
					String reason;
					sellers.printSellers();
					System.out.print("\nEnter Seller Key: ");
					key = Integer.parseInt(in.nextLine());
					
					if(!sellers.sellerExists(key)) {
						System.out.println("No seller with that key found.");
						break;
					}
					
					
 					sID = sellers.getSeller(key).getID();
					System.out.print("\nEnter Product ID: ");
					pID = in.nextLine();
					System.out.print("\nEnter reason: ");
					reason = in.nextLine();
					
					if(bankDict.containsLoan(pID) || bankDict.containsCard(pID)) {
						ProductSale sale = new ProductSale(sID, pID, reason);
						bankData.add(sale);
						sellers.getSeller(key).addSale(sale);
					} else {
						System.out.println("No product with that ID found.");
						break;
					}
					
					if(bankDict.containsCard(pID)) {
						bankDict.getCard(pID).setSeller(key);
						break;
					}
					
					bankDict.getLoan(pID).setSeller(key);
					
					sellers.getSeller(key).setCommission("loans", sellers.getSeller(key).getCommission("loans") + bankDict.getLoan(pID).getAmount());
					
					break;
				
				case 4:
					if (returnToMenu("Inserting Credit Card Tansaction").equals("2")) {
						break;
					}
					
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
					
					CreditCard temp = bankDict.getCard(cID);
					Seller tempSeller = sellers.getSeller(temp.getSellerKey());
					double sellerCommission = tempSeller.getCommission(cID) + amount * temp.getCommission();
					tempSeller.setCommission(cID, sellerCommission);
					
					ct = new CardTransaction(cID, amount, res);
					bankData.add(ct);
					bankDict.getCard(cID).addTransaction(ct);
					break;
					
				case 5:
					if (returnToMenu("Showing Loans").equals("2")) {
						break;
					}
					
					bankData.printLoans();
					break;
				
				case 6:
					if (returnToMenu("Computing a Seller's Commission").equals("2")) {
						break;
					}
					
					sellers.printSellers();
					System.out.print("\nEnter Seller Key: ");
					key = Integer.parseInt(in.nextLine());
					
					if(!sellers.sellerExists(key)) {
						System.out.println("No seller with that key found.");
						break;
					}
					
					System.out.print("\n-------------------\n");
					System.out.println("\n\t(Seller)");
					computeCommission(sellers.getSeller(key));
					System.out.print("\n-------------------\n");
					break;
				
				case 7:
					if (returnToMenu("Showing Seller-Linked Credit Card Transactions").equals("2")) {
						break;
					}
					
					sellers.printSellers();
					key = Integer.parseInt(in.nextLine());
					
					if(!sellers.sellerExists(key)) {
						System.out.println("No seller with that key found.");
						break;
					}
					
					ArrayList<ProductSale> salesOfSeller = sellers.getSeller(key).getSales();
					for(ProductSale ps : salesOfSeller) {
						String prodID = ps.getProductID();
						if(bankDict.containsCard(prodID)) {
							for(CardTransaction transaction : bankDict.getCard(prodID).getTransactions()) {
								System.out.println(transaction);
							}
						}
					}
					break;
				
				case 8:
					if (returnToMenu("Computing Commission for all Sellers").equals("2")) {
						break;
					}
					
					for(Seller seller : sellers.getSellerList()) {
						System.out.print("\n-------------------\n");
						System.out.println("\n\t(Seller)");
						computeCommission(seller);
					}
					System.out.print("\n-------------------\n");
					break;
					
				case 9:
					if (returnToMenu("Showing the Commission Amount of all Sellers").equals("2")) {
						break;
					}
					
					double total_commissions = 0;
					
					for(Seller seller : sellers.getSellerList()) {
						double com = seller.getCommission("total");
						total_commissions += com;
						System.out.print("\n-------------------\n");
						System.out.printf("\n%s\nCommission Given: %.2f Euro\n", seller, com);
					}
					
					System.out.print("\n-------------------\n");
					
					System.out.printf("\nTotal Commissions: %.2f Euro\n", total_commissions);
					break;
			}
			
			
		}
		
		in.close();
	}
	

}
