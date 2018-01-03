package mfes;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Product {
  private String name;
  private Number price = 0L;
  private Number stock = 0L;
  private Number discount = 0.0;

  public void cg_init_Product_1(final String n, final Number p, final Number s, final Number d) {

    name = n;
    setPrice(p);
    setStock(s);
    setDiscount(d);
    return;
  }

  public Product(final String n, final Number p, final Number s, final Number d) {

    cg_init_Product_1(n, p, s, d);
  }

  public String getName() {

    return name;
  }

  public Number getPrice() {

    return price;
  }

  public Number getStock() {

    return stock;
  }

  public Number getDiscount() {

    return discount;
  }

  public void setPrice(final Number p) {

    price = p;
  }

  public void setStock(final Number s) {

    stock = s;
  }

  public void setDiscount(final Number d) {

    discount = d;
  }

  public Product() {}

  public String toString() {

    return "Product{"
        + "name := "
        + Utils.toString(name)
        + ", price := "
        + Utils.toString(price)
        + ", stock := "
        + Utils.toString(stock)
        + ", discount := "
        + Utils.toString(discount)
        + "}";
  }
}
