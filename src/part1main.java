import java.io.*;  
import java.util.*; 

public class part1main {
    public static void main(String[] args) {

        // list of training data 
        ArrayList<Pair<int[],Integer>> trainlist = new ArrayList<Pair<int[],Integer>>();

        int kval = 0;
        
        try {
            Scanner inRead = new Scanner(System.in);

            // read in training set
            System.out.println("Enter data file: ");
            String filename = inRead.nextLine();

            // read in k value
            System.out.println("Enter k value: ");
            kval = inRead.nextInt();

            File dataset = new File(filename);
            inRead.close();

            // read training set  
            Scanner dataRead = new Scanner(dataset);
            while (dataRead.hasNextLine()) {
                String line = dataRead.nextLine();
                String[] separate = line.split(" ");
                int[] datavec = new int[separate.length - 1];
                for (int i = 0; i < separate.length - 1; ++i) {
                    datavec[i] = Integer.parseInt(separate[i]);
                }
                int tmp = Integer.parseInt(separate[separate.length-1]);

                // put (vector, label) pair into list
                trainlist.add(new Pair<int[],Integer>(datavec,tmp));
            }

            dataRead.close();
          } catch (FileNotFoundException e) {
            System.out.println("Can't find file.");
          }

          
    }

    public int distance() {
        return 0;
    }

    // read in training data and put it into an array?? or other data structure
    //read in value of k
    /* method to predict one test data label (one line):
        find distance between test point and all points in training array
            use dist method in a loop & store (label, dist) pairs in result array
        find the k lowest distances 
        return the majority label of the k lowest distances
            findmajority method
        https://www.geeksforgeeks.org/sort-an-array-of-pairs-using-java-pair-and-comparator/
        (resolve ties randomly) 

        ------

        compare this output with the output in the training array for this data point 
        add 1 to error count if wrong
        add 1 to overall count every time (unless we already have overall count)
        
    */


    static class Pair<K,V> {
        private K key;
        private V value; 
    
        public Pair (K key, V value) {
            this.key = key;
            this.value = value;
        }
    
        public K getKey() {
            return key;
        }
    
        public V getValue() {
            return value;
        }
    
        public void setKey(K key) {
            this.key = key;
        }
        
        public void setValue(V value) {
            this.value = value;
        }
    }


}



