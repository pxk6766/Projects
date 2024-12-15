package customer;

import java.io.*;
import product.Media;

/**
 * Represents a student with an account.
 * 
 * @since 2.0
 */
public class Student {
    private String name;
    private String id;
    private String email;
    private Account account;

    /**
     * Constructs a Student with the specified name, id, email, and account type.
     * 
     * @param name The student's name.
     * @param id The student's ID as a string.
     * @param email The student's email address, which must end with '@uta.edu' or '@mavs.uta.edu'.
     * @param accountType The type of account ("unlimited" or "alacarte").
     * @throws IllegalArgumentException if the email is not a UTA email or accountType is invalid.
     * @since 0.2
     */
    public Student(String name, String id, String email, String accountType) {
        validateEmail(email);
        this.name = name;
        this.id = id;
        this.email = email;
        this.account = initializeAccount(accountType);
    }

    /**
     * Constructs a Student with the specified name, integer id, email, and account type.
     * Converts the integer id to a String.
     * 
     * @param name The student's name.
     * @param id The student's ID as an integer.
     * @param email The student's email address, which must end with '@uta.edu' or '@mavs.uta.edu'.
     * @param accountType The type of account ("unlimited" or "alacarte").
     * @throws IllegalArgumentException if the email is not a UTA email or accountType is invalid.
     * @since 0.2
     */
    public Student(String name, int id, String email, String accountType) {
        this(name, String.valueOf(id), email, accountType);
    }

    /**
     * Constructor that reconstructs a Student from a BufferedReader.
     * 
     * @param br BufferedReader object to read student data from.
     * @throws IOException if an I/O error occurs.
     */
    public Student(BufferedReader br) throws IOException {
        this.name = br.readLine();  // Reads student's name
        this.id = br.readLine();    // Reads student's id
        this.email = br.readLine(); // Reads student's email
        
        // Read the account type (Unlimited or Alacarte)
        String accountType = br.readLine();
        
        // Use a switch (or if/else) to choose the correct account type
        switch (accountType) {
            case "Alacarte":
                this.account = new Alacarte(br);  // Reconstructs Alacarte account
                break;
            case "Unlimited":
                this.account = new Unlimited(br); // Reconstructs Unlimited account
                break;
            default:
                throw new IOException("Unknown account type: " + accountType);
        }
    }

    /**
     * Saves the Student to a file using BufferedWriter.
     * 
     * @param bw BufferedWriter object to write student data to.
     * @throws IOException if an I/O error occurs.
     */
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + "\n");  // Save student's name
        bw.write(id + "\n");    // Save student's id
        bw.write(email + "\n"); // Save student's email
        
        // Write the account type to the file (e.g., Alacarte or Unlimited)
        bw.write(account.getClass().getSimpleName() + "\n");
        
        // Save the account data using the account's save method
        account.save(bw);
    }

    /**
     * Validates the student's email.
     * 
     * @param email The student's email address.
     * @throws IllegalArgumentException if the email does not end with '@uta.edu' or '@mavs.uta.edu'.
     */
    private void validateEmail(String email) {
        if (!email.endsWith("@uta.edu") && !email.endsWith("@mavs.uta.edu")) {
            throw new IllegalArgumentException("Non-UTA email address: " + email);
        }
    }

    /**
     * Initializes the student's account based on the specified account type.
     * 
     * @param accountType The type of account ("unlimited" or "alacarte").
     * @return The initialized Account object.
     * @throws IllegalArgumentException if the account type is unknown.
     */
    private Account initializeAccount(String accountType) {
        if (accountType.equalsIgnoreCase("unlimited")) {
            return new Unlimited();
        } else if (accountType.equalsIgnoreCase("alacarte")) {
            return new Alacarte();
        } else {
            throw new IllegalArgumentException("Unknown account type: " + accountType);
        }
    }

    /**
     * Requests to play the specified media using the student's account.
     * 
     * @param media The media to be played.
     * @return The result of the play action.
     * @since 0.2
     */
    public String requestMedia(Media media) {
        return account.play(media);
    }

    /**
     * Gets the student's account.
     * 
     * @return The student's account.
     * @since 0.2
     */
    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return name + " (" + id + ", " + email + ", Account #" + account.getAccountNumber() + ")";
    }
}
