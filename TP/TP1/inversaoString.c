#include <stdio.h>

int main()
{
    char entrada[100];
    //alteracao na condicao do while, para evitar falso positivo com palvras FIM....
    while( (fgets(entrada, sizeof(entrada), stdin) != NULL) && 
          !(entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M' && 
           (entrada[3] == '\n' || entrada[3] == '\0')) ) 
    {
        int c = 0;
        //contar total de caracteres na string
        for(int i = 0; entrada[i] != '\0'; i++)
        {
            c++;
        }
        //retirar \n SE HOUVER
        if(entrada[c-1] == '\n')
        {
            c--;
        }
        //comecar printando pelo final, excluindo o \0, ate o inicio
        for(int j = c-1; j >= 0; j--)
        {
            printf("%c", entrada[j]);
        }
        printf("\n");
    }    
}