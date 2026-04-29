import java.util.ArrayList;
import java.util.List;
/**
* Classe principal do Caixa Eletronico.
* Implementa o contrato ICaixaEletronico fornecido pelo professor.
*
* ESTRUTURA DA MATRIZ cedulas[linha][coluna]:
* coluna 0 = valor da cedula (100, 50, 20, 10, 5, 2)
* coluna 1 = quantidade disponivel de cada cedula
*
* @author Grupo - UNICID POO 2026
* @version 1.0
*/
public class CaixaEletronico implements ICaixaEletronico {
 // =====================================================
 // ATRIBUTOS DA CLASSE
 // =====================================================
 /**
 * Matriz de cedulas: linha=tipo de nota, coluna0=valor, coluna1=quantidade.
 * Valores iniciais conforme especificacao do professor.
 */
 private int[][] cedulas = {
 { 100, 100 }, // 100 notas de R$100
 { 50, 200 }, // 200 notas de R$50
 { 20, 300 }, // 300 notas de R$20
 { 10, 350 }, // 350 notas de R$10
 { 5, 450 }, // 450 notas de R$5
 { 2, 500 } // 500 notas de R$2
 };
 /** Cota minima de valor total para atender clientes. */
 private int cotaMinima = 0;
 /** Historico de todas as transacoes da sessao (extrato). */
 private List<String> extrato = new ArrayList<>();
 // =====================================================
 // METODOS DA INTERFACE (preenchidos por Int.2 e Int.3)
 // =====================================================
 @Override
 public String pegaValorTotalDisponivel() {
	 int valorTotal = 0;
	 
	 //Percorre toda a linha da matriz de cedulas
	 for (int i = 0; i < cedulas.length; i++) {
		 int valorDaNota = cedulas[i][0];
		 int quantidadeDisponivel = cedulas[i][1];
		 
	//Multiplica o valor da nota pela quantidade e soma ao total
		 valorTotal += (valorDaNota * quantidadeDisponivel);
	 }
	 
 return String.value0f(valorTotal);
 }
 @Override
 public String sacar(Integer valor) {

     // Validacao 1: valor invalido
     if (valor == null || valor <= 0) {
         return "Erro: informe um valor positivo para o saque.";
     }

     // Validacao 2: cota minima
     if (calcularTotal() <= cotaMinima) {
         return "Caixa Vazio: Chame o Operador";
     }

     // Calculo das notas
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

     // Validacao 3: nao conseguiu pagar
     if (valorRestante != 0) {
         return "Saque nao realizado por falta de cedulas.\n" +
                "Nao e possivel pagar R$ " + valor + " com as notas disponiveis.";
     }

     // Validacao 4: mais de 30 cedulas
     if (totalCedulas > 30) {
         return "Saque nao permitido: seriam emitidas " + totalCedulas +
                " cedulas.\nLimite maximo e 30 cedulas por saque.";
     }

     // Efetua o saque — atualiza a matriz
     StringBuilder resultado = new StringBuilder();
     resultado.append("=== SAQUE REALIZADO ===\n");
     resultado.append("Valor: R$ " + valor + "\n");
     resultado.append("Cedulas emitidas:\n");

     for (int i = 0; i < cedulas.length; i++) {
         if (notasUsadas[i] > 0) {
             cedulas[i][1] -= notasUsadas[i];
             resultado.append("  R$ " + cedulas[i][0] +
                              ": " + notasUsadas[i] + " nota(s)\n");
         }
     }

     resultado.append("Total de cedulas: " + totalCedulas + "\n");

     // Verifica cota minima apos saque
     int totalApos = calcularTotal();
     if (totalApos <= cotaMinima) {
         resultado.append("\nCaixa Vazio: Chame o Operador");
     }

     // Registra no extrato
     extrato.add("Saque R$ " + valor + " | Cedulas: " +
                 totalCedulas + " | Saldo apos: R$ " + totalApos);

     return resultado.toString();
 }
 @Override
 public String pegaRelatorioCedulas() {
     StringBuilder sb = new StringBuilder();
     sb.append("=== RELATORIO DE CEDULAS ===\n");
     sb.append("----------------------------\n");
     for (int i = 0; i < cedulas.length; i++) {
         sb.append("Nota R$ " + cedulas[i][0] +
                   ": " + cedulas[i][1] + " unidade(s)\n");
     }
     sb.append("----------------------------\n");
     sb.append("Total: R$ " + calcularTotal());
     return sb.toString();
 }
 @Override
 public String reposicaoCedulas(Integer cedula, Integer quantidade) {
	 //Percorre as gavetas do caixa para achar a nota certa
	 for (int = 0; i < cedulas.length; i++) {
		 //Se achou a gaveta com o valor da cedula que o carro-forte trouxe
		 if (cedulas[i][0] == cedula) {
			 // soma a quantidade nova com a que já tinha lá
			 cedulas[i][1] += quantidade;
			 return "Cédulas repostas com sucesso!";
		 }
	 }
	 return "Cédula inválida";
 }
 
 @Override
 public String armazenaCotaMinima(Integer minimo) {
 // 1º Segurança: Verifica se o valor veio vazio
	if (minimo = null) {
		return "Erro: O valor da cota minima não pode ser nulo.";
	}
	
// 2º Segurança: Verifica se o valor é negativo
	if (minimo = 0) {
		return "Erro: O valor da cota minima não pode ser negativo.";
	}
//Se passou pela segurança, pode salvar no post-it!
	this.cotaMinima = minimo;
	return "Cota minima atualizada com sucesso.";
 }
 
//Metodo auxiliar — calcula o total disponivel no caixa
private int calcularTotal() {
  int total = 0;
  for (int i = 0; i < cedulas.length; i++) {
      total += cedulas[i][0] * cedulas[i][1];
  }
  return total;
}
 
 // =====================================================
 // GETTERS (para os outros integrantes acessarem)
 // =====================================================
 public int[][] getCedulas() { return cedulas; }
 public int getCotaMinima() { return cotaMinima; }
 public List<String> getExtrato() { return extrato; }
 public void setCotaMinima(int v) { this.cotaMinima = v; }
 // =====================================================
 // METODO MAIN — inicia a interface grafica do professor
 // =====================================================
 //public static void main(String[] args) {
 // NAO ALTERE ESTA LINHA — e exatamente como o professor pediu
 // GUI janela = new GUI(CaixaEletronico.class);
 // janela.show();
// }
 
//Teste temporário sem a GUI:
 public static void main(String[] args) {
	    CaixaEletronico caixa = new CaixaEletronico();

	    System.out.println("=== TESTE RELATORIO ===");
	    System.out.println(caixa.pegaRelatorioCedulas());

	    System.out.println("\n=== TESTE SAQUE R$380 ===");
	    System.out.println(caixa.sacar(380));

	    System.out.println("\n=== TESTE SAQUE R$3 (impossivel) ===");
	    System.out.println(caixa.sacar(3));

	    System.out.println("\n=== RELATORIO APOS SAQUE ===");
	    System.out.println(caixa.pegaRelatorioCedulas());
	}
}