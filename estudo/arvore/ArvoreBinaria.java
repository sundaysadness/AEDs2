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

    private No buscarNo(int x, No i)
    {
        if(i == null || i.elemento == x){ return i; }
        if(x < i.elemento){ return buscarNo(x, i.esq); }
        return buscarNo(x, i.dir);
    }

    public void descendentes(int x)
    {
        No i = buscarNo(x, raiz);
        if(i == null)
        {
            System.out.println("Elemento não encontrado!");
        }
        else
        {
            System.out.print("Descendentes de " + x + ": ");
            caminharPre(i.esq);
            caminharPre(i.dir);
            System.out.println();
        }
    }

    public void ancestrais(int x)
    {
        if(!pesquisar(x))
        {
            System.out.println("Elemento não encontrado!");
        }
        else
        {
            System.out.print("Ancestrais de " + x + ": ");
            ancestrais(raiz, x);
            System.out.println();
        }
    }

    private boolean ancestrais(No i, int x)
    {
        if(i == null || i.elemento == x)
        {
            return i != null && i.elemento == x;
        }

        // Se o elemento está na subárvore esquerda ou direita, 
        // o nó atual 'i' faz parte dos ancestrais.
        if(x < i.elemento)
        {
            if(ancestrais(i.esq, x))
            {
                System.out.print(i.elemento + " ");
                return true;
            }
        }
        else
        {
            if(ancestrais(i.dir, x))
            {
                System.out.print(i.elemento + " ");
                return true;
            }
        }
        return false;
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