public class ArvoreAVL {
    private NoAVL raiz;
    private int tamanho;

    public ArvoreAVL() {
        this.raiz = null;
        this.tamanho = 0;
    }

    private int altura(NoAVL no) {
        return (no == null) ? 0 : no.altura;
    }

    private void atualizarAltura(NoAVL no) {
        if (no != null) {
            no.altura = 1 + max(altura(no.esquerda), altura(no.direita));
        }
    }

    private int fatorBalanceamento(NoAVL no) {
        return (no == null) ? 0 : altura(no.esquerda) - altura(no.direita);
    }


    private int max(int a, int b) {
        return (a > b) ? a : b;
    }


    private NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esquerda;
        NoAVL T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    private NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.direita;
        NoAVL T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    private NoAVL rotacaoEsquerdaDireita(NoAVL no) {
        no.esquerda = rotacaoEsquerda(no.esquerda);
        return rotacaoDireita(no);
    }

    private NoAVL rotacaoDireitaEsquerda(NoAVL no) {
        no.direita = rotacaoDireita(no.direita);
        return rotacaoEsquerda(no);
    }

    public void inserir(int valor) {
        this.raiz = inserirRecursivo(this.raiz, valor);
    }

    private NoAVL inserirRecursivo(NoAVL no, int valor) {

        if (no == null) {
            this.tamanho++;
            return new NoAVL(valor);
        }

        if (valor < no.valor) {
            no.esquerda = inserirRecursivo(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserirRecursivo(no.direita, valor);
        } else {
            return no;
        }
        atualizarAltura(no);

        int fb = fatorBalanceamento(no);

        if (fb > 1 && valor < no.esquerda.valor) {
            return rotacaoDireita(no);
        }
        if (fb < -1 && valor > no.direita.valor) {
            return rotacaoEsquerda(no);
        }
        if (fb > 1 && valor > no.esquerda.valor) {
            return rotacaoEsquerdaDireita(no);
        }
        if (fb < -1 && valor < no.direita.valor) {
            return rotacaoDireitaEsquerda(no);
        }
        return no;
    }

    public boolean buscar(int chave) {
        return buscarRecursivo(this.raiz, chave);
    }

    private boolean buscarRecursivo(NoAVL atual, int chave) {

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