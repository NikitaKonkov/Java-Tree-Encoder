package THF_Encryption;


public class StringEncoder2 {




    private static byte[] Generator(String text){
        return Extender(Disassembler(Fuser(text.getBytes(), Signer(text))));
    }
    private static byte[] Fuser(byte[] a, byte[] b) {
        byte[] r = new byte[a.length + b.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        return r;
    }
    private static byte[] Signer(String text){
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
    private static byte[] Disassembler(byte[] text) {
        int l = text.length * 2;
        byte[] out = new byte[l];
        for (int n = 0; n < text.length; n++) {
            int u = text[n] & 0xFF;
            out[n * 2] = (byte) ((u >> 4) & 0x0F);
            out[n * 2 + 1] = (byte) (u & 0x0F);
        }
        return out;
    }
    private static byte[] Assembler(byte[] text) {
        int l = text.length / 2;
        byte[] out = new byte[l];
        for (int n = 0; n < l; n++) {
            int ah = text[n * 2] & 0x0F;
            int al = text[n * 2 + 1] & 0x0F;
            out[n] = (byte) ((ah << 4) | al);
        }
        return out;
    }
    private static byte[] Extender(byte[] text){
        byte[] b = Disassembler(text);
        int e = 1;
        while (e * 2 < b.length){
            e *= 2;
        }
        return Fuser(text,FlexHash.Flex(text,e - text.length-1));
    }





    /**
     * This code defines a Cypher method that encrypts or decrypts a byte array by repeatedly applying array substitution based on the given parameter c.
     * @param text byte array
     * @param itr amount of iterations
     * @return cyphered text*/
    public static byte[] Cypher(byte[] text, long itr) throws Exception {
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
     * @return filtered string*/
    private static String Filter(byte[] input) throws IllegalStateException {
        byte a = 1, b = 1,c = 1, d = 1;
        int count = 0;
        StringBuilder out = new StringBuilder();
        for (byte value : input) {
            out.append((char) value);
            a -= value;
            b += value;
            c ^= value;
            d |= value;
            if (input[count+1] == a &&
                input[count+2] == b &&
                input[count+3] == c &&
                input[count+4] == d){count = -1;System.out.println("Filter: [OK]"); break;}
            count++;
        }
        if (count != -1) throw new IllegalStateException("Filter: [Data Corrupted]");
        return out.toString();
    }





    public static void main(String[] args) throws Exception {
        String text = "AEIOU123400000214256352AEIOU123400000214256352AEIOU123400";

        byte[] raw = Assembler(Cypher(Generator(text),111));
        printa(raw);
        raw = Assembler(Cypher(Disassembler(raw),-111));
        printa(raw);
        System.out.println(Filter(Assembler(Generator(text))));

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

}
