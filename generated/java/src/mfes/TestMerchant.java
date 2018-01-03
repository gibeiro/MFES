package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

import junit.framework.TestCase;

@SuppressWarnings("all")
public class TestMerchant extends TestCase {
  public void testMerchantMethods() {

    Merchant merchant = new Merchant("TestMerchant", 1000.0);
    String product_name = "TestProduct";
    Product product = new Product(product_name, 15.0, 100L, 0.1);
    assertTrue(Utils.equals(merchant.getBonus(), 0.0));
    merchant.incrementReferrals();
    assertTrue(merchant.getBonus().doubleValue() > 0.0);
    merchant.addProduct(product);
    assertTrue(Utils.equals(merchant.getProduct(product_name), product));
    assertTrue(Utils.equals(merchant.removeProduct(product_name), product));
  }

  public TestMerchant() {}

  public String toString() {

    return "TestMerchant{}";
  }
}
