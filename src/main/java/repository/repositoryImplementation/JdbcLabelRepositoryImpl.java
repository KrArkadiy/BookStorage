package repository.repositoryImplementation;

import model.Label;
import repository.LabelRepository;
import utility.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcLabelRepositoryImpl implements LabelRepository {

    public void creatingLabelTableInDatabase() throws ClassNotFoundException, SQLException {

        Connection connection = null;
        Statement statement = null;
        try {
            System.out.println("Registering JDBC Driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Creating table in selected database...");
            statement = connection.createStatement();

            String creatingLabelTable = "CREATE TABLE label " +
                    "(id INTEGER NOT NULL," +
                    "name VARCHAR (255))";
            statement.executeUpdate(creatingLabelTable);
            System.out.println("Table successfully created...");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void insertInformation() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            System.out.println("Registering JDBC Driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Inserting information in selected database...");
            statement = connection.createStatement();

            String insertingInformationFirst = "INSERT INTO Label (id, name) VALUES (1, 'FirstLabel')";
            String insertingInformationSecond = "INSERT INTO Label (id, name) VALUES (2, 'SecondLabel')";

            statement.executeUpdate(insertingInformationFirst);
            statement.executeUpdate(insertingInformationSecond);

            System.out.println("Data successfully inserted...");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                statement.close();
            }
        }
    }

    @Override
    public Label getById(Long aLong) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        Integer id = 0;
        String name = "";

        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Getting record..");
            statement = connection.createStatement();

            String gettingLabelById = "SELECT id, name FROM Label WHERE id = " + aLong;
            ResultSet resultSet = statement.executeQuery(gettingLabelById);

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
            }
            return new Label(id, name);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public List<Label> getAll() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        List<Label> labelsFromDatabase = new ArrayList<>();

        Integer id;
        String name;

        try {
            System.out.println("Registering JDBC Driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Getting all records...");
            statement = connection.createStatement();

            String gettingAllResults = "SELECT * FROM Label";

            ResultSet resultSet = statement.executeQuery(gettingAllResults);

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
                labelsFromDatabase.add(new Label(id, name));
            }
            return labelsFromDatabase;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public Label update(Label label) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        Integer id;
        String name;

        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Getting record..");
            statement = connection.createStatement();

            String updatingLabel = "SELECT * FROM Label WHERE id = " + label.getId();

            ResultSet resultSet = statement.executeQuery(updatingLabel);

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
            }

            System.out.println("Changing name of the label..");
            System.out.println("Getting updated records");

            resultSet = statement.executeQuery(updatingLabel);

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2) + "Updated";
            }
            return label;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public Label save(Label label) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            System.out.println("Registering JDBC Driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Creating new Label");
            statement = connection.createStatement();

            String creatingNewLabel = "INSERT INTO Label (id, name) VALUES (" + label.getId() + ", '" + label.getName() + "')";

            statement.executeUpdate(creatingNewLabel);

            System.out.println("New label added to database");

            return label;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void deleteById(Long aLong) throws ClassNotFoundException, SQLException{
        Connection connection = null;
        Statement statement = null;

        Integer id = 0;
        String name = "";

        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Getting records");
            statement = connection.createStatement();

            String gettingRecords = "SELECT * FROM Label";

            ResultSet resultSet = statement.executeQuery(gettingRecords);

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
            }

            System.out.println("Deleting label with id " + aLong);
            statement = connection.createStatement();

            String deletingLabel = "DELETE FROM Label WHERE id = " + aLong;

            statement.executeUpdate(deletingLabel);
        } finally {
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }
}
