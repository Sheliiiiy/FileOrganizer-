package org.example;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileOrganizer {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the directory path: ");
        String directoryPath = scanner.nextLine();
        listFilesInDirectory(directoryPath);
        System.out.println(getCategorizedExtensions(directoryPath));
    }

    public void listFilesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);

        // Check if the directory exists
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            // Check if any files exist in the directory
            if (files != null && files.length > 0) {
                System.out.println("Files in the directory:");
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println(file.getName());
                    }
                }
            } else {
                System.out.println("The directory is empty.");
            }
        } else {
            System.out.println("The specified directory does not exist.");
        }
    }

    public HashMap<String, String> getCategorizedExtensions(String directoryPath) {
        HashMap<String, String> categorizedExtensions = new HashMap<>();

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String extension = getExtension(fileName);

                    if (!extension.isEmpty()) {
                        String category = getCategory(extension);
                        categorizedExtensions.put(extension, category);
                    }
                }
            }
        }
        return categorizedExtensions;
    }

    private String getExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }

    private String getCategory(String extension) {
        return switch (extension) {
            case "txt", "doc", "docx" -> "Documents";
            case "jpg", "png", "gif" -> "Images";
            case "mp3", "wav", "flac" -> "Audio";
            case "mp4", "avi", "mkv" -> "Videos";
            default -> "Others";
        };
    }
}
