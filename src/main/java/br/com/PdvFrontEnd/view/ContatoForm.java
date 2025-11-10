package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Contato;
import br.com.PdvFrontEnd.service.ContatoService;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class ContatoForm extends JFrame {
    // Paleta: Vermelho, Amarelo e Branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color ACCENT_COLOR = new Color(255, 152, 0); // Amarelo alaranjado
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Texto escuro
    private static final Color BACKGROUND_COLOR = Color.WHITE; // Fundo branco
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro

    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JTextField txtEndereco;
    private ContatoService contatoService;
    private ContatoList contatoList;
    private Contato contatoEmEdicao;

    public ContatoForm(ContatoService service, ContatoList list) {
        this(service, list, null);
    }

    public ContatoForm(ContatoService service, ContatoList list, Contato contato) {
        this.contatoService = service;
        this.contatoList = list;
        this.contatoEmEdicao = contato;

        setTitle(contato == null ? "Cadastro de Contato" : "Editar Contato");
        getContentPane().setBackground(BACKGROUND_COLOR);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(new JLabel("Telefone:"));


        txtTelefone = new JTextField();
        txtTelefone.setBackground(Color.WHITE);
        txtTelefone.setForeground(Color.BLACK);
        txtTelefone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTelefone.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtTelefone);

        mainPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        txtEmail.setBackground(Color.WHITE);
        txtEmail.setForeground(Color.BLACK);
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtEmail.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtEmail);

        mainPanel.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        txtEndereco.setBackground(Color.WHITE);
        txtEndereco.setForeground(Color.BLACK);
        txtEndereco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtEndereco.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtEndereco);

        // Se estiver editando, preencher os campos
        if (contatoEmEdicao != null) {
            txtTelefone.setText(contatoEmEdicao.getTelefone());
            txtEmail.setText(contatoEmEdicao.getEmail());
            txtEndereco.setText(contatoEmEdicao.getEndereco());
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnSalvar = createStyledButton("Salvar", PRIMARY_COLOR, Color.WHITE);
        JButton btnCancelar = createStyledButton("Cancelar", SECONDARY_COLOR, TEXT_COLOR);

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarContato());
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

    private void salvarContato() {
        if (contatoEmEdicao != null) {
            // Modo edição
            Contato contatoAtualizado = new Contato(
                    null, // ID será definido pelo backend
                    txtTelefone.getText(),
                    txtEmail.getText(),
                    txtEndereco.getText()
            );
            contatoService.updateContato(contatoEmEdicao.getId(), contatoAtualizado);
        } else {
            // Modo criação
            Contato contato = new Contato(
                    null, // ID será definido pelo backend
                    txtTelefone.getText(),
                    txtEmail.getText(),
                    txtEndereco.getText()
            );
            contatoService.addContato(contato);
        }
        contatoList.atualizarTabela();
        dispose();
    }
}
