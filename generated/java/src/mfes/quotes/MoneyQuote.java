package mfes.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class MoneyQuote {
  private static int hc = 0;
  private static MoneyQuote instance = null;

  public MoneyQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static MoneyQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new MoneyQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof MoneyQuote;
  }

  public String toString() {

    return "<Money>";
  }
}
