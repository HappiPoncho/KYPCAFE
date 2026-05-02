public class Menu {
    private String name;
    private String price;
    private String imagePath;

    public Menu (String name, String price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getImagePath() { return imagePath; }
}