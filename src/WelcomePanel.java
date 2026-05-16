import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class WelcomePanel extends JFrame {

    public WelcomePanel() {

        FlatMacLightLaf.setup();

        setTitle("KiPeYe Coffee");
        setSize(500, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel bg = new JPanel(new BorderLayout()) {

            BufferedImage logo;

            {
                try {
                    logo = ImageIO.read(new File("rsrc/logoFIN.png"));
                } catch (IOException e) {
                    logo = null;
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (logo != null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.15f));

                    int size = Math.min(getWidth(), getHeight());
                    int x = (getWidth() - size) / 2;
                    int y = (getHeight() - size) / 2;

                    g2d.drawImage(logo, x, y, size, size, this);
                    g2d.dispose();
                }
            }
        };

        bg.setBackground(new Color(250, 245, 230));
        setContentPane(bg);

        Color brown = new Color(90, 60, 30);
        Color cream = new Color(255, 253, 208);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(60, 40, 20, 40));

        JLabel shopName = new JLabel("KiPeYe Coffee");
        shopName.setFont(Fonts.of(Font.BOLD, 80f));
        shopName.setForeground(brown);
        shopName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tagline = new JLabel("Good coffee, great vibes.");
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

        bg.add(centerPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 16, 0));
        btnPanel.setOpaque(false);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 60, 50, 60));

        JButton dineInBtn  = makeButton("Dine In",  brown, Color.WHITE);
        JButton takeOutBtn = makeButton("Take Out", cream, brown);

        dineInBtn.addActionListener(e  -> proceed("Dine In"));
        takeOutBtn.addActionListener(e -> proceed("Take Out"));

        btnPanel.add(dineInBtn);
        btnPanel.add(takeOutBtn);

        bg.add(btnPanel, BorderLayout.SOUTH);

        JPanel upPanel = new JPanel(new BorderLayout());
        upPanel.setOpaque(false);
        upPanel.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        JButton exitBtn = makeButton("X", Color.red, Color.WHITE);
        exitBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        });

        upPanel.add(exitBtn, BorderLayout.EAST);

        /*
        JButton adminBtn = makeIconButton("rsrc/icons and fonts/settings.png");
        adminBtn.addActionListener(e -> {
            System.out.println("sd");
        });

        upPanel.add(adminBtn, BorderLayout.WEST);*/

        bg.add(upPanel, BorderLayout.NORTH);

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
        return btn;
    }

    /*private JButton makeIconButton(String path) {
        JButton btn = new JButton();

        try {
            BufferedImage img = ImageIO.read(new File(path));
            if (img != null) {
                Image scaled = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
                btn.setIcon(new ImageIcon(scaled));
            } else {
                btn.setText("?");
            }
        } catch (IOException e) {
            btn.setText("?");
        }

        btn.setPreferredSize(new Dimension(45, 35));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return btn;
    }*/

    private void proceed(String orderType) {
        dispose();
        SwingUtilities.invokeLater(() -> new Main().createUI(orderType));
    }
}