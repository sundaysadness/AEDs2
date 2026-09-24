#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>

// <<struct>> Data
typedef
struct Data
{
    int ano;
    int mes;
    int dia;
}
Data;

// <<struct>> Veiculo
typedef 
struct Veiculo
{
    int    id;
    char   marca[40];
    char   modelo[40];
    int    ano;
    char   categoria[40];
    char   combustivel[2][20];
    int    cilindros;
    double cilindrada;
    char   transmissao[40];
    char   tracao[40];
    double consumoCidade;
    double consumoEstrada;
    double co2;
    bool   turbo;
    Data   dataRegistro;
}
Veiculo;

// <<functions>> DataFunctions
Data parseData(char* s)
{
    Data d;
    int ano, mes, dia;
    //sscanf para extrair e formatar a data da string
    sscanf(s, "%d-%d-%d", &ano, &mes, &dia);
    d.dia = dia;
    d.mes = mes;
    d.ano = ano;
    return (d);
} //end parseData

void formatData(Data d, char* buffer)
{
    //sprintf formata o texto e guarda no buffer
    sprintf(buffer, "%02d/%02d/%04d", d.dia, d.mes, d.ano);
} //end formatData

// <<functions>> VeiculoFunctions
Veiculo* parseVeiculo(char* s)
{
    //malloc devolve ponteiro, por isso o * em Veiculo
    Veiculo* v = malloc(sizeof(Veiculo));
    //strtok percorre s ate encontrar a primeira virgula, p guarda a posicao do pedaco "retirado"
    char* p = strtok(s, ",");
    //usado -> por ser ponteiro para struct
    //atoi para fazer conversao para o tipo respectivo
    v->id = atoi(p);
    p = strtok(NULL, ",");
    //strcpy usado para tipos string
    strcpy(v->marca, p);
    p = strtok(NULL, ",");
    strcpy(v->modelo, p);
    p = strtok(NULL, ",");
    v->ano = atoi(p);
    p = strtok(NULL, ",");
    strcpy(v->categoria, p);
    //lidar com combustivel sendo multivalorado
    p = strtok(NULL, ",");
    //cria um ponteiro que procura o ; no pedaco da string pra reposicionar com ;
    char* delimitador = strchr(p, ';');
    //se achou ; (entao tem 2 combustiveis)
    if(delimitador != NULL) 
    { 
        //posicionar \0 para quebrar essa parte da string em 2
        *delimitador = '\0';
        //copia a string pro array combustivel
        strcpy(v->combustivel[0], p);
        //resto da string quebrada esta logo depois do delimitador, entao somar 1 leva pra ela
        strcpy(v->combustivel[1], delimitador+1);
    }
    else
    {
        //caso que so existe 1 combustivel
        strcpy(v->combustivel[0], p);
        //caso haja apenas 1 combustivel, limpar lixo de memoria deixado no segundo slot
        v->combustivel[1][0] = '\0'; 
    }

    p = strtok(NULL, ",");
    v->cilindros = atoi(p);
    p = strtok(NULL, ",");
    v->cilindrada = atof(p);
    p = strtok(NULL, ",");
    strcpy(v->transmissao, p);
    p = strtok(NULL, ",");
    strcpy(v->tracao, p);
    p = strtok(NULL, ",");
    v->consumoCidade = atof(p);
    p = strtok(NULL, ",");
    v->consumoEstrada = atof(p);
    p = strtok(NULL, ",");
    v->co2 = atof(p);
    p = strtok(NULL, ",");
    //strcmp: se as duas strings forem iguais retorna 0
    //0 == 0? fica true, retorna 1
    v->turbo = (strcmp(p, "true") == 0);
    p = strtok(NULL, ",");
    v->dataRegistro = parseData(p);

    return v;
} //end parseVeiculos

//funcao para formatar o combustivel, colocar "," no lugar de ";"
void formatCombustivel(Veiculo v, char* c)
{
    //pega/copia o primeiro combustivel
    strcpy(c, v.combustivel[0]);
    //se houver outro combustivel (segunda linha != de \0), vai juntar com o primeiro
    if(v.combustivel[1][0] != '\0')
    {
        //concate com o que ja existia 
        strcat(c, ",");
        strcat(c, v.combustivel[1]);
    }
} //end formatCombustivel

void formatVeiculo(Veiculo v, char* buffer)
{
    //buffer temporario
    char dataFormatada[15];
    //passar dataRegistro e buffer para formatacao de datas
    formatData(v.dataRegistro, dataFormatada);
    //outro buffer para receber o combustivel formatado com ,
    char combustivelFormatado[50]; 
    //passar v e buffer para formatacao do combustivel
    formatCombustivel(v, combustivelFormatado);
    //%s para tipo bool, uso do operador ternário "?" para, se v.turbo for 1, escrever true, caso contrario, false
    sprintf(buffer, "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %s ## %s]", 
            v.id, v.marca, v.modelo, v.ano, v.categoria, combustivelFormatado, v.cilindros, 
            v.cilindrada, v.transmissao, v.tracao, v.consumoCidade, v.consumoEstrada, v.co2, 
            v.turbo ? "true" : "false", dataFormatada);
} //end formatVeiculo

