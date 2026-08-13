import java.util.*;

class Espelho
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        int n1, n2;
        int[] numeros;
        int[] digitos;

        while(sc.hasNext())
        {
            n1 = sc.nextInt();
            n2 = sc.nextInt();

            numeros = new int[100];
            digitos = new int[100];

            int c = 0;
            int n = 0;
            int i = 0;

            for(int x = n1; x <= n2; x++)
            {
                System.out.print(x);
                numeros[c] = x;
                c++; //numero de elementos
            }
            for(int z = c-1; z >=0; z--)
            {
                n = numeros[z];
                while(n > 0)
                {
                    digitos[i] = n % 10;
                    i++;
                    n = n / 10; 
                } 
            }
            for(int y = 0; y < i; y++)
            {
                System.out.print(digitos[y]);
            }
            System.out.println();
        }
        sc.close();
    }
}