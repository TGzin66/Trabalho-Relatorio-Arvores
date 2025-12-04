public class ArvoreBinariaBusca {
    private NoABB raiz;
    private int tamanho;

    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.tamanho = 0;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public void inserir(int valor) {
        this.raiz = inserirRecursivo(this.raiz, valor);
        this.tamanho++;
    }

    private NoABB inserirRecursivo(NoABB atual, int valor) {

        if (atual == null) {
            return new NoABB(valor);
        }
        if (valor < atual.valor) {
            atual.esquerda = inserirRecursivo(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = inserirRecursivo(atual.direita, valor);
        } else {
            return atual;
        }
        return atual;
    }

    public boolean buscar(int chave) {
        return buscarRecursivo(this.raiz, chave);
    }

    private boolean buscarRecursivo(NoABB atual, int chave) {

        if (atual == null) {
            return false;
        }
        if (chave == atual.valor) {
            return true;
        }
        if (chave < atual.valor) {
            return buscarRecursivo(atual.esquerda, chave);
        } else {
            return buscarRecursivo(atual.direita, chave);
        }
    }
}