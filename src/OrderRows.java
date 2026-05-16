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

        JLabel nameLabel = new JLabel(item.getMenu().getName() + " x" + item.getQuantity());
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel priceLabel = new JLabel(item.getFormattedTotal());
        priceLabel.setFont(new Font("Arial", Font.BOLD, 12));
        priceLabel.setForeground(new Color(80, 80, 80));

        JButton removeBtn = new JButton("X");
        removeBtn.setFont(new Font("Arial", Font.BOLD, 10));
        removeBtn.setForeground(Color.RED);
        removeBtn.setBorderPainted(false);
        removeBtn.setContentAreaFilled(false);
        removeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        removeBtn.addActionListener(e -> orderManager.removeItem(item));

        add(nameLabel, BorderLayout.CENTER);
        add(priceLabel, BorderLayout.WEST);
        add(removeBtn, BorderLayout.EAST);
    }
}