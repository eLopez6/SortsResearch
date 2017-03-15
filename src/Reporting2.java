import java.io.*;

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

        String path = System.getProperty("user.home") + "\\Desktop";    // Finds desktop path of user

        String input = args[0]; //Input text file path.
        String hsOut = path + "\\eil11HS.txt";
        String qsOut = path + "\\eil11QS.txt";
        String msOut = path + "\\eil11MS.txt";
        
        sortFile(input, hsOut, 0);
        sortFile(input, qsOut, 1);
        sortFile(input, msOut, 2);
    }
    
    /**
     * Reads an input text file, populates an array, sorts the array, and writes 
     * the sorted array into a text file. 
     * @param input Input filename
     * @param output Output filename 
     * @param sortType Number representation of each sort: 0=HS, 1=QS, 2=MS
     * @throws IOException If a file input error occurs
     */
    private static void sortFile(String input, String output, int sortType) throws IOException {
        String caseId = "eil11";
        BufferedReader br = new BufferedReader(new FileReader(input));
        int[] arr;
        int lineCounter = 0;
        
        /* Counts the number of lines (and therefore, elements) in the input*/
        do {
            lineCounter++;
        } while (br.readLine() != null);
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


        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(output)));
        String outline = sort + caseId + ": " + time + "ns\n";
        pw.print("");
        pw.println(outline);

        for (int num : arr)
            pw.println(num);

        pw.close();
        System.out.println(outline);

    }
    
}
