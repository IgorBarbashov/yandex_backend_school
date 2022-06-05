package brackets;

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/brackets/input.txt"))) {
            int c;
            int i = 0;
            int oP = -1;
            int cP = -1;
            int count = 0;

            while ((c = br.read()) != -1) {
                i++;

                if (c == '(') {
                    if (count == 0) {
                        oP = i;
                    }
                    count++;
                }

                if (c == ')') {
                    count--;

                    if (count == 0 && oP < cP) {
                        oP = -1;
                        cP = -1;
                    } else if (count == -1 && cP == -1) {
                        cP = i;
                    }
                }
            }

            System.out.println(Math.abs(count) == 1 ? (oP > 0 ? oP : cP > 0 ? cP : -1) : -1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
