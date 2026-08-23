#include <stdio.h>

int somaDigitos(int n)
{
    int soma = 0;
    //caso base, acontece quando todos os digitos do numero original foram separados e somados
    if(n == 0){ soma = 0; }
    else
    {
        //isola digito por digito
        soma = n % 10;
        //soma o digito e pega o proximo, chamando a funcao pro numero original / 10
        //o que retira o digito que ja foi somado para efetuar a proxima chamada 
        soma = soma + somaDigitos(n / 10);
    }
    return soma;
}

int main()
{
    int n;
    //enquanto scanf fizer leitura
    while(scanf("%d", &n) == 1)
    {
        printf("%d\n", somaDigitos(n));
    }
}