package view;

import controller.PostController;
import model.Label;
import model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView extends BasicView {

    private final PostController POST_CONTROLLER;
    private final Scanner SCANNER = new Scanner(System.in);

    public PostView(PostController postController) {
        this.POST_CONTROLLER = postController;
    }

    @Override
    void getById() {
        System.out.println("Enter id of necessary label");
        long id = SCANNER.nextInt();
        Post post = POST_CONTROLLER.getById(id);
        System.out.println(post.getId() + post.getContent());
        for (Label label : post.getLabels()) {
            System.out.println(label.getName());
        }
    }

    @Override
    void getAll() {
        List<Post> posts = POST_CONTROLLER.getAll();
        for (Post post : posts) {
            System.out.println(post.getId() + post.getContent());
            for (Label label : post.getLabels()) {
                System.out.println(label.getName());
            }
        }
        System.out.println("Operation ended successfully");
    }

    @Override
    void deleteById() {
        System.out.println("Enter id of the post you want to delete");
        long id = SCANNER.nextInt();
        POST_CONTROLLER.deleteById(id);
        System.out.println("Operation ended successfully");
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
        System.out.println("Enter label id for this post");
        int labelId = SCANNER.nextInt();
        List<Label> labels = new ArrayList<>();

        Post newPost = new Post(id, content, created, updated, labels);
        POST_CONTROLLER.save(newPost);
        System.out.println(newPost.getId() + " "
                + newPost.getContent() + " "
                + newPost.getCreated() + " "
                + newPost.getUpdated());
        System.out.println("Operation ended successfully");
    }

    @Override
    void update() {
        System.out.println("Enter content of the post you want to update");
        String content = SCANNER.nextLine();
        System.out.println("Enter id for the new post");
        int id = SCANNER.nextInt();
        System.out.println("Enter number of update");
        long updated = SCANNER.nextInt();
        System.out.println("Enter number of creation");
        long created = SCANNER.nextInt();

        List<Label> labels = POST_CONTROLLER.getById((long) id).getLabels();
        Post post = new Post(id, content, updated, created, labels);
        POST_CONTROLLER.update(post);
        System.out.print(post.getId() + " "
                + post.getContent() + " "
                + post.getUpdated() + " "
                + post.getCreated() + " ");
        labels.forEach(x -> System.out.println(x.getName()));
    }
}
