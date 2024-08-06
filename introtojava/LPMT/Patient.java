public class Patient{
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