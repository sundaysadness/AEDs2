//importar toda a biblioteca java
import java.util.*;

public class Quadraticos
{
    static void swap(int a, int b)
    {
        int tmp = a;
        a = b;
        b = tmp;
    }

    //Selection sort (não é estável)
    /*
    Seleciona o menor e coloca no inico
    */
    static void selecao(int[] array, int n)
    {
        for(int i = 0; i < n - 1; i++)
        {
            int menor = i;
            for(int j = i + 1; j < n; j++)
            {
                if(array[menor] > array[j])
                {
                    menor = j;
                }
            }
            swap(menor, i);
        }
    }

    //Insertion sort
    /*
    pega o proximo e insere no lugar certo dele no array ordenado
    */
    static void insercao(int[] array, int n)
    {
        for(int i = 1; i < n; i++)
        {
            int tmp = array[i];
            int j = i - 1;
            while( (j >= 0) && (array[j] > tmp))
            {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = tmp;
        }
    }

    //Bubble sort
    static void bolha(int[] array, int n)
    {
        for(int i = (n - 1); i > 0; i--) 
        {
			for(int j = 0; j < i; j++) 
            {
			    if(array[j] > array[j + 1]) 
                {
                    swap(j, j+1);
				}
			}
		}
    }

    //Main
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        double x = sc.nextDouble();
        String palavra = sc.next();      // até espaço
        String palavra2 = sc.nextLine();
        char c = sc.next().charAt(0);    // char não tem nextChar

        while (sc.hasNext()) { /* lê até EOF */ }
        sc.close();

        //5
        //Maria Silva
        int nu = sc.nextInt();
        sc.nextLine();                  // descarta o resto da linha do 5
        String nome = sc.nextLine();    // agora lê "Maria Silva"
        
        /*
            next(), nextInt(), nextDouble(): pulam espaços e quebras de linha antes do valor, leem só o token e param logo depois dele, sem consumir o \n
            nextLine(): não pula nada. Lê o que estiver na frente, mesmo que seja uma linha vazia
        */
    }
}