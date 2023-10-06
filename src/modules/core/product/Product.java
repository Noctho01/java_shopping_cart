package modules.core.product;

public class Product implements Comparable<Product> {
    private int id;
    private String name;
    private float price;
    private String description;

    public Product(int id, String name, float price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(Product o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %d, NOME: %s, PREÇO: %.2f, DESCRIÇÂO: %s",
            id,
            name,
            price,
            description
        );
    }
}
