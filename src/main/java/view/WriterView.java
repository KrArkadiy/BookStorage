package view;

import controller.WriterController;
import model.Post;
import model.Writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriterView extends BasicView {

    private final WriterController WRITER_CONTROLLER;

    private final Scanner SCANNER = new Scanner(System.in);

    public WriterView(WriterController writerController) {
        this.WRITER_CONTROLLER = writerController;
    }

    @Override
    void getById() {
        System.out.println("Enter id of necessary writer");
        long id = SCANNER.nextInt();

        Writer writer = WRITER_CONTROLLER.getById(id);
        System.out.println(writer.getName());
    }

    @Override
    void getAll() {
        List<Writer> writers = WRITER_CONTROLLER.getAll();

        writers.forEach(x -> System.out.println(x.getName()));
        System.out.println("Operation ended successfully");
    }

    @Override
    void deleteById() {
        System.out.println("Enter id of the writer you want to delete");
        long id = SCANNER.nextInt();

        WRITER_CONTROLLER.deleteById(id);
        System.out.println("Operation ended successfully");
    }

    @Override
    void save() {
        System.out.println("Enter name of the new writer");
        String name = SCANNER.nextLine();
        System.out.println("Enter post id for this writer");
        int postId = SCANNER.nextInt();
        System.out.println("Enter id of the new writer");
        int id = SCANNER.nextInt();
        List<Post> posts = new ArrayList<>();
        Writer newWriter = new Writer(id, name, posts);
        WRITER_CONTROLLER.save(newWriter);
        System.out.println(newWriter.getId() + " "
                + newWriter.getName());
    }

    @Override
    void update() {
        System.out.println("Enter name of the updated writer");
        String name = SCANNER.nextLine();
        System.out.println("Enter id of the writer you want to update");
        int id = SCANNER.nextInt();
        List<Post> posts = WRITER_CONTROLLER.getById((long) id).getPosts();
        Writer updatedWriter = new Writer(id, name, posts);
        WRITER_CONTROLLER.update(updatedWriter);
        System.out.print(updatedWriter.getId() + " " + updatedWriter.getName());
        posts.forEach(x -> System.out.println(x.getContent()));
    }
}