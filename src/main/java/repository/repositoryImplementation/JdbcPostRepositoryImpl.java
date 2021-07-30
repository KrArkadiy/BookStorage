package repository.repositoryImplementation;

import liquibase.pro.packaged.A;
import liquibase.pro.packaged.C;
import liquibase.pro.packaged.J;
import liquibase.pro.packaged.S;
import model.Label;
import model.Post;
import repository.PostRepository;
import utility.JdbcTemplate;

import javax.swing.plaf.nimbus.State;
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

            String creatingWriterTable = "CREATE TABLE Post " +
                    "id INTEGER NOT NULL AUTO_INCREMENT, " +
                    "content VARCHAR (255), " +
                    "created INTEGER NOT NULL, " +
                    "updated INTEGER NOT NULL)";

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

            String insertInformationFirst = "INSERT INTO Post (content, created, updated) VALUES ('firstContent', 1, 1)";
            String insertInformationSecond = "INSERT INTO Post (content, created, updated) VALUES ('secondContent', 1, 1)";

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
    public Post getById(Long aLong) throws ClassNotFoundException, SQLException{
        Connection connection = null;
        Statement statement = null;

        Integer id = 0;
        String content = "";
        Long created = 0L;
        Long updated = 0L;
        List<Label> labels = new ArrayList<>();

        try{
            System.out.println("Registering JDBC driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Getting post...");
            statement = connection.createStatement();

            String gettingPostById = "SELECT id, content, created, updated, name FROM Post LEFT JOIN Label ON Post.id = Label.id WHERE id = " + aLong;
            ResultSet resultSet = statement.executeQuery(gettingPostById);

            while (resultSet.next()){
                id = resultSet.getInt(1);
                content = resultSet.getString(2);
                created = resultSet.getLong(3);
                updated = resultSet.getLong(4);
                Label label = resultSet.getObject(5, Label.class);
                labels.add(label);
            }
            return new Post(id, content, created, updated,labels);
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
    public List<Post> getAll() throws ClassNotFoundException, SQLException {

        Connection connection = null;
        Statement statement = null;

        Integer id = 0;
        String content = "";
        Long created = 0L;
        Long updated = 0L;
        List<Label> labels = new ArrayList<>();
        List<Post> posts = new ArrayList<>();

        try {
            System.out.println("Registering JDBC driver...");
            Class.forName(JdbcTemplate.getJdbcDriver());

            System.out.println("Creating connection to database...");
            connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            System.out.println("Getting all records...");
            statement = connection.createStatement();

            String gettingAllResults = "SELECT id, content, created, updated, name FROM Post LEFT JOIN Label ON Post.id = Label.id";

            ResultSet resultSet = statement.executeQuery(gettingAllResults);

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                content = resultSet.getString(2);
                created = resultSet.getLong(3);
                updated = resultSet.getLong(4);
                Label label = resultSet.getObject(5, Label.class);
                labels.add(label);
                posts.add(new Post(id, content, created, updated, labels));
            }
            return posts;
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
    public Post update(Post post) {

        return null;
    }

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }
}
