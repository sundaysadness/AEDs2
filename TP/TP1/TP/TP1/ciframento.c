#include <stdio.h>

void ciframento(int x, char s[])
{
    //caso base, funcao retorna quando cifrou todos os caracteres
    if(s[x] == '\n'){ return; }
    else
    {
        //troca o caractere por ele mais a chave 3
        s[x] = s[x] + 3;
        //chama funcao novamente para uma posicao a frente
        ciframento(x + 1, s);
    }
}

int main()
{
    char entrada[300];
          //lembrar de tirar o \n
    while((fgets(entrada, sizeof(entrada), stdin) != NULL) && 
          (entrada[0] != 'F' || entrada[1] != 'I' || entrada[2] != 'M')) 
    {
        if(entrada[0] != 'F' && entrada[1] != 'I' && entrada[2] != 'M')
        {
            //chamar a funcao para comecar a alterar da primeira posicao
            ciframento(0, entrada);
            printf("%s", entrada);
        }
    }
}