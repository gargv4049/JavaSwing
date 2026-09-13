package chat;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server extends JFrame {
    private final JPanel chat = new JPanel();
    private JScrollPane scroll;
    private final JTextField input = new JTextField();
    private final JButton send = new JButton("Send");
    private final JLabel status = new JLabel("● WAITING");
    private DataInputStream in;
    private DataOutputStream out;

    public Server() {
        gui();
        startServer();
    }

    private void gui() {
        setTitle("Server - Chat Application");
        setSize(500, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(100, 100);

        JLabel title = new JLabel("SERVER CHAT");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        status.setForeground(Color.ORANGE);
        status.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel head = new JPanel(new BorderLayout());
        head.setBackground(new Color(45, 45, 45));
        head.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));
        head.add(title, BorderLayout.WEST);
        head.add(status, BorderLayout.EAST);

        chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
        chat.setBackground(Color.WHITE);

        scroll = new JScrollPane(chat);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        send.setBackground(new Color(52, 152, 219));
        send.setForeground(Color.WHITE);
        send.setFocusPainted(false);

        JPanel bottom = new JPanel(new BorderLayout(10, 10));
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottom.add(input, BorderLayout.CENTER);
        bottom.add(send, BorderLayout.EAST);

        add(head, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        send.addActionListener(e -> sendMessage());
        input.addActionListener(e -> sendMessage());

        setVisible(true);
    }

    private void startServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5000)) {
                Socket socket = serverSocket.accept();

                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                SwingUtilities.invokeLater(() -> {
                    status.setText("● ONLINE");
                    status.setForeground(Color.GREEN);
                });

                while (true) {
                    String msg = in.readUTF();
                    SwingUtilities.invokeLater(() -> addMessage(msg, false));
                }

            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> {
                    status.setText("● OFFLINE");
                    status.setForeground(Color.RED);
                });
            }
        }).start();
    }

    private void sendMessage() {
        String msg = input.getText().trim();

        if (msg.isEmpty() || out == null) return;

        try {
            out.writeUTF(msg);
            out.flush();
            addMessage(msg, true);
            input.setText("");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Connection lost!");
        }
    }

    private void addMessage(String msg, boolean sent) {
        JPanel wrapper = new JPanel(new FlowLayout(
                sent ? FlowLayout.RIGHT : FlowLayout.LEFT, 8, 4));
        wrapper.setBackground(Color.WHITE);

        JPanel bubble = new JPanel();
        bubble.setLayout(new BoxLayout(bubble, BoxLayout.Y_AXIS));
        bubble.setBackground(sent
                ? new Color(210, 230, 250)
                : new Color(235, 235, 235));
        bubble.setBorder(BorderFactory.createEmptyBorder(7, 11, 5, 11));

        JTextArea text = new JTextArea(msg);
        text.setFont(new Font("Arial", Font.PLAIN, 14));
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        text.setOpaque(false);
        text.setBorder(null);

        int width = Math.min(280,
                Math.max(60, text.getFontMetrics(text.getFont())
                        .stringWidth(msg) + 15));

        text.setSize(new Dimension(width, Short.MAX_VALUE));
        text.setPreferredSize(new Dimension(width, text.getPreferredSize().height));

        JLabel time = new JLabel(
                new SimpleDateFormat("HH:mm").format(new Date()));
        time.setFont(new Font("Arial", Font.PLAIN, 10));
        time.setForeground(Color.GRAY);
        time.setAlignmentX(Component.RIGHT_ALIGNMENT);

        bubble.add(text);
        bubble.add(Box.createVerticalStrut(3));
        bubble.add(time);
        wrapper.add(bubble);

        chat.add(wrapper);
        chat.revalidate();
        chat.repaint();

        SwingUtilities.invokeLater(() ->
                scroll.getVerticalScrollBar().setValue(
                        scroll.getVerticalScrollBar().getMaximum()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Server::new);
    }
}