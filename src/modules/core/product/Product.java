package modules.core.product;

public class Product implements Comparable<Product> {
    private final int id;
    private final String name;
    private final float price;
    private final String description;

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

    @Override
    public int compareTo(Product o) {
        return Integer.compare(this.getId(), o.getId());
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %d\nNOME: %s\nPREÇO: %.2f\nDESCRIÇÂO: %s",
            id,
            name,
            price,
            description
        );
    }
}
