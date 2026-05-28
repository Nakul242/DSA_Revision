import java.util.*;

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

    // Merge Sorted Array - Leetcode 88
     public static void mergeSortedArray(int arr1[], int arr2[], int m, int n) {
        int i = m-1;
        int j = n-1;
        int k = m+n-1;

        while (i >= 0 && j >= 0) {
            if (arr1[i] > arr2[j]) {
                arr1[k--] = arr1[i--];
            }
            else {
                arr1[k--] = arr2[j--];
            }
        }

        while (j >= 0) {
            arr1[k--] = arr2[j--];
        }

        printArr(arr1);
    }

    // Merge Without Extra Space - GFG => ( Gap Method )
    public static int nextGap(int gap) {
        if (gap <= 1) return 0;
        return (gap/2) + (gap%2);
    }

    public static void mergeWithoutExtraSpace(int a[], int b[]) {
        int n = a.length;
        int m = b.length;

        int gap = nextGap(n + m);

        while (gap > 0) {
            int i = 0;
            while (i + gap < n) {
                if (a[i] > a[i + gap]) {
                    int temp = a[i];
                    a[i] = a[i + gap];
                    a[i + gap] = temp;
                }
                i++;
            }

            int j = gap > n ? gap - n : 0;
            while (i < n && j < m) {
                if (a[i] > b[j]) {
                    int temp = a[i];
                    a[i] = b[j];
                    b[j] = temp;
                }
                i++;
                j++;
            }

            if (j < m) {
                j = 0;
                while (j + gap < m) {
                    if (b[j] > b[j + gap]) {
                        int temp = b[j];
                        b[j] = b[j + gap];
                        b[j + gap] = temp;
                    }
                    j++;
                }
            }

            gap = nextGap(gap);
        }

        printArr(a);
        System.out.println();
        printArr(b);
    }

    // Union of two sorted arrays ( GFG ) => ( Two Pointer Approach )
    public static ArrayList<Integer> unionOfTwoSorted(int a[], int b[]) {
        ArrayList<Integer> list = new ArrayList<>();
        int val = 0;
        int i = 0;
        int j = 0;

        while (i < a.length  && j < b.length) {
            if (a[i] == b[j]) {
                val = a[i];
                i++; j++;
            }
            else if (a[i] < b[j]) {
                val = a[i];
                i++;
            }
            else {
                val = b[j];
                j++;
            }

            if (list.isEmpty() || list.get(list.size()-1) != val) {
                list.add(val);
            }
        }

        while (i < a.length) {
            val = a[i++];

            if (list.isEmpty() || list.get(list.size()-1) != val) {
                list.add(val);
            }
        }

        while (j < b.length) {
            val = b[j++];

            if (list.isEmpty() || list.get(list.size()-1) != val) {
                list.add(val);
            }
        }

        return list;
    }

    // Intersection of Arrays ( leetcode 349 ) => ( Two Pointer Approach )
    public static ArrayList<Integer> intersectionOfTwo(int a[], int b[]) {
        ArrayList<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                if (list.isEmpty() || list.get(list.size()-1) != a[i]) {
                    list.add(a[i]);
                }
                i++; j++;
            }
            else if (a[i] < b[j]) {
                i++;
            }
            else {
                j++;
            }
        }

        return list;
    }

    // Intersection of Arrays ( leetcode 349 ) => ( HashSet Approach )
    public static int[] intersectionOfTwo2(int a[], int b[]) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for (int num : a) {
            set.add(num);
        }

        for (int num : b) {
            if (set.contains(num)) {
                set2.add(num);
            }
        }

        int[] result = new int[set2.size()];
        int index = 0;
        for (int num : set2) {
            result[index++] = num;
        }
        return result;
    }

    // Intersection of Arrays ( leetcode 350 ) => ( Sorting + Two Pointer Approach )
    public static int[] intersectionOfTwo3(int a[], int b[]) {
        Arrays.sort(a);
        Arrays.sort(b);

        ArrayList<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) {
                list.add(a[i]);
                i++; j++;
            }
            else if (a[i] < b[j]) {
                i++;
            }
            else {
                j++;
            }
        }

        int[] result = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            result[k] = list.get(k);
        }

        return result;
    }

    // Intersection of Arrays ( leetcode 350 ) => ( Brute Force Approach )
    public static int[] intersectionOfTwo4(int a[], int b[]) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] == b[j]) {
                    if (!list.contains(a[i])) {
                        list.add(a[i]);
                    }
                }
            }
        }

        int[] result = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            result[k] = list.get(k);
        }

        return result;
    }

    // Common Elements in three sorted arrays ( GFG ) => ( Three Pointer Approach )
    public static int[] commonElementsInThreeSortedArrays(int a[], int b[], int c[]) {
        ArrayList<Integer> list = new ArrayList<>();
        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length && k < c.length) {
            if (a[i] == b[j] && b[j] == c[k]) {
                if (list.isEmpty() || list.get(list.size()-1) != a[i]) {
                    list.add(a[i]);
                }
                i++; j++; k++;
            }
            else if (a[i] < b[j]) {
                i++;
            }
            else if (b[j] < c[k]) {
                j++;
            }
            else {
                k++;
            }
        }

        int[] result = new int[list.size()];
        int index = 0;
        for (int idx : list) {
            result[index++] = idx;
        }

        return result;
    }

    // Find the difference between two arrays ( leetcode 2215 ) => ( HashSet Approach )
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            set2.add(num);
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int num : set1) {
            if (!set2.contains(num)) {
                list1.add(num);
            }
        }

        for (int num : set2) {
            if (!set1.contains(num)) {
                list2.add(num);
            }
        }

        result.add(list1);
        result.add(list2);

        return result;
    }

    // Find the difference between two arrays ( leetcode 2215 ) => ( Brute Force Approach )
    public static List<List<Integer>> findDifference2(int[] nums1, int[] nums2) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int num : nums1) {
            boolean found = false;
            for (int num2 : nums2) {
                if (num == num2) {
                    found = true;
                    break;
                }
            }
            if (!found && !list1.contains(num)) {
                list1.add(num);
            }
        }

        for (int num : nums2) {
            boolean found = false;
            for (int num1 : nums1) {
                if (num == num1) {
                    found = true;
                    break;
                }
            }
            if (!found && !list2.contains(num)) {
                list2.add(num);
            }
        }

        result.add(list1);
        result.add(list2);

        return result;
    }

    public static void main(String[] args) {
        // int arr[] = {5, 4, 1, 3, 2};
        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);
        // shellSort(arr);
        // int ans[] = mergeSort(arr, 0, arr.length-1);
        // printArr(ans);
        // mergeSort2(arr, 0, arr.length-1);
        // printArr(arr);

        // int arr1[] = {1, 2, 3, 0, 0, 0};
        // int arr2[] = {2, 5, 6};
        // mergeSortedArray(arr1, arr2, 3, 3);

        // int a[] = {1, 4, 7, 8, 10};
        // int b[] = {2, 3, 9};
        // mergeWithoutExtraSpace(a, b);

        // int a[] = {1, 2, 4, 5, 6};
        // int b[] = {2, 3, 5, 7};
        // System.out.println(unionOfTwoSorted(a, b));
        // System.out.println(intersectionOfTwo(a, b));
        // System.out.println(Arrays.toString(intersectionOfTwo2(a, b)));
        // System.out.println(Arrays.toString(intersectionOfTwo3(a, b)));
        // System.out.println(Arrays.toString(intersectionOfTwo4(a, b)));

        // int a[] = {1, 5, 10, 20, 40, 80};
        // int b[] = {6, 7, 20, 80, 100};
        // int c[] = {3, 4, 15, 20, 30, 70, 80, 120};
        // System.out.println(Arrays.toString(commonElementsInThreeSortedArrays(a, b, c)));

        int nums1[] = {1, 2, 3};
        int nums2[] = {2, 4, 6};
        System.out.println(findDifference(nums1, nums2));
        System.out.println(findDifference2(nums1, nums2));
    }
}
