package br.com.PdvFrontEnd.view;

import javax.swing.*;
import java.awt.*;

public class BombaListView extends JFrame {
    // Paleta: Vermelho, Amarelo e Branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color ACCENT_COLOR = new Color(255, 152, 0); // Amarelo alaranjado
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Texto escuro
    private static final Color ACTIVE_COLOR = new Color(40, 167, 69); // Verde para status ativo
    private static final Color BACKGROUND_COLOR = Color.WHITE; // Fundo branco
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro

    public BombaListView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Gerenciamento de Bombas - Sistema PDV");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout());

        // Cabeçalho redesenhado
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JLabel lblTitle = new JLabel("GERENCIAMENTO DE BOMBAS");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle, BorderLayout.WEST);

        JLabel lblSubtitle = new JLabel("Sistema PDV");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(new Color(255, 255, 255, 180));
        headerPanel.add(lblSubtitle, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);

        // Painel central - layout vertical com lista de bombas
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        // Criar painel para cada bomba em lista
        for (int i = 1; i <= 3; i++) {
            mainPanel.add(createBombaCard(i));
            if (i < 3) {
                mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createBombaCard(int numeroBomba) {
        JPanel card = new JPanel(new BorderLayout(15, 0));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        // Painel esquerdo - Número e status
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        leftPanel.setOpaque(false);

        // Círculo numerado
        JPanel numberCircle = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(SECONDARY_COLOR);
                g2.fillOval(0, 0, 60, 60);
                g2.setColor(TEXT_COLOR);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 24));
                FontMetrics fm = g2.getFontMetrics();
                String num = String.valueOf(numeroBomba);
                int x = (60 - fm.stringWidth(num)) / 2;
                int y = ((60 - fm.getHeight()) / 2) + fm.getAscent();
                g2.drawString(num, x, y);
            }
        };
        numberCircle.setPreferredSize(new Dimension(60, 60));
        numberCircle.setOpaque(false);
        leftPanel.add(numberCircle);

        leftPanel.add(Box.createRigidArea(new Dimension(20, 0)));

        // Info da bomba
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("BOMBA " + numeroBomba);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(TEXT_COLOR);
        infoPanel.add(lblTitle);

        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JPanel statusLine = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        statusLine.setOpaque(false);

        JLabel statusDot = new JLabel("●");
        statusDot.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        statusDot.setForeground(ACTIVE_COLOR);
        statusLine.add(statusDot);

        JLabel lblStatus = new JLabel(" Disponível");
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblStatus.setForeground(ACTIVE_COLOR);
        statusLine.add(lblStatus);

        infoPanel.add(statusLine);
        leftPanel.add(infoPanel);

        card.add(leftPanel, BorderLayout.WEST);

        // Botão à direita
        JButton btnAbastecer = new JButton("ABASTECER");
        btnAbastecer.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAbastecer.setBackground(PRIMARY_COLOR);
        btnAbastecer.setForeground(Color.WHITE);
        btnAbastecer.setFocusPainted(false);
        btnAbastecer.setBorderPainted(false);
        btnAbastecer.setPreferredSize(new Dimension(150, 45));
        btnAbastecer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAbastecer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAbastecer.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAbastecer.setBackground(PRIMARY_COLOR);
            }
        });
        btnAbastecer.addActionListener(e -> new BombaManagerView(numeroBomba).setVisible(true));

        card.add(btnAbastecer, BorderLayout.EAST);

        return card;
    }



    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR.darker(), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Color bg = (Color) button.getClientProperty("originalColor");
                if (bg != null) {
                    button.setBackground(bg);
                } else {
                    button.setBackground(PRIMARY_COLOR);
                }
            }
        });

        button.putClientProperty("originalColor", button.getBackground());
        return button;
    }
}

