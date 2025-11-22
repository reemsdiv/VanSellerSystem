import java.io.*;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(LoginInterface loginInterface) {
        return loginInterface.enterCredentials(username, password);
    }
}

class LoginInterface {
    private VanSalesSystem system;

    public LoginInterface(VanSalesSystem system) {
        this.system = system;
    }

    public boolean enterCredentials(String username, String password) {
        String role = system.sendCredentials(username, password);
        if (role != null) {
            System.out.println("\nWelcome " + role + "!");
            
            return true;
        } else {
            System.out.println("\nLogin failed! Invalid username or password.\n");
            return false;
        }
    }
}

class VanSalesSystem {
    private Database database;

    public VanSalesSystem(Database database) {
        this.database = database;
    }

    public String sendCredentials(String username, String password) {
        return database.validateCredentials(username, password);
    }
}

class Database {
    private String dbFilePath = "users.txt"; // Use relative path for easier file access

    public String validateCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dbFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String dbUsername = parts[0].trim();
                    String dbPassword = parts[1].trim();
                    String dbRole = parts[2].trim();

                    if (username.equals(dbUsername) && password.equals(dbPassword)) {
                        if (dbRole.equalsIgnoreCase("Manager")) {
                            dbRole = "Admin"; // Treat Manager as Admin
                        }
                        return dbRole;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading database file: " + e.getMessage());
        }
        return null;
    }
}

public class UserLoginSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Database db = new Database();
        VanSalesSystem system = new VanSalesSystem(db);
        LoginInterface loginInterface = new LoginInterface(system);

        System.out.println("=== Van Sales Login System ===");

        int attempts = 0;
        final int maxAttempts = 3;
        boolean loggedIn = false;

        while (attempts < maxAttempts && !loggedIn) {
            System.out.print("Enter username: ");
            String username = input.nextLine();

            System.out.print("Enter password: ");
            String password = input.nextLine();

            User user = new User(username, password);
            loggedIn = user.login(loginInterface);

            if (!loggedIn) {
                attempts++;
                if (attempts < maxAttempts) {
                    System.out.println("Please try again. (" + (maxAttempts - attempts) + " attempts left)\n");
                } else {
                    System.out.println("Too many failed attempts. Access denied.\n");
                }
            }
        }

        input.close();
    }
}
