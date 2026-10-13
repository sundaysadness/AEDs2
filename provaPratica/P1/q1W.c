#include <stdio.h>

typedef struct Aluno
{
int nota;
int falta;
char nome[51];
} Aluno;

void imprimir(Aluno v[], int n)
{
for(int i = 0; i < n; i++)
{
printf("%d %d %s", v[i].nota, v[i].falta, v[i].nome);
printf("\n");
}
}

void ordenar(Aluno v[], int n)
{
for(int i = 1; i < n; i++)
{
Aluno tmp = v[i];
int j = i - 1;
while( (j >= 0 ) && v[j].nota < tmp.nota || (v[j].nota == tmp.nota && v[j].falta > tmp.falta) )
{
v[j + 1] = v[j];
j--;
}
v[j+1] = tmp;
}
}

int main()
{
Aluno alunos[100];
int n = 0;
while(scanf("%d %d %[^\n]", &alunos[n].nota, &alunos[n].falta, alunos[n].nome) != EOF)
{
n++;
}
ordenar(alunos, n);
imprimir(alunos, n);
}