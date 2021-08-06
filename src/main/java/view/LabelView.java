package view;

import controller.LabelController;
import model.Label;

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
        System.out.println("Enter id of necessary label");
        long id = SCANNER.nextInt();
        Label label = LABEL_CONTROLLER.getById(id);
        System.out.println(label.getName());
    }

    @Override
    void getAll() {
        List<Label> labels = (LABEL_CONTROLLER.getAll());
        labels.forEach(x -> System.out.println(x.getName()));
        System.out.println("Operation ended successfully");
    }

    @Override
    void deleteById() {
        System.out.println("Enter id of label you want to delete");
        long id = SCANNER.nextInt();

        LABEL_CONTROLLER.deleteById(id);
        System.out.println("Operation ended successfully");
    }

    @Override
    void save() {
        System.out.println("Enter name of the label");
        String name = SCANNER.nextLine();
        System.out.println("Enter label's id");
        int id = SCANNER.nextInt();
        LABEL_CONTROLLER.save(new Label(id, name));
    }

    @Override
    void update() {
        System.out.println("Enter name of the label");
        String name = SCANNER.nextLine();
        System.out.println("Enter label's id");
        int id = SCANNER.nextInt();
        Label updateLabel = new Label(id, name);
        LABEL_CONTROLLER.update(updateLabel);
        System.out.println(updateLabel.getName());
    }
}
