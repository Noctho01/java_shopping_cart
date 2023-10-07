package modules.core.product;

import java.util.ArrayList;
import java.util.Optional;

public class StockManager {
   protected final ArrayList<ProductStock> productStocks;
   public StockManager(ArrayList<ProductStock> productStocks1) {
      this.productStocks = productStocks1;
   }

   public ProductStock findById(int productId) throws Exception {
      Optional<ProductStock> optionalProductStock = productStocks
              .stream()
              .filter(product -> product.getProduct().getId() == productId)
              .findFirst();

      if (optionalProductStock.isEmpty()) {
         throw new Exception("produto não encontrado");
      }

      return optionalProductStock.get();
   }
   public float getCostTotal() {
      float cost = 0;

      for (ProductStock productStock: productStocks)
         cost += productStock.getCostTotal();

      return cost;
   }

   public void addNewProduct(ProductStock productStock) throws Exception {
      Optional<ProductStock> optionalProductStock = productStocks
              .stream()
              .filter(productStock1 -> productStock1.getProduct().compareTo(productStock.getProduct()) == 0)
              .findFirst();

      if (optionalProductStock.isPresent()) {
         int quantity = productStock.getQuantity();
         optionalProductStock.get().more(quantity);

      } else {
         productStocks.add(productStock);
         productStocks.sort(null);
      }
   }

   public void moreStock(int productId, int value) throws Exception {
      var productStock = this.findById(productId);
      productStock.more(value);
   }

   public void lessStock(int productId, int value) throws Exception {
      var productStock = this.findById(productId);
      productStock.less(value);

      if (productStock.getQuantity() == 0)
         productStocks.remove(productStock);
   }
}
