package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTemplate {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mysql";

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static final String USER = "root";

    private static final String PASSWORD = "arkadiy";

    public static String getDatabaseUrl() {
        return DATABASE_URL;
    }

    public static String getJdbcDriver() {
        return JDBC_DRIVER;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    private void creatingDatabase() throws ClassNotFoundException, SQLException {

        Connection connection = null;
        Statement statement = null;

        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            System.out.println("Creating database...");
            statement = connection.createStatement();

            String creatingDataBaseSQL = "CREATE DATABASE BookStorage";
            statement.execute(creatingDataBaseSQL);
            System.out.println("Database successfully created.");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void selectingDataBase() throws ClassNotFoundException, SQLException {

        Connection connection = null;
        Statement statement = null;

        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            System.out.println("Connection to " + DATABASE_URL + " successfully established.");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void run() throws ClassNotFoundException, SQLException {
        creatingDatabase();
        selectingDataBase();
    }
}
