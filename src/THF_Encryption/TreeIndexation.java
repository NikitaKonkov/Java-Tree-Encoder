package THF_Encryption;
public class TreeIndexation {



    /**
     * Generates an array of length (2 ^ (n + 1)) - 1, used for indexing byte characters.
     * @param n the input parameter for generating the array
     * @return the generated array
     * @throws IllegalArgumentException if n is not between 1 and 28
     * @throws IllegalStateException if generated array is corrupted
     */
    public static int[] TreeArray(int n) {
        if (n < 1 || n > 28) throw new IllegalArgumentException("TreeIndexation: [Value must be between 1 & 28]");

        int N = (1 << (n + 1)) - 1; // Calculate the length of the array
        int[] A = new int[N];
        A[0] = (1 << n) - 1; // Initialize the first element
        int K = 1;
        int L = 2;

        // Populate the array using bitwise operations
        for (int k = n - 1; k >= 0; --k) {
            int b = (1 << k) - 1;
            int s = 1 << (k + 1);
            for (int i = 0; i < L; ++i) {
                A[K + i] = b + i * s;
            }
            K += L;
            L *= 2;
        }

        // Verify the sum of the array
        if (A134057(n) != TreeSum(A)) throw new IllegalStateException("TreeIndexation: [Tree index corruption]");

        return A;
    }




    /**
     * Sums up the given array and returns the result as a long.
     * @param A the array to sum
     * @return the sum of the array elements
     */
    private static long TreeSum(int[] A) {
        long s = 0;
        for (int n : A) {
            s += n;
        }
        return s;
    }



    /**
     * Generates a sequence based on (2^n - 1, 2) to check the sum.
     * @param n the input parameter for generating the sequence
     * @return the sequence value at position t+1
     */
    private static long A134057 (int n) {
        long[] s = new long[n + 2];
        for (int i = 0; i < n + 2; i++) {
            long p = (1L << i); // 2^n
            long m = (p - 1) * (p - 2) / 2;
            s[i] = m;
        }
        return s[n + 1];
    }

    //public static void main(String[] args) {
    //    System.out.println(Arrays.toString(TreeArray(12)));
    //}
}
