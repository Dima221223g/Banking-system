import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLDatabaseConnection {
    public static void main(String[] args) throws ClassNotFoundException {
        String connectionUrl = "jdbc:sqlserver://LAPTOP-VBM2QA8J:1433/Banking-system";
        Class.forName("com.mysql.jdbc.Driver");

        String insertSql = "INSERT INTO BankingUser (cardNo, userName, passWord, phone, balance) VALUES"
                + "(8, 'Ivan Ivanov', '13561473', '89879517142', 10000);";
        ResultSet resultSet = null;
        try (Connection connection = DriverManager.getConnection(connectionUrl);
                PreparedStatement prepsInsertBankingUser = connection.prepareStatement(insertSql,
                        Statement.RETURN_GENERATED_KEYS);) {
            prepsInsertBankingUser.execute();
            resultSet = prepsInsertBankingUser.getGeneratedKeys();
            while (resultSet.next()) {
                System.out.println("Generated: " + resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
