public class part1main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    public int distance() {
        return 0;
    }

    // read in training data and put it into an array?? or other data structure
    //read in value of k
    /* method to predict one test data label (one line):
        find distance between test point and all points in training array
            use dist method in a loop & store (dist, label) pairs in result array
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
}
