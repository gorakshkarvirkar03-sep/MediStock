
import java.util.Scanner;
import service.UserService;

public class ResetAdminPassword {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        System.out.println("===== MediStock Admin Password Reset =====");

        System.out.print("Enter new admin password: ");
        String newPassword = scanner.nextLine();

        System.out.print("Confirm new admin password: ");
        String confirmPassword = scanner.nextLine();

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("ERROR: Passwords do not match!");
            scanner.close();
            return;
        }

        boolean success =
                userService.resetPassword("admin", newPassword);

        if (success) {
            System.out.println("SUCCESS: Admin password updated!");
        } else {
            System.out.println("FAILED: Password reset unsuccessful.");
        }

        scanner.close();
    }
}
