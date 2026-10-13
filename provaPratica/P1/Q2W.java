import java.util.*;

class Paciente
{
    String nome;
    int pend;

    public Paciente(String n, int p)
    {
        this.nome = n;
        this.pend = p;
    }
}

public class Q2W
{
    public void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            int n = sc.nextInt();
            Paciente[] fila = new Paciente[n];
            int inicio = 0;
            int fim = 0;
            int qnt = 0;
            for(int i = 0; i < n; i++)
            {
                fila[fim] = new Paciente(sc.next(), sc.nextInt());
                fim = (fim + 1) % fila.length;
                qnt++;
            }

            while(qnt > 0)
            {
                Paciente p = fila[inicio];
                inicio = (inicio + 1) % fila.length;
                qnt--;
                System.out.print(p.nome + " ");
                if(p.pend > 0)
                {
                    p.pend--;
                    fila[fim] = p;
                    qnt++;
                    fim = (fim + 1) % fila.length;
                }
            }
            System.out.println();
        }
    }
}