#include <stdio.h>

int main()
{
    /**
     * QUICKSORT
     * OBS: Na primeira chamada, teremos: quicksort(0, n-1)
     *      Mediana é a melhor escolha de pivô
    void quicksort(int esq, int dir) 
    {
        int i = esq, j = dir, pivo = vet[(esq+dir)/2];
        while (i <= j) 
        {
            while(vet[i] < pivo)
            i++;
            while(vet[j] > pivo)
            j--;
            if(i <= j)
            { swap(i, j); i++; j--; }
        }
        if (esq < j)
        quicksort(esq, j);
        if (i < dir)
        quicksort(i, dir);
        }
    */

    /**
     * MERGESORT
    void mergesort(int esq, int dir) 
    {
        if(esq < dir)
        {
            int meio = (esq + dir) / 2;
            mergesort(esq, meio);
            mergesort(meio + 1, dir);
            intercalar(esq, meio, dir);
        }
    }

    void intercalar(int esq, int meio, int dir)
    {
        //Definir tamanho dos dois subvetores
        int nEsq = (meio+1)-esq;
        int nDir = dir - meio;
        int* vetEsq = (int*) malloc((nEsq+1) * sizeof(int));
        int* vetDir = (int*) malloc((nDir+1) * sizeof(int));

        //0x7FFFFFFF é um sentinela e assumimos que todos os
        //elementos do vetor são menores que esse valor
        vetEsq[nEsq] = vetDir[nDir] = 0x7FFFFFFF;
        int iEsq, iDir, i;

        //Inicializar primeiro subvetor
        for(iEsq = 0; iEsq < nEsq; vetEsq[iEsq++] = vet[esq+iEsq]);

        //Inicializar segundo subvetor
        for(iDir = 0; iDir < nDir; vetDir[iDir++] = vet[(meio+1)+iDir]);

        //Intercalação propriamente dita, sendo vet[] uma variável global
        for(iEsq = iDir = 0, i = esq; i <= dir; i++)
        {
            vet[i] =(vetEsq[iEsq] <= vetDir[iDir]) ? vetEsq[iEsq++] : vetDir[iDir++];
        }
    }
    */

    /**
     * HEAPSORT
    void construir(int* vet, int tamHeap) 
    {
        for (int i = tamHeap; i > 1 && vet[i] > vet[i/2]; i /= 2) 
        {
            swap(vet, i, i/2);
        } 
    }
    int* uniao(int* heap1, int tamHeap1, int* heap2, int tamHeap2) {
    int* resp = (int*) malloc((tamHeap1 + tamHeap2 + 1) * sizeof(int));

    //Copiar todos os elementos de heap1 para a resposta
    for (int i = 1; i <= tamHeap1; resp[i++] = heap1[i]);

    //Para cada elemento de heap2, insira-o na resposta e chame o método construir
    for (int i = 1; i <= tamHeap2; i++) 
    {
        resp[tamHeap1 + i] = heap2[i];
        construir(resp, tamHeap1 + i);
    }
    return resp;
    }

    void heapsort() 
    {
        //Construção do heap
        for
        (int tam = 2; tam <= n; tam++){

        construir(tam);
    }

    Ordenação propriamente dita
    int tam = n;
    while(tam > 1)
    {
        swap(1, tam--);
        reconstruir(tam);
    }
    }

    void construir(int tam){
    }

    void reconstruir(int tam){
    }
    */
}