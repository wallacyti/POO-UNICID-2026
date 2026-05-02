package caixaeletronico;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.*;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private ICaixaEletronico caixa;

    // --- Paleta de cores do sistema ---
    private static final Color COR_HEADER_BG     = new Color(15, 40, 80);
    private static final Color COR_HEADER_ICON_BG = new Color(26, 58, 110);
    private static final Color COR_PAGINA_BG     = new Color(245, 247, 250);
    private static final Color COR_SECAO_LABEL   = new Color(136, 146, 164);
    private static final Color COR_DIVISOR       = new Color(220, 224, 230);

    // Botão Cliente — azul
    private static final Color COR_BTN_CLIENTE   = new Color(23, 85, 200);
    private static final Color COR_BTN_CLIENTE_H = new Color(15, 66, 170);

    // Botão Admin — verde escuro
    private static final Color COR_BTN_ADMIN     = new Color(15, 110, 86);
    private static final Color COR_BTN_ADMIN_H   = new Color(10, 85, 65);

    // Botão Sair — vermelho escuro
    private static final Color COR_BTN_SAIR      = new Color(163, 45, 45);
    private static final Color COR_BTN_SAIR_H    = new Color(130, 30, 30);

    private static final Color COR_BTN_TEXTO     = Color.WHITE;
    private static final Color COR_ICONE_BG      = new Color(255, 255, 255, 40);

    // Método main comentado — ponto de entrada único é o CaixaEletronico.main()
    /*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI frame = new GUI(new CaixaEletronico());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    */

    // Construtor obrigatório recebendo a interface
    public GUI(ICaixaEletronico caixa) {
        this.caixa = caixa;
        configurarJanela();
        construirLayout();
    }

    // Define as propriedades da janela principal
    private void configurarJanela() {
        setTitle("Caixa Eletrônico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(COR_PAGINA_BG);
    }

    // Monta a estrutura principal da janela com header e painel de botões
    private void construirLayout() {
        setLayout(new BorderLayout());
        add(criarHeader(), BorderLayout.NORTH);
        add(criarPainelBotoes(), BorderLayout.CENTER);
    }

    // Cria o cabeçalho escuro com ícone de caixa eletrônico e título do sistema
    private JPanel criarHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COR_HEADER_BG);
        header.setBorder(new EmptyBorder(18, 20, 18, 20));

        // Painel do ícone com fundo levemente destacado
        JPanel painelIcone = new JPanel(new BorderLayout());
        painelIcone.setOpaque(true);
        painelIcone.setBackground(COR_HEADER_ICON_BG);
        painelIcone.setBorder(new EmptyBorder(8, 10, 8, 10));
        painelIcone.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(255, 255, 255, 20), 1, true),
            new EmptyBorder(6, 10, 6, 10)
        ));

        JLabel icone = new JLabel("🏧");
        icone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        icone.setHorizontalAlignment(SwingConstants.CENTER);
        painelIcone.add(icone, BorderLayout.CENTER);

        // Textos do cabeçalho
        JPanel textos = new JPanel(new GridLayout(2, 1, 0, 2));
        textos.setOpaque(false);
        textos.setBorder(new EmptyBorder(0, 14, 0, 0));

        JLabel titulo = new JLabel("Caixa Eletrônico");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 17));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Sistema de Auto-Atendimento");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitulo.setForeground(new Color(122, 159, 204));

        textos.add(titulo);
        textos.add(subtitulo);

        header.add(painelIcone, BorderLayout.WEST);
        header.add(textos, BorderLayout.CENTER);
        return header;
    }

    // Cria o painel central com as seções, labels e botões organizados
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(COR_PAGINA_BG);
        painel.setBorder(new EmptyBorder(18, 18, 18, 18));

        // Seção Cliente
        painel.add(criarLabelSecao("Módulo do Cliente"));
        painel.add(Box.createVerticalStrut(8));
        painel.add(criarBotao("💳", "Efetuar Saque",         COR_BTN_CLIENTE, COR_BTN_CLIENTE_H, e -> executarSaque()));

        painel.add(Box.createVerticalStrut(14));
        painel.add(criarDivisor());
        painel.add(Box.createVerticalStrut(14));

        // Seção Administrador
        painel.add(criarLabelSecao("Módulo do Administrador"));
        painel.add(Box.createVerticalStrut(8));
        painel.add(criarBotao("📊", "Relatório de Cédulas",  COR_BTN_ADMIN, COR_BTN_ADMIN_H, e -> mostrarRelatorio()));
        painel.add(Box.createVerticalStrut(8));
        painel.add(criarBotao("💰", "Valor Total Disponível", COR_BTN_ADMIN, COR_BTN_ADMIN_H, e -> mostrarValorTotal()));
        painel.add(Box.createVerticalStrut(8));
        painel.add(criarBotao("🔄", "Reposição de Cédulas",  COR_BTN_ADMIN, COR_BTN_ADMIN_H, e -> executarReposicao()));
        painel.add(Box.createVerticalStrut(8));
        painel.add(criarBotao("⚙️", "Cota Mínima",           COR_BTN_ADMIN, COR_BTN_ADMIN_H, e -> definirCotaMinima()));

        painel.add(Box.createVerticalStrut(14));
        painel.add(criarDivisor());
        painel.add(Box.createVerticalStrut(14));

        // Seção Ambos
        painel.add(criarLabelSecao("Módulo de Ambos"));
        painel.add(Box.createVerticalStrut(8));
        painel.add(criarBotao("🚪", "Sair",                  COR_BTN_SAIR, COR_BTN_SAIR_H, e -> executarSaida()));

        return painel;
    }

    // Cria um label de categoria para separar as seções visualmente
    private JLabel criarLabelSecao(String texto) {
        JLabel label = new JLabel(texto.toUpperCase());
        label.setFont(new Font("Segoe UI", Font.BOLD, 10));
        label.setForeground(COR_SECAO_LABEL);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    // Cria uma linha horizontal de separação entre as seções
    private JSeparator criarDivisor() {
        JSeparator sep = new JSeparator();
        sep.setForeground(COR_DIVISOR);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setAlignmentX(Component.LEFT_ALIGNMENT);
        return sep;
    }

    // Cria um botão estilizado com ícone, texto, cor e efeito de hover
    private JButton criarBotao(String emoji, String texto, Color corNormal, Color corHover, ActionListener acao) {

        // Botão com cantos arredondados via paintComponent customizado
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 12, 12));
                g2.dispose();
                super.paintComponent(g);
            }
        };

        // Layout interno: ícone à esquerda, texto centralizado, seta à direita
        btn.setLayout(new BorderLayout());

        JLabel lblIcone = new JLabel(emoji);
        lblIcone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        lblIcone.setForeground(COR_BTN_TEXTO);
        lblIcone.setOpaque(true);
        lblIcone.setBackground(COR_ICONE_BG);
        lblIcone.setBorder(new EmptyBorder(6, 10, 6, 10));
        lblIcone.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblTexto = new JLabel(texto);
        lblTexto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTexto.setForeground(COR_BTN_TEXTO);
        lblTexto.setBorder(new EmptyBorder(0, 14, 0, 0));

        JLabel lblSeta = new JLabel("›");
        lblSeta.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSeta.setForeground(new Color(255, 255, 255, 120));
        lblSeta.setBorder(new EmptyBorder(0, 0, 0, 14));

        btn.add(lblIcone, BorderLayout.WEST);
        btn.add(lblTexto, BorderLayout.CENTER);
        btn.add(lblSeta, BorderLayout.EAST);

        // Propriedades visuais do botão
        btn.setBackground(corNormal);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setBorder(new EmptyBorder(6, 6, 6, 6));

        // Efeito de hover: escurece o botão ao passar o mouse
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(corHover); btn.repaint(); }
            @Override public void mouseExited(MouseEvent e)  { btn.setBackground(corNormal); btn.repaint(); }
        });

        btn.addActionListener(acao);
        return btn;
    }

    // --- Ações dos botões ---

    // Solicita o valor e efetua o saque no caixa
    private void executarSaque() {
        try {
            String input = JOptionPane.showInputDialog(this,
                "Digite o valor do saque:", "Efetuar Saque", JOptionPane.PLAIN_MESSAGE);
            if (input != null && !input.trim().isEmpty()) {
                String msg = caixa.sacar(Integer.parseInt(input.trim()));
                JOptionPane.showMessageDialog(this, msg, "Resultado do Saque", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Digite apenas números inteiros.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Exibe o relatório com a quantidade atual de cada cédula
    private void mostrarRelatorio() {
        JOptionPane.showMessageDialog(this,
            caixa.pegaRelatorioCedulas(), "Relatório de Cédulas", JOptionPane.INFORMATION_MESSAGE);
    }

    // Exibe o saldo total disponível no caixa
    private void mostrarValorTotal() {
        JOptionPane.showMessageDialog(this,
            caixa.pegaValorTotalDisponivel(), "Valor Total Disponível", JOptionPane.INFORMATION_MESSAGE);
    }

    // Solicita o tipo e a quantidade de cédulas para reposição
    private void executarReposicao() {
        try {
            String nota = JOptionPane.showInputDialog(this,
                "Valor da cédula (ex: 100, 50, 20...):", "Reposição de Cédulas", JOptionPane.PLAIN_MESSAGE);
            if (nota == null) return;
            String qtd = JOptionPane.showInputDialog(this,
                "Quantidade a repor:", "Reposição de Cédulas", JOptionPane.PLAIN_MESSAGE);
            if (qtd == null) return;
            String msg = caixa.reposicaoCedulas(Integer.parseInt(nota.trim()), Integer.parseInt(qtd.trim()));
            JOptionPane.showMessageDialog(this, msg, "Reposição Realizada", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Digite apenas números inteiros.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Solicita e armazena o valor da cota mínima de operação
    private void definirCotaMinima() {
        try {
            String input = JOptionPane.showInputDialog(this,
                "Digite o valor da cota mínima:", "Cota Mínima", JOptionPane.PLAIN_MESSAGE);
            if (input != null && !input.trim().isEmpty()) {
                String msg = caixa.armazenaCotaMinima(Integer.parseInt(input.trim()));
                JOptionPane.showMessageDialog(this, msg, "Cota Mínima Definida", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Digite apenas números inteiros.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Exibe o extrato de todas as transações e encerra o sistema
    private void executarSaida() {
        // Verifica se a interface instanciada é a nossa classe concreta
        if (caixa instanceof CaixaEletronico) {
            // Faz o cast para acessar o método gerarExtrato() que criamos fora da interface
            String extrato = ((CaixaEletronico) caixa).gerarExtrato();
            JOptionPane.showMessageDialog(this, extrato, "Extrato Bancário", JOptionPane.INFORMATION_MESSAGE);
        }
        System.exit(0); // Fecha o programa após o usuário dar OK no extrato
    }
}