package modules.core.customer;

public class Customer {
   private String name;
   private float balance;
   private Cart myCart;

   public Customer(String name, float balance, Cart cart) {
      this.name = name;
      this.balance = balance;
      this.myCart = cart;
   }

   public String getName() {
      return name;
   }

   public float getBalance() {
      return balance;
   }

   public Cart getMyCart() {
      return myCart;
   }

   public void payProductsOnCart() throws Exception {
      if (myCart.getCostTotal() > balance) {
         throw new Exception("saldo insuficiente para completar o pagamento");
      }

      balance -= myCart.getCostTotal();
      System.out.println("Pagamento feito com sucesso");
   }
}
