public class Orders {
    private Menu menu;
    private int quantity;

    public Orders(Menu menu) {
        this.menu = menu;
        this.quantity = 1;
    }

    public Menu getMenu() { return menu; }
    public int getQuantity() { return quantity; }
    public void increment() { quantity++; }

    public int getTotalPrice() {
        int price = Integer.parseInt(menu.getPrice().replace("₱", "").trim());
        return price * quantity;
    }

    public String getFormattedTotal() { return "₱" + getTotalPrice(); }
}