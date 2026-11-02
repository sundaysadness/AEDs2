class No
{
    int elemento;
    No esq;
    No dir;

    //construtores
    public No(int elemento)
    {
        this(elemento, null, null);
    }

    public No(int elemento, No esq, No dir)
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

    //inserir retorna void
    public void inserir(int x)
    {
        raiz = inserir(x, raiz);
    }

    public No inserir(int x, No i)
    {
        //se a arvore estiver vazia
        if(i == null){ i = new No(x); }
        //elemento a ser inserido é menor que raiz
        else if(x < i.elemento){ i.esq = inserir(x, i.esq); }
        //elemento a ser inserido é maior que raiz
        else if(x > i.elemento){ i.dir = inserir(x, i.dir); }
        else{ System.out.println("Elemento repetido!"); }
        return i;
    }

    //pesquisar retorna verdadeiro ou falso
    //pesquisar recursivo
    public boolean pesquisarRec(int x)
    {
        return pesquisarRec(x, raiz); 
    }

    public boolean pesquisarRec(int x, No i)
    {
        boolean resp = false;
        if(i == null){ resp = false; }
        else if(i.elemento == x){ resp = true; }
        else if(x < i.elemento){ pesquisarRec(x, i.esq); }
        else if(x > i.elemento){ pesquisarRec(x, i.dir); }
        return resp;
    }

    //pesquisar iterativo
    public boolean pesquisar(int x)
    {
        No i = raiz;
        while(i != null && i.elemento != x)
        {
            if(x < i.elemento){ i = i.esq; }
            else{ i = i.dir; }
        }
        return (i != null);
    }

    public void caminharPre()
    {
        //se arvore estiver vazia
        if(raiz == null){ System.out.println("Arvore vazia!"); }
        else{ caminharPre(raiz); }
    }

    public void caminharPre(No i)
    {
        if(i != null)
        {
            System.out.print(i.elemento + " ");
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }

    public void caminharPos()
    {
        //se arvore estiver vazia
        if(raiz == null){ System.out.println("Arvore vazia!"); }
        else{ caminharPos(raiz); }
    }

    public void caminharPos(No i)
    {
        if(i != null)
        {
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.print(i.elemento + " ");
        }
    }

    public void caminharEn()
    {
        //se arvore estiver vazia
        if(raiz == null){ System.out.println("Arvore vazia!"); }
        else{ caminharEn(raiz); }
    }

    public void caminharEn(No i)
    {
        if(i != null)
        {
            caminharEn(i.esq);
            System.out.print(i.elemento + " ");
            caminharEn(i.dir);
        }
    }

    public void altura()
    {
        int h = altura(raiz);
        System.out.println(h);
    }

    //retorna a altura inteira
    public int altura(No i)
    {
        //caso base (parada)
        if(i == null){ return -1; }

        int altEsq = altura(i.esq);
        int altDir = altura(i.dir);

        if(altEsq > altDir){ return altEsq + 1; }
        else{ return altDir + 1; }
    }

    public void descendentes(int x)
    {

    }

     public void mostrarFolhas()
    {
        mostrarFolhas(raiz);
    }

    public void mostrarFolhas(No i)
    {
        if(i != null)
        {
            mostrarFolhas(i.esq);
            if(i.esq == null & i.dir == null)
            {
                System.out.print(i.elemento + " ");
            }
            mostrarFolhas(i.dir);
        }
    }

     public void mostrarNos()
    {
        mostrarNos(raiz);
    }

    public void mostrarNos(No i)
    {
        if(i != null)
        {
            mostrarNos(i.esq);
            if(i.esq != null || i.dir != null)
            {
                System.out.print(i.elemento + " ");
            }
            mostrarNos(i.dir);
        }
    }

    public static void main(String args[])
    {}
}