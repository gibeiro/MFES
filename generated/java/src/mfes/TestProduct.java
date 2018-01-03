package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

import junit.framework.TestCase;

@SuppressWarnings("all")
public class TestProduct extends TestCase {
  public void testProductMethods() {

    String name = "TestProduct";
    Number price = 15L;
    Number stock = 100L;
    Number discount = 0.1;
    Product product = new Product(name, 0L, 0L, 0L);
    assertTrue(Utils.equals(name, product.getName()));
    product.setPrice(price);
    assertTrue(Utils.equals(price, product.getPrice()));
    product.setStock(stock);
    assertTrue(Utils.equals(stock, product.getStock()));
    product.setDiscount(discount);
    assertTrue(Utils.equals(discount, product.getDiscount()));
  }

  public TestProduct() {}

  public String toString() {

    return "TestProduct{}";
  }
}
