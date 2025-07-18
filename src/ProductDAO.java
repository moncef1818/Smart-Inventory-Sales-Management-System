import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
    public static boolean addProduct(Product product){
        String sql = "INSERT INTO products (name , category, description,stock,price) VALUES (?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
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

            while (resultSet.next()){
                products.add(buildProduct(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public Product searchProductByName(String name){
        String sql = "SELECT * FROM products WHERE name = ?";

        try (Connection conn = DBConnection.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return buildProduct(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Product> getProductsByCategory(String category) {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category = ?";

        try (Connection conn = DBConnection.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,category);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                products.add(buildProduct(resultSet));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    public ArrayList<Product> getProductsByNameLike(String nameLike){
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ?";

        try (Connection conn = DBConnection.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,"%" + nameLike + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                products.add(buildProduct(resultSet));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
    public ArrayList<Product> getProductsInStock(){
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE stock > 0 ";

        try (Connection conn = DBConnection.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                products.add(buildProduct(resultSet));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    private static Product buildProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getString("name"),
                rs.getString("category"),
                rs.getInt("id"),
                rs.getString("description"),
                rs.getInt("stock"),
                rs.getDouble("price")
        );
    }

}
