package com.emmeliejohansson.consumer;

import com.emmeliejohansson.service.CurrencyConverter;
import com.emmeliejohansson.service.CurrencyConverterImpl;

import java.util.Scanner;
import java.util.ServiceLoader;

public class Main {

    public static void main(String[] args) {
        ServiceLoader<CurrencyConverter> loader = ServiceLoader.load(CurrencyConverter.class);
        Scanner scanner = new Scanner(System.in);

        CurrencyConverter converter = chooseConverter(scanner, loader);
        if (converter != null) {
            double amount = chooseAmount(scanner);
            String toCurrency = chooseToCurrency(scanner, loader);

            double convertedAmount = converter.convert(amount, toCurrency);
            System.out.println("Converted amount: " + convertedAmount + " " + toCurrency);
        } else {
            System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    private static CurrencyConverter chooseConverter(Scanner scanner, ServiceLoader<CurrencyConverter> loader) {
        printConverterMenu(loader);
        int choice = readIntInput(scanner);
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
        System.out.println("Enter the amount to convert:");
        return readDoubleInput(scanner);
    }

    private static String chooseToCurrency(Scanner scanner, ServiceLoader<CurrencyConverter> loader) {
        printToCurrencyMenu(loader);
        int choice = readIntInput(scanner);
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
        if (choice >= 1 && choice <= currencies.length) {
            return currencies[choice - 1];
        }
        return "";
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
