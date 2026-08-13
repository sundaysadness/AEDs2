#include <stdio.h>
#include <string.h>

int main()
{
    char x[100];
    char y[100]; 

    int tamanho1 = 0;
    int tamanho2 = 0;
    int diferenca = 0;

    while(scanf("%s %s", x, y) == 2)
    {
        tamanho1 = strlen(x);
        tamanho2 = strlen(y);

        if(tamanho1 == tamanho2)
        {
            for(int a = 0; a < tamanho1; a++)
            {
                printf("%c", x[a]);
                printf("%c", y[a]);
            }
            printf("\n"); 
        }
        else
        {
            if(tamanho1 < tamanho2)
            {
                diferenca = tamanho2 - tamanho1;
                for(int b = 0; b < tamanho1; b++)
                {
                    printf("%c", x[b]);
                    printf("%c", y[b]);
                }
                while(diferenca > 0)
                {
                    printf("%c", y[tamanho1++]);
                    diferenca--;
                }
                printf("\n"); 
            }
            else //tamanho1 > tamanho2
            {
                diferenca = tamanho1 - tamanho2;
                for(int d = 0; d < tamanho2; d++)
                {
                    printf("%c", x[d]);
                    printf("%c", y[d]);
                }
                while(diferenca > 0)
                {
                    printf("%c", x[tamanho2++]);
                    diferenca--;
                }
                printf("\n"); 
            }
        }
    }
}