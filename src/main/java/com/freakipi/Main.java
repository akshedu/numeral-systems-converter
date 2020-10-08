package com.freakipi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Read the input
        Scanner scanner = new Scanner(System.in);
        String rawSourceRadix;
        String rawTargetRadix;
        String number;

        // Read source base
        if (scanner.hasNext()) {
            rawSourceRadix = scanner.next();
        } else {
            System.out.println("error: Missing required source base");
            return;
        }
        if (isNotValidInt(rawSourceRadix)) {
            System.out.println("error: Source base is not a valid integer");
            return;
        }
        int sourceRadix = Integer.parseInt(rawSourceRadix);
        if (isNotValidBase(sourceRadix)) {
            System.out.println("error: Source base is not a valid base");
            return;
        }

        // Read the number to be converted
        if (scanner.hasNext()) {
            number = scanner.next();
        } else {
            System.out.println("error: Missing required number");
            return;
        }

        // Read target radix
        if (scanner.hasNext()) {
            rawTargetRadix = scanner.next();
        } else {
            System.out.println("error: Missing required target base");
            return;
        }
        if (isNotValidInt(rawTargetRadix)) {
            System.out.println("error: Target base is not a valid integer");
            return;
        }
        int targetRadix = Integer.parseInt(rawTargetRadix);
        if (isNotValidBase(targetRadix)) {
            System.out.println("error: Target base is not a valid base");
            return;
        }

        // Create fraction flag
        boolean fraction = true;
        if (number.indexOf('.') == -1) {
            fraction = false;
        }

        // Handle pure integers
        if (!fraction) {
            int decimalNumber = convertIntToBase10(sourceRadix, number);
            String targetBaseNumber = convertFromBase10(decimalNumber, targetRadix);
            System.out.println(targetBaseNumber);
        } else {
            String[] numberSplit = number.split("\\.");
            String intPart = numberSplit[0];
            String fractionPart = numberSplit[1];
            int decimalIntPart = convertIntToBase10(sourceRadix, intPart);
            double decimalFractionPart = convertFractionToBase10(sourceRadix, fractionPart);
            System.out.println(convertFromBase10(decimalIntPart, targetRadix) + "." + convertFromBase10Fraction(decimalFractionPart, targetRadix));
        }
    }

    public static int convertIntToBase10(int sourceRadix, String number) {
        if (sourceRadix == 1) {
            return number.length();
        } else {
            return Integer.parseInt(number, sourceRadix);
        }
    }

    public static double convertFractionToBase10(int sourceRadix, String fractionPart) {
        double decimalValue = 0;
        char[] tokens = fractionPart.toCharArray();
        for (int i = 0; i < tokens.length; i++) {
            decimalValue += convertCharToInt(tokens[i]) * Math.pow(sourceRadix, -(i + 1));
        }
        return decimalValue;
    }

    public static String convertFromBase10(int decimalNumber, int targetRadix) {
        if (targetRadix == 1) {
            return "1".repeat(Math.max(0, decimalNumber));
        } else {
            return Integer.toString(decimalNumber, targetRadix);
        }
    }

    public static String convertFromBase10Fraction(double decimalFractionPart, int targetRadix) {
        StringBuilder targetFraction = new StringBuilder();
        double currentFraction = decimalFractionPart;
        for (int i = 0; i < 5; i++) {
            int fractionDigit = getFractionDigit(currentFraction, targetRadix);
            targetFraction.append(convertIntToChar(fractionDigit));
            currentFraction = (currentFraction * targetRadix) - fractionDigit;
        }
        return targetFraction.toString();
    }

    public static int convertCharToInt(char letter) {
        if (Character.isDigit(letter)) {
            return Character.getNumericValue(letter);
        } else {
            return (int) letter - 87;
        }
    }

    public static char convertIntToChar(int digit) {
        if (digit < 10) {
            return (char) (digit + 48);
        } else {
            return (char) (digit + 87);
        }
    }

    public static int getFractionDigit(double decimalFractionPart, int targetRadix) {
        return (int) (decimalFractionPart * targetRadix);
    }

    private static boolean isNotValidInt(String input) {
        return !input.matches("\\d+");
    }

    private static boolean isNotValidBase(int input) {
        return input < 1 || input > 36;
    }
}
