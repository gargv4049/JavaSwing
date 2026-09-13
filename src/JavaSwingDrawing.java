import javax.swing.*;
import java.awt.*;

public class JavaSwingDrawing extends JFrame {

    public JavaSwingDrawing() {
        // Window Configuration
        setTitle("Java Swing Drawing");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Add custom panel for drawing components
        DrawingPanel panel = new DrawingPanel();
        panel.setBackground(Color.WHITE);
        add(panel);
    }

    // Custom JPanel class overriding paintComponent for 2D graphics
    class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // Enable Antialiasing for clean shapes and text
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 1. Draw "Vivek Garg (24EARCS186)" Text
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            g2d.setColor(Color.BLACK);
            FontMetrics fm1 = g2d.getFontMetrics();
            String text1 = "Vivek Garg (24EARCS186)";
            int x1 = (getWidth() - fm1.stringWidth(text1)) / 2;
            g2d.drawString(text1, x1, 45);

            // 2. Draw "Hello World!" Text
            g2d.setFont(new Font("Arial", Font.BOLD, 22));
            FontMetrics fm2 = g2d.getFontMetrics();
            String text2 = "Hello World!";
            int x2 = (getWidth() - fm2.stringWidth(text2)) / 2;
            g2d.drawString(text2, x2, 85);

            // 3. Draw Outlined Rectangle
            int rectWidth = 200;
            int rectHeight = 60;
            int rectX = (getWidth() - rectWidth) / 2;
            int rectY = 115;
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.drawRect(rectX, rectY, rectWidth, rectHeight);

            // 4. Draw Filled Black Oval
            int ovalWidth = 100;
            int ovalHeight = 65;
            int ovalX = (getWidth() - ovalWidth) / 2;
            int ovalY = 210;
            g2d.fillOval(ovalX, ovalY, ovalWidth, ovalHeight);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JavaSwingDrawing().setVisible(true);
        });
    }
}