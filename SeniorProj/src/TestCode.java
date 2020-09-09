import java.awt.Toolkit;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCode {

	public static final String EXAMPLE_TEST = "This int hello [] is my small example string which I'm going to use for pattern matching.";

	/**
	 * 
	 * @param A
	 * @param p
	 *            = start of the array
	 * @param r
	 *            = end of the array
	 */
	Toolkit toolkit;
	Timer timer;

	public TestCode(int seconds) {
		toolkit = Toolkit.getDefaultToolkit();
		timer = new Timer();
		timer.schedule(new RemindTask(), 0, // initial delay
				seconds * 1000); // subsequent rate
	}

	class RemindTask extends TimerTask {
		int numWarningBeeps = 3;

		public void run() {
			if (numWarningBeeps > 0) {
				System.out.println("Beep!");
				numWarningBeeps--;
			} else {
				System.out.println("Time's up!");
				// timer.cancel(); // Not necessary because
				// we call System.exit
				System.exit(0); // Stops the AWT thread
								// (and everything else)
			}
		}
	}

	public static void Quicksort(int A[], int p, int r) {
		if (p < r) {
			int q = Partition(A, p, r);
			Quicksort(A, p, q - 1);
			Quicksort(A, q + 1, r);
		}
	}

	public static void RandomizedQuicksort(int A[], int p, int r) {
		if (p < r) {
			Random z = new Random();
			int someint = z.nextInt(r - p + 1) + p;
			int swap = A[someint];
			A[someint] = A[r];
			A[r] = swap;
			int q = Partition(A, p, r);
			RandomizedQuicksort(A, p, q - 1);
			RandomizedQuicksort(A, q + 1, r);
		}
	}

	private static int Partition(int A[], int p, int r) {
		int pivot = A[r];
		int first = p - 1;
		for (int j = p; j < r; j++) {
			if (A[j] <= pivot) {
				first++;
				int hold = A[first];
				A[first] = A[j];
				A[j] = hold;
			}
			// System.out.println(java.util.Arrays.toString(A));
			// System.out.print(p + " ");
			// System.out.print(r);
			// System.out.println("");
		}
		int swap = A[first + 1];
		A[first + 1] = A[r];
		A[r] = swap;
		System.out.println(swap);
		return first + 1;
	}

	public static void main(String[] args) {

		int A[] = { 15, 8, 41, 3, 86, 50, 38, 12, 52, 70 };
		System.out.println(java.util.Arrays.toString(A));

		Quicksort(A, 0, A.length - 1);
		System.out.println("The sorted array");
		System.out.println(java.util.Arrays.toString(A));
		System.out.println("");
		int B[] = { 26, 98, 41, 3, 16, 50, 38, 12, 52, 70, 109, 13 };

		// System.out.println(java.util.Arrays.toString(B));
		// RandomizedQuicksort(B, 0, B.length - 1);
		// System.out.println(java.util.Arrays.toString(B));

		Pattern pattern = Pattern.compile("int \\w+ \\[]");
		// in case you would like to ignore case sensitivity,
		// you could use this statement:
		// Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(EXAMPLE_TEST);
		// check all occurance
		while (matcher.find()) {
			System.out.print("Start index: " + matcher.start());
			System.out.print(" End index: " + matcher.end() + " ");
			System.out.println(matcher.group());
		}
		// now create a new pattern and matcher to replace whitespace with tabs
		Pattern replace = Pattern.compile("\\s+");
		Matcher matcher2 = replace.matcher(EXAMPLE_TEST);
		System.out.println(matcher2.replaceAll("\t"));
		new TestCode(3);
	}
}
