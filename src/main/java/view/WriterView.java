package view;

import controller.WriterController;
import model.Post;
import model.Writer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriterView extends BasicView {

    private final WriterController WRITER_CONTROLLER;

    private final Scanner SCANNER = new Scanner(System.in);

    private final String MENU_MESSAGE = "Choose action on label: \n" +
            "1. Get writer by ID\n" +
            "2. Get all writers\n" +
            "3. Add new writer\n" +
            "4. Edit existing writer\n" +
            "5. Delete writer\n" +
            "6. Exit";

    public WriterView(WriterController writerController) {
        this.WRITER_CONTROLLER = writerController;
    }

    @Override
    void getById() {
        System.out.println(MENU_MESSAGE);
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
        System.out.println(MENU_MESSAGE);
        try {
            System.out.println(WRITER_CONTROLLER.getAll());
            System.out.println("Operation ended successfully");
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Override
    void deleteById() {
        System.out.println(MENU_MESSAGE);
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
        System.out.println(MENU_MESSAGE);
        System.out.println("Enter id of the new writer");
        int id = SCANNER.nextInt();
        System.out.println("Enter name of the new writer");
        String name = SCANNER.nextLine();
        List<Post> posts = new ArrayList<>();
        try {
            WRITER_CONTROLLER.save(new Writer(id, name, posts));
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }

    @Override
    void update() {
        System.out.println("Enter id of the writer you want to update");
        int id = SCANNER.nextInt();
        System.out.println("Enter name of the updated writer");
        String name = SCANNER.nextLine();
        List<Post> posts = new ArrayList<>();
        try {
            WRITER_CONTROLLER.update(new Writer(id, name, posts));
        } catch (ClassNotFoundException | SQLException exception){
            System.out.println("Error occurred");
        }
    }
}