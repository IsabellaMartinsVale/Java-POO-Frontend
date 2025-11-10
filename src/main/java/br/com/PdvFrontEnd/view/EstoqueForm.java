package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Estoque;
import br.com.PdvFrontEnd.service.EstoqueService;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EstoqueForm extends JFrame {
    // Paleta: Vermelho, Amarelo e Branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color ACCENT_COLOR = new Color(255, 152, 0); // Amarelo alaranjado
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Texto escuro
    private static final Color BACKGROUND_COLOR = Color.WHITE; // Fundo branco
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro

    private JTextField txtQuantidade;
    private JTextField txtLocalTanque;
    private JTextField txtLocalEndereco;
    private JTextField txtLoteFabricacao;
    private JTextField txtDataValidade;
    private EstoqueService estoqueService;
    private EstoqueList estoqueList;
    private Estoque estoqueEmEdicao;

    public EstoqueForm(EstoqueService service, EstoqueList list) {
        this(service, list, null);
    }

    public EstoqueForm(EstoqueService service, EstoqueList list, Estoque estoque) {
        this.estoqueService = service;
        this.estoqueList = list;
        this.estoqueEmEdicao = estoque;

        setTitle(estoque == null ? "Cadastro de Estoque" : "Editar Estoque");
        getContentPane().setBackground(BACKGROUND_COLOR);
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(new JLabel("Quantidade:"));


        txtQuantidade = new JTextField();
        txtQuantidade.setBackground(Color.WHITE);
        txtQuantidade.setForeground(Color.BLACK);
        txtQuantidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtQuantidade.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtQuantidade);

        mainPanel.add(new JLabel("Local Tanque:"));
        txtLocalTanque = new JTextField();
        txtLocalTanque.setBackground(Color.WHITE);
        txtLocalTanque.setForeground(Color.BLACK);
        txtLocalTanque.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtLocalTanque.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtLocalTanque);

        mainPanel.add(new JLabel("Local Endereço:"));
        txtLocalEndereco = new JTextField();
        txtLocalEndereco.setBackground(Color.WHITE);
        txtLocalEndereco.setForeground(Color.BLACK);
        txtLocalEndereco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtLocalEndereco.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtLocalEndereco);

        mainPanel.add(new JLabel("Lote Fabricação:"));
        txtLoteFabricacao = new JTextField();
        txtLoteFabricacao.setBackground(Color.WHITE);
        txtLoteFabricacao.setForeground(Color.BLACK);
        txtLoteFabricacao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtLoteFabricacao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtLoteFabricacao);

        mainPanel.add(new JLabel("Data de Validade (dd/MM/yyyy):"));
        txtDataValidade = new JTextField();
        txtDataValidade.setBackground(Color.WHITE);
        txtDataValidade.setForeground(Color.BLACK);
        txtDataValidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDataValidade.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtDataValidade);

        // Se estiver editando, preencher os campos
        if (estoqueEmEdicao != null) {
            txtQuantidade.setText(estoqueEmEdicao.getQuantidade().toString());
            txtLocalTanque.setText(estoqueEmEdicao.getLocalTanque());
            txtLocalEndereco.setText(estoqueEmEdicao.getLocalEndereco());
            txtLoteFabricacao.setText(estoqueEmEdicao.getLoteFabricacao());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            txtDataValidade.setText(dateFormat.format(estoqueEmEdicao.getDataValidade()));
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnSalvar = createStyledButton("Salvar", PRIMARY_COLOR, Color.WHITE);
        JButton btnCancelar = createStyledButton("Cancelar", SECONDARY_COLOR, TEXT_COLOR);

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarEstoque());
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

    private void salvarEstoque() {
        try {
            BigDecimal quantidade = new BigDecimal(txtQuantidade.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dataValidade = dateFormat.parse(txtDataValidade.getText());

            if (estoqueEmEdicao != null) {
                // Modo edição
                Estoque estoqueAtualizado = new Estoque(
                        null, // ID será definido pelo backend
                        quantidade,
                        txtLocalTanque.getText(),
                        txtLocalEndereco.getText(),
                        txtLoteFabricacao.getText(),
                        dataValidade
                );
                estoqueService.updateEstoque(estoqueEmEdicao.getId(), estoqueAtualizado);
            } else {
                // Modo criação
                Estoque estoque = new Estoque(
                        null, // ID será definido pelo backend
                        quantidade,
                        txtLocalTanque.getText(),
                        txtLocalEndereco.getText(),
                        txtLoteFabricacao.getText(),
                        dataValidade
                );
                estoqueService.addEstoque(estoque);
            }
            estoqueList.atualizarTabela();
            dispose();
        } catch (NumberFormatException | ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro de formato de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
