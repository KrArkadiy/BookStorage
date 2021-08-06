package repository.repositoryImplementation;

import model.Label;
import model.Post;
import repository.PostRepository;
import repository.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostRepositoryImpl implements PostRepository {

    /*public void creatingPostTableInDatabase() throws ClassNotFoundException, SQLException {

        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {

            String creatingWriterTable = "CREATE TABLE Post (" +
                    "id INTEGER NOT NULL, " +
                    "content VARCHAR (255), " +
                    "created INTEGER NOT NULL, " +
                    "updated INTEGER NOT NULL," +
                    "label_id INTEGER)";

            statement.executeUpdate(creatingWriterTable);
            System.out.println("Table successfully created...");
        } catch (SQLException exception){
            System.out.println("Error occurred " + exception.getMessage());
        }
    }

    public void insertInformation() throws ClassNotFoundException, SQLException {
        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {

            String insertInformationFirst = "INSERT INTO Post (id, content, created, updated, label_id) VALUES (1, 'firstContent', 1, 1,1)";
            String insertInformationSecond = "INSERT INTO Post (id, content, created, updated, label_id) VALUES (2, 'secondContent', 1, 1,2)";

            statement.executeUpdate(insertInformationFirst);
            statement.executeUpdate(insertInformationSecond);

            System.out.println("Data successfully inserted...");
        } catch (SQLException exception){
            System.out.println("Error occurred " + exception.getMessage());
        }
    }*/

    @Override
    public Post getById(Long aLong) {
        int id = 0;
        String content = "";
        long created = 0L;
        long updated = 0L;
        List<Label> labels = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(String.format(SQLQueries.GETPOSTBYID.getQuery(), aLong));

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                content = resultSet.getString(2);
                created = resultSet.getLong(3);
                updated = resultSet.getLong(4);
                String name = resultSet.getString(7);
                Label label = new Label(id, name);
                labels.add(label);
            }
        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
        return new Post(id, content, created, updated, labels);
    }

    @Override
    public List<Post> getAll() {

        List<Post> posts = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {

            ResultSet resultSet = statement.executeQuery(SQLQueries.GETALLPOST.getQuery());

            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String content = resultSet.getString(2);
                Long created = resultSet.getLong(3);
                Long updated = resultSet.getLong(4);
                String name = resultSet.getString(7);
                Label label = new Label(id, name);
                List<Label> labels = new ArrayList<>();
                labels.add(label);
                posts.add(new Post(id, content, created, updated, labels));
            }
        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
        return posts;
    }

    @Override
    public Post update(Post post) {

        int id = 0;
        String content = "";
        long created = 0L;
        long updated = 0L;
        String name;
        List<Label> labels = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {
            System.out.println("Getting record...");

            ResultSet resultSet = statement.executeQuery(String.format(SQLQueries.UPDATEPOST.getQuery(), post.getId()));

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                content = resultSet.getString(2);
                created = resultSet.getLong(3);
                updated = resultSet.getLong(4);
                name = resultSet.getString(7);
                Label label = new Label(id, name);
                labels.add(label);
            }

            System.out.println("Changing content of the post...");
            System.out.println("Getting updated record...");

            resultSet = statement.executeQuery(String.format(SQLQueries.UPDATEPOST.getQuery(), post.getId()));
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                content = resultSet.getString(2);
                created = resultSet.getLong(3);
                updated = resultSet.getLong(4);
                name = resultSet.getString(7);
                Label label = new Label(id, name);
                labels.add(label);
            }
        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
        return new Post(id, content, created, updated, labels);
    }

    @Override
    public Post save(Post post) {

        List<Label> labels = post.getLabels();

        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {
            String creatingNewPost = String.format(SQLQueries.SAVEPOST.getQuery(), post.getId())
                    + ", " + "'" + post.getContent() + "'" + ", " + post.getCreated() + ", " + post.getUpdated() + ", "
                    + post.getId() + ")";

            statement.executeUpdate(creatingNewPost);

            System.out.println("New post added to database");
        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
        return post;
    }

    @Override
    public void deleteById(Long aLong) {
        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {
            String removingSafeMode = "SET_SAFE_UPDATES = 0";
            statement.executeUpdate(removingSafeMode);

            statement.executeUpdate(String.format(SQLQueries.DELETEPOSTBYID.getQuery(), aLong));
        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
    }
}
