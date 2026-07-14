package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
        "jdbc:postgresql://dpg-d9b3q2flk1mc73cd7qog-a.oregon-postgres.render.com:5432/agrishare_db?sslmode=require";
    private static final String USER = "agrishare_db_user";
    private static final String PASSWORD = "UwGTAxjemszjHbTnERAr3HeNAR3R4UMa";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("DB Connection failed: " + e.getMessage());
        }
        return con;
    }
}