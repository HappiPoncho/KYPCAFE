import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createUI());
    }

    public void createUI() {

        JFrame frame = new JFrame("KiPeYe Coffee");
        frame.setSize(900, 620);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        frame.setLayout(new BorderLayout(15, 15));

        Color bg = new Color(250, 245, 230);
        Color cream = new Color(255, 253, 208);
        frame.getContentPane().setBackground(bg);

        // PANELS
        JPanel menuPanel = new JPanel(new BorderLayout(0, 8));
        OrderManager orderManager = new OrderManager();
        OrderPanel orderPanel = new OrderPanel(orderManager);

        menuPanel.setPreferredSize(new Dimension(580, 0));
        orderPanel.setPreferredSize(new Dimension(280, 0));
        menuPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        orderPanel.setBorder(BorderFactory.createEtchedBorder());
        menuPanel.setBackground(cream);

        frame.add(menuPanel, BorderLayout.CENTER);
        frame.add(orderPanel, BorderLayout.EAST);

        // MENU ITEM CONTAINER
        JPanel container = new JPanel(new GridLayout(0, 3, 10, 10));
        container.setBackground(cream);
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // no bottom scroll
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

// Force container width to match the scroll pane's viewport
        scrollPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent e) {
                int width = scrollPane.getViewport().getWidth();
                container.setPreferredSize(new Dimension(width, container.getPreferredSize().height));
                container.revalidate();
            }
        });

        // CATEGORIES with their own menu items
        Category[] categories = {
                new Category("HOT COFFEE", "rsrc/pHolder.png", new Menu[]{
                        new Menu("IC1", "₱120", "rsrc/pHolder.png"),
                        new Menu("IC2",  "₱135", "rsrc/pHolder.png"),
                        new Menu("IC3",  "₱180", "rsrc/pHolder.png"),
                        new Menu("IC4",   "₱140", "rsrc/pHolder.png")
                }),
                new Category("HOT COFFEE", "rsrc/pHolder.png", new Menu[]{
                        new Menu("HC1",   "₱90",  "rsrc/pHolder.png"),
                        new Menu("HC2", "₱150", "rsrc/pHolder.png"),
                        new Menu("HC3",   "₱200", "rsrc/pHolder.png")
                }),
                new Category("FRAPEE", "rsrc/pHolder.png", new Menu[]{
                        new Menu("FR1",   "₱80",  "rsrc/pHolder.png"),
                        new Menu("FR2", "₱110", "rsrc/pHolder.png"),
                        new Menu("FR3",      "₱120", "rsrc/pHolder.png")
                }),
                new Category("OVER ICED", "rsrc/pHolder.png", new Menu[]{
                        new Menu("OI1",    "₱60", "rsrc/pHolder.png"),
                        new Menu("OI2",    "₱70", "rsrc/pHolder.png"),
                        new Menu("OI3", "₱90", "rsrc/pHolder.png")
                }),
                new Category("SNACKS", "rsrc/pHolder.png", new Menu[]{
                        new Menu("SN1", "₱65", "rsrc/pHolder.png"),
                        new Menu("SN2",      "₱85", "rsrc/pHolder.png"),
                        new Menu("SN3",      "₱75", "rsrc/pHolder.png")
                }),
                new Category("PASTRY", "rsrc/pHolder.png", new Menu[]{
                        new Menu("PY1",  "₱70",  "rsrc/pHolder.png"),
                        new Menu("PY2",    "₱95",  "rsrc/pHolder.png"),
                        new Menu("PY3", "₱130", "rsrc/pHolder.png")
                })
        };

        // Helper to load cards from a category
        Runnable[] loadCategory = { null };
        loadCategory[0] = () -> {}; // placeholder

        CategoryPanel categoryPanel = new CategoryPanel(categories, selected -> {
            container.removeAll();
            for (Menu item : selected.getItems()) {
                container.add(new MenuCard(item, orderManager));
            }
            container.revalidate();
            container.repaint();
        });

        // Load first category by default
        for (Menu item : categories[0].getItems()) {
            container.add(new MenuCard(item, orderManager));
        }

        // NORTH: title + category buttons
        JPanel northPanel = new JPanel(new BorderLayout(0, 8));
        northPanel.setBackground(cream);
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JLabel title = new JLabel("KiPeYe Coffee");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        northPanel.add(title, BorderLayout.NORTH);
        northPanel.add(categoryPanel, BorderLayout.SOUTH);

        menuPanel.add(northPanel, BorderLayout.NORTH);
        menuPanel.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}