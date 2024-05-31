// name of this package is ui
package ui;

// we import the Scanner class from the java.util package for user input & the Calculator class from the model package for the calculator operations logic
import java.util.Scanner;
import model.Calculator;

// this is the CalculatorUI class, which is the user interface for the calculator, it is public so it can be accessed by other classes
public class CalculatorUI {
    // we create an instance of the Calculator class to perform the calculations, and an instance of the Scanner class to get user input, they are private so they can only be accessed within this class
    private Calculator calculator;
    private Scanner scanner;

    // this is the constructor for the CalculatorUI class, it initializes the calculator and scanner instances
    public CalculatorUI() {
        calculator = new Calculator();
        scanner = new Scanner(System.in);
    }
    // this is the start method, which is the main method for the calculator UI, it takes no arguments and returns nothing (void)
    public void start() {
        // this is an infinite loop that keeps running until the user types 'exit' to quit
        while (true) {
            // we prompt the user to choose an operation
            System.out.println("Choose an operation: add, subtract, multiply, divide, advanced, or type 'help' for help, 'exit' to quit");
            // we get the user's input
            String operation = scanner.nextLine();
            // if the user types 'exit', we break out of the infinite loop and the program ends
            if (operation.equalsIgnoreCase("exit")) {
                break;
            }
            // if the user types 'help', we print the help menu and continue to the next iteration of the loop
            if (operation.equalsIgnoreCase("help")) {
                printHelp();
                continue;
            }
            // if the user types 'advanced', we prompt the user to enter an expression
            if (operation.equalsIgnoreCase("advanced")) {
                // we get the user's input
                System.out.println("Enter an expression:");
                // we read the expression from the user
                String expression = scanner.nextLine();
                // we try to evaluate the expression using the calculator's advanced method
                try {
                    double result = calculator.advanced(expression);
                    System.out.println("Result: " + result);
                } catch (IllegalArgumentException e) {
                    // if an exception is thrown, we catch it and print the error message
                    System.out.println(e.getMessage());
                }
                // we continue to the next iteration of the loop
                continue;
            }
            // if the user types 'add','subtract','multiply', or 'divide', we prompt the user to enter the numbers
            System.out.println("Enter numbers separated by spaces:");
            // we read the numbers from the user, split them by spaces, and convert them to an array of doubles
            String[] input = scanner.nextLine().split(" ");
            // we create an array of doubles to store the numbers entered by the user, the length of the array is the same as the length of the input array but for now it is empty
            double[] numbers = new double[input.length];
            // we iterate through the input array and convert each string to a double, storing it in the numbers array
            for (int i = 0; i < input.length; i++) {
                numbers[i] = Double.parseDouble(input[i]);
            }
            // we try to perform the operation using the calculator's appropriate method
            try {
                switch (operation.toLowerCase()) {
                    // if the operation is 'add', we call the add method of the calculator and print the result & break
                    case "add":
                        System.out.println("Result: " + calculator.add(numbers));
                        break;
                    // if the operation is'subtract', we call the subtract method of the calculator and print the result & break
                    case "subtract":
                        System.out.println("Result: " + calculator.subtract(numbers));
                        break;
                    // if the operation is'multiply', we call the multiply method of the calculator and print the result & break
                    case "multiply":
                        System.out.println("Result: " + calculator.multiply(numbers));
                        break;
                    // if the operation is 'divide', we call the divide method of the calculator and print the result & break
                    case "divide":
                        System.out.println("Result: " + calculator.divide(numbers));
                        break;
                    // if the operation is not any of the above, we print an error message & break
                    default:
                        System.out.println("Unknown operation. Type 'help' for help.");
                }
            // catch any exceptions thrown by the calculator methods and print the error message
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    // this is the printHelp method, which prints the help menu, it is private so it can only be accessed within this class
    private void printHelp() {
        System.out.println("Available operations: add, subtract, multiply, divide, advanced");
        System.out.println("To perform an operation, type its name and press enter, then enter the numbers separated by spaces.");
        System.out.println("Example: \nadd\n5 6\nThis will add 5 and 6.");
        System.out.println("For the 'advanced' operation, enter an expression. Example: \nadvanced\n5 + 6 * 2\nThis will evaluate the expression '5 + 6 * 2'.");
    }
    // this is the main method, which creates an instance of the CalculatorUI class and calls the start method to start the calculator UI
    public static void main(String[] args) {
        new CalculatorUI().start();
    }
}