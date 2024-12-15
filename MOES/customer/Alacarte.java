package customer;

import java.io.*;
import product.Media;

/**
 * Alacarte account charges for each media access, with a points system.
 * Includes file I/O methods for saving and loading account information.
 * 
 * @version 1.2
 * @since 1.0
 */
public class Alacarte extends Account {
    private int remainingPoints;

    /**
     * Constructs an Alacarte account with zero initial points.
     */
    public Alacarte() {
        super(); // Calls the constructor of the Account class
        this.remainingPoints = 0;
    }

    /**
     * Constructs an Alacarte account from a BufferedReader.
     * Reads the account details from the file and calls the superclass constructor.
     * 
     * @param br BufferedReader object to read account data from.
     * @throws IOException if an I/O error occurs.
     */
    public Alacarte(BufferedReader br) throws IOException {
        super(br); // Calls the Account constructor to read accountNumber and nextAccountNumber
        this.remainingPoints = Integer.parseInt(br.readLine()); // Reads the remainingPoints from the file
    }

    /**
     * Saves the Alacarte account to a file using a BufferedWriter.
     * Calls the superclass save method to write account details.
     * 
     * @param bw BufferedWriter object to write account data to.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void save(BufferedWriter bw) throws IOException {
        super.save(bw); // Chaining to superclass's save method
        bw.write(Integer.toString(remainingPoints) + "\n"); // Writing Alacarte-specific field
    }

    /**
     * Adds the specified number of points to the account.
     * 
     * @param points The number of points to add.
     * @throws IllegalArgumentException if points are negative.
     */
    public void buyPoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points must be non-negative.");
        }
        remainingPoints += points;
    }

    /**
     * Retrieves the number of remaining points in the account.
     * 
     * @return The current points balance.
     */
    public int getPointsRemaining() {
        return remainingPoints;
    }

    /**
     * Plays the media and deducts points if enough are available.
     * 
     * @param media The media object to be played.
     * @return A message indicating whether the media was played or if more points are needed.
     */
    @Override
    public String play(Media media) {
        int mediaPoints = media.getPoints();
        if (remainingPoints >= mediaPoints) {
            remainingPoints -= mediaPoints;
            return "Playing " + media.toString();
        } else {
            return "Insufficient points: Requires " + mediaPoints + " points, but you only have " + remainingPoints;
        }
    }

    @Override
    public String toString() {
        return "Alacarte Account, Account Number: " + getAccountNumber() + ", Remaining Points: " + remainingPoints;
    }
}
