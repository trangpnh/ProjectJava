package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private final static String DB_USER = "root";
    private final static String DB_PASSWORD = "Clapclap97";

    private final static String DB_URL_CONNECTION = "jdbc:mysql://localhost:3306/quanlynhansu";

    private MyConnection(){}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL_CONNECTION, DB_USER, DB_PASSWORD);
    }
}
