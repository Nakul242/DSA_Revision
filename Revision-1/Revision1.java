import java.util.*;

class Revision1 {
    // printArr
    public static void printArr(int arr[]) {
        for(int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    // bubble Sort
    public static void bubbleSort(int arr[]) {
        for(int i=0; i<arr.length-1; i++) {
            boolean isSwapped = false;
            for(int j=0; j<arr.length-1-i; j++) {
                if(arr[j] > arr[j+1]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    isSwapped = true;
                }
            }
            if(!isSwapped) {
                break;
            }
        }
        printArr(arr);
    }
    
    // Selection Sort
    public static void selectionSort(int arr[]) {
        int n = arr.length;
        for(int i=0; i<n-1; i++) {
            int min = i;
            for(int j=i+1; j<n; j++) {
                if(arr[min] > arr[j]) {
                    min = j;
                }
            }
            // swap only when needed
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        printArr(arr);
    }

    // Insertion sort
    public static void insertionSort(int arr[]) {
        // 👉 Insertion Sort =  Pick → Shift → Insert
        int n = arr.length;
        for(int i=1; i<n; i++) {
            int curr = arr[i];
            int prev = i-1;

            while (prev >= 0 && arr[prev] > curr) {
                arr[prev+1] = arr[prev];
                prev--;
            }

            arr[prev+1] = curr;
        }
        printArr(arr);
    }

    // Shell sort
    public static void shellSort(int arr[]) {
        int n = arr.length;

        for (int gap = n/2; gap > 0; gap/=2) {
            // insertion sort with gap
            for (int i = gap; i < n; i++) {
                int curr = arr[i];
                int prev = i-gap;

                while (prev >= 0  && arr[prev] > curr) {
                    arr[prev+gap] = arr[prev];
                    prev-=gap;
                }

                arr[prev+gap] = curr;
            }
        }
        printArr(arr);
    }

    // Merge Sort
    public static int[] mergeSort(int arr[], int s, int e) {

        if (s > e) {
            return new int[0];
        }

        if(s == e) {
            // return new int[]{arr[s]};
            int res[] = new int[1];
            res[0] = arr[s];
            return res;
        }

        int mid = s + (e - s)/2;
        int a1[] = mergeSort(arr, s, mid);
        int a2[] = mergeSort(arr, mid+1, e);

        return merge(a1, a2);
    }

    public static int[] merge(int a1[] , int a2[]) {
        int n1 = a1.length;
        int n2 = a2.length;

        int p1 = 0, p2 = 0, k = 0;
        int temp[] = new int[n1+n2];

        while (p1 < n1  && p2 < n2) {
            if(a1[p1] < a2[p2]) {
                temp[k++] = a1[p1++];
            } else {
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

    // Merge Sorted Arrays ( leetCode 88) => ( Two Pointer Approach )
    // Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    // Output: [1,2,2,3,5,6]
    public static void mergeSorted(int arr1[], int n, int arr2[], int m) {
        int n1 = n;
        int n2 = m;

        int p1 = 0, p2 = 0, k = 0;
        int temp[] = new int[n1+n2];

        while (p1 < n1  && p2 < n2) {
            if(arr1[p1] < arr2[p2]) {
                temp[k++] = arr1[p1++];
            } else {
                temp[k++] = arr2[p2++];
            }
        }

        while (p1 < n1) {
            temp[k++] = arr1[p1++];
        }

        while (p2 < n2) {
            temp[k++] = arr2[p2++];
        }

        printArr(temp);
    }

    public static int[] mergeSorted2(int arr1[], int n, int arr2[], int m) {
        // Optimal Approach => ( Two Pointer Approach )
        // without using extra space ( in place merging )
        int i = n-1; 
        int j = m-1;
        int k = arr1.length-1;

        while (i >= 0 && j >= 0) {
            if(arr1[i] > arr2[j]) {
                arr1[k--] = arr1[i--];
            } else {
                arr1[k--] = arr2[j--];
            }
        }

        while (j >= 0) {
            arr1[k--] = arr2[j--];
        }

        return arr1;
    }

    // Merge Sort without using extra space ( in place merging ) => ( Gap Method )
    // Input: a[] = [2, 4, 7, 10], b[] = [2, 3]
    // Output: a[] = [2, 2, 3, 4], b[] = [7, 10]
    // Explanation: After merging the two non-decreasing arrays, we get, [2, 2, 3, 4, 7, 10]
    public static int nextGap(int gap) {
        if (gap <= 1) {
            return 0;
        }
        return (gap / 2) + (gap % 2);
    }

    public static void mergeGap(int arr1[], int n, int arr2[], int m) {
        int gap = nextGap(n+m);

        while (gap > 0) {
            int i=0;

            // comparing ele. in first arr
            while (i+gap < n) {
                if (arr1[i] > arr1[i+gap]) {
                    // swap
                    int temp = arr1[i];
                    arr1[i] = arr1[i+gap];
                    arr1[i+gap] = temp;
                }
                i++;
            }

            // comparing ele. in both 
            int j = gap > n ? gap-n : 0;

            while (i < n  && j < m) {
                if (arr1[i] > arr2[j]) {
                    // swap
                    int temp = arr1[i];
                    arr1[i] = arr2[j];
                    arr2[j] = temp;
                }
                i++; j++;
            }

            // comparing ele. in second arr
            if (j < m) {
                j = 0;

                while (j+gap < m) {
                    if (arr2[j] > arr2[j+gap]) {
                        // swap
                        int temp = arr2[j];
                        arr2[j] = arr2[j+gap];
                        arr2[j+gap] = temp;
                    }
                    j++;
                }
            }

            gap = nextGap(gap);
        }
        printArr(arr1);
        System.out.println();
        printArr(arr2);
    }

    // Union of two sorted arrays (GFG) => ( Two Pointer Approach )
    public static ArrayList<Integer> unionOfTwo(int arr1[], int arr2[]) {
        int n = arr1.length;
        int m = arr2.length;
        int i = 0, j = 0;

        ArrayList<Integer> ans = new ArrayList<>();
        int val = 0;

        while (i < n && j < m) {
            if (arr1[i] == arr2[j]) {
                val = arr1[i];
                i++;j++;
            }
            else if (arr1[i] < arr2[j]) {
                val = arr1[i++];
            }
            else {
                val = arr2[j++];
            }

            if (ans.isEmpty() || ans.get(ans.size()-1) != val ) {
                ans.add(val);
            }
        }

        while (i < n) {
            val = arr1[i++];
            if (ans.isEmpty() || ans.get(ans.size()-1) != val ) {
                ans.add(val);
            }
        }

        while (j < m) {
            val = arr2[j++];
            if (ans.isEmpty() || ans.get(ans.size()-1) != val ) {
                ans.add(val);
            }
        }

        return ans;
    }

    // Intersection of two arrays ( LeetCode 349) => ( HashSet Approach )
    public static int[] intersectionOfTwo(int arr1[], int arr2[]) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr1) {
            set.add(num);
        }

        HashSet<Integer> result = new HashSet<>();
        for (int num : arr2) {
            if (set.contains(num)) {
                result.add(num);
            }
        }

        int ans[] = new int[result.size()];
        int i=0;
        for (int val : result) {
            ans[i++] = val;
        }
        return ans;
    }

    // Intersection of two arrays ( LeetCode 350) => ( Two Pointer Approach )
    public static int[] intersectionOfTwo2(int arr1[], int arr2[]) {
        int n = arr1.length;
        int m = arr2.length;
        int i = 0, j = 0, k = 0;

        ArrayList<Integer> list = new ArrayList<>();

        while (i < n && j < m) {
            if (arr1[i] == arr2[j]) {
                list.add(arr1[i]);
                i++;j++;
            }
            else if (arr1[i] < arr2[j]) {
                i++;
            }
            else {
                j++;
            }
        }

        int ans[] = new int[list.size()];
        for (int val : list) {
            ans[k++] = val;
        }

        return ans;
    }

    // Intersection of two arrays ( LeetCode 350) => ( HashMap Approach )
    public static int[] intersectionOfTwo3(int arr1[], int arr2[]) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : arr1) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int num : arr2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                list.add(num);
                map.put(num, map.get(num)-1);
            }
        }

        int ans[] = new int[list.size()];
        int i=0;
        for (int val : list) {
            ans[i++] = val;
        }
        return ans;
    }

