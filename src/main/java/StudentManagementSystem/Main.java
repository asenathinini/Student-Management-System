package StudentManagementSystem;



import Student.*;

import java.util.Scanner;
import gui.LoginGUI;
import javax.swing.SwingUtilities;


public class Main {
    public static void main(String[] args) {
        
         DBSetup.createDatabase();
         System.out.println("Student Management System running");


          SwingUtilities.invokeLater(() -> {
            new LoginGUI().setVisible(true);
        });
    }
}
