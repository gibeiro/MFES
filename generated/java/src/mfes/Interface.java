package mfes;

import java.io.*;

import org.overture.codegen.runtime.VDMSet;

public final class Interface {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static Network network = new Network(0.1);
	
	public static void main(String[] args) {mainMenu();}
	
	public static void mainMenu() {
		System.out.println("Small Business Discount Network");
		System.out.println("\t1.Add Customer");
		System.out.println("\t2.Add Merchant");
		System.out.println("\t3.Remove Customer");
		System.out.println("\t4.Remove Merchant");
		System.out.println("\t5.Get Customer");
		System.out.println("\t6.Get Merchant");		
		System.out.println("\t7.List Customers");
		System.out.println("\t8.List Merchants");
		System.out.println("\t9.Transfer Credit");
		System.out.println("\t10.Purchase Product");
		System.out.println("\t11.Set Network Fee");
		System.out.println("\t12.Set Product Discount");
		System.out.print(": ");
		
		try {
			switch(reader.readLine()) {
			default: mainMenu(); break;
			case "1": addCustomer(); break;
			case "2": addMerchant(); break;
			case "3": removeCustomer(); break;
			case "4": removeMerchant(); break;
			case "5": getCustomer(); break;
			case "6": getMerchant(); break;			
			case "7": listCustomers(); break;
			case "8": listMerchants(); break;
			case "9": transferCredit(); break;
			case "10": purchaseProduct(); break;
			case "11": setFee(); break;
			case "12": setDiscount(); break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void setFee() {
		try {
			System.out.println("Set Fee");
			Float fee = (Float) network.getFee();
			System.out.println("\tCurrent Fee: "+fee);
			System.out.print("\tNew Fee: ");
			fee = Float.parseFloat(reader.readLine());
			network.setFee(fee);
		} catch (Exception e) {
			System.out.println("Invalid input!");
		}
		mainMenu();
	}
	public static void addCustomer() {		
		try {
			System.out.println("Add Customer");
			System.out.print("\tName: ");
			String name = reader.readLine();
			System.out.print("\tBalance: ");
			Float balance = Float.parseFloat(reader.readLine());
			System.out.print("\tReferrer: ");
			String referrer_name = reader.readLine();
			Customer referrer, customer;
			try {
				referrer = network.getCustomer(referrer_name);
				customer = new Customer(referrer, name, balance);
			}
			catch (IllegalArgumentException e) {
				System.out.println("\tCustomer '"+referrer_name+"' doesn't exist!");
				customer = new Customer(name, balance);
			}			
			network.addCustomer(customer);			
		} catch (Exception e1) {
			System.out.println("\tInvalid Input!");

		}		
		mainMenu();
	}
	public static void addMerchant() {
		try {
			System.out.println("Add Merchant");
			System.out.print("\tName: ");
			String name = reader.readLine();
			System.out.print("\tBalance: ");
			Float balance = Float.parseFloat(reader.readLine());
			System.out.print("\tReferrer: ");
			String referrer_name = reader.readLine();
			Merchant referrer, merchant;
			try {
				referrer = network.getMerchant(referrer_name);
				merchant = new Merchant(referrer, name, balance);
			}
			catch (IllegalArgumentException e) {
				System.out.println("\tMerchant '"+referrer_name+"' doesn't exist!");
				merchant = new Merchant(name, balance);
			}			
			
			network.addMerchant(merchant);
			
		} catch (Exception e1) {
			System.out.println("\tInvalid Input!");
		}
		
		mainMenu();
	}
	public static void removeCustomer() {
		try {
			System.out.println("Remove Customer");
			System.out.print("\tName: ");
			String name = reader.readLine();
			 network.removeCustomer(name);
		} catch (Exception e1) {}		
		mainMenu();
	}
	public static void removeMerchant() {
		try {
			System.out.println("Remove Merchant");
			System.out.print("\tName: ");
			String name = reader.readLine();
			 network.removeMerchant(name);
		} catch (Exception e1) {}		
		mainMenu();
	}
	public static void getCustomer() {
		try {
			System.out.println("Get Customer");
			System.out.println("\tName: ");
			String name = reader.readLine();			
			Customer customer = network.getCustomer(name);
			Customer referrer = customer.getReferrer();
			Float balance = (Float) customer.getBalance();
			Float credits = (Float) customer.getCredits();
			System.out.println("\tBalance: "+balance);
			System.out.println("\tCredits: "+credits);
			if(referrer == null) System.out.println("\tReferrer: null");
			else System.out.println("\tReferrer: "+referrer.getName());
		} catch (Exception e) {
			System.out.println("Invalid input!");
		}
		mainMenu();
	}
	public static void getMerchant() {
		try {
			System.out.println("Get Merchant");
			System.out.println("\tName: ");
			String name = reader.readLine();			
			Merchant merchant = network.getMerchant(name);
			Float balance = (Float)merchant.getBalance();
			Float bonus = (Float)merchant.getBonus();
			VDMSet products = merchant.getProducts();
			System.out.println("\tBalance: "+balance);
			System.out.println("\tBonus: "+bonus);
			if(products.size() == 0) System.out.println("\tNo products available!");
			else {
				System.out.println("\t"+products.size()+" products available!");
				System.out.println("Name\t\tPrice\tStock\tDiscount");
				
				for(Object o: products) {
					Product p = (Product) o;
					System.out.println(p.getName()+"\t"+p.getPrice()+"\t"+p.getStock()+"\t"+p.getDiscount());
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("Invalid input!");
		}
		mainMenu();
	}
	public static void listCustomers() {
		System.out.println("List Customers");
		VDMSet customers = network.getCustomers();
		
		if(customers.size() == 0) System.out.println("\tNo customers found!");
		else {
			System.out.println("\t"+customers.size()+" customers found!");
			System.out.println("Name\t\tBalance\tCredits");
			for(Object o : customers) {
				Customer c = (Customer)o;
				System.out.println(c.getName()+"\t\t"+c.getBalance()+"\t"+c.getCredits());
			}	
		}
		mainMenu();		
	}
	public static void listMerchants() {
		System.out.println("List Merchants");
		VDMSet merchants = network.getMerchants();
		
		if(merchants.size() == 0) System.out.println("\tNo merchants found!");
		else {
			System.out.println("\t"+merchants.size()+" merchants found!");
			System.out.println("Name\t\tBalance\tBonus");
			for(Object o : merchants) {
				Merchant m = (Merchant)o;
				System.out.println(m.getName()+"\t\t"+m.getBalance()+"\t"+m.getBonus());
			}	
		}
		mainMenu();
	}
	public static void transferCredit() {
		try {
			System.out.println("Transfer Credit");
			System.out.print("\tSending Customer: ");
			String customer1 = reader.readLine();
			System.out.print("\tReceiveing Customer: ");
			String customer2 = reader.readLine();
			System.out.print("\tAmount: ");
			Float amount = Float.parseFloat(reader.readLine());
			network.transferCredit(customer1, customer2, amount);
		} catch (Exception e1) {
			System.out.println("Invalid input!");
		}		
		mainMenu();
	}
	public static void purchaseProduct() {
		try {
			System.out.println("Purchase Product");
			System.out.println("\tCurrency:");
			System.out.println("\t\t1.Money");
			System.out.println("\t\t2.Credit");
			System.out.print(": ");
			Object currency = null;
			switch(reader.readLine()) {
				default: purchaseProduct(); break;
				case "1": currency = mfes.quotes.MoneyQuote.getInstance(); break;
				case "2": currency = mfes.quotes.CreditQuote.getInstance(); break;
			}			
			System.out.print("\tBuying Customer: ");
			System.out.println("Purchase Product");
			System.out.print("\tBuying Customer: ");
			String customer = reader.readLine();
			System.out.print("\tSelling Merchant: ");
			String merchant = reader.readLine();
			System.out.print("\tProduct: ");
			String product = reader.readLine();
			System.out.print("\tQuantity: ");
			Integer quantity = Integer.parseInt(reader.readLine());
			network.purchaseProduct(currency, customer, merchant, product, quantity);
		} catch (Exception e1) {}		
		mainMenu();
	}
	public static void addProduct() {
		//TODO		
	}
	public static void setDiscount() {
		//TODO		
	}

}
