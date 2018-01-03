package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Network {
  private Number fee = 0.1;
  private VDMMap merchants = MapUtil.map();
  private VDMMap customers = MapUtil.map();

  public void cg_init_Network_1(final Number f) {

    setFee(f);
    return;
  }

  public Network(final Number f) {

    cg_init_Network_1(f);
  }

  public VDMSet getMerchants() {

    return MapUtil.rng(Utils.copy(merchants));
  }

  public VDMSet getCustomers() {

    return MapUtil.rng(Utils.copy(customers));
  }

  public Number getFee() {

    return fee;
  }

  public void setFee(final Number f) {

    fee = f;
  }

  public void addCustomer(final Customer c) {

    customers = MapUtil.munion(Utils.copy(customers), MapUtil.map(new Maplet(c.getName(), c)));
  }

  public void addMerchant(final Merchant m) {

    merchants = MapUtil.munion(Utils.copy(merchants), MapUtil.map(new Maplet(m.getName(), m)));
  }

  public void removeCustomer(final String n) {

    customers = MapUtil.domResBy(SetUtil.set(n), Utils.copy(customers));
  }

  public void removeMerchant(final String n) {

    merchants = MapUtil.domResBy(SetUtil.set(n), Utils.copy(merchants));
  }

  public Customer getCustomer(final String n) {

    return ((Customer) Utils.get(customers, n));
  }

  public Merchant getMerchant(final String n) {

    return ((Merchant) Utils.get(merchants, n));
  }

  public void purchaseProduct(
      final Object currency,
      final String c,
      final String m,
      final String p,
      final Number quantity) {

    Customer customer = getCustomer(c);
    Customer referrer = customer.getReferrer();
    Merchant merchant = getMerchant(m);
    Product product = merchant.getProduct(p);
    Number cost = product.getPrice().doubleValue() * quantity.longValue();
    Number discount = product.getDiscount().doubleValue() * cost.doubleValue();
    Number maintenance = fee.doubleValue() * cost.doubleValue();
    Number bonus = maintenance.doubleValue() * merchant.getBonus().doubleValue();
    product.setStock(product.getStock().longValue() - quantity.longValue());
    if (Utils.equals(currency, mfes.quotes.CreditQuote.getInstance())) {
      customer.setCredits(customer.getCredits().doubleValue() - cost.doubleValue());
    }

    if (Utils.equals(currency, mfes.quotes.MoneyQuote.getInstance())) {
      customer.setBalance(customer.getBalance().doubleValue() - cost.doubleValue());
      customer.setCredits(customer.getCredits().doubleValue() + discount.doubleValue());
      merchant.setBalance(
          merchant.getBalance().doubleValue()
              + cost.doubleValue()
              - maintenance.doubleValue()
              + bonus.doubleValue());
      if (!(Utils.equals(referrer, null))) {
        referrer.setCredits(referrer.getCredits().doubleValue() + maintenance.doubleValue());
      }
    }
  }

  public void transferCredit(final String f, final String t, final Number amount) {

    Customer source = getCustomer(f);
    Customer dest = getCustomer(t);
    Number source_credits = source.getCredits();
    Number dest_credits = dest.getCredits();
    source_credits = source_credits.doubleValue() - amount.doubleValue();
    dest_credits = dest_credits.doubleValue() + amount.doubleValue();
    source.setCredits(source_credits);
    dest.setCredits(dest_credits);
  }

  public Network() {}

  public String toString() {

    return "Network{"
        + "fee := "
        + Utils.toString(fee)
        + ", merchants := "
        + Utils.toString(merchants)
        + ", customers := "
        + Utils.toString(customers)
        + "}";
  }
}
