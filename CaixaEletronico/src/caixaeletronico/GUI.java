package caixaeletronico;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ICaixaEletronico caixa;

    // Método main para testar apenas a janela (inicializa um caixa vazio)
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

    // Construtor obrigatório recebendo a interface
    public GUI(ICaixaEletronico caixa) {
        this.caixa = caixa;
        
        setOpacity(1.0f); 
        setTitle("Caixa Eletronico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 424, 406);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        JLabel lblNewLabel = new JLabel("Módulo do Cliente:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        JButton btnNewButton = new JButton("Efetuar Saque");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = JOptionPane.showInputDialog("Digite o valor do saque:");
                    if (input != null && !input.isEmpty()) {
                        String msg = GUI.this.caixa.sacar(Integer.parseInt(input));
                        JOptionPane.showMessageDialog(null, msg);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor inválido.");
                }
            }
        });
        
        JLabel lblMduloDoAdministrador = new JLabel("Módulo do Administrador:");
        lblMduloDoAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        JButton btnRelatorioDeCedulas = new JButton("Relatorio de Cedulas");
        btnRelatorioDeCedulas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnRelatorioDeCedulas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, GUI.this.caixa.pegaRelatorioCedulas());
            }
        });
        
        JButton btnValorTotalDisponivel = new JButton("Valor Total Disponivel");
        btnValorTotalDisponivel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnValorTotalDisponivel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, GUI.this.caixa.pegaValorTotalDisponivel());
            }
        });
        
        JButton btnReposioDeCedulas = new JButton("Reposição de Cedulas");
        btnReposioDeCedulas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnReposioDeCedulas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String nota = JOptionPane.showInputDialog("Valor da cédula:");
                    String qtd = JOptionPane.showInputDialog("Quantidade:");
                    if (nota != null && qtd != null) {
                        String msg = GUI.this.caixa.reposicaoCedulas(Integer.parseInt(nota), Integer.parseInt(qtd));
                        JOptionPane.showMessageDialog(null, msg);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor inválido.");
                }
            }
        });
        
        JButton btnCotaMinima = new JButton("Cota Minima");
        btnCotaMinima.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCotaMinima.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = JOptionPane.showInputDialog("Digite a nova cota mínima:");
                    if (input != null && !input.isEmpty()) {
                        String msg = GUI.this.caixa.armazenaCotaMinima(Integer.parseInt(input));
                        JOptionPane.showMessageDialog(null, msg);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor inválido.");
                }
            }
        });
        
        JLabel lblMduloDeAmbos = new JLabel("Módulo de Ambos:");
        lblMduloDeAmbos.setFont(new Font("Tahoma", Font.PLAIN, 12));
        
        JButton btnCotaMinima_1 = new JButton("Sair");
        btnCotaMinima_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCotaMinima_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCotaMinima_1, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblMduloDeAmbos, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnCotaMinima, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnReposioDeCedulas, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnValorTotalDisponivel, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnRelatorioDeCedulas, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
                        .addComponent(lblMduloDoAdministrador, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
            gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                    .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                    .addGap(25)
                    .addComponent(lblMduloDoAdministrador, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnRelatorioDeCedulas, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnValorTotalDisponivel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnReposioDeCedulas, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnCotaMinima, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(lblMduloDeAmbos, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnCotaMinima_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(107, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
    }
}