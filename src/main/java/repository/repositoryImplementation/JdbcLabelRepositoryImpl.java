package repository.repositoryImplementation;

import model.Label;
import repository.LabelRepository;
import repository.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcLabelRepositoryImpl implements LabelRepository {

/*    public void creatingLabelTableInDatabase() throws ClassNotFoundException, SQLException {

        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {
            String creatingLabelTable = "CREATE TABLE label " +
                    "(id INTEGER NOT NULL," +
                    "name VARCHAR (255))";
            statement.executeUpdate(creatingLabelTable);
            System.out.println("Table successfully created...");
        } catch(SQLException exception){
            System.out.println("Error occurred " + exception.getMessage());
        }
    }

    public void insertInformation() throws ClassNotFoundException, SQLException {
        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {

            String insertingInformationFirst = "INSERT INTO Label (id, name) VALUES (1, 'FirstLabel')";
            String insertingInformationSecond = "INSERT INTO Label (id, name) VALUES (2, 'SecondLabel')";

            statement.executeUpdate(insertingInformationFirst);
            statement.executeUpdate(insertingInformationSecond);

            System.out.println("Data successfully inserted...");
        } catch (SQLException exception){
            System.out.println("Error occurred " + exception.getMessage());
        }
    }*/

    @Override
    public Label getById(Long aLong) {
        int id = 0;
        String name = "";

        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(String.format(SQLQueries.GETLABELBYID.getQuery(), aLong));

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
            }
        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
        return new Label(id, name);
    }

    @Override
    public List<Label> getAll() {
        List<Label> labelsFromDatabase = new ArrayList<>();
        int id;
        String name;

        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {

            ResultSet resultSet = statement.executeQuery(SQLQueries.GETALLLABELS.getQuery());

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
                labelsFromDatabase.add(new Label(id, name));
            }
        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
        return labelsFromDatabase;
    }

    @Override
    public Label update(Label label) {
        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {


            ResultSet resultSet = statement.executeQuery(String.format(SQLQueries.UPDATELABEL.getQuery(), label.getId()));

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
            }

            System.out.println("Changing name of the label..");
            System.out.println("Getting updated records");

            resultSet = statement.executeQuery(String.format(SQLQueries.UPDATELABEL.getQuery(), label.getId()));

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2) + "Updated";
            }

        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
        return label;
    }

    @Override
    public Label save(Label label) {
        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.executeUpdate(String.format(SQLQueries.SAVELABEL.getQuery(), label.getId())
                    + "'" + label.getName() + "')");

            System.out.println("New label added to database");
        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
        return label;
    }

    @Override
    public void deleteById(Long aLong) {
        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {
            System.out.println("Deleting label with id " + aLong);

            String removingSafeMode = "SET_SAFE_UPDATES = 0";
            statement.executeUpdate(removingSafeMode);

            statement.executeUpdate(String.format(SQLQueries.DELETELABELBYID.getQuery(), aLong));
        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
    }
}
