package view;

import controller.PostController;
import model.Label;
import model.Post;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView extends BasicView {

    private PostController postController;
    private final Scanner sc = new Scanner(System.in);

    public PostView(PostController postController) {
        this.postController = postController;
    }

    @Override
    void getById() throws ClassNotFoundException, SQLException {
        long id = sc.nextInt();
        postController.getById(id);
    }

    @Override
    void getAll() throws SQLException, ClassNotFoundException {
        postController.getAll();
    }

    @Override
    void deleteById() throws SQLException, ClassNotFoundException {
        long id = sc.nextInt();
        postController.deleteById(id);
    }

    @Override
    void save() throws SQLException, ClassNotFoundException {
        String content = sc.nextLine();
        int id = sc.nextInt();
        long updated = sc.nextInt();
        long created = sc.nextInt();
        List<Label> labels = new ArrayList<>();
        postController.save(new Post(id, content, created, updated, labels));
    }

    @Override
    void update() throws SQLException, ClassNotFoundException {
        String content = sc.nextLine();
        int id = Integer.parseInt(content.substring(0, 1));
        long updated = sc.nextInt();
        long created = sc.nextInt();
        List<Label> labels = new ArrayList<>();
        postController.update(new Post(id, content, created, updated, labels));
    }
}
