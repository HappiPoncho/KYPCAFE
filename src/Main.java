import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomePanel());
    }

    public void createUI(String orderType)  {

        FlatMacLightLaf.setup();

        JFrame frame = new JFrame("KPY COFFEE KIOSK");
        frame.setSize(900, 620);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        frame.setLayout(new BorderLayout(15, 15));
        frame.setResizable(false);

        Color bg = new Color(249, 248, 246);
        Color cream = new Color(239, 233, 227);
        frame.getContentPane().setBackground(bg);

        // PANELS
        JPanel menuPanel = new JPanel(new BorderLayout(0, 8));
        OrderManager orderManager = new OrderManager();
        OrderPanel orderPanel = new OrderPanel(orderManager, orderType);

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


        scrollPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent e) {
                int width = scrollPane.getViewport().getWidth();
                container.setPreferredSize(new Dimension(width, container.getPreferredSize().height));
                container.revalidate();
            }
        });

        // CATEGORIES with their own menu items
        Category[] categories = {
                new Category("ICED COFFEE", "rsrc/pHolder.png", new Menu[]{
                        new Menu("Iced Americano", "₱120", "rsrc/pHolder.png"),
                        new Menu("Iced Oat Milk Latte",  "₱165", "rsrc/pHolder.png"),
                        new Menu("Iced Caffe Latte",  "₱140", "rsrc/pHolder.png"),
                        new Menu("Iced Mocha Latte",   "₱140", "rsrc/pHolder.png"),
                        new Menu("Iced Hazelnut Latte",   "₱140", "rsrc/pHolder.png"),
                        new Menu("Iced Butterscotch Latte",   "₱140", "rsrc/pHolder.png"),
                        new Menu("Iced White Mocha Latte",   "₱140", "rsrc/pHolder.png"),
                        new Menu("Iced Spanish Latte",   "₱140", "rsrc/pHolder.png"),
                        new Menu("Iced Caramel Macchiato",   "₱140", "rsrc/pHolder.png"),
                        new Menu("Iced Salted Caramel",      "₱120", "rsrc/pHolder.png"),
                        new Menu("Iced Biscoff Latte",      "₱120", "rsrc/pHolder.png")
                }),
                new Category("HOT SELECTION", "rsrc/pHolder.png", new Menu[]{
                        new Menu("Americano","₱90","rsrc/pHolder.png"),
                        new Menu("Flat White", "₱150", "rsrc/pHolder.png"),
                        new Menu("Pistachio Cortado","₱200", "rsrc/pHolder.png"),
                        new Menu("Affogato", "₱200","rsrc/pHolder.png"),
                        new Menu("Caffe Latte", "₱200","rsrc/pHolder.png"),
                        new Menu("Cappucino", "₱200","rsrc/pHolder.png"),
                        new Menu("Caramel Macchiato", "₱200","rsrc/pHolder.png"),
                        new Menu("Signature Hot Choco",      "₱120", "rsrc/pHolder.png")
                }),
                new Category("FRAPPE", "rsrc/pHolder.png", new Menu[]{
                        new Menu("Oreo Frappe",   "₱80",  "rsrc/pHolder.png"),
                        new Menu("Choco Frappe", "₱110", "rsrc/pHolder.png"),
                        new Menu("Matcha Frappe",      "₱120", "rsrc/pHolder.png"),
                        new Menu("Java Chip Frappe",      "₱120", "rsrc/pHolder.png"),
                        new Menu("Cheesecake Frappe",      "₱120", "rsrc/pHolder.png"),
                        new Menu("Biscoff Frappe",      "₱120", "rsrc/pHolder.png"),
                        new Menu("Strawberry Frappe",      "₱120", "rsrc/pHolder.png")
                }),
                new Category("REFRESHERS", "rsrc/pHolder.png", new Menu[]{
                        new Menu("Iced Matcha",    "₱60", "rsrc/pHolder.png"),
                        new Menu("Strawberry Milk",      "₱120", "rsrc/pHolder.png"),
                        new Menu("Mango Ade",    "₱70", "rsrc/pHolder.png"),
                        new Menu("Strawberry Ade",      "₱120", "rsrc/pHolder.png"),
                        new Menu("PassionFruit Ade", "₱90", "rsrc/pHolder.png"),
                        new Menu("GreenAple Ade",    "₱70", "rsrc/pHolder.png"),
                        new Menu("Strawberry Ade",    "₱70", "rsrc/pHolder.png"),
                        new Menu("Lemonade",      "₱120", "rsrc/pHolder.png")
                }),
                new Category("SIDES", "rsrc/pHolder.png", new Menu[]{
                        new Menu("Hamburger", "₱65", "rsrc/pHolder.png"),
                        new Menu("Ham 'n Egg Sandwich",      "₱85", "rsrc/pHolder.png"),
                        new Menu("BLT Sandwich",      "₱75", "rsrc/pHolder.png"),
                        new Menu("Tuna Pesto",      "₱120", "rsrc/pHolder.png"),
                        new Menu("Chicken Alfredo",      "₱120", "rsrc/pHolder.png"),
                        new Menu("Italian Spaghetti",      "₱120", "rsrc/pHolder.png"),
                        new Menu("Fries Overload",      "₱120", "rsrc/pHolder.png"),
                        new Menu("Nachos Overload",      "₱120", "rsrc/pHolder.png")
                }),
                new Category("PASTRY", "rsrc/pHolder.png", new Menu[]{
                        new Menu("Croissants",  "₱70",  "rsrc/pHolder.png"),
                        new Menu("PY2",    "₱95",  "rsrc/pHolder.png"),
                        new Menu("PY3", "₱130", "rsrc/pHolder.png")
                })
        };

        Runnable[] loadCategory = { null };
        loadCategory[0] = () -> {};

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