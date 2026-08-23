#include <stdio.h>

int main()
{
    char entrada[100];
    int controle = 0;
    //lembrar do \n no fgets
    while(fgets(entrada, sizeof(entrada), stdin) != NULL && !controle)
    {
        //parar quando a entrada for igual a FIM
        //é preciso conferir o caractere na posicao 3 para nao dar falso positivo
        if(entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M' //&& 
          //(entrada[3] == '\0' || entrada[3] == '\n')
        )
        { controle = 1; }
        else //fazer caso nao seja fim
        {
            int c = 0;
            //contar caracteres na string
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
}