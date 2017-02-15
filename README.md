************
3G Chipmunks
APCS2 pd03
02/14/2017
************

Summary of Algorithm: 
1) The array is split into two smaller arrays until each array is only one element long. 2) The sorted arrays are merged until there is one fully sorted array. 
—> They are merged by comparing the “top” value of each array and placing the smaller element into the front of the retArray (the array that will be returned) & then comparing the next values of the respective arrays… until all slots in retArray have been filled. 
—> We know all of the elements will have been used by then because the length of the retArray is the sum of the lengths of the merging arrays.

Time Trials:
To visualize the runtime of the algorithm, visit the link below.
———————————————————————————————————————————————————————————————————————————————
https://docs.google.com/a/stuy.edu/spreadsheets/d/18H10mXgMUhce2ABhcTabvalMvDoSA822HMxlW2eZ9RQ/pubchart?oid=32225416&format=image
———————————————————————————————————————————————————————————————————————————————

The code for the sorting algorithm is in the class MergeSort.
First, clone this repo. Then, javac MergeSort.java and run to your heart’s content.
To run your own time trials, javac Driver.java. If you want proof that the super long arrays are really being sorted, uncomment the lines of code in test() that print the array. There will be a lot of numbers on the screen, so that may be overwhelming. It’s recommended that you run the main method as it is before you try that.