public class PaymentCard {
    private String cardHolder;
    private String cardId;
    private String password;
    private String expireDate;

    PaymentCard(String cardHolder,String cardId,String password,String expireDate){
        setCardHolder(cardHolder);
        setCardId(cardId);
        setExpireDate(expireDate);
        setPassword(password);
    }

    public void setCardId(String cardId) {
        if(cardId.length() == 16) this.cardId =cardId;
        else System.out.println("Card Id should be 16 digit long");
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = (cardHolder.isEmpty())? "Guest" : cardHolder;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCardId() {
        return cardId;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public String getPassword() {
        return password;
    }
}
