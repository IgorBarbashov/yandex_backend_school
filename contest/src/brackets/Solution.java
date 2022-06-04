package brackets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("src/brackets/input.txt"))) {
            String str = in.nextLine();
            int p = -1;
            int count = 0;

            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') {
                    if (count == 0) {
                        p = i + 1;
                    }
                    count++;
                }

                if (str.charAt(i) == ')') {
                    count--;
                    if (count == 0) {
                        p = -1;
                    }
                }

                if (count == -2) {
                    p = -1;
                    break;
                }
            }

            System.out.println(Math.abs(count) == 1 ? p : -1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
