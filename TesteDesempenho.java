import java.util.Arrays;
import java.util.Random;

public class TesteDesempenho {
    private static final int[] TAMANHOS = {100, 1000, 10000};
    private static final int ITERACOES_BUSCA = 1000;
    private static final String[] CENARIOS = {"Ordenado", "Inverso", "Aleatorio"};

    public static void main(String[] args) {
        System.out.println("--- Relatorio de desempenho de estruturas de dados e algoritmos ---");
        System.out.println("\nMedicoes em milissegundos (ms). Buscas repetidas " + ITERACOES_BUSCA + " vezes.");
        System.out.println("\nins = insercao, seq = sequencial,");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-10s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s\n", 
            "Tamanho", "Cenario", "Vetor ins", "ABB ins", "AVL ins", 
            "Busca seq", "Busca binaria", "ABB busca", "AVL busca", 
            "BubbleSort", "QuickSort");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (int n : TAMANHOS) {
            executarTestesCompleto(n);
        }
    }
    
    private static void executarTestesCompleto(int n) {
        for (String cenario : CENARIOS) {

            int[] dadosOriginais = gerarDados(n, cenario);
            
            long tVetorIns = medirInsercao(new Vetor(), dadosOriginais, n);
            long tABBIns = medirInsercao(new ArvoreBinariaBusca(), dadosOriginais, n);
            long tAVLIns = medirInsercao(new ArvoreAVL(), dadosOriginais, n);

            Vetor vetorBusca = construirVetor(dadosOriginais, n);
            ArvoreBinariaBusca abbBusca = construirABB(dadosOriginais, n);
            ArvoreAVL avlBusca = construirAVL(dadosOriginais, n);
            
            long tBuscaSeq = medirBuscaVetor(vetorBusca, dadosOriginais, "sequencial");
            
            AlgoritmosOrdenacao.quickSort(vetorBusca.getArray(), n); 
            long tBuscaBin = medirBuscaVetor(vetorBusca, dadosOriginais, "binaria");

            long tABBBusca = medirBuscaABB(abbBusca, dadosOriginais);
            long tAVLBusca = medirBuscaAVL(avlBusca, dadosOriginais);
            
            
            int[] dadosBubble = Arrays.copyOf(dadosOriginais, n);
            long tBubbleSort = medirOrdenacao(dadosBubble, n, "bubble");

            int[] dadosQuick = Arrays.copyOf(dadosOriginais, n);
            long tQuickSort = medirOrdenacao(dadosQuick, n, "quick");

            System.out.printf("%-10d | %-10s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s | %-12s\n", 
                n, 
                cenario, 
                formatarTempo(tVetorIns),
                formatarTempo(tABBIns),
                formatarTempo(tAVLIns),
                formatarTempo(tBuscaSeq),
                formatarTempo(tBuscaBin),
                formatarTempo(tABBBusca),
                formatarTempo(tAVLBusca),
                formatarTempo(tBubbleSort),
                formatarTempo(tQuickSort)
            );
        }
    }
    
    private static int[] gerarDados(int tamanho, String ordem) {
        int[] dados = new int[tamanho];
        Random rand = new Random();

        for (int i = 0; i < tamanho; i++) {
            switch (ordem) {
                case "Ordenado":
                    dados[i] = i;
                    break;
                case "Inverso":
                    dados[i] = tamanho - 1 - i;
                    break;
                default:
                    dados[i] = rand.nextInt(tamanho * 10);
                    break;
            }
        }
        return dados;
    }

    private static Vetor construirVetor(int[] dados, int n) {
        Vetor v = new Vetor();
        for (int valor : dados) v.inserir(valor);
        return v;
    }

    private static ArvoreBinariaBusca construirABB(int[] dados, int n) {
        ArvoreBinariaBusca abb = new ArvoreBinariaBusca();
        for (int valor : dados) abb.inserir(valor);
        return abb;
    }

    private static ArvoreAVL construirAVL(int[] dados, int n) {
        ArvoreAVL avl = new ArvoreAVL();
        for (int valor : dados) avl.inserir(valor);
        return avl;
    }

    private static long medirInsercao(Object estrutura, int[] dados, int n) {
        long inicio = System.nanoTime();
        switch (estrutura) {
            case Vetor v -> {
                for (int valor : dados) v.inserir(valor);
            }
            case ArvoreBinariaBusca abb -> {
                for (int valor : dados) abb.inserir(valor);
            }
            case ArvoreAVL avl -> {
                for (int valor : dados) avl.inserir(valor);
            }
            default -> {
            }
        }
        return System.nanoTime() - inicio;
    }

    private static long medirBuscaVetor(Vetor vetor, int[] dadosOriginais, String tipo) {
        Random rand = new Random();
        long inicio = System.nanoTime();

        for (int i = 0; i < ITERACOES_BUSCA; i++) {
            int chaveExistente = dadosOriginais[rand.nextInt(dadosOriginais.length)];
            
            if (tipo.equals("sequencial")) {
                vetor.buscaSequencial(chaveExistente);
            } else {
                vetor.buscaBinaria(chaveExistente);
            }
        }
        return System.nanoTime() - inicio;
    }

    private static long medirBuscaABB(ArvoreBinariaBusca abb, int[] dadosOriginais) {
        Random rand = new Random();
        long inicio = System.nanoTime();
        for (int i = 0; i < ITERACOES_BUSCA; i++) {
            abb.buscar(dadosOriginais[rand.nextInt(dadosOriginais.length)]);
        }
        return System.nanoTime() - inicio;
    }

    private static long medirBuscaAVL(ArvoreAVL avl, int[] dadosOriginais) {
        Random rand = new Random();
        long inicio = System.nanoTime();
        for (int i = 0; i < ITERACOES_BUSCA; i++) {
            avl.buscar(dadosOriginais[rand.nextInt(dadosOriginais.length)]);
        }
        return System.nanoTime() - inicio;
    }
    
    private static long medirOrdenacao(int[] dados, int n, String tipo) {
        long inicio = System.nanoTime();
        if (tipo.equals("bubble")) {
            AlgoritmosOrdenacao.bubbleSort(dados, n);
        } else {
            AlgoritmosOrdenacao.quickSort(dados, n);
        }
        return System.nanoTime() - inicio;
    }
    
    private static String formatarTempo(long nanosegundos) {
        long milissegundos = nanosegundos / 1000000;
        if (milissegundos < 1) {
            return "<1ms";
        }
        return milissegundos + "ms";
    }
}