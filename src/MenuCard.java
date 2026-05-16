import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuCard extends JPanel {

    public MenuCard(Menu item, OrderManager orderManager) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 200));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true));

        JLabel lblImage = new JLabel();
        lblImage.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon icon = new ImageIcon(item.getImagePath());
        Image pic = icon.getImage().getScaledInstance(140, 100, Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(pic));
        add(lblImage, BorderLayout.CENTER);

        JPanel pnlName = new JPanel(new GridLayout(2, 1));
        pnlName.setBackground(Color.WHITE);
        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        JLabel priceLabel = new JLabel(item.getPrice());
        priceLabel.setForeground(Color.GRAY);
        pnlName.add(nameLabel);
        pnlName.add(priceLabel);
        add(pnlName, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                orderManager.addItem(item);
            }
            public void mouseEntered(MouseEvent e) { setBackground(new Color(230, 230, 230)); }
            public void mouseExited(MouseEvent e) { setBackground(Color.WHITE); }
        });
    }
}