import java.util.Scanner;

public class SomaDeDigitos 
{
    public static int digitosSomados(int n)
    {
        int soma = 0;
        //caso base, acontece quando todos os digitos do numero original foram separados e somados
        if(n == 0){ soma = 0; }
        else
        {
            //isola digito por digito
            soma = n % 10;
            //soma o digito e pega o proximo, chamando a funcao pro numero original / 10
            //o que retira o digito que ja foi somado para efetuar a proxima chamada 
            soma = soma + digitosSomados(n / 10);
        }
        return soma;
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int n; 

        while(sc.hasNextInt())
        {
            n = sc.nextInt();
            System.out.println(digitosSomados(n));
        }
        sc.close();
    }
}
