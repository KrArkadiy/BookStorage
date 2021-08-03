package view;

import controller.LabelController;
import model.Label;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utility.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class LabelViewTest {

    private final LabelController LABEL_CONTROLLER = new LabelController();

    @Test
    void givenId_whenRun_thenReturnLabelName() {
        try{
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());

            Assertions.assertEquals("FirstLabel", LABEL_CONTROLLER.getById(1L).getName());
            Assertions.assertNotEquals("ThirdLabel", LABEL_CONTROLLER.getById(1L).getName());
        } catch (ClassNotFoundException | SQLException exception){
            exception.getStackTrace();
        }
    }

    @Test
    void getAll() {
        try{
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());
            List<Label> labels = LABEL_CONTROLLER.getAll();
            Assertions.assertEquals("SecondLabel", labels.get(0).getName());
            Assertions.assertEquals("FirstLabel", labels.get(1).getName());
            Assertions.assertEquals("ThirdLabel", labels.get(2).getName());
        } catch (ClassNotFoundException | SQLException exception){
            exception.getStackTrace();
        }
    }

    @Test
    void deleteById() {
        try{
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());
            LABEL_CONTROLLER.deleteById(1L);
            List<Label> labels = LABEL_CONTROLLER.getAll();
            Assertions.assertEquals("SecondLabel", labels.get(0).getName());
            Assertions.assertEquals("ThirdLabel", labels.get(1).getName());
        } catch (ClassNotFoundException | SQLException exception){
            exception.getStackTrace();
        }
    }

    @Test
    void save() {
        try{
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());
            List<Label> labels = LABEL_CONTROLLER.getAll();
            LABEL_CONTROLLER.save(new Label(1, "ForthLabel"));
            Assertions.assertEquals("SecondLabel", labels.get(0).getName());
            Assertions.assertEquals("FirstLabel", labels.get(1).getName());
            Assertions.assertEquals("ThirdLabel", labels.get(2).getName());
            Assertions.assertEquals("ForthLabel", labels.get(3).getName());
        } catch (ClassNotFoundException | SQLException exception){
            exception.getStackTrace();
        }
    }

/*    @Test
    void update() {
        try{
            Class.forName(JdbcTemplate.getJdbcDriver());
            Connection connection = DriverManager.getConnection(JdbcTemplate.getDatabaseUrl(), JdbcTemplate.getUSER(), JdbcTemplate.getPASSWORD());
            Label label = new Label(1, "ForthLabel");
            LABEL_CONTROLLER.update(label);
            List<Label> labels = LABEL_CONTROLLER.getAll();
            Assertions.assertEquals("SecondLabel", labels.get(0).getName());
            Assertions.assertEquals("ThirdLabel", labels.get(1).getName());
            Assertions.assertEquals("FirstLabel", labels.get(2).getName());
        } catch (ClassNotFoundException | SQLException exception){
            exception.getStackTrace();
        }
    }*/
}