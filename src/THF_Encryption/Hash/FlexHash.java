package THF_Encryption.Hash;
public class FlexHash {



    /**
     * Hash generator, uses statistical calculations and arithmetic hashing.
     * @param input string to hash
     * @param size size of the hash
     * @return return the final hash
     */
    private static byte[] Generator(byte[] input , int size){
        int[] P =  { // 16 Primes
                0x35,0x61,0xC1,0x185,
                0x301,0x607,0xC07,0x1807,
                0x3001,0x6011,0xC005,0x1800D,
                0x30005,0x60019,0xC0001,0x180005
        };
        long[] h = new long[size];
        long[] H = new long[size * 2];
        long K = 0,
             L = 0,
             M = 0,
             N = 0;
        double O = 0.0;
        int i = 0;
        int l = input.length;
        byte[] out = new byte[l];
        System.arraycopy(input, 0, out, 0, l);
        for (byte s: out) {
            l += P[0xF - i % 0x10] * l + s;
            i++;
        }
        for(int n = 0; n < size; n++){
            l = (P[n % 0x10] * l + n);
            H[n % 0x100] = l + n;
        }
        out = new byte[size];
        for (long e : H){ // sum, min, max
            M = Math.max(M, e);
            N = Math.min(N, e);
            K += e;
        }
        for (long e : H) L += (e >> 2); // mean
        L = L / size;
        for(long e : H) O += Math.pow(e - L, 2); //deviation
        O = (double) K / (size * 2);
        for (int n = 0; n < size; n++) { // build hash array
            h[n] = (H[n] * L * (N + 1));
            h[n] +=  (H[ size - n - 1] + K *  M ) * (N - 1);
            out[n] = (byte) Math.abs((h[n] / (O + 1)) % 0x10);
        }
        return out;
    }



    /**
     * Flexible hashing, with error detections
     * @param input data to hash
     * @param size given size for the hash
     * @return an int hash array
     * @throws IllegalArgumentException if the value is below limit.
     * @throws IllegalStateException if the generated hash corrupted.
     * */
    public static byte[] Flex(byte[] input, int size) throws Exception {
        if (input.length < 2){ throw new Exception("Flex: [The string is to short]");}
        else if (size <= 0) return new byte[0];
        byte[] out = Generator(input , size);
        int c = 0;
        for (int n: Generator(input , size)){
            if (n != out[c]) throw new Exception("Flex: [Hash corruption!]");
            c++;
        }
        return out;
    }

    // public static void main(String[] args) {
    //     for (int c : Flex(new byte[]{'a','c'},8)) System.out.print(Integer.toHexString(c));
    // }
}