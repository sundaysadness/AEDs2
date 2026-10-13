#include <stdio.h>

typedef struct
Alunos
{
    int nota;
    int falta;
    char nome[51];
} Alunos;

void ordenar(Alunos v[], int n)
{
    for(int y = 1; y < n; y++)
    {
        int j = y - 1;
        Alunos tmp = v[y];
        while( 
               //enquanto indice for valido e
               (j >= 0) && 
               //enquanto nota for maior e falta for menor
               (v[j].nota < tmp.nota || (v[j].nota == tmp.nota && v[j].falta > tmp.falta) ) )
        {
            v[j + 1] = v[j];
            j--;
        }
        v[j + 1] = tmp;
    }
}

int main()
{
    //objeto array do tipo da struct
    Alunos aluno[100];
    int i = 0;
    //ler nota, falta e string
    while(scanf("%d %d %[^\n]", &aluno[i].nota, &aluno[i].falta, aluno[i].nome) == 3)
    {
        i++;
    }
    //chamar metodo de ordenacao
    ordenar(aluno, i);
    //mostrar
    for(int x = 0; x < i; x++)
    {
        printf("%d %d %s\n", aluno[x].nota, aluno[x].falta, aluno[x].nome);
    }
}