package LabSQL;

import java.util.Objects;
public class Product {
    private String vendor_code;
    private String name_product;
    private String color;
    private int price;
    private int balance_in_stock;
    public Product() {
    }
    public Product(String name_product, String color) {
        setName_product(name_product);
        setColor(color);
    }
    public Product(String vendor_code, String name_product, String color, int price, int balance_in_stock) {
        setName_product(name_product);
        setColor(color);
        setVendor_code(vendor_code);
        setName_product(name_product);
        setColor(color);
        setPrice(price);
        setBalance_in_stock(balance_in_stock);
    }

    public String getVendor_code() {
        return vendor_code;
    }
    public void setVendor_code(String vendor_code) throws IllegalArgumentException{
        if (!vendor_code.equals(null)) {
            this.vendor_code = vendor_code;}
        else {throw new IllegalArgumentException("vendor_code");}
    }
    public String getName_product() {
        return name_product;
    }
    public void setName_product(String name_product) throws IllegalArgumentException{
        if (!name_product.equals(null)) {
            this.name_product = name_product;}
        else {throw new IllegalArgumentException("name_product");}
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) throws IllegalArgumentException {
        if (price >= 0) {
            this.price = price;}
        else {throw new IllegalArgumentException("price");}
    }
    public int getBalance_in_stock() {
        return balance_in_stock;
    }
    public void setBalance_in_stock(int balance_in_stock) throws IllegalArgumentException{
        if (balance_in_stock >= 0) {
            this.balance_in_stock = balance_in_stock;}
        else {throw new IllegalArgumentException("pricbalance_in_stocke");}
    }
    @Override
    public String toString() {
        return "Product{" +
                "vendor_code='" + vendor_code + '\'' +
                ", name_product='" + name_product + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", balance_in_stock=" + balance_in_stock +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(vendor_code, product.vendor_code) && Objects.equals(name_product, product.name_product) && Objects.equals(color, product.color);
    }
    @Override
    public int hashCode() {
        return Objects.hash(vendor_code, name_product, color);
    }
}
