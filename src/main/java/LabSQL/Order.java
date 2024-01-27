package LabSQL;
import java.sql.Timestamp;
import java.util.Objects;
public class Order {
    private int ID_order;
    private Timestamp order_creation_date;
    private String customer_name;
    private String phone;
    private String email;
    private String address;
    private String order_status;
    private Timestamp order_shipment_date;
    public Order() {
    }
    public Timestamp getOrder_shipment_date() {
        return order_shipment_date;
    }

    public void setOrder_shipment_date(Timestamp order_shipment_date) {
        this.order_shipment_date = order_shipment_date;
    }
    public Order(int ID_order, Timestamp order_creation_date, String customer_name, String phone, String email, String address, String order_status, Timestamp order_shipment_date) {
        this.ID_order = ID_order;
        this.order_creation_date = order_creation_date;
        this.customer_name = customer_name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.order_status = order_status;
        this.order_shipment_date = order_shipment_date;
    }
    public int getID_order() {
        return ID_order;
    }
    public void setID_order(int ID_order) {
        this.ID_order = ID_order;
    }
    public Timestamp getOrder_creation_date() {
        return order_creation_date;
    }
    public void setOrder_creation_date(Timestamp order_creation_date) {
        this.order_creation_date = order_creation_date;
    }
    public String getCustomer_name() {
        return customer_name;
    }
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getOrder_status() {
        return order_status;
    }
    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return ID_order == order.ID_order && Objects.equals(order_creation_date, order.order_creation_date) && Objects.equals(customer_name, order.customer_name) && Objects.equals(email, order.email) && Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID_order, order_creation_date, customer_name, email, address);
    }

    @Override
    public String toString() {
        return "Order{" +
                "ID_order=" + ID_order +
                ", order_creation_date=" + order_creation_date +
                ", customer_name='" + customer_name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", order_status='" + order_status + '\'' +
                '}';
    }
}
