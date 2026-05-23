public class Revision4 {
    public static void printArr(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    public static void bubbleSort(int arr[]) {
        for (int i = 0; i < arr.length-1; i++) {
            boolean isSwapped = true;
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSwapped = false;
                }
            }
            if ( isSwapped ) break;
        }
        printArr(arr);
    }

    public static void selectionSort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            // swap if need
            if (min != i) {
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
        printArr(arr);
    }

    public static void insertionSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i];
            int prev = i-1;

            while (prev >=0  && arr[prev] > curr) {
                arr[prev+1] = arr[prev];
                prev--;
            }

            // fix 
            arr[prev+1] = curr;
        }
        printArr(arr);
    }

    public static void shellSort(int arr[]) {
        int n = arr.length;
        for (int gap = n/2; gap > 0; gap/=2) {
            for (int i = gap; i < arr.length; i++) {
                int curr = arr[i];
                int prev = i-gap;

                while (prev >= 0 && arr[prev] > curr) {
                    arr[prev+gap] = arr[prev];
                    prev-=gap;
                }

                // fix
                arr[prev+gap] = curr;
            }
        }
        printArr(arr);
    }

    public static int[] mergeSort(int arr[], int s, int e) {
        if (s == e)  return new int[]{arr[s]};

        if ( s > e) return new int[0];

        int mid = s + ( e -s) /2;
        int a[] = mergeSort(arr, s, mid);
        int b[] = mergeSort(arr, mid+1, e);
        return merge(a, b);
    }

    public static int[] merge(int a[], int b[]) {
        int n1 = a.length; 
        int n2 = b.length;

        int temp[] = new int[n1+n2];
        int p1 = 0, p2 = 0, k = 0;

        while (p1 < n1 && p2 < n2) {
            if (a[p1] < b[p2]) {
                temp[k++] = a[p1++];
            }
            else {
                temp[k++] = b[p2++];
            }
        }

        while ( p1 < n1 ) temp[k++] = a[p1++];
        while ( p2 < n2 ) temp[k++] = b[p2++];

        return temp;
    }

    public static void mergeSort2(int arr[], int s, int e) {
        if ( s >= e) return;

        int mid = s + ( e -s )/2;
        mergeSort2(arr, s, mid);
        mergeSort2(arr, mid+1, e);

        merge2(arr, s, mid, e);
    }

    public static void merge2(int arr[], int s, int mid, int e) {
        int i = s;
        int j = mid + 1;
        int k = 0;

        int temp[] = new int[e - s + 1];

        while (i <= mid && j <= e) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            }
            else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= e) {
            temp[k++] = arr[j++];
        }

        for (int num : temp) {
            arr[s++] = num;
        }
    }

    public static void main(String[] args) {
        int arr[] = {5, 4, 1, 3, 2};
        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);
        // shellSort(arr);
        // int ans[] = mergeSort(arr, 0, arr.length-1);
        // printArr(ans);
        mergeSort2(arr, 0, arr.length-1);
        printArr(arr);
    }
}
