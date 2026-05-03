import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class CategoryPanel extends JPanel {
    private CategoryBtn[] buttons;

    public CategoryPanel(Category[] categories, Consumer<Category> onCategorySelected) {
        setLayout(new GridLayout(1, categories.length, 8, 0));
        setBackground(new Color(239, 233, 227));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        buttons = new CategoryBtn[categories.length];

        for (int i = 0; i < categories.length; i++) {
            final int index = i;
            buttons[i] = new CategoryBtn(categories[i], () -> {
                selectButton(index);
                onCategorySelected.accept(categories[index]);
            });
            add(buttons[i]);
        }

        // Select first by default
        if (categories.length > 0) {
            buttons[0].setSelected(true);
        }
    }

    private void selectButton(int index) {
        for (CategoryBtn btn : buttons) btn.setSelected(false);
        buttons[index].setSelected(true);
    }
}