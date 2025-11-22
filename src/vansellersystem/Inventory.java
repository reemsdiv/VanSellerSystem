/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vansellersystem;
import java.util.ArrayList;

/**
 *
 * @author remys
 */
public class Inventory {
    //Attributes
    private ArrayList<Product> products;
    
    //Constructor
    public Inventory() {
        products = new ArrayList();
    }
     
    //Methods
    public void add(Product p){
        products.add(p);
    }
    
    public int getInventorySize(){
        return products.size();
    }
    
    public void displayInventory(){
        for(Product p : products){
            System.out.println(p.getProductDetails());
        }
    }

}