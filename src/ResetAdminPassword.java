import java.util.Scanner;
import service.UserService;
import model.User;

public class ResetAdminPassword {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        System.out.print("Enter admin password to verify: ");
        String password = scanner.nextLine();

        User user = userService.login("admin", password);

        if (user != null) {
            System.out.println("SUCCESS: Admin authentication works!");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Role: " + user.getRole());
        } else {
            System.out.println("FAILED: Java authentication did not succeed.");
        }

        scanner.close();
    }
}