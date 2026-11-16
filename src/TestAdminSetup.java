
import model.User;
import service.UserService;

public class TestAdminSetup {

    public static void main(String[] args) {

        UserService userService = new UserService();

        // 1. Create the first administrator
        System.out.println("===== FIRST ADMIN SETUP =====");

        boolean created = userService.createFirstAdmin(
            "admin",
            "MediStockAdmin2026!",
            "MediStock Administrator"
        );

        if (created) {
            System.out.println("First administrator created successfully!");
        } else {
            System.out.println("Admin setup was rejected or an admin already exists.");
        }

        // 2. Test administrator login
        System.out.println("\n===== ADMIN LOGIN =====");

        User admin = userService.login(
            "admin",
            "MediStockAdmin2026!"
        );

        if (admin != null && "ADMIN".equals(admin.getRole())) {
            System.out.println("Admin login successful!");
            System.out.println("Username: " + admin.getUsername());
            System.out.println("Role: " + admin.getRole());
        } else {
            System.out.println("Admin login failed!");
        }

        // 3. Try creating a second administrator
        System.out.println("\n===== SECOND ADMIN SETUP =====");

        boolean secondAdmin = userService.createFirstAdmin(
            "admin_second",
            "AnotherSecurePass2026!",
            "Second Administrator"
        );

        if (!secondAdmin) {
            System.out.println("Second admin creation rejected successfully!");
        } else {
            System.out.println("Admin setup restriction failed!");
        }
    }
}
