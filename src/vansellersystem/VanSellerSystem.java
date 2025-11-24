package vansellersystem;

import java.io.*;
import java.time.LocalDate;
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
                price) );
        
        }
        inventory.displayInventory();
        
        
        // Van seller (driver)
        VanSalesPerson seller = new VanSalesPerson("James Carter", "VAN-12");

        // Products
        Product p1 = new Product("P001", "Coca-Cola-500ml", 1.20);
        Product p2 = new Product("P002", "Lays-Classic", 0.75);

        // Create delivery note
        DeliveryNote note = new DeliveryNote(
                "DN-1001",
                "S-1001",
                LocalDate.of(2025, 11, 15),
                seller,
                "FreshMart Supermarket",
                "45 Industrial Zone"
        );

        // Add sold items
        note.addSale(p1, 24);
        note.addSale(p2, 30);

        // Add returns
        note.addReturn(p2, 2);

        // Payment
        double total = (24 * 1.20) + ((30 - 2) * 0.75);
        note.setPayment(total, "Cash");

        // Print
        note.print();
    
        
        input.close();
        consoleInput.close();
    }
}