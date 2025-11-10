package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Produto;
import br.com.PdvFrontEnd.service.ProdutoService;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class ProdutoForm extends JFrame {
    // Paleta: Vermelho, Amarelo e Branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color ACCENT_COLOR = new Color(255, 152, 0); // Amarelo alaranjado
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Texto escuro
    private static final Color BACKGROUND_COLOR = Color.WHITE; // Fundo branco
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro

    private JTextField txtNome;
    private JTextField txtReferencia;
    private JTextField txtFornecedor;
    private JTextField txtCategoria;
    private JTextField txtMarca;
    private ProdutoService produtoService;
    private ProdutoList produtoList;
    private Produto produtoEmEdicao;

    public ProdutoForm(ProdutoService service, ProdutoList list) {
        this(service, list, null);
    }

    public ProdutoForm(ProdutoService service, ProdutoList list, Produto produto) {
        this.produtoService = service;
        this.produtoList = list;
        this.produtoEmEdicao = produto;

        setTitle(produto == null ? "Cadastro de Produto" : "Editar Produto");
        getContentPane().setBackground(BACKGROUND_COLOR);
        setSize(450, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(new JLabel("Nome:"));


        txtNome = new JTextField();
        txtNome.setBackground(Color.WHITE);
        txtNome.setForeground(Color.BLACK);
        txtNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNome.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtNome);

        mainPanel.add(new JLabel("Referência:"));
        txtReferencia = new JTextField();
        txtReferencia.setBackground(Color.WHITE);
        txtReferencia.setForeground(Color.BLACK);
        txtReferencia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtReferencia.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtReferencia);

        mainPanel.add(new JLabel("Fornecedor:"));
        txtFornecedor = new JTextField();
        txtFornecedor.setBackground(Color.WHITE);
        txtFornecedor.setForeground(Color.BLACK);
        txtFornecedor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtFornecedor.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtFornecedor);

        mainPanel.add(new JLabel("Categoria:"));
        txtCategoria = new JTextField();
        txtCategoria.setBackground(Color.WHITE);
        txtCategoria.setForeground(Color.BLACK);
        txtCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCategoria.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtCategoria);

        mainPanel.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        txtMarca.setBackground(Color.WHITE);
        txtMarca.setForeground(Color.BLACK);
        txtMarca.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMarca.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtMarca);

        // Se estiver editando, preencher os campos
        if (produtoEmEdicao != null) {
            txtNome.setText(produtoEmEdicao.getNome());
            txtReferencia.setText(produtoEmEdicao.getReferencia());
            txtFornecedor.setText(produtoEmEdicao.getFornecedor());
            txtCategoria.setText(produtoEmEdicao.getCategoria());
            txtMarca.setText(produtoEmEdicao.getMarca());
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnSalvar = createStyledButton("Salvar", PRIMARY_COLOR, Color.WHITE);
        JButton btnCancelar = createStyledButton("Cancelar", SECONDARY_COLOR, TEXT_COLOR);

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarProduto());
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

    private void salvarProduto() {
        if (produtoEmEdicao != null) {
            // Modo edição
            Produto produtoAtualizado = new Produto(
                    txtNome.getText(),
                    txtReferencia.getText(),
                    txtFornecedor.getText(),
                    txtCategoria.getText(),
                    txtMarca.getText()
            );
            produtoService.updateProduto(produtoEmEdicao.getId(), produtoAtualizado);
        } else {
            // Modo criação
            Produto produto = new Produto(
                    txtNome.getText(),
                    txtReferencia.getText(),
                    txtFornecedor.getText(),
                    txtCategoria.getText(),
                    txtMarca.getText()
            );
            produtoService.addProduto(produto);
        }
        produtoList.atualizarTabela();
        dispose();
    }
}
