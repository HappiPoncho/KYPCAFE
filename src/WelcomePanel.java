import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JFrame {

    public WelcomePanel() {
        FlatMacLightLaf.setup();

        setTitle("KiPeYe Coffee");
        setSize(500, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        Color bg = new Color(250, 245, 230);
        Color brown = new Color(90, 60, 30);
        Color cream = new Color(255, 253, 208);
        getContentPane().setBackground(bg);

        // CENTER — shop name + subtitle
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(bg);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(60, 40, 20, 40));

        JLabel shopName = new JLabel("KiPeYe Coffee");
        shopName.setFont(new Font("Arial", Font.BOLD, 36));
        shopName.setForeground(brown);
        shopName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tagline = new JLabel("Veni, Vidi, Vici.");
        tagline.setFont(new Font("Arial", Font.ITALIC, 14));
        tagline.setForeground(Color.GRAY);
        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel prompt = new JLabel("How will you be dining today?");
        prompt.setFont(new Font("Arial", Font.PLAIN, 13));
        prompt.setForeground(new Color(100, 80, 60));
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(shopName);
        centerPanel.add(Box.createVerticalStrut(6));
        centerPanel.add(tagline);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(prompt);

        add(centerPanel, BorderLayout.CENTER);

        // BOTTOM — buttons
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 16, 0));
        btnPanel.setBackground(bg);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 60, 50, 60));

        JButton dineInBtn = makeButton("Dine In", brown, Color.WHITE);
        JButton takeOutBtn = makeButton("Take Out", cream, brown);

        dineInBtn.addActionListener(e -> proceed("Dine In"));
        takeOutBtn.addActionListener(e -> proceed("Take Out"));

        btnPanel.add(dineInBtn);
        btnPanel.add(takeOutBtn);

        add(btnPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JButton makeButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(0, 44));
        return btn;
    }

    private void proceed(String orderType) {
        dispose();
        SwingUtilities.invokeLater(() -> new Main().createUI(orderType));
    }
}