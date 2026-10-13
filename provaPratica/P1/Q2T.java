import java.util.Scanner;

class Pessoa
{
    String nome;
    int pend;

    public Pessoa(String n, int p)
    {
        this.nome = n;
        this.pend = p;
    }
}

public class Q2T
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        
        while(sc.hasNext())
        {
            int n = sc.nextInt();
            Pessoa[] paciente = new Pessoa[n];
            for(int i = 0; i < n; i++)
            {
                String s = sc.next();
                int p = sc.nextInt();
                paciente[i] = new Pessoa(s, p);
            }

            boolean pendFim = false;

            while(!pendFim)
            {
                pendFim = true;
                for(int y = 0; y < n; y++)
                {
                    if(paciente[y].pend >= 0)
                    {
                        System.out.print(paciente[y].nome + " ");
                        paciente[y].pend--;
                        pendFim = false;
                    }
                }    
            }
            System.out.println();
        }
    }
}