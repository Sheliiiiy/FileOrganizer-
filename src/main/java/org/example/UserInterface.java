package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class UserInterface {
    public void displayMenu() {
        FileOrganizer fileOrganizer = new FileOrganizer();

        // Display the menu to the user and interact with FileOrganizer class
        JFrame frame = new JFrame("File Organizer");
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter Directory Path:");

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.45), 50));

        JTextArea textResult = new JTextArea();
        textResult.setEditable(false);

        // Create a JScrollPane and add the text area to it
        JScrollPane scrollPane = new JScrollPane(textResult);
        scrollPane.setPreferredSize(new Dimension(
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.45),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.3)));

        // Create a JButtons
        JButton getExtensions = new JButton("Categorized Extensions");
        getExtensions.addActionListener(e -> {
            // Define the actions to be performed when the button is clicked
            textResult.setText(textResult.getText() + "------------------------ Categorized Extensions Button clicked ------------------------\n");
            for (Map.Entry<String, String> entry : fileOrganizer.getCategorizedExtensions(textField.getText()).entrySet()) {
                textResult.setText(textResult.getText() + " " + entry.getKey() + " " + entry.getValue() + "\n");
            }
        });

        JButton listFiles = new JButton("List Files In Directory");
        listFiles.addActionListener(e -> {
            // Define the actions to be performed when the button is clicked
            textResult.setText(textResult.getText() + "------------------------ List Files In Directory Button clicked ------------------------\n");
            for (String str : fileOrganizer.listFilesInDirectory(textField.getText())) {
                textResult.setText(textResult.getText() + str + "\n");
            }
        });

        JButton createDirectory = new JButton("Create Directory");
        createDirectory.addActionListener(e -> {
            // Define the actions to be performed when the button is clicked
            textResult.setText(textResult.getText() + "------------------------ Create Directory Button clicked ------------------------\n");
            textResult.setText(textResult.getText() + fileOrganizer.createDirectory(textField.getText()) + "\n");
        });

        JButton moveFile = new JButton("Move File");
        moveFile.addActionListener(e -> {
            // Define the actions to be performed when the button is clicked
            textResult.setText(textResult.getText() + "------------------------ Move File Button clicked ------------------------\n");
            String[] parts = textField.getText().split(",");
            textResult.setText(textResult.getText() + fileOrganizer.moveFile(parts[0], parts[1]) + "\n");
        });

        //Add elements to the Frame
        frame.add(label);
        frame.add(textField);
        frame.add(getExtensions);
        frame.add(listFiles);
        frame.add(createDirectory);
        frame.add(moveFile);
        frame.add(scrollPane);

        // Set the size and close operation of the frame
        frame.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.5),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
