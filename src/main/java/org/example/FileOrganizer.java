package org.example;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class FileOrganizer {
    public void run() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the directory path: ");
//        String directoryPath = scanner.nextLine();
//        listFilesInDirectory(directoryPath);
//        System.out.println(getCategorizedExtensions(directoryPath));
//        createDirectory(directoryPath);
    }

    public String moveFile(String sourceFilePath, String destinationFilePath) {
        File sourceFile = new File(sourceFilePath);
        File destinationFile = new File(destinationFilePath);

        try {
            Path sourcePath = sourceFile.toPath();
            Path destinationPath = destinationFile.toPath();
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            return "File moved successfully from " + sourceFilePath + " to " + destinationFilePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to move file from " + sourceFilePath + " to " + destinationFilePath;
        }
    }

    public String createDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        boolean created = directory.mkdir();

        if (created) {
            return "Directory created: " + directory.getAbsolutePath();
        } else {
            return "Failed to create directory: " + directory.getAbsolutePath();
        }
    }

    public List<String> listFilesInDirectory(String directoryPath) {
        List<String> fileNamesList = new ArrayList<>();
        File directory = new File(directoryPath);

        // Check if the directory exists
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            // Check if any files exist in the directory
            if (files != null && files.length > 0) {
                fileNamesList.add("Files in the directory:");
                for (File file : files) {
                    if (file.isFile()) {
                        fileNamesList.add(file.getName());
                    }
                }
            } else {
                fileNamesList.add("The directory is empty.");
            }
        } else {
            fileNamesList.add("The specified directory does not exist.");
        }
        return fileNamesList;
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
