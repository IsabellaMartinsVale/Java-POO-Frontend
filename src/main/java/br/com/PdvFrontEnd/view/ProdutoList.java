package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Produto;
import br.com.PdvFrontEnd.service.ProdutoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.util.List;

public class ProdutoList extends JFrame {
    private final ProdutoService produtoService;
    private JTable table;

    // Paleta: Vermelho, Amarelo e Branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color ACCENT_COLOR = new Color(255, 152, 0); // Amarelo alaranjado
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Texto escuro
    private static final Color BACKGROUND_COLOR = Color.WHITE; // Fundo branco
    private static final Color TABLE_HEADER_COLOR = PRIMARY_COLOR; // Vermelho para cabeçalho
    private static final Color TABLE_SELECTION_COLOR = new Color(255, 235, 59, 100); // Amarelo transparente
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro

    public ProdutoList(ProdutoService service) {
        this.produtoService = service;
        initComponents();
    }

    private void initComponents() {
        setTitle("Gerenciamento de Produtos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1100, 550);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel("GERENCIAMENTO DE PRODUTOS", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setOpaque(true);
        header.setBackground(PRIMARY_COLOR);
        header.setForeground(Color.WHITE);
        header.setBorder(new EmptyBorder(20, 0, 20, 0));
        mainPanel.add(header, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setGridColor(new Color(222, 226, 230));
        table.setSelectionBackground(TABLE_SELECTION_COLOR);
        table.setSelectionForeground(TEXT_COLOR);
        table.getTableHeader().setBackground(TABLE_HEADER_COLOR);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnAdicionar = criarBotao("Adicionar", PRIMARY_COLOR, Color.WHITE);
        JButton btnEditar = criarBotao("Editar", SECONDARY_COLOR, TEXT_COLOR);
        JButton btnRemover = criarBotao("Remover", PRIMARY_COLOR, Color.WHITE);
        JButton btnAtualizar = criarBotao("Atualizar", SECONDARY_COLOR, TEXT_COLOR);

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnAtualizar);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        btnAdicionar.addActionListener(e -> {
            ProdutoForm form = new ProdutoForm(produtoService, this);
            form.setVisible(true);
        });

        btnEditar.addActionListener(e -> editarProduto(e));
        btnRemover.addActionListener(e -> removerProduto(e));
        btnAtualizar.addActionListener(e -> atualizarTabela());

        atualizarTabela();
    }

    private JButton criarBotao(String texto, Color fundo, Color textoCor) {
        JButton btn = new JButton(texto);
        btn.setBackground(fundo);
        btn.setForeground(textoCor);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(fundo.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(fundo);
            }
        });
        return btn;
    }

    private void editarProduto(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            List<Produto> produtos = produtoService.getAllProdutos();
            if (selectedRow < produtos.size()) {
                Produto produto = produtos.get(selectedRow);
                if (produto.getId() != null) {
                    ProdutoForm form = new ProdutoForm(produtoService, this, produto);
                    form.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Produto não possui ID válido!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removerProduto(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            List<Produto> produtos = produtoService.getAllProdutos();
            if (selectedRow < produtos.size()) {
                Produto produto = produtos.get(selectedRow);
                if (produto.getId() != null) {
                    produtoService.removeProduto(produto.getId());
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Produto não possui ID válido!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void atualizarTabela() {
        String[] colunas = {"Nome", "Referência", "Fornecedor", "Categoria", "Marca"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        List<Produto> produtos = produtoService.getAllProdutos();
        for (Produto produto : produtos) {
            model.addRow(new Object[]{
                    produto.getNome(),
                    produto.getReferencia(),
                    produto.getFornecedor(),
                    produto.getCategoria(),
                    produto.getMarca()
            });
        }

        table.setModel(model);
    }
}
