import java.io.*;
import java.util.*;

public class part1main {
    public static void main(String[] args) {

        // list of training data
        ArrayList<Pair<int[], Integer>> trainlist = new ArrayList<Pair<int[], Integer>>();

        int kval = 0;
        String testfile = "";
        int size = 0;
        int errorcnt = 0;
        double error = 0;

        try {
            Scanner inRead = new Scanner(System.in);

            // read in training set
            System.out.println("Enter training set: ");
            String filename = inRead.nextLine();

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
                int tmp = Integer.parseInt(separate[separate.length - 1]);

                // put (vector, label) pair into list
                trainlist.add(new Pair<int[], Integer>(datavec, tmp));
            }

            dataRead.close();
            Scanner inRead2 = new Scanner(System.in);

            while (true) {
                size = 0;
                errorcnt = 0;

                // read in test file path               
                System.out.println("Enter test file: ");
                testfile = inRead2.nextLine();
                File toTest = new File(testfile);

                // read in k value
                System.out.println("Enter k value: ");
                kval = inRead2.nextInt();
                inRead2.nextLine();
                

                Scanner testRead = new Scanner(toTest);
                while(testRead.hasNextLine()) {
                    size++;

                    // obtain a data (vector) point from the test data
                    String line = testRead.nextLine();
                    String[] separate = line.split(" ");
                    int[] datavec = new int[separate.length - 1];
                    for (int i = 0; i < separate.length - 1; ++i) {
                        datavec[i] = Integer.parseInt(separate[i]);
                    }

                    // the correct label for the data (vector) point
                    int truLabel = Integer.parseInt(separate[separate.length - 1]);

                    // attempt to predict the label of the point
                    int predictedLabel = predict(datavec);

                    if (predictedLabel != truLabel) {
                        errorcnt++;
                    }
                }

                testRead.close();

                // calculate error and tell user
                error = (double)errorcnt / size;
                System.out.println("Given the file: " + testfile + " and a k-value of: " + kval + ", the error was: " + error);

                System.out.println("Go again? (y/n)"); 
                if (!inRead2.nextLine().equals("y")) {
                    break;
                }
            }
            inRead2.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file.");
        }
        

    }

    public int distance() {
        return 0;
    }

    // read in training data and put it into an array?? or other data structure
    // read in value of k
    /*
     * method to predict one test data label (one line): 
     *      find distance between test point and all points in training array 
     *      use dist method in a loop & store (label, dist) pairs in result array 
     *      find the k lowest distances return the
     * majority label of the k lowest distances findmajority method
     * https://www.geeksforgeeks.org/sort-an-array-of-pairs-using-java-pair-and-
     * comparator/ (resolve ties randomly)
     * 
     * ------
     * 
     * compare this output with the output in the training array for this data point
     * add 1 to error count if wrong add 1 to overall count every time (unless we
     * already have overall count)
     * 
     */

    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
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
