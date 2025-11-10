package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.service.*;
import br.com.PdvFrontEnd.util.SessionManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainApp {
    // Paleta de cores: Vermelho, Amarelo e Branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho principal
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color ACCENT_COLOR = new Color(255, 152, 0); // Amarelo alaranjado
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Quase preto para contraste
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro hover
    private static final Color PURPLE_COLOR = new Color(155, 89, 182); // Roxo para Cadastrar Frentista
    private static final Color PURPLE_HOVER_COLOR = new Color(135, 69, 162); // Roxo escuro hover
    private static final Color ACTIVE_COLOR = new Color(255, 235, 59); // Amarelo claro
    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250); // Branco acinzentado

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            // Inicia o sistema sempre pela tela de login
            new LoginView().setVisible(true);
        });
    }

    public static void showMainApp() {
        EventQueue.invokeLater(() -> {
            SessionManager sessionManager = SessionManager.getInstance();
            boolean isAdmin = sessionManager.isAdmin();

            JFrame mainFrame = new JFrame("Sistema de Gerenciamento - " + (isAdmin ? "ADMINISTRADOR" : "FRENTISTA"));
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(750, 680);
            mainFrame.setResizable(false);
            mainFrame.setLocationRelativeTo(null);

            // Fundo branco simples
            JPanel backgroundPanel = new JPanel();
            backgroundPanel.setBackground(Color.WHITE);
            backgroundPanel.setLayout(new BorderLayout(15, 15));
            mainFrame.setContentPane(backgroundPanel);

            // Painel superior simples com fundo vermelho
            JPanel topPanel = new JPanel(new BorderLayout());
            topPanel.setBackground(PRIMARY_COLOR);
            topPanel.setBorder(BorderFactory.createEmptyBorder(18, 30, 18, 30));

            // Nome do usuário em branco
            JLabel lblUser = new JLabel("Usuário: " + sessionManager.getCurrentUsername() + " (" + sessionManager.getUserRole() + ")");
            lblUser.setFont(new Font("Segoe UI", Font.BOLD, 17));
            lblUser.setForeground(Color.WHITE);

            JButton btnLogout = createModernButton("Sair", SECONDARY_COLOR, ACCENT_COLOR);
            btnLogout.setPreferredSize(new Dimension(110, 38));
            btnLogout.setForeground(TEXT_COLOR);
            btnLogout.addActionListener(e -> {
                sessionManager.logout();
                mainFrame.dispose();
                new LoginView().setVisible(true);
            });

            topPanel.add(lblUser, BorderLayout.WEST);
            topPanel.add(btnLogout, BorderLayout.EAST);
            backgroundPanel.add(topPanel, BorderLayout.NORTH);

            // Card central simples com fundo branco
            JPanel cardPanel = new JPanel();
            cardPanel.setBackground(Color.WHITE);
            cardPanel.setLayout(new GridLayout(5, 2, 15, 15)); // 2 colunas
            cardPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

            // Botão Gerenciar Bombas (sempre disponível) - AMARELO
            JButton btnBombas = createModernButton("Gerenciar Bombas", SECONDARY_COLOR, ACCENT_COLOR);
            btnBombas.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnBombas.setForeground(TEXT_COLOR);
            btnBombas.addActionListener(e -> new BombaListView().setVisible(true));
            cardPanel.add(btnBombas);

            // Botão Cadastrar Novo Frentista (apenas para Admin) - ROXO
            JButton btnCadastrarFrentista = createModernButton("Cadastrar Novo Frentista", PURPLE_COLOR, PURPLE_HOVER_COLOR);
            btnCadastrarFrentista.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnCadastrarFrentista.setForeground(Color.WHITE);
            btnCadastrarFrentista.addActionListener(e -> {
                if (checkAdminAccess(mainFrame)) {
                    new RegisterView().setVisible(true);
                }
            });
            if (!isAdmin) {
                btnCadastrarFrentista.setEnabled(false);
                btnCadastrarFrentista.setBackground(new Color(100, 100, 100));
            }
            cardPanel.add(btnCadastrarFrentista);

            // Botões de gerenciamento (TODOS AMARELOS)
            JButton btnPessoas = createModernButton("Gerenciar Pessoas", SECONDARY_COLOR, ACCENT_COLOR);
            btnPessoas.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnPessoas.setForeground(TEXT_COLOR);
            btnPessoas.addActionListener(e -> {
                if (checkAdminAccess(mainFrame)) {
                    new PessoaList(new PessoaService()).setVisible(true);
                }
            });
            if (!isAdmin) {
                btnPessoas.setEnabled(false);
            }
            cardPanel.add(btnPessoas);

            JButton btnPrecos = createModernButton("Gerenciar Preços", SECONDARY_COLOR, ACCENT_COLOR);
            btnPrecos.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnPrecos.setForeground(TEXT_COLOR);
            btnPrecos.addActionListener(e -> {
                if (checkAdminAccess(mainFrame)) {
                    new PrecoList(new PrecoService()).setVisible(true);
                }
            });
            if (!isAdmin) {
                btnPrecos.setEnabled(false);
            }
            cardPanel.add(btnPrecos);

            JButton btnProdutos = createModernButton("Gerenciar Produtos", SECONDARY_COLOR, ACCENT_COLOR);
            btnProdutos.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnProdutos.setForeground(TEXT_COLOR);
            btnProdutos.addActionListener(e -> {
                if (checkAdminAccess(mainFrame)) {
                    new ProdutoList(new ProdutoService()).setVisible(true);
                }
            });
            if (!isAdmin) {
                btnProdutos.setEnabled(false);
            }
            cardPanel.add(btnProdutos);

            JButton btnCustos = createModernButton("Gerenciar Custos", SECONDARY_COLOR, ACCENT_COLOR);
            btnCustos.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnCustos.setForeground(TEXT_COLOR);
            btnCustos.addActionListener(e -> {
                if (checkAdminAccess(mainFrame)) {
                    new CustoList(new CustoService()).setVisible(true);
                }
            });
            if (!isAdmin) {
                btnCustos.setEnabled(false);
            }
            cardPanel.add(btnCustos);

            JButton btnEstoques = createModernButton("Gerenciar Estoques", SECONDARY_COLOR, ACCENT_COLOR);
            btnEstoques.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnEstoques.setForeground(TEXT_COLOR);
            btnEstoques.addActionListener(e -> {
                if (checkAdminAccess(mainFrame)) {
                    new EstoqueList(new EstoqueService()).setVisible(true);
                }
            });
            if (!isAdmin) {
                btnEstoques.setEnabled(false);
            }
            cardPanel.add(btnEstoques);

            JButton btnAcessos = createModernButton("Gerenciar Acessos", SECONDARY_COLOR, ACCENT_COLOR);
            btnAcessos.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnAcessos.setForeground(TEXT_COLOR);
            btnAcessos.addActionListener(e -> {
                if (checkAdminAccess(mainFrame)) {
                    new AcessoList(new AcessoService()).setVisible(true);
                }
            });
            if (!isAdmin) {
                btnAcessos.setEnabled(false);
            }
            cardPanel.add(btnAcessos);

            JButton btnContatos = createModernButton("Gerenciar Contatos", SECONDARY_COLOR, ACCENT_COLOR);
            btnContatos.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnContatos.setForeground(TEXT_COLOR);
            btnContatos.addActionListener(e -> {
                if (checkAdminAccess(mainFrame)) {
                    new ContatoList(new ContatoService()).setVisible(true);
                }
            });
            if (!isAdmin) {
                btnContatos.setEnabled(false);
            }
            cardPanel.add(btnContatos);

            // Centralizar o card
            JPanel centerWrapper = new JPanel(new GridBagLayout());
            centerWrapper.setBackground(Color.WHITE);
            centerWrapper.add(cardPanel);
            backgroundPanel.add(centerWrapper, BorderLayout.CENTER);

            // Painel inferior simples com fundo amarelo
            JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            footerPanel.setBackground(SECONDARY_COLOR);
            footerPanel.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));

            JLabel lblInfo = new JLabel("Sistema de Gerenciamento - PDV Posto de Combustível");
            lblInfo.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblInfo.setForeground(TEXT_COLOR);
            footerPanel.add(lblInfo);

            backgroundPanel.add(footerPanel, BorderLayout.SOUTH);

            mainFrame.setVisible(true);
        });
    }

    private static boolean checkAdminAccess(JFrame parent) {
        SessionManager sessionManager = SessionManager.getInstance();
        if (!sessionManager.isAdmin()) {
            JOptionPane.showMessageDialog(parent,
                    "Acesso negado!\n\nApenas o Administrador pode acessar este recurso.",
                    "Acesso Restrito",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private static JButton createModernButton(String text, Color startColor, Color endColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(startColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Efeito hover simples
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    button.setBackground(startColor.darker());
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    button.setBackground(startColor);
                }
            }
        });

        return button;
    }
}

