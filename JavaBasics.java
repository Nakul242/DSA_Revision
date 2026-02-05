public class JavaBasics {
    public static int sum(int a, int b) {
        return a+b;
    }

    public static int simpleInterest(int p, int r, int y) {
        return (p * r * y) / 100;
    }

    public static int maxNumber(int a, int b, int c) {
        if(a>b && a>c) {
            return a;
        }
        else if(b>c) {
            return b;
        }
        else {
            return c;
        }
    }

    public static boolean isPrime(int n) {
        for(int i=2; i<=Math.sqrt(n); i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int sumNaturalNo(int n) {
        int sum = 0;
        for (int i=1; i<=n; i++) {
            sum += i;
        }
        return sum;
    }

    public static void EvenOrOdd(int s, int e) {
        for(int i=s; i<e; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }

    public static int factorial(int n) {
        int fact = 1;
        for(int i=n; i>=1; i--) {
            fact *= i;
        }
        return fact;
    }

    public static void bottomLeft(int n) {
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col<=n; col++) {
                if (col - row <= 0){
                    System.out.print(" *");
                }
            }
            System.out.println();
        }
    }

    public static void bottomRight(int n) {
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (col + row >= n+1){
                    System.out.print(" *");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public static void topLeft(int n) {
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col<=n; col++) {
                if (col + row <= n+1){
                    System.out.print(" *");
                }
            }
            System.out.println();
        }
    }

    public static void bottomLeft_Num(int n) {
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col<=n; col++) {
                if (col - row <= 0){
                    System.out.print(" "+col);
                }
            }
            System.out.println();
        }
    }

    public static void bottomLeft_Char(int n) {
        char ch = 'A';
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col<=n; col++) {
                if (col - row <= 0){
                    System.out.print(" " + ch++);
                }
            }
            System.out.println();
        }
    }

    public static int binToDec(int n) {
        int i = 0;
        int dec = 0;
        while (n!=0) {
            int ld = n % 10;
            dec += ld * (int) Math.pow(2, i);
            i++;
            n/=10;
        }
        return dec;
    }

    public static int decToBin(int n) {
        int bin = 0;
        int i = 0;

        while (n != 0) {
            int rem = n % 2;
            bin += rem * (int) Math.pow(10, i);
            i++;
            n /= 2;
        }
        return bin;
    }

    public static boolean isPalindrome(int n) {
        int org = n;
        int rev = 0;
        while (n!=0) {
            int ld = n % 10;
            rev = rev * 10 + ld;
            n/=10;
        }
        return org == rev;
    }

    // Count number of digits in a number
    public static int countDigit(int n) {
        int count = 0;
        while(n != 0) {
            n = n/10;
            count++;
        }
        return count;
    }

    // Reverse a number
    public static int reverse(int n) {
        int rev = 0;
        while (n != 0) {
            int ld = n % 10;
            rev = rev * 10 + ld;
            n/=10;
        }
        return rev;
    }

    // Count how many times digit x appears in a number
    public static int countAppear(int n, int x) {
        int count = 0;
        while (n != 0) {
            int ld = n % 10;
            if ( ld == x) {
                count++;
            }
            n/=10;
        }
        return count;
    }

    // Count how many times digit x appears from l to u
    public static int countAppearRange(int x, int s, int e) {
        int count = 0;
        for(int i=s; i<e; i++) {
            int n = i;

            while (n != 0) {
                int ld = n % 10;
                if ( ld == x) {
                    count++;
                }
    
                n/=10;
            }
        }
        return count;
    }

    // Find sum of digits of a number
    public static int sumOfDigit(int n) {
        int sum = 0;
        while ( n!=0 ) {
            int ld = n % 10;
            sum += ld;
            n/=10;
        }
        return sum;
    }

    // Print Hollow rectangle
    public static void hollowRect(int i, int j) {
        for (int row = 1; row <= i; row++) {
            for (int col = 1; col<=j; col++) {
                if (col == 1 || col == j || row == 1 || row == i) {
                    System.out.print(" *");
                }
                else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    // Print 0-1 triangle
    public static void triangle(int n) {
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col<=n; col++) {
                if (col - row <= 0){
                    if ((col - row) % 2 == 0) {
                        System.out.print(" 1");
                    }
                    else {
                        System.out.print(" 0");
                    }
                }
            }
            System.out.println();
        }
    }

    // Print butterfly pattern
    public static void butterfly(int n) {
        int total_no_of_lines = 2*n;
        int stars = 1;
        int spaces = 2 * n - 2;                      
        int current_no_of_line = 1;

        while (current_no_of_line <= total_no_of_lines) {
            // stars
            for (int i=1; i<=stars; i++) {
                System.out.print(" *");
            }

            // spaces
            for (int i=1; i<=spaces; i++) {
                System.out.print("  ");
            }

            // stars
            for (int i=1; i<=stars; i++) {
                System.out.print(" *");
            }

            // next line 
            System.out.println();
            if (current_no_of_line < n) {
                stars++;
                spaces-=2;
            }

            else if (current_no_of_line == n) {
                // continue;
            }

            else {
                stars--;
                spaces+=2;
            }
            current_no_of_line++;
        }
    }

    // print rhombus pattern
    public static void rhombus(int n) {
        int total_no_of_lines = n;
        int stars = n;
        int spaces = n-1;
        int current_no_of_line = 1;

        while (current_no_of_line <= total_no_of_lines) {
            // spaces
            for (int i=1; i<=spaces; i++) {
                System.out.print("  ");
            }

            // stars
            for (int i=0; i<stars; i++) {
                System.out.print("* ");
            }

            // update
            System.out.println();
            current_no_of_line++;
            spaces--;
        }
    }

    // print hollow rhombus pattern
    public static void hollowRhombus(int n) {
        int total_no_of_lines = n;
        int stars = n;
        int spaces = n-1;
        int spaces2 = n-2;
        int current_no_of_line = 1;

        while (current_no_of_line <= total_no_of_lines) {
            // spaces
            for (int i=1; i<=spaces; i++) {
                System.out.print("  ");
            }

            if (current_no_of_line == 1  || current_no_of_line == n) {
                // stars
                for (int i=0; i<stars; i++) {
                    System.out.print("* ");
                }
            }
            else {
                // stars
                System.out.print("* ");

                // spaces2
                for (int i=1; i<=spaces2; i++) {
                    System.out.print("  ");
                }

                // stars
                System.out.print("* ");
            }



            // update
            System.out.println();
            current_no_of_line++;
            spaces--;
        }
    }

    // print a diamond pattern
    public static void diamond(int n) {
        int total_no_lines = 2*n;
        int stars = 1;
        int spaces = n-1;
        int current_no_line = 1;

        while (current_no_line <= total_no_lines) {
            // spaces
            for (int i=1; i<=spaces; i++) {
                System.out.print("  ");
            }

            // stars
            for(int i=1; i<=stars; i++) {
                System.out.print("* ");
            }

            // update for next line
            System.out.println();
            if (current_no_line < n) {
                stars+=2;
                spaces--;
            }
            else if (current_no_line == n) {
                // continue;
            }
            else {
                stars-=2;
                spaces++;
            }
            current_no_line++;
            
        }
    }
    
    public static void main(String[] args) {
        // System.out.println(sum(24, 2));
        // System.out.println(simpleInterest(1000, 2, 1));
        // System.out.println(maxNumber(4, 8, 5));
        // System.out.println(isPrime(5));
        // System.out.println(sumNaturalNo(15));
        // EvenOrOdd(9, 100);
        // System.out.println(factorial(4));
        // bottomLeft(4);
        // bottomRight(4);
        // topLeft(4);
        // bottomLeft_Num(8);
        // bottomLeft_Char(5);
        // System.out.println(binToDec(10102));
        // System.out.println(decToBin(11));
        // System.out.println(countDigit(212334));
        // System.out.println(reverse(12323));
        // System.out.println(countAppear(12233223, 1));
        // System.out.println(countAppearRange( 2, 5, 100));
        // System.out.println(sumOfDigit(12345));
        // hollowRect(4, 5);
        // triangle(5);
        // butterfly(4);
        // rhombus(4);
        // hollowRhombus(6);
        // diamond(4);
        
    }
}