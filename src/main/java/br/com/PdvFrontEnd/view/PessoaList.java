package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Pessoa;
import br.com.PdvFrontEnd.service.PessoaService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PessoaList extends JFrame {
    private final PessoaService pessoaService;
    private JTable table;

    // Paleta: Vermelho, Amarelo e Branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color ACCENT_COLOR = new Color(255, 152, 0); // Amarelo alaranjado
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Texto escuro
    private static final Color BACKGROUND_COLOR = Color.WHITE; // Fundo branco
    private static final Color TABLE_HEADER_COLOR = PRIMARY_COLOR; // Vermelho para cabeçalho
    private static final Color TABLE_SELECTION_COLOR = new Color(255, 235, 59, 100); // Amarelo claro transparente
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro para hover

    public PessoaList(PessoaService service) {
        this.pessoaService = service;
        initComponents();
    }

    private void initComponents() {
        setTitle("Gerenciamento de Pessoas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 550);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel("GERENCIAMENTO DE PESSOAS", SwingConstants.CENTER);
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

        JButton btnAdicionar = criarBotao("Adicionar", SECONDARY_COLOR, TEXT_COLOR);
        JButton btnEditar = criarBotao("Editar", PRIMARY_COLOR, Color.WHITE);
        JButton btnRemover = criarBotao("Remover", PRIMARY_COLOR, Color.WHITE);
        JButton btnAtualizar = criarBotao("Atualizar", SECONDARY_COLOR, TEXT_COLOR);

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnAtualizar);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        btnAdicionar.addActionListener(this::adicionarPessoa);
        btnEditar.addActionListener(this::editarPessoa);
        btnRemover.addActionListener(this::removerPessoa);
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

    private void adicionarPessoa(ActionEvent e) {
        PessoaForm form = new PessoaForm(pessoaService, this);
        form.setVisible(true);
    }

    private void editarPessoa(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            List<Pessoa> pessoas = pessoaService.listPessoas();
            if (selectedRow < pessoas.size()) {
                Pessoa pessoa = pessoas.get(selectedRow);
                PessoaForm form = new PessoaForm(pessoaService, this, pessoa);
                form.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma pessoa para editar!");
        }
    }

    private void removerPessoa(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Pegar a pessoa da lista usando o índice da linha
            List<Pessoa> pessoas = pessoaService.listPessoas();
            if (selectedRow < pessoas.size()) {
                Pessoa pessoa = pessoas.get(selectedRow);
                if (pessoa.getId() != null) {
                    pessoaService.removePessoa(pessoa.getId());
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Pessoa não possui ID válido!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma pessoa para remover!");
        }
    }

    void atualizarTabela() {
        String[] colunas = {"Nome", "CPF", "Data de Nascimento", "Tipo"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (Pessoa p : pessoaService.listPessoas()) {
            model.addRow(new Object[]{
                    p.getNome(),
                    p.getCpf(),
                    p.getDataNascimento(),
                    p.getTipo()
            });
        }

        table.setModel(model);
    }
}
