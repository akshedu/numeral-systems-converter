package com.freakipi;

import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
    public static void main(String[] args) {
        // write your code here
        String solved = "YES";
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int size = n * n;

        int[] ref = new int[size];
        for (int i = 0; i < size; i++) {
            ref[i] = i + 1;
        }

        int[][] rows = new int[size][size];
        int[][] columns = new int[size][size];
        int[][] squares = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int next = s.nextInt();
                rows[i][j] = next;
                columns[j][i] = next;
                int rSquare = n * (i / n) + (j / n);
                int cSquare = n * (i % n) + (j % n);
                squares[rSquare][cSquare] = next;
            }
        }

        for (int i = 0; i < size; i++) {
            if (!(isArrayCorrect(rows[i], ref) && isArrayCorrect(columns[i], ref) && isArrayCorrect(squares[i], ref))) {
                solved = "NO";
                break;
            }
        }
        System.out.print(solved);
    }

    public static boolean isArrayCorrect(int[] array, int[] ref) {
        Arrays.sort(array);
        return Arrays.equals(array, ref);
    }
}
