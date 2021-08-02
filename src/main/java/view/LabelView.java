package view;

import controller.LabelController;
import liquibase.Liquibase;
import model.Label;

import java.sql.SQLException;
import java.util.Scanner;

public class LabelView extends BasicView {

    private LabelController labelController;

    private Scanner sc = new Scanner(System.in);

    private final String MENU_MESSAGE = "Choose action on label: \n" +
            "1. Get label by ID\n" +
            "2. Get all labels\n" +
            "3. Add new label\n" +
            "4. Edit existing label\n" +
            "5. Delete label\n" +
            "6. Exit";

    public LabelView(LabelController labelController) {
        this.labelController = labelController;
    }

    @Override
    void getById() {
        System.out.println(MENU_MESSAGE);
        try {
            System.out.println("Enter id of necessary label");
            long id = sc.nextInt();
            Label label = labelController.getById(id);
            System.out.println(label.getName());
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred during operation");
        }
    }

    @Override
    void getAll() {
        System.out.println(MENU_MESSAGE);
        try {
            System.out.println(labelController.getAll());
            System.out.println("Operation ended successfully");
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred during operation");
        }
    }

    @Override
    void deleteById() {
        System.out.println(MENU_MESSAGE);
        System.out.println("Enter id of label you want to delete");
        long id = sc.nextInt();
        try {
            labelController.deleteById(id);
            System.out.println("Operation ended successfully");
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred during operation");
        }
    }

    @Override
    void save() {
        System.out.println(MENU_MESSAGE);
        System.out.println("Enter name of the label");
        String name = sc.nextLine();
        System.out.println("Enter label's id");
        int id = sc.nextInt();
        try {
            labelController.save(new Label(id, name));
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred during operation");
        }
    }

    @Override
    void update() {
        System.out.println(MENU_MESSAGE);
        System.out.println("Enter name of the label");
        String name = sc.nextLine();
        System.out.println("Enter label's id");
        int id = sc.nextInt();
        try {
            labelController.update(new Label(id, name));
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred during operation");
        }
    }
}
