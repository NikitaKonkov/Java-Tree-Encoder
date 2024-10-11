package Tree_Substitution;

public class FlexHash {
    /**
     * @Variable [Preset of special primes]
     * @Variable [Size Limitation]
     * @Variable [Output Array]*/
    static int[] Primes =  {53,97,193,389,769,1543,3079,6151,12289,24593,49157,98317,196613,393241,786433,1572869};// 16 Primes
    static int Size = 4046; // Size limit of hash
    static long[] Hex = new long[Size];
    private static final long[] Hash = new long[Size * 2];

    /**
     * Generator of the hash value, uses statistical calculations and simple hashing.
     *
     * @param data long array
     * @param size size of the hash
     * @return return the final hash
     */
    public static int[] Generator(long[] data, int size){
        int[] D = new int[size];
        long sum = 0, mean = 0, max = 0, min = data[0];
        double devia = 0.0;
        for (long e : data){ // sum, min, max
            max = Math.max(max, e);
            min = Math.min(min, e);
            sum += e;
        }
        for (long e : data){ mean += (e >> 2);} // mean
        mean = mean / Size;
        for(long e : data) { devia += Math.pow(e - mean, 2);} //deviation
        devia = (double) sum / (Size * 2);

        for (int i = 0; i < Size; i++) { // build hash array
            Hex[i] = (data[i] * mean * (min+1));
            Hex[i] +=  (data[ Size - i - 1] + sum *  max ) * (min-1);
            D[i] = (char) Math.abs((Hex[ i ] / (devia + 1)) % 16);
        }
        return D;
    }

    /**
     * Prepares the hash for the Generator.
     * @param input data to hash
     * @param hash_size given size for the hash
     * @return a hash char array*/
    public static int[] Preparatory(String input , int hash_size){
        Size = hash_size;
        int data = input.length();
        byte[] ram = new byte[data];
        for (int n = 0; n < data; n++){
            ram[n] = (byte) input.charAt(n);
        }
        int i = 0;
        for (byte s: ram) {
            data += Primes[ 15 - i % 16 ] * data + s;
            i++;
        }
        for(int n = 0; n < Size; n++){
            data += (Primes[ n % 16 ] * data + n);
            Hash[ n%256 ] = data + n;
        }
        return Generator(Hash,hash_size);
    }
    /**
     * Flexible hashing, with error detections
     * */
    public static int[] Flex(String input, int hash_size){
        if (hash_size < 2 || input.isEmpty()) throw new IllegalArgumentException("The value is below accepted limit.");
        int[] dub = Preparatory(input , hash_size);
        int c = 0;
        for (int n: Preparatory(input , hash_size)){
            if (n != dub[c]) throw new IllegalStateException("Hash corruption!");
            c++;
        }
        return dub;
    }

    public static void main(String[] args) {
        for (int c : Flex("2",32)) System.out.print(Integer.toHexString(c)+" ");
    }
}