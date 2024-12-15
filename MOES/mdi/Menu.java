/*
Menu Manager Copyright (c) 2024 Professsor George F. Rice

Licensed under The MIT License (MIT)

Summary: Basically, you can do whatever you want as long as you include 
this copyright and license notice in any copy of the software/source.

Permission is hereby granted, free of charge, to any person obtaining a copy 
of this software and associated documentation files (the "Software"), to deal 
in the Software without restriction, including without limitation the rights 
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies 
of the Software, and to permit persons to whom the Software is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included 
in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION 
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package mdi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This class manages the entire menu and dispatch table
class Menu {
    private List<MenuItem> items = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);  // Static scanner to be used for input

    public void addMenuItem(MenuItem item) {
        items.add(item);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++)
            sb.append(" " + i + "] " + items.get(i) + "\n");
        return sb.toString();
    }

    public void run(int itemNumber) {
        items.get(itemNumber).run();
    }

    // Static method to get string input from the user
    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Static method to get integer input from the user with error handling
    public static int getInt(String prompt) {
        int number = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                number = Integer.parseInt(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return number;
    }
}