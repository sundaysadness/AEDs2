import java.util.*;

class Corredor
{
    String nome;
    int hora;
    int minuto;
    int segundo;

    public Corredor(String n, int h, int m, int s)
    {
        nome = n;
        hora = h;
        minuto = m;
        segundo = s;
    }
}

public class CorridaRua
{
    static boolean maior(Corredor a, Corredor b)
    {
        boolean resp;
        if(a.hora > b.hora){ resp = true; }
        else
        {
            if(a.hora < b.hora){ resp = false; }
            else //empate das horas
            {
                if(a.minuto > b.minuto){ resp = true; }
                else
                {
                    if(a.minuto < b.minuto){ resp = false; }
                    else //empate dos minutos
                    {
                        if(a.segundo > b.segundo){ resp = true; }
                        else
                        {
                            if(a.segundo < b.segundo){ resp = false; }
                            else //empate dos segundos
                            {
                                resp = false;
                            }
                        }
                    }
                }
            }
        }
        return resp;
    }

    public static void sortParcial(Corredor[] v, int q, int k)
    {
        //percorre o vetor inteiro (de 1 a n-1)
        for(int i = 1; i < q; i++)
        {
            Corredor tmp = v[i];
            //(i < k) elemento esta dentro da zona (segue insercao normal com i-1)
            //(i >= k) elemento veio de fora da zona (j comeca em k-1, ultimo da zona)
            int j = (i < k)? i - 1 : k - 1;

            while( (j >= 0) && (maior(v[j], tmp)) )
            {
                v[j + 1] = v[j];
                j--;
            }
            v[j + 1] = tmp;
        }
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext())
        {
            int q = sc.nextInt();
            int k = sc.nextInt();

            Corredor[] lista = new Corredor[q];
            for(int i = 0; i < q; i++)
            {
                String n = sc.next();
                int h = sc.nextInt();
                int m = sc.nextInt();
                int s = sc.nextInt();
                lista[i] = new Corredor(n, h, m ,s);
            }

            sortParcial(lista, q, k);

            for(int i = 0; i < k; i++)
            {
                System.out.println(lista[i].nome + " " + lista[i].hora + " " +
                lista[i].minuto + " " + lista[i].segundo);
            }
        }
    }
}