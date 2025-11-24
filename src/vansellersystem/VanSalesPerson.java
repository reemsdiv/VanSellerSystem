/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vansellersystem;

/**
 *
 * @author shaha
 */
public class VanSalesPerson {
    private String name;
    private String vanId;

    public VanSalesPerson(String name, String vanId) {
        this.name = name;
        this.vanId = vanId;
    }

    public String getName() { return name; }
    public String getVanId() { return vanId; }
}
