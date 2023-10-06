package modules.services;

import modules.core.customer.Customer;
import modules.core.product.Product;

public final class AddProductOnCart {

   public static void execute(Customer customer, Product product) throws Exception {
      float customerBalance = customer.getBalance();
      float productPrice = product.getPrice();
      float totalCust = customer.getMyCart().getCostTotal();

      if (totalCust + productPrice > customerBalance) {
         throw new Exception("saldo insuficiente para adicionar produto ao carrinho");
      }

      customer.getMyCart().addProduct(product);
   }
}
