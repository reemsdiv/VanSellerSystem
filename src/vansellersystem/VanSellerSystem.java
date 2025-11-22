package vansellersystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author remys
 */
public class VanSellerSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
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
    }
    
}
