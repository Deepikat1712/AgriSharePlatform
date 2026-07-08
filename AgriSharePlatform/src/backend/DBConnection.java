package backend;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection con;

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12832471",
    "sql12832471",
    " YaKHWSX1HH"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}
