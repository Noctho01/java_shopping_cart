package modules.core.product;

import java.util.ArrayList;

public class Catalog extends StockManager{

   public Catalog(ArrayList<ProductStock> productStocks) {
      super(productStocks);
   }

   public void printProductStock(int productId) throws Exception {
      var productStock = this.findById(productId);
      System.out.printf(
         "\n%s\nEM ESTOQUE: %d\n%n",
         productStock.getProduct().toString(),
         productStock.getQuantity()
      );
   }

   public void printCatalog() {
      System.out.printf("%-4s %-18s %10s %10s%n", "ID", "NOME", "ESTOQUE", "PREÇO");

      for (ProductStock productStock: productStocks) {
         var product = productStock.getProduct();
         var quantity = productStock.getQuantity();

         System.out.printf(
           "%-4s %-18s %10d %10.2f%s",
           product.getId() < 10 ? "0" + product.getId() : product.getId(),
           product.getName(),
           quantity,
           product.getPrice(),
           productStocks.indexOf(productStock) == productStocks.size() - 1 ? "\n\n" : "\n"
         );
      }
   }
}
