import java.util.*;
import com.javamex.classmexer.MemoryUtil;

// switched time to nano seconds
public class Project2Experiment{
    private final long start;

    /**
     * Initializes a new stopwatch.
     */
    public Project2Experiment() {
        start = System.nanoTime(); //stopwatch
    } 


    /**
     * Returns the elapsed CPU time (in seconds) since the stopwatch was created.
     *
     * @return elapsed CPU time (in seconds) since the stopwatch was created
     */
    public double elapsedTime() {
        long now = System.nanoTime();
        return (now - start);
    }
    public static void main(String [] args){
        int n = 1000000;
        int m = 1000;
        int range = n*100; // change to 3 when super large nums, 100 for normal (below 1e8)

        int[] nData = generateNarray(n);
        int[] mTargets = generateTargets(m, range); 

        Arrays.sort(nData); //sorting n values works up to here

        long arrayMemory = MemoryUtil.deepMemoryUsageOf(nData);

        double totalLinearTime = 0.0;
        int LinearFound = 0;
        for (int target : mTargets){
            Project2Experiment timer = new Project2Experiment();
            int resultLinear = linearSearch(nData, target);
            totalLinearTime += timer.elapsedTime();

            if(resultLinear != -1){
                LinearFound++;
            }
        }

      
        double totalBinaryTime = 0.0;
        int BinaryFound = 0;
        for(int target2: mTargets){
            Project2Experiment timer2 = new Project2Experiment();
            int BinaryResult = binarySearch(nData, 0, n-1, target2);
            totalBinaryTime += timer2.elapsedTime();

            if(BinaryResult != -1){
                BinaryFound++;
            }

        }

        //Results
        System.out.println("N value" + n);
        System.out.println("Linear Search:");
        System.out.println("Total Time (ns): " + totalLinearTime);
        System.out.println("Average Time(ns): " + (totalLinearTime / m));
        System.out.println("Found: " + LinearFound);

        System.out.println();

        System.out.println("Binary Search:");
        System.out.println("Total Time(ns): " + totalBinaryTime);
        System.out.println("Average Time(ns): " + (totalBinaryTime / m));
        System.out.println("Found: " + BinaryFound);
        System.out.println("Memory used by nData array: " + arrayMemory + " bytes");

        
        //System.out.println(Arrays.toString(nData));
        //System.out.println(Arrays.toString(mTargets));


    }
    public static int[] generateNarray(int n){
        Random ranNums = new Random();

        // For n values below 1e8
        HashSet<Integer> nHashSet = new HashSet<>(n); // instantiating an object of Hashset for the size of n numbers

        // add random numbers to nHashSet  
        while (nHashSet.size() < n){
            int randomInt = ranNums.nextInt(n*100); // for n =10,000 use 1,000,000. need to use a value a lot bigger than n
            nHashSet.add(randomInt);
        }
        long setMemory = MemoryUtil.deepMemoryUsageOf(nHashSet);
        System.out.println("Memory used by HashSet: " + setMemory + " bytes");



        int[] arrayN = new int[n]; //array the size of n to convert the HashSet into
        int index = 0;
        //iterate through the hashset to add the values to an array
        for (int i : nHashSet){
            arrayN[index] = i;
            index++;
        }

        // When the n size is > 1e7 do not use a HashSet (heap will get full)
        // does not guarantee no duplicates like a hashset will, but tries to have none
        // int [] arrayN = new int[n];
        // for (int i = 0; i<n; i++){
        //     arrayN[i] = ranNums.nextInt(n *3);
        // }
        // long arrayNMemory = MemoryUtil.deepMemoryUsageOf(arrayN);
        // System.out.println("Memory used by generating array(no hash): " + arrayNMemory + " bytes");

    
        
        return arrayN;
    }
    public static int[] generateTargets(int m, int range){ // can have repeating numbers
        Random randomNumbersM = new Random();
        int[] arrayM = new int[m];
        // add random numbers to array  
        for (int i=0; i< m; i++){
            int randomInt = randomNumbersM.nextInt(range); // for n =10,000 use 1,000,000. need to use a value a lot bigger than n
            arrayM[i] = randomInt;
        }

        return arrayM;
    }

    public static int linearSearch(int nData[], int target){
        for(int i=0; i<nData.length; i++){
            if (nData[i] == target){
                return i;
            }

        } return -1; 
            
    }
// recursive binary search
    static int binarySearch(int nData[], int l, int r, int target2){ // x is the target, n is the length, l is left, r is right
        if (r>=l){
            int mid = l + (r-l)/2;

            // index of the element
            if(nData[mid] == target2){
                return mid;
            }
            // if the element is smaller than mid then it has to be in the left

            if (nData[mid] > target2){
                return binarySearch(nData, l, mid -1, target2);
            }
            //else it has to be in the right
            return binarySearch(nData, mid+1, r, target2);
        }
        // not found
        return -1;
    }


    


}
// program has to: 1. randomly generate n int numbers (to avoid duplicates, we want the range of the data to be much 
// greater than the size) and save them in an array 2. Sort them in order
// 3. Randomly generate m numbers, for each number, perform linear and binear search on the array, record the actual search time (if it is found or not)
// Create a report
    
// Approach:
//done 1. Generate random integers with no dupes: use a HashSet since sets reject dupes and then convert it into an array
//done 2. Generate m random search targets ( could or might not exist in the array we made) going to test each of the m numbers with linear and binary search time
// 3. Linear search O(n)
// 4. Binary Search O(logn)
// 5. Measure time
