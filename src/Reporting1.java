import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * @author Emilio Lopez
 * @since Java 8 
 * For EECS 233 Data Structures
 */
public class Reporting1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                                
        int[] sizes = {1000, 10000, 100000, 1000000}; //Test sizes
        long[] trys = new long[10];
        
        //HeapSort
        System.out.println("HeapSort");
        for (int size : sizes)
            hsRandomTest(size, trys);
        
        System.out.println('\n');
        for (int size : sizes) 
            hsSortedTest(size);
        
        System.out.println('\n');
        for (int size : sizes) 
            hsReverseTest(size);
        
        
        //QuickSort
        System.out.println('\n');
        System.out.println("QuickSort");
        for (int size : sizes)
            qsRandomTest(size, trys);
        
        System.out.println('\n');
        for (int size : sizes) 
            qsSortedTest(size);
        
        System.out.println('\n');
        for (int size : sizes) 
            qsReverseTest(size);
            
        System.out.println('\n');
        
        // MergeSort
        trys = new long[10];
        System.out.println("MergeSort");
        for (int size : sizes) 
            msRandomTest(size, trys);
        
        System.out.println('\n');
        for (int size : sizes) 
            msSortedTest(size);
        
        System.out.println('\n');
        for (int size : sizes) 
            msReverseTest(size);
                 
    }
    
    /**
     * Creates an array of random values, which can be controlled by the 
     * parameters.
     * @param length The length of the array
     * @return An unsorted array. 
     */
    private static int[] createRandomArray(int length) {

        int[] randomArr = new int[length];
        Random randomNumber = new Random();       
        int randInt;

        for (int i = 0; i < length; i++) {
            randInt = randomNumber.nextInt(100);
            if (randInt < 0)
                randInt = 0;

            randomArr[i] = randInt;
        }
        return randomArr;
    }

    /**
     * Creates a pre-sorted array, which can be controlled by the parameters. 
     * @param length The length of the array
     * @return The pre-sorted array. 
     */
    private static int[] createSortedArray(int length) {
        
        int[] primSort = createRandomArray(length);
        Integer[] sortArr = new Integer[length];
        
        for (int i = 0; i < length; i++)
            sortArr[i] = primSort[i];
        
        Arrays.sort(sortArr);
        
        for (int i = 0; i < length; i++)
            primSort[i] = sortArr[i];
        
        return primSort;
    }

    /**
     * Creates a reverse-sorted array, which can be controlled by the parameters
     * @param length The length of the array
     * @return The reverse-sorted array. 
     */
    private static int[] createReverseArray(int length) {

        int[] primSort = createRandomArray(length);
        Integer[] reverseArr = new Integer[length];
        
        for (int i = 0; i < length; i++) 
            reverseArr[i] = primSort[i];
        
        Arrays.sort(reverseArr, Collections.reverseOrder());
        
        for (int i = 0; i < length; i++)
            primSort[i] = reverseArr[i];
        
        return primSort;
    }
    
    /**
     * Finds the mean value of an array.
     * @return The mean value of the array. 
     */
    private static double meanVal(long[] samples){
        int mean = 0; 
        for (long num : samples)
            mean += num;
        
        mean /= samples.length; 
        return mean;       
    }
    
    /**
     * Gets the Variance of an array based on the mean.
     * @param mean The mean of the array 
     * @return The variance of the array 
     */
    private static double getVariance(long[] samples, double mean) {
        double variance = 0;
        for (long num : samples) 
            variance += (mean - num) * (mean - num);
        
        variance /= samples.length;
        return variance;
    }
    
    /**
     * HeapSort on a random array. 
     * @param size The size of the array in the test The size of the array in the test 
     * @param trys The array that stores the runtime of each sort
     */
    private static void hsRandomTest(int size, long[] trys){
        double mean;
        for (int i = 0; i < 10; i++){
            int[] temp = createRandomArray(size);
            trys[i] = Sorting.heapSort(temp);
        }
        mean = meanVal(trys);
        double variance = getVariance(trys, mean);
        System.out.println("Random " + size + ", Mean Runtime: " + mean + " Variance: " + variance);
    }
    
    /**
     * HeapSort on a sorted array
     * @param size The size of the array in the test 
     */
    private static void hsSortedTest(int size){
        long median;
        long[] runs = new long[3];
        
        for (int i = 0; i < 3; i++){
            int[] temp = createSortedArray(size);
            runs[i] = Sorting.heapSort(temp);
        }
        median = getMedianOfThree(runs);
        System.out.println("Sorted: " + size + " Median Runtime: " + median);
    }
    
    /**
     * HeapSort on a reversed sorted array
     * @param size The size of the array in the test The size of the array in the test 
     */
    private static void hsReverseTest(int size){
        long median;
        long[] runs = new long[3];
        
        for (int i = 0; i < 3; i++) {
            int[] temp = createReverseArray(size);
            runs[i] = Sorting.heapSort(temp);
        }
        median = getMedianOfThree(runs);
        System.out.println("Reversed: " + size + " Median Runtime: " + median);
        
    }
    
    
    /**
     * QuickSort on a random array. 
     * @param size The size of the array in the test The size of the array in the test in the test 
     * @param trys The array that stores the runtime of each sort 
     */
    private static void qsRandomTest(int size, long[] trys){
        double mean;
        for (int i = 0; i < 10; i++){
            int[] temp = createRandomArray(size);
            trys[i] = Sorting.quickSort(temp);
        }
        mean = meanVal(trys);
        double variance = getVariance(trys, mean);
        System.out.println("Random " + size + ", Mean Runtime: " + mean + " Variance: " + variance);
    }
    
    /**
     * QuickSort on a sorted array
     * @param size The size of the array in the test The size of the array in the test in the test
     */
    private static void qsSortedTest(int size){
        long median;
        long[] runs = new long[3];
        
        for (int i = 0; i < 3; i++){
            int[] temp = createSortedArray(size);
            runs[i] = Sorting.quickSort(temp);
        }
        median = getMedianOfThree(runs);
        System.out.println("Sorted: " + size + " Median Runtime: " + median);
    }
    
    /**
     * QuickSort on a reversed sorted array
     * @param size The size of the array in the test The size of the array in the test in the test
     */
    private static void qsReverseTest(int size){
        long median;
        long[] runs = new long[3];
        
        for (int i = 0; i < 3; i++) {
            int[] temp = createReverseArray(size);
            runs[i] = Sorting.quickSort(temp);
            
        }
        median = getMedianOfThree(runs);
        System.out.println("Reversed: " + size + " Median Runtime: " + median);
    }
    
    /**
     * MergeSort on a random array. 
     * @param size The size of the array in the test The size of the array in the test 
     * @param trys The array that stores the runtime of each sort
     */
    private static void msRandomTest(int size, long[] trys){
        double mean;
        for (int i = 0; i < 10; i++){
            int[] temp = createRandomArray(size);
            trys[i] = Sorting.mergeSort(temp); 
        }
        mean = meanVal(trys);
        double variance = getVariance(trys, mean);
        System.out.println("Random " + size + ", Mean Runtime: " + mean + " Variance: " + variance);
    }
    
    /**
     * MergeSort on a sorted array
     * @param size The size of the array in the test The size of the array in the test in the test
     */
    private static void msSortedTest(int size){
        long median;
        long[] runs = new long[3];
        
        for (int i = 0; i < 3; i++){
            int[] temp = createSortedArray(size);
            runs[i] = Sorting.mergeSort(temp);
        }
        median = getMedianOfThree(runs);
        System.out.println("Sorted: " + size + " Median Runtime: " + median);
    }
    
    /**
     * MergeSort on a reversed sorted array
     * @param size The size of the array in the test The size of the array in the test in the test
     */
    private static void msReverseTest(int size){
        long median;
        long[] runs = new long[3];
        
        for (int i = 0; i < 3; i++) { 
            int[] temp = createReverseArray(size);
            runs[i] = Sorting.mergeSort(temp);
        }
        median = getMedianOfThree(runs);
        System.out.println("Reversed: " + size + " Median Runtime: " + median);
    }    
    
    
    /**
     * Find the median long value of the sorts.
     * @param arr The array of sort times. 
     * @return The median 
     */
    private static long getMedianOfThree(long[] arr) {
        long a = arr[0];
        long b = arr[1];
        long c = arr[2];

        if (a > b) {
            if (b > c) {
                return b;
            } else if (a > c) {
                return c;
            } else {
                return a;
            }
        } else if (a > c) {
            return a;
        } else if (b > c) {
            return c;
        } else {
            return b;
        }

    }

}
