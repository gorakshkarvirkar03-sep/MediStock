
import model.User;
import service.UserService;

public class TestUserAuthentication {

    public static void main(String[] args) {

        UserService userService = new UserService();

        // 1. Register a staff account
        System.out.println("===== STAFF REGISTRATION =====");

        boolean registered = userService.registerStaff(
            "staff_test_01",
            "TestPass123!",
            "Test Staff"
        );

        if (registered) {
            System.out.println("Staff registered successfully!");
        } else {
            System.out.println("Registration failed or username already exists.");
        }

        // 2. Test login with correct password
        System.out.println("\n===== CORRECT PASSWORD LOGIN =====");

        User user = userService.login(
            "staff_test_01",
            "TestPass123!"
        );

        if (user != null) {
            System.out.println("Login successful!");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Full Name: " + user.getFullName());
            System.out.println("Role: " + user.getRole());
        } else {
            System.out.println("Login failed!");
        }

        // 3. Test login with incorrect password
        System.out.println("\n===== INCORRECT PASSWORD LOGIN =====");

        User wrongLogin = userService.login(
            "staff_test_01",
            "WrongPassword123"
        );

        if (wrongLogin == null) {
            System.out.println("Incorrect password rejected successfully!");
        } else {
            System.out.println("Authentication test failed!");
        }
    }
}

