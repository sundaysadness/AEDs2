import java.util.Scanner;

public class ValidacaoSenha
{
    public static boolean letraMaiuscula(String entrada, int t)
    { 
        boolean resultado = false, controle = true;
        //percorre a string toda ou percorre enquanto controle for true
        for(int x = 0; x < t && controle; x++)
        {
            //se houver pelo menos uma letra maiuscula
            if(entrada.charAt(x) >= 65 && entrada.charAt(x) <= 90)
            {
                controle = false; resultado = true;
            }
        }
        return resultado;
    }

    public static boolean letraMinuscula(String entrada, int t)
    { 
        boolean resultado = false, controle = true;
        //percorre a string toda ou percorre enquanto controle for true
        for(int x = 0; x < t && controle; x++)
        {
            //se houver pelo menos uma letra minuscula
            if(entrada.charAt(x) >= 97 && entrada.charAt(x) <= 122)
            {
                controle = false; resultado = true;
            }
        }
        return resultado;
    }

    public static boolean numero(String entrada, int t)
    { 
        boolean resultado = false, controle = true;
        //percorre a string toda ou percorre enquanto controle for true
        for(int x = 0; x < t && controle; x++)
        {
            //se houver pelo menos um numero
            if(entrada.charAt(x) >= 48 && entrada.charAt(x) <= 57)
            {
                controle = false; resultado = true;
            }
        }
        return resultado;
    }

    public static boolean caractereEspecial(String entrada, int t)
    { 
        boolean resultado = false, controle = true;
        //percorre a string toda ou percorre enquanto controle for true
        for(int x = 0; x < t && controle; x++)
        {
            //se houver pelo menos um numero
            if(entrada.charAt(x) == '!' || entrada.charAt(x) == '@' || entrada.charAt(x) == '#' || 
               entrada.charAt(x) == '^' || entrada.charAt(x) == '@')
            {
                controle = false; resultado = true;
            }
        }
        return resultado;
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
            //pelo menos 8 caracteres
            if(tamanho >= 8)
            {
                if(letraMaiuscula(entrada, tamanho) && letraMinuscula(entrada, tamanho) && 
                   numero(entrada, tamanho) && caractereEspecial(entrada, tamanho))
                {
                    System.out.println("SIM");
                }
                else
                {
                    System.out.println("NAO");
                }
            }
            else
            {
                System.out.println("NAO");
            }
            //fazer leitura de nova linha
            entrada = sc.nextLine();
        }
        sc.close();
    }
}