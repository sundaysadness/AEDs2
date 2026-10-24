#include <stdio.h>
#include <stdlib.h>

int main()
{
    /** 
     * Seleção Parcial
    for(int i = 0; i < k; i++)
    {
        int menor = i;
        for(int j = (i + 1); j < n; j++)
        {
            if(vet[menor] > vet[j])
            {
                menor = j;
            }
        }
        swap(menor, i);
    }
    */

    /**
     * Inserção Parcial
    for(int i = 1; i < n; i++)
    {
        int tmp = vet[i];
        int j = (i < k) ? i - 1 : k - 1;
        while( (j >= 0) && (vet[j] > tmp) )
        {
            vet[j + 1] = vet[j];
            j--;
        }
        vet[j + 1] = tmp;
    }
    */

    /**
     * Quicksort Parcial
    void quicksort(int esq, int dir, int k) 
    {
        int i = esq,
        j = dir,
        pivo = vet[(esq+dir)/2];
        while(i <= j) 
        {
            while (vet[i] < pivo)
            i++;
            while (vet[j] > pivo)
            j--;
            if(i <= j)
            { swap(i, j); i++; j--; }
        }
        if (esq < j)
        quicksort(esq, j);
        if (i < k && i < dir)
        quicksort(i, dir);
    }
    */

    /**
     * Heapsort Parcial
    void heapsort() 
    {
        //Construção do heap com os k primeiros elementos
        for(int tam = 2; tam <= k; tam++)
            construir(tam);

        //Para cada um dos (n-k) demais elementos, se ele for menor que a raiz, inserir do heap
        for(int i = k + 1; i <= n; i++)
            if (vet[i] < vet[1])
            {
                swap(i , 1);
                reconstruir(k);
            }
                
        //Ordenação propriamente dita
        int tam = k;
        while(tam > 1)
        {
            swap(1, tam--);
            reconstruir(tam);
        }
    }
    */
}