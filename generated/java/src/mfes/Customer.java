package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Customer extends User {
  private Number credits = 0.0;
  private Customer referrer;

  public void cg_init_Customer_2(final String n, final Number b) {

    name = n;
    setBalance(b);
    return;
  }

  public void cg_init_Customer_1(final Customer r, final String n, final Number b) {

    referrer = r;
    name = n;
    setBalance(b);
    return;
  }

  public Customer(final Customer r, final String n, final Number b) {

    cg_init_Customer_1(r, n, b);
  }

  public Customer(final String n, final Number b) {

    cg_init_Customer_2(n, b);
  }

  public Number getCredits() {

    return credits;
  }

  public void setCredits(final Number c) {

    credits = c;
  }

  public Customer getReferrer() {

    return referrer;
  }

  public Customer() {}

  public String toString() {

    return "Customer{"
        + "credits := "
        + Utils.toString(credits)
        + ", referrer := "
        + Utils.toString(referrer)
        + "}";
  }
}
