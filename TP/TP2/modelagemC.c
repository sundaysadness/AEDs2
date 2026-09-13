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
    int id;
    char marca[40];
    char modelo[40];
    int ano;
    char categoria[40];
    char combustivel[2][20];
    int cilindros;
    double cilindrada;
    char transmissao[40];
    char tracao[40];
    double consumoCidade;
    double consumoEstrada;
    double co2;
    bool turbo;
    Data dataRegistro;
}
Veiculo;

// <<functions>> DataFunctions
Data parseData(char* s)
{

} //end parseData

void formatData(Data d, char* buffer)
{

} //end formatData

// <<functions>> VeiculoFunctions
Veiculo* parseVeiculo(char* s)
{

} //end parseVeiculos

void formatVeiculo(Veiculo v, char* buffer)
{

} //end formatVeiculo

// <<functions>> LeitorCsvFunctions
Veiculo* lerCsv(char* arquivo, int* n)
{

} //end lerCsv

int main()
{

} //end main