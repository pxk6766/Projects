package customer;

import java.io.*;
import product.Media;

/**
 * Represents a user account. Each account is assigned a unique account number.
 * Includes file I/O methods for saving and loading account information.
 * 
 * @version 1.2
 * @since 1.0
 */
public abstract class Account {
    private int accountNumber;
    private static int nextAccountNumber = 1;

    /**
     * Constructor to initialize an account with a unique account number.
     */
    public Account() {
        accountNumber = generateNextAccountNumber();
    }

    /**
     * Constructor to reconstruct an account from a BufferedReader.
     * Reads the account number and static fields from the file.
     * 
     * @param br BufferedReader object to read the account data from a file.
     * @throws IOException if an I/O error occurs.
     */
    public Account(BufferedReader br) throws IOException {
        this.accountNumber = Integer.parseInt(br.readLine()); // Read account number
        nextAccountNumber = Integer.parseInt(br.readLine()); // Read static nextAccountNumber
    }

    /**
     * Generates and returns the next account number in sequence.
     * 
     * @return A unique account number.
     */
    private static int generateNextAccountNumber() {
        return nextAccountNumber++;
    }

    /**
     * Retrieves the account number for this account.
     * 
     * @return The account number associated with this account.
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Saves the account details to a file using BufferedWriter.
     * Writes the account number and the static nextAccountNumber to the file.
     * 
     * @param bw BufferedWriter object to write account data to the file.
     * @throws IOException if an I/O error occurs.
     */
    public void save(BufferedWriter bw) throws IOException {
        bw.write(Integer.toString(accountNumber) + "\n"); // Write account number
        bw.write(Integer.toString(nextAccountNumber) + "\n"); // Write static nextAccountNumber
    }

    /**
     * Abstract method to handle media playback, to be implemented by subclasses.
     * 
     * @param media The media to be played.
     * @return A message indicating the playback result.
     */
    public abstract String play(Media media);
}
