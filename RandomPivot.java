import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPivot<T extends Comparable<? super T>> implements Partitionable<T> {

    @Override
    public int partition(T[] a, int first, int last) {
        int pivotIndex = ThreadLocalRandom.current().nextInt(first, last);
        T pivot = a[pivotIndex];
        if (pivotIndex != last){
            swap(a, pivotIndex, last);
            pivotIndex = last;
        }
        int left = first;
        int right = last - 1;
        boolean done = false;

        while (!done){
            while (a[left].compareTo(pivot) < 0)
                left++;
            while (a[right].compareTo(pivot) > 0 && right > first)
                right--;

            if (left < right){
                swap(a, left, right);
                left++;
                right--;
            } else
                done = true;
        }
        swap(a, pivotIndex, left);
        pivotIndex = left;
        return pivotIndex;

    }

    private void swap(Object[] a, int i, int j)
    {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp; 
    }
}
