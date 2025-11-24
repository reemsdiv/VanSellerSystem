package vansellersystem;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

/**
 * Main Van Seller System with integrated login
 * @author remys
 */
public class VanSellerSystem {
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner consoleInput = new Scanner(System.in);
        
        // Login Process using Login classes
        System.out.println("=== Van Sales Login System ===");
        VanSalesSystem system = new VanSalesSystem();
        LoginInterface loginInterface = new LoginInterface(system);
        
        int attempts = 0;
        boolean loggedIn = false;
        
        while (attempts < MAX_LOGIN_ATTEMPTS && !loggedIn) {
            System.out.print("Enter username: ");
            String username = consoleInput.nextLine();
            System.out.print("Enter password: ");
            String password = consoleInput.nextLine();
            
            User user = new User(username, password);
            loggedIn = user.login(loginInterface);
            
            if (!loggedIn) {
                attempts++;
                if (attempts < MAX_LOGIN_ATTEMPTS) {
                    System.out.println("Please try again. (" + (MAX_LOGIN_ATTEMPTS - attempts) + " attempts left)\n");
                } else {
                    System.out.println("Too many failed attempts. Access denied.\n");
                }
            }
        }
        
        if (!loggedIn) {
            System.out.println("Access denied. Exiting system.");
            consoleInput.close();
            return;
        }
        
        System.out.println("\nLoading inventory...\n");
        
        // Original Van Seller System code
        File inputFile = new File("inputFile.txt");
        Scanner input = new Scanner(inputFile);
        String id, name;
        double price;
        int quantity, mfgY, mfgM, mfgD, expY, expM, expD;
        Inventory inventory = new Inventory();
        
        while(input.hasNext()){
            id = input.next();
            name = input.next();
            mfgY = input.nextInt(); mfgM = input.nextInt(); mfgD = input.nextInt();
            expY = input.nextInt(); expM = input.nextInt(); expD = input.nextInt();
            price = input.nextInt();
            quantity = input.nextInt();
            
            inventory.add(new Product(id, name, 
                new Date(mfgY - 1900,mfgM,mfgD), 
                new Date(expY - 1900, expM, expD), 
                price, quantity) );
        
        }
        inventory.displayInventory();
        
        input.close();
        consoleInput.close();
    }
}