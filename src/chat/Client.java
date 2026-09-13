package Client_Server;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client extends JFrame {
    private final JPanel chat = new JPanel();
    private JScrollPane scroll;
    private final JTextField input = new JTextField();
    private final JButton send = new JButton("Send");
    private final JButton emojiBtn = new JButton("😊");
    private final JLabel status = new JLabel("● OFFLINE");
    private final JComboBox<String> themePicker = new JComboBox<>(new String[]{"Emerald", "Ocean Blue", "Purple", "Dark Mode"});

    private DataInputStream in;
    private DataOutputStream out;

    // Active Theme Palette
    private Color sentBubbleColor = new Color(210, 245, 220);
    private Color sentTextColor = Color.BLACK;
    private Color accentColor = new Color(46, 204, 113);

    public Client() {
        gui();
        connect();
    }

    private void gui() {
        setTitle("Client - Chat Application");
        setSize(520, 680);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(650, 100);

        // Header Configuration
        JLabel title = new JLabel("CLIENT CHAT");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        status.setForeground(Color.RED);
        status.setFont(new Font("Arial", Font.BOLD, 13));

        JPanel headerTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        headerTitlePanel.setOpaque(false);
        headerTitlePanel.add(title);
        headerTitlePanel.add(status);

        // Theme Dropdown Styling
        themePicker.setFocusable(false);
        themePicker.addActionListener(e -> applyTheme((String) themePicker.getSelectedItem()));

        JPanel head = new JPanel(new BorderLayout());
        head.setBackground(new Color(45, 45, 45));
        head.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));
        head.add(headerTitlePanel, BorderLayout.WEST);
        head.add(themePicker, BorderLayout.EAST);

        // Chat Body
        chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
        chat.setBackground(Color.WHITE);

        scroll = new JScrollPane(chat);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Emoji & Action Controls
        emojiBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        emojiBtn.setFocusPainted(false);
        emojiBtn.setMargin(new Insets(2, 8, 2, 8));

        JPopupMenu emojiMenu = createEmojiPicker();
        emojiBtn.addActionListener(e -> emojiMenu.show(emojiBtn, 0, -emojiMenu.getPreferredSize().height));

        send.setBackground(accentColor);
        send.setForeground(Color.WHITE);
        send.setFocusPainted(false);
        send.setFont(new Font("Arial", Font.BOLD, 13));

        JPanel inputPanel = new JPanel(new BorderLayout(8, 0));
        inputPanel.add(emojiBtn, BorderLayout.WEST);
        inputPanel.add(input, BorderLayout.CENTER);
        inputPanel.add(send, BorderLayout.EAST);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottom.add(inputPanel, BorderLayout.CENTER);

        add(head, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        send.addActionListener(e -> sendMessage());
        input.addActionListener(e -> sendMessage());

        setVisible(true);
    }

    private JPopupMenu createEmojiPicker() {
        JPopupMenu popup = new JPopupMenu();
        JPanel emojiPanel = new JPanel(new GridLayout(3, 4, 4, 4));
        emojiPanel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        String[] emojis = {"😊", "😂", "❤️", "👍", "🔥", "🎉", "😎", "🙏", "😮", "💩", "✨", "🚀"};
        for (String emoji : emojis) {
            JButton btn = new JButton(emoji);
            btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(false);
            btn.addActionListener(e -> {
                input.setText(input.getText() + emoji);
                popup.setVisible(false);
                input.requestFocus();
            });
            emojiPanel.add(btn);
        }

        popup.add(emojiPanel);
        return popup;
    }

    private void applyTheme(String theme) {
        switch (theme) {
            case "Emerald":
                accentColor = new Color(46, 204, 113);
                sentBubbleColor = new Color(210, 245, 220);
                sentTextColor = Color.BLACK;
                chat.setBackground(Color.WHITE);
                break;
            case "Ocean Blue":
                accentColor = new Color(52, 152, 219);
                sentBubbleColor = new Color(210, 230, 250);
                sentTextColor = Color.BLACK;
                chat.setBackground(Color.WHITE);
                break;
            case "Purple":
                accentColor = new Color(155, 89, 182);
                sentBubbleColor = new Color(237, 210, 245);
                sentTextColor = Color.BLACK;
                chat.setBackground(Color.WHITE);
                break;
            case "Dark Mode":
                accentColor = new Color(0, 168, 132);
                sentBubbleColor = new Color(5, 71, 64);
                sentTextColor = Color.WHITE;
                chat.setBackground(new Color(18, 27, 34));
                break;
        }

        send.setBackground(accentColor);
        chat.revalidate();
        chat.repaint();
    }

    private void connect() {
        new Thread(() -> {
            try {
                Socket s = new Socket("localhost", 5000);

                in = new DataInputStream(s.getInputStream());
                out = new DataOutputStream(s.getOutputStream());

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
        boolean isDark = themePicker.getSelectedItem().equals("Dark Mode");

        JPanel wrapper = new JPanel(new FlowLayout(
                sent ? FlowLayout.RIGHT : FlowLayout.LEFT, 8, 4));
        wrapper.setOpaque(false);

        JPanel bubble = new JPanel();
        bubble.setLayout(new BoxLayout(bubble, BoxLayout.Y_AXIS));

        if (sent) {
            bubble.setBackground(sentBubbleColor);
        } else {
            bubble.setBackground(isDark ? new Color(32, 44, 51) : new Color(235, 235, 235));
        }

        bubble.setBorder(BorderFactory.createEmptyBorder(7, 11, 5, 11));

        JTextArea text = new JTextArea(msg);
        text.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        text.setForeground(sent ? sentTextColor : (isDark ? Color.WHITE : Color.BLACK));
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
        time.setForeground(isDark ? new Color(170, 170, 170) : Color.GRAY);
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
        SwingUtilities.invokeLater(Client::new);
    }
}