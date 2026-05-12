import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Revision2 {
    public static void printArr(int arr[]) {
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    public static void bubbleSort(int arr[]) {
        int n = arr.length;
        for(int i=0; i<n-1; i++) {
            boolean isSwaped = false;
            for(int j=0; j<n-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSwaped = true;
                }
            }
            if(!isSwaped) break;
        }
        printArr(arr);
    }

    public static void selectionSort(int arr[]) {
        int n = arr.length;
        for(int i=0; i<n-1; i++) {
            int min = i;
            for(int j=i+1; j<n; j++) {
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
        // Time Complexity = O(n^2)  |  Space Complexity = O(1)  | Stable Sort | In-place Sort | best case = O(n) when array is already sorted
        for(int i=1; i<arr.length; i++) {
            int prev = i-1;
            int curr = arr[i];

            while (prev >= 0  &&  arr[prev] > curr) {
                arr[prev+1] = arr[prev];
                prev--;
            }

            arr[prev+1] = curr;
        }
        printArr(arr);
    }

    public static int[] mergeSort(int arr[], int s, int e) {
        if (s > e) {
            return new int[0];
        }

        if (s == e) {
            // return new int[] {arr[s]};
            int singleEle[] = new int[1];
            singleEle[0] = arr[s];
            return singleEle;
        }

        int mid = s + (e-s)/2;
        int a[] = mergeSort(arr, s, mid);
        int b[] = mergeSort(arr, mid+1, e);
        
        return merge(a,b);
    }

    public static int[] merge(int a[], int b[]) {
        int n1 = a.length;
        int n2 = b.length;

        int p1=0, p2=0, k=0;
        int temp[] = new int[n1+n2];

        while (p1 < n1 && p2 < n2) {
            if (a[p1] < b[p2]) {
                temp[k++] = a[p1++];
            }
            else {
                temp[k++] = b[p2++];
            }
        }

        while (p1 < n1) {
            temp[k++] = a[p1++];
        }

        while (p2 < n2) {
            temp[k++] = b[p2++];
        }

        return temp;
    }

    // Merge Sorted Arrays ( leetCode 88) => ( Merge Sort Approach )
    // Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    // Output: [1,2,2,3,5,6]
    public static void mergeSorted(int arr1[], int n, int arr2[], int m) {
        int p1 = 0, p2 = 0, k = 0;
        int temp[] = new int[n+m];

        while (p1 < n && p2 < m) {
            if (arr1[p1] < arr2[p2]) {
                temp[k++] = arr1[p1++];
            }
            else {
                temp[k++] = arr2[p2++];
            }
        }

        while (p1 < n) {
            temp[k++] = arr1[p1++];
        }

        while (p2 < m) {
            temp[k++] = arr2[p2++];
        }

        for (int i = 0; i < temp.length; i++) {
            arr1[i] = temp[i];
        }

        printArr(arr1);
    }

    // Merge Sorted Arrays ( leetCode 88) => ( 2 Pointer Approach )
    public static void mergeSorted2(int arr1[], int n, int arr2[], int m) {
        int p1 = n-1, p2 = m-1, k = n+m-1;

        while (p1 >= 0 && p2 >= 0) {
            if (arr1[p1] > arr2[p2]) {
                arr1[k--] = arr1[p1--];
            }
            else {
                arr1[k--] = arr2[p2--];
            }
        }

        while (p2 >= 0) {
            arr1[k--] = arr2[p2--];
        }

        printArr(arr1);
    }

    // Common in 3 Sorted Array (Using HashSet)
    public static List<Integer> commonIn3SortedArr( List<Integer> arr1, List<Integer> arr2, List<Integer> arr3) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for(int num : arr1) set.add(num);
        for(int num : arr2) set2.add(num);

        List<Integer> ans = new ArrayList<>();

        for(int num : arr3) {
            if (set.contains(num) && set2.contains(num) && !ans.contains(num)) {
                ans.add(num);
            }
        }

        return ans.size() > 0 ? ans : Arrays.asList(-1);
    }

    // Using 3 pointer Approach
    public static List<Integer> commonIn3SortedArr2( List<Integer> arr1, List<Integer> arr2, List<Integer> arr3) {
        int p1 = 0 , p2 = 0, p3 = 0;
        List<Integer> ans = new ArrayList<>();

        while (p1 < arr1.size() && p2 < arr2.size() && p3 < arr3.size()) {
            int a = arr1.get(p1);
            int b = arr2.get(p2);
            int c = arr3.get(p3);

            if (a == b && b == c) {
                // to avoid duplicates in answer
                if (ans.size() == 0 || ans.get(ans.size()-1) != a) {
                    ans.add(a);
                }
                p1++; p2++; p3++;
            }
            // else {
            //     int min = Math.min(a, Math.min(c, b));     
            //     if(min == a) p1++;
            //     if(min == b) p2++;
            //     if(min == c) p3++;
            // }
            else if (a < b && a < c) {
                p1++;
            }
            else if (b < c) {
                p2++;
            }
            else {
                p3++;
            }
        }

        if (ans.isEmpty()) ans.add(-1);
        return ans;
    }

    // Find the difference between 2 arrays
    public static List<List<Integer>> findDifference(int arr1[], int arr2[]) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for (int num : arr1) {
            set1.add(num);
        }
        for (int num : arr2) {
            set2.add(num);
        }

        List<Integer> diff1 = new ArrayList<>();
        List<Integer> diff2 = new ArrayList<>();

        for (int num : arr1) {
            if (!set2.contains(num)) {
                diff1.add(num);
            }
        }
        for (int num : arr2) {
            if (!set1.contains(num)) {
                diff2.add(num);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(diff1);
        result.add(diff2);

        return result;
    }

    // Square of a Sorted Array (LeetCode) => ( 2 Pointer Approach )
    public static int[] sortedSquares(int arr[]) {
        int p1 = 0;
        int p2 = arr.length-1;
        int k = arr.length-1;
        int nums[] = new int[arr.length];

        while (p1 <= p2) {
            int first = arr[p1] * arr[p1];
            int last = arr[p2] * arr[p2];

            if (first > last) {
                nums[k--] = first;
                p1++;
            }
            else {
                nums[k--] = last;
                p2--;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        // int arr[] = { 2, 5, 4, 7, 6};
        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);

        // int result[] = mergeSort(arr, 0, arr.length-1);
        // printArr(result);

        // mergeSorted(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
        mergeSorted2(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
        
        // List<Integer> result = commonIn3SortedArr2(Arrays.asList(1,2,3,4,5), Arrays.asList(2,3,4,5,6), Arrays.asList(3,4,5,6,7));
        // System.out.println(result);

        // List<List<Integer>> result = findDifference(new int[]{1,2,3}, new int[]{2,4,6});
        // System.out.println(result);

        // int result[] = sortedSquares(new int[]{-4,-1,0,3,10});
        // printArr(result);

    }
}  