//Árvore com lista, árvore possui iniciais de nomes e a lista os nomes

public class Agenda 
{
    private No raiz; //ponteiro para raiz da árvore

    //pesquisar um contato por nome 
    //responsabilidade dele é orquestrar os metodos de pesquisar na arvore e pesquisar na lista
    //modularizar para fazer com que ele tenha apenas uma responsabilidade
    public Contato pesquisarPorNome(String nome)
    {
        Contato contato;
        //primeiro achar o no e depois achar na lista
        //pesquisar na arvore
        char letraInicial = nome.charAt(0);
        No no = pesquisarArvore(letraInicial, raiz);
        //pesquisar na lista correspondente se houver no com aquela inicial
        if(no == null){ contato = null; }
        else
        {
            contato = pesquisarListaPorNome(no, nome);
        }
        //retornar contato procurado
        return contato;
    }

    private No pesquisarArvore(char letra, No i)
    {
        No noEncontrado;
        if(i == null){ noEncontrado = null; }
        else
        {
            if(i.letra == letra){ noEncontrado = i; }
            else if(letra < i.letra){ noEncontrado = pesquisarArvore(letra, i.esq); }
            else{ noEncontrado = pesquisarArvore(letra, i.dir); }
        }
        return noEncontrado;
    }

    //pesquisar na lista
    //o no é o no da arvore que encontrados
    //primeiro é a celula cabeça da lista daquele no
    //prox é a primeira celula que realmente possui um contato
    private Contato pesquisarListaPorNome(No no, String nome)
    {
        Contato contato = null;
        
        for(Celula i = no.primeiro.prox; i != null; i = i.prox)
        {
            if(i.contato.nome.equals(nome))
            {
                contato = i.contato;
                i = no.ultimo; //finalizar a iteração
            }
        }
        return contato;
    }

    //inserção supondo que exista nos com todas as letras
    //é o orquestrador
    public void inserir(Contato contato) throws Exception
    {
        char letraInicial = contato.nome.charAt(0);
        No no = pesquisarArvore(letraInicial, raiz);
        if(no == null) //pré-condição não satisfeira
        { throw new Exception("No inexistente!"); }
        inserirLista(no, contato);
    }

    //insere novo contato na lista
    private void inserirLista(No no, Contato contato)
    {
        Celula tmp = new Celula(contato);
        no.ultimo.prox = tmp;
        no.ultimo = no.ultimo.prox;
    }

    //insere sem a suposição de que há todos os nos com todas as letras na lista
    public void inserir2(Contato contato)
    {
        char letraInicial = contato.nome.charAt(0);
        raiz = inserirPesquisarArvore(letraInicial, raiz, contato);
    }

    private No inserirPesquisarArvore(char letra, No i, Contato contato)
    {
        //se nao encontrou o no
        if(i == null)
        {
            //cria o no
            i = new No(letra);
            //inserir na lista do no i
            inserirLista(i, contato);
        }
        //se encontrou o no
        else if(letra == i.letra)
        {
            //inserir na lista do no i
            inserirLista(i, contato);
        }
        //caminha, chamando recursivamente, para subir a arvore da esq ou da dir
        else if(letra < i.letra){ i.esq = inserirPesquisarArvore(letra, i.esq, contato); }
        else{ i.dir = inserirPesquisarArvore(letra, i.dir, contato); }
        return i;
    }

    //remover
    public Contato remover(Contato contato)
    {
        //declar uma variavel temporaria
        Contato cont = null;
        //fazer pesquisa pela letra inicial do nome
        char letraInicial = contato.nome.charAt(0);
        No no = pesquisarArvore(letraInicial, raiz);
        //fazer algo apenas se encontrar essa inicial em algum no
        if(no != null)
        {
            //remover da lista encadeada associada ao no
            cont = removerLista(no, contato);
        }
        return cont;
    }

    private Contato removerLista(No no, Contato contatoProcurado)
    {
        Contato result;
        Celula i;

        //se a lista estiver vazia
        if(no.primeiro.prox == null){ result = null;  }

        //parar na celula anterior aq estamos procurando
        for(i = no.primeiro; 
            i.prox.contato != contatoProcurado && i.prox != null; 
            i = i.prox);

        //se chegou ao final sem encontrar nenhum contato
        if(i.prox == null){ result = null; }
        //guarda a celula que sera removida
        Celula tmp = i.prox;
        //guarda o contato que sera retornado
        result = tmp.contato;
        //fazer a celula anterior pular a celula que sera removida
        i.prox = tmp.prox;
        //se oq foi removido foi o ultimo, atualizar ultimo
        if(no.ultimo == tmp)
        { no.ultimo = i; }
        //desconectar a celula removida
        tmp.prox = null; //remover
        return result;
    }

    public Contato pesquisarPorCpf(int cpf)
    {
        //caminhar pela arvore
        //para cada no visitado, pesquisar pelo cpf na lista
        return caminharArvorePesquisarCpf(raiz, cpf);
    }

    private Contato caminharArvorePesquisarCpf(No i, int cpf)
    {
        Contato contato = null;
        if(i != null)
        {
            //visitar o no, pesquisa em sua lista pelo cpf
            contato = pesquisarListaPorcpf(i, cpf);
            //se nao encontrou o contato, caminhar para sub arvore da esquerda
            if(contato == null)
            {
                contato = caminharArvorePesquisarCpf(i.esq, cpf);
            }
            //se nao encontrou caminhar para sub arvore da direita
            if(contato == null)
            {
                contato = caminharArvorePesquisarCpf(i.dir, cpf);
            }
        }
        return contato;
    }

    //reutilização do pesquisarListaPorNome
    private Contato pesquisarListaPorcpf(No no, int cpf)
    {
        Contato contato = null;
        
        for(Celula i = no.primeiro.prox; i != null; i = i.prox)
        {
            if(i.contato.cpf == cpf)
            {
                contato = i.contato;
                i = no.ultimo; //finalizar a iteração
            }
        }
        return contato;
    }
}

class Contato
{
    String nome, telefone, email;
    int cpf;
}

class Celula
{
    Contato contato;
    Celula prox;

    //construtor
    public Celula(Contato contato)
    { this.contato = contato; }
}

class No
{
    char letra;
    No esq, dir;
    Celula primeiro, ultimo; //lista com cabeça

    //construtor
    public No(char letra)
    { 
        this.letra = letra; 
        //gerar lista vazia, com cleula cabeça
        primeiro = ultimo = new Celula(null);
    }
}

/**
 * Analise da complexidade
   Operação	    Melhor caso	    Pior caso
    Pesquisar	O(1)	          O(n+m)
    Inserir	    O(1)	           O(n)
    Remover	    O(1)	          O(n+m)

    (n): quantidade de nós na árvore, ou seja, quantidade de letras iniciais cadastradas.
    (m): quantidade de contatos na lista de uma determinada letra.
    (h): altura da árvore.
*/