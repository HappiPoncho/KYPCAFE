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
        setLayout(new BorderLayout());
        setIconImage(null);

        JPanel pnlBg = new JPanel(new BorderLayout()) {

            BufferedImage imgWallpaper;
            BufferedImage imglogo;

            {
                try {
                    imgWallpaper = ImageIO.read(new File("rsrc/icons and fonts/wallpaper.png"));
                } catch (IOException e) {
                    imgWallpaper = null;
                }

                try {
                    imglogo = ImageIO.read(new File("rsrc/icons and fonts/llogo.png"));
                } catch (IOException e) {
                    imglogo = null;
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                if (imgWallpaper != null) {
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.45f));
                    g2d.drawImage(imgWallpaper, 0, 0, getWidth(), getHeight(), this);
                }

                if (imglogo != null) {
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.15f));
                    int size = Math.min(getWidth(), getHeight());
                    int x = (getWidth() - size) / 2;
                    int y = (getHeight() - size) / 2;
                    g2d.drawImage(imglogo, x, y, size, size, this);
                }

                g2d.dispose();
            }
        };

        pnlBg.setBackground(new Color(250, 245, 230));
        setContentPane(pnlBg);

        Color clrBrown = new Color(90, 60, 30);
        Color clrCream = new Color(255, 253, 208);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(60, 40, 20, 40));

        JLabel shopName = new JLabel("KiPeYe Coffee");
        shopName.setFont(Fonts.of(Font.BOLD, 80f));
        shopName.setForeground(clrBrown);
        shopName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel tagline = new JLabel("one cup can fill you up");
        tagline.setFont(new Font("Arial", Font.ITALIC, 14));
        tagline.setForeground(Color.BLACK);
        tagline.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel prompt = new JLabel("How will you be dining today?");
        prompt.setFont(new Font("Arial", Font.PLAIN, 13));
        prompt.setForeground(Color.BLACK);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(shopName);
        centerPanel.add(Box.createVerticalStrut(6));
        centerPanel.add(tagline);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(prompt);

        pnlBg.add(centerPanel, BorderLayout.CENTER);

        JPanel pnlButtons = new JPanel(new GridLayout(1, 2, 16, 0));
        pnlButtons.setOpaque(false);
        pnlButtons.setBorder(BorderFactory.createEmptyBorder(10, 60, 50, 60));

        JButton btnDine = makeButton("Dine In",  clrBrown, Color.WHITE);
        JButton btnToGo = makeButton("Take Out", clrCream, clrBrown);

        btnDine.addActionListener(e  -> proceed("Dine In"));
        btnToGo.addActionListener(e -> proceed("Take Out"));

        pnlButtons.add(btnDine);
        pnlButtons.add(btnToGo);

        pnlBg.add(pnlButtons, BorderLayout.SOUTH);

        JPanel pnlUp = new JPanel(new BorderLayout());
        pnlUp.setOpaque(false);
        pnlUp.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        JButton btnExit = makeButton("X", Color.red, Color.WHITE);
        btnExit.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) System.exit(0);
        });

        pnlUp.add(btnExit, BorderLayout.EAST);
        pnlBg.add(pnlUp, BorderLayout.NORTH);

        setVisible(true);
    }

    private JButton makeButton(String text, Color bg, Color fg) {
        JButton parambtn = new JButton(text);
        parambtn.setFont(new Font("Arial", Font.BOLD, 14));
        parambtn.setBackground(bg);
        parambtn.setForeground(fg);
        parambtn.setFocusPainted(false);
        parambtn.setBorderPainted(false);
        parambtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return parambtn;
    }



    private void proceed(String orderType) {
        dispose();
        SwingUtilities.invokeLater(() -> new Main().createUI(orderType));
    }
}