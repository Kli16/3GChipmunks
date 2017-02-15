/* 
  ~~~~~~~~~~~~~~~~~~~~ 
   AUGIE MURPHY
   APCS2
   HW#06-- mergeSort!!!
   02/12/17
  ~~~~~~~~~~~~~~~~~~~
*/


/*======================================
  class MergeSort
  Implements mergesort on array of ints.

  Summary of Algorithm: 
  The array is split into two smaller arrays until each array is only one element long. Then, the sorted arrays are merged until there is only one array left. They are merged by placing the smaller element into the front of the array to be returned & then comparing the next values until all slots in the retArray have been filled. We know all of the elements will have been used by then because the length of the retArray is the sum of the lengths of the merging arrays.

  ======================================*/

public class MergeSort {

   /******************************************************
     * int[] merge(int[],int[]) 
     * Merges two input arrays
     * Precond:  Input arrays are sorted in ascending order
     * Postcond: Input arrays unchanged, and 
     * output array sorted in ascending order.
     ******************************************************/
    private static int[] merge( int[] a, int[] b ) 
    {
	int[] retArray = new int[a.length + b.length];
	int y = 0;
	for( int x = 0; x < retArray.length; x++ ){
	    if( a.length <= y ){ retArray[x] = b[x-y]; }
	    else if( b.length <= x-y ){ retArray[x] = a[y]; }
	    else if( a[y] < b[x-y] ){ retArray[x] = a[y]; y++; }
	    else{ retArray[x] = b[x-y]; }
	}
	return retArray;

    }//end merge()


    /******************************************************
     * int[] sort(int[]) 
     * Sorts input array using mergesort algorithm
     * Returns sorted version of input array (ascending)
     ******************************************************/
    public static int[] sort( int[] arr ) 
    {
	if( arr.length > 1 ){
	    int[] D1 = new int[arr.length/2];
	    int[] D2 = new int[arr.length-D1.length];
	    for( int x = 0; x < arr.length; x++ ){
		if( x < D1.length ){ D1[x] = arr[x]; }
		else{ D2[x-D1.length] = arr[x]; }
	    }
	    return merge( sort(D1), sort(D2) );
	}
	return arr;
    }//end sort()



    //-------------------HELPERS-------------------------
    //tester function for exploring how arrays are passed
    //usage: print array, mess(array), print array. Whaddayasee?
    public static void mess( int[] a ) {
	for( int i = 0 ; i<a.length; i++ )
	    a[i] = 0;
    }

    //helper method for displaying an array
    public static void printArray( int[] a ) {
	System.out.print("[");
	for( int i : a )
	    System.out.print( i + ",");
	System.out.println("]");
    }
    //---------------------------------------------------


    //main method for testing
    public static void main( String [] args ) {

	int[] arr0 = {0};
	int[] arr1 = {1};
	int[] arr2 = {1,2};
	int[] arr3 = {3,4};
	int[] arr4 = {1,2,3,4};
	int[] arr5 = {4,3,2,1};
	int[] arr6 = {9,42,17,63,0,512,23};
	int[] arr7 = {9,42,17,63,0,9,512,23,9};

	System.out.println("\nTesting mess-with-array method...");
	printArray( arr3 );
	mess(arr3);
	printArray( arr3 );

	System.out.println("\nMerging arr1 and arr0: ");
	printArray( merge(arr1,arr0) );

	System.out.println("\nMerging arr4 and arr6: ");
	printArray( merge(arr4,arr6) );

	System.out.println("\nSorting arr4-7...");
	printArray( sort( arr4 ) );
	printArray( sort( arr5 ) );
	printArray( sort( arr6 ) );

	System.out.println("\nMerging sorted arrays arr4 and arr6: ");
	printArray( merge(sort(arr4),sort(arr6)) );
    }//end main()

}//end class MergeSort

