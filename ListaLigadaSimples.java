public class ListaLigadaSimples {
    private No primeiro;
    //construtor padrão
    public No getPrimeiro() {
        return primeiro;
    }
    public void setPrimeiro(No primeiro) {
        this.primeiro = primeiro;
    }
    public boolean estaVazia() {
        return primeiro==null;
    }
    public void insereInicio(int i) {
        No novo = new No(i);
        if (!estaVazia()) {
            novo.setProximo(primeiro);
        }
        primeiro = novo;
    }
    @Override
    public String toString () {
        String s = "lista: ";
        if (estaVazia()) {
            s += "vazia";
        }
        else {
            No aux = primeiro;
            while (aux != null) {//varre a lista até o final
                s = s + aux;
                aux = aux.getProximo();
            }
            s = s + "\\\\";
        }
        return s;
    }
    public void insereFim(int i) {
        No novo = new No(i);
        if (estaVazia()) {
            primeiro = novo;
        }
        else {
            No aux = primeiro;
            while (aux.getProximo() != null) {
                aux = aux.getProximo();                
            }
            aux.setProximo(novo);
        }
    }
    public int removeInicio () {
        int temp = primeiro.getInfo();
        primeiro = primeiro.getProximo();
        return temp;
    }
    public int removeFim() {
        int temp;
        //caso especial: um elemento só
        if (primeiro.getProximo() == null) {
            temp = primeiro.getInfo();
            primeiro = null;
        }
        else {
            //percorrer até encontrar o penúltimo
            No aux = primeiro;
            while (aux.getProximo().getProximo() != null) {
                aux = aux.getProximo();
            }
            temp = aux.getProximo().getInfo();
            aux.setProximo(null);
        }
        return temp;
    }
    public Retorno encontraMaior () {
        Retorno r = new Retorno();
        if (estaVazia()) {
            r.setAchou(false);
            r.setContador(0);
            return r;
        }
        int maior = primeiro.getInfo();
        No aux = primeiro.getProximo();
        while (aux != null) {
            if (aux.getInfo() > maior) 
                maior = aux.getInfo();
            aux = aux.getProximo();
        }
        r.setAchou(true);
        r.setContador(maior);
        return r;
    }
}
