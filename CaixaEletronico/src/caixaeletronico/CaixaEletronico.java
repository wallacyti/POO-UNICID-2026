package caixaeletronico;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

public class CaixaEletronico implements ICaixaEletronico {

    // Matriz de cédulas: {valor, quantidade}
    private int[][] cedulas = {
        {100, 100},
        {50, 200},
        {20, 300},
        {10, 350},
        {5, 450},
        {2, 500}
    };

    // Valor mínimo em caixa para operação
    private int cotaMin = 0;

    // Registro histórico de saques
    private List<String> extrato = new ArrayList<>();

    // Calcula o saldo total baseado na quantidade de notas disponíveis na matriz
    private int total() {
        int t = 0;
        for (int[] c : cedulas) t += c[0] * c[1];
        return t;
    }

    @Override
    public String pegaValorTotalDisponivel() {
        // Retorna o saldo total formatado como string para exibição na GUI
        return "Valor disponivel: R$ " + total();
    }

    @Override
    public String sacar(Integer valor) {
        // Validações de entrada
        if (valor == null || valor <= 0) return "Valor invalido.";

        int saldoAtual = total();

        // Verifica se o caixa JÁ ESTÁ bloqueado ANTES do saque
        // Permite o saque que cruza a cota, bloqueia todos os seguintes
        if (saldoAtual <= cotaMin) {
            return "Caixa Vazio: Chame o Operador.";
        }

        // Verifica se o caixa tem dinheiro físico suficiente para cobrir o valor pedido
        if (saldoAtual < valor) {
            return "Saque indisponivel: Saldo fisico insuficiente no caixa.";
        }

        // Arrays para armazenar o resultado do cálculo recursivo
        int[] resultado = new int[cedulas.length];

        // Chamada do algoritmo de backtracking para encontrar a combinação de notas
        if (!calcularSaque(valor, 0, new int[cedulas.length], resultado, 0)) {
            // Mensagem exigida pelo enunciado quando não há combinação possível de cédulas
            return "Nao Temos Notas Para Este Saque";
        }

        // Efetivação do débito nas notas e formatação do recibo
        int nCedulas = 0;
        StringBuilder sb = new StringBuilder("=== SAQUE REALIZADO ===\nValor: R$ " + valor + "\n");

        for (int i = 0; i < cedulas.length; i++) {
            if (resultado[i] > 0) {
                // Debita a quantidade usada da cédula correspondente na matriz
                cedulas[i][1] -= resultado[i];
                nCedulas += resultado[i];
                sb.append("R$ ").append(cedulas[i][0]).append(": ").append(resultado[i]).append(" nota(s)\n");
            }
        }
        sb.append("Cedulas: ").append(nCedulas);

        // Gravação da transação no histórico para exibição no extrato ao sair
        int saldoNovo = total();
        extrato.add("Saque R$ " + valor + " | cedulas: " + nCedulas + " | saldo: R$ " + saldoNovo);
        return sb.toString();
    }

    // Algoritmo de Backtracking para testar todas as combinações de cédulas possíveis
    // Prioriza as cédulas de maior valor e respeita o limite máximo de 30 notas por saque
    private boolean calcularSaque(int valorRestante, int indiceAtual, int[] usadas, int[] resultado, int totalNotas) {
        // Condição de sucesso: valor zerado
        if (valorRestante == 0) {
            System.arraycopy(usadas, 0, resultado, 0, usadas.length);
            return true;
        }

        // Condição de parada: fim do array de cédulas ou limite de notas excedido
        if (indiceAtual >= cedulas.length || totalNotas >= 30) {
            return false;
        }

        int valorNota = cedulas[indiceAtual][0];
        int qtdDisponivel = cedulas[indiceAtual][1];

        // Define o máximo de notas possíveis para o valor atual respeitando o limite de 30
        int maxNotasPossiveis = Math.min(valorRestante / valorNota, qtdDisponivel);
        maxNotasPossiveis = Math.min(maxNotasPossiveis, 30 - totalNotas);

        // Tenta alocar notas da maior quantidade possível até 0
        for (int i = maxNotasPossiveis; i >= 0; i--) {
            usadas[indiceAtual] = i;
            if (calcularSaque(valorRestante - (i * valorNota), indiceAtual + 1, usadas, resultado, totalNotas + i)) {
                return true; // Encontrou combinação válida
            }
        }

        // Desfaz a escolha (backtrack)
        usadas[indiceAtual] = 0;
        return false;
    }

    @Override
    public String pegaRelatorioCedulas() {
        // Percorre a matriz de cédulas e formata o relatório com valor e quantidade de cada tipo
        StringBuilder sb = new StringBuilder("=== CEDULAS ===\n");
        for (int[] c : cedulas) sb.append("R$ ").append(c[0]).append(": ").append(c[1]).append(" un.\n");
        sb.append("Total: R$ ").append(total());
        return sb.toString();
    }

    @Override
    public String reposicaoCedulas(Integer cedula, Integer qtd) {
        // Valida os parâmetros recebidos antes de processar a reposição
        if (cedula == null || qtd == null || qtd <= 0) return "Parametros invalidos.";
        // Busca a cédula informada na matriz e incrementa a quantidade disponível
        for (int[] c : cedulas) {
            if (c[0] == cedula) {
                c[1] += qtd;
                return "R$ " + cedula + ": " + c[1] + " un.";
            }
        }
        return "Cedula R$ " + cedula + " nao existe.";
    }

    @Override
    public String armazenaCotaMinima(Integer min) {
        if (min == null || min < 0) return "Valor invalido.";

        // Impede cota minima maior ou igual ao saldo atual — seria impossivel operar
        if (min >= total()) {
            return "Cota invalida: o valor de R$ " + min 
                 + " e maior ou igual ao saldo disponivel (R$ " + total() + ").";
        }

        cotaMin = min;
        return "Cota minima definida: R$ " + min;
    }

    // Retorna o histórico formatado de transações para exibição ao encerrar o sistema
    public String gerarExtrato() {
        if (extrato.isEmpty()) return "Sem transacoes.";
        StringBuilder sb = new StringBuilder("=== EXTRATO ===\n");
        for (int i = 0; i < extrato.size(); i++) sb.append(i + 1).append(". ").append(extrato.get(i)).append("\n");
        return sb.toString();
    }

    // Getters e Setters
    public int[][] getCedulas()      { return cedulas; }
    public int getCotaMinima()       { return cotaMin; }
    public List<String> getExtrato() { return extrato; }
    public void setCotaMinima(int v) { cotaMin = v; }

    // Ponto de entrada da aplicação — instancia o caixa e abre a janela
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
}