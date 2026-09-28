#include <stdio.h>

void swap(int *a, int *b)
{
    int tmp = a;
    a = b;
    b = tmp;
}

//Selection sort (não é estável)
void selecao(int *array, int n)
{
    for(int i = 0; i < (n - 1); i++) 
    {
        int menor = i;
        for(int j = (i + 1); j < n; j++)
        {
            if(array[menor] > array[j])
            {
                menor = j;
            }
        }
        swap(&array[menor], &array[i]);
    }
}

//Insertion sort
void insercao(int *array, int n)
{
    for(int i = 1; i < n; i++) 
    {
        int tmp = array[i];
        int j = i - 1;
        while( (j >= 0) && (array[j] > tmp) ) 
        {
            array[j + 1] = array[j];
            j--;
        }
        array[j+1] = tmp;
    }
}

//Bubble sort
void bolha(int *array, int n)
{
    int i, j;
    for(i = 0; i < (n-1); i++) 
    {
        for(j = 0; j < (n-1); j++) 
        {
            if(array[j] > array[j + 1]) 
            {
                swap(&array[j], &array[j + 1]);
            }
        }
    }
}

//main 
int main()
{
    int n;
    double x;
    char c;
    char palavra[100];

    scanf("%d", &n);          // inteiro
    scanf("%lf", &x);         // double (%f é para float)
    scanf(" %c", &c);         // espaço antes do %c ignora \n pendente
    scanf("%s", palavra);     // lê até espaço/quebra de linha (sem &)

    while (scanf("%d", &n) == 1) { /* processa */ }

    char linha[256];
    fgets(linha, sizeof(linha), stdin);
    
    char palavra[200];
    for(int i = 0; i < sizeof(palavra); i++)
    {
        if(palavra[i] == '/n' || palavra[i] == '/0' )
        {
            palavra[i] == '/0';
            break;
        }
    }
}