// CS 0445 Spring 2021
// Sorter<T> interface.  All of your sorting classes must implement
// this interface.  To see how this interface is used, read over
// the SortTest.java program.

//For the mergeSort code I used the code given in recitation 8

public class MergeSort<T extends Comparable<? super T>> implements Sorter<T> {
	private int MIN_SIZE; // min size to recurse, use InsertionSort
	 // for smaller sizes to complete sort
	public MergeSort()
	{
		MIN_SIZE = 3;
	}	
	// MERGE SORT


	public void mergeSort(T[] a, int first, int last)
	{
	  T[] tempArray = (T[]) new Comparable<?>[a.length];
	  mergeSort(a, tempArray, first, last);
	} // end mergeSort
	
	private void mergeSort(T[] a, T[] tempArray, int first, int last)
	{
		if (last - first + 1 < MIN_SIZE){
			insertionSort(a, first, last);
		} else{
	   	if (first < last)
	   	{  
	      	int mid = (first + last)/2;// index of midpoint
	      	mergeSort(a, tempArray, first, mid);  // sort left half array[first..mid]
	      	mergeSort(a, tempArray, mid + 1, last); // sort right half array[mid+1..last]

			if (a[mid].compareTo(a[mid + 1]) > 0)      // Question 2, Chapter 9
	     	 	merge(a, tempArray, first, mid, last); // merge the two halves
	   	}  
		}
	}  
	
	
	private void merge(T[] a, T[] tempArray, int first, int mid, int last)
	{
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;

		int index = beginHalf1; 
		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++){  
	   
	      	if (a[beginHalf1].compareTo(a[beginHalf2]) <= 0){  
	      		tempArray[index] = a[beginHalf1];
	        	beginHalf1++;
	      	}
	      	else{  
	      		tempArray[index] = a[beginHalf2];
	        	beginHalf2++;
	      	}  // end if
	   	}  // end for

	   	for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
	      	tempArray[index] = a[beginHalf1];

		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
	      	tempArray[index] = a[beginHalf2];

	   	for (index = first; index <= last; index++)
	      	a[index] = tempArray[index];
	}  

	public void insertionSort(T[] a, int n)
	{
		insertionSort(a, 0, n - 1);
	} 

    public void insertionSort(T[] a, int first, int last)
	{
		int unsorted;
		
		for (unsorted = first + 1; unsorted <= last; unsorted++){   
		
			T firstUnsorted = a[unsorted];
			
			insertInOrder(firstUnsorted, a, first, unsorted - 1);
		}
	}

    private void insertInOrder(T element, T[] a, int begin, int end)
	{
		int index;
		for (index = end; (index >= begin) && (element.compareTo(a[index]) < 0); index--){
			a[index + 1] = a[index];
		}
		a[index + 1] = element;
	}

	@Override
	public void sort(T[] a, int size) {
		mergeSort(a, 0, size - 1);
	
	}
	@Override
	public void setMin(int minSize) {
		MIN_SIZE = minSize;
	
	}
}