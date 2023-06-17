package org.example;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class UserInterface {
    public void displayMenu() {
        // Display the menu to the user and interact with FileOrganizer class
        JFrame frame = new JFrame("File Organizer");

        // Create a text box
        JTextField textField = new JTextField();
        textField.setBounds(20, 20, 200, 30);
        frame.getContentPane().add(textField);

        // Create a JButton
        JButton button = new JButton("Click Me");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define the actions to be performed when the button is clicked
                System.out.println("Button clicked!");
                FileOrganizer fileOrganizer = new FileOrganizer();
                fileOrganizer.run();
            }
        });
        frame.getContentPane().add(button);

        // Create a JButton
        JButton listFiles = new JButton("List Files In Directory");

        listFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define the actions to be performed when the button is clicked
                System.out.println("Button clicked!");
                FileOrganizer fileOrganizer = new FileOrganizer();
                fileOrganizer.listFilesInDirectory(textField.getText());//Get the entered text
            }
        });
        frame.getContentPane().add(listFiles);

        // Set the size and close operation of the frame
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the frame visible
        frame.setVisible(true);
    }
}
