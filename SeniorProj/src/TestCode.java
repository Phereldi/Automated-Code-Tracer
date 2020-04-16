import java.util.Random;

public class TestCode {

	
	
	/**
    *
    * @param A
    * @param p = start of the array
    * @param r = end of the array
    */
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
           //System.out.println(java.util.Arrays.toString(A));
           //System.out.print(p + " ");
           //System.out.print(r);
           //System.out.println("");
       }
       int swap = A[first + 1];
       A[first + 1] = A[r];
       A[r] = swap;
       System.out.println(swap);
       return first + 1;
   }

   public static void main(String[] args) {

       int A[] = {15, 8, 41, 3, 86, 50, 38, 12, 52, 70};
       System.out.println(java.util.Arrays.toString(A));

       Quicksort(A, 0, A.length - 1);
       System.out.println("The sorted array");
       System.out.println(java.util.Arrays.toString(A));
       System.out.println("");
       int B[] = {26, 98, 41, 3, 16, 50, 38, 12, 52, 70, 109, 13};
       //System.out.println(java.util.Arrays.toString(B));
       //RandomizedQuicksort(B, 0, B.length - 1);
       //System.out.println(java.util.Arrays.toString(B));
   }
}