import java.util.Arrays;
/**
 * Heap class that makes the implementation of HeapSort feasible. 
 * @author Emilio Lopez
 * @since Java 8
 * For EECS 233 Data Structures 
 */
public class Heap {

    //The fields are protected to make accessing them in HeapSort easier. 
    
    /** The number of items in the array. */
    protected int numItems;
    /** The max number of items possible in the array. */
    ;protected int maxItems;
    /** The array representation of the heap. */
    protected int[] items;
    
    /**
     * Creates a new Heap with initial size based on input. 
     * @param arr The array to be converted to a heap. 
     */
    public Heap(int[] arr){
        items = arr;
        maxItems = arr.length;
        numItems = arr.length;
        buildHeap();
    }
    
    public Heap(){
      maxItems = 0;
      numItems = 0;
      items = new int[0];
    }
    
    /**
     * Builds a Min-On-Top heap, sorting the array.
     */
    public void buildHeap() {
        for (int i = (numItems - 2) / 2; i >= 0; i--) {
            siftDown(i);
        }
    }

    public int removeMax() {
        int max = items[0];
        items[0] = items[numItems - 1];
        numItems--;
        siftDown(0);
        return max;
    }

    public void insert(int num) {
        System.out.println(numItems);
        System.out.println(maxItems);
        if (numItems == maxItems) {
            growArray();
        }
        items[numItems] = num;
        siftUp(numItems);
        numItems++;
    }

    private void growArray() {
        int[] temp = items;
        items = new int[items.length + 1];
        for (int i = 0; i < temp.length; i++){
          items[i] = temp[i];
        }
        maxItems = items.length;

    }
    
    /**
     * Sifts the heap node up in the heap if necessary. 
     * @param i The index of the heap element to be sorted. 
     */
    private void siftUp(int i) {

        int toSift = items[i];
        int child = i;
        int parent = (child == 1) ? 0:((child - 1) / 2); //Parent of the child 

        while (child > 0) {

            if (toSift < items[parent])
                break;
            

            items[child] = items[parent];
            items[parent] = toSift;
            child = parent;
            parent = (child == 1) ? 0:((child - 1) / 2);
        }
        items[child] = toSift;
    }

    /**
     * Sifts down the element if necessary.
     *
     * @param i The number to be sifted.
     */
    private void siftDown(int i) {

        int toSift = items[i];
        int parent = i;
        int child = 2 * parent + 1; //Child to compare with; start with left child

        while (child < numItems) {
            if (child + 1 < numItems && items[child] < items[child + 1])
                child += 1;            

            if (toSift >= items[child])
                break;
            
            items[parent] = items[child];
            items[child] = toSift;
            parent = child;
            child = 2 * parent + 1;

        }
        items[parent] = toSift;
        
    }

}
