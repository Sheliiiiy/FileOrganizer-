package org.example;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class UserInterface {
    public void displayMenu() {
        FileOrganizer fileOrganizer = new FileOrganizer();

        // Display the menu to the user and interact with FileOrganizer class
        JFrame frame = new JFrame("File Organizer");
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter Directory Path:");

        // Create a text box
        JTextField textField = new JTextField();
        textField.setColumns(20);

        // Create a JButton
        JButton getExtensions = new JButton("Categorized Extensions");
        getExtensions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define the actions to be performed when the button is clicked
                System.out.println("Button clicked!");
                fileOrganizer.getCategorizedExtensions(textField.getText());
            }
        });

        // Create a JButton
        JButton listFiles = new JButton("List Files In Directory");

        listFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define the actions to be performed when the button is clicked
                System.out.println("Button clicked!");
                fileOrganizer.listFilesInDirectory(textField.getText());//Get the entered text
            }
        });

        //Add elements to the Frame
        frame.add(label);
        frame.add(textField);
        frame.add(getExtensions);
        frame.add(listFiles);

        // Set the size and close operation of the frame
        frame.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.5),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the frame visible
        frame.setVisible(true);
    }
}
