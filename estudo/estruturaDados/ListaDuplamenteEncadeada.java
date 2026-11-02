class CelulaDupla 
{
    public int elemento;
    public CelulaDupla prox, ant;

    public CelulaDupla() 
    {
        this(0);
    }

    public CelulaDupla(int x) 
    {
        this.elemento = x;
        this.prox = this.ant = null;
    }
}

class ListaDupla 
{
    private CelulaDupla primeiro, ultimo;

    public ListaDupla() 
    {
        primeiro = new CelulaDupla();
        ultimo = primeiro;
    }

    public void inserirInicio(int x) 
    {
        CelulaDupla tmp = new CelulaDupla(x);
        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if(primeiro == ultimo) 
        {
            ultimo = tmp;
        } 
        else 
        {
            tmp.prox.ant = tmp;
        }
        tmp = null;
    }

    public void inserirFim(int x) 
    {
        ultimo.prox = new CelulaDupla(x);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
    }

    public void inserir(int x, int pos) throws Exception 
    {
        int tamanho = tamanho();
        if(pos < 0 || pos > tamanho)
        { throw new Exception("Erro!"); } 
        else if(pos == 0){ inserirInicio(x); } 
        else if(pos == tamanho){ inserirFim(x); } 
        else 
        {
            CelulaDupla i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.ant = i;
            tmp.prox = i.prox;
            tmp.ant.prox = tmp.prox.ant = tmp;
            tmp = i = null;
        }
    }

    public int removerInicio() throws Exception 
    {
        if(primeiro == ultimo)
        throw new Exception("Erro!");
        CelulaDupla tmp = primeiro;
        primeiro = primeiro.prox;
        int elemento = primeiro.elemento;
        tmp.prox = primeiro.ant = null;
        tmp = null;
        return elemento;
    }

   public int removerFim() throws Exception 
   {
        if(primeiro == ultimo)
        throw new Exception("Erro!");

        int elemento = ultimo.elemento;
        ultimo = ultimo.ant;
        ultimo.prox.ant = null;
        ultimo.prox = null;
        return elemento;
    }

    public int remover(int pos) throws Exception 
    {
        int elemento, tamanho = tamanho();
        if(primeiro == ultimo){ throw new Exception("Erro!"); } 
        else if(pos < 0 || pos >= tamanho){ throw new Exception("Erro!"); } 
        else if(pos == 0){ elemento = removerInicio(); } 
        else if(pos == tamanho - 1){ elemento = removerFim(); } 
        else 
        {
            CelulaDupla i = primeiro;
            for (int j = 0; j <= pos; j++, i = i.prox);
            i.ant.prox = i.prox;
            i.prox.ant = i.ant;
            elemento = i.elemento;
            i.prox = i.ant = null;
            i = null;
        }
        return elemento;
    }

    public void mostrar() 
    {  
        for(CelulaDupla i = primeiro.prox; i != null; i = i.prox)
        {

        }
    }

    private int tamanho()
    {
        int tamanho = 0;
        return tamanho;
    }
}
