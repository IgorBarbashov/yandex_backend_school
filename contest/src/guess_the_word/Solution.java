package guess_the_word;

import java.io.*;

public class Solution {

    final static int ALPHABET_LENGTH = 26;

    final static int ALPHABET_START_CODE = 65;

    enum Coincidence {
        correct,
        present,
        absent
    }

    public static void main(String[] args) throws IOException {
        int[] alphabet = new int[ALPHABET_LENGTH];

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String S = r.readLine();
        String Q = r.readLine();

        int N = S.length();
        String[] result = new String[N];

        for (int i = 0; i < N; i++) {
            int charS = S.charAt(i);
            int charIndex = charS - ALPHABET_START_CODE;

            if (charS == Q.charAt(i)) {
                result[i] = Coincidence.correct.toString();
            } else {
                alphabet[charIndex]++;
            }
        }

        for (int i = 0; i < N; i++) {
            if (result[i] == null) {
                int charQ = Q.charAt(i);
                int charIndex = charQ - ALPHABET_START_CODE;

                if (alphabet[charIndex] != 0) {
                    result[i] = Coincidence.present.toString();
                    alphabet[charIndex]--;
                } else {
                    result[i] = Coincidence.absent.toString();
                }
            }
        }

        for (String s : result) {
            System.out.println(s);
        }
    }
}