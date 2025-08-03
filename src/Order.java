import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Order {

    private ArrayList<Order.OrderedProduct> orderedProducts = new ArrayList<>();
    private double totalPrice=0;
    private String orderTime;
    private int orderID;
    private int userID;

    Order(int  orderID,int userID){
        setOrderDate();
        setOrderID(orderID);
        setUserID(userID);
        updateTotalPrice();
    }

    public void addOrderedProduct(Product product,int quantity){
        orderedProducts.add(new OrderedProduct(product,quantity));
        updateTotalPrice();

    }

    public void setOrderDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd:MM:yyyy");
        this.orderTime = now.format(formatter);
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void updateTotalPrice() {
        totalPrice = 0;
        for (OrderedProduct orderedProduct : orderedProducts){
            totalPrice += orderedProduct.quantity * orderedProduct.product.getPrice();
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getOrderID() {
        return orderID;
    }

    public ArrayList<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public int getUserID() {return userID;}

    public static class OrderedProduct{
        Product product;
        int quantity;

        OrderedProduct(Product product,int quantity){
            setProduct(product);
            setQuantity(quantity);
        }
        public void setProduct(Product product) {
            this.product = product;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }
    }


}
