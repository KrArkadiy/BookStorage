package view;

import controller.LabelController;
import model.Label;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LabelView extends BasicView {

    private final LabelController LABEL_CONTROLLER;

    private final Scanner SCANNER = new Scanner(System.in);

    public LabelView(LabelController labelController) {
        this.LABEL_CONTROLLER = labelController;
    }

    @Override
    void getById() {
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
        try {
            List<Label> labels = (LABEL_CONTROLLER.getAll());
            labels.forEach(x-> System.out.println(x.getName()));
            System.out.println("Operation ended successfully");
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred during operation");
        }
    }

    @Override
    void deleteById() {
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
        System.out.println("Enter name of the label");
        String name = SCANNER.nextLine();
        System.out.println("Enter label's id");
        int id = SCANNER.nextInt();
        try {
            Label updateLabel = new Label(id, name);
            LABEL_CONTROLLER.update(updateLabel);
            System.out.println(updateLabel.getName());
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Error occurred during operation");
        }
    }
}
