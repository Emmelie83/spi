package com.emmeliejohansson.consumer;

import com.emmeliejohansson.service.CurrencyConverter;
import com.emmeliejohansson.service.CurrencyConverterImpl;

import java.util.Scanner;
import java.util.ServiceLoader;

public class Main {
    private static final int NUM_CURRENCIES = 4;

    public static void main(String[] args) {
        ServiceLoader<CurrencyConverter> loader = ServiceLoader.load(CurrencyConverter.class);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            CurrencyConverter converter = chooseConverter(scanner, loader);
            if (converter != null) {
                double amount = chooseAmount(scanner);
                String toCurrency = chooseToCurrency(scanner, loader);
                double convertedAmount = converter.convert(amount, toCurrency);
                System.out.println("Converted amount: " + convertedAmount + " " + toCurrency);
            } else {
                System.out.println("Invalid choice.");
            }
            System.out.println("Press 'q' and enter to quit or just enter to continue");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("q")) break;
        }
        scanner.close();
    }


    private static CurrencyConverter chooseConverter(Scanner scanner, ServiceLoader<CurrencyConverter> loader) {
        printConverterMenu(loader);
        int choice = readInputInRange(scanner, 1, NUM_CURRENCIES);
        return getConverterByChoice(choice, loader);
    }

    private static void printConverterMenu(ServiceLoader<CurrencyConverter> loader) {
        System.out.println("What currency do you want to convert from?");
        int i = 1;
        for (CurrencyConverter converter : loader) {
            CurrencyConverterImpl annotation = converter.getClass().getAnnotation(CurrencyConverterImpl.class);
            System.out.println(i++ + ". " + annotation.name());
        }
    }

    private static CurrencyConverter getConverterByChoice(int choice, ServiceLoader<CurrencyConverter> loader) {
        int i = 1;
        for (CurrencyConverter converter : loader) {
            if (i++ == choice) {
                return converter;
            }
        }
        return null;
    }

    private static double chooseAmount(Scanner scanner) {
        while (true) {
            System.out.println("Enter the amount to convert:");
            double amount = readDoubleInput(scanner);
            if (amount > 0) {
                return amount;
            }
            System.out.println("The amount must be greater than 0.");
        }
    }

    private static String chooseToCurrency(Scanner scanner, ServiceLoader<CurrencyConverter> loader) {
        printToCurrencyMenu(loader);
        int choice = readInputInRange(scanner, 1, NUM_CURRENCIES);
        return getCurrencyByChoice(choice);
    }

    private static void printToCurrencyMenu(ServiceLoader<CurrencyConverter> loader) {
        System.out.println("What currency do you want to convert to?");
        int i = 1;
        for (CurrencyConverter converter : loader) {
            CurrencyConverterImpl annotation = converter.getClass().getAnnotation(CurrencyConverterImpl.class);
            System.out.println(i++ + ". " + annotation.name());
        }
    }

    private static String getCurrencyByChoice(int choice) {
        String[] currencies = {"GBP", "USD", "EUR", "SEK"};
        return currencies[choice - 1];
    }

    private static int readInputInRange(Scanner scanner, int min, int max) {
        while (true) {
            int choice = readIntInput(scanner);
            if (choice >= min && choice <= max) {
                return choice;
            }
            System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
        }
    }

    private static int readIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private static double readDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
