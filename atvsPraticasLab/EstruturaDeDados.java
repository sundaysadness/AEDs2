import java.util.Scanner;

public class EstruturaDeDados
{
    //funcao para testar se é pilha
    public static boolean isPilha(int[] vetTipo, int[] vetNum, int n1)
    {
        //vetor temporario para guardar valores inseridos
        int[] vetTemp = new int [n1];
        //indice para numeros
        int y = 0;
        //variavel de controle
        boolean c = true;
        //percorrer todo o array de tipo (1 ou 2)
        for(int i = 0; i < n1 && c; i++)
        {
            //se for para inserir (tipo 1)
            if(vetTipo[i] == 1)
            {
                //insere em vetTemp
                vetTemp[y] = vetNum[i];
                y++; //aumenta o indice (conta os elementos totais)
            }
            else
            {
                //necessario subtrair para indicar o ultimo elemento que foi colocado (topo)
                y--;
                //compara se o elemento que quer tirar é diferente que esta no topo
                if(vetNum[i] != vetTemp[y])
                {
                    //se for diferente, fica falso e o loop para
                    c = false;
                }
            }
        }
        return c;
    }
    //funcao para testar se é fila
    public static boolean isFila(int[] vetTipo, int[] vetNum, int n1)
    {
        //vetor temporario para guardar valores inseridos
        int[] vetTemp = new int [n1];
        //indice para numeros
        int y = 0;
        int x = 0;
        //variavel de controle
        boolean c = true;
        //percorrer todo o array de tipo (1 ou 2)
        for(int i = 0; i < n1 && c; i++)
        {
            //se for para inserir (tipo 1)
            if(vetTipo[i] == 1)
            {
                //insere em vetTemp
                vetTemp[y] = vetNum[i];
                y++; //aumenta o indice (conta os elementos totais)
            }
            else
            {
                //compara se o elemento que quer tirar é diferente do que esta no inicio
                if(vetNum[i] != vetTemp[x])
                {
                    //se for diferente, fica falso e o loop para
                    c = false;
                }
                //aumenta indice do inicio
                x++;
            }
        }
        return c;
    }
    //funcao para testar se é fila de prioridade
    public static boolean isFilaPrioridade(int[] vetTipo, int[] vetNum, int n1)
    {
        //vetor temporario para guardar valores inseridos
        int[] vetTemp = new int [n1];
        //indice para numeros
        int y = 0;
        //variavel de controle
        boolean c = true;
        //percorrer todo o array de tipo (1 ou 2)
        for(int i = 0; i < n1 && c; i++)
        {
            //se for para inserir (tipo 1)
            if(vetTipo[i] == 1)
            {
                //insere em vetTemp
                vetTemp[y] = vetNum[i];
                y++; //aumenta o indice (conta os elementos totais)
                //algoritmo de ordenacao (insercao)
                //limite do for sendo o tamanho atual do vetor
                for(int j = 1; j < y; j++)
                {
                    int tmp = vetTemp[j]; //salva o elemento a ser inserido
                    int k = j - 1; //indice para elemento anterior
                    //enquanto k for maior que temp (e for um indice valido)
                    while((k >= 0) && (vetTemp[k] > tmp)) 
                    {
                        vetTemp[k+1] = vetTemp[k]; //realiza a troca
                        k--; //segue para o proximo elemento
                    }
                    //insere elemento salvo na posicao correta
                    vetTemp[k+1] = tmp;
                }
            }
            else
            {
                //indicar o ultimo elemento do array ordenado, ate o momento
                y--;
                //compara se o elemento que quer tirar é diferente do que esta no inicio
                if(vetNum[i] != vetTemp[y])
                {
                    //se for diferente, fica falso e o loop para
                    c = false;
                }
            }
        }
        return c;
    }
    //funcao principal
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        //repetir enquanto houver algo para ler
        while(sc.hasNext())
        {
            //le o primeiro inteiro (numero de movimentacoes)
            int n1 = sc.nextInt();
            //vetor para guardar se o numero entra ou sai (1 ou 2)
            int[] vetTipo = new int[n1];
            //vetor para guardar os valores em si, inseridos ou retirados
            int[] vetNum = new int [n1];
            //registrar todas as movimentacoes
            for(int i = 0; i < n1; i++)
            {
                //guardar 1 ou 2
                vetTipo[i] = sc.nextInt();
                //guardar valor
                vetNum[i] = sc.nextInt();
            }
            //declarar variaveis de controle
            boolean c1, c2, c3;
            //chamar funcoes
            c1 = isPilha(vetTipo, vetNum, n1);
            //System.out.println(c1); 
            c2 = isFila(vetTipo, vetNum, n1);
            //System.out.println(c2);
            c3 = isFilaPrioridade(vetTipo, vetNum, n1);
            //System.out.println(c3);
            //se pelo menos duas forem verdadeiras
            if((c1 && c2) || (c1 && c3) || (c2 && c3))
            {
                System.out.println("not sure");        //indeterminado
            }
            else
            {
                //se pilha for verdade
                if(c1)
                {
                    System.out.println("stack");           //pilha
                }
                else
                {
                    //se fila for verdade
                    if(c2)
                    {
                        System.out.println("queue");           //fila
                    }
                    else
                    {
                        //se fila prioridade for verdade
                        if(c3)
                        {
                            System.out.println("priority queue");  //fila de prioridade
                        }   
                        else
                        {
                            //nenhum for verdade
                            System.out.println("impossible");      //impossivel
                        }
                    }
                }
            }
        }
        sc.close();
    }
}