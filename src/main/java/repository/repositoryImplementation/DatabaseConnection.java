package repository.repositoryImplementation;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private Connection connection;

    private final String DATABASE_URL = "jdbc:mysql://localhost:3306/YOUR_DB_NAME";

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private final String USER = "YOUR_USERNAME";

    private final String PASSWORD = "YOUR_PASSWORD";

    private DatabaseConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            this.connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Database Connection Creation Failed: " + exception.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() {
        try {
            if (instance == null) {
                instance = new DatabaseConnection();
            } else if (instance.getConnection().isClosed()) {
                instance = new DatabaseConnection();
            }
        } catch (SQLException exception) {
            System.out.println("Database Connection Creation Failed: " + exception.getMessage());
        }
        return instance;
    }
}
