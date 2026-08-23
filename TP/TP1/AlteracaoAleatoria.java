import java.util.Scanner;
import java.util.Random;

public class AlteracaoAleatoria
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        Random gerador = new Random();
        gerador.setSeed(4);
        String entrada;
        //fazer leitura da primeira linha
        entrada = sc.nextLine();
        //fazer enquanto string for != FIM
        while(!(entrada.length() == 3 && entrada.charAt(0) == 'F' && 
                entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M'))
        {
            //gerar caracteres aleatorios
            char c1 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
            char c2 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
            //pegar tamanho da string de entrada e criar array de saida com o mesmo
            int tamanho = entrada.length();
            char[] saida = new char[tamanho];
            //preencher array de saida com conteudo da de entrada
            for(int i = 0; i < tamanho; i++)
            {
                saida[i] = entrada.charAt(i);
            }
            //percorrer toda a string procurando ocorrencia de c1
            for(int x = 0; x < tamanho; x++)
            {
                if(entrada.charAt(x) == c1)
                {
                    //substituir por c2 caso haja c1 naquela posicao
                    saida[x] = c2;
                }
            }
            System.out.println(saida);
            //fazer leitura de nova linha
            entrada = sc.nextLine();
        }
        sc.close();
    }
}