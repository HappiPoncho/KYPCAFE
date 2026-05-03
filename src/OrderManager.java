import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Orders> orderItems = new ArrayList<>();
    private List<Runnable> listeners = new ArrayList<>();

    public void addItem(Menu menu) {
        for (Orders item : orderItems) {
            if (item.getMenu().getName().equals(menu.getName())) {
                item.increment();
                notifyListeners();
                return;
            }
        }
        orderItems.add(new Orders(menu));
        notifyListeners();
    }

    public void removeItem(Orders item) {
        orderItems.remove(item);
        notifyListeners();
    }

    public void notifyListenCheckout() { listeners.forEach(Runnable::run); }

    public List<Orders> getOrderItems() { return orderItems; }

    public int getTotal() {
        return orderItems.stream().mapToInt(Orders::getTotalPrice).sum();
    }

    public void addChangeListener(Runnable listener) { listeners.add(listener); }

    private void notifyListeners() { listeners.forEach(Runnable::run); }
}