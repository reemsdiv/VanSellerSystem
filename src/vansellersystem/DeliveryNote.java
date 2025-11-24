/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vansellersystem;

/**
 *
 * @author shaha
 */
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class DeliveryNote {

    private String noteId;
    private String saleId;
    private LocalDate date;
    private VanSalesPerson seller;

    private String customerName;
    private String customerAddress;

    private double totalCollected;
    private String paymentMethod;

    private Map<Product, Integer> sold = new LinkedHashMap<>();
    private Map<Product, Integer> returns = new LinkedHashMap<>();

    public DeliveryNote(String noteId, String saleId, LocalDate date, VanSalesPerson seller,
                        String customerName, String customerAddress) {

        this.noteId = noteId;
        this.saleId = saleId;
        this.date = date;
        this.seller = seller;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
    }

    public void addSale(Product p, int qty) {
        sold.put(p, qty);
    }

    public void addReturn(Product p, int qty) {
        returns.put(p, qty);
    }

    public void setPayment(double amount, String method) {
        this.totalCollected = amount;
        this.paymentMethod = method;
    }

    private double calculateNet() {
        double net = 0;
        for (var entry : sold.entrySet()) {
            Product p = entry.getKey();
            int soldQty = entry.getValue();
            int returnedQty = returns.getOrDefault(p, 0);
            net += p.getPrice() * (soldQty - returnedQty);
        }
        return net;
    }

    public void print() {
        System.out.println("=============================================");
        System.out.println("              VAN SALES DELIVERY NOTE        ");
        System.out.println("=============================================");
        System.out.printf("Note ID : %s\tDate: %s%n", noteId, date);
        System.out.printf("Van     : %s\tDriver: %s%n", seller.getVanId(), seller.getName());
        System.out.println("---------------------------------------------");
        System.out.println("Customer Information:");
        System.out.println("Name    : " + customerName);
        System.out.println("Address : " + customerAddress);
        System.out.println("Linked Sale: " + saleId);
        System.out.println("---------------------------------------------");
        System.out.printf("%-8s | %-20s | %5s | %8s | %9s%n",
                "Code", "Product", "Sold", "Returned", "LineTotal");
        System.out.println("---------------------------------------------------------------");

        for (var entry : sold.entrySet()) {
            Product p = entry.getKey();
            int soldQty = entry.getValue();
            int returnedQty = returns.getOrDefault(p, 0);
            double lineTotal = p.getPrice() * (soldQty - returnedQty);

            System.out.printf("%-8s | %-20s | %5d | %8d | %9.2f%n",
                    p.getProductID(), p.getName(), soldQty, returnedQty, lineTotal);
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("Net Sales Value: %.2f%n%n", calculateNet());
        System.out.println("Money Collected:");
        System.out.printf("Amount Received: %.2f\tPayment Method: %s%n", totalCollected, paymentMethod);
        System.out.println("=============================================");
    }
}
