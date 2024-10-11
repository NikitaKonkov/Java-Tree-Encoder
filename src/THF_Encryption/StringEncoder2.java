package THF_Encryption;


public class StringEncoder2 {
    public static void main(String[] args) throws Exception {
        String text = "AEIOU123400000214256352AEIOU123400000214256352AEIOU123400";

        byte[] raw = Assembler(Cypher(Generator(text),111));
        printa(raw);
        raw = Assembler(Cypher(Disassembler(raw),-111));
        printa(raw);
        System.out.println(Filter(Assembler(Generator(text))));


    }
    private static byte[] Cypher(byte[] text, long c) throws Exception {
        if (c < 0){
            for (;c!=0;c++) text = ArrayDeSubstitution(text);
        }else {
            for (;c!=0;c--) text = ArraySubstitution(text);
        }
        return text;
    }
    private static byte[] Generator(String text){
        return Extender(Disassembler(Fuser(text.getBytes(), Signer(text))));
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
     * Signer creates a byte array with four check values and one reserved.
     * @param text string input
     * @return array with 5 bytes*/
    private static byte[] Signer(String text){
        byte a = 1, b = 1,c = 1, d = 1;
        byte[] t = text.getBytes();
        for (byte value : t) {
            a -= value;
            b += value;
            c ^= value;
            d |= value;
        }
        return new byte[]{a,b,c,d,0};
    }
    private static byte[] Fuser(byte[] a, byte[] b) {
        byte[] r = new byte[a.length + b.length];
        System.arraycopy(a, 0, r, 0, a.length);
        System.arraycopy(b, 0, r, a.length, b.length);
        return r;
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
    private static byte[] ArraySubstitution(byte[] text) throws Exception {
        int size = 1;
        final int MAX_SIZE = 28;
        while (size <= MAX_SIZE) {
            try {
                int[] ea = TreeIndexation.TreeArray(size);
                byte[] out = new byte[text.length];
                for (int n = 0; n < text.length; n++) {
                    out[ea[n]] = text[n];
                }
                return out;
            } catch (ArrayIndexOutOfBoundsException e) {
                size++;
            }
        }
        throw new Exception("ArraySubstitution: [Failed to perform Substitution after " + MAX_SIZE + " attempts]");
    }

    private static byte[] ArrayDeSubstitution(byte[] text) throws Exception {
        int size = 1;
        final int MAX_SIZE = 28;
        while (size <= MAX_SIZE) {
            try {
                int[] ea = TreeIndexation.TreeArray(size);
                byte[] out = new byte[text.length];
                for (int n = 0; n < text.length; n++) {
                    out[n] = text[ea[n]];
                }
                return out;
            } catch (ArrayIndexOutOfBoundsException e) {
                size++;
            }
        }
        throw new Exception("ArrayDeSubstitution: [Failed to perform Substitution after " + MAX_SIZE + " attempts]");
    }
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
}
