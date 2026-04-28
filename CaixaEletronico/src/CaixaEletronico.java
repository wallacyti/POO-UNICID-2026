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
 // TODO: Integrante 3 implementa aqui
 return "";
 }
 @Override
 public String sacar(Integer valor) {
 // TODO: Integrante 2 implementa aqui
 return "";
 }
 @Override
 public String pegaRelatorioCedulas() {
 // TODO: Integrante 2 implementa aqui
 return "";
 }
 @Override
 public String reposicaoCedulas(Integer cedula, Integer quantidade) {
 // TODO: Integrante 3 implementa aqui
 return "";
 }
 @Override
 public String armazenaCotaMinima(Integer minimo) {
 // TODO: Integrante 3 implementa aqui
 return "";
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
 public static void main(String[] args) {
 // NAO ALTERE ESTA LINHA — e exatamente como o professor pediu
 // GUI janela = new GUI(CaixaEletronico.class);
 // janela.show();
 }
}

// Wallacy é gay
