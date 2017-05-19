/**
 * Utility class that contains sorting methods.
 *
 * @author Emilio Lopez
 * @since Java 8 
 * For EECS 233 Data Structures
 */

import java.util.ArrayList;

public class Sorting {

    /**
     * Sorts the input array using the HeapSort algorithm
     *
     * @param arr The array to be sorted.
     * @return he number of milliseconds that elapsed to complete the method.
     */
    public static long heapSort(int[] arr) {
        long startTime = System.nanoTime(); //Stores the start time in nanoseconds 
        Heap heaper = new Heap(arr);
        int endUnsorted = heaper.numItems - 1;
        int largestRemaining;

        while (endUnsorted > 0) {
            largestRemaining = heaper.removeMax();
            arr[endUnsorted] = largestRemaining;
            heaper.items[endUnsorted] = largestRemaining;
            endUnsorted--;
        }
               
        long endTime = System.nanoTime(); //Stores the end time in milliseconds
        return endTime - startTime; //Returns the run time in millseconds. 
    }

    /**
     * Sorts the input array using the QuickSort algorithm.
     *
     * @param arr The array to be sorted.
     * @return The number of milliseconds that elapsed to complete the method.
     */
    public static long quickSort(int[] arr) {
        long startTime = System.nanoTime(); //Stores the start time in milliseconds
        myQuickSort(arr, 0, arr.length - 1);
        long endTime = System.nanoTime(); //Stores the end time in milliseconds
        return endTime - startTime; //Returns the run time in millseconds. 

    }

    /**
     * Recursive QuickSort.
     *
     * @param arr The array to be sorted.
     * @param first The first element in the array.
     * @param last The last element in the array.
     */
    private static void myQuickSort(int[] arr, int first, int last) {
        if (first >= last) {
            return;
        }
        
       int split = partition(arr, first, last);

        myQuickSort(arr, first, split);
        myQuickSort(arr, split + 1, last);
    }

    /**
     * Splits the array, compares, and swaps values that are greater than the
     * partition value.
     *
     * @param arr The sub-array being sorted
     * @param first The first element of the defined sub-array
     * @param last The last element of the defined sub-array
     * @return the end of the left partition of the sort
     */
    private static int partition(int[] arr, int first, int last) {
        
        int pivot = detSelectMedian(arr);
        int i = first - 1;
        int j = last + 1;

        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);
            //Increments i until a value greater than the pivot is found 

            do {
                j--;
            } while (arr[j] > pivot);
            //Decrements j until a value smaller than the pivot is found

            if (i < j) {
                swap(arr, i, j); //Swaps the elements in the array 
            } else {
                return j; //arr[j] is the end of the left array 
            }
        }
    }

    /**
     * Divides the array into smaller "subarrays" (constant space) and sorts the
     * array.
     *
     * @param arr The array to be sorted. 
     * @return The time in milliseconds to complete the sorting.
     */
    public static long mergeSort(int[] arr) {
        long startTime = System.nanoTime(); //Stores the start time in milliseconds
        
        int[] in = arr;
        int[] temp = new int[arr.length];
        int[] out = temp;
        
        int i;
        int j;
        int k;
        int l;
        int stride = 1;   //INcrease stride by power of two after each loop 

        while (stride < arr.length) {
            i = 0;
            while (i < arr.length) {
                

                if (i + stride >= arr.length)
                    k = arr.length - 1;
                else
                    k = i + stride;
                
                if (k - 1 >= arr.length)
                    j = arr.length -1;
                else
                    j = k-1;
                
                if (j + stride >= arr.length)
                    l = arr.length -1;
                else
                    l = j + stride; 
               
                
                merge(in, out, i, j, k, l);
                i = l + 1;
            }
            stride *= 2;
            if (out == temp){
                out = arr;
                in = temp;
            }else{
                in = arr;
                out = temp;
            }
        }
        
        if (out == arr){
            System.arraycopy(temp, 0, arr, 0, arr.length);
        }
        
        long endTime = System.nanoTime(); //Stores the end time in milliseconds
        return endTime - startTime; //Returns the run time in millseconds. 
    }

    
                                //HELPER METHODS//

    
    /**
     * Switches the elements of two indexes in an array.
     *
     * @param arr The corresponding array
     * @param i The first index to be swapped
     * @param j The second index to be swapped
     */
    private static void swap(int[] arr, int i, int j) {
        int swap1 = arr[i];
        int swap2 = arr[j];
        arr[i] = swap2;
        arr[j] = swap1;
    }

    /**
     * Helper method to sort values from one array into another.
     *
     * @param in Input array
     * @param out Output array
     * @param leftStart First left "subarray" index
     * @param leftEnd Last left "subarray" index
     * @param rightStart First right "subarray" index
     * @param rightEnd Last right "subarray" index
     */
    public static void merge(int[] in, int[] out, int leftStart, int leftEnd,
            int rightStart, int rightEnd) {

        int a = leftStart; //index into left subarray
        int b = rightStart; //index into right subarray
        int c = leftStart; // index into temp 

        while (a <= leftEnd && b <= rightEnd) { //Merges until one array has ended
            if (in[a] < in[b]) {
                out[c] = in[a];
                a++;
                c++;
            } else {
                out[c] = in[b];
                b++;
                c++;
            }
        }

        if (a <= leftEnd) { //Copies all the remaining items into the array 
            while (a <= leftEnd) {
                out[c] = in[a];
                a++;
                c++;
            }
        }

        if (b <= rightEnd) { //Copies all the remaining items into the array 
            while (b <= rightEnd) {
                out[c] = in[b];
                b++;
                c++;
            }
        }
    }

    /**
     * Implements the Deterministic Selection algorithm to aid in generating a median for an ideal pivot for QuickSort
     * @param arr the array to be sorted and from which the median will be created
     * @return the median element
     */
    private static int detSelectMedian(int[] arr)
    {
        ArrayList<Integer> babyMedians = new ArrayList<>();
        int medianGroups = (int)Math.ceil((double)(arr.length / 5));  // Calculates the baby median group numbers
        int lastGSize = medianGroups - arr.length;

        if (arr.length == 1)
            return arr[0];


        // G_0, G_1, ... G_g-2 : 5 elements
        for (int i = 0; i < medianGroups - 1; i++)
        {
            ArrayList<Integer> group = new ArrayList<>();
            int start = i * 5;    // indices of (i * 5) through (i * 5) + 4

            // Creates the group
            for (int j = start; j < start + 4; j++)
                group.add(arr[j]);

            group.sort(null);  // (Theta)(5log5) => (Theta)(1)

            babyMedians.add(group.get(5/2));   // median of group
        }
//        System.gc();

        // G_g-1 : lastGSize elements
        if (lastGSize == 1)
            babyMedians.add(arr[arr.length - 1]);
        else if (lastGSize == 2)
            if (arr[arr.length - 2] < arr[arr.length -1])
                babyMedians.add(arr[arr.length - 2]);
            else
                babyMedians.add(arr[arr.length - 1]);
        // Remaining group has more than 2 elements
        else{
            ArrayList<Integer> last = new ArrayList<>();
            for (int i = medianGroups - 1; i < arr.length; i++)
                last.add(arr[i]);

            last.sort(null);
            babyMedians.add(last.get(last.size()/2));
        }

        babyMedians.sort(null);
        return babyMedians.get(babyMedians.size()/2);
    }
    
    

}
