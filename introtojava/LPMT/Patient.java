public class Patient{
    public void patientMenu(){
        // method 
        System.out.println("1. Complete registration");
        System.out.println("2. Calculate LPMT");
        System.out.println("3. View medical history");
        System.out.println("4. Update profile");
        System.out.println("5. Delete profile");
        System.out.println("6. View profile");
        System.out.println("7. Exit");
        System.out.println("Please enter your choice: ");
        int choice = Integer.parseInt(System.console().readLine());
        switch (choice) {
            case 1:
                CompleteRegistration();
                break;
            case 2:
                CalculateLPMT();
                break;
            case 3:
                ViewMedicalHistory();
                break;
            case 4:
                UpdateProfile();
                break;
            case 5:
                DeleteProfile();
                break;
            case 6:
                ViewProfile();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                break;
        }
    }
    public void CompleteRegistration(){
        System.out.println("Completing registration");
        // I want to ask the user to enter their username and password
        System.out.println("Please enter your username: ");
        String username = System.console().readLine();
        System.out.println("Please enter your password: ");
        char[] password = System.console().readPassword();
        System.out.println("Please enter your email: ");
        String email = System.console().readLine();
        System.out.println("Please enter your phone number: ");
        String phone = System.console().readLine();
        System.out.println("Please enter your address: ");
        String address = System.console().readLine();
        System.out.println("Please enter your date of birth: ");
        String dob = System.console().readLine();
        System.out.println("");
        System.out.println("Registration completed successfully!");
    }

    public void CalculateLPMT(){
    // method to calculate LPMT - life expectancy
    }

    public void ViewMedicalHistory(){
    // method to view medical history
    }

    public void UpdateProfile(){
    // method to update profile
    }

    public void DeleteProfile(){
    // method to delete profile
    }

    public void ViewProfile(){
    // method to view profile
    }
}