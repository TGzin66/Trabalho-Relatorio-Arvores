public class AlgoritmosOrdenacao {

    public static void bubbleSort(int[] array, int n) {
        boolean houveTroca;

        for (int i = 0; i < n - 1; i++) {
            houveTroca = false;
            
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    houveTroca = true;
                }
            }

            if (!houveTroca) {
                break;
            }
        }
    }

    public static void quickSort(int[] array, int n) {
        quickSortRecursivo(array, 0, n - 1);
    }

    private static void quickSortRecursivo(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int indicePivo = partition(array, inicio, fim);
            
            quickSortRecursivo(array, inicio, indicePivo - 1);
            quickSortRecursivo(array, indicePivo + 1, fim);
        }
    }

    private static int partition(int[] array, int inicio, int fim) {
        int pivo = array[fim];
        int i = (inicio - 1); 

        for (int j = inicio; j < fim; j++) {
            
            if (array[j] <= pivo) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        
        int temp = array[i + 1];
        array[i + 1] = array[fim];
        array[fim] = temp;

        return i + 1;
    }
}