package LabSQL;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Repository {
    String url = "jdbc:mysql://127.0.0.1:3306/druzhinina_lab130_1";
    String user = "root";
    String password = "hfljcnm";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password); // создали запрос драйвера и запросили соед. с бд
    } // прописать pom!!!!!
    public List<Product> getAllProduct() {
        List<Product> products = new LinkedList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) { // запрос к бд
            ResultSet rs = statement.executeQuery("select * from product");
            while (rs.next()) {
                String vendor_code = rs.getString("vendor_code");
                String name_product = rs.getString("name_product");
                String color = rs.getString("color");
                Integer price = rs.getInt("price");
                Integer balance_in_stock = rs.getInt("balance_in_stock");
                products.add(new Product(vendor_code, name_product, color, price, balance_in_stock));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(2);
        }
        return products;
    }
    public int removeOrder(int ID_order) {
        int count = 0;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            count = statement.executeUpdate("delete from orders where ID_order=" + ID_order);
        } catch (SQLException e) {
        }
        return count;
    }
    public Product findByVendorCode(String vendor_code) {
        Product product = null;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from product where vendor_code=" + vendor_code);
            if (rs.next()) {
                String name_product = rs.getString("name_product");
                String color = rs.getString("color");
                int price = rs.getInt("price");
                int balance_in_stock = rs.getInt("balance_in_stock");
                product = new Product(vendor_code, name_product, color, price, balance_in_stock);
            }
        } catch (SQLException e) {
        }
        return product;
    }
    public List returnByOrderID(int orderCode) {
        Product product = null;
        List<String> productsInOrder = new LinkedList<>();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from Druzhinina_Lab130_1.Product\n" +
                     "                                                         left join Druzhinina_Lab130_1.Order_items\n" +
                     "                                                                   on Druzhinina_Lab130_1.Order_items.order_record_code =" + orderCode + "\n" +
                     "where Druzhinina_Lab130_1.Order_items.vendor_code_product = Druzhinina_Lab130_1.Product.vendor_code;")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name_product = rs.getString("name_product");
                String color = rs.getString("color");
                product = new Product(name_product, color);
                String result = product.getName_product();
                if (product.getColor() != null) {
                    result += "\t" + product.getColor();
                }
                productsInOrder.add(result);
            }
            connection.close();
        } catch (SQLException e) {
        }
        return productsInOrder;
    }
    public Order createOrder(String customer_name, String phone, String email, String address) throws SQLException {
        Connection connection = getConnection();
        Order order;
        String sqlOrder = "insert into orders (ID_order, order_creation_date, customer_name, phone, email, address, order_status) VALUE (?,?,?,?,?,?,?)";
        Timestamp order_creation_date = new Timestamp(System.currentTimeMillis());
        int ID_order = 0;
        try (Statement statement = connection.createStatement()) { // запрос к бд
            ResultSet rs = statement.executeQuery("select ID_order from orders");
            while (rs.next()) {
                ID_order = rs.getInt("ID_order");
            }
        } catch (SQLException e) {
        }
        order = new Order();
        order.setID_order(ID_order + 1);
        order.setOrder_creation_date(order_creation_date);
        order.setCustomer_name(customer_name);
        order.setEmail(email);
        order.setPhone(phone);
        order.setAddress(address);
        order.setOrder_status("P");
        try (PreparedStatement ps = connection.prepareStatement(sqlOrder)) {
            ps.setInt(1, order.getID_order());
            ps.setTimestamp(2, order.getOrder_creation_date());
            ps.setString(3, order.getCustomer_name());
            ps.setString(4, order.getPhone());
            ps.setString(5, order.getEmail());
            ps.setString(6, order.getAddress());
            ps.setString(7, "P");
            ps.execute();
        } catch (SQLException e) {
            System.out.println(4);
        }
        return order;
    }
    public void createOrderItems(Order order, OrderItems... orderItems) throws SQLException {
        Connection connection = getConnection();
        String sqlOrderItems = "insert into order_items (order_record_code, vendor_code_product, price, quantity) value (?,?,?,?)";
        for (OrderItems j : orderItems) {
            Product product = findByVendorCode(j.getVendor_code_product());
            try (PreparedStatement ps = connection.prepareStatement(sqlOrderItems)) {
                ps.setInt(1, order.getID_order());
                ps.setString(2, product.getVendor_code());
                ps.setInt(3, product.getPrice());
                ps.setInt(4, j.getQuantity());
                ps.execute();
            } catch (SQLException e) {
                System.out.println(5);
            }
        }
    }
}
