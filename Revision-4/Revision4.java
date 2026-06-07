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

    // Square of a Sorted Array ( leetcode 977 ) => ( Two Pointer Approach )
    public static int[] squareOfSortedArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;
        int index = n - 1;

        while (left <= right) {
            int first = nums[left] * nums[left];
            int second = nums[right] * nums[right];

            if (first > second) {
                result[index--] = first;
                left++;
            } else {
                result[index--] = second;
                right--;
            }
        }

        return result;
    }

    // Qucik Sort Algorithm
    public static void quickSort(int arr[], int low, int high) {
        if (low > high) return;

        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }

    public static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap pivot to correct position
        i++;
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;

        return i;
    }

    // Binary Array Sorting ( GFG ) => ( Two Pointer Approach )
    public static void binaryArraySorting(int[] arr) {
        int low = 0, high = arr.length - 1;

        while (low < high) {
            if (arr[low] == 0) {
                low++;
            } else {
                // swap
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
                high--;
            }
        }
    }

    // Binary Array Sorting ( GFG ) => ( Partition Approach )
    public static void binaryArraySorting2(int[] arr) {
        int pivot = 1;
        int i = -1;

        for (int j = 0; j < arr.length; j++) {
            if (arr[j] < pivot) {
                i++;
                // swap
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }

    // Sort Colors ( leetcode 75 ) => ( Dutch National Flag Algorithm )
    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                // swap
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                // swap
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }

    // Sort Colors ( leetcode 75 ) => ( Partition Approach two times )
    public static void sortColors2(int[] nums) {
        int pivot = 2;
        int i = -1;

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] < pivot) {
                i++;
                // swap
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        i = -1;
        pivot = 1;

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] < pivot) {
                i++;
                // swap
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
    }

    // Three Way Partitioning ( Dutch National Flag Algorithm ) => ( GFG )
    public static void threeWayPartitioning(int[] arr, int lowVal, int highVal) {
        int low = 0, mid = 0, high = arr.length - 1;

        while (mid <= high) {
            if (arr[mid] < lowVal) {
                // swap
                int temp = arr[low];
                arr[low] = arr[mid];
                arr[mid] = temp;
                low++;
                mid++;
            } else if (arr[mid] >= lowVal && arr[mid] <= highVal) {
                mid++;
            } else {
                // swap
                int temp = arr[mid];
                arr[mid] = arr[high];
                arr[high] = temp;
                high--;
            }
        }
    }

    // Move Zeros to end ( leetcode 283 ) => ( OverWrite Approach )
    public static void moveZerosToEnd(int[] nums) {
        int k = 0;
        for ( int num : nums) {
            if (num != 0) {
                nums[k++] = num;
            }
        }
        while (k < nums.length) {
            nums[k++] = 0;
        }
    }

    // Sort Array By Parity ( leetcode 905 ) => ( Partition Approach )
    public static void sortArrayByParity(int[] nums) {
        int i = -1;

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] % 2 == 0) {
                i++;
                // swap
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
    }

    // Move all negative ele to end ( GFG ) => ( Single Array Approach )
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

        for (int i = 0; i < arr.length; i++) {
            arr[i] = ans[i];
        }
    }

    // Sort the Array By Parity II ( leetcode 922 ) => ( Two Pointer Approach )
    public static void sortArrayByParityII(int[] nums) {
        int evenIdx = 0;
        int oddIdx = 1;

        while (evenIdx < nums.length && oddIdx < nums.length) {
            if (nums[evenIdx] % 2 == 0) {
                evenIdx += 2;
            } else if (nums[oddIdx] % 2 == 1) {
                oddIdx += 2;
            } else {
                // swap
                int temp = nums[evenIdx];
                nums[evenIdx] = nums[oddIdx];
                nums[oddIdx] = temp;

                evenIdx += 2; oddIdx += 2;
            }
        }
    }

    // Sort the Array By Parity II ( leetcode 922 ) => ( Single Pass Approach )
    public static void sortArrayByParityII2(int[] nums) {
        int evenIdx = 0;
        int oddIdx = 1;
        int ans[] = new int[nums.length];

        for (int num : nums) {
            if (num % 2 == 0) {
                ans[evenIdx] = num;
                evenIdx += 2;
            } else {
                ans[oddIdx] = num;
                oddIdx += 2;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ans[i];
        }
    }

    // Rearrange the array ele by sign ( leetcode 2149 ) => ( Single Pass Approach )
    public static void rearrangeArrayBySign(int[] nums) {
        int posIdx = 0;
        int negIdx = 1;
        int ans[] = new int[nums.length];

        for (int num : nums) {
            if (num >= 0) {
                ans[posIdx] = num;
                posIdx += 2;
            } else {
                ans[negIdx] = num;
                negIdx += 2;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ans[i];
        }
    }

    // Factorial of a number ( GFG ) => ( Recursive Approach )
    public static long factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }

    // Factorial of a number ( GFG ) => ( Tail Recursive Approach )
    public static long factorialTailRecursive(int n, long ans) {
        if (n == 0 || n == 1) return ans;
        return factorialTailRecursive(n - 1, n * ans);
    }

    // Fibonacci Number ( GFG ) => ( Recursive Approach )
    public static long fibonacci(int n) {
        if (n == 0 || n == 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Fibonacci Number ( GFG ) => ( Tail Recursive Approach )
    public static long fibonacciTailRecursive(int n, long a, long b) {
        if (n == 0) return a;
        if (n == 1) return b;
        return fibonacciTailRecursive(n - 1, b, a + b);
    }

    // Print Increasing and Decreasing ( GFG ) => ( Recursive Approach )
    public static void printIncreasingDecreasing(int n, int i) {
        if (i == n) {
            System.out.print(n + " ");
            return;
        }
        System.out.print(i + " ");
        printIncreasingDecreasing(n, i + 1);
        System.out.print(i + " ");
    }

    // Check if the array is sorted or not ( GFG ) => ( Recursive Approach )
    public static boolean isSorted(int arr[], int i) {
        if (i == arr.length - 1) return true;
        if (arr[i] > arr[i + 1]) return false;
        return isSorted(arr, i + 1);
    }

    // Tower of Hanoi ( GFG ) => ( Recursive Approach )
    public static void towerOfHanoi(int n, char src, char dest, char helper) {
        if (n == 1) {
            System.out.println("Move disk 1 from " + src + " to " + dest);
            return;
        }
        towerOfHanoi(n - 1, src, helper, dest);
        System.out.println("Move disk " + n + " from " + src + " to " + dest);
        towerOfHanoi(n - 1, helper, dest, src);
    }

    // Largest Element in an Array ( GFG ) => ( Recursive Approach )
    public static int largestElement(int arr[], int i) {
        if (i == arr.length - 1) return arr[i];
        return Math.max(arr[i], largestElement(arr, i + 1));
    }

    // Largest Element in an Array ( GFG ) => ( Tail Recursive Approach )
    public static int largestElementTailRecursive(int arr[], int i, int max) {
        if (i == arr.length) return max;
        if (arr[i] > max) {
            max = arr[i];
        }
        return largestElementTailRecursive(arr, i + 1, max);
    }

    // First and Last Occurrence of an Element in an Array ( GFG ) => ( Recursive Approach )
    public static int[] firstAndLastOccurrence(int arr[], int i, int target, int first, int last) {
        if (i == arr.length) return new int[]{first, last};
        if (arr[i] == target) {
            if (first == -1) {
                first = i;
            }
            last = i;
        }
        return firstAndLastOccurrence(arr, i + 1, target, first, last);
    }

    // Power of a Number ( leetcode 50 ) => ( Recursive Approach )
    public static double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        double half = myPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    // Reverse the Stack ( GFG ) => ( Recursive Approach )
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) return;
        int top = stack.pop();
        reverseStack(stack);
        insertAtBottom(stack, top);
    }

    public static void insertAtBottom(Stack<Integer> stack, int value) {
        if (stack.isEmpty()) {
            stack.push(value);
            return;
        }
        int top = stack.pop();
        insertAtBottom(stack, value);
        stack.push(top);
    }

    // Reverse the Stack ( GFG ) => ( Recursive Approach with extra Stack )
    public static void reverseStackWithExtraStack(Stack<Integer> stack) {
        if (stack.isEmpty()) return;

        int top = stack.pop();
        reverseStackWithExtraStack(stack);

        Stack<Integer> tempStack = new Stack<>();
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }

        stack.push(top);
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
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

        // int nums1[] = {1, 2, 3};
        // int nums2[] = {2, 4, 6};
        // System.out.println(findDifference(nums1, nums2));
        // System.out.println(findDifference2(nums1, nums2));

        // int nums[] = {-4, -1, 0, 3, 10};
        // System.out.println(Arrays.toString(squareOfSortedArray(nums)));

        // int nums[] = {10, 7, 8, 9, 1, 5};
        // quickSort(nums, 0, nums.length-1);
        // printArr(nums);

        // int nums[] = {0, 1, 0, 1, 1, 0};
        // binaryArraySorting(nums);
        // binaryArraySorting2(nums);
        // printArr(nums);

        // int nums[] = {2, 0, 2, 1, 1, 0};
        // sortColors(nums);
        // sortColors2(nums);
        // printArr(nums);

        // int nums[] = {1, 4, 3, 6, 2, 1};
        // threeWayPartitioning(nums, 1, 3);
        // printArr(nums);

        // int nums[] = {0, 1, 0, 3, 12};
        // moveZerosToEnd(nums);
        // printArr(nums);

        // int nums[] = {3, 1, 2, 4};
        // sortArrayByParity(nums);
        // printArr(nums);

        // int arr[] = {-1, 2, -3, 4, 5, -6};
        // moveNegativeToEnd(arr);
        // printArr(arr);

        // int nums[] = {4, 2, 5, 7, 6, 1, 3, 0};
        // sortArrayByParityII(nums);
        // sortArrayByParityII2(nums);
        // printArr(nums);

        // int nums2[] = {3, 1, -2, -5, 2, -4};
        // rearrangeArrayBySign(nums2);
        // printArr(nums2);

        // System.out.println(factorial(5));
        // System.out.println(factorialTailRecursive(5, 1));
        // System.out.println(fibonacci(10));
        // System.out.println(fibonacciTailRecursive(10, 0, 1));
        // printIncreasingDecreasing(5, 1);
        // int arr[] = {1, 2, 3, 4, 5};
        // System.out.println(isSorted(arr, 0));
        // towerOfHanoi(3, 'A', 'C', 'B');

        // int arr[] = {1, 5, 3, 9, 2};
        // System.out.println(largestElement(arr, 0));
        // System.out.println(largestElementTailRecursive(arr, 0, Integer.MIN_VALUE));
        // int arr[] = {1, 2, 3, 4, 2, 5};
        // System.out.println(Arrays.toString(firstAndLastOccurrence(arr, 0, 2, -1, -1)));
        // System.out.println(myPow(2.0, 10));
        // Stack<Integer> stack = new Stack<>();
        // stack.push(1);
        // stack.push(2);  
        // stack.push(3);
        // // reverseStack(stack);
        // reverseStackWithExtraStack(stack);
        // System.out.println(stack);
        
        


    }
}
