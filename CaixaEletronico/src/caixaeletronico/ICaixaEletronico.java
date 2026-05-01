package caixaeletronico;

/**
* Interface (contrato) para utilizacao da interface grafica.
* Define as operacoes de entrada e saida do caixa eletronico.
* IMPORTANTE: Nao altere nenhum metodo desta interface!
*
* @author Professor Jadir - UNICID
*/
public interface ICaixaEletronico {
 /**
 * Retorna o valor total disponivel no caixa.
 * @return String formatada com o total disponivel
 */
 public String pegaValorTotalDisponivel();
 /**
 * Efetua o saque do valor informado.
 * @param valor - valor a ser sacado
 * @return String informando o resultado do saque
 */
 public String sacar(Integer valor);
 /**
 * Retorna relatorio das cedulas e quantidades disponíveis.
 * @return String formatada com cedulas e quantidades
 */
 public String pegaRelatorioCedulas();
 /**
 * Efetua a reposicao de cedulas no caixa.
 * @param cedula - valor da cedula (2,5,10,20,50 ou 100)
 * @param quantidade - quantidade a adicionar
 * @return String informando o resultado
 */
 public String reposicaoCedulas(Integer cedula, Integer quantidade);
 /**
 * Armazena a cota minima de atendimento.
 * @param minimo - valor minimo para continuar operando
 * @return String confirmando a cota definida
 */
 public String armazenaCotaMinima(Integer minimo);
 
 
}