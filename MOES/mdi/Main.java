package mdi;

import customer.Student;
import java.io.*;
import java.util.Scanner;
import moes.Moes;
import product.Media;

/**
 * Main class to run the Moes system via a menu-driven interface.
 * 
 * @version 1.2
 * @since 3.0
 */
public class Main {
    private Moes moes;
    private Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    private static final String FILE_EXTENSION = ".moes";
    private String currentFilename;
    private static final String MAGIC_COOKIE = "MOES_MAGIC_COOKIE";
    private static final String FILE_VERSION = "1.0";

    public static void main(String[] args) {
        Main main = new Main(args);
        main.run();
    }

    public Main(String[] args) {
        if (args.length > 0) {
            currentFilename = args[0];
            openFileFromCommandLine(currentFilename);
        } else {
            moes = new Moes(); // Start with an empty Moes instance if no command-line argument is given
        }
    }

    public void run() {
        while (running) {
            displayMenu();
            int option = getIntInput("Choose an option: ");
            processMenuOption(option);
        }
    }

    private void displayMenu() {
        System.out.println("0] Add Student");
        System.out.println("1] List Students");
        System.out.println("2] Add Media");
        System.out.println("3] List Media");
        System.out.println("4] Play Media");
        System.out.println("5] Buy Points");
        System.out.println("6] Exit");
    }

    private void processMenuOption(int option) {
        switch (option) {
            case 0:
                addStudent();
                break;
            case 1:
                listStudents();
                break;
            case 2:
                addMedia();
                break;
            case 3:
                listMedia();
                break;
            case 4:
                playMedia();
                break;
            case 5:
                buyPoints();
                break;
            case 6:
                running = false;
                System.out.println("Exiting the program.");
                break;
            default:
                System.out.println("Invalid option. Try again.");
        }
    }

    private void autoSave() {
        if (currentFilename == null) {
            System.out.println("No file loaded. Please provide a filename as a command-line argument.");
            return;
        }

        File file = new File(currentFilename);

        // Auto-backup with a tilde (~) if the file exists
        if (file.exists()) {
            File backupFile = new File(currentFilename + "~");
            if (backupFile.exists()) {
                backupFile.delete(); // Delete the previous backup if it exists
            }
            if (!file.renameTo(backupFile)) {
                System.out.println("Failed to create a backup file.");
                return; // Stop the save process if backup creation fails
            }
            System.out.println("Backup created: " + backupFile.getName());
        }

        // Save the current data to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(currentFilename))) {
            bw.write(MAGIC_COOKIE + "\n");
            bw.write(FILE_VERSION + "\n");
            moes.save(bw);
            System.out.println("Auto-save successful.");
        } catch (IOException e) {
            System.out.println("Error during auto-save: " + e.getMessage());
        }
    }

    private void openFileFromCommandLine(String filename) {
        if (!filename.endsWith(FILE_EXTENSION)) {
            filename += FILE_EXTENSION;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String magicCookie = br.readLine();
            String fileVersion = br.readLine();

            if (!MAGIC_COOKIE.equals(magicCookie) || !FILE_VERSION.equals(fileVersion)) {
                throw new IOException("Invalid file format or version.");
            }

            moes = new Moes(br); // Load the MOES instance from the file
            currentFilename = filename;
            System.out.println("File loaded successfully from: " + currentFilename);
        } catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
            moes = new Moes(); // If file fails to load, start with an empty Moes instance
        }
    }

    private void addStudent() {
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();

        int id = getIntInput("Enter student's ID (numeric): ");

        System.out.print("Enter student's email: ");
        String email = scanner.nextLine();

        System.out.print("Is this an unlimited or alacarte account? ");
        String accountType = scanner.nextLine();

        try {
            moes.addStudent(new Student(name, id, email, accountType));
            autoSave();  // Automatically save after adding a student
            System.out.println("Student added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    private void listStudents() {
        System.out.println(moes.getStudentList());
    }

    private void addMedia() {
        String title = getStringInput("Enter media title: ");
        String url = getStringInput("Enter media URL: ");
        int points = getIntInput("Enter points for media: ");
        moes.addMedia(new Media(title, url, points));
        autoSave();  // Automatically save after adding media
        System.out.println("Media added successfully.");
    }

    private void listMedia() {
        System.out.println(moes.getMediaList());
    }

    private void playMedia() {
        int studentIndex = getIntInput("Enter student index: ");
        int mediaIndex = getIntInput("Enter media index: ");
        System.out.println(moes.playMedia(studentIndex, mediaIndex));
        autoSave();  // Automatically save after playing media
    }

    private void buyPoints() {
        int studentIndex = getIntInput("Enter student index: ");
        int points = getIntInput("Enter number of points to buy: ");
        System.out.println(moes.buyPoints(studentIndex, points));
        autoSave();  // Automatically save after buying points
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a numeric value.");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        return value;
    }
}
