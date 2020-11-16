
/**
 * 
 * @author Myles Thomas JAN/27/2019 CSCI 333 This code divides an array of
 *         elements into 3 parts and searches for the target element within each
 *         third.
 */
public class TrinarySearch {

	/**
	 * 
	 * @param a
	 *            is the array
	 * @param target
	 *            is the element that the method is trying to find
	 * @param start
	 *            is the first element of the array
	 * @param end
	 *            is the last element of the array
	 * @param q1
	 *            is the first third of the array
	 * @param q2
	 *            is the second third of the array
	 * @param q3
	 *            is the final third of the array
	 * 
	 */
	public static int triSearch(int a[], int target, int start, int end) {
		int q1 = (start + end) / 3;
		int q2 = q1 * 2;
		int q3 = end;
		if (start > end) {
			return -1;
		} else if (a[q1] == target) {
			return q1;
		} else if (a[q1] > target) {
			return triSearch(a, target, start, q1 - 1);
		} else if (a[q2] == target) {
			return q2;
		} else if (a[q2] > target) {
			return triSearch(a, target, start, q2 - 1);
		}
		if (a[q3] == target) {
			return q3;
		} else if (a[q3] > target) {
			return triSearch(a, target, start, q3 - 1);
		}
		return triSearch(a, target, q1 + 1, end);
	}

	public static int triSearch(int a[], int target) {
		return TrinarySearch.triSearch(a, target, 0, a.length - 1);
	}

	public static void main(String[] args) {
		TrinarySearch ob = new TrinarySearch();
		int arr[] = { 2, 3, 4, 10, 40, 50 };
		int arr2[] = { 2, 3, 4, 10, 40, 50, 55, 60, 65, 70 };
		int arr3[] = { 2, 3, 4, 10, 40, 50, 55, 60, 65, 70, 75, 80 };
		int arr4[] = { 1, 2, 3 };
		int arr5[] = { 15, 86, 156, 201, 684 };
		int target = 50;
		int target2 = 40;
		int target3 = 65;
		int target4 = 2;
		int target5 = 156;
		System.out.println("The target number is: " + target);
		int result = ob.triSearch(arr, target);
		if (result == -1)
			System.out.println("Element not present");
		else
			System.out.println("Element found at " + "index " + result);

		System.out.println("The target number is: " + target2);
		result = ob.triSearch(arr2, target2);
		if (result == -1)
			System.out.println("Element not present");
		else
			System.out.println("Element found at " + "index " + result);

		System.out.println("The target number is: " + target3);
		result = ob.triSearch(arr3, target3);
		if (result == -1)
			System.out.println("Element not present");
		else
			System.out.println("Element found at " + "index " + result);

		System.out.println("The target number is: " + target4);
		result = ob.triSearch(arr4, target4);
		if (result == -1)
			System.out.println("Element not present");
		else
			System.out.println("Element found at " + "index " + result);

		System.out.println("The target number is: " + target5);
		result = ob.triSearch(arr5, target5);
		if (result == -1)
			System.out.println("Element not present");
		else
			System.out.println("Element found at " + "index " + result);
	}
}
