package view;

import controller.LabelController;
import controller.PostController;
import model.Label;
import model.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utility.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class PostViewTest {

    private final PostController POST_CONTROLLER = new PostController();

    @Test
    void givenId_whenRun_thenReturnPostContent() {
        try {
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            Assertions.assertEquals("firstContent", POST_CONTROLLER.getById(1L).getContent());
            Assertions.assertNotEquals("ThirdContent", POST_CONTROLLER.getById(1L).getContent());
        } catch (ClassNotFoundException | SQLException exception) {
            exception.getStackTrace();
        }
    }

    @Test
    void givenNotExistingId_whenRun_thenReturnEmptyString(){
        try {
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            Assertions.assertEquals("",POST_CONTROLLER.getById(5L).getContent());
        } catch (ClassNotFoundException | SQLException exception) {
            exception.getStackTrace();
        }
    }

    @Test
    void givenTableInDB_whenRun_thenReturnAllPosts() {
        try {
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());
            List<Post> posts = POST_CONTROLLER.getAll();
            Assertions.assertEquals("secondContent", posts.get(0).getContent());
            Assertions.assertEquals("firstContent", posts.get(1).getContent());
            Assertions.assertEquals("ContentNewOnePost", posts.get(2).getContent());
            Assertions.assertEquals("", posts.get(3).getContent());
        } catch (ClassNotFoundException | SQLException exception) {
            exception.getStackTrace();
        }
    }

    @Test
    void givenId_whenRun_thenPostWithThatIdDeletedFromDB() {
        try {
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());
            POST_CONTROLLER.deleteById(1L);
            List<Post> posts = POST_CONTROLLER.getAll();
            Assertions.assertEquals("secondContent", posts.get(0).getContent());
            Assertions.assertEquals("ContentNewOnePost", posts.get(1).getContent());
        } catch (ClassNotFoundException | SQLException exception) {
            exception.getStackTrace();
        }
    }

    @Test
    void givenNewLabel_whenRun_thenNewLabelCreatedInDB() {
        try {
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());
            int testPostId = 1;
            Label testLabel = new Label(testPostId, new LabelController().getById((long)testPostId).getName());
            List<Label> labels = new ArrayList<>();
            labels.add(testLabel);
            POST_CONTROLLER.save(new Post(1, "firstContent", 1L, 1L, labels));
            List<Post> posts = POST_CONTROLLER.getAll();
            Assertions.assertEquals("secondContent", posts.get(0).getContent());
            Assertions.assertEquals("firstContent", posts.get(1).getContent());
            Assertions.assertEquals("ContentNewOnePost", posts.get(2).getContent());
        } catch (ClassNotFoundException | SQLException exception) {
            exception.getStackTrace();
        }
    }
}