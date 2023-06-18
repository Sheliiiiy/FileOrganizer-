package org.example;
import javax.swing.*;
import java.awt.*;

public class UserInterface {
    public void displayMenu() {
        FileOrganizer fileOrganizer = new FileOrganizer();

        // Display the menu to the user and interact with FileOrganizer class
        JFrame frame = new JFrame("File Organizer");
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter Directory Path:");

        // Create a text box
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.45), 50));

        ScrollableTextArea.AutoExpandingTextArea textResult = new ScrollableTextArea.AutoExpandingTextArea();
        textResult.setColumns((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.03));
//        textResult.setPreferredSize(new Dimension(
//                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.45),
//                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.3)));
        textResult.setEditable(false);
        textResult.setLineWrap(true);
        textResult.setWrapStyleWord(true);

        // Create a scroll pane to contain the text area
        JScrollPane scrollPane = new JScrollPane(textResult);
//        scrollPane.setPreferredSize(new Dimension(
//                (int) (frame.getWidth() * 0.45),
//                (int) (frame.getHeight() * 0.3)));

        // Create a JButton
        JButton getExtensions = new JButton("Categorized Extensions");
        getExtensions.addActionListener(e -> {
            // Define the actions to be performed when the button is clicked
            textResult.setText(textResult.getText() + "\nCategorized Extensions Button clicked!");
            fileOrganizer.getCategorizedExtensions(textField.getText());
        });

        // Create a JButton
        JButton listFiles = new JButton("List Files In Directory");

        listFiles.addActionListener(e -> {
            // Define the actions to be performed when the button is clicked
            textResult.setText(textResult.getText() + "\nList Files In Directory Button clicked!");
            fileOrganizer.listFilesInDirectory(textField.getText());//Get the entered text
        });

        //Add elements to the Frame
        frame.add(label);
        frame.add(textField);
        frame.add(getExtensions);
        frame.add(listFiles);
        //frame.add(textResult);
        frame.add(scrollPane);

//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(5, 5, 5, 5);
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        frame.add(label, gbc);
//
//        gbc.gridy = 1;
//        gbc.weightx = 0.0;
//        gbc.weighty = 0.0;
//        frame.add(textField, gbc);
//
//        gbc.gridy = 2;
//        gbc.weightx = 0.0;
//        gbc.weighty = 0.0;
//        frame.add(getExtensions, gbc);
//
//        gbc.gridy = 3;
//        gbc.weightx = 0.0;
//        gbc.weighty = 0.0;
//        frame.add(listFiles, gbc);
//
//        gbc.gridy = 4;
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.weightx = 1.0;
//        gbc.weighty = 1.0;
//        frame.add(scrollPane, gbc);


        // Set the size and close operation of the frame
        frame.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.5),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the frame visible
        frame.setVisible(true);
    }
}
