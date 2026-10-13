import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Locale;

class Veiculo
{
    //atributos
    private int      id;
    private String   marca;
    private String   modelo; 
    private int      ano;
    private String   categoria;
    private String[] combustivel;
    private int      cilindros;
    private double   cilindrada;
    private String   transmissao;
    private String   tracao;
    private double   consumoCidade;
    private double   consumoEstrada;
    private double   co2;
    private boolean  turbo;
    private Data     dataRegistro;

    //construtores
    public Veiculo(int id, String marca, String modelo, int ano, String categoria, String[] combustivel,
            int cilindros, double cilindrada, String transmissao, String tracao, double consumoCidade,
            double consumoEstrada, double co2, boolean turbo, Data dataRegistro)
    {
        this.id             = id;
        this.marca          = marca;
        this.modelo         = modelo;
        this.ano            = ano;
        this.categoria      = categoria;
        this.combustivel    = combustivel;
        this.cilindros      = cilindros;
        this.cilindrada     = cilindrada;
        this.transmissao    = transmissao;
        this.tracao         = tracao;
        this.consumoCidade  = consumoCidade;
        this.consumoEstrada = consumoEstrada;
        this.co2            = co2;
        this.turbo          = turbo;
        this.dataRegistro   = dataRegistro;
    } //end construtor

    //metodos get
    int      getId()             { return id; }
    String   getMarca()          { return marca; }
    String   getModelo()         { return modelo; }
    int      getAno()            { return ano; }
    String   getCategoria()      { return categoria; }
    String[] getCombustivel()    { return combustivel; }
    int      getCilindros()      { return cilindros; }
    double   getCilindrada()     { return cilindrada; }
    String   getTransmissao()    { return transmissao; }
    String   getTracao()         { return tracao; }
    double   getConsumoCidade()  { return consumoCidade; }
    double   getConsumoEstrada() { return consumoEstrada; }
    double   getCo2()            { return co2; }
    boolean  getTurbo()          { return turbo; }
    Data     getDataRegistro()   { return dataRegistro; }

    //metodo parse
    //faz a conversao dos atributos para seus respectivos tipo, pois sao todos recebidos como string
    public static Veiculo parseVeiculo(String s)
    {
        //quebra em pedacos, usando "," como parametro para dividir
        String[] pedacos = s.split(",");
        //pega cada pedaco e transforma no seu respectivo tipo 
        int      id                = Integer.parseInt(pedacos[0]);
        String   marca             = pedacos[1];
        String   modelo            = pedacos[2];
        int      ano               = Integer.parseInt(pedacos[3]);
        String   categoria         = pedacos[4];
        //split pra combustivel, cada posicao com os elementos que antes estavam separados por ";"
        String[] combustivel       = pedacos[5].split(";");
        int      cilindros         = Integer.parseInt(pedacos[6]);
        double   cilindrada        = Double.parseDouble(pedacos[7]);
        String   transmissao       = pedacos[8];
        String   tracao            = pedacos[9];
        double   consumoCidade     = Double.parseDouble(pedacos[10]);
        double   consumoEstrada    = Double.parseDouble(pedacos[11]);
        double   co2               = Double.parseDouble(pedacos[12]);
        boolean  turbo             = Boolean.parseBoolean(pedacos[13]);
        Data     dataRegistro      = Data.parseData(pedacos[14]);
        //retorna o veiculo
        return (new Veiculo(id, marca, modelo, ano, categoria, combustivel, cilindros, 
                            cilindrada, transmissao, tracao, consumoCidade, consumoEstrada, co2, 
                            turbo, dataRegistro));
    } //end parseVeiculo

    //metodo auxiliar para formatar o array combustivel, separando os elementos por virgula
    private String formatCombustivel()
    {
        String combustivelString = "";
        for(int i = 0; i < combustivel.length; i++) 
        {
            //fazer ate o penultimo elemento
            if(i < combustivel.length - 1)
            {
                //adicionar virgula depois de cada elemento
                combustivelString = combustivelString + combustivel[i] + ",";
            }
            else
            {
                //nao colocar virgula no final
                combustivelString = combustivelString + combustivel[i];
            }
        }
        return combustivelString;
    } //end formatCombustivel

    //metodo forma
    String format()
    {
        //chamar o formatador de combustivel, para sair no modelo requisitado 
        String combustivelString = formatCombustivel();
        //imprimir informacoes de veiculo
        return String.format(Locale.US, "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %b ## %s]",
                                id, marca, modelo, ano, categoria, combustivelString, cilindros, 
                                cilindrada, transmissao, tracao, consumoCidade, consumoEstrada, co2, 
                                turbo, dataRegistro.format());
    } //end format
} //end Veiculo

class Data
{
    //atributos
    private int dia;
    private int mes;
    private int ano; 

    //construtores
    Data(int dia, int mes, int ano)
    {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    } //end construtor

    //metodos get
    int getDia() { return dia; }
    int getMes() { return mes; }
    int getAno() { return ano; }

    //metodo parse
    //conversao de string para os tipos respectivos
    public static Data parseData(String s) 
    { 
        //quebra em tres pedacos, usando "-" como parametro para dividir
        String[] pedacos = s.split("-");
        //pega cada pedaco e transforma em inteiro
        int ano = Integer.parseInt(pedacos[0]);
        int mes = Integer.parseInt(pedacos[1]);
        int dia = Integer.parseInt(pedacos[2]);
        //cria objeto Data e retorna
        return (new Data(dia, mes, ano));
    } //end parseData

    //metodo forma
    String format()
    {
        //String.format sendo usado para fazer a data sair na formatacao certa, intercalado por "/"
        //%02d -> garante 2 digitos com zero a esquerda, caso precise 
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    } //end format
} //end Data

