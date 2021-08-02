package view;

import controller.LabelController;
import controller.PostController;
import controller.WriterController;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleFunctionality {
    private final Scanner SCANNER = new Scanner(System.in);

    private final String OPTIONS = "Choose entity: \n" +
            "1. Label\n" +
            "2. Writer\n" +
            "3. Post\n" +
            "4. Exit\n";

    LabelController labelController = new LabelController();
    WriterController writerController = new WriterController();
    PostController postController = new PostController();

    BasicView labelView = new LabelView(labelController);
    BasicView postView = new PostView(postController);
    BasicView writerView = new WriterView(writerController);

    public void run() throws ClassNotFoundException, SQLException {
        System.out.println(OPTIONS);
        int option = SCANNER.nextInt();
        if (option == 1) {
            labelView.show();
        } else if (option == 2) {
            writerView.show();
        } else if (option == 3) {
            postView.show();
        } else if (option == 4) {
            return;
        }
    }
}
