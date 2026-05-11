import com.itextpdf.text.*;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.border.*;

public class Receipt extends JDialog {

    private final List<Orders> items;
    private final int total;
    private final String dateTime;
    private final String orderType;
    private final JFrame parent;

    public Receipt(JFrame parent, List<Orders> items, int total, String orderType) {
        super(parent, "Receipt", true);
        this.parent = parent;
        this.items = items;
        this.total = total;
        this.orderType = orderType;
        this.dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy  hh:mm a"));

        setSize(380, 520);
        setLocationRelativeTo(parent);
        setResizable(false);
        setLayout(new BorderLayout());

        add(ReceiptPanel(), BorderLayout.CENTER);
        add(buildButtonPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel ReceiptPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 24, 12, 24));

        JLabel shop = new JLabel("KiPeYe Coffee");
        shop.setFont(new Font("Arial", Font.BOLD, 20));
        shop.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Official Receipt");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitle.setForeground(Color.GRAY);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel date = new JLabel(dateTime);
        date.setFont(new Font("Arial", Font.PLAIN, 11));
        date.setForeground(Color.GRAY);
        date.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel typeLabel = new JLabel(orderType.toUpperCase());
        typeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        typeLabel.setForeground(new Color(90, 60, 30));
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(shop);
        panel.add(Box.createVerticalStrut(4));
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(2));
        panel.add(date);
        panel.add(Box.createVerticalStrut(4));
        panel.add(typeLabel);
        panel.add(Box.createVerticalStrut(12));
        panel.add(makeDivider());
        panel.add(Box.createVerticalStrut(10));

        panel.add(makeRow("Item", "Qty", "Price", true));
        panel.add(Box.createVerticalStrut(6));

        for (Orders item : items) {
            panel.add(makeRow(
                    item.getMenu().getName(),
                    "x" + item.getQuantity(),
                    "₱" + item.getTotalPrice(),
                    false
            ));
            panel.add(Box.createVerticalStrut(4));
        }

        panel.add(Box.createVerticalStrut(10));
        panel.add(makeDivider());
        panel.add(Box.createVerticalStrut(10));

        JPanel totalRow = new JPanel(new BorderLayout());
        totalRow.setBackground(Color.WHITE);
        totalRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));

        JLabel totalLabel = new JLabel("TOTAL");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel totalAmount = new JLabel("₱" + total);
        totalAmount.setFont(new Font("Arial", Font.BOLD, 14));

        totalRow.add(totalLabel, BorderLayout.WEST);
        totalRow.add(totalAmount, BorderLayout.EAST);
        panel.add(totalRow);

        panel.add(Box.createVerticalStrut(16));

        JLabel thanks = new JLabel("Thank you for your order!");
        thanks.setFont(new Font("Arial", Font.ITALIC, 12));
        thanks.setForeground(Color.GRAY);
        thanks.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(thanks);

        return panel;
    }

    private JPanel makeRow(String name, String qty, String price, boolean bold) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(Color.WHITE);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));

        int style = bold ? Font.BOLD : Font.PLAIN;

        JLabel nameLabel  = new JLabel(name);
        JLabel qtyLabel   = new JLabel(qty, JLabel.CENTER);
        JLabel priceLabel = new JLabel(price, JLabel.RIGHT);

        nameLabel.setFont(new Font("Arial", style, 12));
        qtyLabel.setFont(new Font("Arial", style, 12));
        priceLabel.setFont(new Font("Arial", style, 12));

        qtyLabel.setPreferredSize(new Dimension(40, 20));
        priceLabel.setPreferredSize(new Dimension(70, 20));

        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(Color.WHITE);
        right.add(qtyLabel, BorderLayout.WEST);
        right.add(priceLabel, BorderLayout.EAST);
        right.setPreferredSize(new Dimension(110, 20));

        row.add(nameLabel, BorderLayout.CENTER);
        row.add(right, BorderLayout.EAST);
        return row;
    }

    private JSeparator makeDivider() {
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setForeground(new Color(200, 200, 200));
        return sep;
    }

    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(0, 24, 20, 24));

        JButton closeBtn = new JButton("Close");
        closeBtn.setFocusPainted(false);
        closeBtn.addActionListener(e -> {
            dispose();
            parent.dispose();
            SwingUtilities.invokeLater(() -> new WelcomePanel());
        });

        JButton downloadBtn = new JButton("Download PDF");
        downloadBtn.setBackground(new Color(90, 60, 30));
        downloadBtn.setForeground(Color.WHITE);
        downloadBtn.setFocusPainted(false);
        downloadBtn.setBorderPainted(false);
        downloadBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        downloadBtn.addActionListener(e -> savePDF());

        panel.add(closeBtn);
        panel.add(downloadBtn);
        return panel;
    }

    private void savePDF() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("KiPeYe_Receipt.pdf"));
        int result = chooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) return;

        File file = chooser.getSelectedFile();
        if (!file.getName().endsWith(".pdf")) file = new File(file.getAbsolutePath() + ".pdf");

        try {
            Document doc = new Document(PageSize.A6);
            PdfWriter.getInstance(doc, new FileOutputStream(file));
            doc.open();

            com.itextpdf.text.Font titleFont  = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 18, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font subFont    = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10, com.itextpdf.text.Font.NORMAL, BaseColor.GRAY);
            com.itextpdf.text.Font headerFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10, com.itextpdf.text.Font.BOLD);
            com.itextpdf.text.Font bodyFont   = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10);
            com.itextpdf.text.Font totalFont  = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 12, com.itextpdf.text.Font.BOLD);

            Paragraph shopName = new Paragraph("KiPeYe Coffee", titleFont);
            shopName.setAlignment(Element.ALIGN_CENTER);
            doc.add(shopName);

            Paragraph receipt = new Paragraph("Official Receipt", subFont);
            receipt.setAlignment(Element.ALIGN_CENTER);
            doc.add(receipt);

            Paragraph dt = new Paragraph(dateTime, subFont);
            dt.setAlignment(Element.ALIGN_CENTER);
            dt.setSpacingAfter(6);
            doc.add(dt);

            Paragraph type = new Paragraph(orderType.toUpperCase(), headerFont);
            type.setAlignment(Element.ALIGN_CENTER);
            type.setSpacingAfter(10);
            doc.add(type);

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{5, 1.5f, 2});
            table.setSpacingBefore(6);
            table.setSpacingAfter(6);

            for (String h : new String[]{"Item", "Qty", "Price"}) {
                PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));
                cell.setBorder(Rectangle.BOTTOM);
                cell.setPadding(4);
                table.addCell(cell);
            }

            for (Orders item : items) {
                PdfPCell c1 = new PdfPCell(new Phrase(item.getMenu().getName(), bodyFont));
                PdfPCell c2 = new PdfPCell(new Phrase("x" + item.getQuantity(), bodyFont));
                PdfPCell c3 = new PdfPCell(new Phrase("₱" + item.getTotalPrice(), bodyFont));
                c2.setHorizontalAlignment(Element.ALIGN_CENTER);
                c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                for (PdfPCell c : new PdfPCell[]{c1, c2, c3}) {
                    c.setBorder(Rectangle.NO_BORDER);
                    c.setPadding(3);
                }
                table.addCell(c1);
                table.addCell(c2);
                table.addCell(c3);
            }

            doc.add(table);

            Paragraph divider = new Paragraph("________________________________________________", subFont);
            divider.setAlignment(Element.ALIGN_CENTER);
            doc.add(divider);

            Paragraph totalPara = new Paragraph("TOTAL: ₱" + total, totalFont);
            totalPara.setAlignment(Element.ALIGN_RIGHT);
            totalPara.setSpacingBefore(8);
            doc.add(totalPara);

            Paragraph thanks = new Paragraph("Thank you for your order!", subFont);
            thanks.setAlignment(Element.ALIGN_CENTER);
            thanks.setSpacingBefore(16);
            doc.add(thanks);

            doc.close();
            JOptionPane.showMessageDialog(this, "Receipt saved to:\n" + file.getAbsolutePath());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to save PDF:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}