#include <stdio.h>
#include <stdbool.h>
#include <string.h>

int main()
{
    char entrada[100];
    bool t = true;

    while(t && fgets(entrada, sizeof(entrada), stdin) != NULL)
    {
        if(entrada[0] == 'F' && entrada[1] == 'I' && entrada[2] == 'M')
        { t = false; }
        else
        {
            int c = 0;
            int tamanho = strlen(entrada);
            for(int x = 0; x < tamanho; x++)
            {
                if(entrada[x] >= 'A' && entrada[x] <= 'Z')
                {
                    c++;
                }
            }
            printf("%d\n", c);
        }
    }
}