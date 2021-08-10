

public class MedOfThree<T extends Comparable<? super T>> implements Partitionable<T> {

    @Override
    public int partition(T[] data, int first, int last) {
        int mid = (first + last)/2;
	    sortFirstMiddleLast(data, first, mid, last);
	  
	  
	    // move pivot to next-to-last position in array
	    swap(data, mid, last - 1);
	    int pivotIndex = last - 1;
	    T pivot = data[pivotIndex];

	    int indexFromLeft = first + 1; 
	    int indexFromRight = last - 2; 
	    boolean done = false;
	    while (!done)
	    {
	        while (data[indexFromLeft].compareTo(pivot) < 0)
	            indexFromLeft++;
	      
	    
	        while (data[indexFromRight].compareTo(pivot) > 0)
	            indexFromRight--;
	      
	        assert data[indexFromLeft].compareTo(pivot) >= 0 && data[indexFromRight].compareTo(pivot) <= 0;
	           
	        if (indexFromLeft < indexFromRight)
	    {
	        swap(data, indexFromLeft, indexFromRight);
	        indexFromLeft++;
	        indexFromRight--;
	    }
	    else 
	        done = true;
	    } // end while
	  
	    // place pivot between Smaller and Larger subarrays
	    swap(data, pivotIndex, indexFromLeft);
	    pivotIndex = indexFromLeft;
	  
	  
	    return pivotIndex; 
    } // end partition
    
    private void sortFirstMiddleLast(T[] a, int first, int mid, int last)
	{
	    order(a, first, mid); // make a[first] <= a[mid]
	    order(a, mid, last);  // make a[mid] <= a[last]
	    order(a, first, mid); // make a[first] <= a[mid]
	} // end sortFirstMiddleLast

    private void order(T[] a, int i, int j)
	{
	    if (a[i].compareTo(a[j]) > 0)
	        swap(a, i, j);
	} // end order

    private void swap(Object[] a, int i, int j)
    {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp; 
    }
    

}
