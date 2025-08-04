package model;

public class Product {
    private String productName;
    private String category;
    private int productID;
    private String description;
    private int stock;
    private double price;

    public Product(String productName,String category,int id,String description,int stock,double price){
        setProductID(id);
        setCategory(category);
        setDescription(description);
        setPrice(price);
        setStock(stock);
        setProductName(productName);
    }
    public Product(String productName,String category,String description,int stock,double price){
        setCategory(category);
        setDescription(description);
        setPrice(price);
        setStock(stock);
        setProductName(productName);
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }
}
