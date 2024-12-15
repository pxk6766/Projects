package test;

import product.Media;

/**
 * Regression test for the Media class.
 * 
 * @since 0.2
 */
public class TestMedia {

    public static void main(String[] args) {
        int errorFlag = 0;  // Tracks any test failures
        int vector = 1;  // For bitwise operation, each test increments it

        // Test media string representation
        String title = "The Little Shop of Horrors";
        String url = "https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0";
        int points = 10;
        Media media = new Media(title, url, points);

        String expectedOutput = String.format("%s (%s, %d points)", title, url, points);
        String actualOutput = media.toString();

        if (!actualOutput.equals(expectedOutput)) {
            System.err.println("FAIL: Expected media output: " + expectedOutput + '\n'
                               + "      Actual output: " + actualOutput);
            errorFlag |= vector;
        }

        // Shift the vector for the next test
        vector <<= 1;

        // Test valid URLs
        String[] validUrls = {"https://youtube.com", "file://media/lib/movie.mp4"};
        for (String validUrl : validUrls) {
            try {
                new Media("Valid Media", validUrl, points);
            } catch (Exception e) {
                System.err.println("FAIL: Unexpected exception for valid URL: " + validUrl);
                System.err.println(e);
                errorFlag |= vector;
            }
        }

        // Shift the vector for the next test
        vector <<= 1;

        // Test invalid URLs
        String[] invalidUrls = {"htt://badurl.com", "flub://badurl.com", "hello.world"};
        for (String invalidUrl : invalidUrls) {
            try {
                new Media("Invalid Media", invalidUrl, points);
                System.err.println("FAIL: Expected exception for invalid URL: " + invalidUrl);
                errorFlag |= vector;
            } catch (IllegalArgumentException e) {
                // Expected exception, so no error
            } catch (Exception e) {
                System.err.println("FAIL: Unexpected exception type for URL: " + invalidUrl);
                System.err.println(e);
                errorFlag |= vector;
            }
        }

        // Print result summary
        if (errorFlag != 0) {
            System.err.println("\nFAIL: Error code " + errorFlag);
        } else {
            System.out.println("PASS: All tests passed.");
        }

        System.exit(errorFlag);
    }
}