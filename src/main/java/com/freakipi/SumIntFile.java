package com.freakipi;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class SumIntFile {
    public static void main(String[] args) {

        long delta = 0;
        int deltaYear = 1900;
        int year;
        long population;
        long prevPopulation;

        try (Scanner scanner = new Scanner(new File("/Users/akshansh/Downloads/dataset_91069.txt"))) {
            scanner.nextLine();
            String[] row = scanner.nextLine().split("\\s+");
            prevPopulation = parsePopulation(row[1]);
            while(scanner.hasNext()) {
                row = scanner.nextLine().split("\\s+");
                year = Integer.parseInt(row[0]);
                population = parsePopulation(row[1]);
                if ((population - prevPopulation) > delta) {
                    delta = population - prevPopulation;
                    deltaYear = year;
                }
                prevPopulation = population;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!");
        }
        System.out.println(deltaYear);
    }

    public static long parsePopulation(String population) {
        return Long.parseLong(population.replace(",", ""));
    }
}
