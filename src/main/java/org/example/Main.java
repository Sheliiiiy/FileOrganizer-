package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // Initialize the program and start execution
        SwingUtilities.invokeLater(() -> {
            FileOrganizer fileOrganizer = new FileOrganizer();
            fileOrganizer.run();
        });
    }
}