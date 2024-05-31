package ui;

import model.Calculator;

import java.util.Scanner;

public class CalculatorUI {
    private Calculator calculator;
    private Scanner scanner;

    public CalculatorUI() {
        calculator = new Calculator();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Choose an operation: add, subtract, multiply, divide, advanced, or type 'help' for help, 'exit' to quit");
            String operation = scanner.nextLine();

            if (operation.equalsIgnoreCase("exit")) {
                break;
            }

            if (operation.equalsIgnoreCase("help")) {
                printHelp();
                continue;
            }

            if (operation.equalsIgnoreCase("advanced")) {
                System.out.println("Enter an expression:");
                String expression = scanner.nextLine();
                try {
                    double result = calculator.advanced(expression);
                    System.out.println("Result: " + result);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }

            System.out.println("Enter numbers separated by spaces:");
            String[] input = scanner.nextLine().split(" ");
            double[] numbers = new double[input.length];
            for (int i = 0; i < input.length; i++) {
                numbers[i] = Double.parseDouble(input[i]);
            }

            try {
                switch (operation.toLowerCase()) {
                    case "add":
                        System.out.println("Result: " + calculator.add(numbers));
                        break;
                    case "subtract":
                        System.out.println("Result: " + calculator.subtract(numbers));
                        break;
                    case "multiply":
                        System.out.println("Result: " + calculator.multiply(numbers));
                        break;
                    case "divide":
                        System.out.println("Result: " + calculator.divide(numbers));
                        break;
                    default:
                        System.out.println("Unknown operation. Type 'help' for help.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printHelp() {
        System.out.println("Available operations: add, subtract, multiply, divide, advanced");
        System.out.println("To perform an operation, type its name and press enter, then enter the numbers separated by spaces.");
        System.out.println("Example: \nadd\n5 6\nThis will add 5 and 6.");
        System.out.println("For the 'advanced' operation, enter an expression. Example: \nadvanced\n5 + 6 * 2\nThis will evaluate the expression '5 + 6 * 2'.");
    }

    public static void main(String[] args) {
        new CalculatorUI().start();
    }
}