package caixaeletronico;

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

    // Calcula o saldo total baseado na quantidade de notas
    private int total() {
        int t = 0;
        for (int[] c : cedulas) t += c[0] * c[1];
        return t;
    }

    @Override
    public String pegaValorTotalDisponivel() {
        return "Valor disponivel: R$ " + total();
    }

    @Override
    public String sacar(Integer valor) {
        // Validações de entrada
        if (valor == null || valor <= 0) return "Valor invalido.";
        
        // Validação da cota mínima e saldo disponível
        int saldoAtual = total();
        if (saldoAtual - valor < cotaMin) {
            return "Saque indisponivel: Limite da cota minima atingido. Chame o Operador.";
        }

        // Arrays para armazenar o resultado do cálculo recursivo
        int[] resultado = new int[cedulas.length];
        
        // Chamada do algoritmo de backtracking para encontrar a combinação de notas
        if (!calcularSaque(valor, 0, new int[cedulas.length], resultado, 0)) {
            return "Saque nao realizado por falta de cedulas ou limite excedido (max 30 notas).";
        }

        // Efetivação do débito nas notas e formatação do recibo
        int nCedulas = 0;
        StringBuilder sb = new StringBuilder("=== SAQUE REALIZADO ===\nValor: R$ " + valor + "\n");
        
        for (int i = 0; i < cedulas.length; i++) {
            if (resultado[i] > 0) {
                cedulas[i][1] -= resultado[i];
                nCedulas += resultado[i];
                sb.append("R$ ").append(cedulas[i][0]).append(": ").append(resultado[i]).append(" nota(s)\n");
            }
        }
        sb.append("Cedulas: ").append(nCedulas);

        // Verificação de caixa vazio após transação
        int saldoNovo = total();
        if (saldoNovo <= cotaMin) sb.append("\nCaixa Vazio: Chame o Operador");

        // Gravação da transação
        extrato.add("Saque R$ " + valor + " | cedulas: " + nCedulas + " | saldo: R$ " + saldoNovo);
        return sb.toString();
    }

    // Algoritmo de Backtracking para testar todas as combinações de cédulas possíveis
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
        StringBuilder sb = new StringBuilder("=== CEDULAS ===\n");
        for (int[] c : cedulas) sb.append("R$ ").append(c[0]).append(": ").append(c[1]).append(" un.\n");
        sb.append("Total: R$ ").append(total());
        return sb.toString();
    }

    @Override
    public String reposicaoCedulas(Integer cedula, Integer qtd) {
        if (cedula == null || qtd == null || qtd <= 0) return "Parametros invalidos.";
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
        cotaMin = min;
        return "Cota minima: R$ " + min;
    }

    // Retorna o histórico formatado de transações
    public String gerarExtrato() {
        if (extrato.isEmpty()) return "Sem transacoes.";
        StringBuilder sb = new StringBuilder("=== EXTRATO ===\n");
        for (int i = 0; i < extrato.size(); i++) sb.append(i+1).append(". ").append(extrato.get(i)).append("\n");
        return sb.toString();
    }

    // Getters e Setters
    public int[][] getCedulas()      { return cedulas; }
    public int getCotaMinima()       { return cotaMin; }
    public List<String> getExtrato() { return extrato; }
    public void setCotaMinima(int v) { cotaMin = v; }

    //public static void main(String[] args) {
      //  ICaixaEletronico caixa = new CaixaEletronico();
        // GUI janela = new GUI(caixa);
        // janela.setVisible(true); // Substitui o janela.show()
    // }
}