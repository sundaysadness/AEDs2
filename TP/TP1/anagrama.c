#include <stdio.h>

//funcao utilizada para nao dar erro em casos de letra maiuscula e letra minusculas serem iguais
char conversor(char c)
{
    //se houver maiuscula
    if(c >= 'A' && c <= 'Z')
    {
        //deixar minuscula
        c = c + 32; 
    }
    //retornar caractere minusculo
    return c;
}

int main()
{
    char s1[10];
    char s2[10];
    //foi usado || para nao conflitar com palavras que tem algum desses caracteres, mas nao sao FIM
    while((scanf("%s", s1) == 1) && (s1[0] != 'F' || s1[1] != 'I' || s1[2] != 'M'))
    {
        //se a primeira string for diferente de FIM
        if(s1[0] != 'F' || s1[1] != 'I' || s1[2] != 'M')
        {
            //ler a segunda e continuar o programa
            scanf("%s", s2);
            int t = 0;
            //contar total de caracteres na string
            for(int i = 0; s1[i] != '\0'; i++)
            {
                t++;
            }
            //chamar conversor de string
            for(int j = 0; j < t; j++)
            {
                //analisar caractere por caractere
                s1[j] = conversor(s1[j]);
                s2[j] = conversor(s2[j]);
            }
            int c1 = 0;
            //caractere da primeira string temporariamente fixo
            for(int x = 0; x < t; x++)
            {
                //resetar controle para nao repetir contagem de caracteres iguais
                int c2 = 0;
                //percorre a segunda string enquanto nao achar pelo menos um caractere igual
                for(int y = 0; y < t && (c2 == 0); y++)
                {
                    //se tiver pelo menos um caractere igual
                    if(s1[x] == s2[y])
                    {
                        //aumenta c1
                        c1 = c1 + 1;
                        //substituir caractere naquela posicao para nao dar falso positivo em casos de repeticao
                        s2[y] = '0';
                        //controle para indicar que ja achou uma letra equivalente a aquele caractere (nao contar duas vezes)
                        c2 = 1;
                    }
                }
            }
            //se a contagem total de caracteres iguais for igual tamanho
            if(c1 == t)
            { printf("SIM\n"); }
            else
            { printf("NAO\n"); }
        }
    }
}