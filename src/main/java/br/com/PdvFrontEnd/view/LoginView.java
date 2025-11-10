package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.util.SessionManager;
import br.com.PdvFrontEnd.service.AcessoService;
import br.com.PdvFrontEnd.model.Acesso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class LoginView extends JFrame {
    // Paleta vermelho, amarelo e branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color ACCENT_COLOR = new Color(255, 152, 0); // Amarelo alaranjado
    private static final Color SUCCESS_COLOR = new Color(255, 235, 59); // Amarelo claro
    private static final Color PURPLE_COLOR = new Color(255, 193, 7); // Amarelo (sem roxo)
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Quase preto
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JRadioButton rbFrentista;
    private JRadioButton rbAdmin;
    private SessionManager sessionManager;
    private AcessoService acessoService;

    public LoginView() {
        this.sessionManager = SessionManager.getInstance();
        this.acessoService = new AcessoService();
        initComponents();
    }

    private void initComponents() {
        setTitle("Login - Sistema PDV");
        setSize(480, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fundo branco simples
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(15, 15));

        // Painel principal sem bordas coloridas
        JPanel cardPanel = new JPanel();
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(35, 45, 35, 45));

        // Cabeçalho simples
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitle = new JLabel("SISTEMA PDV");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitle.setForeground(PRIMARY_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(lblTitle);

        headerPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel lblSubtitle = new JLabel("Posto de Combustível");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(new Color(108, 117, 125));
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(lblSubtitle);

        cardPanel.add(headerPanel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Tipo de usuário com layout horizontal
        JLabel lblTipoLabel = new JLabel("Tipo de Acesso:");
        lblTipoLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTipoLabel.setForeground(TEXT_COLOR);
        lblTipoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(lblTipoLabel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 8)));

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        radioPanel.setOpaque(false);

        rbFrentista = createStyledRadioButton("Frentista", true);
        rbAdmin = createStyledRadioButton("Administrador", false);

        ButtonGroup group = new ButtonGroup();
        group.add(rbFrentista);
        group.add(rbAdmin);

        radioPanel.add(rbFrentista);
        radioPanel.add(rbAdmin);
        cardPanel.add(radioPanel);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Campo Username
        JLabel lblUsername = new JLabel("Nome de Usuário:");
        lblUsername.setForeground(TEXT_COLOR);
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(lblUsername);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        txtUsername = createStyledTextField(20);
        txtUsername.setMaximumSize(new Dimension(350, 40));
        cardPanel.add(txtUsername);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Password
        JLabel lblPassword = new JLabel("Senha de Acesso:");
        lblPassword.setForeground(TEXT_COLOR);
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        cardPanel.add(lblPassword);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        txtPassword = createStyledPasswordField(20);
        txtPassword.setMaximumSize(new Dimension(350, 40));
        cardPanel.add(txtPassword);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Botão Login vermelho
        JButton btnLogin = createModernButton("ENTRAR NO SISTEMA", PRIMARY_COLOR, BUTTON_HOVER_COLOR);
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(350, 45));
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.addActionListener(e -> handleLogin());
        cardPanel.add(btnLogin);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Botão Preencher Informações amarelo
        JButton btnPreencherInfo = createModernButton("Cadastrar Dados Pessoais", SECONDARY_COLOR, ACCENT_COLOR);
        btnPreencherInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPreencherInfo.setMaximumSize(new Dimension(350, 42));
        btnPreencherInfo.setForeground(TEXT_COLOR);
        btnPreencherInfo.addActionListener(e -> new CadastroPessoaView().setVisible(true));
        cardPanel.add(btnPreencherInfo);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 10)));


        // Botão Cadastrar Admin (só se não existir admin no backend)
        if (!hasAdminInBackend()) {
            JButton btnRegisterAdmin = createModernButton("Cadastrar Administrador", PRIMARY_COLOR, BUTTON_HOVER_COLOR);
            btnRegisterAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnRegisterAdmin.setMaximumSize(new Dimension(350, 42));
            btnRegisterAdmin.setForeground(Color.WHITE);
            btnRegisterAdmin.addActionListener(e -> {
                new RegisterAdminView().setVisible(true);
                dispose();
            });
            cardPanel.add(btnRegisterAdmin);
        }

        // Centralizar o card na tela
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(cardPanel);

        add(centerPanel, BorderLayout.CENTER);

        // Enter para fazer login
        txtPassword.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tenta fazer login via backend
        try {
            Acesso acesso = acessoService.login(username, password);

            if (acesso == null) {
                JOptionPane.showMessageDialog(this,
                        "Usuário ou senha incorretos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                txtPassword.setText("");
                return;
            }

            // Verifica se o role corresponde ao tipo selecionado
            boolean isAdmin = rbAdmin.isSelected();
            String roleEsperado = isAdmin ? "ADMIN" : "FRENTISTA";

            if (acesso.getRole() != null && !acesso.getRole().equals(roleEsperado)) {
                JOptionPane.showMessageDialog(this,
                        "Este usuário não tem permissão de " + roleEsperado + "!",
                        "Acesso Negado",
                        JOptionPane.WARNING_MESSAGE);
                txtPassword.setText("");
                return;
            }

            // Login bem-sucedido
            String role = acesso.getRole() != null ? acesso.getRole() : "FRENTISTA";
            sessionManager.login(username, String.valueOf(acesso.getId()), role);

            JOptionPane.showMessageDialog(this,
                    "Login realizado com sucesso!\n" +
                    "Bem-vindo(a), " + (acesso.getNomeCompleto() != null ? acesso.getNomeCompleto() : username) + "!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            // Abre a tela principal
            MainApp.showMainApp();
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Usuário ou senha incorretos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
        }
    }

    private JRadioButton createStyledRadioButton(String text, boolean selected) {
        JRadioButton rb = new JRadioButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (isSelected()) {
                    g2d.setColor(new Color(255, 193, 7, 40)); // Amarelo transparente
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);

                    // Borda vermelha quando selecionado
                    g2d.setColor(PRIMARY_COLOR);
                    g2d.setStroke(new BasicStroke(2f));
                    g2d.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 8, 8);
                }

                super.paintComponent(g);
            }
        };
        rb.setForeground(TEXT_COLOR);
        rb.setFont(new Font("Segoe UI", Font.BOLD, 13));
        rb.setOpaque(false);
        rb.setSelected(selected);
        rb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rb.setFocusPainted(false);
        return rb;
    }

    private JTextField createStyledTextField(int columns) {
        JTextField field = new JTextField(columns);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218), 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        return field;
    }

    private JPasswordField createStyledPasswordField(int columns) {
        JPasswordField field = new JPasswordField(columns);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(206, 212, 218), 2),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        field.setAlignmentX(Component.CENTER_ALIGNMENT);

        return field;
    }

    private boolean hasAdminInBackend() {
        try {
            System.out.println("🔍 Verificando se existe admin no backend...");

            // Tenta buscar todos os acessos do backend
            var acessos = acessoService.getAllAcessos();

            if (acessos == null) {
                System.out.println("⚠️ Lista de acessos é null - Backend pode estar offline");
                return false; // Permite cadastro apenas na primeira vez
            }

            if (acessos.isEmpty()) {
                System.out.println("⚠️ Nenhum acesso cadastrado - Sistema vazio (primeiro acesso)");
                return false; // Primeiro acesso do sistema
            }

            // Verifica se existe algum admin
            boolean temAdmin = acessos.stream()
                .anyMatch(a -> {
                    if (a == null) return false;
                    String role = a.getRole();
                    System.out.println("  - Acesso encontrado: usuário=" + a.getUsuario() + ", role=" + role);
                    return role != null && "ADMIN".equalsIgnoreCase(role.trim());
                });

            if (temAdmin) {
                System.out.println("✅ ADMIN JÁ EXISTE - Botão será OCULTADO");
            } else {
                System.out.println("❌ NENHUM ADMIN ENCONTRADO - Botão será EXIBIDO");
            }

            return temAdmin;

        } catch (Exception e) {
            System.err.println("❌ ERRO ao verificar admin: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();

            // Se der erro de conexão, assumimos que é primeira vez (backend pode não estar rodando)
            // MAS se o erro for diferente, melhor não mostrar o botão
            if (e instanceof java.net.ConnectException || e.getMessage().contains("Connection refused")) {
                System.out.println("⚠️ Backend offline - Permitindo cadastro de admin (primeira vez)");
                return false;
            } else {
                // Outros erros = não mostrar botão por segurança
                System.err.println("⚠️ Erro desconhecido - OCULTANDO botão por segurança");
                return true; // Oculta o botão
            }
        }
    }

    private JButton createModernButton(String text, Color startColor, Color endColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(startColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Efeito hover simples
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(startColor.darker());
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(startColor);
            }
        });

        return button;
    }
}

