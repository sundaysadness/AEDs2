import java.util.*;

class Aluno
{
    String nome;
    int nota;
    int falta;

    public Aluno(int n, int f, String name)
    {
        nota = n;
        falta = f;
        nome = name;
    }
}

public class DestaqueAcademico
{
    public static void swap(Aluno[] v, int i, int j)
    {
        Aluno temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }
     
    public static boolean compare(Aluno a, Aluno b)
    {
        boolean result;
        if(a.nota > b.nota){ result = true; }
        else
        {
            if(a.nota < b.nota){ result = false; }
            else //se nota for igual, fazer desempate por falta
            {
                if(a.falta < b.falta){ result = true; }
                else
                {
                    if(a.falta > b.falta){ result = false; }
                    else //se a falta tbm empatar, desempatar por nome
                    {
                        if(a.nome.compareTo(b.nome) < 0){ result = true; }
                        else
                        {
                            result = false;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void sort(Aluno[] v, int esq, int dir, int k) 
    {
        quicksortParcial(v, 0, dir-1, k);
    }

    public static void quicksortParcial(Aluno[] v, int esq, int dir, int k)
    {
        int i = esq, j = dir;
        Aluno pivo = v[(dir+esq)/2];
        while (i <= j) 
        {
            while(compare(v[i], pivo)) i++;
            //correcao aqui, antes estava !compare(v[j], pivo), o que nao funcionava pois significava 
            //“v[j] não vem antes do pivô” e nao “v[j] vem depois do pivô”
            while(compare(pivo, v[j])) j--;
            if(i <= j) 
            {
                swap(v, i, j);
                i++;
                j--;
            }
        }
        if(esq < j) quicksortParcial(v, esq, j, k);
        if(i < k && i < dir) quicksortParcial(v, i, dir, k);
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext())
        {
            int n = sc.nextInt();
            int k = sc.nextInt();

            Aluno[] v = new Aluno[n];

            sc.nextLine(); //consumir o resto da linha de "n k"

            for(int i = 0; i < n; i++)
            {
                //usar split para retirar espaco do nome
                //o 3 do final indica um limite, para que o nome fique inteiro
                String[] p = sc.nextLine().split(" ", 3);
                v[i] = new Aluno(Integer.parseInt(p[0]), Integer.parseInt(p[1]), p[2]);
            }

            sort(v, 0, n, k);

            for(int i = 0; i < k; i++)
            {
                System.out.println(v[i].nota + " " + v[i].falta + " " + v[i].nome);
            }
        }
    }
}