package test;

import customer.Account;
import customer.Alacarte;
import customer.Unlimited;

/**
 * Regression test for the Account class. 
 * Tests for correctness of account numbers and proper subclass behavior.
 * 
 * @since 0.2
 */
public class TestAccount {
    public static void main(String[] args) {
        int errorFlag = 0;  // Flag to track any errors

        Account[] accountList = { new Unlimited(), new Alacarte() };

        for (int idx = 0; idx < accountList.length; idx++) {
            int expectedNumber = idx + 1;  // Expected account number
            int actualNumber = accountList[idx].getAccountNumber();
            if (actualNumber != expectedNumber) {
                System.err.println("ERROR: Expected account number " + expectedNumber + ", but got: " + actualNumber);
                errorFlag = 1;
            }
        }

        if (errorFlag != 0) {
            System.err.println("Test failed with error code " + errorFlag);
        } else {
            System.out.println("All tests passed successfully.");
        }

        System.exit(errorFlag);
    }
}