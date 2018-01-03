package mfes;

import java.io.*;

import org.overture.codegen.runtime.VDMSet;

public final class Interface {

	static BufferedReader reader;
	static Network network;
	
	public static void main(String[] args) {
		reader = new BufferedReader(new InputStreamReader(System.in));
		network = new Network(0.1);
		Customer customer1 = new Customer("customer1",100.0);
		Customer customer2 = new Customer(customer1,"customer2",20.0);
		Customer customer3 = new Customer("customer3",40.0);
		Customer customer4 = new Customer(customer3,"customer4",80.0);
		Customer customer5 = new Customer("customer5",60.0);
		
		Merchant merchant1 = new Merchant("merchant1",0.0);
		Merchant merchant2 = new Merchant(merchant1,"merchant2",0.0);
		Merchant merchant3 = new Merchant("merchant3",0.0);
		Merchant merchant4 = new Merchant(merchant3,"merchant4",0.0);
		Merchant merchant5 = new Merchant("merchant5",0.0);
		
		Product product1 = new Product("product1",5.0,60,0.08);
		Product product2 = new Product("product2",10.0,25,0.1);
		Product product3 = new Product("product3",21.0,7,0.08);
		Product product4 = new Product("product4",15.0,30,0.08);
		Product product5 = new Product("product5",64.0,4,0.12);
		
		merchant1.addProduct(product1);
		merchant2.addProduct(product2);
		merchant3.addProduct(product3);
		merchant4.addProduct(product4);
		merchant5.addProduct(product5);
		
		network.addCustomer(customer1);
		network.addCustomer(customer2);
		network.addCustomer(customer3);
		network.addCustomer(customer4);
		network.addCustomer(customer5);
		
		network.addMerchant(merchant1);
		network.addMerchant(merchant2);
		network.addMerchant(merchant3);
		network.addMerchant(merchant4);
		network.addMerchant(merchant5);
		
		mainMenu();
	}
	
