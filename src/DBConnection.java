import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/stock_system";
    private static final String username = "root";
    private static final String password ="***************";
    private static Connection connection = null;

    public static Connection getConnection(){
        if(connection == null){
            try {
                connection = DriverManager.getConnection(url,username,password);
                System.out.println("✅ Connected to the sql server successfully");
            } catch (SQLException e) {
                System.out.println("❌ Connection failed!");
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
