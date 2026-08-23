#include <stdio.h>

int main()
{
    char entrada[100];
    int controle = 0;
    do
    {
        //lembrar do \n no fgets
        fgets(entrada, sizeof(entrada), stdin);
        //evitar que faca pra FIM
        if(entrada[0] != 'F' && entrada[1] != 'I' && entrada[2] != 'M')
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
     //parar quando a entrada for igual a FIM
    }while(entrada[0] != 'F' && entrada[1] != 'I' && entrada[2] != 'M');
}