// <<functions>> LeitorCsvFunctions
Veiculo* lerCsv(char* arquivo, int* n)
{
    //variavel para contagem
    int c = 0;
    //ler o arquivo uma primeira vez para contar quantidade de linhas
    //abrir arquivo
    FILE *arqContagem = fopen(arquivo, "r");
    char linha[300];
    //desconsiderar primeira linha (cabecalho)
    fgets(linha, sizeof(linha), arqContagem); 
    //ler enquanto tiver linha
    while((fgets(linha, sizeof(linha), arqContagem) != NULL))
    { 
        c++; 
    }
    //fechar arquivo
    fclose(arqContagem);
    //atualizar n
    *n = c;

    int i = 0;
    //reabrindo a leitura do arquivo de seu comeco
    FILE *arqLeitura = fopen(arquivo, "r");
    //criar array com quantidade correta de veiculos
    Veiculo* veiculos = malloc(c * sizeof(Veiculo));
    //desconsiderar primeira linha (cabecalho)
    fgets(linha, sizeof(linha), arqLeitura); 
    //ler linha do arquivo
    while((fgets(linha, sizeof(linha), arqLeitura) != NULL))
    {
        //passar para parseVeiculo colocando em cada posicao do vetor veiculos 
        veiculos[i] = *parseVeiculo(linha);
        i++;
    }
    //fechar arquivo
    fclose(arqLeitura);
    
    //retornar array
    return veiculos;
} //end lerCsv

//funcao para encontrar o maior valor no array, auxilio para counting sort
int getMaior(Veiculo* v, int n)
{
    int maior = v[0].cilindros;
    for(int i = 1; i < n; i++)
    {
        if(v[i].cilindros > maior)
        {
            maior = v[i].cilindros;
        }
    }
    return maior;
} //end getMaior

//ordenacao por Counting Sort
void ordenacaoCilindros(Veiculo* array, int n)
{
    //array para contar o numero de ocorrencia de cada elemento (cada cilindro)
    //+1 garante que haja espaco suficiente para o maior valor (levando em conta que index comeca em 0)
    int tamCount = getMaior(array, n) + 1;
    int count[tamCount];
    Veiculo* ordenado = malloc(n * sizeof(Veiculo));
    //incializar cada posicao do array de contagem com zero
    for(int i = 0; i < tamCount; count[i] = 0, i++);
    //contagem de elementos em count
    //array[i].cilindros pega o elemento (numero de cilindros) na posicao i
    //cont[...]++ usa array[i] como indice para incrementar a posicao
    for(int i = 0; i < n; count[array[i].cilindros]++, i++);
    //descobrir qual posicao do array final cada elemento deve ser colocado
    //diz quantos elementos menores ou iguais existem do incio do array ate aquela posicao analisada
    for(int i = 1; i < tamCount; count[i] = count[i] + count[i-1], i++);
    //ordenando usando os valores acumulados no array de contagem 
    for(int i = n-1; i >= 0; i--)
    {
        //numero de cilindros 
        int cilindros = array[i].cilindros;
        //calcula posicao no arrray ordenado
        int posicao = count[cilindros] - 1;
        //coloca o veiculo na posicao correta
        ordenado[posicao] = array[i];
        //decrementa contagem p/ o proximo
        count[cilindros]--;
    }  
    //copiar para o array original
    for(int i = 0; i < n; array[i] = ordenado[i], i++);
} //end ordenacaoCilindros 

int main()
{
    char* caminho;
    //tentara abrir no caminho linux
    FILE* teste = fopen("/tmp/veiculos.csv", "r");
    //se funcionar
    if(teste != NULL)
    {
        fclose(teste);
        //caminho linux
        caminho = "/tmp/veiculos.csv";
    }
    else
    {
        //se falhar, usar o caminho windows
        caminho = "veiculos.csv";
    }

    int totalVeiculos = 0;
    Veiculo* veiculos = lerCsv(caminho, &totalVeiculos);
    //array para guardar veiculos selecionados
    Veiculo* selecionados = malloc(totalVeiculos * sizeof(Veiculo));
    //tamanho do array preenchido depois de ler os ids
    int n = 0 ;

    int id;
    //enquanto nao chegar no final do arquivo com -1
    while(scanf("%d", &id) == 1 && id != -1)
    {
        bool controle = false;
        for(int i = 0; i < totalVeiculos && !controle; i++) 
        {
            if(veiculos[i].id == id) 
            {
                //se for o veiculo com o id procurado, guardar em selecionados
                selecionados[n] = veiculos[i];
                n++;
                controle = true;
            }
        }
    }

    ordenacaoCilindros(selecionados, n);

    for(int i = 0; i < n; i++) 
    {
        char buffer[300];
        formatVeiculo(selecionados[i], buffer);
        printf("%s\n", buffer);
    }

} //end main