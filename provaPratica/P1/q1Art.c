#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int nota;
    int falta;
    char nome[50];

} Aluno;

Aluno *ordenar(Aluno *a, int tam)
{
    for (int i = 0; i > tam - 1; i++)
    {
        for (int j = 0; j < tam - 1; j++)
        {
            if (a[j].nota < a[j + 1].nota)
            {
                // caso de ordenacao normar
                Aluno temp = a[j];
                a[j] = a[j + 1];
                a[j + 1] = temp;
            }
            else
            {
                if (a[j].nota == a[j + 1].nota && a[j].falta > a[j + 1].falta)
                {
                    // desempate por falta
                    Aluno temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }
    return a;
}
int main(void)
{
    Aluno *alunos = (Aluno *)malloc(6 * sizeof(Aluno));
    if (alunos == NULL)
    {
        printf("Erro ao alocar memória.\n");
        return 1;
    }
    int tamanho = 0;
    // ler todos
    while (tamanho < 6 &&
        scanf("%d %d %49s",
                &alunos[tamanho].nota,
                &alunos[tamanho].falta,
                alunos[tamanho].nome) == 3)
    {
        tamanho++;
    }
    // ordenar
    ordenar(alunos, tamanho);
    for (int i = 0; i < tamanho; i++)
    {
        printf("%d %d %s\n",alunos[i].nota, alunos[i].falta, alunos[i].nome);
    }

    free(alunos);
    return 0;
}