public class ListaDupla {
    private NoDuplo primeiro;
    private NoDuplo ultimo;
    //construtor padrão
    public boolean estaVazia() {
        return primeiro == null;
    }
    public void insereInicio (int info) {
        NoDuplo novo = new NoDuplo(info);
        if (estaVazia()) {
            ultimo = novo;
        }
        else {
            novo.setProximo(primeiro);
            primeiro.setAnterior(novo);
        }
        primeiro = novo;
    }
    public void insereFim(int info) {
        NoDuplo novo = new NoDuplo(info);
        if (estaVazia()) {
            primeiro = novo;
        } else {
            novo.setAnterior(ultimo);
            ultimo.setProximo(novo);
        }
        ultimo = novo;
    }
    public int removeInicio () {
        int info = primeiro.getInfo();
        primeiro = primeiro.getProximo();
        if (primeiro == null) { //tinha um único elemento
            ultimo = null; //esvaziou a lista
        }
        else {
            primeiro.setAnterior (null);
        }
        return info;
    }    
    public int removeFim () {
        int info = ultimo.getInfo();
        ultimo = ultimo.getAnterior();
        if (ultimo == null) {//esvaziou a lista
            primeiro = null;
        }
        else {
            ultimo.setProximo(null);
        }
        return info;
    }
    @Override
    public String toString () {
        String s = "";
        if (estaVazia()) {
            s += "lista vazia";
        }
        else {
            NoDuplo aux = primeiro;
            s += "//< = ";
            while (aux != null) {
                s += aux + " = ";
                aux = aux.getProximo();
            }
            s += ">//";
        }
        return s;
    }
 
    public void insereElementoPosicao (int info, int pos) {
        if (estaVazia() || pos == 1) {
            insereInicio(info);
        }
        else { // encontrar a posicao
            NoDuplo aux = primeiro;
            int cont = 1;
            while (aux != ultimo && cont < pos) {
                aux = aux.getProximo();
                cont++;
            }
            if (aux == ultimo) {
                insereFim(info);
            }
            else {
                NoDuplo novo = new NoDuplo(info);
                novo.setProximo(aux);
                novo.setAnterior(aux.getAnterior());
                novo.getProximo().setAnterior(novo);
                novo.getAnterior().setProximo(novo);
            }
        }
    }
}