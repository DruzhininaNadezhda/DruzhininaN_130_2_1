package LabSQL;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Repository result = new Repository();
        System.out.println(result.getAllProduct());
        System.out.println(result.findByVendorCode("3251620"));
        OrderItems one = new OrderItems();
        one.setQuantity(6);
        one.setVendor_code_product("3251617");
        OrderItems two = new OrderItems();
        two.setQuantity(6);
        two.setVendor_code_product("3251620");
        result.createOrderItems(result.createOrder("Степанов", "999", "почта", "СПБ"),one, two);
    }
}
