package view;

import java.sql.SQLException;
import java.util.Scanner;

public abstract class BasicView {

    private final Scanner sc = new Scanner(System.in);

    abstract void getById() throws ClassNotFoundException, SQLException;

    abstract void getAll() throws SQLException, ClassNotFoundException;

    abstract void deleteById() throws SQLException, ClassNotFoundException;

    abstract void save() throws SQLException, ClassNotFoundException;

    abstract void update() throws SQLException, ClassNotFoundException;

    public void show() throws ClassNotFoundException, SQLException {
        int option = sc.nextInt();
        if (option == 1) {
            getById();
        } else if (option == 2) {
            getAll();
        } else if (option == 3) {
            save();
        } else if (option == 4) {
            update();
        } else if (option == 5) {
            deleteById();
        } else {
            return;
        }
    }
}

