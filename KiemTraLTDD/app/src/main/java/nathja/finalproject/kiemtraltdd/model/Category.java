package nathja.finalproject.kiemtraltdd.model;

public class Category {
    private int id;
    private String name;
    private String images;
    private String description;
    public Category(String name, String images, String description) {
        this.name = name;
        this.images = images;
        this.description = description;
    }
//getter and setter
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
