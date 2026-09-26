import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Locale;

class Lista
{
    //atributos
    Veiculo[] vetor;
    int tamanho;

    //construtor 
    Lista(int capacidade)
    {
        vetor = new Veiculo[capacidade];
        tamanho = 0;
    } //end construtor

    void inserirInicio(Veiculo veiculo)
    {
        //empurra todo mundo uma posicao pra frente, abrindo espaco no indice 0
        for(int i = tamanho; i > 0; i--)
        {
            vetor[i] = vetor[i - 1];
        }
        vetor[0] = veiculo;
        tamanho++;
    } //end inserirInicio

    void inserir(Veiculo veiculo, int posicao)
    {
        //empurra os elementos a partir da posicao, abrindo espaco
        for(int i = tamanho; i > posicao; i--)
        {
            vetor[i] = vetor[i - 1];
        }
        vetor[posicao] = veiculo;
        tamanho++;
    } //end inserir

    void inserirFim(Veiculo veiculo)
    {
        vetor[tamanho] = veiculo;
        tamanho++;
    } //end inserirFim

    Veiculo removerInicio()
    {
        Veiculo tmp = null;
        if(tamanho == 0)
        {
            System.out.println("Erro! Lista vazia!");
        }
        else
        {
            tmp = vetor[0];
            //puxa todo mundo uma posicao pra tras, fechando o buraco
            for(int i = 0; i < tamanho - 1; i++)
            {
                vetor[i] = vetor[i + 1];
            }
            tamanho--;
        }
        return tmp;
    } //end removerInicio

    Veiculo remover(int posicao)
    {
        Veiculo tmp = null;
        if(tamanho == 0)
        {
            System.out.println("Erro! Lista vazia!");
        }
        else
        {
            tmp = vetor[posicao];
            for(int i = posicao; i < tamanho - 1; i++)
            {
                vetor[i] = vetor[i + 1];
            }
            tamanho--;
        }
        return tmp;
    } //end remover

    Veiculo removerFim()
    {
        Veiculo tmp = null;
        if(tamanho == 0)
        {
            System.out.println("Erro! Lista vazia!");
        }
        else
        {
            tmp = vetor[tamanho - 1];
            tamanho--;
        }
        return tmp;
    } //end removerFim

} //end Lista

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
    
    public static Veiculo parseVeiculo(String s)
    {
        String[] pedacos = s.split(",");
        int      id                = Integer.parseInt(pedacos[0]);
        String   marca             = pedacos[1];
        String   modelo            = pedacos[2];
        int      ano               = Integer.parseInt(pedacos[3]);
        String   categoria         = pedacos[4];
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
        return (new Veiculo(id, marca, modelo, ano, categoria, combustivel, cilindros, 
                            cilindrada, transmissao, tracao, consumoCidade, consumoEstrada, co2, 
                            turbo, dataRegistro));
    } //end parseVeiculo

    private String formatCombustivel()
    {
        String combustivelString = "";
        for(int i = 0; i < combustivel.length; i++) 
        {
            if(i < combustivel.length - 1)
                combustivelString = combustivelString + combustivel[i] + ",";
            else
                combustivelString = combustivelString + combustivel[i];
        }
        return combustivelString;
    } //end formatCombustivel

    String format()
    {
        String combustivelString = formatCombustivel();
        return String.format(Locale.US, "[%d ## %s ## %s ## %d ## %s ## [%s] ## %d ## %.1f ## %s ## %s ## %.2f ## %.2f ## %.1f ## %b ## %s]",
                                id, marca, modelo, ano, categoria, combustivelString, cilindros, 
                                cilindrada, transmissao, tracao, consumoCidade, consumoEstrada, co2, 
                                turbo, dataRegistro.format());
    } //end format
} //end Veiculo

class Data
{
    private int dia;
    private int mes;
    private int ano; 

    Data(int dia, int mes, int ano)
    {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    } //end construtor

