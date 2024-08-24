import java.util.Scanner;
import java.util.Date;
public class Ordenacao {
    public static void main(String[] args) {
        int tamanho;
        Scanner sc = new Scanner(System.in);
        MeuVetor v1;
        System.out.print("digite o tamanho do vetor, 0 encerra: ");
        tamanho = sc.nextInt();
        while (tamanho > 0) {
            v1 = new MeuVetor(tamanho);
            v1.preencheVetor();
            //System.out.println("vetor gerado:\n" + v1);
            long inicio = new Date().getTime();
            int comparacoes = v1.bubbleSort();
            long fim = new Date().getTime();
            //System.out.println("vetor ordenado pelo Bubble:\n" + v1);
            //System.out.println("tamanho: " + tamanho + ", comparacoes = " + comparacoes);
            System.out.println("tamanho  = " + tamanho + ", demorou " + (fim - inicio) + " ms");
            System.out.print("digite o tamanho do vetor, 0 encerra: ");
            tamanho = sc.nextInt();
        }
        sc.close();
    }
}