import java.util.*;
public class Revision3 {
    public static void printArr(int arr[]) {
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    public static void bubbleSort(int arr[]) {
        int n = arr.length;

        for (int i = 0; i < n-1; i++) {
            boolean isSwapped = true;
            for (int j = 0; j < n-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSwapped = false;
                }
            }
            if (isSwapped) {
                break;
            }
        }
        printArr(arr);
    }

    public static void selectionSort(int arr[]) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (arr[min] > arr[j]) {
                    min = j;        
                }
            }
            // swap only when needed
            if (min != i) {
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
        printArr(arr);
    }

    public static void insertionSort(int arr[]) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int curr = arr[i];
            int prev = i - 1;

            while (prev >= 0 && arr[prev] > curr) {
                arr[prev+1] = arr[prev];
                prev--;
            }

            // fix curr position
            arr[prev+1] = curr;
        }
        printArr(arr);
    }

    public static void shellSort(int arr[]) {
        int n = arr.length;
        for (int gap = n/2; gap > 0; gap/=2) {
            // insertion sort with the gap
            for (int i = gap; i < n; i++) {
                int curr = arr[i];
                int prev = i-gap;

                while (prev >= 0 && arr[prev] > curr) {
                    arr[prev+gap] = arr[prev];
                    prev-=gap;
                }

                //fix curr pos
                arr[prev+gap] = curr;
            }
        }
        printArr(arr);
    }

    public static int[] mergeSort(int arr[], int s, int e) {
        if (s == e) {
            return new int[]{arr[s]};
        }

        if (s > e) {
            return new int[0];
        }

        int mid = s + ( e - s) /2;
        int a1[] = mergeSort(arr, s, mid);
        int a2[] = mergeSort(arr, mid+1, e);

        return merge(a1, a2);
    }

    public static int[] merge(int a1[], int a2[]) {
        int n1 = a1.length;
        int n2 = a2.length;
        int p1 = 0, p2 = 0, k = 0;
        int temp[] = new int[n1+n2];

        while (p1 < n1 && p2 < n2) {
            if (a1[p1] < a2[p2]) {
                temp[k++] = a1[p1++];
            }
            else {
                temp[k++] = a2[p2++];
            }
        }

        while (p1 < n1) {
            temp[k++] = a1[p1++];
        }

        while (p2 < n2) {
            temp[k++] = a2[p2++];
        }

        return temp;
    }

    // Merge Sorted Array ( Leetcode 88) => ( Two pointer Approach )
    public static int[] mergeSorted(int arr[] , int n, int arr2[], int m) {
        // Timpe Complexity => O(n+m)
        int i = n-1; 
        int j = m-1;
        int k = arr.length-1;

        while (i >= 0  && j >= 0) {
            if (arr[i] >= arr2[j]) {
                arr[k--] = arr[i--];
            }
            else {
                arr[k--] = arr2[j--];
            }
        }

        while (i >= 0) {
            arr[k--] = arr[i--];
        }

        while (j >= 0) {
            arr[k--] = arr2[j--];
        }

        return arr;
    }

    public static int[] mergeSorted2(int arr1[], int n, int arr2[], int m) {
        int n1 = n;
        int n2 = m;
        int p1 = 0, p2 = 0, k = 0;

        int temp[] = new int[n1+n2];

        while (p1 < n1  && p2 < n2) {
            if (arr1[p1] <= arr2[p2]) {
                temp[k++] = arr1[p1++];
            }
            else {
                temp[k++] = arr2[p2++];
            }
        }

        while (p1 < n1) {
            temp[k++] = arr1[p1++];
        }

        while (p2 < n2) {
            temp[k++] = arr2[p2++];
        }

        return temp;
    }

    // Find the difference of two arrays (LeetCode) => ( 2 Pointer Approach )
    public static List<List<Integer>> differenceOfTwo(int arr1[], int arr2[]) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for (int num : arr1) {
            set.add(num);
        }

        for (int num : arr2) {
            set2.add(num);
        }

        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int num : set) {
            if (!set2.contains(num)) {
                list.add(num);
            }
        }

        for (int num : set2) {
            if (!set.contains(num)) {
                list2.add(num);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(list);
        ans.add(list2);

        return ans;
    }

    public static void main(String[] args) {
        int arr[] = { 2, 8, 4, 10, 6, 8};
        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);
        // shellSort(arr);

        int sortedArr[] = mergeSort(arr, 0, arr.length-1);
        printArr(sortedArr);

        // int arr1[] = {1, 2, 3, 0, 0, 0};
        // int arr2[] = {2, 5, 6};
        // int n = 3, m = 3;

        // // int mergedArr[] = mergeSorted(arr1, n, arr2, m);
        // int mergedArr[] = mergeSorted2(arr1, n, arr2, m);
        // printArr(mergedArr);
    }
}