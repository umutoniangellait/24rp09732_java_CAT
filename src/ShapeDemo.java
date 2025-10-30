import java.awt.*;
import javax.swing.*;

interface ShapeDrawable {
    void draw(Graphics2D g);
}

// HeadSticker draws a head with a sticker but no text
class HeadSticker implements ShapeDrawable {
    private int cx, cy, radius;

    public HeadSticker(int cx, int cy, int radius) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Head
        int d = radius * 2;
        int x = cx - radius;
        int y = cy - radius;
        g.setColor(new Color(255, 224, 189)); // skin tone
        g.fillOval(x, y, d, d);
        g.setColor(Color.DARK_GRAY);
        g.setStroke(new BasicStroke(2));
        g.drawOval(x, y, d, d);

        // Eyes
        int eyeW = radius / 4, eyeH = radius / 6;
        int leftEyeX = cx - radius / 2, rightEyeX = cx + radius / 4;
        int eyeY = cy - radius / 6;
        g.setColor(Color.WHITE);
        g.fillOval(leftEyeX, eyeY, eyeW, eyeH);
        g.fillOval(rightEyeX, eyeY, eyeW, eyeH);
        g.setColor(Color.BLACK);
        g.fillOval(leftEyeX + eyeW/4, eyeY + eyeH/4, eyeW/3, eyeH/3);
        g.fillOval(rightEyeX + eyeW/4, eyeY + eyeH/4, eyeW/3, eyeH/3);

        // Mouth
        int mouthW = radius, mouthH = radius / 4;
        int mouthX = cx - mouthW / 2, mouthY = cy + radius / 6;
        g.setColor(new Color(200, 60, 60));
        g.fillArc(mouthX, mouthY, mouthW, mouthH, 0, -180);
        g.setColor(Color.DARK_GRAY);
        g.drawArc(mouthX, mouthY, mouthW, mouthH, 0, -180);

        // Sticker without text
        int stickerW = radius;
        int stickerH = radius / 3;
        int stickerX = cx - stickerW / 2;
        int stickerY = y + radius / 6;
        g.setColor(new Color(255, 215, 0)); // gold
        g.fillRoundRect(stickerX, stickerY, stickerW, stickerH, 15, 15);
        g.setColor(Color.BLACK);
        g.drawRoundRect(stickerX, stickerY, stickerW, stickerH, 15, 15);
    }
}

// Drawing panel for the head
class DrawingPanel extends JPanel {
    private java.util.List<ShapeDrawable> items = new java.util.ArrayList<>();

    public DrawingPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
    }

    public void addItem(ShapeDrawable s) {
        items.add(s);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Title
        g2.setFont(new Font("SansSerif", Font.BOLD, 40));
        g2.setColor(Color.BLUE);
        String title = "MY SHAPES";
        FontMetrics fm = g2.getFontMetrics();
        int tx = (getWidth() - fm.stringWidth(title)) / 2;
        g2.drawString(title, tx, 70);

        // Draw shapes (only head sticker)
        for (ShapeDrawable s : items) {
            s.draw(g2);
        }

        g2.dispose();
    }
}

// Main class
public class ShapeDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My Shapes");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DrawingPanel panel = new DrawingPanel();

            // Add only the head sticker
            panel.addItem(new HeadSticker(400, 320, 100));

            frame.setContentPane(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

