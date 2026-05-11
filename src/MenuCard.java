import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuCard extends JPanel {

    public MenuCard(Menu item, OrderManager orderManager) {  // <-- add OrderManager param
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 200));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true));

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon icon = new ImageIcon(item.getImagePath());
        Image img = icon.getImage().getScaledInstance(140, 100, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
        add(imageLabel, BorderLayout.CENTER);

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setBackground(Color.WHITE);
        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel priceLabel = new JLabel(item.getPrice());
        priceLabel.setForeground(Color.GRAY);
        textPanel.add(nameLabel);
        textPanel.add(priceLabel);
        add(textPanel, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                orderManager.addItem(item);
            }
            public void mouseEntered(MouseEvent e) { setBackground(new Color(230, 230, 230)); }
            public void mouseExited(MouseEvent e) { setBackground(Color.WHITE); }
        });
    }
}