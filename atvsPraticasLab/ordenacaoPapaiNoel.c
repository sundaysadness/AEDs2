#include <stdio.h>
#include <string.h>

void ordenacao(char s[][50], int n)
{
    //ordenacao por insercao
    for (int i = 1; i < n; i++) 
    {
        char tmp[50];
        strcpy(tmp, s[i]);
        int j = i - 1;

        while ( (j >= 0) && (strcmp(s[j], tmp) >0) ) 
        {
            strcpy(s[j + 1], s[j]);
            j--;
        }
        strcpy(s[j+1], tmp);
   }
}

int main()
{
    int n;
    scanf("%d", &n);
    //consumir enter
    getchar();   
    char nomes[n][50];
    int mais = 0;
    int menos = 0;
    for(int i = 0; i < n; i++)
    {
        char s[50];
        //ler uma linha realizando leitura do teclado
        fgets(s, sizeof(s), stdin);
        //separar em duas strings
        char* sinal = strtok(s, " ");
        char* p = strtok(NULL, "\r\n");
        //contar + e -
        if(sinal[0] == '+')
        { mais++; }
        else
        { menos++; }
        //copiar segunda parte da string dividida (nomes)
        strcpy(nomes[i], p);
    }
    //ordenar
    ordenacao(nomes, n);
    //printar nomes
    for(int j = 0; j < n; j++)
    {
        printf("%s\n", nomes[j]);
    }
    //printar quantidade + e -
    printf("Se comportaram: %d | Nao se comportaram: %d\n", mais, menos);
}

