package test;

import customer.Alacarte;
import customer.Student;
import product.Media;

/**
 * Regression test for the Student class.
 * 
 * @since 0.2
 */
public class TestStudent {

    public static void main(String[] args) {
        int result = 0;
        int bitMask = 1;

        try {
            Student unlimitedStudent = new Student("Prof Rice", "1001234567", "george.rice@mavs.uta.edu", "unlimited");
            Media media = new Media("The Little Shop of Horrors", "https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0", 5);

            String expectedMessage = "Playing The Little Shop of Horrors (https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0, 5 points)";
            String actualMessage = unlimitedStudent.requestMedia(media);

            if (!expectedMessage.equals(actualMessage)) {
                System.err.println("FAIL: Expected: " + expectedMessage + " but got: " + actualMessage);
                result |= bitMask;
            }
        } catch (Exception e) {
            System.err.println("FAIL: Unexpected exception: " + e.getMessage());
            result |= bitMask;
        }

        bitMask <<= 1;

        try {
            Student alacarteStudent = new Student("Student A", "1234567890", "student.a@uta.edu", "alacarte");
            Alacarte account = (Alacarte) alacarteStudent.getAccount();
            account.buyPoints(10);

            Media media = new Media("The Little Shop of Horrors", "https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0", 5);
            String expectedMessage = "Playing The Little Shop of Horrors (https://publicdomainmovie.net/movie/the-little-shop-of-horrors-0, 5 points)";
            String actualMessage = alacarteStudent.requestMedia(media);

            if (!expectedMessage.equals(actualMessage)) {
                System.err.println("FAIL: Expected: " + expectedMessage + " but got: " + actualMessage);
                result |= bitMask;
            }
        } catch (Exception e) {
            System.err.println("FAIL: Unexpected exception: " + e.getMessage());
            result |= bitMask;
        }

        bitMask <<= 1;

        String badEmail = "george.rice@example.com";
        try {
            new Student("Prof Rice", "1234567890", badEmail, "unlimited");
            System.err.println("FAIL: Expected IllegalArgumentException for " + badEmail);
            result |= bitMask;
        } catch (IllegalArgumentException e) {
            String expectedMessage = "Non-UTA email address: " + badEmail;
            if (!e.getMessage().equals(expectedMessage)) {
                System.err.println("FAIL: Expected message: " + expectedMessage + " but got: " + e.getMessage());
                result |= bitMask;
            }
        } catch (Exception e) {
            System.err.println("FAIL: Unexpected exception: " + e.getMessage());
            result |= bitMask;
        }

        if (result != 0) {
            System.err.println("\nFAIL: Error code " + result);
        } else {
            System.out.println("PASS: All tests passed successfully.");
        }

        System.exit(result);
    }
}