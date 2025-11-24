package vansellersystem;
import java.util.Date;

/**
 *
 * @author remys
 */
public class Product {
    //Attributes
    private String productID;
    private String name;
    private Date mfgDate;
    private Date expDate;
    private double price;
    private int quantity;
    
    
    //Constructor
    public Product(String productID, String name, Date mfgDate, Date expDate, double price, int quantity) {
        this.productID = productID;
        this.name = name;
        this.mfgDate = mfgDate;
        this.expDate = expDate;
        this.price = price;
        this.quantity = quantity;
    }

    //Methods
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getMfgDate() {
        return mfgDate;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpDate() {
        return expDate;
    }

    public boolean isExpired(){
        Date currentDate = new Date();
        return expDate.before(currentDate);
    }
    
    public String getProductDetails() {
        return  "productID = " + productID 
                + "\nname = " + name 
                + "\nmfgDate = " + mfgDate 
                + "\nexpDate = " + expDate 
                + "\nprice = " + price 
                + "\nquantity = " + quantity;
    }   
    
}
