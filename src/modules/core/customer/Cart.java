package modules.core.customer;

import modules.core.product.Product;

import java.util.ArrayList;

public class Cart {
   private ArrayList<Product> products;

   public Cart(ArrayList<Product> products) {
      this.products = products;
   }

   public int getSize() {
      return products.size();
   }

   public float getCostTotal() {
      float cost = 0;

      for (Product product: products) {
         cost += product.getPrice();
      }

      return cost;
   }

   public void listProducts() {
      products.forEach(System.out::println);
   }

   public void addProduct(Product newProduct) {
      products.add(newProduct);
   }
}
