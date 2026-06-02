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

    // Merge without Extra Space ( GFG ) => Gap Method
    public static int nextGap(int gap) {
        if (gap <= 1) {
            return 0;
        }
        return gap/2 + gap%2;
    }

    public static void mergeGap(int arr1[], int arr2[]) {
        int n = arr1.length;
        int m = arr2.length;
        int gap = nextGap(n+m);

        while (gap > 0) {
            int i = 0;

            // compare in first array
            while (i+gap < n) {
                if (arr1[i] > arr1[i+gap]) {
                    // swap
                    int temp = arr1[i];
                    arr1[i] = arr1[i+gap];
                    arr1[i+gap] = temp;
                }
                i++;
            }

            // compare in both arr
            int j = 0;
            while (i<n && i<m) {
                if (arr1[i] > arr2[j]) {
                    // swap
                    int temp = arr1[i];
                    arr1[i] = arr2[j];
                    arr2[j] = temp;
                }
                i++; j++;
            }

            // compare in second arr
            if (j<m) {
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

    // Union of two Sorted Array ( GFG ) => ( 2 Pointer Approach ) 
    public static ArrayList<Integer> unionOfTwo(int a[], int b[]) {
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

    // Intersection of two Arrays ( LeetCode 349 ) => ( HashSet Approach )
    public static int[] intersectionOfTwo(int arr1[], int arr2[]) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> ans = new HashSet<>();

        for (int num : arr1) {
            set.add(num);
        }

        for (int num : arr2) {
            if (set.contains(num)) {
                ans.add(num);
            }
        }

        // convert to array
        int result[] = new int[ans.size()];
        int i=0;

        for (int val : ans) {
            result[i++] = val;
        }

        return result;
    }

    // Intersection of two Arrays ( LeetCode 350 ) => ( HashMap Approach )
    public static int[] intersectionOfTwo2(int arr1[], int arr2[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int num : arr1) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }

        for (int num : arr2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                list.add(num);
                map.put(num, map.get(num)-1);
            }
        }

        // convert to array
        int result[] = new int[list.size()];
        int i=0;

        for (int val : list) {
            result[i++] = val;
        }

        return result;
    }

    // Intersection of two Arrays ( LeetCode 350 ) => ( Sorting + Two Pointer Approach )
    public static int[] intersectionOfTwo3(int arr1[], int arr2[]) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        ArrayList<Integer> list = new ArrayList<>();
        int i=0 , j=0;

        while (i < arr1.length  && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                list.add(arr1[i]);
                i++; j++;
            }
            else if (arr1[i] < arr2[j]) {
                i++;
            }
            else {
                j++;
            }
        }

        // convert
        int ans[] = new int[list.size()];
        int k=0;

        for (int val : list) {
            ans[k++] = val;
        }

        return ans;
    }

    // Common Elements in three sorted arrays ( GFG ) => ( 3 Pointer Approach )
    public static ArrayList<Integer> commonElements(int a[], int b[], int c[]) {
        ArrayList<Integer> list = new ArrayList<>();
        int i=0, j=0, k=0;

        while (i < a.length  && j < b.length  && k < c.length) {
            if (a[i] == b[j] && b[j] == c[k]) {
                if (list.isEmpty() || list.get(list.size()-1) != a[i]) {
                    list.add(a[i]);
                }
                i++; j++; k++;
            }
            else {
                int min = Math.min(a[i], Math.min(b[j], c[k]));

                if ( a[i] == min ) i++;
                if ( b[j] == min ) j++;
                if ( c[k] == min ) k++;
            }
        }
        return list;
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

    // Find the difference of two arrays (LeetCode) => ( brute force approach )
    public static List<List<Integer>> differenceOfTwoBruteForce(int arr1[], int arr2[]) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (int num : arr1) {
            boolean found = false;
            for (int n : arr2) {
                if (n == num) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                if (!list1.contains(num)) {
                    list1.add(num);
                }
            }
        }

        for (int num : arr2) {
            boolean found = false;
            for (int n : arr1) {
                if (n == num) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                if (!list2.contains(num)) {
                    list2.add(num);
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(list1);
        ans.add(list2);

        return ans;
    }

    // Square of a Sorted Array ( LeetCode 977 ) => ( Two Pointer Approach )
    public static int[] squareOfSortedArray(int[] nums) {
        int n = nums.length;
        int ans[] = new int[n];
        int k = n-1;

        int i=0, j=n-1;
        while (i < j) {
            int a = nums[i] * nums[i];
            int b = nums[j] * nums[j];

            if (a > b) {
                ans[k--] = a;
                i++;
            }
            else {
                ans[k--] = b;
                j--;
            }
        }

        return ans;
    }

    // Quick Sort 
    public static void quickSort(int[] arr, int s, int e) {
        if ( s > e ) {
            return;
        }

        int pivot = partition(arr, s , e);
        quickSort(arr, s, pivot-1);
        quickSort(arr, pivot+1, e);
    }

    public static int partition(int arr[], int s, int e) {
        int pivot = arr[e];
        int i = s-1;

        for (int j = s; j < e; j++) {
            if (arr[j] < pivot) {
                // swap
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // fix pivot pos
        i++;
        int temp = arr[i];
        arr[i] = arr[e];
        arr[e] = temp;

        return i;
    }

    // Binary Array Sorting ( GFG ) => ( partition approach )
    public static void binaryArraySorting(int arr[]) {
        int n = arr.length;
        int i = -1;

        for (int j = 0; j < n; j++) {
            if (arr[j] < 1) {
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
     public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length-1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                // swap
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++; mid++;
            }
            else if (nums[mid] == 1) {
                mid++;
            }
            else {
                // swap
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
        printArr(nums);
    }

    // Sort Colors ( LeetCode 75 ) => ( Partition approach )
    public static void sortColors2(int[] nums) {
        int n = nums.length;
        int i = -1;

        for (int j = 0; j < n; j++) {
            if (nums[j] == 0) {
                // swap
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        for (int k = i+1; k < n; k++) {
            if (nums[k] == 1) {
                // swap
                i++;
                int temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
            }
        }
        printArr(nums);
    }

    // Three way partitioning ( Dutch National Flag Algorithm ) => ( GFG )
    public static void threeWayPartitioning(int[] nums, int a, int b) {
        int low = 0, mid = 0, high = nums.length-1;

        while (mid <= high) {
            if (nums[mid] < a) {
                // swap
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++; mid++;
            }
            else if (nums[mid] >= a && nums[mid] <= b) {
                mid++;
            }
            else {
                // swap
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
        printArr(nums);
    }

    // Partintion array according to given pivot (order preserving)=> ( leetcode 2161 ) => ( Single Array approach )
    public static int[] partitionArray(int[] nums, int pivot) {
        int ans[] = new int[nums.length];
        int k = 0;

        for (int num : nums) {
            if (num < pivot) {
                ans[k++] = num;
            }
        }

        for (int num : nums) {
            if (num == pivot) {
                ans[k++] = num;
            }
        }

        for (int num : nums) {
            if (num > pivot) {
                ans[k++] = num;
            }
        }

        return ans;
    }

    // Move Zeros to end ( LeetCode 283 ) => ( Two Pointer Approach )
    public static void moveZerosToEnd(int[] nums) {
        int n = nums.length;
        int i = -1;

        for (int j = 0; j < n; j++) {
            if (nums[j] != 0) {
                // swap
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        printArr(nums);
    }

    // Move Zeros to end ( LeetCode 283 ) => ( Overwrite Approach )
    public static void moveZerosToEnd2(int[] nums) {
        int n = nums.length;
        int k = 0;

        for (int num : nums) {
            if (num != 0) {
                nums[k++] = num;
            }
        }

        while (k < n) {
            nums[k++] = 0;
        }
        printArr(nums);
    }

    // Sort Array by Parity ( LeetCode 905 ) => ( partition approach )
     public static void sortArrayByParity(int[] nums) {
        int n = nums.length;
        int i = -1;

        for (int j = 0; j < n; j++) {
            if (nums[j] % 2 == 0) {
                // swap
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        printArr(nums);
    }

    // Move all negative ele to end of array ( GFG ) => ( Single Array approach )
    public static void moveNegativeToEnd(int[] arr) {
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

    // Sort Array by Parity ( LeetCode 922 ) => ( Single Array Approach )
    public static void sortArrayByParityII(int[] nums) {
        int n = nums.length;
        int ans[] = new int[n];
        int even = 0, odd = 1;

        for (int num : nums) {
            if (num % 2 == 0) {
                ans[even] = num;
                even += 2;
            }
        }

        for (int num : nums) {
            if (num % 2 != 0) {
                ans[odd] = num;
                odd += 2;
        }
            }
        printArr(ans);
    }

    // Sort Array by Parity ( LeetCode 922 ) => ( Two Pointer Approach )
    public static void sortArrayByParityII2(int[] nums) {
        int n = nums.length;
        int even = 0, odd = 1;

        while (even < n && odd < n) {
            if (nums[even] % 2 == 0) {
                even += 2;
            }
            else if (nums[odd] % 2 != 0) {
                odd += 2;
            }
            else {
                // swap
                int temp = nums[even];
                nums[even] = nums[odd];
                nums[odd] = temp;
                even += 2;
                odd += 2;
            }
        }
        printArr(nums);
    }

    // Rearrange the array in alternating positive and negative items with O(1) extra space  (leetcode 2149) => ( Single Array Approach 
    public static void rearrangeAlternating(int[] nums) {
        int n = nums.length;
        int ans[] = new int[n];
        int pos = 0, neg = 1;

        for (int num : nums) {
            if (num >= 0) {
                ans[pos] = num;
                pos += 2;
            }
        }

        for (int num : nums) {
            if (num < 0) {
                ans[neg] = num;
                neg += 2;
            }
        }
        printArr(ans);
    }

    // Factorial of a number ( GFG ) => ( Recursion )
    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n-1);
    }

    // Factorial of a number ( GFG ) => ( Recursion => Tail Recursion )
    public static int factorialTailRecursion(int n, int ans) {
        if (n == 0 || n == 1) {
            return ans;
        }
        return factorialTailRecursion(n-1, n*ans);
    }

    // Fibonacci Number ( GFG ) => ( Recursion )
    public static int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    // Fibonacci Number ( GFG ) => ( Recursion => Tail Recursion )
    public static int fibonacciTailRecursion(int n, int a, int b) {
        if (n == 0) {
            return a;
        }
        if (n == 1) {
            return b;
        }
        return fibonacciTailRecursion(n-1, b, a+b);
    }

    // Print Increasing and Decreasing ( GFG ) => ( Recursion )
    public static void printIncreasingDecreasing(int n, int i) {
        if (i == n) {
            System.out.print(i+" ");
            return;
        }

        System.out.print(i+" ");
        printIncreasingDecreasing(n, i+1);
        System.out.print(i+" ");
    }

    // Check if array is sorted ( GFG ) => ( Recursion )
    public static boolean isSorted(int arr[], int i) {
        if (i == arr.length-1) {
            return true;
        }

        if (arr[i] > arr[i+1]) {
            return false;
        }

        return isSorted(arr, i+1);
    }

    // Tower of Hanoi ( GFG ) => ( Recursion )
    public static void towerOfHanoi(int n, char src, char dest, char helper) {
        if (n == 0) {
            return;
        }

        towerOfHanoi(n-1, src, helper, dest);
        System.out.println("Move disk "+n+" from "+src+" to "+dest);
        towerOfHanoi(n-1, helper, dest, src);
    }

    // Largest Element in an Array ( GFG ) => ( Recursion )
    public static int largestElement(int arr[], int i) {
        if (i == arr.length-1) {
            return arr[i];
        }

        int max = largestElement(arr, i+1);
        return Math.max(arr[i], max);
    }

    public static int largestElement2(int arr[], int i, int max) {
        if (i == arr.length) {
            return max;
        }

        return largestElement2(arr, i+1, Math.max(max, arr[i]));
    }

    // Find first and last occurrence of an element in an array ( GFG ) => ( Recursion )
    public static int[] firstAndLastOccurrence(int arr[], int i, int key) {
        if (i == arr.length) {
            return new int[]{-1, -1};
        }

        int res[] = firstAndLastOccurrence(arr, i+1, key);
        if (arr[i] == key) {
            res[0] = i;
            if (res[1] == -1) {
                res[1] = i;
            }
        }
        return res;
    }

    public static void firstAndLastOccurrence2(int arr[], int i, int key, int res[]) {
        if (i == arr.length) {
            return;
        }

        if (arr[i] == key) {
            if (res[0] == -1) {
                res[0] = i;
            }
            res[1] = i;
        }

        firstAndLastOccurrence2(arr, i+1, key, res);
    }

    // Power of a number ( leetcode 50 ) => ( Recursion )
    public static double power(double x, int n) {
        if (n == 0) return 1;

        if (n < 0) {
            x = 1/x;
            n = -n;
        }

        double half = power(x, n/2);
        double ans = half * half;

        if (n % 2 != 0) {
            ans *= x;
        }
        return ans;
    }

    // Reverse the Stack ( GFG ) => ( Recursion )
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        int top = stack.pop();
        reverseStack(stack);
        insertAtBottom(stack, top);
    }

    public static void insertAtBottom(Stack<Integer> stack, int val) {
        if (stack.isEmpty()) {
            stack.push(val);
            return;
        }

        int top = stack.pop();
        insertAtBottom(stack, val);
        stack.push(top);
    }

    // Reverse the stack ( GFG ) => ( Recursion with extra space )
    public static void reverseStackWithExtraSpace(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        int top = stack.pop();
        reverseStackWithExtraSpace(stack);

        Stack<Integer> temp = new Stack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }

        stack.push(top);
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    // Subsets ( LeetCode 78 ) => ( Recursion => Backtracking )
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        generateSubsets(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    public static void generateSubsets(int[] nums, int idx, List<Integer> curr, List<List<Integer>> ans) {
        if (idx == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        // Exclude current element
        generateSubsets(nums, idx + 1, curr, ans);

        // Include current element
        curr.add(nums[idx]);
        generateSubsets(nums, idx + 1, curr, ans);
        curr.remove(curr.size() - 1);
    }

    // Subsets II ( LeetCode 90 ) => ( Recursion => Backtracking )
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); // Sort to handle duplicates
        generateSubsetsWithDup(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    public static void generateSubsetsWithDup(int[] nums, int idx, List<Integer> curr, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(curr));

        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i] == nums[i - 1]) {
                continue;
            }
            curr.add(nums[i]);
            generateSubsetsWithDup(nums, i + 1, curr, ans);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        // int arr[] = { 2, 8, 4, 10, 6, 8};
        // bubbleSort(arr);
        // selectionSort(arr);
        // insertionSort(arr);
        // shellSort(arr);

        // int sortedArr[] = mergeSort(arr, 0, arr.length-1);
        // printArr(sortedArr);

        // int arr1[] = {1, 2, 3, 0, 0, 0};
        // int arr2[] = {2, 5, 6};
        // int n = 3, m = 3;

        // // int mergedArr[] = mergeSorted(arr1, n, arr2, m);
        // int mergedArr[] = mergeSorted2(arr1, n, arr2, m);
        // printArr(mergedArr);

        // mergeGap(new int[]{1, 4, 7, 8, 10}, new int[]{2, 3, 9});

        // int a[] = {1, 2, 4, 5, 6};
        // int b[] = {2, 3, 5, 7};
        // System.out.println(unionOfTwo(a, b));

        // int arr1[] = {1, 2, 2, 1};
        // int arr2[] = {2, 2};
        // System.out.println(Arrays.toString(intersectionOfTwo(arr1, arr2)));
        // System.out.println(Arrays.toString(intersectionOfTwo2(arr1, arr2)));
        // System.out.println(Arrays.toString(intersectionOfTwo3(arr1, arr2)));

        // System.out.println(commonElements(new int[]{1, 5, 10, 20, 80}, new int[]{ 10, 20, 80}, new int[]{5, 10, 20, 80}));

        //  int arr3[] = {1, 2, 3, 3};
        //  int arr4[] = {2, 4, 6};
        //  System.out.println(differenceOfTwo(arr3, arr4));
        //  System.out.println(differenceOfTwoBruteForce(arr3, arr4));

        // int arr5[] = {-4, -1, 0, 3, 10};
        // System.out.println(Arrays.toString(squareOfSortedArray(arr5)));

        // int arr6[] = {10, 7, 8, 9, 1, 5};
        // quickSort(arr6, 0, 5);
        // printArr(arr6);

        // int arr7[] = {0, 1, 0, 1, 1, 0};
        // binaryArraySorting(arr7);

        // sortColors(new int[]{2, 0, 2, 1, 1, 0});
        // sortColors2(new int[]{2, 0, 2, 1, 1, 0});

        // threeWayPartitioning(new int[]{1, 12, 3, 4, 5, 6, 7}, 3, 5);

        // System.out.println(Arrays.toString(partitionArray(new int[]{9, 12, 3, 5, 14, 10, 10}, 10)));

        // moveZerosToEnd(new int[]{10, 1, 0, 3, 0});
        // moveZerosToEnd2(new int[]{10, 1, 0, 3, 0});

        // sortArrayByParity(new int[]{3, 1, 2, 4});
        // sortArrayByParityII(new int[]{4, 2, 5, 7});
        // sortArrayByParityII2(new int[]{4, 2, 5, 7});
        // moveNegativeToEnd(new int[]{-1, 2, -3, 4, 5, -6});

        // rearrangeAlternating(new int[]{1, 2, -3, -4, 5});

        // System.out.println(factorial(5));
        // System.out.println(factorialTailRecursion(5, 1));
        // System.out.println(fibonacci(10));
        // System.out.println(fibonacciTailRecursion(10, 0, 1));
        // printIncreasingDecreasing(5, 1);
        // System.out.println(isSorted(new int[]{1, 2, 3, 4}, 0));
        // towerOfHanoi(3, 'A', 'C', 'B');

        // System.out.println(largestElement(new int[]{1, 2, 3, 4}, 0));
        // System.out.println(largestElement2(new int[]{1, 2, 3, 4}, 0, Integer.MIN_VALUE));
        // System.out.println(Arrays.toString(firstAndLastOccurrence(new int[]{1, 2, 3, 2, 1}, 0, 2)));
        // int res[] = {-1, -1};
        // firstAndLastOccurrence2(new int[]{1, 2, 3, 2, 1}, 0, 2, res);
        // System.out.println(Arrays.toString(res));
        // System.out.println(power(2, 10));

        //  Stack<Integer> stack = new Stack<>();
        //  stack.push(1);
        //  stack.push(2);
        //  stack.push(3);
        //  reverseStack(stack);
        // reverseStackWithExtraSpace(stack);
        // System.out.println(stack);

        // System.out.println(subsets(new int[]{1, 2, 3}));
        // System.out.println(subsetsWithDup(new int[]{1, 2, 2}));


        
    }
}