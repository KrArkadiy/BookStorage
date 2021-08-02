package view;

import controller.PostController;
import model.Label;
import model.Post;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView extends BasicView {

    private final PostController POST_CONTROLLER;
    private final Scanner SCANNER = new Scanner(System.in);

    private final String MENU_MESSAGE = "Choose action on label: \n" +
            "1. Get post by ID\n" +
            "2. Get all posts\n" +
            "3. Add new post\n" +
            "4. Edit existing post\n" +
            "5. Delete post\n" +
            "6. Exit";

    public PostView(PostController postController) {
        this.POST_CONTROLLER = postController;
    }

    @Override
    void getById() {
        System.out.println(MENU_MESSAGE);
        System.out.println("Enter id of necessary label");
        long id = SCANNER.nextInt();
        try {
            Post post = POST_CONTROLLER.getById(id);
            System.out.println(post.getContent());
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Override
    void getAll() {
        System.out.println(MENU_MESSAGE);
        try {
            POST_CONTROLLER.getAll();
            System.out.println("Operation ended successfully");
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred during operation");
        }
    }

    @Override
    void deleteById() {
        System.out.println("Enter id of the post you want to delete");
        long id = SCANNER.nextInt();
        try {
            POST_CONTROLLER.deleteById(id);
            System.out.println("Operation ended successfully");
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Override
    void save() {
        System.out.println("Enter content of the new post");
        String content = SCANNER.nextLine();
        System.out.println("Enter id for the new post");
        int id = SCANNER.nextInt();
        System.out.println("Enter number of update");
        long updated = SCANNER.nextInt();
        System.out.println("Enter number of creation");
        long created = SCANNER.nextInt();
        List<Label> labels = new ArrayList<>();
        try {
            POST_CONTROLLER.save(new Post(id, content, created, updated, labels));
            System.out.println("Operation ended successfully");
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Override
    void update() {
        System.out.println("Enter content of the new post");
        String content = SCANNER.nextLine();
        System.out.println("Enter id for the new post");
        int id = SCANNER.nextInt();
        System.out.println("Enter number of update");
        long updated = SCANNER.nextInt();
        System.out.println("Enter number of creation");
        long created = SCANNER.nextInt();
        List<Label> labels = new ArrayList<>();
        try {
            POST_CONTROLLER.update(new Post(id, content, created, updated, labels));
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }
}
