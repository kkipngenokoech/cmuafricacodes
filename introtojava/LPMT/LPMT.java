import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LPMT {
    static String uuid;
    static String username;
    static char[] password;
    static Map<String, Runnable> commands = new HashMap<>();
    private static Admin admin = new Admin();
    private static Patient patient = new Patient();
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to LPMT - Your healtcare clock-in system. Please login to access our services");
        initializeFunctionMap();
        login();

    }

    public static void login() throws IOException, InterruptedException{
        System.out.println("login with email/username? 1 or UUID? 2 note:  UUID is just for first time login");
        int choice = Integer.parseInt(System.console().readLine());
        if (choice == 1){
            System.out.println("Please enter your username: ");
            username = System.console().readLine();
            System.out.println("Please enter your password: ");
            password = System.console().readPassword();
            callBash("loginUser", username, password);
        } else {
            System.out.println("Please enter your UUID: ");
            String uuid = System.console().readLine();
            String[] command ={"./usermanagement.sh", "checkUUID", uuid};
            String username = executeCommand(command).trim();
            if (username == null || username.isEmpty()){
                System.out.println("Invalid UUID");
                login();
            } else {
                System.out.println("Congratulations! Your UUID is valid. Please proceed and complete your registration");
                String[] commandCompleteReg= patient.CompleteRegistration(uuid);
                System.out.println(commandCompleteReg.length);
                String test = executeCommand(commandCompleteReg);
                System.out.println(test+" ........");
                patient.patientMenu(uuid);

            }
        }
        // method to login
    }

    public static void callBash(String function, String username, char[] password, String role) throws IOException, InterruptedException{
        String[] command ={"./usermanagement.sh", function, username, new String(password), role};
        executeCommand(command);
    }

    public static void callBash(String function, String username, char[] password) throws IOException, InterruptedException{
        // method to call bash script
        String[] command ={"./usermanagement.sh", function, username, new String(password)};
        String output = executeCommand(command).trim();
        String role;
        String[] lines = output.split("\n");
        if (lines.length > 0) {
            // Split the first line by ':' to get role and UUID
            String[] parts = lines[0].split(":");
            if (parts.length == 2) {
                role = parts[0].trim();
                uuid = parts[1].trim();
            } else {
                role = lines[0].trim();
            }
            role = role.split("\n")[0].trim();
            System.out.println(role);
            switch (function) {
                case "loginUser":
                    if ((role.equals("admin") || role.equals("patient"))){
                        System.out.println("Login successful");
                        System.out.println("Welcome " + username + "!");
                            if (role.equals("admin")){
                                admin.callAdminMenu();
                            } else {
                                patient.patientMenu(uuid);
                            }
                    } else {
                        System.out.println("Invalid username or password");
                        login();
                    }
                    break;
                case "createUser":
                    System.out.println("User " + username + " created successfully!");
                    break;
            default:
                break;
            }
    }
    }
    static String executeCommand(String[] command) throws IOException, InterruptedException{
        Process process = Runtime.getRuntime().exec(command);
        int exitCode = process.waitFor();
        if(exitCode != 0){
            System.out.println("An error occurred while executing the command");
            return null;
        }
        return new String(process.getInputStream().readAllBytes()).trim();

    }

    public static void initializeFunctionMap(){
        commands.put("login", () -> {
            try {
                login();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        // commands.put("CreateUser", admin::CreateUser);
        // commands.put("CalculateLPMT", LPMT::CalculateLPMT);
        // commands.put("ViewMedicalHistory", LPMT::ViewMedicalHistory);
        // commands.put("UpdateProfile", LPMT::UpdateProfile);
        // commands.put("DeleteProfile", LPMT::DeleteProfile);
        // commands.put("ViewProfile", LPMT::ViewProfile);
    }

}