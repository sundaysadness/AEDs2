#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct
Aluno
{
    char nome[51];
    int nota;
    int falta; 

} Aluno;

void swap(Aluno *v, int i, int j)
{
    Aluno temp = v[i];
    v[i] = v[j];
    v[j] = temp;
}
     
int compare(Aluno *a, Aluno *b)
{
    if(a->nota != b->nota) return a->nota > b->nota;       //maior nota primeiro
    if(a->falta != b->falta) return a->falta < b->falta;   //menos faltas primeiro
    return strcmp(a->nome, b->nome) < 0;                   //nome em ordem alfabetica
}

void quicksortParcial(Aluno *v, int esq, int dir, int k)
{
    int i = esq, j = dir;
    Aluno pivo = v[(dir+esq)/2];
    while (i <= j) 
    {
        while(compare(&v[i], &pivo)) i++;
        while(compare(&pivo, &v[j])) j--;
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

void sort(Aluno *v, int n, int k) 
{
    quicksortParcial(v, 0, n-1, k);
}

int main()
{
    int n, k;

    while(scanf("%d %d", &n, &k) == 2)
    {
        Aluno *v = malloc(n * sizeof(Aluno));

        for(int i = 0; i < n; i++)
        {
            // %[^\n] aceitar qualquer caractere, exceto a quebra de linha
            scanf("%d %d %255[^\n]", &v[i].nota, &v[i].falta, v[i].nome);
        }

        sort(v, n, k);

        for(int i = 0; i < k; i++)
        {
            printf("%d %d %s\n", v[i].nota, v[i].falta, v[i].nome);
        }

        free(v);
    }
}
