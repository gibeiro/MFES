package mfes.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CreditQuote {
  private static int hc = 0;
  private static CreditQuote instance = null;

  public CreditQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static CreditQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new CreditQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof CreditQuote;
  }

  public String toString() {

    return "<Credit>";
  }
}
