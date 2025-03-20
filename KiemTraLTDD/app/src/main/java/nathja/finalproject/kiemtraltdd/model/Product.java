package nathja.finalproject.kiemtraltdd.model;

public class Product {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private String imageUrl;
    private Category category;

    public Product(int id, String name, String imageUrl, Category category) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
    }
}
