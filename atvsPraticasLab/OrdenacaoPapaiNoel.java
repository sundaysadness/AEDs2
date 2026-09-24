import java.util.Scanner;

public class OrdenacaoPapaiNoel
{
    static void ordenacao(String[] s, int n)
    {
        //ordenacao por insercao
        for(int i = 1; i < n; i++)
        {
            String tmp = s[i];
            int j = i - 1;
            while( (j >= 0) && (s[j].compareTo(tmp) > 0) )
            {
                s[j + 1] = s[j];
                j--;
            }   
            s[j + 1] = tmp;
        }
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextInt();
        //ler enter
        sc.nextLine();
        String[] nomes = new String[n];
        int mais = 0;
        int menos = 0;
        for(int i = 0; i < n; i++)
        {
            String s = sc.nextLine();
            //separar em duas strings
            String[] p = s.split(" ");
            //contar + e -
            if(p[0].charAt(0) == '+')
            { mais++; }
            else
            { menos++; }
            //colocar segunda parte da string dividida (nomes)
            nomes[i] = p[1];
        }
        //ordenar
        ordenacao(nomes, n);
        //printar nomes
        for(int j = 0; j < n; j++)
        {
            System.out.println(nomes[j]);
        }
        //printar quantidade + e -
        System.out.printf("Se comportaram: %d | Nao se comportaram: %d\n", mais, menos);
        sc.close();
    }
}