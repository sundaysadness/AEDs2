import java.util.*;

class No
{
    int elemento;
    No esq;
    No dir;
    
    No(int elemento)
    {
        //chamara o segundo construtor
        this(elemento, null, null);
    }

    No(int elemento, No esq, No dir)
    {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
} //end No

class ArvoreBinaria
{
    No raiz;
    ArvoreBinaria()
    {
        raiz = null;
    }

    public void inserir(int x)
    {
        raiz = inserir(x, raiz);
    }

    No inserir(int x, No i)
    {
        //se a raiz estiver vazia
        if(i == null){ i = new No(x); }
        //elemento a ser inserido é menor que raiz
        else if(x < i.elemento){ i.esq = inserir(x, i.esq); }
        //elemento a ser inserido é maior que raiz
        else if(x > i.elemento){ i.dir = inserir(x, i.dir); }
        else
        { System.out.println("Elemento já existe!"); }
        return i;
    }

    public boolean pesquisar(int x)
    {
        No i = raiz;
        while(i != null && x != i.elemento)
        {
            if(x < i.elemento){  System.out.print(i.elemento + " "); i = i.esq; }
            else
            {  System.out.print(i.elemento + " "); i = i.dir; }
        }
        if(i != null){ System.out.print(x + " "); }
        return (i != null);
    }

    public void caminharPre()
    {
        if(raiz == null)
        { System.out.println("V");}
        else
        { caminharPre(raiz); }
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
        if(raiz == null)
        { System.out.println("V");}
        else
        { caminharPos(raiz); }
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

    public void caminharEm()
    {
        if(raiz == null)
        { System.out.print("V");}
        else
        { caminharEm(raiz); }
    }

    public void caminharEm(No i)
    {
        if(i != null)
        {
            caminharEm(i.esq);
            System.out.print(i.elemento + " ");
            caminharEm(i.dir);
        }
    }

    public void altura()
    {
        int h = altura(raiz);
        System.out.println(h);
    }

    public int altura(No i)
    {
        if(i == null) 
        {
            return -1;
        }

        int altEsq = altura(i.esq);
        int altDir = altura(i.dir);

        if(altEsq > altDir) 
        {
            return altEsq + 1;
        } else 
        {
            return altDir + 1;
        }
    }

    public void descendentes(int x)
    {
        No i = raiz;
        //caminhamento
        while(i != null && i.elemento != x)
        { 
            if(x < i.elemento){ i = i.esq; }
            else
            { i = i.dir; }
        }
        if(i != null)
        {
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
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
            if(i.esq == null && i.dir == null)
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
} //end ArvoreBinaria

class LabArvoreBinaria
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        ArvoreBinaria arvore = new ArvoreBinaria();

        while(sc.hasNext())
        {
            //.next() le a proxima palavra ate encontrar um espaço
            //.charAt(0) pega apenas o primeiro caractere transformando string em char
            //char L = sc.next().charAt(0);
            
            //ler a linha toda
            String linha = sc.nextLine();
            //separar nos espaços
            String[] p = linha.split(" ");
            //ler a parte de letra/string
            String l = p[0];
            //se tamanho do array de pedaços for maior que 1, pegar o numero
            int n = 0;
            if(p.length > 1)
            {
                n = Integer.parseInt(p[1]);
            }

            if(l.equals("PRE")){ arvore.caminharPre(); System.out.println(); }
            else
            {
                if(l.equals("POS")){ arvore.caminharPos(); System.out.println(); } 
                else
                if(l.equals("EM")){ arvore.caminharEm(); System.out.println(); }
                else
                if(l.equals("I")){ arvore.inserir(n); }
                else
                if(l.equals("P"))
                { System.out.println(arvore.pesquisar(n) ? "S" : "N"); }
                else 
                if(l.equals("H")){ arvore.altura(); }
                else
                if(l.equals("D")){ arvore.descendentes(n); System.out.println(); }
                else
                if(l.equals("F")){ arvore.mostrarFolhas(); System.out.println(); }
                else
                if(l.equals("N")){ arvore.mostrarNos();System.out.println();  }
            }
        }
    }
} //end LabArvoreBinaria