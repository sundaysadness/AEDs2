#include <stdio.h>

int main()
{
    char entrada[100];
    while((fgets(entrada, sizeof(entrada), stdin) != NULL) && 
          (entrada[0] != 'F' || entrada[1] != 'I' || entrada[2] != 'M')) 
    {
        int c = 0;
        //contar total de caracteres na string
        for(int i = 0; entrada[i] != '\0'; i++)
        {
            c++;
        }
        //comecar printando pelo final, excluindo o \n, ate o inicio
        for(int j = c-2; j >= 0; j--)
        {
            printf("%c", entrada[j]);
        }
        printf("\n");
    }    
}