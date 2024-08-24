import java.util.Scanner;
 
public class OrdenaBusca {
    public static void main(String[] args) {
        MeuVetor v1 = new MeuVetor(120000);
        v1.preencheVetor();
        //System.out.println("vetor gerado:\n" + v1);
        Scanner sc = new Scanner(System.in);
        System.out.print("digite um valor para busca: ");
        double x = sc.nextDouble();
 
        Retorno r = v1.buscaSimples(x);
        System.out.println();
        if (r.getAchou()){
            System.out.println(x + " encontrado pela busca simples");
        }
        else {
            System.out.println(x + " nao encontrado pela busca simples");
        }
        System.out.println("foram realizados " + r.getContador() + " testes na busca simples");
 
        v1.bubbleSort();
        r = v1.buscaBinaria(x);
        System.out.println();
        if (r.getAchou()){
            System.out.println(x + " encontrado pela busca binaria");
        }
        else {
            System.out.println(x + " nao encontrado pela busca binaria");
        }
        System.out.println("foram realizados " + r.getContador() + " testes pela busca binaria");
        sc.close();
    }
}