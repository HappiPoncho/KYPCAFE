import javax.swing.*;
import java.awt.*;

public class OrderUI extends JPanel {
    private OrderManager orderManager;
    private JPanel pnlItems;
    private JLabel lblTotal;

    private static final Color CREAM = new Color(255, 253, 208);

    public OrderUI(OrderManager orderManager) {
        this.orderManager = orderManager;
        setLayout(new BorderLayout(0, 10));
        setBackground(CREAM);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitle = new JLabel("Your Order");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        add(lblTitle, BorderLayout.NORTH);

        pnlItems = new JPanel();
        pnlItems.setLayout(new BoxLayout(pnlItems, BoxLayout.Y_AXIS));
        pnlItems.setBackground(CREAM);

        JScrollPane scroll = new JScrollPane(pnlItems);
        scroll.setBorder(null);
        scroll.setBackground(CREAM);
        add(scroll, BorderLayout.CENTER);

        lblTotal = new JLabel("Total: ₱0");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        lblTotal.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        add(lblTotal, BorderLayout.SOUTH);

        orderManager.addChangeListener(this::refresh);
    }

    private void refresh() {
        pnlItems.removeAll();

        for (Orders item : orderManager.getOrderItems()) {
            pnlItems.add(new OrderRows(item, orderManager));
            pnlItems.add(Box.createVerticalStrut(5));
        }

        lblTotal.setText("Total: ₱" + orderManager.getTotal());
        pnlItems.revalidate();
        pnlItems.repaint();
    }
}