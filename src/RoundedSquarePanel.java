import javax.swing.*;
import java.awt.*;

public class RoundedSquarePanel extends JPanel {
    public Color colour;
    public int squareSize;

    public RoundedSquarePanel(Color colour, int size) {
        this.colour = colour;
        this.squareSize = size;
        setOpaque(false);
        setPreferredSize(new Dimension(size, size));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(colour);

        int width = getWidth() - 50;
        int height = getHeight() - 50;
        g2.fillRoundRect(25, 25, width, height, 100, 100);
    }
}
