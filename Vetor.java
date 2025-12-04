public class Vetor {
    private int[] elementos;
    private int tamanhoAtual;
    private static final int CAPACIDADE_INICIAL = 10000;

    public Vetor() {
        this.elementos = new int[CAPACIDADE_INICIAL];
        this.tamanhoAtual = 0;
    }

    public int getTamanhoAtual() {
        return this.tamanhoAtual;
    }
    
    private void redimensionar() {
        if (this.tamanhoAtual == this.elementos.length) {
            int novaCapacidade = this.elementos.length * 2;
            int[] novoArray = new int[novaCapacidade];
            
            for (int i = 0; i < this.elementos.length; i++) {
                novoArray[i] = this.elementos[i];
            }
            this.elementos = novoArray;
        }
    }

    public void inserir(int valor) {
        redimensionar();
        this.elementos[this.tamanhoAtual] = valor;
        this.tamanhoAtual++;
    }

  
    public boolean buscaSequencial(int chave) {
        for (int i = 0; i < this.tamanhoAtual; i++) {
            if (this.elementos[i] == chave) {
                return true;
            }
        }
        return false;
    }

    public int getElemento(int indice) {
        if (indice >= 0 && indice < this.tamanhoAtual) {
            return this.elementos[indice];
        }
        return -1;
    }

    public int[] getArray() {
        return this.elementos;
    }
}