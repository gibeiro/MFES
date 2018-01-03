package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  protected String name;
  protected Number balance;

  public String getName() {

    return name;
  }

  public Number getBalance() {

    return balance;
  }

  public void setBalance(final Number b) {

    balance = b;
  }

  public User() {}

  public String toString() {

    return "User{"
        + "name := "
        + Utils.toString(name)
        + ", balance := "
        + Utils.toString(balance)
        + "}";
  }
}
