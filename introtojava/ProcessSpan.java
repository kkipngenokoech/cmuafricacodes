import java.io.IOException;

public class ProcessSpan {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello, World!");
        String[] command ={"ls", "-la"};
        Process process = Runtime.getRuntime().exec(command);
        System.out.write(process.getInputStream().readAllBytes());
    }
}
/***
 * The ProcessSpan class is a simple Java program that executes the ls command and prints the output to the console.
 * The main method first prints "Hello, World!" to the console.
 * It then creates a String array command containing the command to be executed, which is ls.
 * The Runtime.getRuntime().exec() method is used to execute the command, and the resulting Process object is stored in the process variable.
 * The process.getInputStream().readAllBytes() method is used to read the output of the command and print it to the console.
 * The program demonstrates how to execute a command in Java and read its output.
 * The output of the ls command will vary depending on the files and directories in the current working directory.
 * The program may throw an IOException if there is an error executing the command.
 * The program does not handle any exceptions, so it simply propagates the exception to the caller.
 * The program is a simple example of executing a command in Java using the Runtime class.
 * The program is platform-dependent, as the ls command is specific to Unix-like systems. On Windows, the dir command could be used instead.
 * The program does not provide any user input or interaction, as it simply executes a command and prints the output.
 */

