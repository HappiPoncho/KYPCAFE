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
    private final String dtime;

    public Receipt(JFrame parent, List<Orders> items, int total, String orderType) {
        super(parent, "Receipt", true);
        this.parent = parent;
        this.items = items;
        this.total = total;
        this.orderType = orderType;
        this.dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy  hh:mm a"));
        this.dtime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM" +"dd" + "yy" + "HH" + "mm" + "ss"));

        setSize(380, 520);
        setLocationRelativeTo(parent);
        setResizable(false);
        setLayout(new BorderLayout());

        add(ReceiptPanel(), BorderLayout.CENTER);
        add(buildButtonPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel ReceiptPanel() {
        JPanel pnlReceipt = new JPanel();
        pnlReceipt.setLayout(new BoxLayout(pnlReceipt, BoxLayout.Y_AXIS));
        pnlReceipt.setBackground(Color.WHITE);
        pnlReceipt.setBorder(new EmptyBorder(20, 24, 12, 24));

        JLabel lblShop = new JLabel("KiPeYe Coffee");
        lblShop.setFont(new Font("Arial", Font.BOLD, 20));
        lblShop.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Official Receipt");
        lblSub.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSub.setForeground(Color.GRAY);
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblDate = new JLabel(dateTime);
        lblDate.setFont(new Font("Arial", Font.PLAIN, 11));
        lblDate.setForeground(Color.GRAY);
        lblDate.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblType = new JLabel(orderType.toUpperCase());
        lblType.setFont(new Font("Arial", Font.BOLD, 12));
        lblType.setForeground(new Color(90, 60, 30));
        lblType.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlReceipt.add(lblShop);
        pnlReceipt.add(Box.createVerticalStrut(4));
        pnlReceipt.add(lblSub);
        pnlReceipt.add(Box.createVerticalStrut(2));
        pnlReceipt.add(lblDate);
        pnlReceipt.add(Box.createVerticalStrut(4));
        pnlReceipt.add(lblType);
        pnlReceipt.add(Box.createVerticalStrut(12));
        pnlReceipt.add(Divider());
        pnlReceipt.add(Box.createVerticalStrut(10));

        pnlReceipt.add(RowPanel("Item", "Qty", "Price", true));
        pnlReceipt.add(Box.createVerticalStrut(6));

        for (Orders item : items) {
            pnlReceipt.add(RowPanel(
                    item.getMenu().getName(),
                    "x" + item.getQuantity(),
                    "₱" + item.getTotalPrice(),
                    false
            ));
            pnlReceipt.add(Box.createVerticalStrut(4));
        }

        pnlReceipt.add(Box.createVerticalStrut(10));
        pnlReceipt.add(Divider());
        pnlReceipt.add(Box.createVerticalStrut(10));

        JPanel lblTotalRow = new JPanel(new BorderLayout());
        lblTotalRow.setBackground(Color.WHITE);
        lblTotalRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 24));

        JLabel lblTotal = new JLabel("TOTAL");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblTotalAmount = new JLabel("₱" + total);
        lblTotalAmount.setFont(new Font("Arial", Font.BOLD, 14));

        lblTotalRow.add(lblTotal, BorderLayout.WEST);
        lblTotalRow.add(lblTotalAmount, BorderLayout.EAST);
        pnlReceipt.add(lblTotalRow);

        pnlReceipt.add(Box.createVerticalStrut(16));

        JLabel lblThanks = new JLabel("Thank you for your order!");
        lblThanks.setFont(new Font("Arial", Font.ITALIC, 12));
        lblThanks.setForeground(Color.GRAY);
        lblThanks.setAlignmentX(Component.CENTER_ALIGNMENT);
        pnlReceipt.add(lblThanks);

        return pnlReceipt;
    }

    private JPanel RowPanel(String name, String qty, String price, boolean bold) {
        JPanel pnlRow = new JPanel(new BorderLayout());
        pnlRow.setBackground(Color.WHITE);
        pnlRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));

        int style = bold ? Font.BOLD : Font.PLAIN;

        JLabel lblName  = new JLabel(name);
        JLabel lblqty = new JLabel(qty, JLabel.CENTER);
        JLabel lblPrice = new JLabel(price, JLabel.RIGHT);

        lblName.setFont(new Font("Arial", style, 12));
        lblqty.setFont(new Font("Arial", style, 12));
        lblPrice.setFont(new Font("Arial", style, 12));

        lblqty.setPreferredSize(new Dimension(40, 20));
        lblPrice.setPreferredSize(new Dimension(70, 20));

        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(Color.WHITE);
        right.add(lblqty, BorderLayout.WEST);
        right.add(lblPrice, BorderLayout.EAST);
        right.setPreferredSize(new Dimension(110, 20));

        pnlRow.add(lblName, BorderLayout.CENTER);
        pnlRow.add(right, BorderLayout.EAST);
        return pnlRow;
    }

    private JSeparator Divider() {
        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setForeground(new Color(200, 200, 200));
        return sep;
    }

    private JPanel buildButtonPanel() {
        JPanel pnlRece = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlRece.setBackground(Color.WHITE);
        pnlRece.setBorder(new EmptyBorder(0, 24, 20, 24));

        JButton closeBtn = new JButton("Close");
        closeBtn.setFocusPainted(false);
        closeBtn.addActionListener(e -> {
            dispose();
            parent.dispose();
            SwingUtilities.invokeLater(() -> new WelcomePanel());
        });

        JButton btnDownload = new JButton("Download PDF");
        btnDownload.setBackground(new Color(90, 60, 30));
        btnDownload.setForeground(Color.WHITE);
        btnDownload.setFocusPainted(false);
        btnDownload.setBorderPainted(false);
        btnDownload.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDownload.addActionListener(e -> savePDF());

        pnlRece.add(closeBtn);
        pnlRece.add(btnDownload);
        return pnlRece;
    }

    private void savePDF() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(dtime +"KPYReciept.pdf"));
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
                PdfPCell cell1 = new PdfPCell(new Phrase(item.getMenu().getName(), bodyFont));
                PdfPCell cell2 = new PdfPCell(new Phrase("x" + item.getQuantity(), bodyFont));
                PdfPCell cell3 = new PdfPCell(new Phrase("₱" + item.getTotalPrice(), bodyFont));
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                for (PdfPCell c : new PdfPCell[]{cell1, cell2, cell3}) {
                    c.setBorder(Rectangle.NO_BORDER);
                    c.setPadding(3);
                }
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
            }

            doc.add(table);

            Paragraph lineDivider = new Paragraph("________________________________________________", subFont);
            lineDivider.setAlignment(Element.ALIGN_CENTER);
            doc.add(lineDivider);

            Paragraph txtTotal = new Paragraph("TOTAL: ₱" + total, totalFont);
            txtTotal.setAlignment(Element.ALIGN_RIGHT);
            txtTotal.setSpacingBefore(8);
            doc.add(txtTotal);

            Paragraph txtThanks = new Paragraph("Thank you for your order!", subFont);
            txtThanks.setAlignment(Element.ALIGN_CENTER);
            txtThanks.setSpacingBefore(16);
            doc.add(txtThanks);

            doc.close();
            JOptionPane.showMessageDialog(this, "Receipt saved to:\n" + file.getAbsolutePath());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to save PDF:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}