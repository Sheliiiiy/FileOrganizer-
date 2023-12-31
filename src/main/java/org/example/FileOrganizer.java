package org.example;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class FileOrganizer {
    public void run() {
        UserInterface userInterface = new UserInterface();
        userInterface.displayMenu();
    }

    public String renameFile(String filePath, String newFileName) {
        File file = new File(filePath);
        return file.exists() ?
                (file.renameTo(new File(file.getParent(), newFileName)) ?
                        "File renamed successfully." : "Unable to rename the file.") :
                "File does not exist.";
    }

    public String copyFile(String sourceFilePath, String destinationFilePath) {
        Path sourcePath = Path.of(sourceFilePath);
        Path destinationPath = Path.of(destinationFilePath);

        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.COPY_ATTRIBUTES);
            return "File copied successfully.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Unable to copy the file.";
        }
    }

    public String getFileDetails(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            BasicFileAttributes attributes;
            try {
                attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                String details = "File Details:\n";
                details += "Name: " + file.getName() + "\n";
                details += "Path: " + file.getAbsolutePath() + "\n";
                details += "Size: " + attributes.size() + " bytes\n";
                details += "Creation Time: " + attributes.creationTime() + "\n";
                details += "Last Modified Time: " + attributes.lastModifiedTime() + "\n";
                return details;
            } catch (IOException e) {
                e.printStackTrace();
                return "Unable to retrieve file details.";
            }
        } else {
            return "File does not exist.";
        }
    }

    public List<String> searchFiles(String directoryPath, String fileName) {
        List<String> filePaths = new ArrayList<>();
        searchFilesRecursive(new File(directoryPath), fileName, filePaths);
        return filePaths.isEmpty()? new ArrayList<>(Collections.singletonList("Not Found")) : filePaths;
    }

    private void searchFilesRecursive(File directory, String fileName, List<String> filePaths) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchFilesRecursive(file, fileName, filePaths); // Recursively search subdirectories
                } else if (file.getName().equals(fileName)) {
                    filePaths.add(file.getAbsolutePath()); // Found a matching file
                }
            }
        }
    }

    public String deleteFile(String filePath) {
        File file = new File(filePath);
        return file.exists() ?
                (file.delete() ?
                        "File deleted successfully." : "Unable to delete the file.") :
                "File does not exist.";
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
