import modules.core.customer.Cart;
import modules.core.customer.Customer;
import modules.core.product.Catalog;
import modules.core.product.Product;
import modules.core.product.ProductStock;
import modules.services.CartService;
import utils.ConsoleLog;

import java.util.ArrayList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        final var productsToCatalog = new ArrayList<ProductStock>();

        // Criando os produtos do catalogo
        productsToCatalog.add(new ProductStock(new Product(1, "Armario", 450.50F, "Armario de cozinha, 7 portas e 2 gavetas"), 5));
        productsToCatalog.add(new ProductStock(new Product(2, "Guarda Roupa", 799.75F, "Guarda roupa de casal, 4 portas e 6 gavetas"), 8));
        productsToCatalog.add(new ProductStock(new Product(3, "Mesa de Vidro", 850, "Mesa de vidro, 8 cadeiras"), 6));
        productsToCatalog.add(new ProductStock(new Product(4, "Mesa de Marmore", 985, "Mesa de marmore, 8 cadeiras"), 4));
        productsToCatalog.add(new ProductStock(new Product(5, "Sofá", 1250.89F, "Sofá rosa, grande, 4 almofadas"), 7));
        productsToCatalog.add(new ProductStock(new Product(6, "Sofá Retrátil", 1900, "Sofá retrátil preto, grande, 4 almofadas"), 3));
        productsToCatalog.add(new ProductStock(new Product(7, "Cama de Solteiro", 1080.99F, "Cama de solteiro, box, travesseiro incluso"), 8));
        productsToCatalog.add(new ProductStock(new Product(8, "Cama de Casal", 1850.89F, "Cama de casal, box, 2 travesseiros inclusos"), 2));

        // Instanciando catalogo
        final var catalogo = new Catalog(productsToCatalog);

        // instanciando scanner para captura do System.in
        Scanner scanner = new Scanner(System.in);

        // Criando cliente
        System.out.println("Cadastrando cliente");

        System.out.print("Nome: ");
        String customerName = scanner.nextLine();

        System.out.print("Saldo inicial: ");
        float customerBalance = scanner.nextFloat();

        final var customer = new Customer(
            customerName,
            customerBalance,
            new Cart(new ArrayList<>())
        );

        // Iniciando fluxo
        int option = 0;

        while (option != 5 && option != 8) {
            switch (option) {
                case 0:
                    System.out.println(customer);
                    System.out.println("""
                    ** MENU **
                    1 - ver catalogo
                    2 - ver carrinho
                    5 - sair
                    """);
                    System.out.print("escolha: ");
                    option = scanner.nextInt();
                    break;

                case 1:
                    ConsoleLog.clear();
                    option = 0;
                    while(option != 3) {
                        try {
                            switch (option) {
                                case 0:
                                    System.out.println(customer);
                                    catalogo.printCatalog();
                                    System.out.println("""
                                    ** MENU **
                                    1 - ver descrição do produto
                                    2 - adicionar produto ao carrinho
                                    3 - voltar
                                    """);
                                    System.out.print("escolha: ");
                                    option = scanner.nextInt();
                                    if (option > 2) ConsoleLog.clear();
                                    break;

                                case 1:
                                    System.out.print("id do produto: ");
                                    final int productIdCase1 = scanner.nextInt();
                                    ConsoleLog.clear();
                                    catalogo.printProductStock(productIdCase1);
                                    option = 0;
                                    break;

                                case 2:
                                    System.out.print("id do produto: ");
                                    final int productIdCase2 = scanner.nextInt();
                                    System.out.print("quantidade: ");
                                    final int productQuantity = scanner.nextInt();
                                    ConsoleLog.clear();
                                    var productStock = catalogo.findById(productIdCase2);
                                    CartService.add(customer, productStock, productQuantity);
                                    System.out.println("\nProduto adicionado ao seu carrinho\n");
                                    option = 0;
                                    break;

                                default:
                                    ConsoleLog.clear();
                                    option = 0;
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage() + "\n");
                            option = 0;
                        }
                    }
                    option = 0;
                    break;

                case 2:
                    ConsoleLog.clear();
                    option = 0;

                    while (option != 3 && option!= 8) {
                        try {
                            switch (option) {

                                case 0:
                                    if (customer.getMyCart().getSize() == 0) {
                                        System.out.println("\nCarrinho vazio\n");
                                        option = 3;
                                        break;
                                    }
                                    System.out.println(customer);
                                    customer.getMyCart().printCart();
                                    System.out.println("""
                                    ** MENU **
                                    1 - pagar produtos
                                    2 - remover produto
                                    3 - voltar
                                    """);
                                    System.out.print("escolha: ");
                                    option = scanner.nextInt();
                                    if (option > 2) ConsoleLog.clear();
                                    break;

                                case 1:
                                    System.out.println("pagando produto");
                                    customer.payProductsOnCart();
                                    ConsoleLog.clear();
                                    System.out.println("Pagamento feito com sucesso");
                                    option = 8;
                                    break;

                                case 2:
                                    System.out.print("id do produto: ");
                                    final int productId = scanner.nextInt();
                                    System.out.print("quantidade: ");
                                    final int productQuantity = scanner.nextInt();
                                    ConsoleLog.clear();
                                    var customerCart = customer.getMyCart();
                                    CartService.remove(customerCart, catalogo, productId, productQuantity);
                                    System.out.println("\nProduto removido do carrinho\n");
                                    option = 0;
                                    break;

                                default:
                                    ConsoleLog.clear();
                                    option = 0;
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage() + "\n");
                            option = 0;
                        }
                    }
                    option = option == 8 ? 8 : 0;
                    break;

                default:
                    option = 0;
                    break;
            }
        }

        scanner.close();

        if (option != 8) ConsoleLog.clear();
        System.out.println("Saindo...");
    }
}