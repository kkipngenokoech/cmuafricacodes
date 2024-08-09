import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Patient{
    String uuid;
    String username;
    String firstName;
    String lastName;
    Date dateofinfection;
    boolean onMedication;
    Date starDateofMedication;
    char[] password;
    String email;
    Date dob;
    String role;
    String country;
    String lpmt;

    public void patientMenu(String uuid) throws IOException, InterruptedException{
        this.uuid = uuid;
        // method to display patient menu
        System.out.println("2. Calculate LPMT");
        System.out.println("3. Update profile");
        System.out.println("4. View profile");
        System.out.println("5. Exit");
        System.out.println("Please enter your choice: ");
        int choice = Integer.parseInt(System.console().readLine());
        switch (choice) {
            case 2:
                CalculateLPMT();
                break;
            case 3:
                UpdateProfile();
                break;
            case 4:
                ViewProfile();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                break;
        }
    }
    public String[] CompleteRegistration(String uuid) {
        this.uuid = uuid;
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
        System.out.println("Please enter your username: ");
        username = scanner.nextLine();
        System.out.println("Please enter your first name: ");
        firstName = scanner.nextLine();
        System.out.println("Please enter your last name: ");
        lastName = scanner.nextLine();
        System.out.println("Please enter your date of infection (yyyy-MM-dd): ");
        String dateInput = scanner.nextLine();
    
        try {
            dateofinfection = dateFormat.parse(dateInput);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            e.printStackTrace();
        }
    
        System.out.println("Are you on medication? (true/false): ");
        onMedication = Boolean.parseBoolean(scanner.nextLine());
        if (onMedication) {
            System.out.println("Please enter the start date of your medication (yyyy-MM-dd): ");
            String medStartDateInput = scanner.nextLine();
            try {
                starDateofMedication = dateFormat.parse(medStartDateInput);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                e.printStackTrace();
            }
        } else {
            starDateofMedication = null;
        }
    
        System.out.println("Please enter your password: ");
        password = scanner.nextLine().toCharArray();
        System.out.println("Please enter your email: ");
        email = scanner.nextLine();
        System.out.println("Please enter your date of birth (yyyy-MM-dd): ");
        String dobInput = scanner.nextLine();
        try {
            dob = dateFormat.parse(dobInput);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            e.printStackTrace();
        }
        System.out.println("Please enter your country: ");
        country = scanner.nextLine();
        String[] command = {"./usermanagement.sh", "completeRegistration", uuid, username, new String(password), firstName, lastName, email, dateofinfection.toString(), Boolean.toString(onMedication), starDateofMedication != null ? starDateofMedication.toString() : "null", dob.toString(), country, "patient"};
        System.out.println("Command: " + Arrays.toString(command));
        return command;
    }

    public void CalculateLPMT(){
    // method to calculate LPMT - life expectancy - week two deliverable
    }


    public void UpdateProfile(){
        System.out.println("Updating profile");
        // I want to ask the user what he wants to updatte by displaying the options and loops until he chooses to save
        System.out.println("Please select an option: ");
        boolean save = false;
        while(!save){
            System.out.println("1. Update username");
            System.out.println("2. Update password");
            System.out.println("3. Update email");
            System.out.println("4. Update phone number");
            System.out.println("5. Update address");
            System.out.println("6. Update date of birth");
            System.out.println("7. Save");
            System.out.println("Please enter your choice: ");
            int choice = Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1:
                    System.out.println("Please enter your new username: ");
                    String username = System.console().readLine();
                    break;
                case 2:
                    System.out.println("Please enter your new password: ");
                    char[] password = System.console().readPassword();
                    break;
                case 3:
                    System.out.println("Please enter your new email: ");
                    String email = System.console().readLine();
                    break;
                case 4:
                    System.out.println("Please enter your new phone number: ");
                    String phone = System.console().readLine();
                    break;
                case 5:
                    System.out.println("Please enter your new address: ");
                    String address = System.console().readLine();
                    break;
                case 6:
                    System.out.println("Please enter your new date of birth: ");
                    String dob = System.console().readLine();
                    break;
                case 7:
                    save = true;
                    break;
                default:
                    break; 
            }
        }
    }
    public void ViewProfile() throws IOException, InterruptedException{
        // i want to fetch the user data using the uuid and display it
        System.out.println("Viewing profile");
        String[] command = {"./usermanagement.sh", "viewProfile", uuid};
        String output = LPMT.executeCommand(command);
        String[] userData = output.split(":");
        if (userData.length >= 12) {
            String username = userData[0];
            String patientId = userData[3];
            String firstName = userData[4];
            String lastName = userData[5];
            String email = userData[6];
            String dateofinfection = userData[7]+":" + userData[8] +":"+ userData[9];
            // change to boolean
            boolean onMedication = Boolean.parseBoolean(userData[10]);
            String startDateOfMedication = userData[11] + ":" + userData[12] + ":" + userData[13];
            String dob = userData[14] + ":" + userData[15] + ":" + userData[16];
            String country = userData[userData.length-1];
            // try to convert the date strings to Date objects
            dateofinfection = dateofinfection.replaceAll("\\s+", " ");
            startDateOfMedication = startDateOfMedication.replaceAll("\\s+", " ");
            dob = dob.replaceAll("\\s+", " ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            try {
                this.dateofinfection = dateFormat.parse(dateofinfection);
                this.starDateofMedication = startDateOfMedication.equals("null") ? null : dateFormat.parse(startDateOfMedication);
                this.dob = dateFormat.parse(dob);
                System.out.println("Date of infection: " + this.dateofinfection);
                System.out.println("Start date of medication: " + this.starDateofMedication);
                System.out.println("Date of birth: " + this.dob);
            } catch (ParseException e) {
                System.out.println("An error occurred while parsing the date strings.");
                e.printStackTrace();
            }
            System.out.println("Username: " + username);
            System.out.println("Patient ID: " + patientId);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Email: " + email);
            System.out.println("Date of Birth: " + dateofinfection);
            System.out.println("Start Date of Medication: " + startDateOfMedication);
            System.out.println("Date of Birth: " + dob);
            System.out.println("Active: " + onMedication);
            System.out.println("Country: " + country);
        } else {
            System.out.println("Invalid user data format.");
        }
    }
}