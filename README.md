Análise de Desempenho de Estruturas de Dados em Java (Trabalho Final)
Este projeto tem como objetivo comparar o desempenho das estruturas de dados Vetor, Árvore Binária de Busca (ABB) e Árvore AVL em operações de inserção e busca, 
utilizando diferentes cenários de ordem de dados (Ordenado, Inverso e Aleatório) e tamanhos (N=100, 1.000, 10.000)

Estrutura de Arquivos e Pastas
O projeto é composto por sete arquivos .java, organizados na raiz do diretório


Raiz/
├── AlgoritmosOrdenacao.java      (QuickSort e Bubble Sort)
├── ArvoreABB.java                (Implementação da ABB)
├── ArvoreAVL.java                (Implementação da AVL com rotações)
├── NoABB.java                    (Classe de suporte para nós da ABB)
├── NoAVL.java                    (Classe de suporte para nós da AVL)
├── Vetor.java                    (Implementação do Vetor Dinâmico, Busca Sequencial e Binária)
├── TesteDesempenho.java          (Classe Principal: Geração de cenários, medição de tempo e saída)


Como Compilar e Executar o Projeto
O projeto não utiliza bibliotecas externas, apenas o Java padrão

Pré-requisito
Java Development Kit (JDK): Versão 8 ou superior instalada e configurada (javac e java devem estar acessíveis no terminal/prompt de comando)

1. Compilação (Usando Terminal)
Navegue até o diretório raiz e compile todos os arquivos .java de uma só vez:
exemplo:

cd src/
javac *.java

Isso gerará os arquivos .class correspondentes para todas as classes do projeto

2. Execução (Usando Terminal)
Após a compilação, execute a classe principal TesteDesempenho:
java TesteDesempenho

3. Saída
O programa exibirá na saída padrão (console) uma tabela formatada contendo os tempos de execução (em milissegundos) para as 9 operações e os 2 algoritmos de ordenação,
cobrindo todos os tamanhos (N) e cenários (Ordenado, Inverso, Aleatório).

--- Relatório de Desempenho de Estruturas de Dados e Algoritmos ---
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Tamanho    | Cenario    | Vetor Ins. | ABB Ins.   | AVL Ins.   | B. Sequencial| B. Binária | ABB Busca  | AVL Busca  | Bubble Sort| QuickSort  
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
100        | Ordenado   | <0.1ms     | <0.1ms     | <0.1ms     | <0.1ms       | <0.1ms     | <0.1ms     | <0.1ms     | <0.1ms     | <0.1ms     

Detalhes da Implementação:
Estrutura / Algoritmo	Implementação	Complexidade Teórica (Pior Caso)
Vetor InserçãoArray Dinâmico O(n) (no redimensionamento)
ABB Inserção/BuscaRecursiva O(n) (desbalanceada)
AVL Inserção/BuscaRecursiva com Rotações$ (log n) (balanceada)
Busca SequencialIterativa O(n)
Busca BináriaIterativa O(log n)
Bubble SortSimples O(n^2)
QuickSortRecursivo (Partition) O(n^2)


As medições são realizadas usando System.nanoTime() para garantir a maior precisão possível
Os métodos de inserção e busca são implementados do zero, sem o uso de classes Java prontas (ArrayList, TreeMap, Arrays.sort, etc.)
