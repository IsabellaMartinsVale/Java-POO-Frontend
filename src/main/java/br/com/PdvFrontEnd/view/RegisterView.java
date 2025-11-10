package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Acesso;
import br.com.PdvFrontEnd.model.Pessoa;
import br.com.PdvFrontEnd.service.AcessoService;
import br.com.PdvFrontEnd.service.PessoaService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RegisterView extends JFrame {
    // Paleta: Vermelho, Amarelo e Branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color ACCENT_COLOR = new Color(255, 152, 0); // Amarelo alaranjado
    private static final Color SUCCESS_COLOR = new Color(40, 167, 69); // Verde para sucesso
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Quase preto
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JComboBox<String> cmbPessoas;
    private AcessoService acessoService;
    private PessoaService pessoaService;
    private List<Pessoa> pessoas;

    public RegisterView() {
        this.acessoService = new AcessoService();
        this.pessoaService = new PessoaService();
        initComponents();
        carregarPessoas();
    }

    private void initComponents() {
        setTitle("Cadastrar Frentista - Sistema PDV");
        setSize(480, 520);
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
        JLabel lblTitle = new JLabel("Cadastrar Frentista");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(PRIMARY_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JLabel lblSubtitle = new JLabel("Crie usuário e senha para um frentista");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubtitle.setForeground(new Color(108, 117, 125));
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblSubtitle);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Selecionar Pessoa
        JLabel lblPessoa = new JLabel("Selecionar Frentista:");
        lblPessoa.setForeground(TEXT_COLOR);
        lblPessoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblPessoa.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblPessoa);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        cmbPessoas = new JComboBox<>();
        cmbPessoas.setMaximumSize(new Dimension(360, 40));
        cmbPessoas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbPessoas.setBackground(Color.WHITE);
        cmbPessoas.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        cmbPessoas.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(cmbPessoas);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 18)));

        // Campo Username
        JLabel lblUsername = new JLabel("Usuário de Acesso:");
        lblUsername.setForeground(TEXT_COLOR);
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblUsername);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        txtUsername = new JTextField(20);
        txtUsername.setMaximumSize(new Dimension(360, 40));
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218), 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        mainPanel.add(txtUsername);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 18)));

        // Campo Password
        JLabel lblPassword = new JLabel("Senha de Acesso:");
        lblPassword.setForeground(TEXT_COLOR);
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        txtPassword = new JPasswordField(20);
        txtPassword.setMaximumSize(new Dimension(360, 40));
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218), 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        mainPanel.add(txtPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 18)));

        // Campo Confirmar Password
        JLabel lblConfirmPassword = new JLabel("Confirmar Senha:");
        lblConfirmPassword.setForeground(TEXT_COLOR);
        lblConfirmPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblConfirmPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblConfirmPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        txtConfirmPassword = new JPasswordField(20);
        txtConfirmPassword.setMaximumSize(new Dimension(360, 40));
        txtConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtConfirmPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtConfirmPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218), 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        mainPanel.add(txtConfirmPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Painel de Botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(400, 50));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botão Cadastrar (Verde para sucesso)
        JButton btnRegister = createModernButton("Criar Acesso", SUCCESS_COLOR);
        btnRegister.setPreferredSize(new Dimension(180, 45));
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegister.setForeground(Color.WHITE);
        btnRegister.addActionListener(e -> handleRegister());
        buttonPanel.add(btnRegister);

        // Botão Cancelar (Amarelo)
        JButton btnCancel = createModernButton("Cancelar", SECONDARY_COLOR);
        btnCancel.setPreferredSize(new Dimension(180, 45));
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancel.setForeground(TEXT_COLOR);
        btnCancel.addActionListener(e -> dispose());
        buttonPanel.add(btnCancel);

        mainPanel.add(buttonPanel);

        add(mainPanel, BorderLayout.CENTER);

        // Enter para cadastrar
        txtConfirmPassword.addActionListener(e -> handleRegister());
    }

    private void carregarPessoas() {
        try {
            pessoas = pessoaService.listPessoas();
            cmbPessoas.removeAllItems();
            cmbPessoas.addItem("-- Selecione uma pessoa --");

            for (Pessoa pessoa : pessoas) {
                cmbPessoas.addItem(pessoa.getNome() + " - CPF: " + pessoa.getCpf());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar pessoas: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRegister() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());
        int selectedIndex = cmbPessoas.getSelectedIndex();

        // Validações
        if (selectedIndex <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, selecione uma pessoa!",
                    "Pessoa Não Selecionada",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

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
            txtUsername.requestFocus();
            return;
        }

        if (password.length() < 4) {
            JOptionPane.showMessageDialog(this,
                    "A senha deve ter no mínimo 4 caracteres!",
                    "Senha Inválida",
                    JOptionPane.ERROR_MESSAGE);
            txtPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "As senhas não conferem!",
                    "Erro na Confirmação",
                    JOptionPane.ERROR_MESSAGE);
            txtConfirmPassword.setText("");
            txtConfirmPassword.requestFocus();
            return;
        }

        // Verifica se o usuário já existe
        if (acessoService.usuarioJaExiste(username)) {
            JOptionPane.showMessageDialog(this,
                    "Este usuário já está cadastrado no sistema!",
                    "Usuário Duplicado",
                    JOptionPane.ERROR_MESSAGE);
            txtUsername.requestFocus();
            return;
        }

        // Pega a pessoa selecionada
        Pessoa pessoaSelecionada = pessoas.get(selectedIndex - 1);

        // Cadastra o acesso
        try {
            // Usar o novo método que envia pessoaId e role
            acessoService.addAcessoComPessoa(
                username,
                password,
                pessoaSelecionada.getId(),
                "FRENTISTA" // Sempre FRENTISTA
            );

            JOptionPane.showMessageDialog(this,
                    "Credenciais criadas com sucesso!\n\n" +
                    "Pessoa: " + pessoaSelecionada.getNome() + "\n" +
                    "Usuário: " + username + "\n\n" +
                    "O frentista já pode fazer login no sistema.",
                    "Acesso Criado",
                    JOptionPane.INFORMATION_MESSAGE);

            limparCampos();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao criar acesso:\n" + ex.getMessage(),
                    "Erro no Cadastro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
        cmbPessoas.setSelectedIndex(0);
        txtUsername.requestFocus();
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

