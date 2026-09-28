//importar toda a biblioteca java
import java.util.*;

public class Quadraticos
{
    static void swap(int a, int b)
    {
        int tmp = a;
        a = b;
        b = tmp;
    }

    //Selection sort (não é estável)
    /*
    Seleciona o menor e coloca no inico
    */
    static void selecao(int[] array, int n)
    {
        for(int i = 0; i < n - 1; i++)
        {
            int menor = i;
            for(int j = i + 1; j < n; j++)
            {
                if(array[menor] > array[j])
                {
                    menor = j;
                }
            }
            swap(menor, i);
        }
    }

    //Insertion sort
    /*
    pega o proximo e insere no lugar certo dele no array ordenado
    */
    static void insercao(int[] array, int n)
    {
        for(int i = 1; i < n; i++)
        {
            int tmp = array[i];
            int j = i - 1;
            while( (j >= 0) && (array[j] > tmp))
            {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = tmp;
        }
    }

    //Bubble sort
    static void bolha(int[] array, int n)
    {
        for(int i = (n - 1); i > 0; i--) 
        {
			for(int j = 0; j < i; j++) 
            {
			    if(array[j] > array[j + 1]) 
                {
                    swap(j, j+1);
				}
			}
		}
    }

    //Main
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        double x = sc.nextDouble();
        String palavra = sc.next();      // até espaço
        String palavra2 = sc.nextLine();
        char c = sc.next().charAt(0);    // char não tem nextChar

        while (sc.hasNext()) { /* lê até EOF */ }
        sc.close();

        //5
        //Maria Silva
        int nu = sc.nextInt();
        sc.nextLine();                  // descarta o resto da linha do 5
        String nome = sc.nextLine();    // agora lê "Maria Silva"
        
        /*
            next(), nextInt(), nextDouble(): pulam espaços e quebras de linha antes do valor, leem só o token e param logo depois dele, sem consumir o \n
            nextLine(): não pula nada. Lê o que estiver na frente, mesmo que seja uma linha vazia
        */

        /*
            equals e equalsIgnoreCase (o "strcmp" do Java)
            boolean a.equals(String b);
            boolean a.equalsIgnoreCase(String b);
            Cuidado principal: == compara referências, não conteúdo. a == b pode dar false para textos idênticos (principalmente vindos do Scanner). Use sempre equals.
            Se a puder ser null, a.equals(b) lança NullPointerException. Uma proteção comum: "texto".equals(a).
        */

        /*
            compareTo (ordem alfabética)
            int a.compareTo(String b);
            Retorna 0 se iguais, negativo se a vem antes, positivo se vem depois.
            O valor não é limitado a -1/0/1 (costuma ser a diferença entre os caracteres ou entre os tamanhos). Teste só o sinal.
            Existe compareToIgnoreCase.
        */

        /*
            split (dividir a string)
            String[] s.split(String regex);
            String[] s.split(String regex, int limite);
            java
            String[] partes = "ana,bia,carlos".split(",");
            // ["ana", "bia", "carlos"]
            O parâmetro é uma expressão regular, não um texto literal. Consequências:
            Caracteres especiais precisam de escape: split("\\."), split("\\|"). Sem isso, split(".") devolve um array vazio.
            Para dividir por qualquer quantidade de espaços: split("\\s+").
            Campos vazios: "a,,b".split(",") dá ["a", "", "b"] (o vazio do meio permanece).
            Vazios no fim são descartados: "a,b,,".split(",") dá ["a", "b"]. Para mantê-los, use limite negativo: split(",", -1).
            Vazio no começo permanece: " a b".split(" ") gera "" como primeiro elemento. Aplique trim() antes e use "\\s+".
            Sempre confira partes.length antes de acessar índices.
        */

        /*
            substring, indexOf, charAt, length
            String s.substring(int inicio);
            String s.substring(int inicio, int fim);   // fim EXCLUSIVO
            int s.indexOf(String sub);                 // -1 se não achar
            char s.charAt(int i);
            int s.length();                            // método, com parênteses
            substring(2, 5) pega os índices 2, 3 e 4 (o 5 fica de fora).
            indexOf retorna -1 se não encontrar; teste antes de usar como índice. Existe lastIndexOf e indexOf(sub, aPartirDe).
            charAt e substring lançam StringIndexOutOfBoundsException se o índice for inválido.
            length() (String) versus length (array, sem parênteses): confusão frequente.
        */

        /*
            Integer.parseInt, Double.parseDouble, String.valueOf
            int n = Integer.parseInt("123");
            double d = Double.parseDouble("3.14");
            String s = String.valueOf(42);
            Lançam NumberFormatException se o texto for inválido, inclusive se houver espaço ou \n sobrando. Use trim() antes.
            parseDouble espera ponto decimal. Entrada com vírgula ("3,14") dá erro.
            Integer.parseInt(s, 2) converte de outras bases.
        */

    }
}