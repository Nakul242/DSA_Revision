import java.util.*;

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

    // union of 2 sorted Arrays
    public static int[] unionOfArr(int arr1[], int arr2[]) {
        int n = arr1.length;
        int m = arr2.length;
        int i=0, j=0;
        int val = 0;

        ArrayList<Integer> list = new ArrayList<>();

        while (i<n && j<m) {
            if (arr1[i] == arr2[j]) {
                val = arr1[i];
                i++; j++;
            }
            else if (arr1[i] < arr2[j]) {
                val = arr1[i];
                i++;
            }
            else {
                val = arr2[j];
                j++;
            }

            if (list.isEmpty() || list.get(list.size()-1) != val) {
                list.add(val);
            }
        }

        while (i<n) {
            val = arr1[i];
            if (list.isEmpty() || list.get(list.size()-1) != val) {
                list.add(val);
            }
            i++;
        }

        while (j<m) {
            val = arr2[j];
            if (list.isEmpty() || list.get(list.size()-1) != val) {
                list.add(val);
            }
            j++;
        }

        int ans[] = new int[list.size()];
        int k = 0;

        for (int num : list) {
            ans[k++] = num;
        }
        return ans;
    }

    // Intersection of Two Arrays | ( leetcode 349)
    public static int[] intersectionOfArr(int arr1[], int arr2[]) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();

        for (int val : arr1) {
            set.add(val);
        }

        for (int val : arr2) {
            if (set.contains(val)) {
                result.add(val);
            }
        }

        int ans[] = new int[result.size()];
        int i=0;

        for (int num : result) {
            ans[i++] = num;
        }

        return ans;
    }

    // Intersection of two Arrays || ( leetcode 350) 
    public static int[] intersectionOfArr2(int arr1[], int arr2[]) {
        // using HashMap
        HashMap<Integer,Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int num : arr1) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        for (int num : arr2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                list.add(num);
                map.put(num, map.get(num)-1); // decreasing freq count
            }
        }

        // contvert to arr
        int ans[] = new int[list.size()];
        int i=0;

        for (int num : list) {
            ans[i++] = num;
        }

        return ans;
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

    // QuickSort
    public static void quickSort(int arr[], int s, int e) {
        if (s >= e ) {
            return;
        }

        int pivot = partition(arr, s, e);
        quickSort(arr, s, pivot-1);
        quickSort(arr, pivot+1, e);
    }

    public static int partition(int arr[], int s, int e) {
        int pivot = arr[e];
        int i = s-1;

        for (int j = s; j < e; j++) {
            if (pivot > arr[j]) {
                // swap
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // to fix pivot pos
        i++;
        int temp = arr[i];
        arr[i] = arr[e];
        arr[e] = temp;

        return i;
    }

    // Binary Array Sorting ( GFG ) => ( partition Approach )
    public static void binarySort(int arr[]) {
        int n = arr.length;
        int i = -1;
        int pivot = 1;

        for (int j = 0; j < n; j++) {
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

    // Sort Colors ( LeetCode 75 ) => ( partition Approach )
    public static void sortColors(int arr[]) {
        int n = arr.length;
        int i = -1;
        int pivot = 2;

        for (int j = 0; j < n; j++) {
            if (arr[j] < pivot) {
                // swap
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        i = -1;
        pivot = 1;

        for (int j = 0; j < n; j++) {
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

    // Sort Colors ( LeetCode 75 ) => ( Dutch National Flag Algorithm )
    public static void sortColors2(int arr[]) {
        int low = 0;
        int mid = 0;
        int high = arr.length-1;

        while (mid <= high) {
            if (arr[mid] == 0) {
                // swap
                int temp = arr[mid];
                arr[mid] = arr[low];
                arr[low] = temp;
                mid++; low++;
            }
            else if (arr[mid] == 1) {
                mid++;
            }
            else {
                // swap
                int temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;
                high--;
            }
        }
        printArr(arr);
    }

    // Three way partitioning ( GFG ) => ( Dutch National Flag Algorithm )
    public static void threeWayPartition(int arr[], int a, int b) {
        int low = 0;
        int mid = 0;
        int high = arr.length-1;

        while (mid <= high) {
            if (arr[mid] < a) {
                // swap
                int temp = arr[mid];
                arr[mid] = arr[low];
                arr[low] = temp;
                mid++; low++;
            }
            else if (arr[mid] > b) {
                // swap
                int temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;
                high--;
            }
            else {
                mid++;
            }
        }
        printArr(arr);
    }

    // partition array according to given pivot ( leetCode 215 ) => ( single array approach )
    public static int[] partitionArray(int arr[], int pivot) {
        int ans [] = new int[arr.length];
        int i = 0;

        for (int num : arr) {
            if (num < pivot) {
                ans[i++] = num;
            }
        }

        for (int num : arr) {
            if (num == pivot) {
                ans[i++] = num;
            }
        }

        for (int num : arr) {
            if (num > pivot) {
                ans[i++] = num;
            }
        }

        return ans;
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

    // Move all negative elements to end of array ( GFG ) => ( Single array approach )
    public static void moveNegativesToEnd(int arr[]) {
        int temp[] = new int[arr.length];
        int k = 0;

        for (int num : arr) {
            if (num >= 0) {
                temp[k++] = num;
            }
        }

        for (int num : arr) {
            if (num < 0) {
                temp[k++] = num;
            }
        }

        // copy to array
        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i];
        }

        printArr(arr);
    }

    // Sort Array by Parity II ( LeetCode 922 ) => ( Two Pointer approach )
    public static void sortArrayByParityII(int arr[]) {
        // in place approach
        int n = arr.length;
        int even = 0; // for even index
        int odd = 1; // for odd index

        while (even < n && odd < n) {
            if (arr[even] % 2 == 0) {
                even += 2;
            }
            else if (arr[odd] % 2 == 1) {
                odd += 2;
            }
            else {
                // swap
                int temp = arr[even];
                arr[even] = arr[odd];
                arr[odd] = temp;
                even += 2;
                odd += 2;
            }
        }
        printArr(arr);
    }

    // Sort Array by Parity II ( LeetCode 922 ) => ( Single array approach )
    public static void sortArrayByParityII2(int arr[]) {
        int n = arr.length;
        int temp[] = new int[n];
        int even = 0; // for even index
        int odd = 1; // for odd index

        for (int num : arr) {
            if (num % 2 == 0) {
                temp[even] = num;
                even += 2;
            }
            else {
                temp[odd] = num;
                odd += 2;
            }
        }

        // copy to original array
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
        printArr(arr);
    }

    // Rearrange the array ele by sign ( leetCode 2149 ) => ( Two Pointer approach )
    public static void rearrangeBySign(int arr[]) {
        int n = arr.length;
        int temp[] = new int[n];
        int pos = 0; // for positive index
        int neg = 1; // for negative index

        for (int num : arr) {
            if (num >= 0) {
                temp[pos] = num;
                pos += 2;
            }
            else {
                temp[neg] = num;
                neg += 2;
            }
        }

        // copy to original array
        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
        printArr(arr);
    }

    // **************************************************************  Recursion  **************************************************************
    // *****************************************************************************************************************************************

    // factorial of a number
    public static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n-1);
    }

    // Fibonacci Series
    public static int fibonacci(int n) {
        if (n == 0 || n == 1) return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }

    // Print Increasing And Decreasing
    public static void printIncAndDec(int n, int i) {
        if ( i == n) {
            System.out.print(i+" ");
            return;
        }

        System.out.print(i+" ");
        printIncAndDec(n, i+1);
        System.out.print(i+" ");
    }

    // Check if array is Sorted 
    public static boolean isSorted(int arr[], int i) {
        if ( i == arr.length) return true;
        if ( i != arr.length-1 && arr[i+1] < arr[i]) return false;
        return isSorted(arr, i+1);
    }

    // Tower of Hanoi
    public static void towerOfHanoi(int n, char src, char dest, char helper) {
        if (n == 0) return;

        towerOfHanoi(n-1, src, helper, dest);
        System.out.println("Move "+n+" from "+src+" to "+dest);
        towerOfHanoi(n-1, helper, dest, src);
    }

    // Largest ele. in an array
    public static int largestEle(int arr[], int i, int mid) {
        if ( i == arr.length ) return mid;
        if ( arr[i] > mid ) mid = arr[i];

        return largestEle(arr, i+1, mid);
    }

    public static int largestEle2(int arr[], int i) {
        if ( i == arr.length ) return 1;
        return Math.max(arr[i],largestEle2(arr, i+1));
    }

    // first and last occurrence of an ele. in an array
    public static int[] firstAndLastOcc(int arr[], int i, int key) {
        if ( i == arr.length ) return new int[] {-1, -1};

        int res[] = firstAndLastOcc(arr, i+1, key);

        if (arr[i] == key) {
            res[0] = i; // for first occurrence
            if (res[1] == -1) {
                res[1] = i; // for last occurrence
            }
        }

        return res;
    }

    public static void firstAndLastOcc2(int arr[], int i, int key, int ans[]) {
        if ( i == arr.length ) return;

        
        if (arr[i] == key) {
            if (ans[0] == -1) {
                ans[0] = i; // for first occurrence
            }
            ans[1] = i; // for last occurrence
        }

        firstAndLastOcc2(arr, i+1, key, ans);
    }

    // Power of a number
    public static double power(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if ( n < 0) {
            x = 1 / x;
            n = -n;
        }

        double halfPower = power(x, n/2);

        if (n % 2 == 0) {
            return halfPower * halfPower;
        }
        else {
            return x * halfPower * halfPower;
        }
    }

    // Subset (leetCode 78)
    public static void subset(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Include current element
        current.add(nums[index]);
        subset(nums, index + 1, current, result);
        current.remove(current.size() - 1);

        // Exclude current element
        subset(nums, index + 1, current, result);

    }

    public static void subset2(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            subset2(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    // SubsetII ( leetCode 90 ) => ( to handle duplicates in input array )
    public static void subsetII(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i-1]) continue; // skip duplicates
            current.add(nums[i]);
            subsetII(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    //  All combinations of size r from an array ( GFG ) => ( Using Recursion and Fixing The Elements Approach )
    public static void combination(int arr[], int r, int index, List<Integer> list, List<List<Integer>> ans) {
        if (list.size() == r) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i=index; i<arr.length; i++) {
            list.add(arr[i]);
            combination(arr, r, i+1, list, ans);
            list.remove(list.size()-1);
        }
    }

    // All combinations of size r from an array ( GFG ) => ( Using Recursion and Including - Excluding Each Element Approach )
    public static void combination2(int arr[], int r, int start, List<Integer> list, List<List<Integer>> ans) {
        if (list.size() == r) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if (start == arr.length) {
            return;
        }

        // include current element
        list.add(arr[start]);
        combination2(arr, r, start+1, list, ans);
        list.remove(list.size()-1);

        // exclude current element
        combination2(arr, r, start+1, list, ans);
    }

    // All combinations of size r from n numbers ( GFG ) => ( Using Sort to Handle The Duplicate Elements in Input Approach )
    public static void combination3(int arr[], int r, int start, List<Integer> list, List<List<Integer>> ans) {
        if (list.size() == r) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i=start; i<arr.length; i++) {
            if (i > start && arr[i] == arr[i-1]) continue; // skip duplicates
            list.add(arr[i]);
            combination3(arr, r, i+1, list, ans);
            list.remove(list.size()-1);
        }
    }

    // Combination ( LeetCode 77) => ( Using Recursion and Fixing The Elements Approach )
    public static void combination(int n, int r, int start, List<Integer> list, List<List<Integer>> ans) {
        if (list.size() == r) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i=start; i<=n; i++) {
            list.add(i);
            combination(n, r, i+1, list, ans);
            list.remove(list.size()-1);
        }
    }

    // Letter tile Possibilities ( LeetCode 1079 ) => ( Using Recursion and Fixing The Elements Approach )
    public static int letterTilePossibilities(String tiles) {
        char arr[] = tiles.toCharArray();
        Arrays.sort(arr); // to handle duplicates in input

        boolean used[] = new boolean[arr.length];
        
        return letterTilePossibilitiesHelper(arr, used, 0);
    }

    public static int letterTilePossibilitiesHelper(char arr[], boolean used[], int count) {
        for (int i = 0; i < arr.length; i++) {
            if (used[i] || (i > 0 && arr[i] == arr[i-1] && !used[i-1])) {
                continue; // skip duplicates
            }

            used[i] = true;
            count++;
            count = letterTilePossibilitiesHelper(arr, used, count);
            used[i] = false;
        }
        return count;
    }

    public static void main(String[] args) {
        // int arr[] = { 2, 5, 4, 7, 6};
        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);

        // int result[] = mergeSort(arr, 0, arr.length-1);
        // printArr(result);

        // mergeSorted(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
        // mergeSorted2(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);

        // int result[] = unionOfArr(new int[]{1,2,4}, new int[]{1,3,4});
        // printArr(result);

        // int result[] = intersectionOfArr(new int[]{1,2,4}, new int[]{1,3,4});
        // printArr(result);
        
        // List<Integer> result = commonIn3SortedArr2(Arrays.asList(1,2,3,4,5), Arrays.asList(2,3,4,5,6), Arrays.asList(3,4,5,6,7));
        // System.out.println(result);

        // List<List<Integer>> result = findDifference(new int[]{1,2,3}, new int[]{2,4,6});
        // System.out.println(result);

        // int result[] = sortedSquares(new int[]{-4,-1,0,3,10});
        // printArr(result);

        // quickSort(arr, 0, arr.length-1);
        // printArr(arr);

        // binarySort(new int[]{0,1,0,1,1,0});

        // sortColors(new int[]{2,0,2,1,1,0});
        // sortColors2(new int[]{2,0,2,1,1,0});

        // threeWayPartition(new int[]{1, 4, 3, 6, 2, 8, 5}, 3, 6);

        // int result[] = partitionArray(new int[]{9, 12, 3, 5, 14, 10, 10}, 10);
        // printArr(result);

        // moveZeroes(new int[]{0,1,0,3,12});
        // moveZeroes2(new int[]{0,1,0,3,12});

        // sortArrayByParity(new int[]{3,1,2,4});

        // moveNegativesToEnd(new int[]{1, -1, 3, 2, -7, -5});

        // sortArrayByParityII(new int[]{4,2,5,7});
        // sortArrayByParityII2(new int[]{4,2,5,7});

        // rearrangeBySign(new int[]{1, -1, 3, -2, 4, -3});

        // System.out.println(factorial(5));
        // System.out.println(fibonacci(5));
        // printIncAndDec(5, 1);
        // System.out.println(isSorted(new int[] { 1, 5, 2, 4}, 0));
        // towerOfHanoi(3, 'A', 'C', 'B');

        // System.out.println(largestEle(new int[]{1, 5, 2, 4}, 0, Integer.MIN_VALUE));
        // System.out.println(largestEle2(new int[]{1, 5, 2, 4}, 0));

        // int result[] = firstAndLastOcc(new int[]{1, 5, 2, 4, 5}, 0, 5);
        // System.out.println("First Occurrence = "+result[0]+" , Last Occurrence = "+result[1]);
        // int ans[] = new int[] {-1, -1};
        // firstAndLastOcc2(new int[]{1, 5, 2, 4, 5}, 0, 5, ans);
        // System.out.println("First Occurrence = "+ans[0]+" , Last Occurrence = "+ans[1]);
        // System.out.println(power(2.0, 5));

        // List<List<Integer>> result = new ArrayList<>();
        // subset(new int[]{1,2,3}, 0, new ArrayList<>(), result);
        // subset2(new int[]{1,2,3}, 0, new ArrayList<>(), result);
        // System.out.println(result);

        // List<List<Integer>> result2 = new ArrayList<>();
        // // first sort the input array to handle duplicates
        // Arrays.sort(new int[]{1,2,2});
        // subsetII(new int[]{1,2,2}, 0, new ArrayList<>(), result2);
        // System.out.println(result2);

        // int arr[] = {1, 2, 2, 3};
        // Arrays.sort(arr);
        // List<List<Integer>> ans = new ArrayList<>();
        // // combination(arr, 2, 0, new ArrayList<>(), ans);
        // combination2(arr, 2, 0, new ArrayList<>(), ans);
        // // combination3(arr, 2, 0, new ArrayList<>(), ans);
        // System.out.println(ans);

        System.out.println(letterTilePossibilities("AAB"));
    }
}  