public class Driver{

    public static void test( int length ){
	int[] tester = new int[length];
	for( int i = 0; i < length; i++ ){
	    tester[i] = (int)( Math.random() * 100 );
	}
	System.out.println( "\n\nTesting length: " + length );
	//MergeSort.printArray( tester );
        double start = System.currentTimeMillis();
	MergeSort.sort(tester);
	double end = System.currentTimeMillis();
	double time = end-start;
	System.out.println( "RUNTIME: " + time );
	System.out.println( "Sorted Array: " );
	//MergeSort.printArray( MergeSort.sort(tester) );
	System.out.println("********************************************\n\n");
    }
    
    public static void main( String[] args ){

	test( 1 );
	test( 10 );
	test( 100 );
	test( 1024 );
	test( 2048 );
	test( 4096 );
	test( 8192 );
	
    }
}
