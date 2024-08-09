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

    public void exportUserData() throws IOException, InterruptedException{
        System.out.println("Exporting data analytics");
        String[] command ={"./usermanagement.sh", "exportDataAnalytics"};
        String output = LPMT.executeCommand(command);
        System.out.println("LPMT analytics exported successfully");
    }


    public void ExportUserData() throws IOException, InterruptedException{
        System.out.println("Exporting user data");
        String[] command ={"./usermanagement.sh", "exportUserData"};
        String output = LPMT.executeCommand(command);
    }
    public void callAdminMenu() throws IOException, InterruptedException{
        boolean exit = false;
        while(!exit){
            System.out.println("Welcome to the admin menu");
            System.out.println("Please select an option: ");
            System.out.println("1. Create a new user");
            System.out.println("2. Export data analytics");
            System.out.println("3. Export patient data");
            System.out.println("4. Exit");
            System.out.println("Please enter your choice: ");
            int choice = Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1:
                    CreateUser();
                    break;
                case 2:
                    exportUserData();
                    break;
                case 3:
                    ExportUserData();
                    break;
                case 4:
                    exit=true;  
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}