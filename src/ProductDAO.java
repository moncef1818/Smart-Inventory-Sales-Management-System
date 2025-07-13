import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
    public static boolean addProduct(Product product){
        String sql = "INSERT INTO products (name , category, description,stock,price) VALUES (?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,product.getProductName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setString(3,product.getDescription());
            preparedStatement.setString(4, String.valueOf(product.getStock()));
            preparedStatement.setString(5,String.valueOf(product.getPrice()));

            int rows = preparedStatement.executeUpdate();
            if (rows > 0){
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()){
                    product.setProductID(rs.getInt("id"));
                }
                return true;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static ArrayList<Product> getAllProducts(){
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DBConnection.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getInt("stock"),
                        resultSet.getDouble("price")
                );

                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
