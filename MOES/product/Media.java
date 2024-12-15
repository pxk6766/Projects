package product;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Represents a media item with a title, URL, and points system for accessing it.
 * Includes file I/O methods for saving and loading media data.
 * 
 * @since 2.0
 */
public class Media {
    private String title;
    private String url;
    private int points;

    /**
     * Constructs a Media object by validating the URL and setting points required to access it.
     * 
     * @param title The title of the media content.
     * @param url The media's URL.
     * @param points The points needed for access.
     * @throws IllegalArgumentException if the URL is invalid.
     */
    public Media(String title, String url, int points) {
        validateURL(url);
        this.title = title;
        this.url = url;
        this.points = points;
    }

    /**
     * Constructs a Media object from a BufferedReader by reading the title, URL, and points from a file.
     * 
     * @param br The BufferedReader to read media data from.
     * @throws IOException If an I/O error occurs during reading.
     */
    public Media(BufferedReader br) throws IOException {
        this.title = br.readLine();
        this.url = br.readLine();
        this.points = Integer.parseInt(br.readLine());
    }

    /**
     * Saves the Media object to a file using a BufferedWriter.
     * Writes the title, URL, and points to the file.
     * 
     * @param bw The BufferedWriter to write media data to.
     * @throws IOException If an I/O error occurs during writing.
     */
    public void save(BufferedWriter bw) throws IOException {
        bw.write(title + "\n");
        bw.write(url + "\n");
        bw.write(Integer.toString(points) + "\n");
    }

    /**
     * Helper method to validate the given URL format.
     * 
     * @param url The URL string to validate.
     * @throws IllegalArgumentException if the URL is not valid.
     */
    private void validateURL(String url) {
        try {
            new URL(url); // Attempts to validate URL format.
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid URL: " + url);
        }
    }

    /**
     * Retrieves the points required for this media.
     * 
     * @return The points needed to access the media.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns a string representation of the media item, including title, URL, and points.
     * 
     * @return The media description formatted as a string.
     */
    @Override
    public String toString() {
        return String.format("%s (%s, %d points)", title, url, points);
    }
}
