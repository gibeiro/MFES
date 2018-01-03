package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

import junit.framework.TestCase;

@SuppressWarnings("all")
public class TestUser extends TestCase {
  public void testUserMethods() {

    String name = "TestUser";
    Number balance = 100L;
    User user = new Customer(name, 0.0);
    assertTrue(Utils.equals(name, user.getName()));
    user.setBalance(balance);
    assertTrue(Utils.equals(balance, user.getBalance()));
  }

  public TestUser() {}

  public String toString() {

    return "TestUser{}";
  }
}
