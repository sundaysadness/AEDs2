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

class No
{
    Veiculo elemento;
    No esq;
    No dir;

    //construtores
    public No(Veiculo elemento)
    {
        this(elemento, null, null);
    }

    public No(Veiculo elemento, No esq, No dir)
    {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
    }

}

class ArvoreBinaria
{
    No raiz;

    //construtor
    public ArvoreBinaria()
    {
        raiz = null;
    }

    //funcao de apoio para comparar
    private int compare(Veiculo a, Veiculo b)
    {
        int resp;
        if(a.getModelo().compareTo(b.getModelo()) < 0){ resp = 1; }
        else{ resp = 0; }
        return resp;
    } //end compare

    //inserir retorna void
    public void inserir(Veiculo x)
    {
        raiz = inserir(x, raiz);
    } //end inserir

    public No inserir(Veiculo x, No i)
    {
        //se a arvore estiver vazia
        if(i == null){ i = new No(x); }
        //elemento a ser inserido é menor que raiz
        else if(x < i.elemento){ i.esq = inserir(x, i.esq); }
        //elemento a ser inserido é maior que raiz
        else if(x > i.elemento){ i.dir = inserir(x, i.dir); }
        else{ System.out.println("Elemento repetido!"); }
        return i;
    } //end inserir

    //pesquisar retorna verdadeiro ou falso
    //pesquisar recursivo
    public boolean pesquisarRec(Veiculo x)
    {
        return pesquisarRec(x, raiz); 
    } //end pesquisarRec

    public boolean pesquisarRec(Veiculo x, No i)
    {
        boolean resp = false;
        if(i == null){ resp = false; }
        else if(i.elemento == x){ resp = true; }
        else if(compare(x, i.elemento)){ pesquisarRec(x, i.esq); }
        else if(x > i.elemento){ pesquisarRec(x, i.dir); }
        return resp;
    } //end pesquisarRec

    //pesquisar iterativo
    public boolean pesquisar(Veiculo x)
    {
        No i = raiz;
        while(i != null && i.elemento != x)
        {
            if(x < i.elemento){ i = i.esq; }
            else{ i = i.dir; }
        }
        return (i != null);
    } //end pesquisar iterativo 

    public void caminharPre()
    {
        //se arvore estiver vazia
        if(raiz == null){ System.out.println("Arvore vazia!"); }
        else{ caminharPre(raiz); }
    } //end caminharPre

    public void caminharPre(No i)
    {
        if(i != null)
        {
            System.out.print(i.elemento + " ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    } //end caminharPre

    public void caminharPos()
    {
        //se arvore estiver vazia
        if(raiz == null){ System.out.println("Arvore vazia!"); }
        else{ caminharPos(raiz); }
    } //end caminharPos

    public void caminharPos(No i)
    {
        if(i != null)
        {
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.print(i.elemento + " ");
        }
    } //end caminharPos

    public void caminharEn()
    {
        //se arvore estiver vazia
        if(raiz == null){ System.out.println("Arvore vazia!"); }
        else{ caminharEn(raiz); }
    } //end caminharEn

    public void caminharEn(No i)
    {
        if(i != null)
        {
            caminharEn(i.esq);
            System.out.print(i.elemento + " ");
            caminharEn(i.dir);
        }
    } //end caminharEn

    public void altura()
    {
        int h = altura(raiz);
        System.out.println(h);
    } //end altura

    //retorna a altura inteira
    public int altura(No i)
    {
        //caso base (parada)
        if(i == null){ return -1; }

        int altEsq = altura(i.esq);
        int altDir = altura(i.dir);

        if(altEsq > altDir){ return altEsq + 1; }
        else{ return altDir + 1; }
    } //end altura


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

        //arvore para guardar veiculos selecionados
        ArvoreBinaria arvore = new ArvoreBinaria();

        //tamanho do array preenchido depois de ler os ids
        //int n = 0 ;

        //enquanto nao chegar no final do arquivo com -1 inserir os elementos na árvore
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
                    //se for o veiculo com o id procurado, inserir na arvore
                    inserir(veiculos[i]);
                    n++;
                    //tornar controle true (achou), nao fazer procura pelo mesmo id desnecessariamente
                    controle = true;
                }
            }
        }
        sc.close();

        //pesquisar elementos na arvore

        //imprimir arvore com caminhamento em ordem


    } //end main
}