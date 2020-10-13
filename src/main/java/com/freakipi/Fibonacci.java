package com.freakipi;


import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.next();
        int n;

        while(!isPositiveInteger(input)) {
            System.out.println("Please enter a positive integer");
            input = s.next();
        }

        n = Integer.parseInt(input);
        System.out.println(String.format("The nth fibonacci for %d is = ", n) + getFibonacci(n));
    }

    public static boolean isPositiveInteger(String input) {
        if ("0".equals(input)) {
            return false;
        }
        for (char c : input.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static int getFibonacci(int n) {
        int[] fib = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0 || i == 1) {
                fib[i] = 1;
            } else {
                fib[i] = fib[i - 1] + fib[i - 2];
            }
        }
        return fib[n - 1];
    }
}
