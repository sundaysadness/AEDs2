import java.util.Scanner;

public class InversaoString 
{
    public static void inverter(int t, String i, char[] s)
    {
        char temp;
        //caso base, acontece quando percorreu toda a string
        if(t < 0){ return; }
        else
        {
            //salva o caractere, de tras pra frente, da string original
            temp = i.charAt(t);
            //coloca o caractere no vetor char
            s[t] = temp;
            //printa ultima posicao
            System.out.print(s[t]);
            //chama a funcao para a ultima posicao printada - 1
            inverter(t - 1, i, s);
        }
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String entrada;
        entrada = sc.nextLine();
        while(!(entrada.length() == 3 && entrada.charAt(0) == 'F' && 
                entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M'))
        {
            int tamanho = entrada.length();
            //sera usado para a inversao, mesmo tamanho da original
            char[] saida = new char[tamanho];
            //tamanho comeca em t, posicao do ultimo caractere esta em t-1
            inverter(tamanho-1, entrada, saida);
            //quebra de linha
            System.out.println();
            //fazer leitura de nova linha
            entrada = sc.nextLine();
        }
        sc.close();
    }
}
