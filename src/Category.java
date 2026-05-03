public class Category {
    private String name;
    private String imagePath;
    private Menu[] items;

    public Category(String name, String imagePath, Menu[] items) {
        this.name = name;
        this.imagePath = imagePath;
        this.items = items;
    }

    public String getName() { return name; }
    public String getImagePath() { return imagePath; }
    public Menu[] getItems() { return items; }
}