    int getDia() { return dia; }
    int getMes() { return mes; }
    int getAno() { return ano; }

    public static Data parseData(String s) 
    { 
        String[] pedacos = s.split("-");
        int ano = Integer.parseInt(pedacos[0]);
        int mes = Integer.parseInt(pedacos[1]);
        int dia = Integer.parseInt(pedacos[2]);
        return (new Data(dia, mes, ano));
    } //end parseData

    String format()
    {
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    } //end format
} //end Data

class LeitorCsv
{
    public static Veiculo[] ler(String arquivo) throws FileNotFoundException
    {
        int c = 0;
        Scanner leitorContagem = new Scanner(new File(arquivo));
        leitorContagem.nextLine(); 
        while(leitorContagem.hasNextLine())
        {
            leitorContagem.nextLine(); 
            c++; 
        }
        leitorContagem.close();

        int i = 0;
        Scanner leitorVeiculos = new Scanner(new File(arquivo));
        Veiculo[] veiculos = new Veiculo[c];
        leitorVeiculos.nextLine(); 
        while(leitorVeiculos.hasNext())
        {
            String linha = leitorVeiculos.nextLine();
            veiculos[i] = Veiculo.parseVeiculo(linha);
            i++;
        }
        leitorVeiculos.close();
        
        return veiculos;
    }   
} //end LeitorCsv

public class ListaAlocacaoSequencial
{
    static Veiculo pesquisaId(Veiculo[] v, int id)
    {
        for(int i = 0; i < v.length; i++)
        {
            if(v[i].getId() == id)
            {
                return v[i];
            }
        }
        return null;
    }

    public static void main(String args[]) throws FileNotFoundException
    {
        String caminho;
        try
        {
            Scanner teste = new Scanner(new File("/tmp/veiculos.csv"));
            teste.close();
            caminho = "/tmp/veiculos.csv"; 
        }
        catch(FileNotFoundException erro)
        {
            caminho = "veiculos.csv"; 
        }
        Veiculo[] veiculos = LeitorCsv.ler(caminho);
        
        Scanner sc = new Scanner(System.in);
        int id;

        Lista selecionados = new Lista(veiculos.length);

        while((id = sc.nextInt()) != -1)
        {
            boolean controle = false;
            for(int i = 0; i < veiculos.length && !controle; i++)
            {
                if(veiculos[i].getId() == id)
                {
                    selecionados.inserirFim(veiculos[i]);
                    controle = true;
                }
            }
        }

        int N = sc.nextInt();
        
        for(int i = 0; i < N; i++)
        {
            String indice = sc.next();

            if(indice.equals("I*"))
            {
                int pos = sc.nextInt();
                int id2 = sc.nextInt();
                selecionados.inserir(pesquisaId(veiculos, id2), pos);
            }
            else
            {
                if(indice.equals("II"))
                {
                    int id2 = sc.nextInt();
                    selecionados.inserirInicio(pesquisaId(veiculos, id2));
                }
                else
                {
                    if(indice.equals("IF"))
                    {
                        int id2 = sc.nextInt();
                        selecionados.inserirFim(pesquisaId(veiculos, id2));
                    }
                    else
                    {
                        if(indice.equals("R*"))
                        {
                            int pos = sc.nextInt();
                            Veiculo v = selecionados.remover(pos);
                            System.out.println( "(R)" + v.getMarca() + " " + v.getModelo() );
                        }
                        else
                        {
                            if(indice.equals("RI"))
                            {
                                Veiculo v = selecionados.removerInicio();
                                System.out.println( "(R)" + v.getMarca() + " " + v.getModelo() );
                            }
                            else
                            {
                                Veiculo v = selecionados.removerFim();
                                System.out.println( "(R)" + v.getMarca() + " " + v.getModelo() );
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0; i < selecionados.tamanho; i++)
        {
            System.out.println( selecionados.vetor[i].format() );
        }

        sc.close();
    } //end main
} //end ListaAlocacaoSequencial