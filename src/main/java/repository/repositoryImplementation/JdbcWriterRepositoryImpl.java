package repository.repositoryImplementation;

import model.Post;
import model.Writer;
import repository.WriterRepository;
import utility.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl implements WriterRepository {

    public void creatingWriterTableInDatabase() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Creating table in selected database...");
            statement = connection.createStatement();

            String creatingWriterTable = "CREATE TABLE Writer (" +
                    "id INTEGER NOT NULL, " +
                    "name varchar(255), " +
                    "post_id INTEGER)";

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

            String insertInformationFirst = "INSERT INTO Writer (id, name, post_id) VALUES (1, 'FirstWriter', 2)";
            String insertInformationSecond = "INSERT INTO Writer (id, name, post_id) VALUES (2, 'SecondWriter', 1)";

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
    public Writer getById(Long aLong) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        Integer id = 0;
        String name = "";
        List<Post> posts = new ArrayList<>();

        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            statement = connection.createStatement();

            System.out.println("Getting record...");
            String gettingWriterById = "SELECT * FROM Writer LEFT JOIN Post ON Writer.id = Post.id WHERE writer.id = " + aLong;

            ResultSet resultSet = statement.executeQuery(gettingWriterById);

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
                Post post = resultSet.getObject(3, Post.class);
                posts.add(post);
            }
            return new Writer(id, name, posts);
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
    public List<Writer> getAll() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        List<Writer> writers = new ArrayList<>();
        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Getting all records...");
            statement = connection.createStatement();

            String gettingAllRecords = "SELECT * FROM Writer LEFT JOIN Post ON Writer.post_id = Post.id";

            ResultSet resultSet = statement.executeQuery(gettingAllRecords);

            while (resultSet.next()) {
                List<Post> posts = new ArrayList<>();
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Post post = resultSet.getObject(3, Post.class);
                posts.add(post);
                writers.add(new Writer(id, name, posts));
            }
            return writers;
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
    public Writer update(Writer writer) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Getting record");
            statement = connection.createStatement();

            String updateWriter = "SELECT * FROM Writer LEFT JOIN Post ON Writer.post_id = Post.id WHERE writer_id = " + writer.getId();

            ResultSet resultSet = statement.executeQuery(updateWriter);

            while (resultSet.next()) {
                List<Post> posts = new ArrayList<>();
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Post post = resultSet.getObject(3, Post.class);
                posts.add(post);
            }

            System.out.println("Changing content of the writer...");
            System.out.println("Getting updated record...");

            resultSet = statement.executeQuery(updateWriter);
            while (resultSet.next()) {
                List<Post> posts = new ArrayList<>();
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2) + "Updated";
                Post post = resultSet.getObject(3, Post.class);
                posts.add(post);
            }
            return writer;
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
    public Writer save(Writer writer) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            System.out.println("Registering JDBC Driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(),JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Creating new Writer...");
            statement = connection.createStatement();

            String creatingNewWriter = "INSERT INTO Writer (id, name) VALUES (" + writer.getId() + ", " + writer.getName() + ")";
            String creatingNewPostForWriter = "INSERT INTO Post (id,content,created, updated) VALUES (" + writer.getPosts() + ")";

            statement.executeUpdate(creatingNewWriter);
            statement.executeUpdate(creatingNewPostForWriter);

            System.out.println("New writer added to database");
            return writer;
        } finally {
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }

    @Override
    public void deleteById(Long aLong) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        try{
            Class.forName(JdbcTemplate.getJdbcDriver());
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            statement = connection.createStatement();

            String gettingRecords = "SELECT * FROM Writer LEFT JOIN Post ON Writer.post_id = Post.id WHERE writer.id = " + aLong;

            ResultSet resultSet = statement.executeQuery(gettingRecords);

            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                List<Post> posts = new ArrayList<>();
                Post post = resultSet.getObject(3, Post.class);
                posts.add(post);
            }

            System.out.println("Deleting writer with id = " + aLong);

            statement = connection.createStatement();

            String deletingWriter = "DELETE FROM Writer WHERE id = " + aLong;
            statement.executeUpdate(deletingWriter);
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
