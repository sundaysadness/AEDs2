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

// <<struct>> Celula
typedef
struct Celula
{
    Veiculo veiculo;
    struct Celula *prox;
}
Celula;

//ponteiros globais
Celula *primeiro, *ultimo;

//inicializar celula
Celula* novaCelula(Veiculo v)
{
    Celula *nova = (Celula*) malloc(sizeof(Celula)); //!!
    nova->veiculo = v;
    nova->prox = NULL;
    return nova;
}

//inicializar lista
void start()
{
    //celula cabeca
    Veiculo vazio = {0}; 
    primeiro = novaCelula(vazio); 
    ultimo = primeiro;
}

//funcao auxiliar para inserir e remover
int tamanho() 
{
    int tam = 0;
    for(Celula* i = primeiro->prox; i != NULL; i = i->prox) 
    {
        tam++;
    }
    return tam;
}

void inserirInicio(Veiculo v) 
{
    Celula *tmp = novaCelula(v);
    tmp->prox = primeiro->prox;
    primeiro->prox = tmp;
    if(primeiro == ultimo) 
    {
        ultimo = tmp;
    }
    tmp = NULL;
}

void inserirFim(Veiculo v) 
{
    ultimo->prox = novaCelula(v);
    ultimo = ultimo->prox;
}

void inserir(Veiculo v, int pos) 
{
    int tam = tamanho();
    if(pos < 0 || pos > tam) 
    {
        printf("Erro!\n");
    } else if(pos == 0) 
    {
        inserirInicio(v);
    } else if(pos == tam) 
    {
        inserirFim(v);
    } else {
        Celula *i = primeiro;
        for(int j = 0; j < pos; j++, i = i->prox);
        Celula *tmp = novaCelula(v);
        tmp->prox = i->prox;
        i->prox = tmp;
        tmp = i = NULL;
    }
}

Veiculo removerInicio() 
{
    if(primeiro == ultimo) 
    {
        printf("Erro!");
    }
    Celula *tmp = primeiro;
    primeiro = primeiro->prox;
    Veiculo v = primeiro->veiculo;
    tmp->prox = NULL;
    free(tmp);
    tmp = NULL;
    return v;
}

Veiculo removerFim() 
{
    if(primeiro == ultimo)
    {
        printf("Erro!");
    }
    Celula *i;
    for (i = primeiro; i->prox != ultimo; i = i->prox);
    Veiculo v = ultimo->veiculo;
    ultimo = i;
    free(ultimo->prox);
    i = ultimo->prox = NULL;
    return v;
}

Veiculo remover(int pos) 
{
    Veiculo v;
    int tam = tamanho();
    if(primeiro == ultimo) 
    {
        printf("Erro!");
    } else if(pos < 0 || pos >= tam) 
    {
        printf("Erro!");
    } else if(pos == 0) 
    {
        v = removerInicio();
    } else if(pos == tam - 1) 
    {
        v = removerFim();
    } else 
    {
        Celula *i = primeiro;
        for (int j = 0; j < pos; j++, i = i->prox);
        Celula *tmp = i->prox;
        v = tmp->veiculo;
        i->prox = tmp->prox;
        tmp->prox = NULL;
        free(tmp);
        i = tmp = NULL;
    }
    return v;
}

void mostrar() 
{
    for(Celula *i = primeiro->prox; i != NULL; i = i->prox) 
    {
        char buffer[300];
        void formatVeiculo(Veiculo v, char* buffer);
        formatVeiculo(i->veiculo, buffer);
        printf("%s\n", buffer);
    }
}

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

//funcao de auxilio para pesquisar id
Veiculo pesquisaId(Veiculo *v, int n, int id)
{
    for(int i = 0; i < n; i++)
    {
        if(v[i].id == id)
        {
            return v[i];
        }
    }
    Veiculo vazio = {0}; 
    return vazio;
} //end pesquisaId


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

    //inicializar lista
    start();

    int id;
    //enquanto nao chegar no final do arquivo com -1
    while(scanf("%d", &id) == 1 && id != -1)
    {
        Veiculo v = pesquisaId(veiculos, totalVeiculos, id);
        inserirFim(v);
    }

    int N;
    scanf("%d", &N);

    for(int i = 0; i < N; i++)
    {
        char indice[5];
        scanf("%s", indice);
        if(strcmp(indice, "I*") == 0)
        {
            int pos, id2;
            scanf("%d %d", &pos, &id2);
            inserir(pesquisaId(veiculos, totalVeiculos, id2), pos);
        }
        else
        {
            if(strcmp(indice, "II") == 0)
            {
                int id2;
                scanf("%d", &id2);
                inserirInicio(pesquisaId(veiculos, totalVeiculos, id2));
            }
            else
            {
                if(strcmp(indice, "IF") == 0)
                {
                    int id2;
                    scanf("%d", &id2);
                    inserirFim(pesquisaId(veiculos, totalVeiculos, id2));
                }
                else
                {
                    if(strcmp(indice, "R*") == 0)
                    {
                        int pos;
                        scanf("%d", &pos);
                        Veiculo v = remover(pos);
                        printf("(R)%s %s\n", v.marca, v.modelo);
                    }
                    else
                    {
                        if(strcmp(indice, "RI") == 0)
                        {
                            Veiculo v = removerInicio();
                            printf("(R)%s %s\n", v.marca, v.modelo);
                        }
                        else
                        {
                            if(strcmp(indice, "RF") == 0)
                            {
                                Veiculo v = removerFim();
                                printf("(R)%s %s\n", v.marca, v.modelo);
                            }
                        }
                    }
                }
            }
        }
    }

    mostrar();
} //end main