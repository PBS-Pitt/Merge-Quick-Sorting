
import java.util.*;

public class Assig4 {

	public static Random R = new Random();
	private ArrayList<Sorter<Integer>> sorts;
	private Integer[] A;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int size = s.nextInt();
		int runs = s.nextInt();
		boolean sorted = s.nextBoolean();
		s.close();
		String sort;
		if (sorted) {
			sort = "Sorted";
		} else {
			sort = "Random";
		}
		System.out.println("Initialyzation information");
		System.out.println("    Array size: " + size);
		System.out.println("    Number of runs per test: " + runs);
		System.out.println("    Initial data: " + sort + "\n");
		new Assig4(size, runs, sorted);
	}

	public Assig4(int size, int runs, boolean sorted) {

		sorts = new ArrayList<Sorter<Integer>>();
		sorts.add(new QuickSort<Integer>(new SimplePivot<Integer>()));
		sorts.add(new QuickSort<Integer>(new MedOfThree<Integer>()));
		sorts.add(new QuickSort<Integer>(new RandomPivot<Integer>()));
		sorts.add(new MergeSort<Integer>());

		A = new Integer[size];
		// time[0] = simplePivot, time[1] = MedOfThree, time[2] = RandomPivot, time[4] =
		// mergeSort
		Double[][] time = new Double[4][8];
		long start, stop;

		if (!sorted) {
			for (int i = 1; i < 9; i++) {
				for (int j = 0; j < runs; j++) {
					fillArray();
					for (int k = 0; k < sorts.size(); k++) {
						if (time[k][i - 1] == null) {
							time[k][i - 1] = 0.0;
						}
						sorts.get(k).setMin((i * 10) - 5);
						start = System.nanoTime();
						sorts.get(k).sort(A, A.length);
						stop = System.nanoTime();
						long diff = stop - start;
						time[k][i - 1] += (double) diff / 1000000000;
					}
				}
				for (int k = 0; k < 4; k++) {
					time[k][i - 1] = time[k][i - 1] / runs;				}
			}
		} else {
			for (int i = 0; i < A.length; i++) {
				A[i] = i + 1;
			}
			for (int i = 1; i < 9; i++) {
				for (int j = 0; j < runs; j++) {
					for (int k = 0; k < sorts.size(); k++) {
						if (time[k][i - 1] == null) {
							time[k][i - 1] = 0.0;
						}
						sorts.get(k).setMin((i * 10) - 5);
						start = System.nanoTime();
						sorts.get(k).sort(A, A.length);
						stop = System.nanoTime();
						long diff = stop - start;
						time[k][i - 1] += (double) diff / 1000000000;
					}
				}
				for (int k = 0; k < 4; k++) {
					time[k][i - 1] = time[k][i - 1] / runs;
				}
			}
		}

		int[][] overallResults = getBest(time);
		System.out.println("After the tests, here is the best setup:");
		if (overallResults[0][0] == 0) {
			System.out.println("    Algorithm: Simple Pivot QuickSort");
			if (sorted)
				System.out.println("    Data Status:     Sorted");
			else
				System.out.println("    Data Status:     Random");
			System.out.println("    Min Recurse:     " + (overallResults[0][1] * 10 + 5));
			System.out.println("    Average:     " + time[overallResults[0][0]][overallResults[0][1]] + "\n");

		} else if (overallResults[0][0] == 1) {
			System.out.println("    Algorithm: Median of Three QuickSort");
			if (sorted)
				System.out.println("    Data Status:     Sorted");
			else
				System.out.println("    Data Status:     Random");
			System.out.println("    Min Recurse:     " + (overallResults[0][1] * 10 + 5));
			System.out.println("    Average:     " + time[overallResults[0][0]][overallResults[0][1]] + "\n");
		} else if (overallResults[0][0] == 2) {
			System.out.println("    Algorithm: Random Pivot QuickSort");
			if (sorted)
				System.out.println("    Data Status:     Sorted");
			else
				System.out.println("    Data Status:     Random");
			System.out.println("    Min Recurse:     " + (overallResults[0][1] * 10 + 5));
			System.out.println("    Average:     " + time[overallResults[0][0]][overallResults[0][1]] + "\n");
		} else if (overallResults[0][0] == 3) {
			System.out.println("    Algorithm: MergeSort");
			if (sorted)
				System.out.println("    Data Status:     Sorted");
			else
				System.out.println("    Data Status:     Random");
			System.out.println("    Min Recurse:     " + (overallResults[0][1] * 10 + 5));
			System.out.println("    Average:     " + time[overallResults[0][0]][overallResults[0][1]] + "\n");
		}
		System.out.println("After the tests, here is the worst setup:");
		if (overallResults[1][0] == 0) {
			System.out.println("    Algorithm: Simple Pivot QuickSort");
			if (sorted)
				System.out.println("    Data Status:     Sorted");
			else
				System.out.println("    Data Status:     Random");
			System.out.println("    Min Recurse:     " + (overallResults[1][1] * 10 + 5));
			System.out.println("    Average:     " + time[overallResults[1][0]][overallResults[1][1]] + "\n");
		} else if (overallResults[1][0] == 1) {
			System.out.println("    Algorithm: Median of Three QuickSort");
			if (sorted)
				System.out.println("    Data Status:     Sorted");
			else
				System.out.println("    Data Status:     Random");
			System.out.println("    Min Recurse:     " + (overallResults[1][1] * 10 + 5));
			System.out.println("    Average:     " + time[overallResults[1][0]][overallResults[1][1]] + "\n");
		} else if (overallResults[1][0] == 2) {
			System.out.println("    Algorithm: Random Pivot QuickSort");
			if (sorted)
				System.out.println("    Data Status:     Sorted");
			else
				System.out.println("    Data Status:     Random");
			System.out.println("    Min Recurse:     " + (overallResults[1][1] * 10 + 5));
			System.out.println("    Average:     " + time[overallResults[1][0]][overallResults[1][1]] + "\n");
		}
		if (overallResults[1][0] == 3) {
			System.out.println("    Algorithm: MergeSort");
			if (sorted)
				System.out.println("    Data Status:     Sorted");
			else
				System.out.println("    Data Status:     Random");
			System.out.println("    Min Recurse:     " + (overallResults[1][1] * 10 + 5));
			System.out.println("    Average:     " + time[overallResults[1][0]][overallResults[1][1]] + "\n");
		}

		int[][] individualResults = getResults(time);
		System.out.println("Here are the per Algorithm results:");

		System.out.println("Algorithm: Simple Pivot QuickSort");
		System.out.println("    Best Result:");

		System.out.println("                Min Recurse:     " + (individualResults[0][0] * 10 + 5));
		System.out.println("                Average:     " + time[0][individualResults[0][0]]);
		System.out.println("    Worst Result:");

		System.out.println("                Min Recurse:     " + (individualResults[0][1] * 10 + 5));
		System.out.println("                Average:     " + time[0][individualResults[0][1]] + "\n");

		System.out.println("Algorithm: Median of Three QuickSort");
		System.out.println("    Best Result:");

		System.out.println("                Min Recurse:     " + (individualResults[1][0] * 10 + 5));
		System.out.println("                Average:     " + time[1][individualResults[1][0]]);
		System.out.println("    Worst Result:");

		System.out.println("                Min Recurse:     " + (individualResults[1][1] * 10 + 5));
		System.out.println("                Average:     " + time[1][individualResults[1][1]] + "\n");

		System.out.println("Algorithm: Random Pivot QuickSort");
		System.out.println("    Best Result:");

		System.out.println("                Min Recurse:     " + (individualResults[2][0] * 10 + 5));
		System.out.println("                Average:     " + time[2][individualResults[2][0]]);
		System.out.println("    Worst Result:");

		System.out.println("                Min Recurse:     " + (individualResults[2][1] * 10 + 5));
		System.out.println("                Average:     " + time[2][individualResults[2][1]] + "\n");

		System.out.println("Algorithm: MergeSort");
		System.out.println("    Best Result:");

		System.out.println("                Min Recurse:     " + (individualResults[3][0] * 10 + 5));
		System.out.println("                Average:     " + time[3][individualResults[3][0]]);
		System.out.println("    Worst Result:");

		System.out.println("                Min Recurse:     " + (individualResults[3][1] * 10 + 5));
		System.out.println("                Average:     " + time[3][individualResults[3][1]] + "\n");

	}

	public int[][] getResults(Double[][] a) {
		Double best = 100.0;
		Double worst = 0.0;
		// [][0] = best, [][1] = worst
		int[][] position = new int[4][2];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				if (best.compareTo(a[i][j]) > 0) {
					position[i][0] = j;
					best = a[i][j];
				}
				if (worst.compareTo(a[i][j]) < 0) {
					position[i][1] = j;
					worst = a[i][j];
				}
			}
			best = 100.0;
			worst = 0.0;
		}
		return position;

	}

	public int[][] getBest(Double[][] a) {
		Double best = 100.0;
		Double worst = 0.0;
		// [0][] = best, [1][] = worst
		// [][0] = Algo, [][1] = MinSize
		int[][] position = new int[2][2];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				if (best.compareTo(a[i][j]) > 0) {
					position[0][0] = i;
					position[0][1] = j;
					best = a[i][j];
				}
				if (worst.compareTo(a[i][j]) < 0) {
					position[1][0] = i;
					position[1][1] = j;
					worst = a[i][j];
				}
			}
		}
		return position;

	}

	public void fillArray() {
		for (int i = 0; i < A.length; i++) {
			A[i] = R.nextInt(1000000000);
		}
	}

}