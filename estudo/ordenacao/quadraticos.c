#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

void swap(int *a, int *b)
{
    int tmp = a;
    a = b;
    b = tmp;
}

//Selection sort (não é estável)
void selecao(int *array, int n)
{
    for(int i = 0; i < (n - 1); i++) 
    {
        int menor = i;
        for(int j = (i + 1); j < n; j++)
        {
            if(array[menor] > array[j])
            {
                menor = j;
            }
        }
        swap(&array[menor], &array[i]);
    }
}

//Insertion sort
void insercao(int *array, int n)
{
    for(int i = 1; i < n; i++) 
    {
        int tmp = array[i];
        int j = i - 1;
        while( (j >= 0) && (array[j] > tmp) ) 
        {
            array[j + 1] = array[j];
            j--;
        }
        array[j+1] = tmp;
    }
}

//Bubble sort
void bolha(int *array, int n)
{
    int i, j;
    for(i = 0; i < (n-1); i++) 
    {
        for(j = 0; j < (n-1); j++) 
        {
            if(array[j] > array[j + 1]) 
            {
                swap(&array[j], &array[j + 1]);
            }
        }
    }
}

//main 
int main()
{
    int n;
    double x;
    char c;
    char palavra[100];

    scanf("%d", &n);          // inteiro
    scanf("%lf", &x);         // double (%f é para float)
    scanf(" %c", &c);         // espaço antes do %c ignora \n pendente
    scanf("%s", palavra);     // lê até espaço/quebra de linha (sem &)

    while (scanf("%d", &n) == 1) { /* processa */ }

    char linha[256];
    fgets(linha, sizeof(linha), stdin);
    
    char palavra[200];
    for(int i = 0; i < sizeof(palavra); i++)
    {
        if(palavra[i] == '/n' || palavra[i] == '/0' )
        {
            palavra[i] == '/0';
            break;
        }
    }

    /*
    strcmp (comparar strings)
    int strcmp(const char *a, const char *b);

    Funcionamento: compara caractere a caractere (valor ASCII) até achar diferença ou chegar no \0.
    Retorno: 0 se iguais, negativo se a < b, positivo se a > b.
    Cuidados:
    Não compare com ==: isso compara endereços, não conteúdo.
    O retorno não é necessariamente -1 ou 1, então teste com < 0, > 0, == 0.
    if (strcmp(a, b)) é verdadeiro quando as strings são diferentes, o que confunde muita gente.
    Diferencia maiúsculas de minúsculas. Para ignorar, use strcasecmp (<strings.h>, não é padrão ISO) ou converta antes com tolower.
    strncmp(a, b, n) compara só os n primeiros caracteres.
    */

    /*
    strlen (tamanho)
    size_t strlen(const char *s);
    Conta caracteres até o \0, sem contá-lo.
    Cuidado: o retorno é size_t (sem sinal). Se s é vazia, strlen(s) - 1 vira um número enorme, não -1. Isso quebra laços como for (int i = 0; i < strlen(s) - 1; i++).
    É O(n): em laços, calcule uma vez fora (int len = strlen(s);) em vez de chamar a cada iteração.
    */

    /*
    strcpy e strncpy (copiar)
    char *strcpy(char *destino, const char *origem);
    char *strncpy(char *destino, const char *origem, size_t n);
    Cuidados:
    strcpy não verifica tamanho: se origem não cabe em destino, há estouro de buffer.
    strncpy não coloca \0 se origem tiver n ou mais caracteres. Garanta manualmente: destino[n-1] = '\0';
    Não faça destino = origem com vetores: não copia texto.
    */

    /*
    strcat (concatenar)
    char *strcat(char *destino, const char *origem);
    Acrescenta origem ao fim de destino.
    Cuidado: destino precisa ter espaço para os dois textos + \0 e já ter sido inicializado com uma string válida (char s[100] = "";).
    */

    /*
    strtok (dividir por delimitador, o "split" do C)
    char *strtok(char *s, const char *delimitadores);
    c
    char linha[] = "ana,bia,carlos";
    char *tok = strtok(linha, ",");        // 1ª chamada: passa a string
    while (tok != NULL) {
        printf("%s\n", tok);
        tok = strtok(NULL, ",");           // próximas: passa NULL
    }
    Funcionamento: troca cada delimitador por \0 dentro da própria string e guarda internamente onde parou.
    Cuidados:
    Modifica a string original. Se precisar dela intacta, copie antes.
    Não funciona com literais (char *s = "a,b"), pois são somente leitura. Use vetor (char s[] = "a,b").
    Delimitadores consecutivos são tratados como um só: "a,,b" dá "a" e "b" (o campo vazio some).
    Guarda estado interno: não dá para dividir duas strings intercaladas.
    */

    /*
    Conversão: atoi, strtol, sscanf
    int atoi(const char *s);
    long strtol(const char *s, char **fim, int base);
    int sscanf(const char *s, const char *formato, ...);
    atoi("123") retorna 123; para texto inválido retorna 0, sem como distinguir de um "0" legítimo.
    strtol permite checar erro via fim e aceita outras bases (2, 8, 16).
    sscanf funciona como scanf, mas lendo de uma string: sscanf(linha, "%d %d", &a, &b);. Retorna quantos itens leu, então confira o retorno.
    Para o caminho inverso, sprintf(buf, "%d", n) (ou snprintf, mais seguro, pois limita o tamanho).
    */

}