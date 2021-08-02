package view;

import controller.WriterController;
import model.Post;
import model.Writer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriterView extends BasicView {

    private WriterController writerController;
    private final Scanner sc = new Scanner(System.in);

    public WriterView(WriterController writerController) {
        this.writerController = writerController;
    }

    @Override
    void getById() throws ClassNotFoundException, SQLException {
        long id = sc.nextInt();
        writerController.getById(id);
    }

    @Override
    void getAll() throws SQLException, ClassNotFoundException {
        writerController.getAll();
    }

    @Override
    void deleteById() throws SQLException, ClassNotFoundException {
        long id = sc.nextInt();
        writerController.deleteById(id);
    }

    @Override
    void save() throws SQLException, ClassNotFoundException {
        int id = sc.nextInt();
        String name = sc.nextLine();
        List<Post> posts = new ArrayList<>();
        writerController.save(new Writer(id, name, posts));
    }

    @Override
    void update() throws SQLException, ClassNotFoundException {
        int id = sc.nextInt();
        String name = sc.nextLine();
        List<Post> posts = new ArrayList<>();
        writerController.update(new Writer(id, name, posts));
    }
}