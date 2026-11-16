import model.User;
import service.UserService;
import java.util.List;

public class TestUserAccess {

    public static void main(String[] args) {

        UserService userService = new UserService();

        // Login as admin
        User admin = userService.login(
            "admin", "MediStockAdmin2026!"
        );

        // Login as staff
        User staff = userService.login(
            "staff_test_01", "TestPass123!"
        );

        System.out.println("===== ADMIN ACCESS =====");

        if (admin != null) {

            List<User> users = userService.getAllUsers(admin);

            for (User user : users) {
                System.out.println(
                    user.getUserId() + " | " +
                    user.getUsername() + " | " +
                    user.getFullName() + " | " +
                    user.getRole()
                );
            }

        } else {
            System.out.println("Admin login failed.");
        }

        System.out.println("\n===== STAFF ACCESS =====");

        List<User> staffResult = userService.getAllUsers(staff);

        if (staffResult.isEmpty()) {
            System.out.println("Staff access denied successfully.");
        } else {
            System.out.println("Access restriction failed!");
        }
    }
}
