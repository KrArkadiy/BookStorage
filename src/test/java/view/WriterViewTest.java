package view;

import controller.WriterController;
import model.Label;
import model.Post;
import model.Writer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utility.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class WriterViewTest {

    private final WriterController WRITER_CONTROLLER  = new WriterController();

    @Test
    void givenId_whenRun_thenReturnWriterName() {
        try {
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            Assertions.assertEquals("SecondWriter", WRITER_CONTROLLER.getById(2L).getName());
            Assertions.assertNotEquals("NewOneWriterTest", WRITER_CONTROLLER.getById(3L).getName());
        } catch (ClassNotFoundException | SQLException exception) {
            exception.getStackTrace();
        }
    }

    @Test
    void givenNotExistingId_whenRun_thenReturnEmptyString(){
        try {
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            Assertions.assertEquals("",WRITER_CONTROLLER.getById(5L).getName());
        } catch (ClassNotFoundException | SQLException exception) {
            exception.getStackTrace();
        }
    }

    @Test
    void givenTableInDB_whenRun_thenReturnAllPosts() {
        try {
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());
            List<Writer> writers = WRITER_CONTROLLER.getAll();
            Assertions.assertEquals("secondContent", writers.get(0).getName());
            Assertions.assertEquals("firstContent", writers.get(1).getName());
            Assertions.assertEquals("ContentNewOnePost", writers.get(2).getName());
            Assertions.assertEquals("", writers.get(3).getName());
        } catch (ClassNotFoundException | SQLException exception) {
            exception.getStackTrace();
        }
    }

    @Test
    void givenId_whenRun_thenPostWithThatIdDeletedFromDB() {
        try {
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());
            WRITER_CONTROLLER.deleteById(2L);
            List<Writer> writers = WRITER_CONTROLLER.getAll();
            Assertions.assertEquals("NewOneWriterTest", writers.get(0).getName());
        } catch (ClassNotFoundException | SQLException exception) {
            exception.getStackTrace();
        }
    }

    @Test
    void givenNewLabel_whenRun_thenNewLabelCreatedInDB() {
        try {
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());
            List<Label> labels = new ArrayList<>();
            List<Post> posts = new ArrayList<>();
            //WRITER_CONTROLLER.save(new Writer(1, "firstWriter", posts));
            List<Writer> writers = WRITER_CONTROLLER.getAll();
            Assertions.assertEquals("SecondWriter", writers.get(0).getName());
            Assertions.assertEquals("SecondWriter", writers.get(5).getName());
        } catch (ClassNotFoundException | SQLException exception) {
            exception.getStackTrace();
        }
    }
}