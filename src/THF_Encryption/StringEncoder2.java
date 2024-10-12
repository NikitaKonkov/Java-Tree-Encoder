package THF_Encryption;
public class StringEncoder2 {


    /**
     * Collection of instruments for the encryption algorithm.
     * @algorithm
     * {@code Generator, Fuser, Signer, Disassembler, Assembler, Extender}*/
    private static byte[] Generator(String text) throws Exception {
        return Assembler(Extender(Disassembler(Fuser(text.getBytes(), Signer(text)))));
    }
    private static byte[] Fuser(byte[] a, byte[] b) { // combines two byte arrays together
        byte[] out = new byte[a.length + b.length];
        System.arraycopy(a, 0, out, 0, a.length);
        System.arraycopy(b, 0, out, a.length, b.length);
        return out;
    }
    private static byte[] Signer(String text){ // 4 byte string signature
        byte a = 1, b = 1,c = 1, d = 1;
        byte[] t = text.getBytes();
        for (byte val : t) {
            a -= val;
            b += val;
            c ^= val;
            d |= val;
        }
        return new byte[]{a,b,c,d,0};
    }
    private static byte[] Disassembler(byte[] text) { // disassembles a byte array into nibbles
        int l = text.length * 2;
        byte[] out = new byte[l];
        for (int n = 0; n < text.length; n++) {
            int u = text[n] & 0xFF;
            out[n * 2] = (byte) ((u >> 4) & 0x0F);
            out[n * 2 + 1] = (byte) (u & 0x0F);
        }
        return out;
    }
    private static byte[] Assembler(byte[] text) { // assembles a nibble byte array into full bytes
        int l = text.length / 2;
        byte[] out = new byte[l];
        for (int n = 0; n < l; n++) {
            int ah = text[n * 2] & 0x0F;
            int al = text[n * 2 + 1] & 0x0F;
            out[n] = (byte) ((ah << 4) | al);
        }
        return out;
    }
    private static byte[] Extender(byte[] text) throws Exception { // extends the string to a specific size
        byte[] b = Disassembler(text);
        int e = 1;
        while (e * 2 < b.length){
            e *= 2;
        }
        return Fuser(text,FlexHash.Flex(text,e - text.length-1));
    }



    /**
     * Defines a Cypher method that encrypts or decrypts a byte array by repeatedly applying array substitution based on the given parameter c.
     * @param text byte array
     * @param itr amount of iterations
     * @return cyphered text*/
    private static byte[] Cypher(byte[] text, long itr) throws Exception {
        if (itr == 0) {
            return text;
        }
        boolean rev = itr < 0;
        itr = Math.abs(itr);
        for (long i = 0; i < itr; i++) {
            text = Substitution(text, rev);
        }
        return text;
    }
    private static byte[] Substitution(byte[] text, boolean rev) throws Exception {
        int size = 1;
        while (size <= 28) {
            try {
                int[] A = TreeIndexation.TreeArray(size);
                byte[] b = new byte[text.length];
                for (int i = 0; i < text.length; i++) {
                    int ni = rev ? A[i] : i;
                    b[ni] = text[i];
                }
                return b;
            } catch (ArrayIndexOutOfBoundsException e) {
                size++;
            }
        }
        throw new Exception(String.format("Failed to perform substitution after %d attempts", 28));
    }



    /**
     * Filter analyzes the string, checks if all data is correct, than returns the filtered string.
     * @param input assembled byte array
     * @return filtered string
     * @throws IllegalStateException if the data cannot be verified
     */
    private static String Filter(byte[] input) throws Exception {
        if (!(input.length+1 > 0 && (input.length+1 & (input.length)) == 0)) throw new Exception("Filter: [Data size not matching]");

        byte A = 1, B = 1, C = 1, D = 1;
        int c = 0;
        System.out.println(input.length);
        StringBuilder out = new StringBuilder();
        for (byte v : input) {
            out.append((char) v);
            A -= v;
            B += v;
            C ^= v;
            D |= v;
            if (input[c+1] == A &&
                input[c+2] == B &&
                input[c+3] == C &&
                input[c+4] == D){System.out.println("Filter: [OK]"); break;}
            else if(c > input.length-3) throw new Exception("Filter: [ERROR]");
            c++;
        }
        return out.toString();
    }

    private static void printa(byte[] array){
        for (byte ch: array){
            System.out.print(ByteToHex(ch));
        }
        System.out.println();
    }
    private static String ByteToHex(byte b) {
        char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        int v = b & 0xFF; // Convert to unsigned
        return new String(new char[] { HEX_ARRAY[v >>> 4], HEX_ARRAY[v & 0x0F] });
    }

    public static void main(String[] args) throws Exception {
        String text = "abc123212321232123abc123212321232123abc123212321232123abc123212321232123abc123212321232123abc123212321232123abc123212321232123abc123212321232123";

        byte[] raw = Generator(text);

        printa(raw);

        System.out.println(raw.length);

        System.out.println(Filter(raw));


    }


}
