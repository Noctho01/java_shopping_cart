package modules.core.product;

public class ProductStock implements Comparable<ProductStock> {
   private final Product product;
   private int quantity;

   public ProductStock(Product product, int quantity) {
      this.product = product;
      this.quantity = quantity;
   }

   public Product getProduct() {
      return product;
   }

   public int getQuantity() {
      return quantity;
   }

   public float getCostTotal() {
      return product.getPrice() * quantity;
   }

   public void more(int value) throws Exception {
      if (value <= 0)
         throw new Exception("informe um valor maior que 0");

      quantity += value;
   }

   public void less(int value) throws Exception {
      if (quantity == 0)
         throw new Exception("produto indisponivel");

      if (quantity - value < 0)
         throw new Exception("quantidade indisponivel");

      quantity -= value;
   }

   @Override
   public int compareTo(ProductStock o) {
      return Integer.compare(getProduct().getId(), o.getProduct().getId());
   }
}
