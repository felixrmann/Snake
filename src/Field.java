import javax.swing.*;
import java.awt.*;

/**
 * @author Felix Mann
 * @version 1.0
 * @since 2020-Juli-09
 */

public class Field extends JPanel {
    private char color;
    public Field(char color){
        this.color = color;

        fieldUpdate();
    }

    public void fieldUpdate() {
        if (color == ' '){
            setBackground(new Color(77,210,68));
        } else if (color == 's'){
            setBackground(Color.RED);
        } else if (color == 'b'){
            setBackground(new Color(226,44,178));
        } else if (color == 'h'){
            setBackground(new Color(0xC6070B));
        }
    }
}
