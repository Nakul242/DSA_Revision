import java.util.*;
public class BenchmarkTest {
    // Question 1 — Sum of Prime Factors
    public static int sumOfPrimeFactors(int n) {
        int sum = 0;
        for (int i=2; i*i<=n; i++) {
            while (n % i == 0) {
                sum += i;
                n /= i;
            }
        }

        if (n > 1) {
            sum += n;
        }
        return sum;
    }

    // Question 2 — Segregating Even and Odd in Linked List
    public static void segregateEvenOdd(int arr[]) {
        // Approach 1: Using two ArrayLists to store even and odd numbers separately
        ArrayList<Integer> even = new ArrayList<>();
        ArrayList<Integer> odd = new ArrayList<>();

        for(int i = 0; i < arr.length; i++) {

            int x = arr[i];

            if(x % 2 == 0)
                even.add(x);
            else
                odd.add(x);
        }

        for(int x : even)
            System.out.print(x + " ");

        for(int x : odd)
            System.out.print(x + " ");
    }

    public static void segregateEvenOdd2(int arr[]) {
        // Approach 2: Using two LinkedLists to store even and odd numbers separately
        LinkedList<Integer> even = new LinkedList<>();
        LinkedList<Integer> odd = new LinkedList<>();

        for(int i = 0; i < arr.length; i++) {

            int x = arr[i];

            if(x % 2 == 0)
                even.add(x);
            else
                odd.add(x);
        }

        even.addAll(odd); // Combine even and odd lists and can be used in arraylist as well

        for(int x : even)
            System.out.print(x + " ");
    }

    // Question 3 — Remove blocks at odd positions
    public static void blocks(int n) {
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 1; i <= n; i++) {
            list.add(i);
        }
        // System.out.println(list.size());

        while(list.size() != 1) {
            ArrayList<Integer> temp = new ArrayList<>();
            for(int i=1; i<list.size(); i+=2) {
                temp.add(list.get(i));
            }
            list = temp;
        }

        System.out.println(list.get(0));
    }

    public static void blocks2(int n) {
        // Approach 2: By observing the pattern, we can directly calculate the last remaining block without simulating the process
        // The last remaining block will always be the largest power of 2 less than or equal to n. This is because in each round, we are removing blocks at odd positions, which effectively halves the number of blocks until only one remains.
        int power = 1;
        while (power * 2 <= n) {
            power *= 2;
        }
        System.out.println(power);
    }

    // Question 4 — Remove Linked List Elements
    public static void removeEle(int arr[], int val) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int x : arr) {
            if (x != val) {
                list.add(x);
            }
        }

        if(list.size() == 0){
            System.out.println("List is empty");
            return;
        }
        
        for (int x : list) {
            System.out.print(x + " ");
        }
    }

    // Question 5 — Digital grouping with custom separator
    public static void digitalGrouping(String num, char delimiter) {
        if(num.length() < 4 || num.charAt(0) == '0'){
            System.out.println("Invalid input");
            return;
        }

        String lastThree = num.substring(num.length()-3);
        String remaining = num.substring(0,num.length()-3);

        StringBuilder result = new StringBuilder();
        int i = remaining.length();

        while(i > 2){
            result.insert(0, delimiter + remaining.substring(i-2,i));
            i -= 2;
        }

        if(i > 0){
            result.insert(0, remaining.substring(0,i));
        }

        result.append(delimiter).append(lastThree); // ✅ add delimiter before last three

        System.out.println(result);

    }

    // Question 6 — Palindrome Check0
    public static boolean isPalindrome(int arr[] ) {
        // Approach 1: Using two pointers to compare elements from the start and end of the array
        int left = 0, right = arr.length - 1;

        while (left < right) {
            if (arr[left] != arr[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isPalindrome2(int arr[]) {
        // Approach 2: Using a linked list to store the elements and then comparing elements from the start and end of the list
        // as asked in the question, we have to use linked list, but we can also use arraylist as well, the logic will be same
        LinkedList<Integer> list = new LinkedList<>();

        for(int i=0;i<arr.length;i++){
            list.add(arr[i]);
        }

        int left = 0;
        int right = list.size() - 1;

        while(left < right){

            if(!list.get(left).equals(list.get(right))){
                System.out.println("No");
                return false;
            }

            left++;
            right--;
        }

        System.out.println("Yes");
        return true;
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // System.out.println(sumOfPrimeFactors(17)); // Output: 17 (2 + 3 + 5 + 7)
        // segregateEvenOdd(arr);
        // System.out.println();
        // segregateEvenOdd2(arr);
        // blocks(4); // Output: 4
        // blocks2(4); // Output: 4
        // removeEle(arr, 5); // Output: 1 2 3 4 6 7 8 9 10
        digitalGrouping("100000", ','); // Output: 100,000

    }
}