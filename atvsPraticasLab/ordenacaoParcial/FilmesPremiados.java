import java.util.*;

class Filmes
{
    int premios;
    int ano;
    String nome;

    Filmes(int p, int a, String n)
    {
        premios = p;
        ano = a;
        nome = n;
    }
}

public class FilmesPremiados
{
    static boolean maior(Filmes a, Filmes b)
    {
        if(a.premios != b.premios) return a.premios < b.premios; //mais premios
        if(a.ano != b.ano) return a.ano > b.ano; 
        return a.nome.compareTo(b.nome) > 0; //pergunta se a é maior que b, se sim retorna true
    }

    static void swap(Filmes[] v, int i, int j) 
    {
        Filmes tmp = v[i];
        v[i] = v[j];
        v[j] = tmp;
    }

    //sobe o elemento da posição 'tam' até a posição correta (max-heap)
    static void construir(Filmes[] v, int tam) 
    {
        for(int i = tam; i > 1; i /= 2) 
        {
            if(maior(v[i], v[i / 2]))
            {
                swap(v, i, i / 2);
            }
        }
    }

    //desce a raiz até a posição correta, considerando o heap de tamanho 'tam'
    static void reconstruir(Filmes[] v, int tam) 
    {
        int i = 1;
        while(2 * i <= tam) 
        {
            int filho = 2 * i;
            if(filho < tam && maior(v[filho + 1], v[filho])) 
            {
                filho++;                      //escolhe o maior filho
            }
            if(maior(v[filho], v[i])) 
            {
                swap(v, i, filho);
                i = filho;
            } else 
            {
                break;
            }
        }
    }


    public static void heapsort(int n, int k, Filmes[] v)
    {
        //Construção do heap com os k primeiros elementos
        for(int tam = 2; tam <= k; tam++)
        construir(v, tam);
        //Para cada um dos (n-k) demais elementos, se ele
        // for menor que a raiz, inserir do heap
        for(int i = k + 1; i <= n; i++)
        if(maior(v[1], v[i]))
        {
            swap(v, i , 1);
            reconstruir(v, k);
        }
        //Ordenação propriamente dita
        int tam = k;
        while(tam > 1)
        {
            swap(v, 1, tam--);
            reconstruir(v, tam);
        }
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            int n = sc.nextInt();
            int k = sc.nextInt();

            Filmes[] lista = new Filmes[n+1];

            sc.nextLine(); //consumir o resto da linha de "n k"

            //correcao de indice
            for(int i = 1; i <= n; i++)
            {
                String[] p = sc.nextLine().split(" ", 3);
                lista[i] = new Filmes(Integer.parseInt(p[0]), Integer.parseInt(p[1]), p[2]);
            }

            heapsort(n, k, lista);

            //correcao de indice
            for(int i = 1; i <= k; i++)
            {
                System.out.println(lista[i].premios + " " + lista[i].ano + " " + lista[i].nome);
            }
        }
    }
}