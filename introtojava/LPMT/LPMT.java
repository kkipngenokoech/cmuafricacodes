import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LPMT {
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
        // method to login
        System.out.println("Please enter your username: ");
        username = System.console().readLine();
        System.out.println("Please enter your password: ");
        password = System.console().readPassword();
        callBash("loginUser", username, password);
    }

    public static void callBash(String function, String username, char[] password, String role) throws IOException, InterruptedException{
        String[] command ={"./usermanagement.sh", function, username, new String(password), role};
        executeCommand(command);
    }

    public static void callBash(String function, String username, char[] password) throws IOException, InterruptedException{
        // method to call bash script
        String[] command ={"./usermanagement.sh", function, username, new String(password)};
        String role = executeCommand(command).trim();
        role = role.split("\n")[0].trim();
        switch (function) {
            case "loginUser":
                if ((role.equals("admin") || role.equals("patient"))){
                    System.out.println("Login successful");
                    System.out.println("Welcome " + username + "!");
                        if (role.equals("admin")){
                            admin.callAdminMenu();
                        } else {
                            patient.patientMenu();
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
    private static String executeCommand(String[] command) throws IOException, InterruptedException{
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