package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Acesso;
import br.com.PdvFrontEnd.service.AcessoService;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class AcessoForm extends JFrame {
    // Paleta: Vermelho, Amarelo e Branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color ACCENT_COLOR = new Color(255, 152, 0); // Amarelo alaranjado
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Texto escuro
    private static final Color BACKGROUND_COLOR = Color.WHITE; // Fundo branco
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private AcessoService acessoService;
    private AcessoList acessoList;
    private Acesso acessoEmEdicao; // Para rastrear se estamos editando

    public AcessoForm(AcessoService service, AcessoList list) {
        this(service, list, null);
    }

    public AcessoForm(AcessoService service, AcessoList list, Acesso acesso) {
        this.acessoService = service;
        this.acessoList = list;
        this.acessoEmEdicao = acesso;

        setTitle(acesso == null ? "Cadastro de Acesso" : "Editar Acesso");
        getContentPane().setBackground(BACKGROUND_COLOR);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(new JLabel("Usuário:"));


        txtUsuario = new JTextField();
        txtUsuario.setBackground(Color.WHITE);
        txtUsuario.setForeground(Color.BLACK);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtUsuario);

        mainPanel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        txtSenha.setBackground(Color.WHITE);
        txtSenha.setForeground(Color.BLACK);
        txtSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSenha.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtSenha);

        // Se estiver editando, preencher os campos
        if (acessoEmEdicao != null) {
            txtUsuario.setText(acessoEmEdicao.getUsuario());
            txtSenha.setText(acessoEmEdicao.getSenha());
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnSalvar = createStyledButton("Salvar", PRIMARY_COLOR, Color.WHITE);
        JButton btnCancelar = createStyledButton("Cancelar", SECONDARY_COLOR, TEXT_COLOR);

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarAcesso());
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

    private void salvarAcesso() {
        if (acessoEmEdicao != null) {
            // Modo edição
            Acesso acessoAtualizado = new Acesso(
                    txtUsuario.getText(),
                    new String(txtSenha.getPassword())
            );
            acessoService.updateAcesso(acessoEmEdicao.getId(), acessoAtualizado);
        } else {
            // Modo criação
            Acesso acesso = new Acesso(
                    txtUsuario.getText(),
                    new String(txtSenha.getPassword())
            );
            acessoService.addAcesso(acesso);
        }
        acessoList.atualizarTabela();
        dispose();
    }
}
