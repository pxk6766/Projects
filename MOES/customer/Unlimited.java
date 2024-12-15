package customer;

import java.io.*;
import product.Media;

/**
 * Provides unlimited media access for this type of account.
 * Includes file I/O methods for saving and loading account information.
 * 
 * @version 1.2
 * @since 1.0
 */
public class Unlimited extends Account {

    /**
     * Default constructor for Unlimited account.
     */
    public Unlimited() {
        super(); // Calls the constructor of the Account class
    }

    /**
     * Constructor to reconstruct an Unlimited account from a BufferedReader.
     * Reads the account details from the file and calls the superclass constructor.
     * 
     * @param br BufferedReader object to read account data from.
     * @throws IOException if an I/O error occurs.
     */
    public Unlimited(BufferedReader br) throws IOException {
        super(br); // Calls the Account constructor to read accountNumber and nextAccountNumber
    }

    /**
     * Saves the Unlimited account to a file using a BufferedWriter.
     * Calls the superclass save method to write account details.
     * 
     * @param bw BufferedWriter object to write account data to.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw); // Calls the Account class's save method to write accountNumber and nextAccountNumber
        // No additional fields to save for Unlimited
    }

    /**
     * Plays the specified media without any point deduction.
     * 
     * @param media The media to be played.
     * @return A string indicating that the media is being played.
     * @since 1.0
     */
    @Override
    public String play(Media media) {
        return "Playing " + media;
    }

    @Override
    public String toString() {
        return "Unlimited Account, Account Number: " + getAccountNumber();
    }
}
