import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
    int lpmt;

    public void patientMenu(String uuid) throws IOException, InterruptedException{
        this.uuid = uuid;
        String[] command = {"./usermanagement.sh", "viewProfile", uuid};
        String output = LPMT.executeCommand(command);
        String[] userData = output.split(":");
        if (userData.length >= 12) {
            this.username = userData[0];
            this.password = userData[1].toCharArray();
            this.uuid = userData[3];
            this.firstName = userData[4];
            this.lastName = userData[5];
            this.email = userData[6];
            String dateofinfection = userData[7]+":" + userData[8] +":"+ userData[9];
            // change to boolean
            this.onMedication = Boolean.parseBoolean(userData[10]);
            String startDateOfMedication = userData[11] + ":" + userData[12] + ":" + userData[13];
            String dob = userData[14] + ":" + userData[15] + ":" + userData[16];
            this.country = userData[userData.length-1];
            // try to convert the date strings to Date objects
            dateofinfection = dateofinfection.replaceAll("\\s+", " ");
            startDateOfMedication = startDateOfMedication.replaceAll("\\s+", " ");
            dob = dob.replaceAll("\\s+", " ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            try {
                this.dateofinfection = dateFormat.parse(dateofinfection);
                this.starDateofMedication = startDateOfMedication.equals("null") ? null : dateFormat.parse(startDateOfMedication);
                this.dob = dateFormat.parse(dob);
            } catch (ParseException e) {
                System.out.println("An error occurred while parsing the date strings.");
                e.printStackTrace();
            }
            this.lpmt = calculateLPMT();
        }
        // method to display patient menu
        // add a while loop for logout
        boolean logout = false;
        while (!logout) {
            System.out.println("Welcome to the patient menu");
            System.out.println("Please select an option: ");
            System.out.println("1. Calculate LPMT");
            System.out.println("2. Update profile");
            System.out.println("3. View profile");
            System.out.println("4. Logout");
            System.out.println("Please enter your choice: ");
            int choice = Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1:
                    calculateLPMT();
                    break;
                case 2:
                    UpdateProfile();
                    break;
                case 3:
                    ViewProfile();
                    break;
                case 4:
                    logout = true;
                    break;
                default:
                    break;
            }
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
        return command;
    }

    public void CalculateLPMT(){
    // method to calculate LPMT - life expectancy - week two deliverable
    }


    public void UpdateProfile() throws IOException, InterruptedException{
        System.out.println("Updating profile");
        // I want to ask the user what he wants to updatte by displaying the options and loops until he chooses to save
        System.out.println("Please select an option: ");
        boolean save = false;
        String username = null;
        char[] password = null;
        String email = null;
        String country = null;
        String firstName = null;
        String lastName = null;
        Date dob = null;
        Date dateofinfection = null;
        Boolean onMedication = null;
        Date starDateofMedication = null;
        while(!save){
            System.out.println("1. Update username");
            System.out.println("2. Update password");
            System.out.println("3. Update email");
            System.out.println("4. Update country");
            System.out.println("5. Update first name");
            System.out.println("6. Update date of birth");
            System.out.println("7. Save");
            System.out.println("Please enter your choice: ");
            int choice = Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1:
                    System.out.println("Please enter your new username: ");
                    username = System.console().readLine();
                    break;
                case 2:
                    System.out.println("Please enter your new password: ");
                    password = System.console().readPassword();
                    break;
                case 3:
                    System.out.println("Please enter your new email: ");
                    email = System.console().readLine();
                    break;
                case 4:
                    System.out.println("Please enter your new country: ");
                    country = System.console().readLine();
                    break;
                case 5:
                    System.out.println("Please enter your first name: ");
                    firstName = System.console().readLine();
                    break;
                case 6:
                    System.out.println("Please enter your new date of birth: ");
                    String dateofbirth = System.console().readLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        dob = dateFormat.parse(dateofbirth);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    System.out.println("Saving changes...");
                    String[] command = {"./usermanagement.sh", "updateProfile", uuid, username != null ? username : this.username, password != null ? new String(password) : new String(this.password), firstName != null ? firstName : this.firstName, lastName != null ? lastName : this.lastName, email != null ? email : this.email, dateofinfection != null ? dateofinfection.toString() : this.dateofinfection.toString(), onMedication != null ? Boolean.toString(onMedication) : Boolean.toString(this.onMedication), starDateofMedication != null ? starDateofMedication.toString() : this.starDateofMedication.toString(), dob != null ? dob.toString() : this.dob.toString(), country != null ? country : this.country};
                    LPMT.executeCommand(command);
                    System.out.println("User data updated successfully");
                    save = true;
                    break;
                default:
                    break;
                
            }
            System.out.println("Do you want to save the changes? (yes/no): ");
        }
    }
    public void ViewProfile() throws IOException, InterruptedException{
        // i want to fetch the user data using the uuid and display it
        System.out.println("Viewing profile");
            this.lpmt = calculateLPMT();
            System.out.println("Username: " + username);
            System.out.println("Patient ID: " + uuid);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Email: " + email);
            System.out.println("Date of Birth: " + dateofinfection);
            System.out.println("Start Date of Medication: " + starDateofMedication);
            System.out.println("Date of Birth: " + dob);
            System.out.println("On medication: " + onMedication);
            System.out.println("Country: " + country);
            System.out.println("LPMT - remaining lifespan (the clock is ticking): " + lpmt + " years");
    }
    public int calculateLPMT() {
        // Define average lifespan per country
        Map<String, Integer> averageLifespan = new HashMap<>();
        averageLifespan.put("Rwanda", 69);
        // Add other countries as needed...
        // to calculate age current - dob
        Calendar dobCalendar = Calendar.getInstance();
        dobCalendar.setTime(dob);
        int birthYear = dobCalendar.get(Calendar.YEAR);

        Calendar currentCalendar = Calendar.getInstance();
        int currentYear = currentCalendar.get(Calendar.YEAR);

        int age = currentYear - birthYear;
        // to calculate years delayed: start date of medication - date of infection
        Calendar startDateOfMedicationCalendar = Calendar.getInstance();
        startDateOfMedicationCalendar.setTime(starDateofMedication);
        int startYear = startDateOfMedicationCalendar.get(Calendar.YEAR);
        Calendar dateOfInfectionCalendar = Calendar.getInstance();
        dateOfInfectionCalendar.setTime(dateofinfection);
        int infectionYear = dateOfInfectionCalendar.get(Calendar.YEAR);
        int yearsDelayed = startYear - infectionYear;
        // Retrieve the average lifespan for the patient's country
        int avgLifespan = averageLifespan.getOrDefault(country, 70); // Default to 70 if country not found

        // Calculate the initial remaining lifespan
        int remainingLifespan = avgLifespan - age;
        // Adjust remaining lifespan based on ART status and delay
        if (!onMedication) {
            remainingLifespan = 5 - yearsDelayed; // Patient will die in the 5th year if not on ART drugs
        } else {
            // Calculate the remaining lifespan considering the delay in starting ART drugs
            for (int i = 0; i < yearsDelayed; i++) {
                remainingLifespan *= 0.9; // Reduce by 10% for each year delayed
            }
        }

        // Round up to the next full year and if it is less than zero set it to zero
        remainingLifespan = Math.max(0, remainingLifespan);

        // Print the calculated remaining lifespan
        return remainingLifespan;
    }

    // Getters and setters for country, age, onART, and yearsDelayed...
}