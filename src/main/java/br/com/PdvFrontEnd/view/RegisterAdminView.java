package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.service.AcessoService;

import javax.swing.*;
import java.awt.*;

public class RegisterAdminView extends JFrame {
    // Paleta: Vermelho, Amarelo e Branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Texto escuro
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private AcessoService acessoService;

    public RegisterAdminView() {
        this.acessoService = new AcessoService();
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro Administrador - Sistema PDV");
        setSize(450, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fundo branco simples
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(15, 15));

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Cabeçalho simples
        JLabel lblTitle = new JLabel("Cadastrar Administrador");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(PRIMARY_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JLabel lblSubtitle = new JLabel("Configure o acesso de administrador único");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubtitle.setForeground(new Color(108, 117, 125));
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblSubtitle);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Campo Username
        JLabel lblUsername = new JLabel("Nome de Usuário:");
        lblUsername.setForeground(TEXT_COLOR);
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblUsername);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        txtUsername = createStyledTextField();
        txtUsername.setMaximumSize(new Dimension(350, 40));
        txtUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(txtUsername);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 18)));

        // Campo Password
        JLabel lblPassword = new JLabel("Senha de Acesso:");
        lblPassword.setForeground(TEXT_COLOR);
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        txtPassword = createStyledPasswordField();
        txtPassword.setMaximumSize(new Dimension(350, 40));
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(txtPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 18)));

        // Campo Confirmar Password
        JLabel lblConfirmPassword = new JLabel("Confirmar Senha:");
        lblConfirmPassword.setForeground(TEXT_COLOR);
        lblConfirmPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblConfirmPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblConfirmPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        txtConfirmPassword = createStyledPasswordField();
        txtConfirmPassword.setMaximumSize(new Dimension(350, 40));
        txtConfirmPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(txtConfirmPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Painel de Botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setMaximumSize(new Dimension(370, 50));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botão Cadastrar (Vermelho)
        JButton btnRegister = createModernButton("Cadastrar Admin", PRIMARY_COLOR);
        btnRegister.setPreferredSize(new Dimension(165, 42));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.addActionListener(e -> handleRegister());
        buttonPanel.add(btnRegister);

        // Botão Voltar (Amarelo)
        JButton btnBack = createModernButton("Voltar", SECONDARY_COLOR);
        btnBack.setPreferredSize(new Dimension(165, 42));
        btnBack.setForeground(TEXT_COLOR);
        btnBack.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });
        buttonPanel.add(btnBack);

        mainPanel.add(buttonPanel);
        add(mainPanel, BorderLayout.CENTER);

        // Enter para cadastrar
        txtConfirmPassword.addActionListener(e -> handleRegister());
    }

    private void handleRegister() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        // Validações
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha usuário e senha!",
                    "Campos Obrigatórios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (username.length() < 3) {
            JOptionPane.showMessageDialog(this,
                    "O usuário deve ter no mínimo 3 caracteres!",
                    "Usuário Inválido",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.length() < 4) {
            JOptionPane.showMessageDialog(this,
                    "A senha deve ter no mínimo 4 caracteres!",
                    "Senha Inválida",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "As senhas não conferem!",
                    "Erro na Confirmação",
                    JOptionPane.ERROR_MESSAGE);
            txtConfirmPassword.setText("");
            return;
        }

        // Cadastra o administrador SEM vincular pessoa
        // O backend já valida se o usuário existe
        try {
            acessoService.addAcessoComPessoa(
                username,
                password,
                null, // SEM pessoa vinculada
                "ADMIN"
            );

            JOptionPane.showMessageDialog(this,
                    "Administrador cadastrado com sucesso!\n\n" +
                    "Usuário: " + username + "\n\n" +
                    "Você já pode fazer login como Administrador.",
                    "Cadastro Completo",
                    JOptionPane.INFORMATION_MESSAGE);

            new LoginView().setVisible(true);
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao cadastrar administrador:\n" + ex.getMessage(),
                    "Erro no Cadastro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private JTextField createStyledTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218), 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return field;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField field = new JPasswordField(20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218), 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return field;
    }

    private JButton createModernButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Efeito hover
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

