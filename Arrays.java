public class Arrays {
    // Binary Search
    public static int binarySearch(int arr[] , int key) {
        int start = 0;
        int end = arr.length-1;
        while (start <= end) {
            int mid = (start + end ) / 2;
            if (arr[mid] == key) {
                return mid;
            }
            else if (arr[mid] < key) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        return -1;
    }

    // largest element in the array
    public static int lagestEle(int arr[]) {
        int max = Integer.MIN_VALUE;
        for(int i=0; i<arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // linear search
    public static int linearSearch(int arr[], int key) {
        for (int i=0; i<arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    // Reverse an Array
    public static void reverseArr(int arr[]) {
        int start = 0;
        int end = arr.length-1;

        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }

        // print the array
        printArr(arr);
    }

    // print the array
    public static void printArr(int arr[]) {
        for (int i=0; i<arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    // Find pairs in Array
    public static void pairsArr(int arr[]) {
        for(int i=0; i<arr.length; i++) {
            for(int j=i+1; j<arr.length; j++) {
                System.out.print( "[" + arr[i] + " " +  arr[j] + "]" + " ");
            }
            System.out.println();
        }
    }

    // Print subarrays
    public static void subArray(int arr[]) {
        for (int i=0; i<=arr.length; i++) {
            for (int j=i; j<arr.length; j++) {
                for (int k=i; k<=j; k++) {
                    System.out.print(arr[k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    // max Subarray sum (brute force)
    public static int maxSubArr(int arr[]) {
        int max = Integer.MIN_VALUE;
        for(int i=0; i<arr.length; i++) {
            for(int j=i; j<arr.length; j++) {
                int sum = 0;
                for(int k=i; k<=j; k++) {
                    sum+=arr[k];
                    if (sum > max) {
                        max = sum;
                    }
                }
            }
        }
        return max;
    }

    // max Subarray sum (Prefix Sum)
    public static int maxSubArr2(int arr[]) {
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int prefix[] = new int [n];
        prefix[0] = arr[0];

        for (int i=1; i<n; i++) {
            prefix[i] = prefix[i-1] + arr[i];
        }

        for (int i=0; i<n; i++) {
            for (int j=i; j<n; j++) {
                if ( i==0) {
                    sum = prefix[j];
                }
                else {
                    sum = prefix[j] - prefix[i-1];  // Because prefix[j] includes extra elements before i, so we remove them.
                }

                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    } 

    public static void main(String[] args) {
        int[] arr = {11, -2, -3, -4, -5};

        // System.out.println(binarySearch(arr , 4));
        // System.out.println(lagestEle(arr));
        // System.out.println(linearSearch(arr, 4));
        // reverseArr(arr);
        // pairsArr(arr);
        // subArray(arr);
        System.out.println(maxSubArr(arr));
        System.out.println(maxSubArr2(arr));
    }
}
