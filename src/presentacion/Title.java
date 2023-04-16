package presentacion;

import javax.swing.*;
import java.awt.*;

public class Title extends JLabel {
    public Title(String title, Color backgroundColor) {
        this.setText(title);
        this.setBackground(backgroundColor);
        this.setForeground(Color.PINK);
        this.setFont(new Font("arial", Font.BOLD+Font.ITALIC,24));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setOpaque(true);
    }
}
