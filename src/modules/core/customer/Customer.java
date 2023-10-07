package modules.core.customer;

public class Customer {
   private final String name;
   private final Cart myCart;
   private float balance;

   public Customer(String name, float balance, Cart cart) {
      this.name = name;
      this.balance = balance;
      this.myCart = cart;
   }

   public float getBalance() {
      return balance;
   }

   public float getEstimatedBalance() {
      return balance - myCart.getCostTotal();
   }

   public Cart getMyCart() {
      return myCart;
   }

   @Override
   public String toString() {
      return String.format(
              "\nNOME: %s\nSALDO: %.2f\nSALDO ESTIMADO: %.2f\nPRODUTOS NO CARRINHO: %d\n",
              name,
              balance,
              getEstimatedBalance(),
              myCart.getSize()
      );
   }

   public void payProductsOnCart() throws Exception {
      if (myCart.getCostTotal() > balance)
         throw new Exception("saldo insuficiente para completar o pagamento");

      balance -= myCart.getCostTotal();
   }
}
