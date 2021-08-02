package view;

import controller.WriterController;
import liquibase.pro.packaged.W;
import model.Post;
import model.Writer;

import java.sql.SQLException;
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
        try {
            Writer writer = WRITER_CONTROLLER.getById(id);
            System.out.println(writer.getName());
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Override
    void getAll() {
        try {
            List<Writer> writers = WRITER_CONTROLLER.getAll();
            writers.stream().forEach(x-> System.out.println(x.getName()));
            System.out.println("Operation ended successfully");
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Override
    void deleteById() {
        System.out.println("Enter id of the writer you want to delete");
        long id = SCANNER.nextInt();
        try {
            WRITER_CONTROLLER.deleteById(id);
            System.out.println("Operation ended successfully");
        }  catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred");
        }
    }

    @Override
    void save() {
        System.out.println("Enter name of the new writer");
        String name = SCANNER.nextLine();
        System.out.println("Enter id of the new writer");
        int id = SCANNER.nextInt();
        List<Post> posts = new ArrayList<>();
        try {
            WRITER_CONTROLLER.save(new Writer(id, name, posts));
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Override
    void update() {
        System.out.println("Enter name of the updated writer");
        String name = SCANNER.nextLine();
        System.out.println("Enter id of the writer you want to update");
        int id = SCANNER.nextInt();
        try {
            List<Post> posts = WRITER_CONTROLLER.getById((long) id).getPosts();
            Writer updatedWriter = new Writer(id, name, posts);
            WRITER_CONTROLLER.update(updatedWriter);
            System.out.print(updatedWriter.getId() + " " + updatedWriter.getName());
            posts.forEach(x-> System.out.println(x.getContent()));
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }
}