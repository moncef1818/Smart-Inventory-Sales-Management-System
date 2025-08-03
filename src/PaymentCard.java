public class PaymentCard {
    private int id;
    private int userId;
    private String cardHolder;
    private String cardNumber;
    private String hashedPassword;
    private String expireDate;

    public PaymentCard(String cardHolder, String cardNumber, String plainPassword, String expireDate, int userId) {
        setCardHolder(cardHolder);
        setCardNumber(cardNumber);
        setExpireDate(expireDate);
        setHashedPassword(PasswordUtil.hashPassword(plainPassword));
        setUserId(userId);
    }

    public PaymentCard(String cardHolder, String cardNumber, String hashedPassword, String expireDate, int userId, int cardID ) {
        setCardHolder(cardHolder);
        setCardNumber(cardNumber);
        setExpireDate(expireDate);
        setHashedPassword(hashedPassword);
        setUserId(userId);
        setId(cardID);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCardNumber(String cardNumber) {
        if (cardNumber.length() == 16 && cardNumber.matches("\\d+")) {
            this.cardNumber = cardNumber;
        } else {
            throw new IllegalArgumentException("Card number must be exactly 16 digits.");
        }
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = (cardHolder == null || cardHolder.isEmpty()) ? "Guest" : cardHolder;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
