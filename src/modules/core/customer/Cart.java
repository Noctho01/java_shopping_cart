package modules.core.customer;

import modules.core.product.ProductStock;
import modules.core.product.StockManager;

import java.util.ArrayList;

public class Cart extends StockManager {

   public Cart(ArrayList<ProductStock> productsStock) {
      super(productsStock);
   }

   public int getSize() {
      int total = 0;

      for (ProductStock productStock : productStocks)
         total += productStock.getQuantity();

      return total;
   }

   public void printCart() {
      System.out.printf("%-4s %-18s %10s %10s %10s%n", "ID", "NOME", "QUANTIDADE", "PREÇO P/U", "PREÇO T");

      for (ProductStock productStock: productStocks) {
         var product = productStock.getProduct();
         var quantity = productStock.getQuantity();

         System.out.printf("%-4s %-18s %10d %10.2f %10.2f%s",
                 product.getId() < 10 ? "0" + product.getId() : product.getId(),
                 product.getName(),
                 quantity,
                 product.getPrice(),
                 productStock.getCostTotal(),
                 productStocks.indexOf(productStock) == productStocks.size() - 1 ? "\n\n" : "\n"
         );
      }
   }
}
