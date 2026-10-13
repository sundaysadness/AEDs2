import java.util.*;
public class Q2Art
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext())
        {
            int n = sc.nextInt();
            Pessoa[] lista = new Pessoa[n];
            for(int i = 0; i < n; i++)
            {
                lista[i] = new Pessoa(sc.next(),sc.nextInt());
            }
            boolean pendenciasColculidas = false;
            while(!pendenciasColculidas)
            {
                pendenciasColculidas = true;
                for(int i = 0; i < n; i++)
                {
                    if(lista[i].pendencia >= 0)
                        {
                            System.out.print(lista[i].nome + " ");
                            lista[i].pendencia = lista[i].pendencia - 1;
                            pendenciasColculidas = false;
                        }
                }
            }
        }
    }

}

class Pessoa{
    String nome;
    int pendencia;
    public Pessoa(String n, int x) {
        nome = n ;
        pendencia = x;
    }
}