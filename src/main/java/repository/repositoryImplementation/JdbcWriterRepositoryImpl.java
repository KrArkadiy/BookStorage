package repository.repositoryImplementation;

import model.Label;
import model.Post;
import model.Writer;
import repository.SQLQueries;
import repository.WriterRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl implements WriterRepository {

    @Override
    public Writer getById(Long aLong) {

        int id = 0;
        String name = "";
        List<Post> posts = new ArrayList<>();
        List<Label> labels = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {
            System.out.println("Getting record...");

            ResultSet resultSet = statement.executeQuery(String.format(SQLQueries.GETWRITERBYID.getQuery(), aLong));

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
                Integer postId = resultSet.getInt(4);
                String postContent = resultSet.getString(5);
                Long created = resultSet.getLong(6);
                Long updated = resultSet.getLong(7);
                Integer labelId = resultSet.getInt(9);
                String labelName = resultSet.getString(10);
                Label label = new Label(labelId, labelName);
                labels.add(label);
                Post post = new Post(postId, postContent, created, updated, labels);
                posts.add(post);
            }
        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
        return new Writer(id, name, posts);
    }

    @Override
    public List<Writer> getAll() {

        List<Writer> writers = new ArrayList<>();
        int id;
        String name;
        List<Post> posts = new ArrayList<>();
        List<Label> labels = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {

            ResultSet resultSet = statement.executeQuery(SQLQueries.GETALLWRITERS.getQuery());

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
                Integer postId = resultSet.getInt(4);
                String postContent = resultSet.getString(5);
                Long created = resultSet.getLong(6);
                Long updated = resultSet.getLong(7);
                Integer labelId = resultSet.getInt(9);
                String labelName = resultSet.getString(10);
                Label label = new Label(labelId, labelName);
                labels.add(label);
                Post post = new Post(postId, postContent, created, updated, labels);
                posts.add(post);
                writers.add(new Writer(id, name, posts));
            }
        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
        return writers;
    }

    @Override
    public Writer update(Writer writer) {

        int id = 0;
        String name = "";
        List<Post> posts = new ArrayList<>();
        List<Label> labels = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {

            ResultSet resultSet = statement.executeQuery(String.format(SQLQueries.UPDATEWRITER.getQuery(), writer.getId()));

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
                Integer postId = resultSet.getInt(4);
                String postContent = resultSet.getString(5);
                Long created = resultSet.getLong(6);
                Long updated = resultSet.getLong(7);
                Integer labelId = resultSet.getInt(9);
                String labelName = resultSet.getString(10);
                Label label = new Label(labelId, labelName);
                labels.add(label);
                Post post = new Post(postId, postContent, created, updated, labels);
                posts.add(post);
            }

            System.out.println("Changing content of the writer...");
            System.out.println("Getting updated record...");

            resultSet = statement.executeQuery(String.format(SQLQueries.UPDATEWRITER.getQuery(), writer.getId()));
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
                Integer postId = resultSet.getInt(4);
                String postContent = resultSet.getString(5);
                Long created = resultSet.getLong(6);
                Long updated = resultSet.getLong(7);
                Integer labelId = resultSet.getInt(9);
                String labelName = resultSet.getString(10);
                Label label = new Label(labelId, labelName);
                labels.add(label);
                Post post = new Post(postId, postContent, created, updated, labels);
                posts.add(post);
            }
        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
        return new Writer(id, name, posts);
    }

    @Override
    public Writer save(Writer writer) {
        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {
            String creatingNewWriter = String.format(SQLQueries.SAVEWRITER.getQuery(), writer.getId())
                    + ", " + "'" + writer.getName() + "'" + ", " + writer.getId() + ")";

            statement.executeUpdate(creatingNewWriter);

            System.out.println("New writer added to database");
        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
        return writer;
    }

    @Override
    public void deleteById(Long aLong) {
        try (
                Connection connection = DatabaseConnection.getInstance().getConnection();
                Statement statement = connection.createStatement()
        ) {

            ResultSet resultSet = statement.executeQuery(String.format(SQLQueries.DELETEWRITERBYID.getQuery(), aLong));

            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                List<Post> posts = new ArrayList<>();
                Post post = resultSet.getObject(3, Post.class);
                posts.add(post);
            }

            System.out.println("Deleting writer with id = " + aLong);

            String deletingWriter = "DELETE FROM Writer WHERE writer.id = " + aLong;
            statement.executeUpdate(deletingWriter);
        } catch (SQLException exception) {
            System.out.println("Error occurred " + exception.getMessage());
        }
    }
}