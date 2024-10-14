package THF_Encryption.Instruments;

import THF_Encryption.Hash.FlexHash;

public class Instruments {

    /**
     * Collection of instruments for the encryption algorithm.
     * @algorithms
     * {@code Generator, Fuser, Signer, Disassembler, Assembler, Extender}*/
    private static byte[] Generator(String text) throws Exception {
        return Assembler(Extender(Disassembler(Fuser(text.getBytes(), Signer(text)))));
    }
    public static byte[] Fuser(byte[] a, byte[] b) { // combines two byte arrays together
        byte[] out = new byte[a.length + b.length];
        System.arraycopy(a, 0, out, 0, a.length);
        System.arraycopy(b, 0, out, a.length, b.length);
        return out;
    }
    public static byte[] Signer(String text){ // 4 byte string signature
        byte A = 1, B = 1,C = 1, D = 1;
        byte[] t = text.getBytes();
        for (byte v : t) {
            A -= v;
            B += v;
            C ^= v;
            D |= v;
        }
        return new byte[]{A,B,C,D,0};
    }
    public static byte[] Disassembler(byte[] text) { // disassembles a byte array into nibbles
        int l = text.length * 2;
        byte[] out = new byte[l];
        for (int n = 0; n < text.length; n++) {
            int u = text[n] & 0xFF;
            out[n * 2] = (byte) ((u >> 4) & 0x0F);
            out[n * 2 + 1] = (byte) (u & 0x0F);
        }
        return out;
    }
    public static byte[] Assembler(byte[] text) { // assembles a nibble byte array into full bytes
        int l = text.length / 2;
        byte[] out = new byte[l];
        for (int n = 0; n < l; n++) {
            int A = text[n * 2] & 0x0F;
            int B = text[n * 2 + 1] & 0x0F;
            out[n] = (byte) ((A << 4) | B);
        }
        return out;
    }
    public static byte[] Extender(byte[] text) throws Exception { // extends the string to a specific size
        byte[] b = Disassembler(text);
        int e = 1;
        //if (!(text.length+1 > 0 && (text.length+1 & (text.length)) == 0)) System.out.println("HERE");
        while (e * 2 < b.length){
            e *= 2;
        }
        if (e == text.length) e*=2;
        return Fuser(text, FlexHash.Flex(text,e - text.length-1));
    }


    /**
     * Filter analyzes the string, checks if all data is correct, than returns the filtered string.
     * @param input assembled byte array
     * @return filtered string
     * @throws IllegalStateException if the data cannot be verified
     */
    public static String Filter(byte[] input) throws Exception {
        if (!(input.length+1 > 0 && (input.length+1 & (input.length)) == 0)) throw new Exception("Filter: [Data size not matching]");
        byte A = 1, B = 1, C = 1, D = 1;
        int c = 0;
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
                    input[c+4] == D) break;
            else if(c > input.length-3) throw new Exception("Filter: [ERROR]");
            c++;
        }
        return out.toString();
    }

    /**
     * Print byte array */
    public static void printa(byte[] array){
        for (byte ch: array){
            System.out.print(ch+" ");
        }
        System.out.println();
    }
    private static String ByteToHex(byte b) {
        char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        int v = b & 0xFF; // Convert to unsigned
        return new String(new char[] { HEX_ARRAY[v >>> 4], HEX_ARRAY[v & 0x0F] });
    }

}
