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
        frame.setLocationRelativeTo(null);
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
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JButton backBtn = new JButton("← Back");
        backBtn.setFont(new Font("Arial", Font.BOLD, 12));
        backBtn.setBackground(new Color(90, 60, 30));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setBorderPainted(false);
        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Going back will clear your current order. Continue?",
                    "Go Back",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
                SwingUtilities.invokeLater(() -> new WelcomePanel());
            }
        });


        scrollPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent e) {
                int width = scrollPane.getViewport().getWidth();
                container.setPreferredSize(new Dimension(width, container.getPreferredSize().height));
                container.revalidate();
            }
        });

        Category[] categories = {
                new Category("ICED COFFEE", "rsrc/coffee/iced/americano.png", new Menu[]{
                        new Menu("Iced Americano", "₱100", "rsrc/coffee/iced/americano.png"),
                        new Menu("Iced Oat Milk Latte",  "₱165", "rsrc/coffee/iced/outmilk.png"),
                        new Menu("Iced Caffe Latte",  "₱140", "rsrc/pHolder.png"),
                        new Menu("Iced Mocha Latte",   "₱140", "rsrc/coffee/iced/mocha uson.png"),
                        new Menu("Iced Butterscotch Latte",   "₱140", "rsrc/coffee/iced/Butterscotch.png"),
                        new Menu("Iced White Mocha Latte",   "₱140", "rsrc/coffee/iced/white mocha.png"),
                        new Menu("Iced Spanish Latte",   "₱140", "rsrc/coffee/iced/Spanish latte.png"),
                        new Menu("Iced Caramel Macchiato",   "₱140", "rsrc/coffee/iced/caramel macchiato.png"),
                        new Menu("Iced Brown Sugar Latte",      "₱120", "rsrc/coffee/iced/brownsugar.png")
                }),
                new Category("HOT SELECTION", "rsrc/coffee/hots/Americano.png", new Menu[]{
                        new Menu("Americano","₱90","rsrc/coffee/hots/Americano.png"),
                        new Menu("Flat White", "₱150", "rsrc/coffee/hots/Flat White.png"),
                        new Menu("Pistachio Cortado","₱200", "rsrc/coffee/hots/Pistachio.png"),
                        new Menu("Affogato", "₱200","rsrc/coffee/hots/Affogato.png"),
                        new Menu("Caffe Latte", "₱200","rsrc/pHolder.png"),
                        new Menu("Cappucino", "₱200","rsrc/pHolder.png"),
                        new Menu("Signature Hot Choco",      "₱120", "rsrc/pHolder.png")
                }),
                new Category("FRAPPE", "rsrc/coffee/Frappe/dchoc.png", new Menu[]{
                        new Menu("Oreo Frappe",   "₱80",  "rsrc/coffee/Frappe/oreo.png"),
                        new Menu("Choco Frappe", "₱110", "rsrc/coffee/Frappe/dchoc.png"),
                        new Menu("Matcha Oreo Frappe",      "₱120", "rsrc/coffee/Frappe/Matcha Oreo.png"),
                        new Menu("Matcha Frappe",      "₱120", "rsrc/coffee/Frappe/Matcha.png"),
                        new Menu("Java Chip Frappe",      "₱120", "rsrc/coffee/Frappe/Java Chip.png"),
                        new Menu("Cheesecake Frappe",      "₱120", "rsrc/coffee/Frappe/Cheseecake.png"),
                        new Menu("Biscoff Frappe",      "₱120", "rsrc/coffee/Frappe/Biscoff.png"),
                        new Menu("Strawberry Frappe",      "₱120", "rsrc/coffee/Frappe/Strawberry.png"),
                        new Menu("Salted Caramel Frappe",      "₱120", "rsrc/coffee/Frappe/Salted Caramel.png")
                }),
                new Category("REFRESHERS", "rsrc/coffee/Refreshers/mangoA.png", new Menu[]{
                        new Menu("Iced Matcha",    "₱60", "rsrc/coffee/Refreshers/matc.png"),
                        new Menu("Strawberry Milk",      "₱120", "rsrc/coffee/Refreshers/strM.png"),
                        new Menu("Blueberry Milk",      "₱120", "rsrc/coffee/Refreshers/bberyA.png"),
                        new Menu("Choco Milk",      "₱120", "rsrc/coffee/Refreshers/choco.png"),
                        new Menu("Mango Ade",    "₱70", "rsrc/coffee/Refreshers/mangoA.png"),
                        new Menu("Strawberry Ade",      "₱120", "rsrc/coffee/Refreshers/strA.png"),
                        new Menu("Hibiscus Ade", "₱90", "rsrc/coffee/Refreshers/hibis.png"),
                        new Menu("Lemonade",      "₱120", "rsrc/coffee/Refreshers/monade.png")
                }),
                new Category("SIDES", "rsrc/coffee/Sides/bgr.png", new Menu[]{
                        new Menu("Hamburger", "₱65", "rsrc/coffee/Sides/bgr.png"),
                        new Menu("Ham 'n Egg Sandwich",      "₱85", "rsrc/coffee/Sides/hamnegg.png"),
                        new Menu("BLT Sandwich",      "₱75", "rsrc/coffee/Sides/blt.png"),
                        new Menu("Tuna Pesto",      "₱120", "rsrc/coffee/Sides/tuna.png"),
                        new Menu("Chicken Alfredo",      "₱120", "rsrc/coffee/Sides/alfre.png"),
                        new Menu("Italian Spaghetti",      "₱120", "rsrc/coffee/Sides/spag.png"),
                        new Menu("Fries Overload",      "₱120", "rsrc/coffee/Sides/fries.png"),
                        new Menu("Nachos Overload",      "₱120", "rsrc/coffee/Sides/nachos.png")
                }),
                new Category("PASTRY", "rsrc/coffee/Pastry/Croissants.png", new Menu[]{
                        new Menu("Croissants",  "₱70",  "rsrc/coffee/Pastry/Croissants.png"),
                        new Menu("Brownies",    "₱95",  "rsrc/coffee/Pastry/Brownies.png"),
                        new Menu("4pcs. Cookies", "₱130", "rsrc/coffee/Pastry/Cookies.png"),
                        new Menu("Cheesecake", "₱130", "rsrc/coffee/Pastry/Cheesecake.png")
                })
        };

       //Runnable[] loadCategory = { null };
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

        for (Menu item : categories[0].getItems()) {
            container.add(new MenuCard(item, orderManager));
        }

        JPanel northPanel = new JPanel(new BorderLayout(0, 8));
        northPanel.setBackground(cream);
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JLabel title = new JLabel("KiPeYe Coffee");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        northPanel.add(title, BorderLayout.WEST);
        northPanel.add(categoryPanel, BorderLayout.SOUTH);
        northPanel.add(backBtn, BorderLayout.EAST);

        menuPanel.add(northPanel, BorderLayout.NORTH);
        menuPanel.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}