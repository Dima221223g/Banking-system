public class User {
    private int cardNo;
    private String identity;
    private String userName;
    private String passWord;
    private String phone;
    private double balance;

    public User() {
    }

    public User(int cardNo, String identity, String userName, String passWord, String phone, double balance) {
        this.cardNo = cardNo;
        this.identity = identity;
        this.userName = userName;
        this.passWord = passWord;
        this.phone = phone;
        this.balance = balance;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
