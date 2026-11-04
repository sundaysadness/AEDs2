public class rascunho 
{       
    public int terminaCom(String sufixo)
    {
        int resp = caminharArvore(sufixo, raiz);
        return resp;
    }
                                            //primeira chamada é a raiz
    private int caminharArvore(String sufixo, No no)
    {
        int resp;
        if(no == null)
        {
            return 0;
        }
        
        resp = percorrerLista(sufixo, no);
        
        resp = resp + caminharArvore(sufixo, no.esq);
        resp = resp + caminharArvore(sufixo, no.dir);
        

        return resp;
    }

    private int percorrerLista(String sufixo, No no)
    {
        int resp = 0;
        if(no != null)
        {
            for(Celula i = no.primeiro; i != null; i = i.prox)
            {
                if(i.palavra.endsWith(sufixo)) resp++;
            }
        }
        return resp;
    }   
}

void insertionSort()
{
    for(CelulaDupla* i = primeiro->prox->prox; i != primeiro; i = i->prox)
    {
        int tmp = i->elemento;
        CelulaDupla* j = i->ant;
        while( j != primeiro && j->elemento > tmp)
        {
            j->prox->elemento = j->elemento;
            j = j->ant;
        }
        j->prox->elemento = tmp;
    }
}
