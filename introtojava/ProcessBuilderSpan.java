import java.io.IOException;

public class ProcessBuilderSpan {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, World!");
        ProcessBuilder processBuilder = new ProcessBuilder("ls", "-la");
        Process process = processBuilder.start();
        System.out.write(process.getInputStream().readAllBytes());
    }
}

/*
 * The ProcessBuilderSpan class is a simple Java program that executes the ls command and prints the output to the console.
 * The main method first prints "Hello, World!" to the console.
 * It then creates a ProcessBuilder object processBuilder with the command to be executed, which is ls.
 * The processBuilder.start() method is used to start the process, and the resulting Process object is stored in the process variable.
 * The process.getInputStream().readAllBytes() method is used to read the output of the command and print it to the console.
 * The program demonstrates how to execute a command in Java using the ProcessBuilder class.
 */