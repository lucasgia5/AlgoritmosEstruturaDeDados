public class ABB {
    private No raiz;
    private int n;
    public boolean estaVazia() {
        return raiz == null;
    }
    public void insere(int i) {
        No novo = new No(i);
        if (estaVazia()) 
            raiz = novo;
        else 
            insereRec(novo, raiz);
    }
    void insereRec(No novo, No atual) {
        if (novo.getInfo() <= atual.getInfo()) {
            if (atual.getEsquerda() == null) 
                atual.setEsquerda(novo);
            else 
                insereRec(novo, atual.getEsquerda());
            }
        else {
            if (atual.getDireita() == null) 
                atual.setDireita(novo);
            else 
                insereRec(novo, atual.getDireita());
        }
    }
    public String toStringEmOrdem () {
        if (estaVazia())
            return "arvore vazia";
        return toStringEmOrdemRec(raiz);
    }
    String toStringEmOrdemRec (No atual) {
        if (atual == null)
            return "";
        return toStringEmOrdemRec(atual.getEsquerda()) +
               atual.getInfo() + " " +
               toStringEmOrdemRec(atual.getDireita());
    }
    public int numeroNos () {
        if (estaVazia())
            return 0;
        return numeroNosRec (raiz);
    }
    int numeroNosRec (No atual) {
        if (atual == null)
            return 0;
        return numeroNosRec(atual.getEsquerda()) + 1 + numeroNosRec(atual.getDireita());
    }
    public int altura () {
        if (estaVazia()) return 0;
        return alturaRec (raiz);
    }
    int alturaRec (No atual) {
        if (atual.getEsquerda() == null && atual.getDireita() == null)
            return 0;
        
        int nivelEsq = 0;
        if (atual.getEsquerda() != null) 
            nivelEsq = alturaRec(atual.getEsquerda());
 
        int nivelDir = atual.getDireita() != null ? alturaRec(atual.getDireita()) : 0;
 
        return nivelDir > nivelEsq ? nivelDir + 1 : nivelEsq + 1;
    }
    public boolean busca (int x) {
        if (estaVazia()) 
            return false;
        return buscaRec (x, raiz);
    }
    boolean buscaRec (int x, No atual) {
        //modo 1 = requer a verificação do nó antes da chamada, exemplo só para esquerda
        // if (x == atual.getInfo()) return true;
        // if (x <= atual.getInfo())
        //     if (atual.getEsquerda() != null)
        //         return buscaRec(x, atual.getEsquerda());
        //     return false;
 
        //modo 2 = faz a verificação do nó na chegada da chamada
        if (atual == null) return false;
        if (atual.getInfo() == x) return true;
        if (x <= atual.getInfo()) return buscaRec(x, atual.getEsquerda());
        return buscaRec(x, atual.getDireita());
    }
 
    public boolean remocao (int x) {
        if (estaVazia()) return false;
        return remocaoRec (x, raiz, null, false);
    }
    boolean remocaoRec (int x, No atual, No pai, boolean eFilhoEsquerdo) {
        if (atual == null) return false;
        if (atual.getInfo() == x) { //achou
            if (atual.getEsquerda() == null && atual.getDireita() == null) { //nó a ser removido não tem filhos
                if (atual == raiz)
                    raiz = null;
                else if (eFilhoEsquerdo) 
                    pai.setEsquerda(null);
                else 
                    pai.setDireita(null);
            }
            else if (atual.getEsquerda() == null ) { //só tem filho na direita
                if (atual == raiz)
                    raiz = atual.getDireita();
                else if (eFilhoEsquerdo)
                    pai.setEsquerda(atual.getDireita());
                else 
                    pai.setDireita(atual.getDireita());
            }
            else if (atual.getDireita() == null) { //só tem filho da esquerda
                if (atual == raiz)
                    raiz = atual.getEsquerda();
                else if (eFilhoEsquerdo)
                    pai.setEsquerda(atual.getEsquerda());
                else 
                    pai.setDireita(atual.getEsquerda());
            }
            else { //tem os 2 filhos
                //a sub-árvore da esquerda é "adotada" pelo sucessor
                //sucesso é o menor entre os maiores que o atual 
                No sucessor = atual.getDireita();
                while (sucessor.getEsquerda() != null)
                    sucessor = sucessor.getEsquerda();
                sucessor.setEsquerda(atual.getEsquerda());
                //a sub-árvore da direita é "adotada" pelo pai ou é a raiz
                if (pai == null)
                    raiz = atual.getDireita();
                else if (eFilhoEsquerdo)
                    pai.setEsquerda(atual.getDireita());
                else
                    pai.setDireita(atual.getDireita());
            }
            return true;
        }
        else if (x < atual.getInfo()) {
            eFilhoEsquerdo = true;
            pai = atual;
            return remocaoRec(x, atual.getEsquerda(), pai, eFilhoEsquerdo);
        }
        else { // x é maior
            eFilhoEsquerdo = false;
            pai = atual;
            return remocaoRec(x, atual.getDireita(), pai, eFilhoEsquerdo);
        }
    }
    @Override 
    public boolean equals (Object object) {
        if (this == object) return true;
        if (object instanceof ABB) {
            ABB abb = (ABB) object;
            return equalsRec(abb.raiz, this.raiz);
        }
        return false;
    }
    public boolean equalsRec (No abbAtual, No thisAtual) {
        if (thisAtual == null && abbAtual == null) return true;
        else if (thisAtual != null && abbAtual != null) {
            if (thisAtual.getDireita() == null && thisAtual.getEsquerda() == null &&
                abbAtual.getEsquerda() == null && abbAtual.getDireita() == null) {
                    if (thisAtual.getInfo() == abbAtual.getInfo()) return true;
                    return false;
            }
            boolean resultEsq = equalsRec(abbAtual.getEsquerda(), thisAtual.getEsquerda());
            boolean resultDir = equalsRec(abbAtual.getDireita(), thisAtual.getDireita());
            if (resultEsq == false || resultDir == false) return false;
            return true; 
        }
        return false;
    }
 
    public int nivel (int x) {
        if (estaVazia()) 
            return -1;
        n=-1;
        return nivelRec (x, raiz);
    }
    int nivelRec (int x, No atual) {
        if (atual == null) return -1;
        n++;
        if (atual.getInfo() == x) return n;
        if (x < atual.getInfo()) return nivelRec(x, atual.getEsquerda());
        return nivelRec(x, atual.getDireita());
    }
}
