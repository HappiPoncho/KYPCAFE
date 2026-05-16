import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class CategoryPanel extends JPanel {
    private CategoryBtn[] btnCat;

    public CategoryPanel(Category[] categories, Consumer<Category> onCategorySelected) {
        setLayout(new GridLayout(1, categories.length, 8, 0));
        setBackground(new Color(239, 233, 227));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

    btnCat = new CategoryBtn[categories.length];

        for (int i = 0; i < categories.length; i++) {
            final int index = i;
            btnCat[i] = new CategoryBtn(categories[i], () -> {
                selectCat(index);
                onCategorySelected.accept(categories[index]);
            });
            add(btnCat[i]);
        }

        if (categories.length > 0) {
            btnCat[0].setSelected(true);
        }
    }

    private void selectCat(int index) {
        for (CategoryBtn btn : btnCat) btn.setSelected(false);
        btnCat[index].setSelected(true);
    }
}