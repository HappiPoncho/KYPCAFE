import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CategoryBtn extends JPanel {
    private static final Color DEFAULT_BG = Color.WHITE;
    private static final Color SELECTED_BG = new Color(200, 180, 140);
    private static final Color HOVER_BG = new Color(230, 220, 200);

    private boolean selected = false;

    public CategoryBtn(Category category, Runnable onSelect) {
        setLayout(new BorderLayout(0, 4));
        setBackground(DEFAULT_BG);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(8, 6, 8, 6)
        ));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // IMAGE
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon icon = new ImageIcon(category.getImagePath());
        Image img = icon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));

        // LABEL
        JLabel nameLabel = new JLabel(category.getName(), JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 11));

        add(imageLabel, BorderLayout.CENTER);
        add(nameLabel, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { onSelect.run(); }
            public void mouseEntered(MouseEvent e) { if (!selected) setBackground(HOVER_BG); }
            public void mouseExited(MouseEvent e)  { if (!selected) setBackground(DEFAULT_BG); }
        });
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        setBackground(selected ? SELECTED_BG : DEFAULT_BG);
    }
}