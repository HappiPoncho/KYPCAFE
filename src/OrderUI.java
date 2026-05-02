import javax.swing.*;
import java.awt.*;

public class OrderUI extends JPanel {
    private OrderManager orderManager;
    private JPanel itemsPanel;
    private JLabel totalLabel;

    private static final Color CREAM = new Color(255, 253, 208);

    public OrderUI(OrderManager orderManager) {
        this.orderManager = orderManager;
        setLayout(new BorderLayout(0, 10));
        setBackground(CREAM);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title
        JLabel title = new JLabel("Your Order");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        add(title, BorderLayout.NORTH);

        // Items list
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(CREAM);

        JScrollPane scroll = new JScrollPane(itemsPanel);
        scroll.setBorder(null);
        scroll.setBackground(CREAM);
        add(scroll, BorderLayout.CENTER);

        // Total
        totalLabel = new JLabel("Total: ₱0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        add(totalLabel, BorderLayout.SOUTH);

        // Listen for order changes
        orderManager.addChangeListener(this::refresh);
    }

    private void refresh() {
        itemsPanel.removeAll();

        for (Orders item : orderManager.getOrderItems()) {
            itemsPanel.add(new OrderRows(item, orderManager));
            itemsPanel.add(Box.createVerticalStrut(5));
        }

        totalLabel.setText("Total: ₱" + orderManager.getTotal());
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }
}