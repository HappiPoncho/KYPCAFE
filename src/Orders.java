public class Orders {
    private Menu menu;
    private int qt;

    public Orders(Menu menu) {
        this.menu = menu;
        this.qt = 1;
    }

    public Menu getMenu() { return menu; }
    public int getQuantity() { return qt; }
    public void increment() { qt++; }

    public int getTotalPrice() {
        int price = Integer.parseInt(menu.getPrice().replace("₱", "").trim());
        return price * qt;
    }

    public String getFormattedTotal() { return "₱" + getTotalPrice(); }
}