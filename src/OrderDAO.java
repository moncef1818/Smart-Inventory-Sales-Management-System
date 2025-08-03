import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO {
    public static boolean placeOrder(Order order){
        String insertOrderSQL = "INSERT INTO orders (user_id , order_time ,total_price) VALUES (?,?,?)";
        String insertItemSQL = "INSERT INTO order_items (order_id, product_id ,quantity) VALUES (?,?,?)";

        try (Connection conn = DBConnection.getConnection()){
            conn.setAutoCommit(false);

            PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL, PreparedStatement.RETURN_GENERATED_KEYS);

            orderStmt.setInt(1,order.getUserID());
            orderStmt.setString(2,order.getOrderTime());
            orderStmt.setDouble(3,order.getTotalPrice());

            int rows = orderStmt.executeUpdate();

            if (rows == 0) {
                conn.rollback();
                return false;
            }

            ResultSet resultSet = orderStmt.getGeneratedKeys();
            if (!resultSet.next()){
                conn.rollback();
                return false;
            }
            int orderID = resultSet.getInt(1);

            PreparedStatement itemStmt = conn.prepareStatement(insertItemSQL);
            for (Order.OrderedProduct op : order.getOrderedProducts()){
                itemStmt.setInt(1,orderID);
                itemStmt.setInt(2,op.getProduct().getProductID());
                itemStmt.setInt(3,op.getQuantity());

                itemStmt.addBatch();

                int newStock = op.getProduct().getStock() - op.getQuantity();
                ProductDAO.updateStock(op.getProduct().getProductID(),newStock);
            }
            itemStmt.executeBatch();
            conn.commit();
            return true;



        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
}
