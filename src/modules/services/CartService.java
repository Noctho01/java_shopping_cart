package modules.services;

import modules.core.customer.Cart;
import modules.core.customer.Customer;
import modules.core.product.Catalog;
import modules.core.product.ProductStock;

public final class CartService {

   public static void add(Customer customer, ProductStock productCatalogStock, int quantity) throws Exception {
      float customerBalance = customer.getBalance();
      float productPrice = productCatalogStock.getProduct().getPrice();
      float costTotal = customer.getMyCart().getCostTotal();

      if ((productPrice * quantity) + costTotal > customerBalance)
         throw new Exception("saldo insuficiente para adicionar produto ao carrinho");

      var productOfCatalog = productCatalogStock.getProduct();
      var productStockToCart = new ProductStock(productOfCatalog, quantity);

      productCatalogStock.less(quantity);

      try {
         customer.getMyCart().addNewProduct(productStockToCart);
      } catch (Exception e) {
         productCatalogStock.more(quantity);
         throw e;
      }

   }

   public static void remove(Cart customerCart, Catalog catalog, int productId, int quantity) throws Exception {
      customerCart.lessStock(productId, quantity);
      catalog.moreStock(productId, quantity);
   }
}
