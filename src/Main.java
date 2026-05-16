import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomePanel());
    }

    public void createUI(String orderType)  {

        FlatMacLightLaf.setup();

        JFrame mainframe = new JFrame("KPY COFFEE KIOSK");
        mainframe.setSize(900, 620);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainframe.setLayout(new BorderLayout(15, 15));
        mainframe.setResizable(false);
        mainframe.setLocationRelativeTo(null);

        Color bg = new Color(249, 248, 246);
        Color cream = new Color(239, 233, 227);
        mainframe.getContentPane().setBackground(bg);


        // PANELS
        JPanel pnlMenu = new JPanel(new BorderLayout(0, 8));
        OrderManager orderManager = new OrderManager();
        OrderPanel pnlOrder = new OrderPanel(orderManager, orderType);

        pnlMenu.setPreferredSize(new Dimension(580, 0));
        pnlOrder.setPreferredSize(new Dimension(280, 0));
        pnlMenu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        pnlOrder.setBorder(BorderFactory.createEtchedBorder());
        pnlMenu.setBackground(cream);

        mainframe.add(pnlMenu, BorderLayout.CENTER);
        mainframe.add(pnlOrder, BorderLayout.EAST);

        // MENU ITEM CONTAINER
        JPanel cont = new JPanel(new GridLayout(0, 3, 10, 10));
        cont.setBackground(cream);
        cont.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scroll = new JScrollPane(cont);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        JButton btnBack = new JButton("← Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 12));
        btnBack.setBackground(new Color(90, 60, 30));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    mainframe,
                    "Going back will clear your current order. Continue?",
                    "Go Back",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                mainframe.dispose();
                SwingUtilities.invokeLater(() -> new WelcomePanel());
            }
        });



        scroll.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent e) {
                int width = scroll.getViewport().getWidth();
                cont.setPreferredSize(new Dimension(width, cont.getPreferredSize().height));
                cont.revalidate();
            }
        });

        Category[] categories = {
                new Category("ICED COFFEE", "rsrc/coffee/iced/americano.png", new Menu[]{
                        new Menu("Iced Americano", "₱99", "rsrc/coffee/iced/americano.png"),
                        new Menu("Iced Oat Milk Latte",  "₱149", "rsrc/coffee/iced/outmilk.png"),
                        new Menu("Iced Caffe Latte",  "₱139", "rsrc/coffee/iced/classic latte.png"),
                        new Menu("Iced Mocha Latte",   "₱149", "rsrc/coffee/iced/mocha uson.png"),
                        new Menu("Iced Butterscotch Latte",   "₱149", "rsrc/coffee/iced/Butterscotch.png"),
                        new Menu("Iced White Mocha Latte",   "₱149", "rsrc/coffee/iced/white mocha.png"),
                        new Menu("Iced Spanish Latte",   "₱119", "rsrc/coffee/iced/Spanish latte.png"),
                        new Menu("Iced Caramel Macchiato",   "₱109", "rsrc/coffee/iced/caramel macchiato.png"),
                        new Menu("Iced Brown Sugar Latte",      "₱109", "rsrc/coffee/iced/brownsugar.png")
                }),
                new Category("HOT SELECTION", "rsrc/coffee/hots/Americano.png", new Menu[]{
                        new Menu("Americano","₱99","rsrc/coffee/hots/Americano.png"),
                        new Menu("Flat White", "₱149", "rsrc/coffee/hots/Flat White.png"),
                        new Menu("Pistachio Cortado","₱149", "rsrc/coffee/hots/Pistachio.png"),
                        new Menu("Affogato", "₱139","rsrc/coffee/hots/Affogato.png"),
                        new Menu("Caffe Latte", "109","rsrc/coffee/hots/Caffe Latte.png"),
                        new Menu("Signature Hot Choco",      "₱129", "rsrc/coffee/hots/chocosig.png")
                }),
                new Category("FRAPPE", "rsrc/coffee/Frappe/dchoc.png", new Menu[]{
                        new Menu("Oreo Frappe",   "₱139",  "rsrc/coffee/Frappe/oreo.png"),
                        new Menu("Choco Frappe", "₱129", "rsrc/coffee/Frappe/dchoc.png"),
                        new Menu("Matcha Oreo Frappe",      "₱149", "rsrc/coffee/Frappe/Matcha Oreo.png"),
                        new Menu("Matcha Frappe",      "₱139", "rsrc/coffee/Frappe/Matcha.png"),
                        new Menu("Java Chip Frappe",      "₱139", "rsrc/coffee/Frappe/Java Chip.png"),
                        new Menu("Cheesecake Frappe",      "₱149", "rsrc/coffee/Frappe/Cheseecake.png"),
                        new Menu("Biscoff Frappe",      "₱149", "rsrc/coffee/Frappe/Biscoff.png"),
                        new Menu("Strawberry Frappe",      "₱129", "rsrc/coffee/Frappe/Strawberry.png"),
                        new Menu("Salted Caramel Frappe",      "₱129", "rsrc/coffee/Frappe/Salted Caramel.png")
                }),
                new Category("REFRESHERS", "rsrc/coffee/Refreshers/mangoA.png", new Menu[]{
                        new Menu("Iced Matcha",    "₱99", "rsrc/coffee/Refreshers/matc.png"),
                        new Menu("Strawberry Milk",      "₱99", "rsrc/coffee/Refreshers/strM.png"),
                        new Menu("Blueberry Milk",      "₱99", "rsrc/coffee/Refreshers/bberyA.png"),
                        new Menu("Signature Choco Milk",      "₱99", "rsrc/coffee/Refreshers/choco.png"),
                        new Menu("Mango Ade",    "₱89", "rsrc/coffee/Refreshers/mangoA.png"),
                        new Menu("Strawberry Ade",      "₱89", "rsrc/coffee/Refreshers/strA.png"),
                        new Menu("Hibiscus Ade", "₱89", "rsrc/coffee/Refreshers/hibis.png"),
                        new Menu("Lemonade",      "₱99", "rsrc/coffee/Refreshers/monade.png")
                }),
                new Category("SIDES", "rsrc/coffee/Sides/bgr.png", new Menu[]{
                        new Menu("Hamburger", "₱109", "rsrc/coffee/Sides/bgr.png"),
                        new Menu("Ham 'n Egg Sandwich",      "₱99", "rsrc/coffee/Sides/hamnegg.png"),
                        new Menu("BLT Sandwich",      "₱109", "rsrc/coffee/Sides/blt.png"),
                        new Menu("Tuna Pesto",      "₱149", "rsrc/coffee/Sides/tuna.png"),
                        new Menu("Chicken Alfredo",      "₱149", "rsrc/coffee/Sides/alfre.png"),
                        new Menu("Italian Spaghetti",      "₱149", "rsrc/coffee/Sides/spag.png"),
                        new Menu("Fries Overload",      "₱159", "rsrc/coffee/Sides/fries.png"),
                        new Menu("Nachos Overload",      "₱159", "rsrc/coffee/Sides/nach.png")
                }),
                new Category("PASTRY", "rsrc/coffee/Pastry/Croissants.png", new Menu[]{
                        new Menu("Croissant",  "₱99",  "rsrc/coffee/Pastry/Croissants.png"),
                        new Menu("Brownies",    "₱109",  "rsrc/coffee/Pastry/Brownies.png"),
                        new Menu("4pcs. Cookies", "₱109", "rsrc/coffee/Pastry/Cookies.png"),
                        new Menu("Cheesecake", "₱149", "rsrc/coffee/Pastry/Cheesecake.png"),
                        new Menu("Egg Pie", "₱149", "rsrc/coffee/Pastry/Egg Pie.png"),
                        new Menu("Tiramisu", "₱149", "rsrc/coffee/Pastry/Tiramisu.png"),
                        new Menu("Mango Graham", "₱149", "rsrc/coffee/Pastry/Mango Graham.png"),
                        new Menu("Dubai Chewy Choco", "₱149", "rsrc/coffee/Pastry/Dubai Chewy Cookie.png")
                })
        };

        Runnable[] loadCategory = { null };
        loadCategory[0] = () -> {};

        CategoryPanel PnlCategory = new CategoryPanel(categories, selected -> {
            cont.removeAll();
            for (Menu item : selected.getItems()) {
                cont.add(new MenuCard(item, orderManager));
            }
            cont.revalidate();
            cont.repaint();
        });

        for (Menu item : categories[0].getItems()) {
            cont.add(new MenuCard(item, orderManager));
        }

        JPanel PnlNorth = new JPanel(new BorderLayout(0, 8));
        PnlNorth.setBackground(cream);
        PnlNorth.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JLabel title = new JLabel("KiPeYe Coffee");
        title.setFont(new Font("Arial", Font.BOLD, 18));

        PnlNorth.add(title, BorderLayout.WEST);
        PnlNorth.add(PnlCategory, BorderLayout.SOUTH);
        PnlNorth.add(btnBack, BorderLayout.EAST);

        pnlMenu.add(PnlNorth, BorderLayout.NORTH);
        pnlMenu.add(scroll, BorderLayout.CENTER);

        mainframe.setVisible(true);
    }
}