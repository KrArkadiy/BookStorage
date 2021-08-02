package view;

import java.sql.SQLException;
import java.util.Scanner;

public abstract class BasicView {

    private final Scanner sc = new Scanner(System.in);

    private final String MENU_MESSAGE = "Choose action on entity: \n" +
            "1. Get record by ID\n" +
            "2. Get all records\n" +
            "3. Add new record\n" +
            "4. Edit existing record\n" +
            "5. Delete record\n" +
            "6. Exit";

    abstract void getById();

    abstract void getAll();

    abstract void deleteById();

    abstract void save();

    abstract void update();

    public void show() {
        System.out.println(MENU_MESSAGE);
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

