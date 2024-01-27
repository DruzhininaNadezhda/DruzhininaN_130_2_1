package LabSQL;

import java.util.Objects;

public class OrderItems {
    private int quantity;
    private String vendor_code_product;
    Product product;
    Order order;

    public String getVendor_code_product() {
        return vendor_code_product;
    }
    public void setVendor_code_product(String vendor_code_product) {
        this.vendor_code_product = vendor_code_product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItems that = (OrderItems) o;
        return Objects.equals(vendor_code_product, that.vendor_code_product) && Objects.equals(product, that.product) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendor_code_product, product, order);
    }
}
