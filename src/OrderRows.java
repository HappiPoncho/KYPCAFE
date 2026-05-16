import javax.swing.*;
import java.awt.*;

public class OrderRows extends JPanel {
    public OrderRows(Orders item, OrderManager orderManager) {
        setLayout(new BorderLayout(5, 0));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)
        ));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JLabel lblName = new JLabel(item.getMenu().getName() + " x" + item.getQuantity());
        lblName.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel lblPrice = new JLabel(item.getFormattedTotal());
        lblPrice.setFont(new Font("Arial", Font.BOLD, 12));
        lblPrice.setForeground(new Color(80, 80, 80));

        JButton btnRemove = new JButton("X");
        btnRemove.setFont(new Font("Arial", Font.BOLD, 10));
        btnRemove.setForeground(Color.RED);
        btnRemove.setBorderPainted(false);
        btnRemove.setContentAreaFilled(false);
        btnRemove.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRemove.addActionListener(e -> orderManager.removeItem(item));

        add(lblName, BorderLayout.CENTER);
        add(lblPrice, BorderLayout.WEST);
        add(btnRemove, BorderLayout.EAST);
    }
}