class LeitorCsv
{
    //le o arquivo veiculos.csv e guarda todos os veiculos em um array
    public static Veiculo[] ler(String arquivo) throws FileNotFoundException
    {
        //variavel para contagem
        int c = 0;
        //ler o arquivo uma primeira vez para contar quantidade de linhas
        Scanner leitorContagem = new Scanner(new File(arquivo));
        //desconsiderar primeira linha (cabecalho)
        leitorContagem.nextLine(); 
        //ler enquanto tiver linha
        while(leitorContagem.hasNextLine())
        {
            leitorContagem.nextLine(); 
            c++; 
        }
        //fechar leitor
        leitorContagem.close();

        int i = 0;
        //declarar outro Scanner, reabrindo a leitura do arquivo de seu comeco
        Scanner leitorVeiculos = new Scanner(new File(arquivo));
        //criar array com quantidade correta de veiculos
        Veiculo[] veiculos = new Veiculo[c];
        //desconsiderar primeira linha (cabecalho)
        leitorVeiculos.nextLine(); 
        while(leitorVeiculos.hasNext())
        {
            //ler linha do arquivo
            String linha = leitorVeiculos.nextLine();
            //passar para parseVeiculo colocando em cada posicao do vetor veiculos 
            veiculos[i] = Veiculo.parseVeiculo(linha);
            i++;
        }
        //fechar leitor
        leitorVeiculos.close();
        
        //retornar array
        return veiculos;
    }   
} //end LeitorCsv

public class OrdenacaoMergesort 
{
    //metodo para ordenacao
    public static void sort(Veiculo[] v, int n)
    {
        mergesort(v, 0, n-1);
    } //end sort

    private static void mergesort(Veiculo[] v, int esq, int dir) 
    {
        if(esq < dir)
        {
            int meio = (esq + dir) / 2;
            mergesort(v, esq, meio);
            mergesort(v, meio + 1, dir);
            intercalar(v, esq, meio, dir);
        }
    } //end mergesort

    //funcao auxiliar para comparar o consumoCidade e, se necessario, fazer o desempate
    public static boolean compare(Veiculo a, Veiculo b)
    {
        boolean resp;

        if(a == null){ resp = false; }
        else
        {
            if( b == null){ resp = true; }
            else
            {
                //comparar consumo cidade
                int tmp = Double.compare(a.getConsumoCidade(), b.getConsumoCidade());
                //se empatar, comparar categoria
                if(tmp == 0)
                {
                    tmp = a.getCategoria().compareTo(b.getCategoria());
                }
                resp = (tmp < 0);
            }
        }
        return resp;
    } //end compare

    public static void intercalar(Veiculo[] v, int esq, int meio, int dir)
    {
        int n1, n2, i, j, k;

        //Definir tamanho dos dois subarrays
        n1 = meio - esq + 1;
        n2 = dir - meio;

        Veiculo[] a1 = new Veiculo[n1+1];
        Veiculo[] a2 = new Veiculo[n2+1];

        //Inicializar primeiro subarray
        for(i = 0; i < n1; i++)
        {
            a1[i] = v[esq+i];
        }

        //Inicializar segundo subarray
        for(j = 0; j < n2; j++)
        {
            a2[j] = v[meio+j+1];
        }

        //Sentinela no final dos dois arrays
        a1[i] = a2[j] = null;

        //Intercalacao propriamente dita
        //condicao ? a1[i++] : a2[j++] funciona como um if-else
        for(i = j = 0, k = esq; k <= dir; k++)
        {
            v[k] = compare(a1[i], a2[j]) ? a1[i++] : a2[j++];
        }
    } //end intercalar

    //recebe o pub.in com os ids e os procura no array de veiculos 
    public static void main(String args[]) throws FileNotFoundException
    {
        //decidir caminho, linux ou windows (teste local)
        String caminho;
        try
        {
            //scanner para provocar excecao, caso exista
            Scanner teste = new Scanner(new File("/tmp/veiculos.csv"));
            teste.close();
            //arquivo sera aberto com sintaxe linux
            caminho = "/tmp/veiculos.csv"; 
        }
        catch(FileNotFoundException erro)
        {
            //caminho sera aberto com sintaxe windows (p/ teste local)
            caminho = "veiculos.csv"; 
        }
        //ler pelo caminho escolhido 
        Veiculo[] veiculos = LeitorCsv.ler(caminho);
        
        Scanner sc = new Scanner(System.in);
        int id;

        //array para guardar veiculos selecionados
        Veiculo[] selecionados = new Veiculo[veiculos.length];
        //tamanho do array preenchido depois de ler os ids
        int n = 0 ;

        //enquanto nao chegar no final do arquivo com -1
        while((id = sc.nextInt()) != -1)
        {
            //declarar e reiniciar variavel de controle
            boolean controle = false;
            //busca sequencial para achar o veiculo com aquele id
            //cada posicao i da acesso a um objeto de veiculo
            for(int i = 0; i < veiculos.length && !controle; i++)
            {
                //.getId "olha" apenas o id
                if(veiculos[i].getId() == id)
                {
                    //se for o veiculo com o id procurado, guardar em selecionados
                    selecionados[n] = veiculos[i];
                    n++;
                    //tornar controle true (achou), nao fazer procura pelo mesmo id desnecessariamente
                    controle = true;
                }
            }
        }
        sc.close();

        sort(selecionados, n);

        for(int i = 0; i < n; i++) 
        {
            System.out.println(selecionados[i].format());
        }
    } //end main
} //end OrdenacaoMergesort  


