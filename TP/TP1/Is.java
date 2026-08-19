import java.util.Scanner;

public class Is 
{
    public static boolean vogais(String entrada) 
    { 
        boolean resultado = true;
        boolean controle = true;
        int tamanho = entrada.length();
        //percorre a string toda ou percorre enquanto controle for true
        for(int x = 0; x < tamanho && controle; x++)
        {
            //se hover algum caractere diferente de vogal
            if(entrada.charAt(x) != 'a' && entrada.charAt(x) != 'e' && entrada.charAt(x) != 'i'
                                         && entrada.charAt(x) != 'o' && entrada.charAt(x) != 'u')
            {
                //string nao tem so vogal
                controle = false;
                resultado = false;
            }
        }
        return resultado;
    }

    public static boolean consoantes(String entrada)
    { 
        boolean resultado = true;
        boolean controle = true;
        int tamanho = entrada.length();
        //percorre a string toda ou percorre enquanto controle for true
        for(int x = 0; x < tamanho && controle; x++)
        {
            //se houver alguma vogal ou for um numero
            if((entrada.charAt(x) == 'a' || entrada.charAt(x) == 'e' || entrada.charAt(x) == 'i'
                                         || entrada.charAt(x) == 'o' || entrada.charAt(x) == 'u')
                                         || (entrada.charAt(x) >= 48 && entrada.charAt(x) <= 57))
            {
                //string nao tem so consoante 
                controle = false;
                resultado = false;
            }
        }
        return resultado;
    }

    public static boolean inteiro(String entrada)
    { 
        boolean resultado = true;
        boolean controle = true;
        int tamanho = entrada.length();
        //percorre a string toda ou percorre enquanto controle for true
        for(int x = 0; x < tamanho && controle; x++)
        {
            //se o caractere estiver fora do intervalo dos numeros
            if(entrada.charAt(x) < 48 || entrada.charAt(x) > 57)
            {
                //nao é um numero
                controle = false;
                resultado = false;
            }
            //se houver ponto ou virgula
            if(entrada.charAt(x) == '.' || entrada.charAt(x) == ',')
            {
                //nao é um numero inteiro
                controle = false;
                resultado = false;
            }
        }
        return resultado;
    }   
    public static boolean real(String entrada) 
    { 
        boolean resultado = true;
        boolean controle = true;
        int contador = 0;
        int tamanho = entrada.length();
        //percorre a string toda ou percorre enquanto controle for true
        for(int x = 0; x < tamanho && controle; x++)
        {
            //se o caractere estiver fora do intervalo dos numeros ou for != de ponto e de virgula
            if((entrada.charAt(x) < 48 || entrada.charAt(x) > 57) && entrada.charAt(x) != 46 
                                                                  && entrada.charAt(x) != 44)
            {
                //nao é um numero
                controle = false;
                resultado = false;
            }
            //contar ocorrencia de ponto ou virgula
            if(entrada.charAt(x) == '.' || entrada.charAt(x) == ',')
            {
                contador = contador + 1;;
            }
        }
        //se houver mais de um entao nao é um numero que exista
        if(contador > 1){ resultado = false; }
        return resultado;
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String entrada;
        //fazer leitura da primeira linha
        entrada = sc.nextLine();
        //fazer enquanto string for != de FIM
        while(!(entrada.length() == 3 && entrada.charAt(0) == 'F' && 
                entrada.charAt(1) == 'I' && entrada.charAt(2) == 'M'))
        {
            boolean x1, x2, x3, x4;

            x1 = vogais(entrada);
            x2 = consoantes(entrada);
            x3 = inteiro(entrada);
            x4 = real(entrada);

            if(x1){ System.out.print("SIM"); } else { System.out.print("NAO"); }
            System.out.print(" ");
            if(x2){ System.out.print("SIM"); } else { System.out.print("NAO"); }
            System.out.print(" ");
            if(x3){ System.out.print("SIM"); } else { System.out.print("NAO"); }
            System.out.print(" ");
            if(x4){ System.out.print("SIM"); } else { System.out.print("NAO"); }
            System.out.println();
            
            //fazer leitura de nova linha
            entrada = sc.nextLine();
        }
        sc.close();
    }
}