	public static void mainMenu() {
		System.out.println("Small Business Discount Network");
		System.out.println("\tFee: "+network.getFee());
		System.out.println("\t1.Add Customer");
		System.out.println("\t2.Add Merchant");
		System.out.println("\t3.Remove Customer");
		System.out.println("\t4.Remove Merchant");
		System.out.println("\t5.Get Customer");
		System.out.println("\t6.Get Merchant");		
		System.out.println("\t7.List Customers");
		System.out.println("\t8.List Merchants");		
		System.out.println("\t9.Set Network Fee");
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
			case "9": setFee(); break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void setFee() {
		try {
			System.out.println("Set Fee");
			System.out.print("\tNew Fee: ");
			Float fee = Float.parseFloat(reader.readLine());
			network.setFee(fee);
		} catch (Exception e) {
			System.out.println("\tInvalid input!");
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
			Customer referrer, customer = null;
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
		} catch(IllegalAccessError e) {
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
		}catch(IllegalAccessError e) {
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
	
	public static void setBalance(User user) {
		try {
			System.out.println("Set Balance");
			System.out.print("\tBalance: ");
			Float balance = Float.parseFloat(reader.readLine());
			user.setBalance(balance);
		} catch (Exception e1) {
			System.out.println("Invalid input!");
		}
		if(user.getClass() == Merchant.class) getMerchant((Merchant)user);
		else getCustomer((Customer)user);
	}
	
	public static void getCustomer(Customer customer) {
		try {
			System.out.println("Customer");
			System.out.println("\tName: "+customer.getName());
			System.out.println("\tBalance: "+customer.getBalance());
			System.out.println("\tCredits: "+customer.getCredits());
			Customer referrer = customer.getReferrer();
			if(referrer != null) System.out.println("\tReferrer: "+referrer.getName());
			
			System.out.println("\t1.Set Balance");
			System.out.println("\t2.Purchase Product");
			System.out.println("\t3.Transfer Credit");
			System.out.println("\t0.Back");
			System.out.print(":");
			
			switch(reader.readLine()) {
			default: getCustomer(customer); break;
			case "1": setBalance(customer); break;
			case "2": purchaseProduct(customer); break;
			case "3": transferCredit(customer); break;
			case "0": mainMenu(); break;
		}	
			
		} catch (Exception e) {
			System.out.println("Invalid input!");
		}		
		
	}	
	
	public static void getCustomer() {
		try {
			System.out.println("Get Customer");
			System.out.print("\tName: ");
			String name = reader.readLine();			
			Customer customer = network.getCustomer(name);
			getCustomer(customer);
		} catch (Exception e) {
			System.out.println("Invalid input!");
		}
		mainMenu();
	}	
	
	public static void removeProduct(Merchant merchant) {
		try {
			System.out.println("Remove Product");
			System.out.print("\tName: ");
			String product_name = reader.readLine();
			merchant.removeProduct(product_name);
						
		}catch(Exception e) {System.out.println("Invalid input!");}
		getMerchant(merchant);
	}	
	public static void listProducts(Merchant merchant) {
		try {
			System.out.println("List Products");
			VDMSet products = merchant.getProducts();
			if(products.size() == 0) System.out.println("\tNo products available!");
			else {
				System.out.println("\t"+products.size()+" products available!");
				System.out.println("Name\t\tPrice\tStock\tDiscount");
				
				for(Object o: products) {
					Product p = (Product) o;
					System.out.println(p.getName()+"\t"+p.getPrice()+"\t"+p.getStock()+"\t"+p.getDiscount());
				}
			}
		}catch(Exception e) {}
		getMerchant(merchant);
	}
	public static void addProduct(Merchant merchant) {
		try {
			System.out.println("Add Product");
			System.out.print("\tName: ");
			String product_name = reader.readLine();
			System.out.print("\tPrice: ");
			Float price = Float.parseFloat(reader.readLine());
			System.out.print("\tStock: ");
			Integer stock = Integer.parseInt(reader.readLine());
			System.out.print("\tDiscount: ");
			Float discount = Float.parseFloat(reader.readLine());
			Product product = new Product(product_name,price,stock,discount);
			merchant.addProduct(product);
			
		}catch(Exception e) {System.out.println("Invalid input!");}
		catch(IllegalAccessError e) {
			System.out.println("\tInvalid Input!");
		}
		getMerchant(merchant);		
	}
	public static void getProduct(Merchant merchant) {
		try {
			System.out.println("Get Product");
			System.out.print("\tName: ");
			String name = reader.readLine();
			Product product = merchant.getProduct(name);
			getProduct(merchant, product);
		} catch(Exception e){
			System.out.println("\tInvalid input!");
			getMerchant(merchant);
		}
		
	}
	public static void setPrice(Merchant merchant, Product product) {
		try {
			System.out.println("Set Price");
			System.out.print("\tPrice: ");
			Float price = Float.parseFloat(reader.readLine());
			product.setPrice(price);

		}catch(Exception e) {
			System.out.print("\tInvalid input!");			
		}
		getProduct(merchant, product);
	}
	public static void setStock(Merchant merchant, Product product) {
		try {
			System.out.println("Set Stock");
			System.out.print("\tStock: ");
			Float stock = Float.parseFloat(reader.readLine());
			product.setPrice(stock);

		}catch(Exception e) {
			System.out.print("\tInvalid input!");			
		}
		getProduct(merchant, product);
	}
	
	public static void getProduct(Merchant merchant, Product product) {
		try {
			System.out.println("Product");
			System.out.println("\tName: "+product.getName());
			System.out.println("\tPrice: "+product.getPrice());
			System.out.println("\tStock: "+product.getStock());
			System.out.println("\tDiscount: "+product.getDiscount());
			
			System.out.println("\t1.Set Price");
			System.out.println("\t2.Set Stock");
			System.out.println("\t3.Set Discount");
			System.out.println("\t0.Back");
			System.out.print(":");
			
			switch(reader.readLine()) {
				default: getProduct(merchant, product); break;
				case "1": setPrice(merchant, product); break;
				case "2": setStock(merchant, product); break;
				case "3": setDiscount(merchant, product); break;
				case "0": getMerchant(merchant); break;
			}	
		
		} catch (Exception e) {System.out.println("Invalid input!");}
	}
	public static void setDiscount(Merchant merchant, Product product) {
		try {
			System.out.println("Set Discount");
			System.out.print("\tDiscount: ");
			Float discount = Float.parseFloat(reader.readLine());
			product.setDiscount(discount);
			
		}catch(Exception e) {System.out.println("\tInvalid input!");}
		getProduct(merchant, product);
	}
	public static void getMerchant(Merchant merchant) {
		try {
			System.out.println("Merchant");		
			System.out.println("\tName: "+merchant.getName());
			System.out.println("\tBalance: "+merchant.getBalance());
			System.out.println("\tBonus: "+merchant.getBonus());
			
			System.out.println("\t1.List Products");
			System.out.println("\t2.Add Product");
			System.out.println("\t3.Remove Product");
			System.out.println("\t4.Get Product");
			System.out.println("\t5.Set Balance");
			System.out.println("\t0.Back");
			System.out.print(":");
			
			switch(reader.readLine()) {
				default: getMerchant(merchant); break;
				case "1": listProducts(merchant); break;
				case "2": addProduct(merchant); break;
				case "3": removeProduct(merchant); break;
				case "4": getProduct(merchant); break;
				case "5": setBalance(merchant); break;
				case "0": mainMenu(); break;
			}	
		
		} catch (Exception e) {System.out.println("Invalid input!");}
	}
	
	public static void getMerchant() {
		try {
			System.out.println("Get Merchant");
			System.out.print("\tName: ");
			String name = reader.readLine();
			Merchant merchant = network.getMerchant(name);
			getMerchant(merchant);
		}catch(Exception e) {
			System.out.println("\tInvalid input!");
			mainMenu();
		}			
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
				System.out.println(c.getName()+"\t"+c.getBalance()+"\t"+c.getCredits());
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
				System.out.println(m.getName()+"\t"+m.getBalance()+"\t"+m.getBonus());
			}	
		}
		mainMenu();
	}
	public static void transferCredit(Customer customer) {
		try {
			System.out.println("Transfer Credit");			
			System.out.print("\tReceiveing Customer: ");
			String receiver_name = reader.readLine();
			System.out.print("\tAmount: ");
			Float amount = Float.parseFloat(reader.readLine());
			network.transferCredit(customer.getName(), receiver_name, amount);
		} catch (Exception e1) {
			System.out.println("Invalid input!");
		}		
		getCustomer(customer);
	}
	public static void purchaseProduct(Customer customer) {
		try {
			System.out.println("Purchase Product");
			System.out.println("\tCurrency:");
			System.out.println("\t\t1.Money");
			System.out.println("\t\t2.Credit");
			System.out.print(": ");
			Object currency = null;
			switch(reader.readLine()) {
				default: throw new Exception();
				case "1": currency = mfes.quotes.MoneyQuote.getInstance(); break;
				case "2": currency = mfes.quotes.CreditQuote.getInstance(); break;
			}			
			System.out.print("\tMerchant: ");
			String merchant = reader.readLine();
			System.out.print("\tProduct: ");
			String product = reader.readLine();
			System.out.print("\tQuantity: ");
			Integer quantity = Integer.parseInt(reader.readLine());
			network.purchaseProduct(currency, customer.getName(), merchant, product, quantity);
		} catch (Exception e1) {System.out.println("Invalid input!");}		
		getCustomer(customer);
	}


}
