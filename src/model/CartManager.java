package model;

import java.util.ArrayList;


public class CartManager {
    private static final ArrayList<Product> cart = new ArrayList<>();

    public static void addToCart(Product p) { cart.add(p); }
    public static void removeFromCart(Product p) { cart.remove(p); }
    public static ArrayList<Product> getCart() { return cart; }
    public static void clear() { cart.clear(); }
}
