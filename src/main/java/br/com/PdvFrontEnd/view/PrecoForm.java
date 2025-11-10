package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Preco;
import br.com.PdvFrontEnd.service.PrecoService;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrecoForm extends JFrame {
    // Paleta: Vermelho, Amarelo e Branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color ACCENT_COLOR = new Color(255, 152, 0); // Amarelo alaranjado
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Texto escuro
    private static final Color BACKGROUND_COLOR = Color.WHITE; // Fundo branco
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro

    private JTextField txtValor;
    private JTextField txtDataAlteracao;
    private JTextField txtHoraAlteracao;
    private PrecoService precoService;
    private PrecoList precoList;
    private Preco precoEmEdicao;

    public PrecoForm(PrecoService service, PrecoList list) {
        this(service, list, null);
    }

    public PrecoForm(PrecoService service, PrecoList list, Preco preco) {
        this.precoService = service;
        this.precoList = list;
        this.precoEmEdicao = preco;

        setTitle(preco == null ? "Cadastro de Preço" : "Editar Preço");
        getContentPane().setBackground(BACKGROUND_COLOR);
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(new JLabel("Valor:"));


        txtValor = new JTextField();
        txtValor.setBackground(Color.WHITE);
        txtValor.setForeground(Color.BLACK);
        txtValor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtValor.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtValor);

        mainPanel.add(new JLabel("Data de Alteração (dd/MM/yyyy):"));
        txtDataAlteracao = new JTextField();
        txtDataAlteracao.setBackground(Color.WHITE);
        txtDataAlteracao.setForeground(Color.BLACK);
        txtDataAlteracao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDataAlteracao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtDataAlteracao);

        mainPanel.add(new JLabel("Hora de Alteração (HH:mm:ss):"));
        txtHoraAlteracao = new JTextField();
        txtHoraAlteracao.setBackground(Color.WHITE);
        txtHoraAlteracao.setForeground(Color.BLACK);
        txtHoraAlteracao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtHoraAlteracao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtHoraAlteracao);

        // Se estiver editando, preencher os campos
        if (precoEmEdicao != null) {
            txtValor.setText(precoEmEdicao.getValor().toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            txtDataAlteracao.setText(dateFormat.format(precoEmEdicao.getDataAlteracao()));
            txtHoraAlteracao.setText(timeFormat.format(precoEmEdicao.getHoraAlteracao()));
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnSalvar = createStyledButton("Salvar", PRIMARY_COLOR, Color.WHITE);
        JButton btnCancelar = createStyledButton("Cancelar", SECONDARY_COLOR, TEXT_COLOR);

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarPreco());
        btnCancelar.addActionListener(e -> dispose());
    }

    private JButton createStyledButton(String text, Color bgColor, Color textColor) {
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

    private void salvarPreco() {
        try {
            BigDecimal valor = new BigDecimal(txtValor.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            Date dataAlteracao = dateFormat.parse(txtDataAlteracao.getText());
            Date horaAlteracao = timeFormat.parse(txtHoraAlteracao.getText());

            if (precoEmEdicao != null) {
                // Modo edição
                Preco precoAtualizado = new Preco(valor, dataAlteracao, horaAlteracao);
                precoService.updatePreco(precoEmEdicao.getId(), precoAtualizado);
            } else {
                // Modo criação
                Preco preco = new Preco(valor, dataAlteracao, horaAlteracao);
                precoService.addPreco(preco);
            }
            precoList.atualizarTabela();
            dispose();
        } catch (NumberFormatException | ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro de formato de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
