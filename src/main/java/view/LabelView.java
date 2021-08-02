package view;

import controller.LabelController;
import model.Label;

import java.sql.SQLException;
import java.util.Scanner;

public class LabelView extends BasicView {

    private final LabelController LABEL_CONTROLLER;

    private final Scanner SCANNER = new Scanner(System.in);

    private final String MENU_MESSAGE = "Choose action on label: \n" +
            "1. Get label by ID\n" +
            "2. Get all labels\n" +
            "3. Add new label\n" +
            "4. Edit existing label\n" +
            "5. Delete label\n" +
            "6. Exit";

    public LabelView(LabelController labelController) {
        this.LABEL_CONTROLLER = labelController;
    }

    @Override
    void getById() {
        System.out.println(MENU_MESSAGE);
        try {
            System.out.println("Enter id of necessary label");
            long id = SCANNER.nextInt();
            Label label = LABEL_CONTROLLER.getById(id);
            System.out.println(label.getName());
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred during operation");
        }
    }

    @Override
    void getAll() {
        System.out.println(MENU_MESSAGE);
        try {
            System.out.println(LABEL_CONTROLLER.getAll());
            System.out.println("Operation ended successfully");
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred during operation");
        }
    }

    @Override
    void deleteById() {
        System.out.println(MENU_MESSAGE);
        System.out.println("Enter id of label you want to delete");
        long id = SCANNER.nextInt();
        try {
            LABEL_CONTROLLER.deleteById(id);
            System.out.println("Operation ended successfully");
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred during operation");
        }
    }

    @Override
    void save() {
        System.out.println(MENU_MESSAGE);
        System.out.println("Enter name of the label");
        String name = SCANNER.nextLine();
        System.out.println("Enter label's id");
        int id = SCANNER.nextInt();
        try {
            LABEL_CONTROLLER.save(new Label(id, name));
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred during operation");
        }
    }

    @Override
    void update() {
        System.out.println(MENU_MESSAGE);
        System.out.println("Enter name of the label");
        String name = SCANNER.nextLine();
        System.out.println("Enter label's id");
        int id = SCANNER.nextInt();
        try {
            LABEL_CONTROLLER.update(new Label(id, name));
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred during operation");
        }
    }
}
