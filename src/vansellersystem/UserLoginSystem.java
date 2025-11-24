package vansellersystem;

import java.io.*;

/**
 * Represents a user in the system
 */
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

/**
 * Handles the login interface and user interaction
 */
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

/**
 * Core system that validates credentials
 */
class VanSalesSystem {
    
    public String sendCredentials(String username, String password) {
        return validateCredentials(username, password);
    }
    
    /**
     * Validates user credentials from users.txt file
     * @param username User's username
     * @param password User's password
     * @return User role if valid, null otherwise
     */
    private String validateCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts.length >= 3) {
                    String dbUsername = parts[0].trim();
                    String dbPassword = parts[1].trim();
                    String dbRole = parts[2].trim();
                    
                    if (username.equals(dbUsername) && password.equals(dbPassword)) {
                        // Treat Manager as Admin
                        if (dbRole.equalsIgnoreCase("Manager")) {
                            dbRole = "Admin";
                        }
                        return dbRole;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading users file: " + e.getMessage());
        }
        
        return null;
    }
}