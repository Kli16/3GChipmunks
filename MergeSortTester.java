/*Team 3G Chipmunks (August Murphy, Gilvir Gill, Terry Guan)
 *APCS2 pd3
 *HW06 -- what does the data say
 *2016-02-15
 */

 /*======================================
  class MergeTester
  Implements mergesort on array of ints, and tests its run time to imperically check if it's nlogn.

  Summary of Algorithm:
Essentially, you can easily merge two arrays that already sorted, using the merge(a,b) method.
This method adds the smallest element not already added from each array in order, and assumes that one of the arrays is greater than the other in length (it is allowed to make that assumption as the larger and smaller are defined with references).
Keep splitting the array until each subarray has 1 element, at that point you know it's sorted. Use the merge method until all elements have been merged.
Translating this into code means recursively merging sorted versions of each half.
So, given array ab, break into roughly equal parts a and b. Then recursively run merge(sort(a),sort(b)) until you have broken each half into already sorted parts.
  ======================================*/

public class MergeSortTester {


    //main method for testing
    public static void main( String [] args ) {
        System.out.print("NOTE: if superTests do not work, please use the -Xmx tag (i.e. java -Xmx10G MergeTester) to allocate more memory");
        System.out.println("Generating times for given array lengths:");
        //lengths that you wish to try out.
        int[] testcases = {1,2,4,8,16,32,64,128,256,512,1024,2048,3072,4096,8192};
        System.out.println("Length\tTime");
        for (int test: testcases) {
            System.out.println(""+test+","+avgTime(test));
        }
        //these testcases have a lower amount of tests but are much bigger
        int test15 = (int)Math.pow(2,15);
        int test20 = (int)Math.pow(2,20);
        int test25 = (int)Math.pow(2,25);
        int test26 = (int)Math.pow(2,26);
        int test27 = (int)Math.pow(2,27);
        int test28 = (int)Math.pow(2,28);
        int test29 = (int)Math.pow(2,29);
        int[] superTests = {test15,test20,test25,test26,test27,test28,test29};
        System.out.println("SUPERTESTS:");
        System.out.println("Length\tTime");
        for (int test: superTests) {
            System.out.println(""+test+","+avgTime(test,10));
        }
    }//end main()


   /******************************************************
     * int[] merge(int[],int[])
     * Merges two input arrays
     * Precond:  Input arrays are sorted in ascending order
     * Postcond: Input arrays unchanged, and
     * output array sorted in ascending order.
     ******************************************************/
    private static int[] merge( int[] a, int[] b )
    {
        //create a new array with a+b elements
        int length = a.length+b.length;
        int[] ab = new int[length];

        //reference a and b with variables min or max THESE ARE JUST REFERENCES TO THE SAME ARRAY
        int[] min = b;
        int[] max = a;
        //if b is greater in length, a is the min and b is the max
         if (a.length < b.length) {
             min = a;
             max = b;
         }
        //use variables to keep track of current indices that youve passed
        int minIndex = 0;
        int maxIndex = 0;
        //index of ab
        int index = 0;
        while (minIndex < min.length) {
            //until you've gotten to a point where you have added all the elements of one array:
            //or if you've already exhausted all max elements but not all mins (from the while conditional), keep adding the mins (this is the case when the arrays are of equal size)
            if ((maxIndex >= max.length) || (min[minIndex] < max[maxIndex])) { //if the next element of a is less than the next element of b, add it
                ab[index] = min[minIndex];
                minIndex+=1;
            }
            else {
                ab[index] = max[maxIndex];
                maxIndex+=1;
            }
            index+=1;
        }
        //now that all the elements of one of the arrays have been added, just continuously add until you fill out the other one
        while (maxIndex < max.length) {
            ab[index] = max[maxIndex];
            maxIndex+=1;
            index+=1;
        }
        return ab;
    }//end merge()


    /******************************************************
     * int[] sort(int[])
     * Sorts input array using mergesort algorithm
     * Returns sorted version of input array (ascending)
     ******************************************************/
    public static int[] sort( int[] arr )
    {
        if (arr.length <= 1) return arr;
        //split the arrays
        //arr1 will contain elements [0,n/2) and arr2 will contain [n/2,n)
        int n = arr.length;
        int[] arr1 = new int[n/2];
        int[] arr2 = new int[n-(n/2)];
        //ASSEMBLE THE ARRAYS
        for (int i = 0; i < n/2; i++) {
            arr1[i]=arr[i];
        }
        for (int i = n/2; i < n; i++) {
            arr2[i-(n/2)]=arr[i];
        }
        //sort them recursively until you get array of length one, then merge them
        return merge(sort(arr1),sort(arr2));

    }//end sort()




    //-------------------HELPERS-------------------------
    //tester function for exploring how arrays are passed
    //usage: print array, mess(array), print array. Whaddayasee?
    //MODIFIED VERSION: mess a preexisitng array
    public static void mess( int[] a, int nums) {
	for( int i = 0 ; i<a.length; i++ )
	    a[i] = (int) Math.random()*nums;
    }

    public static void mess( int[] a) {
        for( int i = 0 ; i<a.length; i++ ) {
    	    a[i] = 0;
        }
    }


    //helper method for displaying an array
    public static void printArray( int[] a ) {
	System.out.print("[");
	for( int i : a )
	    System.out.print( i + ",");
	System.out.println("]");
    }
    //---------------------------------------------------
    //generate random test cases for performance
    public static int[] randomArray(int length, int nums) {
        int[] arr =  new int[length];
        //populate array in range [0,nums)
        for (int i = 0; i < length; i++) {
            arr[i]=(int) (Math.random()*nums);
        }
        return arr;
    }
    public static int[] randomArray(int length) {
        //since the length of specific elements doesnt matter, limit it to 3 digits max
        return randomArray(length,1000);
    }


    //returns run time as an array with a certain amount of tries
    public static double[] runTime(int length, int tries) {
        int[] arr; //array to be sorted, changed every time
        int[] ans;
        long startTime;
        long endTime;
        double time;
        //create an array that is as long as the specified length
        arr = randomArray(length);
        //array controlling runtime of each try
        double[] times = new double[tries];
        //define start time and end time:
        for (int i=0; i < tries; i++) {
            //rescramble the array and try again
            mess(arr);
            startTime = System.nanoTime();
            ans = sort(arr);
            endTime = System.nanoTime();
            times[i] = ((double)(endTime-startTime))/1000000000.0; //add the time it took to run in seconds
        }
        return times;
    }
    public static double avgTime (int length, int tries) {
        double[] times = runTime(length, tries);
        double total = 0;
        //add the totals together in a for loop
        for (int i = 0; i < times.length; i++) {
            total+=times[i];
        }
        //return the arithmetic average:
        return total/times.length;
    }
    //returns avg time for 10K tests
    public static double avgTime (int length) {
        return avgTime(length, 10000);
    }


}//end class MergeTester
