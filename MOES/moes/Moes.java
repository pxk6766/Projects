package moes;

import customer.Alacarte;
import customer.Student;
import customer.Unlimited;
import java.io.*;
import java.util.ArrayList;
import product.Media;

/**
 * Manages media and student accounts in MOES.
 * Includes file I/O methods for saving and loading data.
 * 
 * @since 2.0
 * @version 1.2
 */
public class Moes {
    private ArrayList<Media> mediaLibrary;
    private ArrayList<Student> studentAccounts;

    /**
     * Constructs the MOES system with empty media and student lists.
     */
    public Moes() {
        mediaLibrary = new ArrayList<>();
        studentAccounts = new ArrayList<>();
    }

    /**
     * Constructs the MOES system by loading media and students from a file.
     * 
     * @param br BufferedReader object to load data from.
     * @throws IOException if an I/O error occurs.
     */
    public Moes(BufferedReader br) throws IOException {
        int mediaCount = Integer.parseInt(br.readLine());
        mediaLibrary = new ArrayList<>();
        for (int i = 0; i < mediaCount; i++) {
            mediaLibrary.add(new Media(br)); // Load media items from the file
        }

        int studentCount = Integer.parseInt(br.readLine());
        studentAccounts = new ArrayList<>();
        for (int i = 0; i < studentCount; i++) {
            studentAccounts.add(new Student(br)); // Load student accounts from the file
        }
    }

    /**
     * Saves the MOES system's data to a file using a BufferedWriter.
     * 
     * @param bw BufferedWriter object to save data to.
     * @throws IOException if an I/O error occurs.
     */
    public void save(BufferedWriter bw) throws IOException {
        bw.write(mediaLibrary.size() + "\n"); // Save the number of media items
        for (Media media : mediaLibrary) {
            media.save(bw); // Save each media item
        }

        bw.write(studentAccounts.size() + "\n"); // Save the number of students
        for (Student student : studentAccounts) {
            student.save(bw); // Save each student account
        }
    }

    /**
     * Adds a new media item to the media library.
     * 
     * @param media Media item to be added.
     */
    public void addMedia(Media media) {
        mediaLibrary.add(media);
    }

    /**
     * Returns a formatted list of media for display.
     * 
     * @return A formatted string representing the media items.
     */
    public String getMediaList() {
        StringBuilder mediaList = new StringBuilder();
        for (int i = 0; i < mediaLibrary.size(); i++) {
            mediaList.append(i).append(") ").append(mediaLibrary.get(i).toString()).append("\n");
        }
        return mediaList.toString();
    }

    /**
     * Adds a new student account to the system.
     * 
     * @param student Student account to be added.
     */
    public void addStudent(Student student) {
        studentAccounts.add(student);
    }

    /**
     * Returns a formatted list of students for display.
     * 
     * @return A formatted string representing the students.
     */
    public String getStudentList() {
        StringBuilder studentList = new StringBuilder();
        for (int i = 0; i < studentAccounts.size(); i++) {
            studentList.append(i).append(") ").append(studentAccounts.get(i).toString()).append("\n");
        }
        return studentList.toString();
    }

    /**
     * Retrieves the available points for a given student.
     * 
     * @param studentIndex The index of the student in the list.
     * @return The remaining points for the student.
     */
    public int getPoints(int studentIndex) {
        Student student = studentAccounts.get(studentIndex);
        if (student.getAccount() instanceof Alacarte) {
            return ((Alacarte) student.getAccount()).getPointsRemaining();
        } else if (student.getAccount() instanceof Unlimited) {
            return Integer.MAX_VALUE;
        } else {
            throw new UnsupportedOperationException("Unknown account type");
        }
    }

    /**
     * Buys points for a student.
     * 
     * @param studentIndex Index of the student.
     * @param points Number of points to buy.
     * @return A message indicating the points purchased.
     */
    public String buyPoints(int studentIndex, int points) {
        Student student = studentAccounts.get(studentIndex);
        if (student.getAccount() instanceof Alacarte) {
            ((Alacarte) student.getAccount()).buyPoints(points);
            return student.toString() + " now has " + ((Alacarte) student.getAccount()).getPointsRemaining() + " points.";
        } else if (student.getAccount() instanceof Unlimited) {
            return student.toString() + " has an unlimited account!";
        } else {
            throw new UnsupportedOperationException("Unknown account type");
        }
    }

    /**
     * Plays a media item for a student.
     * 
     * @param studentIndex Index of the student.
     * @param mediaIndex Index of the media item.
     * @return A message indicating the result of the play action.
     */
    public String playMedia(int studentIndex, int mediaIndex) {
        Media media = mediaLibrary.get(mediaIndex);
        return studentAccounts.get(studentIndex).requestMedia(media);
    }
}
