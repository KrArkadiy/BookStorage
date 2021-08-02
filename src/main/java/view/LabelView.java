package view;

import controller.LabelController;
import model.Label;

import java.sql.SQLException;
import java.util.Scanner;

public class LabelView extends BasicView {

    LabelController labelController;
    private final Scanner sc = new Scanner(System.in);

    public LabelView(LabelController labelController) {
        this.labelController = labelController;
    }

    @Override
    void getById() throws ClassNotFoundException, SQLException {
        long id = sc.nextInt();
        labelController.getById(id);
    }

    @Override
    void getAll() throws SQLException, ClassNotFoundException {
        labelController.getAll();
    }

    @Override
    void deleteById() throws SQLException, ClassNotFoundException {
        long id = sc.nextInt();
        labelController.deleteById(id);
    }

    @Override
    void save() throws SQLException, ClassNotFoundException {
        String name = sc.nextLine();
        int id = sc.nextInt();
        labelController.save(new Label(id, name));
    }

    @Override
    void update() throws SQLException, ClassNotFoundException {
        String name = sc.nextLine();
        int id = Integer.parseInt(name.substring(0, 1));
        labelController.update(new Label(id, name));
    }
}
