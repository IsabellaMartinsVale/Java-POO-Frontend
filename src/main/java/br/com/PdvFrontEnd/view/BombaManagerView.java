package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Bomba;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BombaManagerView extends JFrame {
    // Paleta: Vermelho, Amarelo e Branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color ACCENT_COLOR = new Color(255, 152, 0); // Amarelo alaranjado
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Texto escuro
    private static final Color BACKGROUND_COLOR = Color.WHITE; // Fundo branco
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro
    private static final Color ACTIVE_COLOR = new Color(40, 167, 69); // Verde para status ativo

    private Bomba bomba;
    private int numeroBomba;

    public BombaManagerView(int numeroBomba) {
        this.numeroBomba = numeroBomba;
        this.bomba = new Bomba();
        bomba.setNumeroBomba(numeroBomba);
        initComponents();
    }

    private void initComponents() {
        setTitle("Bomba " + numeroBomba + " - Sistema PDV");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_COLOR);
        setLayout(new BorderLayout(10, 10));

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Título com número da bomba
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("BOMBA " + numeroBomba);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitle.setForeground(TEXT_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(lblTitle);

        // Indicador de status
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statusPanel.setOpaque(false);
        JLabel lblStatus = new JLabel("● ATIVA");
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblStatus.setForeground(ACTIVE_COLOR);
        statusPanel.add(lblStatus);
        headerPanel.add(statusPanel);

        mainPanel.add(headerPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Seleção de Combustível
        JLabel lblCombustivel = new JLabel("Selecione o Combustível:");
        lblCombustivel.setForeground(TEXT_COLOR);
        lblCombustivel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblCombustivel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblCombustivel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Radio buttons para combustível
        JPanel fuelPanel = new JPanel();
        fuelPanel.setLayout(new BoxLayout(fuelPanel, BoxLayout.Y_AXIS));
        fuelPanel.setOpaque(false);

        JRadioButton rbGasolina = new JRadioButton("Gasolina (R$ 5,89/L)");
        rbGasolina.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rbGasolina.setForeground(TEXT_COLOR);
        rbGasolina.setOpaque(false);
        rbGasolina.setSelected(true);

        JRadioButton rbEtanol = new JRadioButton("Etanol (R$ 3,79/L)");
        rbEtanol.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rbEtanol.setForeground(TEXT_COLOR);
        rbEtanol.setOpaque(false);

        ButtonGroup fuelGroup = new ButtonGroup();
        fuelGroup.add(rbGasolina);
        fuelGroup.add(rbEtanol);

        JPanel radioPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        radioPanel1.setOpaque(false);
        radioPanel1.add(rbGasolina);

        JPanel radioPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        radioPanel2.setOpaque(false);
        radioPanel2.add(rbEtanol);

        fuelPanel.add(radioPanel1);
        fuelPanel.add(radioPanel2);
        mainPanel.add(fuelPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Quantidade em Litros
        JLabel lblLitros = new JLabel("Quantidade (Litros):");
        lblLitros.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblLitros.setForeground(TEXT_COLOR);
        lblLitros.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblLitros);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JSpinner spinnerLitros = new JSpinner(new SpinnerNumberModel(10.0, 0.1, 1000.0, 0.1));
        spinnerLitros.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        spinnerLitros.setMaximumSize(new Dimension(200, 40));
        spinnerLitros.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel spinnerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        spinnerPanel.setOpaque(false);
        spinnerPanel.add(spinnerLitros);
        mainPanel.add(spinnerPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Valor a Pagar
        JLabel lblValor = new JLabel("Valor a Pagar: R$ 58,90");
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblValor.setForeground(ACCENT_COLOR);
        lblValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblValor);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Atualizar valor quando mudar combustível ou quantidade
        ActionListener priceUpdater = e -> {
            double litros = (double) spinnerLitros.getValue();
            double pricePerLiter = rbGasolina.isSelected() ? 5.89 : 3.79;
            double totalPrice = litros * pricePerLiter;
            lblValor.setText("Valor a Pagar: R$ " + new DecimalFormat("#,##0.00").format(totalPrice));
        };

        rbGasolina.addActionListener(priceUpdater);
        rbEtanol.addActionListener(priceUpdater);
        spinnerLitros.addChangeListener(e -> priceUpdater.actionPerformed(null));

        // Botão Finalizar Venda (Verde)
        JButton btnFinalizar = new JButton("FINALIZAR VENDA");
        btnFinalizar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFinalizar.setMaximumSize(new Dimension(350, 50));
        btnFinalizar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnFinalizar.setBackground(ACTIVE_COLOR);
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setFocusPainted(false);
        btnFinalizar.setBorderPainted(false);
        btnFinalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFinalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFinalizar.setBackground(ACTIVE_COLOR.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFinalizar.setBackground(ACTIVE_COLOR);
            }
        });
        btnFinalizar.addActionListener(e -> {
            String fuelType = rbGasolina.isSelected() ? "Gasolina" : "Etanol";
            double litros = (double) spinnerLitros.getValue();
            double pricePerLiter = rbGasolina.isSelected() ? 5.89 : 3.79;
            double basePrice = litros * pricePerLiter;
            double finalPrice = rbGasolina.isSelected() ? basePrice * 1.34 : basePrice;

            bomba.setCombustivel(fuelType);
            bomba.setLitros(litros);
            bomba.setValorTotal(finalPrice);

            showReceiptDialog(fuelType, litros, pricePerLiter, basePrice, finalPrice);
        });
        mainPanel.add(btnFinalizar);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void showReceiptDialog(String fuelType, double liters, double pricePerLiter, double basePrice, double finalPrice) {
        JDialog receiptDialog = new JDialog(this, "Nota Fiscal - Bomba " + numeroBomba, true);
        receiptDialog.setSize(500, 550);
        receiptDialog.getContentPane().setBackground(Color.WHITE);
        receiptDialog.setLocationRelativeTo(this);
        receiptDialog.setLayout(new BorderLayout(10, 10));

        JTextArea receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Courier New", Font.PLAIN, 13));
        receiptArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String cnpj = "12.345.678/0001-90";
        String emissionDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        DecimalFormat df = new DecimalFormat("#,##0.00");

        StringBuilder receiptText = new StringBuilder();
        receiptText.append("═══════════════════════════════════════════════\n");
        receiptText.append("              POSTO PDV COMBUSTÍVEL            \n");
        receiptText.append("═══════════════════════════════════════════════\n");
        receiptText.append("CNPJ: ").append(cnpj).append("\n");
        receiptText.append("Rua Exemplo, 123 - Centro\n");
        receiptText.append("Tel: (11) 1234-5678\n");
        receiptText.append("═══════════════════════════════════════════════\n");
        receiptText.append("         CUPOM FISCAL ELETRÔNICO - CF-e        \n");
        receiptText.append("═══════════════════════════════════════════════\n\n");
        receiptText.append("BOMBA Nº: ").append(numeroBomba).append("\n");
        receiptText.append("Data/Hora: ").append(emissionDate).append("\n");
        receiptText.append("Frentista: ").append(br.com.PdvFrontEnd.util.SessionManager.getInstance().getCurrentUsername()).append("\n\n");
        receiptText.append("───────────────────────────────────────────────\n");
        receiptText.append(" PRODUTO      QTD   UN   VL UNIT     VL TOTAL  \n");
        receiptText.append("───────────────────────────────────────────────\n");
        receiptText.append(String.format(" %-12s %.2f L  R$ %6s  R$ %8s\n",
                fuelType, liters, df.format(pricePerLiter), df.format(basePrice)));
        receiptText.append("───────────────────────────────────────────────\n\n");

        receiptText.append(String.format("%-30s R$ %10s\n", "Subtotal:", df.format(basePrice)));

        if (fuelType.equals("Gasolina")) {
            double tributos = basePrice * 0.34;
            receiptText.append(String.format("%-30s R$ %10s\n", "Tributos Aprox. (34%):", df.format(tributos)));
            receiptText.append("───────────────────────────────────────────────\n");
        } else {
            receiptText.append("───────────────────────────────────────────────\n");
        }

        receiptText.append(String.format("%-30s R$ %10s\n", "VALOR TOTAL:", df.format(finalPrice)));
        receiptText.append("═══════════════════════════════════════════════\n\n");
        receiptText.append("        OBRIGADO PELA PREFERÊNCIA!            \n");
        receiptText.append("            VOLTE SEMPRE!                     \n");
        receiptText.append("═══════════════════════════════════════════════\n");

        receiptArea.setText(receiptText.toString());

        JScrollPane scrollPane = new JScrollPane(receiptArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        receiptDialog.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnPrint = createReceiptButton("Imprimir", PRIMARY_COLOR, Color.WHITE);
        btnPrint.addActionListener(e -> {
            JOptionPane.showMessageDialog(receiptDialog,
                    "Nota fiscal enviada para impressão!",
                    "Impressão",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        JButton btnClose = createReceiptButton("Fechar", SECONDARY_COLOR, TEXT_COLOR);
        btnClose.addActionListener(e -> receiptDialog.dispose());

        buttonPanel.add(btnPrint);
        buttonPanel.add(btnClose);
        receiptDialog.add(buttonPanel, BorderLayout.SOUTH);

        receiptDialog.setVisible(true);
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

    private JButton createReceiptButton(String text, Color bgColor, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }
}

