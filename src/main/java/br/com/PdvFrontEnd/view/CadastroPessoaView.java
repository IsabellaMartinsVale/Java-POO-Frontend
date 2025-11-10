package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Pessoa;
import br.com.PdvFrontEnd.service.PessoaService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CadastroPessoaView extends JFrame {
    // Paleta: Vermelho, Amarelo e Branco
    private static final Color PRIMARY_COLOR = new Color(220, 53, 69); // Vermelho
    private static final Color SECONDARY_COLOR = new Color(255, 193, 7); // Amarelo
    private static final Color SUCCESS_COLOR = new Color(40, 167, 69); // Verde sucesso
    private static final Color ACCENT_COLOR = new Color(255, 152, 0); // Amarelo alaranjado
    private static final Color TEXT_COLOR = new Color(33, 37, 41); // Texto escuro
    private static final Color BUTTON_HOVER_COLOR = new Color(200, 35, 51); // Vermelho escuro

    private JTextField txtNomeCompleto;
    private JTextField txtCpfCnpj;
    private JTextField txtDataNascimento;
    private JComboBox<String> cmbTipoPessoa;
    private PessoaService pessoaService;

    public CadastroPessoaView() {
        this.pessoaService = new PessoaService();
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro de Pessoa - Sistema PDV");
        setSize(520, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel de fundo branco com bordas coloridas
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setColor(Color.WHITE);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                g2d.setColor(PRIMARY_COLOR);
                g2d.fillRect(0, 0, 8, getHeight());
                g2d.fillRect(getWidth()-8, 0, 8, getHeight());

                g2d.setColor(SECONDARY_COLOR);
                g2d.fillRect(0, 0, getWidth(), 8);
                g2d.fillRect(0, getHeight()-8, getWidth(), 8);
            }
        };
        backgroundPanel.setOpaque(true);
        backgroundPanel.setLayout(new BorderLayout());
        setContentPane(backgroundPanel);

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));

        // Cabeçalho vermelho
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, PRIMARY_COLOR, getWidth(), 0, BUTTON_HOVER_COLOR);
                g2d.setPaint(gp);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            }
        };
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        headerPanel.setMaximumSize(new Dimension(440, 80));
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitle = new JLabel("Cadastro de Pessoa");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(lblTitle);

        JLabel lblSubtitle = new JLabel("Preencha seus dados pessoais");
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSubtitle.setForeground(new Color(255, 255, 255, 220));
        lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(lblSubtitle);

        mainPanel.add(headerPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Campo Nome Completo
        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setForeground(TEXT_COLOR);
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblNome);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        txtNomeCompleto = new JTextField(20);
        txtNomeCompleto.setMaximumSize(new Dimension(420, 38));
        txtNomeCompleto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNomeCompleto.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtNomeCompleto.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SECONDARY_COLOR, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        mainPanel.add(txtNomeCompleto);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo CPF/CNPJ
        JLabel lblCpfCnpj = new JLabel("CPF ou CNPJ:");
        lblCpfCnpj.setForeground(TEXT_COLOR);
        lblCpfCnpj.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblCpfCnpj.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblCpfCnpj);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        txtCpfCnpj = new JTextField(20);
        txtCpfCnpj.setMaximumSize(new Dimension(420, 38));
        txtCpfCnpj.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCpfCnpj.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtCpfCnpj.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SECONDARY_COLOR, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        mainPanel.add(txtCpfCnpj);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Data de Nascimento
        JLabel lblDataNascimento = new JLabel("Data de Nascimento (dd/MM/yyyy):");
        lblDataNascimento.setForeground(TEXT_COLOR);
        lblDataNascimento.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblDataNascimento.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblDataNascimento);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        txtDataNascimento = new JTextField(20);
        txtDataNascimento.setMaximumSize(new Dimension(420, 38));
        txtDataNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDataNascimento.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtDataNascimento.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SECONDARY_COLOR, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        mainPanel.add(txtDataNascimento);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Tipo de Pessoa
        JLabel lblTipoPessoa = new JLabel("Tipo de Pessoa:");
        lblTipoPessoa.setForeground(TEXT_COLOR);
        lblTipoPessoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTipoPessoa.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTipoPessoa);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

        cmbTipoPessoa = new JComboBox<>(new String[]{"FISICA", "JURIDICA"});
        cmbTipoPessoa.setMaximumSize(new Dimension(420, 38));
        cmbTipoPessoa.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cmbTipoPessoa.setBackground(Color.WHITE);
        cmbTipoPessoa.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SECONDARY_COLOR, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        cmbTipoPessoa.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(cmbTipoPessoa);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Painel de Botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(440, 50));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botão Cadastrar (Verde)
        JButton btnCadastrar = createModernButton("Cadastrar", SUCCESS_COLOR);
        btnCadastrar.setPreferredSize(new Dimension(200, 45));
        btnCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.addActionListener(e -> handleCadastro());
        buttonPanel.add(btnCadastrar);

        // Botão Cancelar (Amarelo)
        JButton btnCancelar = createModernButton("Cancelar", SECONDARY_COLOR);
        btnCancelar.setPreferredSize(new Dimension(200, 45));
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancelar.setForeground(TEXT_COLOR);
        btnCancelar.addActionListener(e -> dispose());
        buttonPanel.add(btnCancelar);

        mainPanel.add(buttonPanel);

        backgroundPanel.add(mainPanel, BorderLayout.CENTER);

        // Enter para cadastrar
        txtDataNascimento.addActionListener(e -> handleCadastro());
    }

    private void handleCadastro() {
        String nomeCompleto = txtNomeCompleto.getText().trim();
        String cpfCnpj = txtCpfCnpj.getText().trim();
        String dataNascimentoStr = txtDataNascimento.getText().trim();
        String tipoPessoa = (String) cmbTipoPessoa.getSelectedItem();

        // Validações
        if (nomeCompleto.isEmpty() || cpfCnpj.isEmpty() || dataNascimentoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos!",
                    "Campos Obrigatórios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Valida e converte data
        LocalDate dataNascimento;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento = LocalDate.parse(dataNascimentoStr, formatter);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Data inválida! Use o formato dd/MM/yyyy\nExemplo: 15/03/1990",
                    "Data Inválida",
                    JOptionPane.ERROR_MESSAGE);
            txtDataNascimento.requestFocus();
            return;
        }

        // Verifica se é maior de idade (opcional)
        if (LocalDate.now().minusYears(18).isBefore(dataNascimento)) {
            int resposta = JOptionPane.showConfirmDialog(this,
                    "A pessoa é menor de idade. Deseja continuar?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION);
            if (resposta != JOptionPane.YES_OPTION) {
                return;
            }
        }

        // Cadastra a pessoa
        try {
            // Formatar data para String no formato yyyy-MM-dd
            DateTimeFormatter backendFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dataFormatada = dataNascimento.format(backendFormatter);

            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nomeCompleto);  // Usar setNome() ao invés de setNomeCompleto()
            pessoa.setCpf(cpfCnpj);  // Usar setCpf() ao invés de setCpfCnpj()
            pessoa.setDataNascimento(dataFormatada);  // Passar como String
            pessoa.setTipo(tipoPessoa);  // Usar setTipo() ao invés de setTipoPessoa()
            // Não há setAtivo() no modelo, então não definir

            pessoaService.addPessoa(pessoa);

            JOptionPane.showMessageDialog(this,
                    "Pessoa cadastrada com sucesso!\n\n" +
                    "Nome: " + nomeCompleto + "\n" +
                    "CPF/CNPJ: " + cpfCnpj + "\n" +
                    "Tipo: " + tipoPessoa + "\n\n" +
                    "Aguarde o administrador criar suas credenciais de acesso.",
                    "Cadastro Realizado",
                    JOptionPane.INFORMATION_MESSAGE);

            limparCampos();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao cadastrar pessoa:\n" + ex.getMessage(),
                    "Erro no Cadastro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtNomeCompleto.setText("");
        txtCpfCnpj.setText("");
        txtDataNascimento.setText("");
        cmbTipoPessoa.setSelectedIndex(0);
        txtNomeCompleto.requestFocus();
    }

    private JButton createModernButton(String text, Color bgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color color = getModel().isPressed() ? bgColor.darker() :
                             getModel().isRollover() ? bgColor.brighter() : bgColor;

                g2d.setColor(color);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);

                g2d.setColor(new Color(33, 37, 41));
                g2d.setStroke(new BasicStroke(2f));
                g2d.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, 8, 8);

                g2d.dispose();
                super.paintComponent(g);
            }
        };

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }
}

