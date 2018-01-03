package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

import junit.framework.TestCase;

@SuppressWarnings("all")
public class TestCustomer extends TestCase {
  public void testCustomerMethods() {

    Customer customer1 = new Customer("TestCustomer1", 1000.0);
    Customer customer2 = new Customer(customer1, "TestCustomer2", 1000.0);
    assertTrue(Utils.equals(customer1.getCredits(), 0.0));
    customer1.setCredits(25.0);
    assertTrue(Utils.equals(customer1.getCredits(), 25.0));
    assertTrue(Utils.equals(customer2.getReferrer(), customer1));
  }

  public TestCustomer() {}

  public String toString() {

    return "TestCustomer{}";
  }
}
