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
		if(seller.getSales().isEmpty()) {
			bankDict.addSellerCommission(seller.getID(), 0);
			return 0;
		}
		
		ArrayList<ProductSale> sales = seller.getSales();

		double loan_amount = 0;
		double com = 0;
		
		for(ProductSale psale : sales) {
			String productID = psale.getProductID();
			
			if(bankDict.containsCard(productID)) {
				ArrayList<CardTransaction> cts = bankDict.getCard(productID).getTransactions();
				
				for(CardTransaction ct : cts) {
					double cardCom = ct.getAmount() * bankDict.getInterestByCard(productID);
					com += cardCom;
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
		
		bankDict.addSellerCommission(seller.getID(), com);
		return com;
	}

	public static void main(String[] args) {
		new Seller("014", "Aleksandar 'Sasha'", "Vezenkov", "581510137");
		new Seller("002", "Tyler", "Dorsey", "232105129");
		new Seller("011", "Kostas", "Sloukas", "264908119");
		new Seller("077", "Shaquille", "McKissic", "191508067");
		new Seller("015", "Giorgos", "Printezis", "130301037");
		
		Loan loan = new Loan("L1", "01", "998751013", 250000.0, 0.03);
		bankDict.add("L1", loan);
		bankData.add(loan);
		
		loan = new Loan("L2", "02", "211103214", 20.5, 0.09);
		bankDict.add("L2", loan);
		bankData.add(loan);
		
		loan = new Loan("L3", "03", "120803199", 49.99, 0.07);
		bankDict.add("L3", loan);
		bankData.add(loan);
		
		loan = new Loan("L4", "04", "140203154", 9.0, 0.43);
		bankDict.add("L4", loan);
		bankData.add(loan);
		
		CreditCard card = new CreditCard("C1", "05", "03163589", 0.005, 2000.0, 100000.0);
		bankDict.add("C1", card);
		bankData.add(card);
		
		card = new CreditCard("C2", "06", "90367834", 0.007, 5000.0, 200000.0);
		bankDict.add("C2", card);
		bankData.add(card);
		
		card = new CreditCard("C3", "07", "98964824", 0.009, 10000.0, 500000.0);
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
						
						bankData.add(new Loan(pID, num, pTIN, amount, yr));
						
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
						
						bankData.add(new CreditCard(pID, num, pTIN, com, movelim, yearlim));
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
					}
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
					
					
					computeCommission(sellers.getSeller(key));
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
						computeCommission(seller);
					}
					break;
					
				case 9:
					if (returnToMenu("Showing the Commission Amount of all Sellers").equals("2")) {
						break;
					}
					
					double total_commissions = 0;
					
					for(Seller seller : sellers.getSellerList()) {
						double com = computeCommission(seller);
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
