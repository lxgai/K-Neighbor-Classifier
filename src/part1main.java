import java.io.*;
import java.util.*;
import java.lang.*;

public class part1main {
    public static void main(String[] args) {

        // list of training data
        ArrayList<Pair<int[], Integer>> trainlist = new ArrayList<Pair<int[], Integer>>();

        // other elements
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
            

            // read training set
            Scanner dataRead = new Scanner(dataset);
            while (dataRead.hasNextLine()) {

                // obtain data (vector) point
                String line = dataRead.nextLine();
                String[] separate = line.split(" ");
                int[] datavec = new int[separate.length - 1];
                for (int i = 0; i < separate.length - 1; ++i) {
                    datavec[i] = Integer.parseInt(separate[i]);
                }

                // obtain label
                int tmp = Integer.parseInt(separate[separate.length - 1]);

                // put (vector, label) pair into list
                trainlist.add(new Pair<int[], Integer>(datavec, tmp));

                // PRINT TRAINING DATA
                //System.out.println("Training data: ");
                //for (int i = 0; i < trainlist.size(); i++) {
                   // System.out.println("Vector: " + trainlist.get(i).getKey() + " -- Label: " + trainlist.get(i).getValue() );

                //}
            }

            dataRead.close();
            
            
            while (true) {
                size = 0;
                errorcnt = 0;

                // read in test file path     
                          
                System.out.println("Enter test file: ");
                testfile = inRead.nextLine();
                File toTest = new File(testfile);

                // read in k value
                System.out.println("Enter k value: ");
                kval = inRead.nextInt();
                inRead.nextLine();
                

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
                    int predictedLabel = predict(datavec, trainlist, kval);

                    if (predictedLabel != truLabel) {
                        errorcnt++;
                    }
                }

                testRead.close();

                // calculate error and tell user
                error = (double)errorcnt / size;
                System.out.println("Given the file: " + testfile + " and a k-value of: " + kval + ", the error was: " + error);

                System.out.println("Go again? (y/n)"); 
                if (!inRead.nextLine().equals("y")) {
                    inRead.close();
                    
                    break;
                }
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file.");
        }
        

    }

    public static int predict(int[] vec, ArrayList<Pair<int[],Integer>> trainlist, int kval) {
        
        // store (dist, label) pairs 
        ArrayList<Pair<Double,Integer>> distResults = new ArrayList<Pair<Double,Integer>>();

        // for each point of the training data, find its dist between test data
        for (int i = 0; i < trainlist.size(); ++i) {
            double dis = dist(trainlist.get(i).getKey(), vec);
            // the pair is (DIST between test and training point, LABEL of the training data point)
            distResults.add(new Pair<Double,Integer>(dis, trainlist.get(i).getValue()));
        }

        return findMajority(distResults, kval);
    }

    public static int findMajority(ArrayList<Pair<Double,Integer>> distResults, int kval) {
        // to update dist counts and get majority
        int[] arr = new int[10];

        // sort the (dist, label) pairs by increasing distance
        Collections.sort(distResults, new Comparator<Pair<Double, Integer>>() {
            @Override
            public int compare(final Pair<Double, Integer> one, final Pair<Double, Integer> two) {
                double tmp = one.getKey() - two.getKey();
                if (tmp == 0) {
                    return 0;
                }
                else if (tmp < 0) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
        });

        // obtain the k-lowest dist counts and their label
        for (int i = 0; i < kval; ++i) {
            arr[distResults.get(i).getValue()]++;
        }
        int maj = 0;
        int majlabel = 0;
        for (int i = 0; i < 10; ++i) {
            // if the ith label has more than the current majority label, update
            if (arr[i] > maj) {
                maj = arr[i];
                majlabel = i;
            }
            // if equal, break tie randomly
            else if (arr[i] == maj) {
                maj = new Random().nextBoolean() ? arr[i] : maj;
            }
        }

        return majlabel;
    }

    public static double dist(int[] one, int[] two) {
        double sum = 0;
        
        for (int i = 0; i < one.length; ++i) {
            sum = sum + Math.pow( (one[i] - two[i]), 2 );
        }
        return Math.sqrt(sum);
    }

    // read in training data and put it into an array?? or other data structure
    // read in value of k
    /*
     * method to predict one test data label (one line): 
     *      find distance between test point and all points in training array 
     *      use dist method in a loop & store (dist, label) pairs in result array 
     *      find the k lowest distances return the
     * majority label of the k lowest distances findmajority method
     * https://www.geeksforgeeks.org/sort-an-array-of-pairs-using-java-pair-and-comparator/ (resolve ties randomly)
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
