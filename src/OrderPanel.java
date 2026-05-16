import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OrderPanel extends JPanel {
    private OrderManager orderManager;
    private JPanel pnlItem;
    private JLabel lblTotal;
    private String orderType;

    private static final Color CREAM = new Color(255, 253, 208);

    public OrderPanel(OrderManager orderManager,String orderType) {
        this.orderManager = orderManager;
        this.orderType = orderType;
        setLayout(new BorderLayout(0, 10));
        setBackground(CREAM);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel title = new JLabel("Your Order");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
        add(title, BorderLayout.NORTH);

        pnlItem = new JPanel();
        pnlItem.setLayout(new BoxLayout(pnlItem, BoxLayout.Y_AXIS));
        pnlItem.setBackground(CREAM);

        JScrollPane scroll = new JScrollPane(pnlItem);
        scroll.setBorder(null);
        scroll.setBackground(CREAM);
        add(scroll, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(0, 8));
        bottomPanel.setBackground(CREAM);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));

        lblTotal = new JLabel("Total: ₱0");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));

        JButton checkoutBtn = new JButton("Checkout");
        checkoutBtn.setFont(new Font("Arial", Font.BOLD, 13));
        checkoutBtn.setBackground(new Color(90, 60, 30));
        checkoutBtn.setForeground(Color.WHITE);
        checkoutBtn.setFocusPainted(false);
        checkoutBtn.setBorderPainted(false);
        checkoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkoutBtn.setPreferredSize(new Dimension(0, 38));
        checkoutBtn.addActionListener(e -> {
            if (orderManager.getOrderItems().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No items in order!", "Checkout", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Total: ₱" + orderManager.getTotal() + "\nProceed to checkout?",
                    "Checkout", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                List<Orders> snapshot = new ArrayList<>(orderManager.getOrderItems());
                int currentTotal = orderManager.getTotal();

                orderManager.getOrderItems().clear();
                orderManager.notifyListenCheckout();

                new Receipt((JFrame) SwingUtilities.getWindowAncestor(this), snapshot, currentTotal, orderType);
            }
        });

        bottomPanel.add(lblTotal, BorderLayout.NORTH);
        bottomPanel.add(checkoutBtn, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        orderManager.addChangeListener(this::refresh);
    }

    private void refresh() {
        pnlItem.removeAll();

        for (Orders item : orderManager.getOrderItems()) {
            pnlItem.add(new OrderRows(item, orderManager));
            pnlItem.add(Box.createVerticalStrut(5));
        }

        lblTotal.setText("Total: ₱" + orderManager.getTotal());
        pnlItem.revalidate();
        pnlItem.repaint();
    }
}