    // Common in 3 Sorted Arrays (GFG ) => ( 3 Pointer Approach  )
    public static void commonInThree(int arr1[], int arr2[], int arr3[]) {
        int p1 = 0, p2 = 0, p3 = 0;
        while (p1 < arr1.length && p2 < arr2.length && p3 < arr3.length) {
            if(arr1[p1] == arr2[p2] && arr2[p2] == arr3[p3]) {
                System.out.print(arr1[p1]+" ");
                p1++;
                p2++;
                p3++;
            } else if (arr1[p1] < arr2[p2]) {
                p1++;
            } else if (arr2[p2] < arr3[p3]) {
                p2++;
            } else {
                p3++;
            }
        }
    }

    // Find the difference of two arrays (LeetCode) => ( 2 Pointer Approach )
    public static List<List<Integer>> differenceOfTwo(int arr1[], int arr2[]) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for(int num : arr1) set.add(num);
        for(int num : arr2) set2.add(num);

        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for(int num : set) {
            if(!set2.contains(num)) {
                list.add(num);
            }
        }

        for(int num : set2) {
            if (!set.contains(num)) {
                list2.add(num);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(list);
        ans.add(list2);

        return ans;
    }

    public static List<List<Integer>> differenceOfTwo2(int arr1[], int arr2[]) {
        // My Approach (Brute Force) 
        // 👉 Time Complexity = O(n*m)  and Space Complexity = O(n+m)
        List<Integer> list = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for(int i=0; i<arr1.length; i++) {
            boolean found = false;
            for(int j=0; j<arr2.length; j++) {
                if(arr1[i] == arr2[j]) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                list.add(arr1[i]);
            }
        }

        for(int i=0; i<arr2.length; i++) {
            boolean found = false;
            for(int j=0; j<arr1.length; j++) {
                if(arr2[i] == arr1[j]) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                list2.add(arr2[i]);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(list);
        ans.add(list2);

        return ans;
    }

    // Sqare of a sorted array (LeetCode) => ( Two Pointer Approach )
    public static int[] squareOfSorted(int arr[]) {
        int n = arr.length;
        int ans[] = new int[n];
        int p1 = 0, p2 = n-1, k = n-1;

        while (p1 <= p2) {
            if(Math.abs(arr[p1]) > Math.abs(arr[p2])) {
                ans[k--] = arr[p1] * arr[p1];
                p1++;
            } else {
                ans[k--] = arr[p2] * arr[p2];
                p2--;
            }
        }

        return ans;
    }

    // Quick Sort (Hoare's Partition Scheme)
    public static void quickSort(int arr[], int s, int e) {
        if (s >= e) {
            return;
        }

        int pivot = partition(arr, s, e);
        quickSort(arr, s, pivot-1);
        quickSort(arr, pivot+1, e);
    }

    public static int partition(int arr[], int s, int e) {
        int pivot = arr[e];
        int i = s-1;

        for(int j=s; j<e; j++) {
            if (arr[j] < pivot) {
                // swap
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // fix pivot position
        i++;
        int temp = arr[i];
        arr[i] = arr[e];
        arr[e] = temp;

        return i;
    }

    // Binary Array Sorting (GFG) => ( Partition Approach )
    public static void binaryArraySorting(int arr[]) {
        int pivot = 1;
        int i = -1;

        for(int j=0; j<arr.length; j++) {
            if (arr[j] < pivot) {
                // swap
                i++;
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        printArr(arr);
    }

    // Sort Color ( Leetcode 75) => (Partition Approach)
    public static void sortColor(int arr[]) {
        int i = -1;
        int pivot = 2;

        for(int j=0; j<arr.length; j++) {
            if (arr[j] < pivot) {
                // swap
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        i = -1; pivot = 1;

        for(int j=0; j<arr.length; j++) {
            if (arr[j] < pivot) {
                // swap
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        printArr(arr);
    }

    // Dutch National Flag Algorithm (optimal) => ( Partition Approach + 3 Pointers )
    public static void sortColor2(int arr[]) {
        int low = 0, mid = 0, high = arr.length-1;

        while (mid <= high) {
            if (arr[mid] == 0) {
                // swap
                int temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;
                low++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else {
                // swap
                int temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;
                high--;
            }
        }
        printArr(arr);
    }

    // Three way partitioning (GFG) => ( Partition Approach + 3 Pointers )
    public static void threeWayPartition(int arr[], int a, int b) {
        int low = 0, mid = 0, high = arr.length-1;

        while (mid <= high) {
            if (arr[mid] < a) {
                // swap with low
                int temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;
                low++; mid++;
            }
            else if ( arr[mid] > b) {
                // swap with high
                int temp = arr[high];
                arr[high] = arr[mid];
                arr[mid] = temp;
                high--;
            }
            else { // if arr[mid] is b/w a and b
                mid++;
            }
        }
        printArr(arr);
    }

    // Parrtition Array according to given pivot ( LeetCode 2153) => ( Partition Approach   )
    public static int[] pivotArray(int arr[], int pivot) {
        int nums[] = new int[arr.length];
        int i=0;

        for(int num : arr) {
            if (num < pivot) {
                nums[i++] = num;
            }
        }

        for(int num : arr) {
            if (num == pivot) {
                nums[i++] = num;
            }
        }

        for (int num : arr) {
            if (num > pivot) {
                nums[i++] = num;
            }
        }
        return nums;
    }

    // Move Zeroes ( LeetCode 283 ) => ( Overwrite approach )
    public static void moveZeroes(int arr[]) {
        int pos = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[pos++] = arr[i];
            }
        }

        while (pos < arr.length) {
            arr[pos++] = 0;
        }

        printArr(arr);
    }

    // Move Zeroes ( LeetCode 283 ) => ( Swap approach )
    public static void moveZeroes2(int arr[]) {
        int pos = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                // swap
                int temp = arr[pos];
                arr[pos] = arr[i];
                arr[i] = temp;
                pos++;
            }
        }

        printArr(arr);
    }

    // Sort Array by Parity ( LeetCode 905 ) => ( partition approach )
    public static void sortArrayByParity(int arr[]) {
        int i = -1;

        for (int j = 0; j < arr.length; j++) {
            if (arr[j] % 2 == 0) {
                // swap
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        printArr(arr);
    }

    // Move all negative numbers to end (GFG) => ( Single Array Approach )
    public static void moveNegative(int arr[]) {
        int ans[] = new int[arr.length];
        int k = 0;

        for (int num : arr) {
            if (num >= 0) {
                ans[k++] = num;
            }
        }

        for (int num : arr) {
            if (num < 0) {
                ans[k++] = num;
            }
        }

        printArr(ans);
    }

    // Sort Array by Parity II ( LeetCode 922 ) => ( Single Array Approach )
    public static void sortArrayByParityII(int arr[]) {
        int ans[] = new int[arr.length];
        int k = 0;

        for (int num : arr) {
            if (num % 2 == 0) {
                ans[k] = num;
                k+=2;
            }
        }

        k = 1;
        for (int num : arr) {
            if (num % 2 != 0) {
                ans[k] = num;
                k+=2;
            }
        }

        printArr(ans);
    }

    // Sort Array by Parity II ( LeetCode 922 ) => ( Two Pointer Approach )
    public static void sortArrayByParityII2(int arr[]) {
        int n = arr.length;
        int even = 0, odd = 1;

        while (even < n && odd < n) {

            // if even index has even number, move forward
            if (arr[even] % 2 == 0) {
                even+=2;
            } 
            
            // if odd index has odd number, move forward
            else if (arr[odd] % 2 != 0) {
                odd+=2;
            } 
            
            // if both wrong, swap
            else {
                int temp = arr[even];
                arr[even] = arr[odd];
                arr[odd] = temp;

                even+=2; odd+=2;
            }
        }
        printArr(arr);
    }

    // Rearrange Array Elements by Sign (LeetCode 2149) => ( Two Pointer Approach )
    public static void rearrangeBySign(int arr[]) {
        int n = arr.length;
        int pos = 0, neg = 1;

        while (pos < n && neg < n) {
            if (arr[pos] >= 0) {
                pos+=2;
            } else if (arr[neg] < 0) {
                neg+=2;
            } else {
                // swap
                int temp = arr[pos];
                arr[pos] = arr[neg];
                arr[neg] = temp;

                pos+=2; neg+=2;
            }
        }
        printArr(arr);
    }

    // Rearrange Array Elements by Sign (LeetCode 2149) => ( Single Array Approach )
    public static void rearrangeBySign2(int arr[]) {
        int n = arr.length;
        int ans[] = new int[n];
        int pos = 0, neg = 1;

        for (int num : arr) {
            if (num >= 0) {
                ans[pos] = num;
                pos+=2;
            } else {
                ans[neg] = num;
                neg+=2;
            }
        }
        printArr(ans);
    }

    public static void main(String[] args) {
        int arr[] = { 2, 8, 6, 4, 10};
        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);
        // shellSort(arr);

        // int sortedArr[] = mergeSort(arr, 0, arr.length - 1);
        // printArr(sortedArr);

        // int arr1[] = {1, 2, 4, 5, 6};
        // int arr2[] = {2, 3, 5, 7};
        // ArrayList<Integer> union = unionOfTwo(arr1, arr2);
        // System.out.println(union);

        // int intersection[] = intersectionOfTwo(arr1, arr2);
        // printArr(intersection);

        // mergeGap(new int[]{1, 4, 7, 8, 10}, 5, new int[]{2, 3, 9}, 3);

        // int arr1[] = {1, 2, 4, 5, 6};
        // int arr2[] = {2, 3, 5, 7};
        // int intersection2[] = intersectionOfTwo2(arr1, arr2);
        // printArr(intersection2);
        // int intersection3[] = intersectionOfTwo3(arr1, arr2);
        // printArr(intersection3);

        // int arr1[] = {1, 5, 10, 20, 40, 80};
        // int arr2[] = {6, 7, 20, 80, 100};
        // int arr3[] = {3, 4, 15, 20, 30, 70, 80, 120};
        // commonInThree(arr1, arr2, arr3);

        // int arr4[] = {1, 3, 2};
        // int arr5[] = {2, 4, 6};
        // List<List<Integer>> result = differenceOfTwo2(arr4, arr5);
        // System.out.println(result);

        // int arr7[] = {-4, -1, 0, 3, 10};
        // int result[] = squareOfSorted(arr7);
        // printArr(result);
   
        // quickSort(arr, 0, arr.length-1);
        // printArr(arr);

        // int arr6[] = {1, 0, 0, 1, 1, 0};
        // binaryArraySorting(arr6);

        // int arr8[] = {2, 0, 2, 1, 1, 0};
        // sortColor(arr8);
        // sortColor2(arr8);

        // threeWayPartition(new int[]{1, 2, 3, 3, 4, 5, 5, 6}, 3, 5);
        // int result[] = pivotArray(new int[]{9, 12, 5, 10, 14, 3, 10}, 10);
        // printArr(result);

        // moveZeroes(new int[]{0,1,0,3,12});
        // moveZeroes2(new int[]{0,1,0,3,12});

        // sortArrayByParity(new int[]{3,1,2,4});

        // moveNegative(new int[]{-1, 2, -3, 4, -5});

        // sortArrayByParityII(new int[]{4, 2, 5, 7});
        // sortArrayByParityII2(new int[]{4, 2, 5, 7});

        // rearrangeBySign(new int[]{3, 1, -2, -4});
        rearrangeBySign2(new int[]{3, 1, -2, -4});
    }
}