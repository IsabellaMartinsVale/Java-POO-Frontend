package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Pessoa;
import br.com.PdvFrontEnd.service.PessoaService;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class PessoaForm extends JFrame {
    // Paleta: Vermelho, Amarelo e Branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color ACCENT_COLOR = new Color(255, 152, 0); // Amarelo alaranjado
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Texto escuro
    private static final Color BACKGROUND_COLOR = Color.WHITE; // Fundo branco
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtDataNascimento;
    private JComboBox<String> comboTipo;
    private PessoaService pessoaService;
    private PessoaList pessoaList;
    private Pessoa pessoaEmEdicao;

    public PessoaForm(PessoaService service, PessoaList list) {
        this(service, list, null);
    }

    public PessoaForm(PessoaService service, PessoaList list, Pessoa pessoa) {
        this.pessoaService = service;
        this.pessoaList = list;
        this.pessoaEmEdicao = pessoa;

        setTitle(pessoa == null ? "Nova Pessoa" : "Editar Pessoa");
        getContentPane().setBackground(BACKGROUND_COLOR);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
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

        mainPanel.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        txtCpf.setBackground(Color.WHITE);
        txtCpf.setForeground(Color.BLACK);
        txtCpf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCpf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtCpf);

        mainPanel.add(new JLabel("Data de Nascimento:"));
        txtDataNascimento = new JTextField();
        txtDataNascimento.setBackground(Color.WHITE);
        txtDataNascimento.setForeground(Color.BLACK);
        txtDataNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDataNascimento.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtDataNascimento);

        mainPanel.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"FISICA", "JURIDICA"});
        comboTipo.setBackground(Color.WHITE);
        comboTipo.setForeground(Color.BLACK);
        comboTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboTipo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(comboTipo);

        // Se estiver editando, preencher os campos
        if (pessoaEmEdicao != null) {
            txtNome.setText(pessoaEmEdicao.getNome());
            txtCpf.setText(pessoaEmEdicao.getCpf());
            txtDataNascimento.setText(pessoaEmEdicao.getDataNascimento());
            comboTipo.setSelectedItem(pessoaEmEdicao.getTipo());
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnSalvar = createStyledButton("Salvar", PRIMARY_COLOR, Color.WHITE);
        JButton btnCancelar = createStyledButton("Cancelar", SECONDARY_COLOR, TEXT_COLOR);

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarPessoa());
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

    private void salvarPessoa() {
        if (pessoaEmEdicao != null) {
            // Modo edição
            Pessoa pessoaAtualizada = new Pessoa(
                    null,
                    txtNome.getText(),
                    txtCpf.getText(),
                    txtDataNascimento.getText(),
                    (String) comboTipo.getSelectedItem()
            );
            pessoaService.updatePessoa(pessoaEmEdicao.getId(), pessoaAtualizada);
        } else {
            // Modo criação
            Pessoa pessoa = new Pessoa(
                    null,
                    txtNome.getText(),
                    txtCpf.getText(),
                    txtDataNascimento.getText(),
                    (String) comboTipo.getSelectedItem()
            );
            pessoaService.addPessoa(pessoa);
        }
        pessoaList.atualizarTabela();
        dispose();
    }
}
