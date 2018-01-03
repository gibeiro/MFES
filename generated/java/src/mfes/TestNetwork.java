package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

import junit.framework.TestCase;

@SuppressWarnings("all")
public class TestNetwork extends TestCase {
  public void testNetworkMethods() {

    Network network = new Network(0.0);
    Number fee = 0.1;
    Number balance = 100L;
    Customer customer1 = new Customer("customer1", 0.0);
    Customer customer2 = new Customer(customer1, "customer2", balance);
    Merchant merchant1 = new Merchant("merchant1", 0.0);
    Merchant merchant2 = new Merchant(merchant1, "merchant2", 0.0);
    Number discount = 0.15;
    Number price = 50L;
    Product product1 = new Product("product1", 15.0, 1L, 0.0);
    Product product2 = new Product("product2", price, 1L, discount);
    merchant1.addProduct(product1);
    merchant2.addProduct(product2);
    network.setFee(fee);
    assertTrue(Utils.equals(fee, network.getFee()));
    network.addCustomer(customer1);
    network.addCustomer(customer2);
    network.addMerchant(merchant1);
    network.addMerchant(merchant2);
    assertTrue(Utils.equals(network.getCustomers(), SetUtil.set(customer2, customer1)));
    assertTrue(Utils.equals(network.getMerchants(), SetUtil.set(merchant2, merchant1)));
    network.purchaseProduct(
        mfes.quotes.MoneyQuote.getInstance(),
        customer2.getName(),
        merchant2.getName(),
        product2.getName(),
        1L);
    assertTrue(Utils.equals(customer2.getBalance(), balance.doubleValue() - price.doubleValue()));
    assertTrue(Utils.equals(customer2.getCredits(), discount.doubleValue() * price.doubleValue()));
    assertTrue(
        Utils.equals(
            merchant2.getBalance(),
            price.doubleValue() - network.getFee().doubleValue() * price.doubleValue()));
    assertTrue(customer1.getCredits().doubleValue() > 0.0);
    assertTrue(merchant1.getBonus().doubleValue() > 0.0);
    network.transferCredit(customer1.getName(), customer2.getName(), customer1.getCredits());
    assertTrue(Utils.equals(customer1.getCredits(), 0L));
    assertTrue(customer2.getCredits().doubleValue() > discount.doubleValue() * price.doubleValue());
    network.removeCustomer(customer1.getName());
    network.removeMerchant(merchant1.getName());
    assertTrue(Utils.equals(network.getCustomers(), SetUtil.set(customer2)));
    assertTrue(Utils.equals(network.getMerchants(), SetUtil.set(merchant2)));
  }

  public TestNetwork() {}

  public String toString() {

    return "TestNetwork{}";
  }
}
