import java.util.*;

public class AquecimentoIterativoJava 
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String entrada;
        entrada = sc.nextLine();
        
        while(!entrada.equals("FIM") )
        {
            int c = 0;
            int tamanho = entrada.length();
            for(int x = 0; x < tamanho; x++)
            {
                if(entrada.charAt(x) >= 'A' && entrada.charAt(x) <= 'Z')
                {
                    c++;
                }
            }
            System.out.println(c);
            entrada = sc.nextLine();
        }
        sc.close();
    }
}
