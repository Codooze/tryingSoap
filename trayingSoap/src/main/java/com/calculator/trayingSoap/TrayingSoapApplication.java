package com.calculator.trayingSoap;

import com.calculator.trayingSoap.sever.CalculatorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TrayingSoapApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrayingSoapApplication.class, args);


		CalculatorService service = new CalculatorService();
		Scanner scanner = new Scanner(System.in);

		displayBasicCalculations(service);

		boolean isCalculatorOn = true;
		while (isCalculatorOn) {
			Double userResult = getUserInputAndCalculate(scanner, service);
			if (userResult == null) return;

			System.out.println("Result: " + userResult);
			isCalculatorOn = askToContinue(scanner);
		}
	}

	private static void displayBasicCalculations(CalculatorService service) {
		System.out.println("1 + 2 = " + service.add(1, 2));
		System.out.println("1 - 2 = " + service.subtract(1, 2));
		System.out.println("1 * 2 = " + service.multiply(1, 2));
		System.out.println("1 / 2 = " + service.divide(1, 2));
	}

	private static Double getUserInputAndCalculate(Scanner scanner, CalculatorService service) {
		System.out.print("Enter operation: ");
		String operation = scanner.nextLine().trim().toLowerCase();

		System.out.print("Enter first number: ");
		double num1 = scanner.nextDouble();

		System.out.print("Enter second number: ");
		double num2 = scanner.nextDouble();

		return calculateResult(operation, service, num1, num2);
	}

	private static Double calculateResult(String operation, CalculatorService service, double num1, double num2) {
        return switch (operation) {
            case "+" -> service.add(num1, num2);
            case "-" -> service.subtract(num1, num2);
            case "*" -> service.multiply(num1, num2);
            case "/" -> {
                if (num2 == 0) {
                    System.out.println("Error: Division by zero is not allowed.");
                    yield null; //yield is like return, but for switch statements
                }
                yield service.divide(num1, num2);
            }
            default -> {
                System.out.println("Error: Invalid operation.");
                yield getUserInputAndCalculate(new Scanner(System.in), service);
            }
        };
	}

	private static boolean askToContinue(Scanner scanner) {
		System.out.println("Do you want to continue? (y/n)");
		String answer = scanner.next().trim().toLowerCase();
		scanner.nextLine(); // Consume newline left-over

		if (answer.equals("n")) {
			return false;
		} else if (answer.equals("y")) {
			return true;
		} else {
			System.out.println("Error: Invalid input.");
			return false;
		}
	}

	}



/*
In React or SvelteKit, you'd use something like fetch or axios to make an API call.
Here, you're directly creating an instance of the CalculatorService
 */
