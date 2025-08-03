import java.sql.*;
import java.util.ArrayList;

public class PaymentCardDAO {
    public static boolean addCard(PaymentCard paymentCard){
        String sql = "INSERT INTO payment_cards (user_id,card_holder,card_number,hashed_password,expire_date) values (?,?,?,?,?) ";

        try (Connection conn = DBConnection.getConnection() ){
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,paymentCard.getUserId());
            preparedStatement.setString(2,paymentCard.getCardHolder());
            preparedStatement.setString(3,paymentCard.getCardNumber());
            preparedStatement.setString(4, paymentCard.getHashedPassword());
            preparedStatement.setString(5, paymentCard.getExpireDate());

            int rows = preparedStatement.executeUpdate();
            if (rows > 0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    paymentCard.setId(resultSet.getInt(1));

                }
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static ArrayList<PaymentCard> getCardsByUser(int userID){
        ArrayList<PaymentCard> cards = new ArrayList<>();
        String sql = "SELECT * FROM payment_cards WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection()){

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,userID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                PaymentCard paymentCard = new PaymentCard(
                        resultSet.getString("card_holder"),
                        resultSet.getString("card_number"),
                        resultSet.getString("hashed_password"),
                        resultSet.getString("expire_date"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("id")
                );
                cards.add(paymentCard);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cards;
    }

    public static boolean validateCard(int userid,String cardNumber,String cardPassword){
        String sql = "SELECT hashed_password FROM payment_cards WHERE user_id = ? AND card_number = ? ";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1,userid);
            preparedStatement.setString(2,cardNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                String hashedPassword = resultSet.getString("hashed_password");
                return PasswordUtil.verifyPassword(cardPassword,hashedPassword);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    public static boolean deleteCard(int cardId) {
        String sql = "DELETE FROM payment_cards WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cardId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
