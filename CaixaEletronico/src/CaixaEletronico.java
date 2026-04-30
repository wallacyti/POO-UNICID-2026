import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal do Caixa Eletrônico.
 * Gerencia o estoque de cédulas, saques, reposição e cota mínima.
 * Implementa o contrato da interface ICaixaEletronico.
 *
 * @author Grupo - UNICID POO 2026
 */
public class CaixaEletronico implements ICaixaEletronico {

    /**
     * Matriz de cédulas em estoque.
     * Coluna 0 = Valor da nota (100, 50, 20, 10, 5, 2).
     * Coluna 1 = Quantidade disponível.
     */
    private int[][] cedulas = {
        { 100, 100 },
        { 50, 200 },
        { 20, 300 },
        { 10, 350 },
        { 5, 450 },
        { 2, 500 }
    };

    /** Valor mínimo em dinheiro para que o caixa continue operando. */
    private int cotaMinima = 0;

    /** Lista que guarda o histórico de todas as operações (saques) realizados. */
    private List<String> extrato = new ArrayList<>();

    /**
     * Calcula e retorna o valor total em reais disponível no caixa.
     * 
     * @return String contendo o valor total somado.
     */
    @Override
    public String pegaValorTotalDisponivel() {
        int valorTotal = 0;
        for (int i = 0; i < cedulas.length; i++) {
            valorTotal += cedulas[i][0] * cedulas[i][1];
        }
        return String.valueOf(valorTotal);
    }

    /**
     * Efetua o saque priorizando as notas de maior valor.
     * Bloqueia a operação se faltar saldo, ultrapassar 30 cédulas ou atingir a cota mínima.
     * 
     * @param valor Valor inteiro que o cliente deseja sacar.
     * @return String informando o sucesso ou o motivo do erro da operação.
     */
    @Override
    public String sacar(Integer valor) {
        if (valor == null || valor <= 0) {
            return "Erro: informe um valor positivo para o saque.";
        }

        if (calcularTotal() <= cotaMinima) {
            return "Caixa Vazio: Chame o Operador";
        }

        int[] notasUsadas = new int[cedulas.length];
        int valorRestante = valor;
        int totalCedulas = 0;

        for (int i = 0; i < cedulas.length; i++) {
            int valorNota = cedulas[i][0];
            int disponivel = cedulas[i][1];
            if (valorNota > valorRestante) continue;
            
            int usar = Math.min(valorRestante / valorNota, disponivel);
            notasUsadas[i] = usar;
            valorRestante -= usar * valorNota;
            totalCedulas += usar;
        }

        if (valorRestante != 0) {
            return "Saque nao realizado por falta de cedulas.\n" +
                   "Nao e possivel pagar R$ " + valor + " com as notas disponiveis.";
        }

        if (totalCedulas > 30) {
            return "Saque nao permitido: seriam emitidas " + totalCedulas +
                   " cedulas.\nLimite maximo e 30 cedulas por saque.";
        }

        StringBuilder resultado = new StringBuilder();
        resultado.append("=== SAQUE REALIZADO ===\n");
        resultado.append("Valor: R$ ").append(valor).append("\n");
        resultado.append("Cedulas emitidas:\n");

        for (int i = 0; i < cedulas.length; i++) {
            if (notasUsadas[i] > 0) {
                cedulas[i][1] -= notasUsadas[i];
                resultado.append("  R$ ").append(cedulas[i][0])
                         .append(": ").append(notasUsadas[i]).append(" nota(s)\n");
            }
        }

        resultado.append("Total de cedulas: ").append(totalCedulas).append("\n");

        int totalApos = calcularTotal();
        if (totalApos <= cotaMinima) {
            resultado.append("\nCaixa Vazio: Chame o Operador");
        }

        extrato.add("Saque R$ " + valor + " | Cedulas: " + totalCedulas + " | Saldo apos: R$ " + totalApos);

        return resultado.toString();
    }

    /**
     * Gera um relatório mostrando a quantidade restante de cada nota no cofre.
     * 
     * @return String formatada com a listagem das notas.
     */
    @Override
    public String pegaRelatorioCedulas() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RELATORIO DE CEDULAS ===\n");
        sb.append("----------------------------\n");
        for (int i = 0; i < cedulas.length; i++) {
            sb.append("Nota R$ ").append(cedulas[i][0])
              .append(": ").append(cedulas[i][1]).append(" unidade(s)\n");
        }
        sb.append("----------------------------\n");
        sb.append("Total: R$ ").append(calcularTotal());
        return sb.toString();
    }

    /**
     * Abastece o caixa adicionando mais unidades a uma cédula específica.
     * 
     * @param cedula Valor da nota que será abastecida (ex: 50).
     * @param quantidade Quantidade de notas que estão sendo inseridas.
     * @return String informando se a nota foi aceita ou se é inválida.
     */
    @Override
    public String reposicaoCedulas(Integer cedula, Integer quantidade) {
        for (int i = 0; i < cedulas.length; i++) {
            if (cedulas[i][0] == cedula) {
                cedulas[i][1] += quantidade;
                return "Cedulas repostas com sucesso!";
            }
        }
        return "Cedula invalida";
    }

    /**
     * Define o limite mínimo de dinheiro para o caixa continuar funcionando.
     * 
     * @param minimo Valor em reais da nova cota mínima.
     * @return String com a mensagem de sucesso ou erro de validação.
     */
    @Override
    public String armazenaCotaMinima(Integer minimo) {
        if (minimo == null) {
            return "Erro: O valor da cota minima nao pode ser nulo.";
        }
        if (minimo < 0) {
            return "Erro: O valor da cota minima nao pode ser negativo.";
        }
        this.cotaMinima = minimo;
        return "Cota minima atualizada com sucesso.";
    }

    /**
     * Método auxiliar interno para somar o dinheiro físico presente no cofre.
     * 
     * @return Total financeiro do caixa em número inteiro.
     */
    private int calcularTotal() {
        int total = 0;
        for (int i = 0; i < cedulas.length; i++) {
            total += cedulas[i][0] * cedulas[i][1];
        }
        return total;
    }

    /**
     * Cria e formata o histórico de todas as movimentações da sessão atual.
     * 
     * @return String completa com a listagem dos saques realizados.
     */
    public String gerarExtrato() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== EXTRATO DA SESSÃO ===\n\n");
        
        if (extrato.isEmpty()) {
            sb.append("Nenhuma transação realizada nesta sessão.\n");
        } else {
            sb.append("Total de saques: ").append(extrato.size()).append("\n\n");
            for (int i = 0; i < extrato.size(); i++) {
                sb.append((i + 1)).append(". ").append(extrato.get(i)).append("\n");
            }
        }
        sb.append("\nTotal em caixa atual: R$ ").append(pegaValorTotalDisponivel());
        return sb.toString();
    }

    public int[][] getCedulas() { return cedulas; }
    public int getCotaMinima() { return cotaMinima; }
    public List<String> getExtrato() { return extrato; }
    public void setCotaMinima(int v) { this.cotaMinima = v; }

    /**
     * Método principal que inicializa a Interface Gráfica do sistema.
     */
    public static void main(String[] args) {
       
    	// JOão coloca GUI Aqui
       // GUI janela = new GUI(CaixaEletronico.class);
        // janela.show();
    }
}