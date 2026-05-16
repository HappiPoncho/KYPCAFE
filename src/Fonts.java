import java.awt.*;
import java.io.File;

public class Fonts {

    public static final Font REGULAR;
    public static final Font BOLD;
    public static final Font ITALIC;

    static {
        Font loaded;
        try {
            loaded = Font.createFont(Font.TRUETYPE_FONT, new File("rsrc/icons and fonts/FeelFree.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(loaded);
        } catch (Exception e) {
            loaded = new Font("Arial", Font.PLAIN, 12);
        }

        REGULAR = loaded.deriveFont(Font.PLAIN, 12f);
        BOLD    = loaded.deriveFont(Font.BOLD, 12f);
        ITALIC  = loaded.deriveFont(Font.ITALIC, 12f);
    }

    public static Font of(int style, float size) {
        return REGULAR.deriveFont(style, size);
    }
}