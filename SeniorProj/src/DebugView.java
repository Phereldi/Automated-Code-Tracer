
public class DebugView{

/**
 *
 * @author Myles Thomas
 * @date 2/9/1019 CSCI 333 HW2: HEAP this code makes a heap and upholds the max Heap property
 */
static class Heap {
    private int arr[];
    private int heapsize;

//This is the constructor for the Heap object
    public Heap(int heapsize) {
        arr = new int[heapsize];
        this.heapsize = heapsize;
        this.buildMaxHeap();

    }
//This rearanges the values to meet the max Heap property

    private void buildMaxHeap() {
        for (int i = this.arr.length; i > this.arr[0]; i--) {
            maxHeapify(i);
        }

    }

// This shows thee parent of a child
    private int parentOf(int i) {
        int parent;
        parent = this.arr[i] / 2;
        if (parent < 0) {
            return -1;
        }
        if (parent > arr.length) {
            return -1;
        }
        return parent;
    }

//This shows the left child of a parent
    private int leftChildOf(int i) {
        int left = this.arr[i] * 2;
        return left;
    }
//This shows the right child of a parent

    private int rightChildOf(int i) {
        int right = this.arr[i] * 2 + 1;
        return right;
    }

//This prints the Heap
    public String printMaxHeap() {
        System.out.println(this.arr.length);
        System.out.println(java.util.Arrays.toString(this.arr));
        return "";
    }

//this compares the left and right childs with the parent to see if the parent is the biggest number
// int l is the left child int r is the right child
    private void maxHeapify(int i) {
        int largest = i;
        int hold;
        int l = 1 * i;
        int r = 1 * i + 1;
        if (l < this.arr.length && this.arr[l] > this.arr[largest]) {
            largest = l;
        } else {
            largest = i;
        }
        if (r < this.arr.length && this.arr[r] > this.arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            hold = this.arr[i];
            this.arr[i] = this.arr[largest];
            this.arr[largest] = hold;
            maxHeapify(largest);
        }
    }

//this puts the heap in the correct order to uphold th max hep property
    public void Heapsort() {
        buildMaxHeap();
        int BackupCopy = this.heapsize;
        for (int i = this.arr.length - 1; i >= 0; i--) {
            int hold = arr[0];
            arr[0] = arr[i];
            arr[i] = hold;
            maxHeapify(0);
        }
    }
    
}
public static void main(String[] args){
            
        int A[] = {5, 13, 2, 25, 7, 17, 20, 8, 4};
        System.out.println(java.util.Arrays.toString(A));
        Heap Heap = new Heap(A.length);
        Heap.Heapsort();
        Heap.printMaxHeap();
        System.out.println("");
        System.out.println("next");
        
        }
}
