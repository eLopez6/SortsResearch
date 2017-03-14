
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Emilio Lopez
 * @since Java 8 
 * For EECS 233 Data Structures
 * eil11
 */
public class Reporting2 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException invalid input
     */
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) { //Verifies that two file paths are input.
            System.out.println("Only one argument may be input.");
            return;
        }

        String input = args[0]; //Input text file path.
        String hsOut = "C:\\Users\\ei_lo\\Desktop\\eil11HS.txt";
        String qsOut = "C:\\Users\\ei_lo\\Desktop\\eil11QS.txt";
        String msOut = "C:\\Users\\ei_lo\\Desktop\\eil11MS.txt";
        
        readFile(input, hsOut, 0);
        readFile(input, qsOut, 1);
        readFile(input, msOut, 2);       
        
    }
    
    
    /**
     * Reads an input text file, populates an array, sorts the array, and writes 
     * the sorted array into a text file. 
     * @param input Input filename
     * @param output Output filename 
     * @param sortType Number representation of each sort: 0=HS, 1=QS, 2=MS
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private static void readFile(String input, String output, int sortType) throws FileNotFoundException, IOException {
        String caseId = "eil11";
        BufferedReader br = new BufferedReader(new FileReader(input));
        ArrayList<Integer> al = new ArrayList<>();
        int[] arr;
        int lineCounter = 0;
        
        /* Counts the number of lines (and therefore, elements) in the input*/
        do {
            lineCounter++;
        }while(br.readLine() != null);
        arr = new int[lineCounter];        
        
        /* Fills the array based on the int at each line.*/
        BufferedReader qr = new BufferedReader(new FileReader(input));
        for (int i = 0; i < arr.length; i++) {
            String rLine = qr.readLine();
            if (rLine != null)
                arr[i] = Integer.parseInt(rLine);
            else
                break;
        }
        
        long time = 0;
        String sort = "";
        if (sortType == 0) {
            time = Sorting.heapSort(arr);
            sort = "HS ";
        }

        if (sortType == 1) {
            time = Sorting.quickSort(arr);
            sort = "QS ";
        }

        if (sortType == 2) {
            time = Sorting.mergeSort(arr);
            sort = "MS ";
        }
        
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(output));
        PrintWriter pw = new PrintWriter(bw);
        String outline = sort + caseId + ": " + time + "ms";
        pw.print("");
        pw.print(outline);
        
        
        for (int num : arr){
            pw.print(num);
            pw.println();
        }
        
        pw.close();
        System.out.println(outline);

    }
    
    
    
    
}
