import java.util.Scanner;

public class Ciframento
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String entrada;
        char temp;
        char c;
        //fazer leitura da primeira linha
        entrada = sc.nextLine();
        //fazer enquanto a entrada for diferente da palavra "FIM"
        //conferir o tamanho para evitar StringIndexOutOfBoundsException em string vazia
        while(!(entrada.length() == 3 &&
                entrada.charAt(0) == 'F' && 
                entrada.charAt(1) == 'I' &&
                entrada.charAt(2) == 'M'))
        {
            int tamanho = entrada.length();
            /** array de caracteres, tentei fazer usando string para saida, mas nao foi 
                possivel, pois string em java é imutavel e "saida.charAt(x)" nao me permite 
                gravar um valor naquela posicao */
            char[] saida = new char[tamanho];
            //percorre a string de entrada
            for(int x = 0; x < tamanho; x++)
            {
                //salva o caractere da string original
                temp = entrada.charAt(x);
                //soma para tres posicoes a frente
                //typecasting necessario para nao ficar int
                c = (char) (temp + 3);
                //colocar criptografia na posicao equivalente do array de saida
                saida[x] = c;
            }
            System.out.println(saida);
            //fazer leitura de nova linha
            entrada = sc.nextLine();
        }
        sc.close();
    }
}