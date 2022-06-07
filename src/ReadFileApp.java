import java.util.*;
import java.io.*;

class ReadFileApp {
	private ArrayList<Product> products;
	private ArrayList<Seller> sellers;
	private ArrayList<ProductSale> sales;
	private ArrayList<CardTransaction> transactions;
	
	public ReadFileApp() {
		this.products = new ArrayList<Product>();
		this.sellers = new ArrayList<Seller>();
		this.sales = new ArrayList<ProductSale>();
		this.transactions = new ArrayList<CardTransaction>();
	}
	
	public void ReadFile(String file) {
		BufferedReader reader = null;
		System.out.printf("[Reading from file: %s]\n", file);
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			
			line = (line != null) ? line.trim() : line;
			
			String ID, sID, reason, num, TIN, fn, ln;
			double amount, yr;
			// HashMap to store the input values (eg. for salesman: <"CODE", "1001">
			HashMap<String, String> item = new HashMap<String, String>();
			
			// Stack with "{" and "}" to check when an input block "{...}" ends
			Stack<String> checkBraces = new Stack<String>();
			
			switch (line) {
			case "BANKITEM_LIST":
				while((line = reader.readLine() ) != null) {
					line = line.trim();
					
					if(line.equals("{")) checkBraces.push(line);
					
					// If stack has a size of 1, it only contains
					// the "{" at the beginning of the file
					// so at that point we are at the end of the file
					// (last "}" character)
					else if(line.equals("}") && checkBraces.size() > 1) {
						checkBraces.pop();
						ID = item.get("CODE");
						num = item.get("DESCR");
						num = (num != null) ? num.substring(0, num.length()-1) : num; // Removes quotes at the end
						TIN = item.get("AFM");
						
						if(item.get("TYPE").equals("Loan")) {
							amount = Double.parseDouble(item.get("AMOUNT"));
							yr = Double.parseDouble(item.get("YRATE"));
							products.add(new Loan(ID, num, TIN, 0, amount, yr, true));
						} else {
							double com = Double.parseDouble(item.get("COM"));
							double lim = Double.parseDouble(item.get("LIM"));
							double ylim = Double.parseDouble(item.get("YLIM"));
							products.add(new CreditCard(ID, num, TIN, 0, com, lim, ylim, true));
						}
						item.clear();					
						
					} else if(line.contains(" ")) {
						String[] splited;
						if(line.contains("\"")) splited = line.split(" \"");
						else splited = line.split("\\s+");
						item.put(splited[0], splited[1]);
					}
				}
				break;
				
			case "SALES_LIST":
				while((line = reader.readLine() ) != null) {
					line = line.trim();
					
					if(line.equals("{")) checkBraces.push(line);
					
					else if(line.equals("}") && checkBraces.size() > 1) {
						checkBraces.pop();
						sID = item.get("SALESMAN_CODE");
						ID = item.get("BANKITEM_CODE");
						reason = item.get("REASON");
						reason = (reason != null) ? reason.substring(0, reason.length()-1) : reason; // Removes quotes at the end
						sales.add(new ProductSale(sID, ID, reason));
						item.clear();					
						
					} else if(line.contains(" ")) {
						String[] splited;
						if(line.contains("\"")) splited = line.split(" \"");
						else splited = line.split("\\s+");
						item.put(splited[0], splited[1]);
					}
				}
				break;
				
				
			case "SALESMAN_LIST":
				while((line = reader.readLine() ) != null) {
					line = line.trim();
					
					if(line.equals("{")) checkBraces.push(line);
					
					else if(line.equals("}") && checkBraces.size() > 1) {
						checkBraces.pop();
						ID = item.get("CODE");
						ln = item.get("SURNAME");
						ln = (ln != null) ? ln.substring(0, ln.length()-1) : ln; // Removes quotes at the end
						
						fn = item.get("FIRSTNAME");
						fn = (fn != null) ? fn.substring(0, fn.length()-1) : fn; // Removes quotes at the end
						
						TIN = item.get("AFM");
						
						sellers.add(new Seller(ID, fn, ln, TIN, true));
						item.clear();
						
					} else if(line.contains(" ")) {
						String[] splited;
						if(line.contains("\"")) splited = line.split(" \"");
						else splited = line.split("\\s+");
						item.put(splited[0], splited[1]);
					}
				}
				break;
				
			case "TRN_LIST":
				while((line = reader.readLine() ) != null) {
					line = line.trim();
					
					if(line.equals("{")) checkBraces.push(line);
					
					else if(line.equals("}") && checkBraces.size() > 1) {				
						checkBraces.pop();
						ID = item.get("BANKITEM_CODE");
						amount = Double.parseDouble(item.get("VAL"));
						
						reason = item.get("JUSTIFICATION");
						reason = (reason != null) ? reason.substring(0, reason.length()-1) : reason; // Removes quotes at the end
						
						transactions.add(new CardTransaction(ID, amount, reason));
						item.clear();
						
					} else if(line.contains(" ")) {
						String[] splited;
						if(line.contains("\"")) splited = line.split(" \"");
						else splited = line.split("\\s+");
						item.put(splited[0], splited[1]);
					}
				}
				break;
			}
			reader.close();			
		} catch (IOException e) {
			System.out.printf("[Error when reading from file: %s]\nException: [%s]", file, e);
		}
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public ArrayList<ProductSale> getSales() {
		return sales;
	}
	
	public ArrayList<Seller> getSellers() {
		return sellers;
	}
	
	public ArrayList<CardTransaction> getTransactions() {
		return transactions;
	}
	

}
