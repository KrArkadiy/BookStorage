package repository.repositoryImplementation;

import model.Label;
import model.Post;
import repository.PostRepository;
import utility.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostRepositoryImpl implements PostRepository {

    public void creatingPostTableInDatabase() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            System.out.println("Registering JDBC Driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Creating table in selected database...");
            statement = connection.createStatement();

            String creatingWriterTable = "CREATE TABLE Post (" +
                    "id INTEGER NOT NULL, " +
                    "content VARCHAR (255), " +
                    "created INTEGER NOT NULL, " +
                    "updated INTEGER NOT NULL," +
                    "label_id INTEGER)";

            statement.executeUpdate(creatingWriterTable);
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
            System.out.println("Registering JDBC driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Inserting information in selected database...");
            statement = connection.createStatement();

            String insertInformationFirst = "INSERT INTO Post (id, content, created, updated, label_id) VALUES (1, 'firstContent', 1, 1,1)";
            String insertInformationSecond = "INSERT INTO Post (id, content, created, updated, label_id) VALUES (2, 'secondContent', 1, 1,2)";

            statement.executeUpdate(insertInformationFirst);
            statement.executeUpdate(insertInformationSecond);

            System.out.println("Data successfully inserted...");
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
    public Post getById(Long aLong) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        Integer id = 0;
        String content = "";
        Long created = 0L;
        Long updated = 0L;
        List<Label> labels = new ArrayList<>();

        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Getting post...");
            statement = connection.createStatement();

            String gettingPostById = "SELECT * FROM Post LEFT JOIN Label ON Post.id = Label.id WHERE post.id = " + aLong;
            ResultSet resultSet = statement.executeQuery(gettingPostById);

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                content = resultSet.getString(2);
                created = resultSet.getLong(3);
                updated = resultSet.getLong(4);
                String name = resultSet.getString(7);
                Label label = new Label(id, name);
                labels.add(label);
            }
            return new Post(id, content, created, updated, labels);
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
    public List<Post> getAll() throws ClassNotFoundException, SQLException {

        Connection connection = null;
        Statement statement = null;

        List<Post> posts = new ArrayList<>();

        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Getting all records...");
            statement = connection.createStatement();

            String gettingAllResults = "SELECT * FROM Post LEFT JOIN Label ON Post.label_id = Label.id ORDER BY Post.id ASC";

            ResultSet resultSet = statement.executeQuery(gettingAllResults);

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
            return posts;
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
    public Post update(Post post) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Getting record...");
            statement = connection.createStatement();

            String updatePost = "SELECT * FROM Post LEFT JOIN Label ON Post.id = Label.id WHERE post.id = " + post.getId();

            ResultSet resultSet = statement.executeQuery(updatePost);

            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String content = resultSet.getString(2);
                Long created = resultSet.getLong(3);
                Long updated = resultSet.getLong(4);
                String name = resultSet.getString(7);
                Label label = new Label(id, name);
                List<Label> labels = new ArrayList<>();
                labels.add(label);
            }

            System.out.println("Changing content of the post...");
            System.out.println("Getting updated record...");

            resultSet = statement.executeQuery(updatePost);
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String content = resultSet.getString(2) + "Updated content";
                Long created = resultSet.getLong(3);
                Long updated = resultSet.getLong(4);
                String name = resultSet.getString(7);
                Label label = new Label(id, name);
                List<Label> labels = new ArrayList<>();
                labels.add(label);
            }
            return post;
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
    public Post save(Post post) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        Statement statement = null;

        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Creating new Post");
            statement = connection.createStatement();

            String creatingNewPost = "INSERT INTO Post (id, content, created, updated) VALUES (" + post.getId()
                    + ", " + post.getContent() + ", " + post.getCreated() + ", " + post.getUpdated() + ")";
            String creatingNewLabelForPost = "INSERT INTO Label (id, name) VALUES (" + post.getId() + ", "
                    + post.getLabels() + ")";

            statement.executeUpdate(creatingNewPost);
            statement.executeUpdate(creatingNewLabelForPost);

            System.out.println("New post added to database");
            return post;
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
    public void deleteById(Long aLong) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(JdbcTemplate.getJdbcDriver());

            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            statement = connection.createStatement();

            String gettingRecords = "SELECT * FROM Post LEFT JOIN Label ON Post.label_id = Label.id WHERE post.id = " + aLong;

            ResultSet resultSet = statement.executeQuery(gettingRecords);

            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String content = resultSet.getString(2);
                Long created = resultSet.getLong(3);
                Long updated = resultSet.getLong(4);
                String name = resultSet.getString(5);
                Label label = new Label(id, name);
                List<Label> labels = new ArrayList<>();
                labels.add(label);
            }

            System.out.println("Deleting label with id = " + aLong);

            statement = connection.createStatement();

            String deletingLabel = "DELETE FROM Post WHERE id = " + aLong;

            statement.executeUpdate(deletingLabel);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
