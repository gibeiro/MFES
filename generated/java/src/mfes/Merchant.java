package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Merchant extends User {
  private Number bonus = 0L;
  private VDMMap products = MapUtil.map();

  public void cg_init_Merchant_2(final String n, final Number b) {

    name = n;
    balance = b;
    return;
  }

  public void cg_init_Merchant_1(final Merchant r, final String n, final Number b) {

    r.incrementReferrals();
    name = n;
    setBalance(b);
    return;
  }

  public Merchant(final Merchant r, final String n, final Number b) {

    cg_init_Merchant_1(r, n, b);
  }

  public Merchant(final String n, final Number b) {

    cg_init_Merchant_2(n, b);
  }

  public Product getProduct(final String n) {

    return ((Product) Utils.get(products, n));
  }

  public VDMSet getProducts() {

    return MapUtil.rng(Utils.copy(products));
  }

  public void addProduct(final Product p) {

    products = MapUtil.munion(Utils.copy(products), MapUtil.map(new Maplet(p.getName(), p)));
  }

  public void removeProduct(final String n) {

    products = MapUtil.domResBy(SetUtil.set(n), Utils.copy(products));
  }

  public Number getBonus() {

    return bonus;
  }

  public void incrementReferrals() {

    Number nr_referrals = Utils.divide(10L * bonus.doubleValue(), 1L - bonus.doubleValue());
    nr_referrals = nr_referrals.longValue() + 1L;
    bonus = Utils.divide((1.0 * nr_referrals.longValue()), nr_referrals.longValue() + 10L);
  }

  public Merchant() {}

  public String toString() {

    return "Merchant{"
        + "bonus := "
        + Utils.toString(bonus)
        + ", products := "
        + Utils.toString(products)
        + "}";
  }
}
