import java.io.IOException;

public class Admin {
    public void CreateUser() throws IOException, InterruptedException{
        System.out.println("Creating a new user");
        // I want to ask the user to enter their username and password
        System.out.println("Please enter the username of the new user: ");
        String username = System.console().readLine();
        System.out.println("Please enter the password of the new user: ");
        char[] password = System.console().readPassword();
        System.out.println("Please enter the role of the new user: ");
        String role = System.console().readLine();
        System.out.println("User " + username + " created successfully!");
        LPMT.callBash("createUser", username, password, role);
    }

    public void DeleteUser(){
        System.out.println("Deleting a user");
        // I want to ask the user to enter the username of the user to delete
        System.out.println("Please enter the username of the user to delete: ");
        String username = System.console().readLine();
        System.out.println("User " + username + " deleted successfully!");
        String[] command ={"./usermanawdagement.sh", username};
        Process process;
        try {
            System.out.println("Executing the command: ");
            process = Runtime.getRuntime().exec(command);
            System.out.println("Output of the command is: ");
            System.out.write(process.getInputStream().readAllBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("An error occurred while executing the command");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void UpdateUser(){
        System.out.println("Updating a user");
        // I want to ask the user to enter the username of the user to update
        System.out.println("Please enter the username of the user to update: ");
        String username = System.console().readLine();
        System.out.println("Please enter the new password of the user: ");
        char[] password = System.console().readPassword();
        System.out.println("Please enter the new role of the user: ");
        String role = System.console().readLine();
        System.out.println("User " + username + " updated successfully!");
        String[] command ={"./usermanawdagement.sh", username, new String(password)};
        Process process;
        try {
            System.out.println("Executing the command: ");
            process = Runtime.getRuntime().exec(command);
            System.out.println("Output of the command is: ");
            System.out.write(process.getInputStream().readAllBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("An error occurred while executing the command");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void ExportUser(){
        System.out.println("Exporting a user");
        // I want to ask the user to enter the username of the user to export
        System.out.println("Please enter the username of the user to export: ");
        String username = System.console().readLine();
        System.out.println("User " + username + " exported successfully!");
        String[] command ={"./usermanawdagement.sh", username};
        Process process;
        try {
            System.out.println("Executing the command: ");
            process = Runtime.getRuntime().exec(command);
            System.out.println("Output of the command is: ");
            System.out.write(process.getInputStream().readAllBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("An error occurred while executing the command");
            e.printStackTrace();
            System.exit(1);
        }
    }
    public void callAdminMenu() throws IOException, InterruptedException{
        System.out.println("Welcome to the admin menu");
        System.out.println("Please select an option: ");
        System.out.println("1. Create a new user");
        System.out.println("2. Delete a user");
        System.out.println("3. Update a user");
        System.out.println("4. Export a user");
        System.out.println("5. Exit");
        System.out.println("Please enter your choice: ");
        int choice = Integer.parseInt(System.console().readLine());
        switch (choice) {
            case 1:
                CreateUser();
                callAdminMenu();
                break;
            case 2:
                DeleteUser();
                callAdminMenu();
                break;
            case 3:
                UpdateUser();
                callAdminMenu();
                break;
            case 4:
                ExportUser();
                callAdminMenu();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                callAdminMenu();
                break;
        }
    }
}