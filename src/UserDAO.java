import java.sql.*;
import java.util.Optional;

public class UserDAO {
    public static boolean registerUser(User user){
        String sql = "INSERT INTO users (username, email, password, role, register_time) VALUES (? ,? ,? ,? ,? )";
        try (Connection conn = DBConnection.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getRegisterTime());

            int affectedRows = preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1)); // set the generated ID
            }
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User login(String email,String password){
        String hashedPassword = PasswordUtil.hashPassword(password);

        String sql = "SELECT * FROM users WHERE email = ? AND password = ? ";
        try (Connection conn = DBConnection.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,hashedPassword);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                return new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("register_time")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static boolean emailExisted(String email){
        String sql = "SELECT * FROM users WHERE email = ? ";
        try (Connection conn = DBConnection.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
