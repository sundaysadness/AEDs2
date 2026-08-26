#include <stdio.h>

int main()
{
    char s1[15];

    while((scanf("%s", s1) == 1) && (s1[0] != 'F' || s1[1] != 'I' || s1[2] != 'M'))
    {
        if(s1[0] != 'F' || s1[1] != 'I' || s1[2] != 'M')
        {
            int c = 0;
            int t = 0;
            //calcular tamanho total da string
            for(int i = 0; s1[i] != '\0'; i++)
            {
                t++;
            }
            //passar pela string toda
            for(int x = 0; x < t; x++)
            {
                //reinicia
                int repeticao = 0; //comeca false
                //comecar analise depois do que ja foi analisado e parar quando houver repeticao 
                for(int y = x; y < t && !repeticao; y++)
                {
                    //comparar o caracterer temporariamente fixo com os que ja passaram
                    for(int z = x; z < y && !repeticao; z++)
                    {
                        //se houver caractere igual
                        if(s1[z] == s1[y])
                        {
                            //repeticao fica true
                            repeticao = 1;
                        }
                    }
                    //se nao encontrou repeticao
                    if(!repeticao)
                    {
                        //tamanho atual da substring
                        int ts = y - x + 1;
                        //conferir se e´maior que algum tamanho anterior calculado 
                        if(ts > c)
                        {
                            c = ts;
                        }
                    }
                }
            }
            printf("%d\n", c);
        }
    }
}