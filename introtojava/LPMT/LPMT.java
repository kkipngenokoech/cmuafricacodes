import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LPMT {
    static String username;
    static char[] password;
    static Map<String, Runnable> commands = new HashMap<>();
    private static Admin admin = new Admin();
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to LPMT - Your healtcare clock-in system. Please login to access our services");
        initializeFunctionMap();
        login();

    }

    public static void login(){
        // method to login
        System.out.println("Please enter your username: ");
        username = System.console().readLine();
        System.out.println("Please enter your password: ");
        password = System.console().readPassword();
        String passwordString = new String(password);
        System.out.println("Welcome " + username + "!" + passwordString);
        callBash("loginUser", username, password);
    }

    public static void callBash(String function, String username, char[] password){
        // method to call bash script
        String[] command ={"./usermanagement.sh", function, username, new String(password)};
        Process process;
        try {
            process = Runtime.getRuntime().exec(command);
            String role = new String(process.getInputStream().readAllBytes()).trim();
            int exitCode = process.waitFor();
            if(exitCode != 0){
                System.out.println("An error occurred while executing the command");
                System.exit(1);
            } else {
                switch (function) {
                    case "loginUser":
                        if ((role.equals("admin") || role.equals("patient"))){
                            System.out.println("Login successful");
                            System.out.println("Welcome " + username + "!");
                            if (role.equals("admin")){
                                admin.callAdminMenu();
                            } else {
                                // patient.patientMenu();
                            }
                        } else {
                            System.out.println("Invalid username or password");
                            login();
                        }
                        break;
                
                    default:
                        break;
                }
            }
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println("An error occurred while executing the command");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void callFunction(String function, String username, char[] password, String role){
        String[] command ={"./usermanagement.sh", function, username, new String(password), role};
        Process process;
    }
    private static int executeCommand(String command){
        Process process;
        try {
            process = Runtime.getRuntime().exec(command);
            String role = new String(process.getInputStream().readAllBytes()).trim();
            int exitCode = process.waitFor();
            return exitCode;
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println("An error occurred while executing the command");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void initializeFunctionMap(){
        commands.put("login", LPMT::login);
        // commands.put("CreateUser", admin::CreateUser);
        // commands.put("CalculateLPMT", LPMT::CalculateLPMT);
        // commands.put("ViewMedicalHistory", LPMT::ViewMedicalHistory);
        // commands.put("UpdateProfile", LPMT::UpdateProfile);
        // commands.put("DeleteProfile", LPMT::DeleteProfile);
        // commands.put("ViewProfile", LPMT::ViewProfile);
    }

}