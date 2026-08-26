#include <stdio.h>
//obs: funcoes verificadoras trabalham com operadores || e && para retornarem 0 ou 1

int vogal(int x, char s[])
{   //retorna se
    return (    //chegou no fim do vetor ou
                (s[x] == '\n') || 
                (
                //se for vogal E se 
                (s[x] == 'a' || s[x] == 'e' || s[x] == 'i' || s[x] == 'o' || s[x] == 'u')
                && 
                //segue pro resto do vetor (verificar se continua sendo vogal)
                vogal(x + 1, s))
            );
}

int consoante(int x, char s[])
{   //retorna se
    return (    //chegou no fim do vetor ou
                (s[x] == '\n') || 
                (
                //se for consoante (preciso checar se nao e' numero tambem) E se 
                ((s[x] != 'a' && s[x] != 'e' && s[x] != 'i' && s[x] != 'o' && s[x] != 'u'
                 && !(s[x] >= 48 && s[x] <= 57)))
                && 
                //segue pro resto do vetor (verificar se continua sendo consoante)
                consoante(x + 1, s))
            );
}

int inteiro(int x, char s[])
{   //retorna se
    return (    //chegou no fim do vetor ou
                (s[x] == '\n' || s[x] == '\0') || 
                (
                //se estiver no intervalo de numeros e nao tiver virgula ou ponto
                ((s[x] >= 48 && s[x] <= 57) && (s[x] != '.' && s[x] != ','))
                && 
                //segue pro resto do vetor
                inteiro(x + 1, s))
            );
}

int real(int x, int c, char s[])
{   //retorna se
    return (    //chegou no fim do vetor ou
                (s[x] == '\n' || s[x] == '\0') 
                || 
                //se estiver no intervalo de numeros segue pro resto do vetor
                ( (s[x] >= 48 && s[x] <= 57) && real(x + 1, c, s) )
                ||
                //se tiver , ou . aumentar contador e estava em zero (nao ha repeticao de , ou .), segue pro resto
                ( (s[x] == ',' || s[x] == '.') && c == 0 && real(x + 1, c + 1, s))
            );
}

int main()
{
    char entrada[300];
     while((fgets(entrada, sizeof(entrada), stdin) != NULL) && 
          (entrada[0] != 'F' || entrada[1] != 'I' || entrada[2] != 'M')) 
    {
        if(entrada[0] != 'F' && entrada[1] != 'I' && entrada[2] != 'M')
        {
            int c = 0;
            if(vogal(0, entrada) == 1){ printf("SIM "); } else { printf("NAO "); }
            if(consoante(0, entrada) == 1){ printf("SIM "); } else { printf("NAO "); }
            if(inteiro(0, entrada) == 1){ printf("SIM "); } else { printf("NAO "); }
            if(real(0, c, entrada) == 1){ printf("SIM"); } else { printf("NAO"); }
            printf("\n");
        }
    }
}